package com.hm.hotel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {

    @NotBlank(message = "Hotel name is required")
    @Size(max = 100, message = "Hotel name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location must be less than 255 characters")
    private String location;

    @Size(max = 1000, message = "About description must be less than 1000 characters")
    private String about;

    @Pattern(regexp = "^[0-5](\\.[0-9])?$", message = "Rating must be between 0.0 and 5.0 with one decimal place")
    private String rating;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String contactEmail;

    @Size(max = 20, message = "Contact phone must be less than 20 characters")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", 
             message = "Invalid phone number format")
    private String contactPhone;
}