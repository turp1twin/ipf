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
package org.openehealth.ipf.commons.ihe.core;

import org.apache.commons.lang.Validate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Configuration registry for eHealth interactions.
 * @author Dmytro Rud
 */
public class IheRegistry {
    private static Map<Class, Map<InteractionId, Object>> MAP;


    /**
     * Constructor.
     * <p>
     * Supposed to be called only once on application start.
     * Automatically searches for available registry configurators
     * and gathers their configuration items.
     * To retrieve the collected configuration items, the static
     * {@link #get(InteractionId, Class) getter} should be used.
     */
    public IheRegistry() {
        synchronized (IheRegistry.class) {
            if (MAP == null) {
                MAP = Collections.synchronizedMap(new HashMap<Class, Map<InteractionId, Object>>());
                ServiceLoader<IheConfigurator> loader = ServiceLoader.load(IheConfigurator.class);
                for (IheConfigurator configurator : loader) {
                    configurator.configure(this);
                }
            }
            else {
               //  throw new IllegalStateException("Registry already configured, use static getter");
            }
        }
    }


    /**
     * Registers a configuration object.
     * @param interactionId
     *      ID of the interaction described by the given configuration object.
     * @param clazz
     *      formal type of the configuration object.
     * @param object
     *      configuration object.
     * @param <T>
     *      formal type of the configuration object.
     * @param <R>
     *      real type of the configuration object.
     */
    public synchronized <T, R extends T> void register(
            InteractionId interactionId,
            Class<T> clazz,
            R object)
    {
        Validate.notNull(interactionId);
        Validate.notNull(clazz);
        Validate.notNull(object);

        if (! MAP.containsKey(clazz)) {
            MAP.put(clazz, new HashMap<InteractionId, Object>());
        }
        MAP.get(clazz).put(interactionId, object);
    }


    /**
     * Retrieves a configuration object.
     * @param interactionId
     *      ID of the interaction described by the given configuration object.
     * @param clazz
     *      formal type of the configuration object.
     * @param <T>
     *      formal type of the configuration object.
     * @param <R>
     *      real type of the configuration object.
     * @return
     *      configuration object or <code>null</code> when no object of the
     *      given formal type has been registered for the given interaction ID.
     */
    public static <T, R extends T> R get(InteractionId interactionId, Class<T> clazz) {
        return MAP.containsKey(clazz)
                ? (R) MAP.get(clazz).get(interactionId)
                : null;
    }

}
