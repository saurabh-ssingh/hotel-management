package com.hm.hotel.service.implementation;

import com.hm.hotel.dto.HotelRequest;
import com.hm.hotel.entities.Hotel;
import com.hm.hotel.repository.HotelRepository;
import com.hm.hotel.service.HotelService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImplementation implements HotelService {

  private final HotelRepository hotelRepository;
  public HotelServiceImplementation(HotelRepository hotelRepository){
    this.hotelRepository = hotelRepository;
  }

  @Override
  public Hotel registerHotel(HotelRequest hotelRequest) {
    Hotel hotel = Hotel.builder()
        .name(hotelRequest.getName())
        .location(hotelRequest.getLocation())
        .about(hotelRequest.getAbout())
        .rating(hotelRequest.getRating())
        .contactEmail(hotelRequest.getContactEmail())
        .contactPhone(hotelRequest.getContactPhone())
        .isActive(true)
        .build();

    return this.hotelRepository.save(hotel);

  }

  @Override
  public Optional<Hotel> getHotelById(String hotelId) {
    return this.hotelRepository.findById(hotelId);
  }

  @Override
  public Page<Hotel> getAllHotels(Pageable pageable) {
    return hotelRepository.findAllByIsActiveTrue(pageable);
  }

  @Override
  public void deleteHotel(String hotelId) {

  }

  @Override
  public Page<Hotel> searchHotelsByLocation(String location, Pageable pageable) {
    return null;
  }

  @Override
  public Page<Hotel> findHotelsByRating(Double minRating, Pageable pageable) {
    return null;
  }

  @Override
  public Hotel activateHotel(String hotelId) {
    return null;
  }

}
