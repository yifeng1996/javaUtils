package com.weng.system.common.configure;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * 
 */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		// 默认为standard模式，设置为strict模式
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 //验证映射
        modelMapper.validate();
		return modelMapper;
	}
}
