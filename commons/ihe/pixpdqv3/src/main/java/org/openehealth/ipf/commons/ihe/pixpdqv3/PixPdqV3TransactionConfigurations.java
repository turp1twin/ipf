/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.pixpdqv3;

import org.openehealth.ipf.commons.ihe.core.IheConfigurator;
import org.openehealth.ipf.commons.ihe.core.XmlValidationProfileRegistry;
import org.openehealth.ipf.commons.ihe.hl7v3.Hl7v3ContinuationAwareServiceInfo;
import org.openehealth.ipf.commons.ihe.hl7v3.Hl7v3ServiceInfo;
import org.openehealth.ipf.commons.ihe.pixpdqv3.iti44.Iti44PixPortType;
import org.openehealth.ipf.commons.ihe.pixpdqv3.iti44.Iti44XdsPortType;
import org.openehealth.ipf.commons.ihe.pixpdqv3.iti45.Iti45PortType;
import org.openehealth.ipf.commons.ihe.pixpdqv3.iti46.Iti46PortType;
import org.openehealth.ipf.commons.ihe.pixpdqv3.iti47.Iti47PortType;
import org.openehealth.ipf.commons.ihe.pixpdqv3.pcc1.Pcc1PortType;
import org.openehealth.ipf.commons.ihe.ws.WebServiceTransactionConfigurationRegistry;

import javax.xml.namespace.QName;

import static org.openehealth.ipf.commons.ihe.core.IpfInteractionId.*;

/**
 * Configurations of HL7v3-based eHealth transactions supported by IPF.
 * @author Dmytro Rud
 */
public class PixPdqV3TransactionConfigurations implements IheConfigurator {

    @Override
    public void configure() {
        doRegisterValidationProfiles();
        doRegisterTransactionConfigurations();
    }


    private static void doRegisterValidationProfiles() {
        XmlValidationProfileRegistry registry = XmlValidationProfileRegistry.instance();

        String[][] ITI_44_REQUEST_VALIDATION_PROFILES = new String[][] {
                new String[] {"PRPA_IN201301UV02", null},
                new String[] {"PRPA_IN201302UV02", null},
                new String[] {"PRPA_IN201304UV02", null}
        };

        String[][] ITI_44_RESPONSE_VALIDATION_PROFILES = new String[][] {
                new String[] {"MCCI_IN000002UV01", null}
        };

        registry.registerValidationProfiles(ITI_44_PIX,
                ITI_44_REQUEST_VALIDATION_PROFILES,
                ITI_44_RESPONSE_VALIDATION_PROFILES);

        registry.registerValidationProfiles(ITI_44_XDS,
                ITI_44_REQUEST_VALIDATION_PROFILES,
                ITI_44_RESPONSE_VALIDATION_PROFILES);

        registry.registerValidationProfiles(ITI_45,
                new String[][]{new String[]{"PRPA_IN201309UV02", null}},
                new String[][]{new String[]{"PRPA_IN201310UV02", null}});

        registry.registerValidationProfiles(ITI_46,
                new String[][]{new String[]{"PRPA_IN201302UV02", null}},
                new String[][]{new String[]{"MCCI_IN000002UV01", null}});

        registry.registerValidationProfiles(ITI_47,
                new String[][]{
                        new String[]{"PRPA_IN201305UV02", "iti47/PRPA_IN201305UV02"},
                        new String[]{"QUQI_IN000003UV01", null},
                        new String[]{"QUQI_IN000003UV01_Cancel", null},
                },
                new String[][]{
                        new String[]{"PRPA_IN201306UV02", "iti47/PRPA_IN201306UV02"},
                        new String[]{"MCCI_IN000002UV01", null}
                });

        registry.registerValidationProfiles(PCD_01,
                new String[][]{
                        new String[]{"QUPC_IN043100UV01", null},
                        new String[]{"QUQI_IN000003UV01", null},
                        new String[]{"QUQI_IN000003UV01_Cancel", null}
                },
                new String[][]{
                        new String[]{"QUPC_IN043200UV01", null},
                        new String[]{"MCCI_IN000002UV01", null}
                });

    }


    private static void doRegisterTransactionConfigurations() {
        String PIXV3_NS_URI = "urn:ihe:iti:pixv3:2007";
        String PDQV3_NS_URI = "urn:ihe:iti:pdqv3:2007";
        String XDS_NS_URI   = "urn:ihe:iti:xds-b:2007";
        String PCC_NS_URI   = "urn:ihe:pcc:qed:2007";

        WebServiceTransactionConfigurationRegistry registry =
                WebServiceTransactionConfigurationRegistry.instance();

        registry.registerConfiguration(ITI_44_PIX, new Hl7v3ServiceInfo(
                new QName(PIXV3_NS_URI, "PIXManager_Service", "ihe"),
                Iti44PixPortType.class,
                new QName(PIXV3_NS_URI, "PIXManager_Binding_Soap12", "ihe"),
                false,
                "wsdl/iti44/iti44-pix-raw.wsdl",
                "MCCI_IN000002UV01",
                false,
                false));

        registry.registerConfiguration(ITI_44_XDS, new Hl7v3ServiceInfo(
                new QName(XDS_NS_URI, "DocumentRegistry_Service", "ihe"),
                Iti44XdsPortType.class,
                new QName(XDS_NS_URI, "DocumentRegistry_Binding_Soap12", "ihe"),
                false,
                "wsdl/iti44/iti44-xds-raw.wsdl",
                "MCCI_IN000002UV01",
                false,
                false));

        registry.registerConfiguration(ITI_45, new Hl7v3ServiceInfo(
                new QName(PIXV3_NS_URI, "PIXManager_Service", "ihe"),
                Iti45PortType.class,
                new QName(PIXV3_NS_URI, "PIXManager_Binding_Soap12", "ihe"),
                false,
                "wsdl/iti45/iti45-raw.wsdl",
                "PRPA_IN201310UV02",
                true,
                false));

        registry.registerConfiguration(ITI_46, new Hl7v3ServiceInfo(
                new QName(PIXV3_NS_URI, "PIXConsumer_Service", "ihe"),
                Iti46PortType.class,
                new QName(PIXV3_NS_URI, "PIXConsumer_Binding_Soap12", "ihe"),
                false,
                "wsdl/iti46/iti46-raw.wsdl",
                "MCCI_IN000002UV01",
                false,
                false));

        registry.registerConfiguration(ITI_47, new Hl7v3ContinuationAwareServiceInfo(
                new QName(PDQV3_NS_URI, "PDSupplier_Service", "ihe"),
                Iti47PortType.class,
                new QName(PDQV3_NS_URI, "PDSupplier_Binding_Soap12", "ihe"),
                false,
                "wsdl/iti47/iti47-raw.wsdl",
                "PRPA_IN201306UV02",
                true,
                false,
                "PRPA_IN201305UV02",
                "PRPA_IN201306UV02"));

        registry.registerConfiguration(PCC_1, new Hl7v3ContinuationAwareServiceInfo(
                new QName(PCC_NS_URI, "ClinicalDataSource_Service", "qed"),
                Pcc1PortType.class,
                new QName(PCC_NS_URI, "ClinicalDataSource_Binding_Soap12", "qed"),
                false,
                "wsdl/pcc1/pcc1-raw.wsdl",
                "QUPC_IN043200UV01",
                true,
                false,
                "QUPC_IN043100UV01",
                "QUPC_IN043200UV01"));
    }

}
