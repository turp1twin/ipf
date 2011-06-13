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
package org.openehealth.ipf.commons.ihe.hl7v2;

import org.openehealth.ipf.commons.ihe.core.IheConfigurator;
import org.openehealth.ipf.commons.ihe.core.IheRegistry;
import org.openehealth.ipf.commons.ihe.hl7v2.definitions.CustomModelClassUtils;
import org.openehealth.ipf.modules.hl7.parser.PipeParser;

import static org.openehealth.ipf.commons.ihe.core.IpfInteractionId.*;

/**
 * @author Dmytro Rud
 */
public class Hl7v2TransactionConfigurations implements IheConfigurator {

    @Override
    public void configure(IheRegistry registry) {
        registerConfigurations(registry);
        registerNakFactories(registry);
    }


    private static void registerConfigurations(IheRegistry registry) {
        final Class<Hl7v2TransactionConfiguration> clazz = Hl7v2TransactionConfiguration.class;

        registry.register(ITI_8, clazz, new Hl7v2TransactionConfiguration(
                "2.3.1",
                "PIX adapter",
                "IPF",
                207,
                207,
                new String[] {"ADT"},
                new String[] {"A01 A04 A05 A08 A40"},
                new String[] {"ACK"},
                new String[] {"*"},
                new boolean[] {true},
                new boolean[] {false},
                new PipeParser()));

        registry.register(ITI_9, clazz, new Hl7v2TransactionConfiguration(
                "2.5",
                "PIX adapter",
                "IPF",
                207,
                207,
                new String[] {"QBP"},
                new String[] {"Q23"},
                new String[] {"RSP"},
                new String[] {"K23"},
                new boolean[] {true},
                new boolean[] {false},
                CustomModelClassUtils.createParser("pix", "2.5")));
    
        registry.register(ITI_10, clazz, new Hl7v2TransactionConfiguration(
                "2.5",
                "PIX adapter",
                "IPF",
                207,
                207,
                new String[] {"ADT"},
                new String[] {"A31"},
                new String[] {"ACK"},
                new String[] {"*"},
                new boolean[] {true},
                new boolean[] {false},
                new PipeParser()));
    
        registry.register(ITI_21, clazz, new PdqTransactionConfiguration(
                "2.5",
                "PDQ adapter",
                "IPF",
                207,
                207,
                new String[] {"QBP", "QCN"},
                new String[] {"Q22", "J01"},
                new String[] {"RSP", "ACK"},
                new String[] {"K22", "*"},
                new boolean[] {true, false},
                new boolean[] {true, false},
                CustomModelClassUtils.createParser("pdq", "2.5")));
    
        registry.register(ITI_22, clazz, new PdqTransactionConfiguration(
                "2.5",
                "PDQ adapter",
                "IPF",
                207,
                207,
                new String[] {"QBP", "QCN"},
                new String[] {"ZV1", "J01"},
                new String[] {"RSP", "ACK"},
                new String[] {"ZV2", "*"},
                new boolean[] {true, false},
                new boolean[] {true, false},
                CustomModelClassUtils.createParser("pdq", "2.5")));
    
        registry.register(PCD_01, clazz, new Hl7v2TransactionConfiguration(
                "2.6",
                "PCD Adapter",
                "IPF",
                207,
                207,
                new String[] {"ORU"},
                new String[] {"R01"},
                new String[] {"ACK"},
                new String[] {"*"},
                null,
                null,
                new PipeParser()));
    }


    private static void registerNakFactories(IheRegistry registry) {
        final Class<NakFactory> clazz = NakFactory.class;
    
        registry.register(ITI_8,  clazz, new NakFactory(ITI_8));
        registry.register(ITI_9,  clazz, new QpdAwareNakFactory(ITI_9, "RSP", "K23"));
        registry.register(ITI_10, clazz, new NakFactory(ITI_10));
        registry.register(ITI_21, clazz, new QpdAwareNakFactory(ITI_21, "RSP", "K22"));
        registry.register(ITI_22, clazz, new QpdAwareNakFactory(ITI_22, "RSP", "ZV2"));
        registry.register(PCD_01, clazz, new NakFactory(PCD_01, false, "ACK^R01^ACK"));
    }
}
