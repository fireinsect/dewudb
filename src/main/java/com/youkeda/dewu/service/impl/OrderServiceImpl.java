package com.youkeda.dewu.service.impl;

import com.youkeda.dewu.dao.OrderDAO;
import com.youkeda.dewu.dataobject.OrderDO;
import com.youkeda.dewu.model.Order;
import com.youkeda.dewu.model.OrderStatus;
import com.youkeda.dewu.service.OrderService;
import com.youkeda.dewu.service.ProductDetailService;
import com.youkeda.dewu.service.UserService;
import com.youkeda.dewu.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Autowired
    Redisson redisson;

    @Override
    public Order add(Order order) {
        if (order.getProductDetailId()==null|| StringUtils.isBlank(order.getProductDetailId())){
            return null;
        }
        order.setId(UUIDUtils.getUUID());
        order.setStatus(OrderStatus.WAIT_BUYER_PAY);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = LocalDate.now().format(dateTimeFormatter);
        RAtomicLong atomicLong = redisson.getAtomicLong(now);
        atomicLong.expire(1, TimeUnit.DAYS);
        long number = atomicLong.incrementAndGet();
        String orderId = now + "" + number;
        order.setOrderNumber(orderId);
        orderDAO.insert(new OrderDO(order));
        return order;
    }
}
