package com.api.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/chart")
public class DashboarController {
  @GetMapping("/sales")
  public ResponseWrapper<Dashboard> getSalesChart() {
    List<Integer> x = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
    List<Double> y = Arrays.asList(8.0, 6.9, 6.7, 6.0, 5.0, 4.0, 1.4, 1.1, 0.8);
    int total = 15632523;
    String percentage = "+9.49%";

    Dashboard salesData = new Dashboard("Total Sales", x, y, total, percentage);

    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), salesData);
  }

  @GetMapping("/orders")
  public ResponseWrapper<Dashboard> getOrderChart() {
    List<Integer> x = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
    List<Double> y = Arrays.asList(0.8, 1.1, 1.4, 4.0, 5.0, 6.0, 6.7, 6.9, 8.0);
    int total = 49394;
    String percentage = "-9.49%";

    Dashboard orderData = new Dashboard("Total Orders", x, y, total, percentage);

    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), orderData);
  }

}
