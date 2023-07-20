package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Order;
import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("api/order/")
public class OrderApi {
    @Autowired
    OrderService orderService;
    @Autowired
    UserAPI userAPI;

    @PostMapping("add")
    @ResponseBody
    public Result<Order> add(Order order, HttpServletRequest request){
        Result<Order> result=new Result<>();
        if (!userAPI.checkLogin(request).getData()){
            result.setSuccess(false);
            result.setCode("500");
            return result;
        }
        orderService.add(order);
        result.setData(order);
        result.setSuccess(true);
        result.setCode("200");
        return result;
    }
}
