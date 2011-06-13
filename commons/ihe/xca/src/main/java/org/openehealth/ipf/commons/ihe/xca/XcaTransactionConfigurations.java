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
package org.openehealth.ipf.commons.ihe.xca;

import org.openehealth.ipf.commons.ihe.core.IheConfigurator;
import org.openehealth.ipf.commons.ihe.core.IheRegistry;
import org.openehealth.ipf.commons.ihe.ws.ItiServiceInfo;
import org.openehealth.ipf.commons.ihe.xca.iti38.Iti38PortType;
import org.openehealth.ipf.commons.ihe.xca.iti38.asyncresponse.Iti38AsyncResponsePortType;
import org.openehealth.ipf.commons.ihe.xca.iti39.Iti39PortType;
import org.openehealth.ipf.commons.ihe.xca.iti39.asyncresponse.Iti39AsyncResponsePortType;

import javax.xml.namespace.QName;

import static org.openehealth.ipf.commons.ihe.core.IpfInteractionId.*;

/**
 * @author Dmytro Rud
 */
public class XcaTransactionConfigurations implements IheConfigurator {

    @Override
    public void configure(IheRegistry registry) {
        String NS_URI = "urn:ihe:iti:xds-b:2007";
        Class<ItiServiceInfo> clazz = ItiServiceInfo.class;

        registry.register(ITI_38, clazz, new ItiServiceInfo(
                new QName(NS_URI, "RespondingGateway_Service", "ihe"),
                Iti38PortType.class,
                new QName(NS_URI, "RespondingGateway_Binding_Soap12", "ihe"),
                false,
                "wsdl/iti38.wsdl",
                true,
                false,
                true));

        registry.register(ITI_38_ASYNC_RESPONSE, clazz, new ItiServiceInfo(
                new QName(NS_URI, "RespondingGateway_Response_Service", "ihe"),
                Iti38AsyncResponsePortType.class,
                new QName(NS_URI, "RespondingGateway_Response_Binding_Soap12", "ihe"),
                false,
                "wsdl/iti38-asyncresponse.wsdl",
                true,
                false,
                false));

        registry.register(ITI_39, clazz, new ItiServiceInfo(
                new QName(NS_URI, "RespondingGateway_Service", "ihe"),
                Iti39PortType.class,
                new QName(NS_URI, "RespondingGateway_Binding_Soap12", "ihe"),
                true,
                "wsdl/iti39.wsdl",
                true,
                false,
                false));

        registry.register(ITI_39_ASYNC_RESPONSE, clazz, new ItiServiceInfo(
                new QName(NS_URI, "RespondingGateway_Response_Service", "ihe"),
                Iti39AsyncResponsePortType.class,
                new QName(NS_URI, "RespondingGateway_Response_Binding_Soap12", "ihe"),
                false,
                "wsdl/iti39-asyncresponse.wsdl",
                true,
                false,
                false));
    }

}
