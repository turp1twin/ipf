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

import javax.naming.OperationNotSupportedException;

import org.apache.camel.Expression;
import org.apache.camel.model.ProcessorDefinition;
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
 * @author Mitko Kolev
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
     * Method to initialize flows from the Java DSL.</br> Returns a new
     * {@link FlowBeginProcessor} after assigning the <code>identifier</code>.
     * The returned processor is registered at the
     * {@link org.openehealth.ipf.platform.camel.flow.ReplayStrategyRegistry}.
     * 
     * @param identifier
     *            the identifier to set on the {@link FlowBeginProcessor}.
     * @param replayUri
     *            a direct endpoint URI (starting with <code>direct:</code>)
     *            from which to replay the flows. This is required only for the
     *            Java DSL.
     * @return a new {@link FlowBeginProcessor}.
     */
    public FlowBeginProcessor initFlow(String identifier, String replayUri) {
        if (replayUri == null) {
            throw new IllegalArgumentException(
                    "If you use the Java DSL, you must specify a direct endpoint to be used"
                            + " for replaying flows with id " + identifier);
        }
        if (!replayUri.startsWith("direct:")) {
            throw new IllegalArgumentException(
                    "Only direct endpoints can be used for replaying flows!");
        }
        FlowBeginProcessor result = routeBuilder
                .lookup(FlowBeginProcessor.class);
        result.identifier(identifier).replayEndpoint(replayUri);
        result.register();
        return result;
    }

    /**
     * Use this processor in a route to acknowledge a flow, started with
     * {@link #initFlow(String, String)}
     * 
     * @return a new {@link FlowEndProcessor} instance
     */
    public FlowEndProcessor ackFlow() {
        return routeBuilder.lookup(FlowEndProcessor.class);
    }

    /**
     * Use this processor in a route to denote a flow, started with
     * {@link #initFlow(String, String)} as error flow.
     * 
     * @see #ackFlow()
     * @return a new {@link FlowErrorProcessor} instance
     */
    public FlowErrorProcessor nakFlow() {
        return routeBuilder.lookup(FlowErrorProcessor.class);
    }

    /**
     * Deprecated, use {@link #initFlow(String, String)}</br>
     * 
     * Returns a new {@link FlowBeginProcessor} after assigning the
     * <code>identifier</code>. The returned processor is registered at the
     * {@link org.openehealth.ipf.platform.camel.flow.ReplayStrategyRegistry}.
     * 
     * @param identifier
     *            the identifier to set on the {@link FlowBeginProcessor}.
     * @return a new {@link FlowBeginProcessor}.
     */
    @Deprecated
    public FlowBeginProcessor flowBegin(String identifier, String replayUri) {
        FlowBeginProcessor result = routeBuilder
                .lookup(FlowBeginProcessor.class);
        result.identifier(identifier).replayEndpoint(replayUri);
        result.register();
        return result;
    }

    /**
     * Deprecated, use {@link #ackFlow()} instead.</br>
     * 
     * Returns a new {@link FlowEndProcessor}.
     * 
     * @return a new {@link FlowEndProcessor}.
     */
    @Deprecated
    public FlowEndProcessor flowEnd() {
        return routeBuilder.lookup(FlowEndProcessor.class);
    }

    /**
     * Deprecated, use {@link #nakFlow()} instead.</br>
     * 
     * Returns a new {@link FlowErrorProcessor}.
     * 
     * @return a new {@link FlowErrorProcessor}.
     */
    @Deprecated
    public FlowErrorProcessor flowError() {
        return routeBuilder.lookup(FlowErrorProcessor.class);
    }

    /**
     * Returns a {@link Dedupe} predicate. You can use the predicate in
     * {@link ProcessorDefinition#filter(org.apache.camel.Predicate)} to filter
     * flows.
     * 
     * @return a {@link Dedupe} instance.
     */
    public Dedupe dedupe() {
        return routeBuilder.lookup(Dedupe.class);
    }

    // ----------------------------------------------------------------
    // Flow management-specific message processors
    // ----------------------------------------------------------------

    /**
     * Deprecated. The Splitter is not supported in the Java DSL.
     * 
     * Returns a new {@link Splitter}
     * 
     * @param splitRule
     *            expression that performs the splitting of the original
     *            exchange
     * @return the new {@link Splitter}
     */
    @Deprecated
    public Splitter split(Expression splitRule) {
        throw new RuntimeException(new OperationNotSupportedException(
                "The IPF Splitter is not supported in Java!"));
        // return new Splitter(splitRule, null);
    }

}
