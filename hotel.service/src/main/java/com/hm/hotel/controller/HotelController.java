package com.hm.hotel.controller;

import com.hm.hotel.dto.HotelRequest;
import com.hm.hotel.entities.Hotel;
import com.hm.hotel.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

  private final HotelService hotelService;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @PostMapping("/register")
  public ResponseEntity<Hotel> registerHotel(@RequestBody @Valid HotelRequest hotelRequest) {
    Hotel registeredHotel = hotelService.registerHotel(hotelRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredHotel);
  }

  @GetMapping("/{hotelId}")
  public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
    return hotelService.getHotelById(hotelId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Page<Hotel>> getAllHotels(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "name") String sortBy,
      @RequestParam(defaultValue = "asc") String direction) {

    Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
        ? Sort.Direction.DESC
        : Sort.Direction.ASC;

    PageRequest pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
    Page<Hotel> hotels = hotelService.getAllHotels(pageable);

    return ResponseEntity.ok(hotels);
  }

//  @PutMapping("/{hotelId}")
//  public ResponseEntity<Hotel> updateHotel(
//      @PathVariable String hotelId,
//      @RequestBody @Valid HotelUpdateRequest hotelUpdate) {
//
//    Hotel updatedHotel = hotelService.updateHotel(hotelId, hotelUpdate);
//    return ResponseEntity.ok(updatedHotel);
//  }

  @DeleteMapping("/{hotelId}")
  public ResponseEntity<Void> deleteHotel(@PathVariable String hotelId) {
    hotelService.deleteHotel(hotelId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public ResponseEntity<Page<Hotel>> searchHotelsByLocation(
      @RequestParam String location,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

    PageRequest pageable = PageRequest.of(page, size);
    Page<Hotel> hotels = hotelService.searchHotelsByLocation(location, pageable);
    return ResponseEntity.ok(hotels);
  }

  @GetMapping("/by-rating")
  public ResponseEntity<Page<Hotel>> findHotelsByRating(
      @RequestParam Double minRating,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

    PageRequest pageable = PageRequest.of(page, size);
    Page<Hotel> hotels = hotelService.findHotelsByRating(minRating, pageable);
    return ResponseEntity.ok(hotels);
  }

  @PatchMapping("/{hotelId}/activate")
  public ResponseEntity<Hotel> activateHotel(@PathVariable String hotelId) {
    Hotel activatedHotel = hotelService.activateHotel(hotelId);
    return ResponseEntity.ok(activatedHotel);
  }

//  @GetMapping("/statistics")
//  public ResponseEntity<HotelStatistics> getHotelStatistics() {
//    HotelStatistics statistics = hotelService.getHotelStatistics();
//    return ResponseEntity.ok(statistics);
//  }
}
