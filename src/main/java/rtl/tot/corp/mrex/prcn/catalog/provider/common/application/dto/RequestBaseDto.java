package rtl.tot.corp.mrex.prcn.catalog.provider.common.application.dto;

import rtl.tot.corp.mrex.prcn.catalog.provider.common.application.enumeration.RequestBodyType;

public class RequestBaseDto {
	protected RequestBodyType requestBodyType;

	public RequestBodyType getRequestBodyType() {
		return requestBodyType;
	}

	public void setRequestBodyType(RequestBodyType requestBodyType) {
		this.requestBodyType = requestBodyType;
	}
}
