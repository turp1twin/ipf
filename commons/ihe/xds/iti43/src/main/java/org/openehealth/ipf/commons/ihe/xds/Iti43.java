package org.openehealth.ipf.commons.ihe.xds;

import javax.xml.namespace.QName;

import org.openehealth.ipf.commons.ihe.xds.core.ItiClientFactory;
import org.openehealth.ipf.commons.ihe.xds.core.ItiServiceFactory;
import org.openehealth.ipf.commons.ihe.xds.core.ItiServiceInfo;

/**
 * Provides access to the service and client factories for ITI-43.
 * @author Jens Riemschneider
 */
public class Iti43 {
    private final static ItiServiceInfo ITI_43 = new ItiServiceInfo(
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRepository_Service", "ihe"),
            Iti43PortType.class,
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRepository_Binding_Soap12", "ihe"),
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRepository_Port_Soap11", "ihe"),
            new QName("urn:ihe:iti:xds-b:2007", "DocumentRepository_Port_Soap12", "ihe"),
            true,
            "wsdl/iti43.wsdl",
            true,
            false);
           
    /**
     * Returns a factory for client stubs of the ITI-43 service.
     * @param soap11 
     *          whether SOAP 1.1 should be used instead of SOAP 1.2 for XDS.b 
     *          transactions.
     * @param audit
     *          <code>true</code> to enable auditing.
     * @param allowIncompleteAudit
     *          <code>true</code> if audit entries are logged even if not all 
     *          necessary data is available. 
     * @param serviceUrl
     *          the URL of the service to use.
     * @return the client factory.
     */
    public static ItiClientFactory getClientFactory(boolean soap11, boolean audit, boolean allowIncompleteAudit, String serviceUrl) {        
        return new ItiClientFactory(ITI_43, soap11, audit ? new Iti43ClientAuditStrategy(allowIncompleteAudit) : null, serviceUrl);
    }

    /**
     * Returns a factory for ITI-43 services.
     * @param audit
     *          <code>true</code> to enable auditing.
     * @param allowIncompleteAudit
     *          <code>true</code> if audit entries are logged even if not all 
     *          necessary data is available. 
     * @param serviceAddress
     *          the address used to publish the service in the current context.  
     * @return the service factory.
     */
    public static ItiServiceFactory getServiceFactory(boolean audit, boolean allowIncompleteAudit, String serviceAddress) {
        return new ItiServiceFactory(ITI_43, audit ? new Iti43ServerAuditStrategy(allowIncompleteAudit) : null, serviceAddress);
    }
}
