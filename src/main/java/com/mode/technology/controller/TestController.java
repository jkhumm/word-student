package com.mode.technology.controller;


import com.mode.technology.annotation.WithoutLogin;
import com.mode.technology.mybatis.dao.CustomerMapper;
import com.mode.technology.mybatis.dao.TOrderDao;
import com.mode.technology.mybatis.entity.Customer;
import com.mode.technology.mybatis.entity.TOrder;
import com.mode.technology.vo.tip.Tip;
import com.mode.technology.vo.tip.TipUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/test")
@Slf4j
@Api(tags = "测试专用")
public class TestController {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private TOrderDao torderDao;


    @ApiOperation(value = "测试新增 正常数据")
    @WithoutLogin
    @GetMapping("/addNormal")
    public Tip<Void> addNormal() {
        Customer customer = new Customer();
        customer.setName("jack");
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        customerMapper.insert(customer);
        return TipUtil.success();
    }

    @ApiOperation(value = "测试分库分表插入")
    @WithoutLogin
    @GetMapping("/add")
    public Tip<Void> add(@RequestParam Integer userId, @RequestParam String zone) {
        TOrder order = new TOrder();
        order.setUserId(userId);
        order.setZone(zone);
        torderDao.insertSelective(order);
        return TipUtil.success();
    }

    @ApiOperation(value = "测试分库分表查询")
    @WithoutLogin
    @GetMapping("/selectAll")
    public Tip<List<TOrder>> selectAll() {
        List<TOrder> orders = torderDao.selectByExample(null);
        return TipUtil.success(orders);
    }

    @ApiOperation(value = "测试分库分表插入-local事务")
    @WithoutLogin
    @GetMapping("/addTransactional")
   // @Transactional
   // @ShardingTransactionType(value = TransactionType.XA)
    public Tip<Void> addTransactional() {
        TOrder order = new TOrder();
        order.setUserId(10);
        order.setZone("bj");
        torderDao.insertSelective(order);

        TOrder order1 = new TOrder();
        order1.setUserId(10);
        order1.setZone("sh");
        torderDao.insertSelective(order1);

        TOrder order2 = new TOrder();
        order2.setUserId(10);
        order2.setZone("sz");
        torderDao.insertSelective(order2);
        int a = 1/0;
        return TipUtil.success();
    }

}
