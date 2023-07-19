package com.youkeda.dewu.api;

import com.youkeda.dewu.model.Paging;
import com.youkeda.dewu.model.Product;
import com.youkeda.dewu.model.Result;
import com.youkeda.dewu.param.BasePageParam;
import com.youkeda.dewu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/product")
public class ProductApi {
    @Autowired
    ProductService productService;

    @GetMapping("page")
    @ResponseBody
    public Result<Paging<Product>> page(BasePageParam basePageParam){
        Result<Paging<Product>> res=new Result<>();
        res.setData(productService.pageQueryProduct(basePageParam));
        res.setCode("200");
        res.setMessage("hh");
        res.setSuccess(true);
        return res;
    }
}
