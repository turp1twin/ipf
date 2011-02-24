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

import org.apache.camel.spring.SpringRouteBuilder;
import org.openehealth.ipf.platform.camel.flow.builder.RouteHelper;

/**
 * Use flow management with Java
 * 
 * @author Mitko Kolev
 *
 */
public class JavaSplitRouteBuilder extends SpringRouteBuilder {

    private RouteHelper flows;

    public JavaSplitRouteBuilder() {
        flows = new RouteHelper(this);
    }

    @Override
    public void configure() throws Exception {
        
        errorHandler(noErrorHandler());
        
        // --------------------------------------------------------------
        //  Split Flows (IPF splitter)
        // --------------------------------------------------------------
        
        from("direct:split-test-ipfsplit")
            .process(flows.initFlow("test-ipfsplit", "direct:split-test-ipfsplit")
                            .application("test").outType(String.class))
            .process(flows.split(body(String.class).tokenize(",")))    
            .to("mock:mock-1")
            .process(flows.ackFlow());

            
        from("direct:split-test-ipfsplit-agg")
            .to("direct:out-3")
            .to("direct:out-4");
        
        from("direct:out-3")
            .process(flows.split(body(String.class).tokenize(",")))   
            .to("mock:mock-1");
            
        from("direct:out-4")
            .to("mock:mock-2");
    }

}
