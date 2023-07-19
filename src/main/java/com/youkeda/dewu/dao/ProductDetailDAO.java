package com.youkeda.dewu.dao;

import com.youkeda.dewu.dataobject.ProductDetailDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDetailDAO {
    int deleteByPrimaryKey(String id);

    int insert(ProductDetailDO record);

    ProductDetailDO selectByPrimaryKey(String id);

    List<ProductDetailDO> selectAll();

    int updateByPrimaryKey(ProductDetailDO record);

    List<ProductDetailDO> selectByProductId(String productId);

}