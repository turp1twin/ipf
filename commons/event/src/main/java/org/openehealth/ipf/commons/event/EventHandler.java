/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.commons.event;

/**
 * Standard interface for event handlers
 * @author Jens Riemschneider
 */
public interface EventHandler {
    /**
     * Called by the {@link EventEngine} to handle an event
     * <p>
     * This method is called if an event occurred that this handler is subscribed
     * to via its {@link Subscription}.
     * @param eventObject
     *          the event object representing the event that occurred
     */
    void handle(EventObject eventObject);
}