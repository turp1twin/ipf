//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.9-03/31/2009 04:14 PM(snajper)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.05.19 at 10:15:07 AM CEST 
//


package org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:query:xsd:2.1}SpecificationLinkFilter" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:query:xsd:2.1}RegistryObjectQuery" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:query:xsd:2.1}RegistryEntryQuery" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "specificationLinkFilter",
    "registryObjectQuery",
    "registryEntryQuery"
})
@XmlRootElement(name = "SpecificationLinkBranch")
public class SpecificationLinkBranch {

    @XmlElement(name = "SpecificationLinkFilter")
    protected FilterType specificationLinkFilter;
    @XmlElement(name = "RegistryObjectQuery")
    protected RegistryObjectQueryType registryObjectQuery;
    @XmlElement(name = "RegistryEntryQuery")
    protected RegistryEntryQueryType registryEntryQuery;

    /**
     * Gets the value of the specificationLinkFilter property.
     * 
     * @return
     *     possible object is
     *     {@link FilterType }
     *     
     */
    public FilterType getSpecificationLinkFilter() {
        return specificationLinkFilter;
    }

    /**
     * Sets the value of the specificationLinkFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterType }
     *     
     */
    public void setSpecificationLinkFilter(FilterType value) {
        this.specificationLinkFilter = value;
    }

    /**
     * Gets the value of the registryObjectQuery property.
     * 
     * @return
     *     possible object is
     *     {@link RegistryObjectQueryType }
     *     
     */
    public RegistryObjectQueryType getRegistryObjectQuery() {
        return registryObjectQuery;
    }

    /**
     * Sets the value of the registryObjectQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistryObjectQueryType }
     *     
     */
    public void setRegistryObjectQuery(RegistryObjectQueryType value) {
        this.registryObjectQuery = value;
    }

    /**
     * Gets the value of the registryEntryQuery property.
     * 
     * @return
     *     possible object is
     *     {@link RegistryEntryQueryType }
     *     
     */
    public RegistryEntryQueryType getRegistryEntryQuery() {
        return registryEntryQuery;
    }

    /**
     * Sets the value of the registryEntryQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistryEntryQueryType }
     *     
     */
    public void setRegistryEntryQuery(RegistryEntryQueryType value) {
        this.registryEntryQuery = value;
    }

}
