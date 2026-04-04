package com.weng.system.common.httpexecutor;

import org.springframework.web.client.RestTemplate;

public interface Task<T> {

     T excutor(RestTemplate restTemplate);
}
