package com.hm.user.service.services.implementation;

import com.hm.user.service.clients.RatingClient;
import com.hm.user.service.entities.Rating;
import com.hm.user.service.entities.User;
import com.hm.user.service.exception.ResourceNotFoundException;
import com.hm.user.service.repository.UserRepository;
import com.hm.user.service.services.UserService;
import feign.FeignException;
import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImplementation implements UserService {
  @Autowired
  private UserRepository userRepository;

  private final RatingClient ratingClient;

  @Autowired
  public UserServiceImplementation(RatingClient ratingClient) {
    this.ratingClient = ratingClient;
  }
  @Override
  public User saveUser(User user) {
    String randomUserId = UUID.randomUUID().toString();
    user.setUserId(randomUserId);
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public User getUser(String userId) {
    log.info("Fetching user with ID: {}", userId); // Log when the method is called

    // Getting user from user repository
    final User user = userRepository.findById(userId).orElseThrow(() -> {
      log.error("User with ID: {} not found", userId); // Log if user is not found
      return new ResourceNotFoundException("User Not Found.");
    });

    log.info("User found: {}", user.getUserId()); // Log user details once retrieved

    try {
      // Fetch ratings using Feign
      log.info("Fetching ratings for user ID: {}", userId);
      List<Rating> ratings = ratingClient.getRatingByUserId(userId);
      user.setRatings(ratings);
      log.info("Successfully fetched ratings for user ID: {}", userId); // Log successful ratings fetch
    } catch (FeignException e) {
      log.error("FeignException occurred while fetching ratings for user ID: {}", userId, e); // Log Feign exception
      throw new RuntimeException("Failed to fetch ratings.");
    } catch (Exception e) {
      log.error("An unexpected error occurred while fetching ratings for user ID: {}", userId, e); // Log any other errors
      throw new RuntimeException("Unexpected error occurred while fetching ratings.");
    }

    return user;
  }
}
