/*
 * Copyright 2012 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.xds.iti63;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.openehealth.ipf.commons.ihe.xds.core.responses.ErrorCode;
import org.openehealth.ipf.commons.ihe.xds.core.responses.QueryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.AdhocQueryRequest;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.AdhocQueryResponse;
import org.openehealth.ipf.commons.ihe.xds.iti63.Iti63PortType;
import org.openehealth.ipf.platform.camel.core.util.Exchanges;
import org.openehealth.ipf.platform.camel.ihe.ws.AbstractWebService;
import org.openehealth.ipf.platform.camel.ihe.ws.AbstractWsEndpoint;
import org.openehealth.ipf.platform.camel.ihe.xds.core.converters.EbXML30Converters;

/**
 * Service implementation for the IHE ITI-63 transaction.
 */
@Slf4j
public class Iti63Service extends AbstractWebService implements Iti63PortType {
    private final AbstractWsEndpoint endpoint;

    public Iti63Service(AbstractWsEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public AdhocQueryResponse crossGatewayFetch(AdhocQueryRequest body) {
        Exchange result = process(body);
        Exception exception = Exchanges.extractException(result);
        if (exception != null) {
            log.debug("ITI-63 service failed", exception);
            QueryResponse errorResponse = new QueryResponse(
                    exception,
                    ErrorCode.REGISTRY_METADATA_ERROR,
                    ErrorCode.REGISTRY_ERROR,
                    endpoint.getHomeCommunityId());
            errorResponse.getErrors().get(0).setLocation(endpoint.getHomeCommunityId());
            return EbXML30Converters.convert(errorResponse);
        }

        return Exchanges.resultMessage(result).getBody(AdhocQueryResponse.class);            
    }
}
