package com.mode.technology.service;

import com.mode.technology.mybatis.entity.Customer;
import com.mode.technology.mybatis.service.CustomerService;
import com.mode.technology.vo.req.WordQueryReq;
import com.mode.technology.vo.tip.Tip;
import com.mode.technology.vo.tip.TipUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heian
 * @date 2022/6/20 23:43
 */
@Service
public class WordService {

    @Resource
    private CustomerService customerService;

    public Tip wordQuery(WordQueryReq req) {
        List<Customer> list = customerService.lambdaQuery().list();
        return TipUtil.success(list);
    }

}
