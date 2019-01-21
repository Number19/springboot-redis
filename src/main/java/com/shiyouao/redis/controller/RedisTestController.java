package com.shiyouao.redis.controller;

import com.shiyouao.redis.base.controller.BaseController;
import com.shiyouao.redis.base.utils.RedisConstants;
import com.shiyouao.redis.base.utils.RedisUtil;
import com.shiyouao.redis.base.utils.StateParameter;
import com.shiyouao.redis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author sya
 * @Date 2019/1/18
 */
@Controller
@RequestMapping("/redis")
public class RedisTestController extends BaseController {
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/list")
    public String view(HttpServletRequest request, String name) {
        logger.info("返回列表页面");
        return "/demoPage/listPage"+name;
    }

    /**
     * 测试redis存储和读取
     * @return
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public ModelMap test(){
        try {
            redisUtil.set("redisTemplate","这是一条测试数据", RedisConstants.datebase2);
            String value = redisUtil.get("redisTemplate", RedisConstants.datebase2).toString();
            logger.info("redisValue = " + value);
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS, value, "操作成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败!");
        }
    }

    @RequestMapping(value = "/setUser")
    @ResponseBody
    public ModelMap setUser(){
        try {
            User user = new User();
            user.setName("隔壁老shi");
            user.setAge(25);
            user.setId(getUuid());
            redisUtil.set("user", user, RedisConstants.datebase3);
            User res = (User)redisUtil.get("user",RedisConstants.datebase3);
            logger.info("res = " + res);
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS, res, "操作成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败!");
        }
    }

}
