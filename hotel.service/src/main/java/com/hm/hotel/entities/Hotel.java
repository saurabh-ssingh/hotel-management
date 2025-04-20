package com.hm.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "hotels") // Using lowercase for table name as common convention
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Added builder pattern for easier object creation
public class Hotel {

  @Id
  @UuidGenerator // Generates UUID automatically
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @NotBlank(message = "Hotel name is required")
  @Size(max = 100, message = "Hotel name must be less than 100 characters")
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @NotBlank(message = "Location is required")
  @Size(max = 255, message = "Location must be less than 255 characters")
  @Column(name = "location", nullable = false)
  private String location;

  @Size(max = 1000, message = "About description must be less than 1000 characters")
  @Column(name = "about", length = 1000)
  private String about;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive = true; // Soft delete flag

  @Column(name = "rating")
  private String rating; // Hotel rating (e.g., 4.5)

  @Column(name = "contact_email", length = 100)
  @Email(message = "Email should be valid") // Add this annotation
  @Size(max = 100, message = "Email must be less than 100 characters")
  private String contactEmail;

  @Column(name = "contact_phone", length = 20)
  private String contactPhone;

}
