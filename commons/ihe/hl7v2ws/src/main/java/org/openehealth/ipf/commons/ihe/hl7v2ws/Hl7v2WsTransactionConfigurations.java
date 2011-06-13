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
package org.openehealth.ipf.commons.ihe.hl7v2ws;

import org.openehealth.ipf.commons.ihe.core.IheConfigurator;
import org.openehealth.ipf.commons.ihe.core.IheRegistry;
import org.openehealth.ipf.commons.ihe.core.IpfInteractionId;
import org.openehealth.ipf.commons.ihe.hl7v2ws.pcd01.Pcd01PortType;
import org.openehealth.ipf.commons.ihe.ws.ItiServiceInfo;

import javax.xml.namespace.QName;

/**
 * @author Dmytro Rud
 */
public class Hl7v2WsTransactionConfigurations implements IheConfigurator {

    @Override
    public void configure(IheRegistry registry) {
        String NS_URI = "urn:ihe:pcd:dec:2010";

        registry.register(IpfInteractionId.PCD_01, ItiServiceInfo.class, new ItiServiceInfo(
                new QName(NS_URI, "DeviceObservationConsumer_Service", "ihe"),
                Pcd01PortType.class,
                new QName(NS_URI, "DeviceObservationConsumer_Binding_Soap12", "ihe"),
                false,
                "wsdl/pcd01/pcd01.wsdl",
                true,
                false,
                false));
    }
}
