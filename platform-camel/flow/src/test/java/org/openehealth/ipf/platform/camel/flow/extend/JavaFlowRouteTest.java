/*
 * Copyright 2008 the original author or authors.
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
package org.openehealth.ipf.platform.camel.flow.extend;

import static org.openehealth.ipf.platform.camel.flow.PlatformMessage.FLOW_ID_KEY;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openehealth.ipf.commons.flow.FlowManager;
import org.openehealth.ipf.platform.camel.flow.process.AbstractFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "/context-flow-route-java.xml" })
public class JavaFlowRouteTest extends AbstractFlowTest {

	@Autowired
	private FlowManager flowManager;

	@Autowired
	private CamelContext context;

	@EndpointInject(uri = "mock:mock")
	private MockEndpoint mock;

	ProducerTemplate producer;

	@Before
	public void setUp() throws Exception {
		producer = context.createProducerTemplate();
	}

	@After
	public void tearDown() throws Exception {
		mock.reset();
		super.tearDown();
	}

	@Test
	public void testDedupe() throws Exception {
		mock.expectedMessageCount(1);
		producer.sendBody("direct:flow-test-4", "1");
		mock.assertIsSatisfied();
		
		long flowId = (Long)mock.getExchanges().get(0).getIn().getHeader(FLOW_ID_KEY);
		// the replay should be filtered
		flowManager.replayFlow(flowId);
		mock.assertIsSatisfied();
	}
	
	
}
