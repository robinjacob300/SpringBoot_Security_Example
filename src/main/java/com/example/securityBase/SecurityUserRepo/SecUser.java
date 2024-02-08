package com.example.securityBase.SecurityUserRepo;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SecUser {

	@Id
	@Column
	String userid = UUID.randomUUID().toString();
	@Nonnull
	@Column
	String username;
	@Column
	String password;
	
	@Column
	String secretQuestion;
	
	String role;
}
