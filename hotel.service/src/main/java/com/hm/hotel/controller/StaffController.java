package com.hm.hotel.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

  @GetMapping
  public ResponseEntity<List<String>> getStaffList(){
    final List<String> staffList = List.of("Mike","Tori","Black","Tom","Sam");
    return ResponseEntity.ok(staffList);
  }

}
