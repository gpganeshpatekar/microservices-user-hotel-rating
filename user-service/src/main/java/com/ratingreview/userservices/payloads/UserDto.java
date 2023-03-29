package com.ratingreview.userservices.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ratingreview.userservices.entities.Rating;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private String userId;

	@NotBlank
	@Size(min = 4, max = 20, message = "NAME MUST BE MIN OF 4 CHARCTERS AND MAX OF 20 CHARACTERS")
	private String name;
	
	@NotEmpty(message = "GENDER CANNOT BE BALNK")
	@Pattern(regexp = "m|M|male|Male|f|F|female|Female|o|other|Other|FEMALE|MALE|OTHER",message = "GENDER MUST BE MALE, FEMALE OR OTHER")
	private String gender;

	@Min(value = 14,message = "AGE SHOULD BE GREATER THAN 14 YEARS")
	private Integer age;

	@NotEmpty
	@Size(min = 4, max = 512, message = "ABOUT MUST BE MIN OF 6 CHARCTERS AND MAX OF 500 CHARACTERS")
	private String about;

	@Email
	@Pattern(regexp = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+",message = "VALID EMAIL ID IS REQUIRED.")
	private String email;

	@NotEmpty
	@Pattern(regexp = "[6-9][0-9]{9}",message = "VALID 10 DIGIT MOBILE NUMBER IS REQUIRED WITHOUT COUNTRY CODE Eg. 9892123456")
	private String mobile;

	@Size(min = 8,max = 16, message = "PASSWORD MUST BE MIN OF 6 CHARCTERS AND MAX OF 16 CHARACTERS")
	private String password;

	private boolean active;
	
	private LocalDateTime createdDateAndTime;
	
	private LocalDateTime updatedDateAndTime;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();
	

}
