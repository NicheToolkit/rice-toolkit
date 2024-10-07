package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <code>RedisTemplateAutoConfigure</code>
 * <p>The type redis template auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @since Jdk1.8
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
     * <p>The template method.</p>
     * @param connectionFactory {@link org.springframework.data.redis.connection.RedisConnectionFactory} <p>The connection factory parameter is <code>RedisConnectionFactory</code> type.</p>
     * @return {@link org.springframework.data.redis.core.RedisTemplate} <p>The template return object is <code>RedisTemplate</code> type.</p>
     * @see org.springframework.data.redis.connection.RedisConnectionFactory
     * @see org.springframework.data.redis.core.RedisTemplate
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(false);
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
