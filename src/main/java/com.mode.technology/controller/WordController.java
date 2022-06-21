package com.mode.technology.controller;


import com.alibaba.fastjson.JSON;
import com.mode.technology.constants.RedisConstant;
import com.mode.technology.service.WordService;
import com.mode.technology.util.RedisUtil;
import com.mode.technology.vo.beans.UserBean;
import com.mode.technology.vo.req.WordQueryReq;
import com.mode.technology.vo.tip.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/word")
@Slf4j
@Api(tags = "调试专用")
public class WordController {


    @Resource
    private WordService wordService;

    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "查询单词")
    @PostMapping("/wordQuery")
    public Tip wordQuery(@Valid @RequestBody WordQueryReq req) throws Exception {
        log.info("查询单词：{}", req);
        return wordService.wordQuery(req);
    }

    @PostConstruct
    public void init() {
        log.info("token init");
        UserBean user = new UserBean();
        user.setAvatar("xxxxx");
        user.setUserName("humingming");
        user.setMobile("13888888888");
        user.setId(1L);
        redisUtil.set(RedisConstant.TOKEN + "humingming", JSON.toJSONString(user));
    }


}
