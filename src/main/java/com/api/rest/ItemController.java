package com.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/item")
public class ItemController {
  @Autowired
  private ItemRepository itemRepository;

  @GetMapping("/")
  ResponseWrapper<List<Item>> getAllItems() {
    List<Item> items = itemRepository.findAll();
    for (Item item : items) {
      int totalQuantity = item.getQuantity();
      if (item.getVariant() != null) {
        for (Variant variant : item.getVariant()) {
          totalQuantity += variant.getQuantity();
        }
      }
      item.setTotalQuantity(totalQuantity);
    }
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), items);
  }

  @GetMapping("/{id}")
  ResponseWrapper<Item> getItemById(@PathVariable String id) {
    Item item = itemRepository.findById(id).orElse(null);
    if (item != null) {
      int totalQuantity = item.getQuantity();
      if (item.getVariant() != null) {
        for (Variant variant : item.getVariant()) {
          totalQuantity += variant.getQuantity();
        }
      }
      item.setTotalQuantity(totalQuantity);
    }
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), item);
  }

  @PostMapping("/")
  ResponseWrapper<Item> createItem(@RequestBody Item item) {
    if (item.getItemName() == null || item.getQuantity() <= 0 || item.getPrice() <= 0) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(400, "Bad Request"), null);
    }

    if (item.getImageUrl() == null || item.getImageUrl().isEmpty()) {
      item.setImageUrl("https://dummyimage.com/300x300");
    }

    item.setTotalQuantity(item.getQuantity());
    item.setVariant(new ArrayList<>());

    Item createItem = itemRepository.save(item);
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), createItem);
  }

  @PutMapping("/{id}/add-variant")
  ResponseWrapper<Item> addVariant(@PathVariable String id, @RequestBody Map<String, Object> variantRequest) {
    Item item = itemRepository.findById(id).orElse(null);
    if (item == null) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(404, "Item Not Found"), null);
    }

    List<Variant> variants = item.getVariant();
    if (variants == null) {
      variants = new ArrayList<>();
    }

    String variantName = (String) variantRequest.get("variantName");
    int quantity = (int) variantRequest.get("quantity");

    Variant newVariant = new Variant(variantName, quantity);
    variants.add(newVariant);

    item.setQuantity(0);

    item.setVariant(variants);

    int totalQuantity = 0;
    for (Variant variant : variants) {
      totalQuantity += variant.getQuantity();
    }
    item.setTotalQuantity(totalQuantity);
    Item updatedItem = itemRepository.save(item);
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), updatedItem);
  }

  @PutMapping("/{id}")
  ResponseWrapper<Item> editItem(@PathVariable String id, @RequestBody Item item) {
    Item oldData = itemRepository.findById(id).orElse(null);
    if (oldData != null) {
      oldData.setItemName(item.getItemName());
      oldData.setImageUrl(item.getImageUrl());
      oldData.setPrice(item.getPrice());

      Item updateItem = itemRepository.save(oldData);

      return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), updateItem);
    } else {
      return new ResponseWrapper<>(new ResponseWrapper.Status(400, "Success"), null);
    }
  }

  @DeleteMapping("/{id}")
  ResponseWrapper<String> deleteItem(@PathVariable String id) {
    itemRepository.deleteById(id);
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), id);
  }
}
