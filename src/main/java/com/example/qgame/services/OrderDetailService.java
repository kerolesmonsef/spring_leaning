package com.example.qgame.services;

import com.example.qgame.Models.Order;
import com.example.qgame.Models.OrderDetail;
import com.example.qgame.Models.Product;
import com.example.qgame.repositories.OrderDetailRepository;
import com.example.qgame.repositories.ProductRepository;
import com.example.qgame.requests.OrderItemRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {


    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, ProductRepository productRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveMany(List<OrderDetail> orderDetails, Order order) {
        orderDetails.forEach(od -> od.setOrder(order));
        orderDetailRepository.saveAll(orderDetails);
    }

    public List<OrderDetail> convertToOrderDetail(List<OrderItemRequest> orderItemRequests) {
        List<Long> productIds = orderItemRequests.stream().map((oir) -> oir.getProductId()).toList();
        List<Product> products = productRepository.getByIds(productIds);
        List<OrderDetail> orderDetials = new ArrayList<>();


        return orderDetials;
    }
}
