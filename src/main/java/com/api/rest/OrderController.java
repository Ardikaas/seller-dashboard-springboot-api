package com.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ItemRepository itemRepository;

  @GetMapping("/")
  ResponseWrapper<List<Order>> getAllOrder() {
    List<Order> orders = orderRepository.findAll();
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), orders);
  }
}
