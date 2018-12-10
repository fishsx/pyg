package com.songxin.pyg.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录的控制器
 * @author fishsx
 * @date 2018/12/9 下午8:00
 */
@RestController
@RequestMapping("login")
public class LoginController {

    /**
     * 获取登录名
     * @param
     * @return java.util.Map
     * @author fishsx
     * @date 2018/12/9 下午8:02
     */
    @RequestMapping("getLoginName")
    public Map getLoginName() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,Object> map = new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}
