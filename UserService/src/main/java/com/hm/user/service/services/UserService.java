package com.hm.user.service.services;

import com.hm.user.service.entities.User;
import java.util.List;

public interface UserService {
  User saveUser(User user);
  List<User> getAllUser();
  User getUser(String userId);

}
