package com.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping("/")
  ResponseWrapper<Order> createOrder(@RequestBody Order order) {
    if (order.getUserName() == null || order.getAddress() == null || order.getPhoneNumber() == null
        || order.getQuantity() <= 0) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(400, "Bad Request"), null);
    }

    Item item = itemRepository.findById(order.getItemId()).orElse(null);
    if (item == null) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(404, "Item Not Found"), null);
    }

    order.setItemName(item.getItemName());

    boolean variantExists = false;
    for (Variant variant : item.getVariant()) {
      if (variant.getVariantName().equals(order.getVariant())) {
        if (variant.getQuantity() < order.getQuantity()) {
          return new ResponseWrapper<>(new ResponseWrapper.Status(400, "Insufficient Variant Quantity"), null);
        }
        variant.setQuantity(variant.getQuantity() - order.getQuantity());
        variantExists = true;
        break;
      }
    }
    if (!variantExists) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(400, "Variant Not Found"), null);
    }

    int totalQuantity = 0;
    for (Variant variant : item.getVariant()) {
      totalQuantity += variant.getQuantity();
    }
    item.setTotalQuantity(totalQuantity);

    itemRepository.save(item);

    if (order.getStatus() == null || order.getStatus().isEmpty()) {
      order.setStatus("Proceed");
    }
    Order createOrder = orderRepository.save(order);

    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), createOrder);
  }

  @PutMapping("/{id}/status-inprocess")
  ResponseWrapper<Order> updateOrderStatus(@PathVariable String id) {
    Order order = orderRepository.findById(id).orElse(null);
    if (order == null) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(404, "Order Not Found"), null);
    }

    order.setStatus("In Process");

    Order updatedOrder = orderRepository.save(order);

    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), updatedOrder);
  }

  @PutMapping("/{id}/status-readytoship")
  ResponseWrapper<Order> statusReadyToShip(@PathVariable String id) {
    Order order = orderRepository.findById(id).orElse(null);
    if (order == null) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(404, "Order Not Found"), null);
    }

    order.setStatus("Ready to Ship");

    Order updatedOrder = orderRepository.save(order);

    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), updatedOrder);
  }

  @PutMapping("/{id}/status-onshipping")
  ResponseWrapper<Order> statusShipping(@PathVariable String id) {
    Order order = orderRepository.findById(id).orElse(null);
    if (order == null) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(404, "Order Not Found"), null);
    }

    order.setStatus("On Shipping");

    Order updatedOrder = orderRepository.save(order);

    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), updatedOrder);
  }

  @PutMapping("/{id}/status-completed")
  ResponseWrapper<Order> statusComplete(@PathVariable String id) {
    Order order = orderRepository.findById(id).orElse(null);
    if (order == null) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(404, "Order Not Found"), null);
    }

    order.setStatus("Completed");

    Order updatedOrder = orderRepository.save(order);

    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), updatedOrder);
  }

  @DeleteMapping("/{id}")
  ResponseWrapper<String> deleteOrder(@PathVariable String id) {
    orderRepository.deleteById(id);
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), id);
  }

}
