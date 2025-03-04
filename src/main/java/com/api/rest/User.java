package com.api.rest;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  public String id;
  public String name;
  public String email;
  public String password;
  public String userImage;
  public int completeOrder;
  public int operationHour;
  public double rating;
}
