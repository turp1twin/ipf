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
package org.openehealth.ipf.commons.ihe.xds;

import org.openehealth.ipf.commons.ihe.ws.ItiServiceInfo;
import org.openehealth.ipf.commons.ihe.xds.iti14.Iti14PortType;
import org.openehealth.ipf.commons.ihe.xds.iti15.Iti15PortType;
import org.openehealth.ipf.commons.ihe.xds.iti16.Iti16PortType;
import org.openehealth.ipf.commons.ihe.xds.iti18.Iti18PortType;
import org.openehealth.ipf.commons.ihe.xds.iti41.Iti41PortType;
import org.openehealth.ipf.commons.ihe.xds.iti42.Iti42PortType;
import org.openehealth.ipf.commons.ihe.xds.iti43.Iti43PortType;

import javax.xml.namespace.QName;

/**
 * @author Dmytro Rud
 */
public class XdsTransactionConfigurations {
    private static final String NS_URI = "urn:ihe:iti:xds:2007";

    public static final ItiServiceInfo ITI_14_CONFIG = new ItiServiceInfo(
            new QName(NS_URI, "DocumentRegistry_Service", "ihe"),
            Iti14PortType.class,
            new QName(NS_URI, "DocumentRegistry_Binding_Soap11", "ihe"),
            false,
            "wsdl/iti14.wsdl",
            false,
            false,
            false);

    public static final ItiServiceInfo ITI_15_CONFIG = new ItiServiceInfo(
            new QName(NS_URI, "DocumentRepository_Service", "ihe"),
            Iti15PortType.class,
            new QName(NS_URI, "DocumentRepository_Binding_Soap11", "ihe"),
            false,
            "wsdl/iti15.wsdl",
            false,
            true,
            false);

    public static final ItiServiceInfo ITI_16_CONFIG = new ItiServiceInfo(
            new QName(NS_URI, "DocumentRegistry_Service", "ihe"),
            Iti16PortType.class,
            new QName(NS_URI, "DocumentRegistry_Binding_Soap11", "ihe"),
            false,
            "wsdl/iti16.wsdl",
            false,
            false,
            true);

    public static final ItiServiceInfo ITI_18_CONFIG = new ItiServiceInfo(
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRegistry_Service", "ihe"),
            Iti18PortType.class,
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRegistry_Binding_Soap12", "ihe"),
            false,
            "wsdl/iti18.wsdl",
            true,
            false,
            true);

    public static final ItiServiceInfo ITI_41_CONFIG = new ItiServiceInfo(
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRepository_Service", "ihe"),
            Iti41PortType.class,
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRepository_Binding_Soap12", "ihe"),
            true,
            "wsdl/iti41.wsdl",
            true,
            false,
            false);

    public static final ItiServiceInfo ITI_42_CONFIG = new ItiServiceInfo(
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRegistry_Service", "ihe"),
            Iti42PortType.class,
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRegistry_Binding_Soap12", "ihe"),
            false,
            "wsdl/iti42.wsdl",
            true,
            false,
            false);

    public static final ItiServiceInfo ITI_43_CONFIG = new ItiServiceInfo(
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRepository_Service", "ihe"),
            Iti43PortType.class,
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRepository_Binding_Soap12", "ihe"),
            true,
            "wsdl/iti43.wsdl",
            true,
            false,
            false);

}
