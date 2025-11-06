package com.login.exemplo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {
	
	@NotNull(message = "O nome não pode ser nulo")
	private String name;
	
	@NotBlank(message = "O email não pode ser nulo")
	private String email;
	
	@Size(min = 6, max = 20 , message = "A senha deve ter no mínimo 6 caracteres")
	private String password;
    
	public UsuarioRequestDTO() {
		
	}
	
	public UsuarioRequestDTO(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    
}
