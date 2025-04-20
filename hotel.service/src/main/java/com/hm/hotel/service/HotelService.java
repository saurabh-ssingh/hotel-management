package com.hm.hotel.service;

import com.hm.hotel.dto.HotelRequest;
import com.hm.hotel.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HotelService {

  /**
   * Registers a new hotel after validating all required fields
   * @param hotel Hotel entity to be registered
   * @return Registered hotel with generated ID and timestamps
   * @throws IllegalArgumentException if hotel data is invalid
   */
  Hotel registerHotel(HotelRequest hotelRequest);

  /**
   * Retrieves a hotel by its ID
   * @param hotelId UUID of the hotel
   * @return Optional containing the hotel if found
   */
  Optional<Hotel> getHotelById(String hotelId);

  /**
   * Retrieves all hotels with pagination support
   * @param pageable Pagination information
   * @return Page of hotels
   */
  Page<Hotel> getAllHotels(Pageable pageable);

  /**
   * Updates an existing hotel
   * @param hotelId ID of the hotel to update
   * @param hotelUpdate DTO with updated fields
   * @return Updated hotel entity
   * @throws com.hm.hotel.exceptions.ResourceNotFoundException if hotel not found
   */
//  Hotel updateHotel(String hotelId, HotelUpdateRequest hotelUpdate);

  /**
   * Deletes a hotel (soft delete)
   * @param hotelId ID of the hotel to delete

   */
  void deleteHotel(String hotelId);

  /**
   * Searches hotels by location
   * @param location Location to search for
   * @param pageable Pagination information
   * @return Page of matching hotels
   */
  Page<Hotel> searchHotelsByLocation(String location, Pageable pageable);

  /**
   * Finds hotels with rating equal or higher than specified
   * @param minRating Minimum rating value
   * @param pageable Pagination information
   * @return Page of matching hotels
   */
  Page<Hotel> findHotelsByRating(Double minRating, Pageable pageable);

  /**
   * Activates a previously deactivated hotel
   * @param hotelId ID of the hotel to activate
   * @return Activated hotel entity
   */
  Hotel activateHotel(String hotelId);


  /**
   * Gets hotel statistics (count by rating, by location, etc.)
   * @return HotelStatistics DTO
   */
  //HotelStatistics getHotelStatistics();
}