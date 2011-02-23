package org.openehealth.ipf.platform.camel.flow.extend;

import org.apache.camel.impl.SerializationDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.openehealth.ipf.platform.camel.flow.builder.RouteHelper;

public class JavaFlowRouteBuilder extends SpringRouteBuilder {

	private SerializationDataFormat format = new SerializationDataFormat();

	private RouteHelper flow;

	public JavaFlowRouteBuilder() {
		flow = new RouteHelper(this);
	}

	@Override
	public void configure() throws Exception {

		onException(Exception.class).handled(true).process(flow.flowError())
				.to("mock:error");
		// --------------------------------------------------------------
		// Linear Flows
		// --------------------------------------------------------------

		from("direct:flow-test-1")
			.process(flow.flowBegin("test-1", "direct:flow-test-3").application("test").outType(String.class))
			.setHeader("foo", constant("test-1")).to("mock:mock")
			.process(flow.flowEnd());

		from("direct:flow-test-2")
			.process(flow.flowBegin("test-2", "direct:flow-test-3").application("test").inFormat(format).outFormat(format))
			.setHeader("foo", constant("test-2")).to("mock:mock")
			.process(flow.flowEnd());

		from("direct:flow-test-3")
	    	.process(flow.flowBegin("test-3", "direct:flow-test-3").application("test").inFormat(format).outConversion(false))
			.setHeader("foo", constant("test-3")).to("mock:mock")
			.process(flow.flowEnd());

		
		 from("direct:flow-test-4")
		 	.process(flow.flowBegin("test-4", "direct:flow-test-4").application("test").outType(String.class))
		 	.setHeader("foo", constant("test-4"))
		 	.filter(flow.dedupe())
		 	.to("mock:mock")
		 	.process(flow.flowEnd());
		//
		// from("direct:flow-test-5")
		// .initFlow("test-5")
		// .application("test")
		// .outType(String.class)
		// .throwException(new Exception('unhandled'))
		// .to("mock:mock")
		//
		// from("direct:flow-test-6")
		// .initFlow("test-6")
		// .replayErrorHandler("mock:error")
		// .application("test")
		// .outType(String.class)
		// .throwException(new Exception('handled'))
		// .to("mock:mock")
		//
		// from("direct:flow-test-7")
		// .initFlow("test-7")
		// .application("test")
		// .inFormat(serialization)
		// .outFormat(new StringDataFormat("UTF-8"))
		// .outConversion(false)
		// .setHeader("foo", constant("test-4"))
		// .to("mock:mock")
		// .ackFlow()
		//
		// from("direct:flow-test-8")
		// .initFlow("test-8")
		// .application("test")
		// .inFormat(serialization)
		// .outFormat(new StringDataFormat("UTF-8"))
		// .setHeader("foo", constant("test-8"))
		// .to("mock:mock")
		// .ackFlow()
		//
		// //
		// --------------------------------------------------------------
		// // Split Flows (original Camel splitter)
		// //
		// --------------------------------------------------------------
		//
		// from("direct:flow-test-split")
		// .initFlow("test-split")
		// .application("test")
		// .outType(String.class)
		// .multicast()
		// .to("direct:out-1")
		// .to("direct:out-2")
		//
		// from("direct:out-1")
		// .to("mock:mock-1")
		// .ackFlow()
		//
		// from("direct:out-2")
		// .to("mock:mock-2")
		// .ackFlow()
		//
		// //
		// --------------------------------------------------------------
		// // Pipe Flows
		// //
		// --------------------------------------------------------------
		//
		// from("direct:flow-test-pipe")
		// .initFlow("test-pipe")
		// .application("test")
		// .outType(String.class)
		// .to("direct:out-1")
		// .to("direct:out-2")
		//
	}

}
