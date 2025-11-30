package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;

/**
 * <code>RedisTemplateAutoConfigure</code>
 * <p>The redis template auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @since Jdk17
 */
@Slf4j
@Configuration
public class RedisTemplateAutoConfigure {
    /**
     * <code>RedisTemplateAutoConfigure</code>
     * <p>Instantiates a new redis template auto configure.</p>
     */
    public RedisTemplateAutoConfigure() {
        log.debug("The auto configuration for [redis-template] initiated");
    }

    /**
     * <code>redisTemplate</code>
     * <p>The redis template method.</p>
     * @param connectionFactory {@link org.springframework.data.redis.connection.RedisConnectionFactory} <p>The connection factory parameter is <code>RedisConnectionFactory</code> type.</p>
     * @param objectMapper      {@link tools.jackson.databind.ObjectMapper} <p>The object mapper parameter is <code>ObjectMapper</code> type.</p>
     * @return {@link org.springframework.data.redis.core.RedisTemplate} <p>The redis template return object is <code>RedisTemplate</code> type.</p>
     * @see org.springframework.data.redis.connection.RedisConnectionFactory
     * @see tools.jackson.databind.ObjectMapper
     * @see org.springframework.data.redis.core.RedisTemplate
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJacksonJsonRedisSerializer(objectMapper));
        redisTemplate.setValueSerializer(new GenericJacksonJsonRedisSerializer(objectMapper));
        redisTemplate.setEnableTransactionSupport(false);
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
