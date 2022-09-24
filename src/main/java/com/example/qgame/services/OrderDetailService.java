package com.example.qgame.services;

import com.example.qgame.Models.Order;
import com.example.qgame.Models.OrderDetail;
import com.example.qgame.helpers.dto.OrderItemDto;
import com.example.qgame.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public List<OrderDetail> saveMany(List<OrderItemDto> orderItemDtos, Order order) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        orderItemDtos.forEach(odt->{
            orderDetails.add(
                    new OrderDetail()
                    .setOrder(order)
                    .setPriceEach(odt.getProduct().getPrice())
                    .setDiscountPercentage(odt.getProduct().getDiscountPercentage())
                    .setProduct(odt.getProduct())
                    .setQuantity(odt.getQuantity())
            );
        });

        return orderDetailRepository.saveAll(orderDetails);

    }
}
