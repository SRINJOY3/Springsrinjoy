package com.capgemini.Springsrinjoy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity 
@Data
@Builder
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank(message = "Patient name cannot be blank")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 120, message = "Age cannot be greater than 120")
    private int age;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotBlank(message = "Disease field cannot be empty")
    private String disease;

    @DecimalMin(value = "0.0", inclusive = true, message = "Bill amount cannot be negative")
    private Double billAmount;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
	
}
