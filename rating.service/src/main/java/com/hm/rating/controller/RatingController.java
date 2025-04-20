package com.hm.rating.controller;

import com.hm.rating.entities.Rating;
import com.hm.rating.service.RatingService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
@Log4j2
public class RatingController {
  private final RatingService ratingService;
  public RatingController(RatingService ratingService){
    this.ratingService = ratingService;
  }

  @PostMapping
  public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
    return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.createRating(rating));
  }

  @GetMapping
  public ResponseEntity<List<Rating>> getRatings(){
    return ResponseEntity.ok(this.ratingService.getRatings());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
    log.info("Started API get rating by user id : {}",userId);
    return ResponseEntity.ok(this.ratingService.getRatingByUserId(userId));
  }

  @GetMapping("/{hotel-id}")
  public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
    return ResponseEntity.ok(this.ratingService.getRatingByHotelId(hotelId));
  }

}
