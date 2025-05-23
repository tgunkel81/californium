/*******************************************************************************
 * Copyright (c) 2015 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 ******************************************************************************/
package org.eclipse.californium.plugtests.tests;

import java.util.Arrays;
import java.util.List;

import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.OptionSet;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.plugtests.TestClientAbstract;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.CoAP.Type;

/**
 * TD_COAP_CORE_18: Perform POST transaction with responses containing
 * several Location-Query options (CON mode)
 */
public class CC19 extends TestClientAbstract {

	public static final String RESOURCE_URI = "/location-query";
	public final ResponseCode EXPECTED_RESPONSE_CODE = ResponseCode.CREATED;

	public CC19(String serverURI) {
		super(CC19.class.getSimpleName());

		// create the request
		Request request = new Request(Code.POST, Type.CON);
		// add payload
		request.setPayload("TD_COAP_CORE_19");
		request.getOptions().setContentFormat(MediaTypeRegistry.TEXT_PLAIN);
		// set the parameters and execute the request
		executeRequest(request, serverURI, RESOURCE_URI);
	}

	protected boolean checkResponse(Request request, Response response) {
		boolean success = true;

		success &= checkType(Type.ACK, response.getType());
		success &= checkCode(EXPECTED_RESPONSE_CODE, response.getCode());
		success &= hasLocationQuery(response);

		// List<Option> options =
		// response.getOptions(OptionNumberRegistry.LOCATION_QUERY);
		// success &= checkOption(new Option("first=1",
		// OptionNumberRegistry.LOCATION_QUERY), options.get(0));
		// success &= checkOption(new Option("second=2",
		// OptionNumberRegistry.LOCATION_QUERY), options.get(1));

		List<String> query = OptionSet.getValues(response.getOptions().getLocationQuery());
		List<String> expec = Arrays.asList("first=1", "second=2");
		success &= checkOption(expec, query, "Location Query");

		return success;
	}
}
