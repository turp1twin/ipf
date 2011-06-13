/*
 * Copyright 2011 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.hl7v2ws.pcd01;

import org.openehealth.ipf.commons.ihe.core.InteractionId;
import org.openehealth.ipf.commons.ihe.core.IpfInteractionId;
import org.openehealth.ipf.platform.camel.ihe.hl7v2ws.AbstractHl7v2WebService;
import org.openehealth.ipf.platform.camel.ihe.hl7v2ws.AbstractHl7v2WsComponent;

/**
 * Camel component for the IHE PCD-01 transaction.
 */
public class Pcd01Component extends AbstractHl7v2WsComponent {

    @Override
    protected Class<? extends AbstractHl7v2WebService> getServiceClass() {
        return Pcd01Service.class;
    }

    @Override
    public InteractionId getInteractionId() {
        return IpfInteractionId.PCD_01;
    }
}
