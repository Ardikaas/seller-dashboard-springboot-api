package com.api.rest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
  public String title;
  public List<Integer> x;
  public List<Double> y;
  public int total;
  public String percentage;
}
