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
package org.openehealth.ipf.commons.ihe.xcpd;

import org.openehealth.ipf.commons.ihe.core.IheConfigurator;
import org.openehealth.ipf.commons.ihe.core.XmlValidationProfileRegistry;
import org.openehealth.ipf.commons.ihe.hl7v3.Hl7v3ServiceInfo;
import org.openehealth.ipf.commons.ihe.ws.WebServiceTransactionConfigurationRegistry;
import org.openehealth.ipf.commons.ihe.xcpd.iti55.Iti55PortType;
import org.openehealth.ipf.commons.ihe.xcpd.iti55.asyncresponse.Iti55AsyncResponsePortType;
import org.openehealth.ipf.commons.ihe.xcpd.iti56.Iti56PortType;
import org.openehealth.ipf.commons.ihe.xcpd.iti56.asyncresponse.Iti56AsyncResponsePortType;

import javax.xml.namespace.QName;

import static org.openehealth.ipf.commons.ihe.core.IpfInteractionId.*;

/**
 * @author Dmytro Rud
 */
public class XcpdTransactionConfigurations implements IheConfigurator {

    @Override
    public void configure() {
        registerXmlValidationProfiles();
        registerTransactionConfigurations();
    }


    private static void registerXmlValidationProfiles() {
        XmlValidationProfileRegistry registry = XmlValidationProfileRegistry.instance();

        registry.registerValidationProfiles(ITI_55,
                new String[][]{new String[]{"PRPA_IN201305UV02", "iti55/PRPA_IN201305UV02"}},
                new String[][]{new String[]{"PRPA_IN201306UV02", "iti55/PRPA_IN201306UV02"}});

        registry.registerValidationProfiles(ITI_56,
                new String[][]{new String[]{"PatientLocationQueryRequest", null, "IHE/XCPD_PLQ"}},
                new String[][]{new String[]{"PatientLocationQueryResponse", null, "IHE/XCPD_PLQ"}});
    }


    private static void registerTransactionConfigurations() {
        final String NS_URI = "urn:ihe:iti:xcpd:2009";

        WebServiceTransactionConfigurationRegistry registry =
                WebServiceTransactionConfigurationRegistry.instance();

        registry.registerConfiguration(ITI_55, new Hl7v3ServiceInfo(
                new QName(NS_URI, "RespondingGateway_Service", "xcpd"),
                Iti55PortType.class,
                new QName(NS_URI, "RespondingGateway_Binding_Soap12", "xcpd"),
                false,
                "wsdl/iti55/iti55-raw.wsdl",
                "PRPA_IN201306UV02",
                true,
                false));

        registry.registerConfiguration(ITI_55_ASYNC_RESPONSE, new Hl7v3ServiceInfo(
                new QName(NS_URI, "RespondingGateway_Response_Service", "xcpd"),
                Iti55AsyncResponsePortType.class,
                new QName(NS_URI, "RespondingGateway_Response_Binding_Soap12", "xcpd"),
                false,
                "wsdl/iti55/iti55-asyncresponse-raw.wsdl",
                null,
                false,
                false));

        registry.registerConfiguration(ITI_56, new Hl7v3ServiceInfo(
                new QName(NS_URI, "RespondingGateway_Service", "xcpd"),
                Iti56PortType.class,
                new QName(NS_URI, "RespondingGateway_Binding_Soap12", "xcpd"),
                false,
                "wsdl/iti56/iti56-raw.wsdl",
                null,
                false,
                true));

        registry.registerConfiguration(ITI_56_ASYNC_RESPONSE, new Hl7v3ServiceInfo(
                new QName(NS_URI, "RespondingGateway_Response_Service", "xcpd"),
                Iti56AsyncResponsePortType.class,
                new QName(NS_URI, "RespondingGateway_Response_Binding_Soap12", "xcpd"),
                false,
                "wsdl/iti56/iti56-asyncresponse-raw.wsdl",
                null,
                false,
                false));
    }

}
