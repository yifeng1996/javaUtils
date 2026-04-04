package com.weng.system.common.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 */
@Configuration
public class RedisConfigure {


	@Bean
	@ConditionalOnClass(RedisOperations.class)
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
		// 遇见未知属性是否抛出异常
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jackson2JsonRedisSerializer.setObjectMapper(mapper);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用 String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的 key也采用 String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用 jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的 value序列化方式采用 jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();

		return template;
	}

	//
	// @Bean(name = "redisTemplate")
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// @ConditionalOnMissingBean(name = "redisTemplate")
	// public RedisTemplate<Object, Object>
	// redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
	// RedisTemplate<Object, Object> template = new RedisTemplate<>();
	//
	// // 使用 fastjson 序列化
	// FastJsonRedisSerializer fastJsonRedisSerializer = new
	// FastJsonRedisSerializer(Object.class);
	// // value 值的序列化采用 fastJsonRedisSerializer
	// template.setValueSerializer(fastJsonRedisSerializer);
	// template.setHashValueSerializer(fastJsonRedisSerializer);
	// // key 的序列化采用 StringRedisSerializer
	// template.setKeySerializer(new StringRedisSerializer());
	// template.setHashKeySerializer(new StringRedisSerializer());
	//
	// template.setConnectionFactory(redisConnectionFactory);
	// return template;
	// }

	// 缓存管理器
	@Bean
	public CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
		return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
				this.getRedisCacheConfigurationWithTtl(-1) // 默认策略，不超时
				// 可以配置默认超时时间，但是使用不会增加到相对应的时间
				// this.getRedisCacheConfigurationWithTtl(60*60)

				, this.getRedisCacheConfigurationMap() // 指定 key 策略
		);
		// RedisCacheManager.RedisCacheManagerBuilder builder =
		// RedisCacheManager.RedisCacheManagerBuilder
		// .fromConnectionFactory(redisConnectionFactory);
		// builder.withInitialCacheConfigurations(getRedisCacheConfigurationMap());
		// return builder.build();
	}

	private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
		// this.getRedisCacheConfigurationWithTtl(18000));
//		Map<String, Long> tmp = redisExpiresProperties.getExpires();
//		Iterator<Entry<String, Long>> it = tmp.entrySet().iterator();
//		while (it.hasNext()) {
//			Entry<String, Long> entry = it.next();
//			redisCacheConfigurationMap.put(entry.getKey(),
//					this.getRedisCacheConfigurationWithTtl(entry.getValue().intValue()));
//		}
//		log.info("初始化指定redis的key失效时间:" + redisExpiresProperties.getExpires());
		return redisCacheConfigurationMap;
	}

	private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		redisCacheConfiguration = redisCacheConfiguration
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				.entryTtl(Duration.ofSeconds(seconds));

		return redisCacheConfiguration;
	}

	@Bean
	@ConditionalOnMissingBean(StringRedisTemplate.class)
	public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(method.getName());
			for (Object obj : params) {
				sb.append(obj.toString());
			}
			return sb.toString();
		};
	}

	@Bean
	public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Serializable> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
