/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.pixpdq.iti21;

import org.apache.camel.CamelContext;
import org.openehealth.ipf.commons.ihe.core.InteractionId;
import org.openehealth.ipf.commons.ihe.core.IpfInteractionId;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpAuditStrategy;
import org.openehealth.ipf.platform.camel.ihe.mllp.core.MllpComponent;
import org.openehealth.ipf.platform.camel.ihe.pixpdq.pdqcore.PdqClientAuditStrategy;
import org.openehealth.ipf.platform.camel.ihe.pixpdq.pdqcore.PdqServerAuditStrategy;

/**
 * Camel component for ITI-21 (PDQ).
 * @author Dmytro Rud
 */
public class Iti21Component extends MllpComponent {
    private static final MllpAuditStrategy CLIENT_AUDIT_STRATEGY =
        new PdqClientAuditStrategy("PDQ");
    private static final MllpAuditStrategy SERVER_AUDIT_STRATEGY = 
        new PdqServerAuditStrategy("PDQ");

    
    public Iti21Component() {
        super();
    }

    public Iti21Component(CamelContext camelContext) {
        super(camelContext);
    }
    
    @Override
    public MllpAuditStrategy getClientAuditStrategy() {
        return CLIENT_AUDIT_STRATEGY;
    }

    @Override
    public MllpAuditStrategy getServerAuditStrategy() {
        return SERVER_AUDIT_STRATEGY;
    }

    @Override
    public InteractionId getInteractionId() {
        return IpfInteractionId.ITI_21;
    }
}
