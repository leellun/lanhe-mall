package com.newland.lanhe.order.dao;

import com.newland.lanhe.order.model.dto.OrderDeliveryDto;
import com.newland.lanhe.order.model.vo.OrderDetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单自定义查询Dao
 */
@Repository
public interface OrderDao {

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OrderDeliveryDto> deliveryParamList);
}
