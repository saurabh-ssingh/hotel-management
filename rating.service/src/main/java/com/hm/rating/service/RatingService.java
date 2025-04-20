package com.hm.rating.service;

import com.hm.rating.entities.Rating;
import java.util.List;

public interface RatingService {
  Rating createRating(Rating rating);
  List<Rating> getRatings();
  List<Rating> getRatingByUserId(String userId);
  List<Rating> getRatingByHotelId(String hotelId);
}
