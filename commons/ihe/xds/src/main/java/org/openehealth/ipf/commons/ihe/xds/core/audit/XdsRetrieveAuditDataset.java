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

/**
 * XDS audit dataset specific for retrieval-related transactions.
 * @author Dmytro Rud
 */
public class XdsRetrieveAuditDataset extends XdsAuditDataset {
    private static final long serialVersionUID = -8776033207572005899L;

    @Getter @Setter private String[] documentUniqueIds;
    @Getter @Setter private String[] repositoryUniqueIds;
    @Getter @Setter private String[] homeCommunityIds;

    public XdsRetrieveAuditDataset(boolean serverSide) {
        super(serverSide);
    }
}
