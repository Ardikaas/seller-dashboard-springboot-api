package com.api.rest;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
  @Id
  public String id;
  public String itemName;
  public String imageUrl;
  public int quantity;
  public int price;
  public int totalQuantity;
  public List<Variant> variant;
}