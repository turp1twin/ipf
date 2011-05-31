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

import org.openehealth.ipf.commons.ihe.hl7v2.definitions.CustomModelClassUtils;
import org.openehealth.ipf.modules.hl7.parser.PipeParser;

/**
 * @author Dmytro Rud
 */
public class Hl7v2TransactionConfigurations {

    public static final Hl7v2TransactionConfiguration ITI_8_CONFIG =
        new Hl7v2TransactionConfiguration(
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
                new PipeParser());


    public static final Hl7v2TransactionConfiguration ITI_9_CONFIG =
        new Hl7v2TransactionConfiguration(
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
                CustomModelClassUtils.createParser("pix", "2.5"));


    public static final Hl7v2TransactionConfiguration ITI_10_CONFIG =
        new Hl7v2TransactionConfiguration(
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
                new PipeParser());


    public static final Hl7v2TransactionConfiguration ITI_21_CONFIG =
        new PdqTransactionConfiguration(
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
                CustomModelClassUtils.createParser("pdq", "2.5"));


    public static final Hl7v2TransactionConfiguration ITI_22_CONFIG =
        new PdqTransactionConfiguration(
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
                CustomModelClassUtils.createParser("pdq", "2.5"));


    public static final Hl7v2TransactionConfiguration PCD_01_CONFIG =
        new Hl7v2TransactionConfiguration(
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
            new PipeParser());


    public static final NakFactory ITI_8_NAK_FACTORY  = new NakFactory(ITI_8_CONFIG);
    public static final NakFactory ITI_9_NAK_FACTORY  = new QpdAwareNakFactory(ITI_9_CONFIG, "RSP", "K23");
    public static final NakFactory ITI_10_NAK_FACTORY = new NakFactory(ITI_10_CONFIG);
    public static final NakFactory ITI_21_NAK_FACTORY = new QpdAwareNakFactory(ITI_21_CONFIG, "RSP", "K22");
    public static final NakFactory ITI_22_NAK_FACTORY = new QpdAwareNakFactory(ITI_22_CONFIG, "RSP", "ZV2");
    public static final NakFactory PCD_01_NAK_FACTORY = new NakFactory(PCD_01_CONFIG, false, "ACK^R01^ACK");

}
