/*
 * Copyright 2010 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.hl7v3;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.openehealth.ipf.commons.ihe.core.IheRegistry;
import org.openehealth.ipf.commons.ihe.core.InteractionId;
import org.openehealth.ipf.commons.ihe.hl7v3.Hl7v3ValidationProfile;
import org.openehealth.ipf.commons.ihe.hl7v3.Hl7v3Validator;

import static org.openehealth.ipf.commons.ihe.core.IpfInteractionId.*;

/**
 * Validating processors for HL7v3-based IPF components.
 * @author Dmytro Rud
 */
abstract public class Hl7v3CamelValidators {
    private static final Hl7v3Validator VALIDATOR = new Hl7v3Validator();

    /**
     * Returns a validating processor for ITI-44 request messages
     * (Patient Identity Feed v3).
     */
    public static Processor iti44RequestValidator() {
        return validatingProcessor(ITI_44_PIX, true);
    }
    
    /**
     * Returns a validating processor for ITI-44 response messages
     * (Patient Identity Feed v3).
     */
    public static Processor iti44ResponseValidator() {
        return validatingProcessor(ITI_44_PIX, false);
    }

    /**
     * Returns a validating processor for ITI-45 request messages
     * (Patient Identity Query v3).
     */
    public static Processor iti45RequestValidator() {
        return validatingProcessor(ITI_45, true);
    }
    
    /**
     * Returns a validating processor for ITI-45 response messages
     * (Patient Identity Query v3).
     */
    public static Processor iti45ResponseValidator() {
        return validatingProcessor(ITI_45, false);
    }

    /**
     * Returns a validating processor for ITI-46 request messages
     * (Patient Identity Update Notification v3).
     */
    public static Processor iti46RequestValidator() {
        return validatingProcessor(ITI_46, true);
    }
    
    /**
     * Returns a validating processor for ITI-46 response messages
     * (Patient Identity Update Notification v3).
     */
    public static Processor iti46ResponseValidator() {
        return validatingProcessor(ITI_46, false);
    }

    /**
     * Returns a validating processor for ITI-47 request messages
     * (Patient Demographics Query v3).
     */
    public static Processor iti47RequestValidator() {
        return validatingProcessor(ITI_47, true);
    }
    
    /**
     * Returns a validating processor for ITI-47 response messages
     * (Patient Demographics Query v3).
     */
    public static Processor iti47ResponseValidator() {
        return validatingProcessor(ITI_47, false);
    }
    
    /**
     * Returns a validating processor for ITI-55 request messages
     * (Cross-Gateway Patient Discovery).
     */
    public static Processor iti55RequestValidator() {
        return validatingProcessor(ITI_55, true);
    }

    /**
     * Returns a validating processor for ITI-55 response messages
     * (Cross-Gateway Patient Discovery).
     */
    public static Processor iti55ResponseValidator() {
        return validatingProcessor(ITI_55, false);
    }

    /**
     * Returns a validating processor for ITI-56 request messages
     * (Patient Location Query).
     */
    public static Processor iti56RequestValidator() {
        return validatingProcessor(ITI_56, true);
    }

    /**
     * Returns a validating processor for ITI-56 response messages
     * (Patient Location Query).
     */
    public static Processor iti56ResponseValidator() {
        return validatingProcessor(ITI_56, false);
    }

    /**
     * Returns a validating processor for PCC-1 request messages
     * (Query for Existing Data).
     */
    public static Processor pcc1RequestValidator() {
        return validatingProcessor(PCC_1, true);
    }

    /**
     * Returns a validating processor for PCC-1 response messages
     * (Query for Existing Data).
     */
    public static Processor pcc1ResponseValidator() {
        return validatingProcessor(PCC_1, false);
    }


    private static Processor validatingProcessor(InteractionId interactionId, boolean request) {
        Hl7v3ValidationProfile profile = IheRegistry.get(interactionId, Hl7v3ValidationProfile.class);
        final String[][] profiles = request ?
                profile.getRequestValidationProfile() :
                profile.getResponseValidationProfile();
        return new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                doValidation(exchange, profiles);
            }
        };
    }

    private static void doValidation(Exchange exchange, String[][] profiles) {
        String message = exchange.getIn().getBody(String.class);
        VALIDATOR.validate(message, profiles);
    }
}
