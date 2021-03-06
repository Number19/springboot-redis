package com.shiyouao.redis.base.controller;

import com.shiyouao.redis.base.utils.StateParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import java.util.UUID;

/**
 * @Author sya
 * @Date 2019/1/18
 */
public abstract class BaseController {

    protected final String success = StateParameter.SUCCESS;
    protected final String fail = StateParameter.FAULT;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ModelMap getModelMap(String stats, Object data, String msg) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("status", stats);
        modelMap.put("data", data);
        modelMap.put("msg", msg);
        return modelMap;
    }

    public String getUuid() {
        //获取UUID并转化成String对象
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-","");
        return uuid;
    }
}
