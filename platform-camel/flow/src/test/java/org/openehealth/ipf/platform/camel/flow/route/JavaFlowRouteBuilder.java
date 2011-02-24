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
package org.openehealth.ipf.platform.camel.flow.route;

import org.apache.camel.component.http.HttpOperationFailedException;
import org.apache.camel.impl.SerializationDataFormat;
import org.apache.camel.impl.StringDataFormat;
import org.apache.camel.processor.aggregate.UseLatestAggregationStrategy;
import org.apache.camel.spring.SpringRouteBuilder;
import org.openehealth.ipf.platform.camel.flow.builder.RouteHelper;

/**
 * Use flow management with Java
 * 
 * @author Mitko Kolev
 *
 */
public class JavaFlowRouteBuilder extends SpringRouteBuilder {

    private SerializationDataFormat format = new SerializationDataFormat();

    private RouteHelper flows;

    public JavaFlowRouteBuilder() {
        flows = new RouteHelper(this);
    }

    @Override
    public void configure() throws Exception {

        onException(HttpOperationFailedException.class)
        .handled(true)
        .process(flows.nakFlow())
        .to("mock:mock");
        
        // --------------------------------------------------------------
        // Linear Flows
        // --------------------------------------------------------------

        from("direct:flow-test-1")
                .process(flows.initFlow("test-1", "direct:flow-test-1")
                         .application("test").outType(String.class))
                .setHeader("foo", constant("test-1"))
                .to("mock:mock")
                .process(flows.ackFlow());

        from("direct:flow-test-2")
                .process(flows.initFlow("test-2", "direct:flow-test-2")
                         .application("test").inFormat(format).outFormat(format))
                .setHeader("foo", constant("test-2")).to("mock:mock")
                .process(flows.ackFlow());

        from("direct:flow-test-3")
                .process(flows.initFlow("test-3", "direct:flow-test-3")
                               .application("test").inFormat(format).outConversion(false))
                .setHeader("foo", constant("test-3"))
                .to("mock:mock")
                .process(flows.ackFlow());

        from("direct:flow-test-4")
                .process(flows.initFlow("test-4", "direct:flow-test-4")
                                .application("test").outType(String.class))
                .setHeader("foo", constant("test-4"))
                .filter(flows.dedupe())
                .to("mock:mock")
                .process(flows.ackFlow());
        
         from("direct:flow-test-5")
             .process(flows.initFlow("test-5", "direct:flow-test-5")
                 .application("test").outType(String.class))
             .throwException(new Exception("unhandled"))
             .to("mock:mock");
        
         from("direct:flow-test-6")
         .process(flows.initFlow("test-6", "direct:flow-test-6")
                 .replayErrorHandler("mock:error").application("test").outType(String.class))
         .throwException(new Exception("handled"))
         .to("mock:mock");
        
         from("direct:flow-test-7")
         .process(flows.initFlow("test-7", "direct:flow-test-7")
                         .application("test").inFormat(format).outFormat(new StringDataFormat("UTF-8")).outConversion(false))
         .setHeader("foo", constant("test-4"))
         .to("mock:mock")
         .process(flows.ackFlow());
         
        
         from("direct:flow-test-8")
         .process(flows.initFlow("test-8", "direct:flow-test-8")
                         .application("test").inFormat(format).outFormat(new StringDataFormat("UTF-8")))
         .setHeader("foo", constant("test-8"))
         .to("mock:mock")
         .process(flows.ackFlow());
         
        
         //
         // --------------------------------------------------------------
         // Split Flows (original Camel splitter)
         // --------------------------------------------------------------
        
         from("direct:flow-test-split")
             .process(flows.initFlow("test-split", "direct:flow-test-split")
                         .application("test").outType(String.class))
             .multicast()
             .to("direct:out-1")
             .to("direct:out-2");
        
         from("direct:out-1")
             .to("mock:mock-1")
             .process(flows.ackFlow());
        
         from("direct:out-2")
             .to("mock:mock-2")
             .process(flows.ackFlow());
        
        //
        // --------------------------------------------------------------
        //  Pipe Flows
        // --------------------------------------------------------------
        
         from("direct:flow-test-pipe")
             .process(flows.initFlow("test-pipe", "direct:flow-test-pipe").application("test").outType(String.class))
             .to("direct:out-1")
             .to("direct:out-2");

   
     //
     // --------------------------------------------------------------
     //  Recepient List
     // --------------------------------------------------------------
     onException(HttpOperationFailedException.class)
         .handled(true)
         .process(flows.nakFlow())
         .to("mock:mock");
             
     from("direct:flow-test-recipient-list")
         .split().body().aggregationStrategy(new UseLatestAggregationStrategy())
         .to("direct:handleFlows");
         
         from("direct:handleFlows")
         .process(flows.initFlow("test-recipient-list", "direct:handleFlows")
                 .application("test"))
         .inOnly().to("seda:recipient");
         
     from("seda:recipient")
         .recipientList().header("recipient")
         .process(flows.ackFlow())
         .to("mock:wait"); // avoid race conditions
         
     from("jetty:http://localhost:7799/recipient")
         .to("mock:mock");
         
	}

}
