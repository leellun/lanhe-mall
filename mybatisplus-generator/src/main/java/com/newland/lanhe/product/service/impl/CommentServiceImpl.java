package com.newland.lanhe.product.service.impl;

import com.newland.lanhe.product.entity.Comment;
import com.newland.lanhe.product.mapper.CommentMapper;
import com.newland.lanhe.product.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
