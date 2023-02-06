package com.newland.lanhe.product.service.impl;

import com.newland.lanhe.product.entity.Product;
import com.newland.lanhe.product.mapper.ProductMapper;
import com.newland.lanhe.product.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-05
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
