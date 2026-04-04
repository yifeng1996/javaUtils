package com.weng.system.common.httpexecutor;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

@Component
public class CommonRetryer implements Retryer {

	@Override
	public boolean retry(RetryException e) {
		if (e.getException() instanceof ResourceAccessException) {
			return true;
		}
		return false;
	}

}
