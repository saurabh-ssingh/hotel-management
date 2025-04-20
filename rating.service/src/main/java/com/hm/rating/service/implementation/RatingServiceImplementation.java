package com.hm.rating.service.implementation;

import com.hm.rating.entities.Rating;
import com.hm.rating.repository.RatingRepository;
import com.hm.rating.service.RatingService;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RatingServiceImplementation implements RatingService {
  private final RatingRepository ratingRepository;

  public RatingServiceImplementation(RatingRepository ratingRepository){
    this.ratingRepository = ratingRepository;
  }
  @Override
  public Rating createRating(Rating rating) {
    rating.setRatingId(UUID.randomUUID().toString());
    return this.ratingRepository.save(rating);
  }

  @Override
  public List<Rating> getRatings() {
    return this.ratingRepository.findAll();
  }

  @Override
  public List<Rating> getRatingByUserId(String userId) {
    log.info("Fetching ratings for userId: {}", userId);
    List<Rating> ratings = this.ratingRepository.findByUserId(userId);

    if (ratings.isEmpty()) {
      log.warn("No ratings found for userId: {}", userId);
      return Collections.emptyList(); // Return empty list
    }

    log.info("Found {} ratings for userId: {}", ratings.size(), userId);
    return ratings;
  }



  @Override
  public List<Rating> getRatingByHotelId(String hotelId) {
    return this.ratingRepository.findByHotelId(hotelId);
  }
}
