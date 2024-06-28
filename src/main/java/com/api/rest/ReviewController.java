package com.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/review")
public class ReviewController {
  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private ItemRepository itemRepository;

  @GetMapping("/")
  ResponseWrapper<List<Review>> getAllReview() {
    List<Review> reviews = reviewRepository.findAll();
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), reviews);
  }

  @PostMapping("/")
  ResponseWrapper<Review> createReview(@RequestBody Review review) {
    if (review.getUserName() == null || review.getUserName().isEmpty() ||
        review.getUserImage() == null || review.getUserImage().isEmpty() ||
        review.getQuantity() <= 0 || review.getRating() <= 0.0) {
      return new ResponseWrapper<>(new ResponseWrapper.Status(400, "Bad Request"), null);
    }
    // Ambil itemName berdasarkan itemId dari database item
    Item item = itemRepository.findById(review.getItemId()).orElse(null);
    if (item != null) {
      review.setItemName(item.getItemName());
    } else {
      return new ResponseWrapper<>(new ResponseWrapper.Status(404, "Item Not Found"), null);
    }

    // Simpan review ke dalam repository
    Review createdReview = reviewRepository.save(review);

    // Kembalikan respons dengan status 200 dan review yang sudah dibuat
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), createdReview);
  }

  @DeleteMapping("/{id}")
  ResponseWrapper<String> deleteReview(@PathVariable String id) {
    reviewRepository.deleteById(id);
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), id);
  }
}
