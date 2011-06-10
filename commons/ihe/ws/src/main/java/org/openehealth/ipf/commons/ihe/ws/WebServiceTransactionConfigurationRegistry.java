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
package org.openehealth.ipf.commons.ihe.ws;

import org.apache.commons.lang.Validate;
import org.openehealth.ipf.commons.ihe.core.IheRegistry;
import org.openehealth.ipf.commons.ihe.core.InteractionId;

import java.util.HashMap;
import java.util.Map;

/**
 * Configurations registry for Web Service-based IPF eHealth transactions.
 * @author Dmytro Rud
 */
public class WebServiceTransactionConfigurationRegistry extends IheRegistry {
    private static final Map<InteractionId, ItiServiceInfo> MAP =
            new HashMap<InteractionId, ItiServiceInfo>();

    private static final WebServiceTransactionConfigurationRegistry INSTANCE =
            new WebServiceTransactionConfigurationRegistry();


    private WebServiceTransactionConfigurationRegistry() {
        super();
    }


    public static WebServiceTransactionConfigurationRegistry instance() {
        return INSTANCE;
    }


    /**
     * Registers configuration of a Web Service-based component which
     * implements the given interaction.
     * @param interactionId
     *      interaction ID.
     * @param configuration
     *      component configuration.
     */
    public synchronized void registerConfiguration(
            InteractionId interactionId,
            ItiServiceInfo configuration)
    {
        Validate.notNull(interactionId);
        Validate.notNull(configuration);
        MAP.put(interactionId, configuration);
    }


    /**
     * Returns configuration of a Web Service-based component which
     * implements the given interaction.
     * @param interactionId
     *      interaction ID.
     * @param <T>
     *      type of the component's configuration.
     * @return
     *      component's configuration.
     */
    public <T extends ItiServiceInfo> T get(InteractionId interactionId) {
        return (T) MAP.get(interactionId);
    }
}
