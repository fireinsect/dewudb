package com.youkeda.dewu.service.impl;

import com.youkeda.dewu.dao.ProductDAO;
import com.youkeda.dewu.dataobject.ProductDO;
import com.youkeda.dewu.model.Paging;
import com.youkeda.dewu.model.Product;
import com.youkeda.dewu.param.BasePageParam;
import com.youkeda.dewu.service.ProductService;
import com.youkeda.dewu.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;

    @Override
    public int save(Product product) {
        product.setId(UUIDUtils.getUUID());
        return productDAO.insert(new ProductDO(product));
    }

    @Override
    public Paging<Product> pageQueryProduct(BasePageParam param) {
        List<Product> res=productDAO.selectAll().stream()
                .map(productDO -> productDO.convertToModel())
                .collect(Collectors.toList());
        Paging<Product> productPaging=new Paging<Product>(param.getPagination(),param.getPageSize(),(int)Math.ceil(res.size()/ param.getPageSize()),res.size(),res);
        return productPaging;
    }
}
