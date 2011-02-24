/*
 * Copyright 20011 the original author or authors.
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

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.After;
import org.junit.Test;
import org.openehealth.ipf.commons.flow.FlowManager;
import org.openehealth.ipf.platform.camel.flow.process.AbstractFlowTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/**
 * @author Mitko Kolev
 *
 */
@ContextConfiguration(locations = { "/context-flow-route-java.xml" })
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class JavaFlowTest extends AbstractFlowTest {

	@Autowired
	private FlowManager flowManager;


	@EndpointInject(uri = "mock:mock")
	private MockEndpoint mock;

	@After
	public void tearDown() throws Exception {
		mock.reset();
		super.tearDown();
	}

	@Test
	public void testDedupe() throws Exception {
		mock.expectedMessageCount(1);
		producerTemplate.sendBody("direct:flow-test-4", "1");
		mock.assertIsSatisfied();
		
		long flowId = (Long)mock.getExchanges().get(0).getIn().getHeader(FLOW_ID_KEY);
		// the replay should be filtered
		flowManager.replayFlow(flowId);
		mock.assertIsSatisfied();
	}

}
