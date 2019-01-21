package com.shiyouao.redis.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * 自定义序列化
 * @Author sya
 * @Date 2019/1/18
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastJson2JsonRedisSerializer(Class<T> clazz){
        super();
        this.clazz = clazz;
    }


    /**
     * 序列化
     * @param t
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }


    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String srt = new String(bytes, DEFAULT_CHARSET);
        //解决com.alibaba.fastjson.JSONException: autoType is not support,添加白名单
        ParserConfig.getGlobalInstance().addAccept("com.shiyouao.redis");
        return (T)JSON.parseObject(srt, clazz);
    }
}
