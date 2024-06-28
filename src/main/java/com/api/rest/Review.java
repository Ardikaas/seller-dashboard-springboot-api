package com.api.rest;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
  @Id
  public String id;
  public String userName;
  public String userImage;
  public String itemId;
  public String itemName;
  public String variant;
  public int quantity;
  public double rating;
  public String testimoni;
}
