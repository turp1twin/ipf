/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.xds.core.audit;

import lombok.Getter;
import lombok.Setter;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRegistryPackage;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLSubmitObjectsRequest;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary;

import java.util.List;

/**
 * XDS audit dataset specific for submission-related transactions.
 * @author Dmytro Rud
 */
public class XdsSubmitAuditDataset extends XdsAuditDataset {
    private static final long serialVersionUID = -4043273663994079282L;

    // submission set unique ID
    @Getter @Setter private String submissionSetUuid;

    /**
     * @param serverSide
     *            specifies whether this audit dataset will be used on the
     *            server side (<code>true</code>) or on the client side (
     *            <code>false</code>)
     */
    public XdsSubmitAuditDataset(boolean serverSide) {
        super(serverSide);
    }

    /**
     * Enriches the set with fields extracted from a submit objects request POJO.
     *
     * @param ebXML
     *      a {@link EbXMLSubmitObjectsRequest} as POJO
     */
    public void enrichDatasetFromSubmitObjectsRequest(EbXMLSubmitObjectsRequest ebXML) {
        List<EbXMLRegistryPackage> submissionSets = 
            ebXML.getRegistryPackages(Vocabulary.SUBMISSION_SET_CLASS_NODE);
        
        for (EbXMLRegistryPackage submissionSet : submissionSets) {
            String patientID = submissionSet.getExternalIdentifierValue(
                    Vocabulary.SUBMISSION_SET_PATIENT_ID_EXTERNAL_ID);            
            getPatientIds().add(patientID);
            
            String uniqueID = submissionSet.getExternalIdentifierValue(
                    Vocabulary.SUBMISSION_SET_UNIQUE_ID_EXTERNAL_ID);
            setSubmissionSetUuid(uniqueID);
        }
    }
}
