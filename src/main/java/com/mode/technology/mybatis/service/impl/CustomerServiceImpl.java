package com.mode.technology.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mode.technology.mybatis.dao.CustomerMapper;
import com.mode.technology.mybatis.entity.Customer;
import com.mode.technology.mybatis.service.CustomerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商户表 服务实现类
 * </p>
 *
 * @author heian
 * @since 2022-06-21
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
