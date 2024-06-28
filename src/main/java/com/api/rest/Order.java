package com.api.rest;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  public String id;
  public String userName;
  public String userImage;
  public String itemId;
  public String itemName;
  public String variant;
  public int quantity;
  public String address;
  public String phoneNumber;
  public String expedition;
  public String status;
}
