package com.hm.user.service.controller;

import com.hm.user.service.entities.User;
import com.hm.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<User> saveUser(@RequestBody User user){
    return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.saveUser(user));
  }

  @GetMapping("/{userId}")
  @CircuitBreaker(name = "ratingHotelCircuitBreaker", fallbackMethod = "ratingHotelFallback")
  public ResponseEntity<User> getSingleUser(@PathVariable String userId){
    log.info("Started getting single user detail by user id : {}",userId);
    return ResponseEntity.ok(this.userService.getUser(userId));
  }

  // This fallback method must match the same parameters as the original method, plus an optional Throwable parameter
  public ResponseEntity<User> ratingHotelFallback(String userId, Throwable throwable) {
    log.error("Fallback is executed because service is down. Error: {}", throwable.getMessage());

    // Create a dummy User object (or you can customize it)
    User dummyUser = User.builder()
        .userId("0000")
        .email("dummyuser@example.com")
        .name("Dummy User")
        .about("This is a dummy user because the service is down")
        .build();

    return new ResponseEntity<>(dummyUser, HttpStatus.OK);
  }



  @GetMapping
  public ResponseEntity<List<User>> getAllUser(){
    return ResponseEntity.ok(this.userService.getAllUser());
  }

}
