package com.shiyouao.redis.base.config;


import com.shiyouao.redis.base.utils.FastJson2JsonRedisSerializer;
import com.shiyouao.redis.base.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import com.shiyouao.redis.base.utils.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Author sya
 * @Date 2019/1/18
 */
@Configuration
@PropertySource("classpath:redis.properties")
@Slf4j
public class RedisConfig {

    @Value("${redis.hostName}")
    private String hostName;

   /* @Value("${redis.password}")
    private String password;*/

   @Value("${redis.port}")
   private Integer port;

   @Value("${redis.maxIdle}")
   private  Integer maxIdle;

   @Value("${redis.timeout}")
   private Integer timeout;

   @Value("${redis.maxTotal}")
   private Integer maxTotal;

   @Value("${redis.maxWaitMillis}")
   private Integer maxWaitMillis;

   @Value("${redis.minEvictableIdleTimeMillis}")
   private Integer  minEvictableIdleTimeMillis;

   @Value("${redis.numTestsPerEvictionRun}")
   private  Integer numTestsPerEvictionRun;

   @Value("${redis.timeBetweenEvictionRunsMillis}")
   private Long timeBetweenEvictionRunsMillis;

   @Value("${redis.testOnBorrow}")
   private boolean testOnBorrow;

   @Value("${redis.testWhileIdle}")
   private boolean testWhileIdle;

    /**
     * Jedis配置
     * @return org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     */
   @Bean
   public JedisConnectionFactory JedisConnectionFactory() {
       RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
       redisStandaloneConfiguration.setHostName(hostName);
       redisStandaloneConfiguration.setPort(port);
       //由于我们使用了动态配置库，所以此处省略
       //redisStandaloneConfiguration.setDatabase(database);
       //我的redis没有设置密码，此处注释掉
       //redisStandaloneConfiguration.getPassword(RedisPassword.of(password));
       JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder = JedisClientConfiguration.builder();
       jedisClientConfigurationBuilder.connectTimeout(Duration.ofMillis(timeout));
       JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfigurationBuilder.build());
       return factory;
   }

    /**
     * 实例化 RedisTemplate 对象
     * @param redisConnectionFactory
     * @return
     */
   @Bean
   public RedisTemplate functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
       log.info("RedisTemplate 实例化成功！");
       RedisTemplate redisTemplate = new RedisTemplate();
       initDomainRedisTemplate(redisTemplate,redisConnectionFactory);
       return redisTemplate;
   }

    /**
     * 引入自定义序列化
     * @return
     */
   @Bean
   public RedisSerializer fastJson2JsonRedisSerializer() {
       return new FastJson2JsonRedisSerializer<>(Object.class);
   }

    /**
     * 设置数据存入 redis的序列化方式，并开始事务
     * @param redisTemplate
     * @param factory
     */
   private void initDomainRedisTemplate(RedisTemplate redisTemplate, RedisConnectionFactory factory) {
       //如果不配置Serializer,那么存储的时候缺省使用string，如果用User类型存储，那么会提示错误 User can't cast to string!
       redisTemplate.setKeySerializer(new StringRedisSerializer());
       redisTemplate.setHashKeySerializer(new StringRedisSerializer());
       redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
       redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer());
       //开始事务
       redisTemplate.setEnableTransactionSupport(true);
       redisTemplate.setConnectionFactory(factory);
   }

    /**
     * 注入封装 RedisTemplate
     * @param redisTemplate
     * @return
     */
   public RedisUtil redisUtil(RedisTemplate redisTemplate) {
       log.info("RedisUtil注入成功!");
       RedisUtil redisUtil = new RedisUtil();
       redisUtil.setRedisTemplate(redisTemplate);
       return redisUtil;
   }

}
