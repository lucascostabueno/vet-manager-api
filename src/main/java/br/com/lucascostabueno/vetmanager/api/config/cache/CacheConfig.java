package br.com.lucascostabueno.vetmanager.api.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(30)).disableCachingNullValues()
        .computePrefixWith(cacheName -> "vetmanager:" + cacheName + "::")
        .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
        .serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.json()));

    return RedisCacheManager.builder(connectionFactory).cacheDefaults(defaultConfig).build();
  }

  @Override
  public CacheErrorHandler errorHandler() {
    return new SimpleCacheErrorHandler() {
      @Override
      public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.error("Failed to retrieve key '{}' from cache '{}'. Falling back to database.", key,
            cache.getName(), exception);
      }

      @Override
      public void handleCachePutError(RuntimeException exception, Cache cache, Object key,
          Object value) {
        log.error("Failed to put key '{}' into cache '{}'.", key, cache.getName(), exception);
      }

      @Override
      public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.error("Failed to evict key '{}' from cache '{}'.", key, cache.getName(), exception);
      }

      @Override
      public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.error("Failed to clear cache '{}'.", cache.getName(), exception);
      }
    };
  }
}
