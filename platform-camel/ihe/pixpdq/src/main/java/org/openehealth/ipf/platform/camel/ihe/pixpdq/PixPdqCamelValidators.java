/*
 * Copyright 2010 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.pixpdq;

import ca.uhn.hl7v2.parser.Parser;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.openehealth.ipf.commons.ihe.core.IheRegistry;
import org.openehealth.ipf.commons.ihe.core.IpfInteractionId;
import org.openehealth.ipf.commons.ihe.hl7v2.Hl7v2TransactionConfiguration;
import org.openehealth.ipf.commons.ihe.pixpdq.MessageAdapterValidator;
import org.openehealth.ipf.modules.hl7dsl.MessageAdapter;
import org.openehealth.ipf.platform.camel.ihe.hl7v2.Hl7v2MarshalUtils;

import static org.openehealth.ipf.commons.ihe.core.IpfInteractionId.*;

/**
 * Validating processors for MLLP-based IPF IHE components.
 * @author Dmytro Rud
 */
abstract public class PixPdqCamelValidators {
    private static final MessageAdapterValidator VALIDATOR = new MessageAdapterValidator(); 

    /**
     * Returns a validating processor for ITI-8 request messages
     * (Patient Identity Feed).
     */
    public static Processor iti8RequestValidator() {
        return validatingProcessor(ITI_8);
    }

    /**
     * Returns a validating processor for ITI-8 response messages
     * (Patient Identity Feed).
     */
    public static Processor iti8ResponseValidator() {
        return validatingProcessor(ITI_8);
    }

    /**
     * Returns a validating processor for ITI-9 request messages.
     * (Patient Identity Query).
     */
    public static Processor iti9RequestValidator() {
        return validatingProcessor(ITI_9);
    }

    /**
     * Returns a validating processor for ITI-9 response messages 
     * (Patient Identity Query).
     */
    public static Processor iti9ResponseValidator() {
        return validatingProcessor(ITI_9);
    }

    /**
     * Returns a validating processor for ITI-10 request messages 
     * (PIX Update Notification).
     */
    public static Processor iti10RequestValidator() {
        return validatingProcessor(ITI_10);
    }

    /**
     * Returns a validating processor for ITI-10 response messages 
     * (PIX Update Notification).
     */
    public static Processor iti10ResponseValidator() {
        return validatingProcessor(ITI_10);
    }

    /**
     * Returns a validating processor for ITI-21 request messages 
     * (Patient Demographics Query).
     */
    public static Processor iti21RequestValidator() {
        return validatingProcessor(ITI_21);
    }

    /**
     * Returns a validating processor for ITI-21 response messages 
     * (Patient Demographics Query).
     */
    public static Processor iti21ResponseValidator() {
        return validatingProcessor(ITI_21);
    }

    /**
     * Returns a validating processor for ITI-22 request messages 
     * (Patient Demographics and Visit Query).
     */
    public static Processor iti22RequestValidator() {
        return validatingProcessor(ITI_22);
    }

    /**
     * Returns a validating processor for ITI-22 response messages 
     * (Patient Demographics and Visit Query).
     */
    public static Processor iti22ResponseValidator() {
        return validatingProcessor(ITI_22);
    }    
    
    
    private static Processor validatingProcessor(IpfInteractionId interactionId) {
        final Parser parser = IheRegistry.get(interactionId, Hl7v2TransactionConfiguration.class).getParser();
        return new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                doValidate(exchange, parser);
            }
        };
    }
    
    private static void doValidate(Exchange exchange, Parser parser) throws Exception {
        MessageAdapter msg = Hl7v2MarshalUtils.extractMessageAdapter(
                exchange.getIn(),
                exchange.getProperty(Exchange.CHARSET_NAME, String.class),
                parser);  
        VALIDATOR.validate(msg, null);
    }
    
}
