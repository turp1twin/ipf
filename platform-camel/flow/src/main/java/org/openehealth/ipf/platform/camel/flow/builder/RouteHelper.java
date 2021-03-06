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
package org.openehealth.ipf.platform.camel.flow.builder;

import org.apache.camel.Expression;
import org.apache.camel.spring.SpringRouteBuilder;
import org.openehealth.ipf.platform.camel.flow.dedupe.Dedupe;
import org.openehealth.ipf.platform.camel.flow.process.FlowBeginProcessor;
import org.openehealth.ipf.platform.camel.flow.process.FlowEndProcessor;
import org.openehealth.ipf.platform.camel.flow.process.FlowErrorProcessor;
import org.openehealth.ipf.platform.camel.flow.process.Splitter;

/**
 * Helper class for creating IPF extensions in Java-based route definitions.
 * 
 * @author Martin Krasser
 */
public class RouteHelper {

    private SpringRouteBuilder routeBuilder;
    
    public RouteHelper(SpringRouteBuilder routeBuilder) {
        this.routeBuilder = routeBuilder;
    }
    
    public void setRouteBuilder(SpringRouteBuilder routeBuilder) {
        this.routeBuilder = routeBuilder;
    }
    
    /**
     * Returns a new {@link FlowBeginProcessor} after assigning the
     * <code>identifier</code>. The returned processor is registered at the
     * {@link org.openehealth.ipf.platform.camel.flow.ReplayStrategyRegistry}.
     * 
     * @param identifier
     *            the identifier to set on the {@link FlowBeginProcessor}.
     * @return a new {@link FlowBeginProcessor}.
     */
    public FlowBeginProcessor flowBegin(String identifier) {
        FlowBeginProcessor processor = routeBuilder.lookup(FlowBeginProcessor.class);
        processor.identifier(identifier).register();
        return processor;
    }
    
    /**
     * Returns a new {@link FlowEndProcessor}.
     * 
     * @return a new {@link FlowEndProcessor}.
     */
    public FlowEndProcessor flowEnd() {
        return routeBuilder.lookup(FlowEndProcessor.class);
    }

    /**
     * Returns a new {@link FlowErrorProcessor}.
     * 
     * @return a new {@link FlowErrorProcessor}.
     */
    public FlowErrorProcessor flowError() {
        return routeBuilder.lookup(FlowErrorProcessor.class);
    }

    /**
     * Returns a new {@link Dedupe}.
     * 
     * @return a new {@link Dedupe}.
     */
    public Dedupe dedupe() {
        return routeBuilder.lookup(Dedupe.class);
    }
    
    // ----------------------------------------------------------------
    //  Flow management-specific message processors
    // ----------------------------------------------------------------
    
    /**
     * Returns a new {@link Splitter}
     * 
     * @param splitRule
     *          expression that performs the splitting of the original exchange
     * @return the new {@link Splitter}
     */
    public Splitter split(Expression splitRule) {
        return new Splitter(splitRule, null);
    }

}
