package com.hm.rating.repository;

import com.hm.rating.entities.Rating;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {
  List<Rating> findByUserId(String userId);
  List<Rating> findByHotelId(String hotelId);
}
