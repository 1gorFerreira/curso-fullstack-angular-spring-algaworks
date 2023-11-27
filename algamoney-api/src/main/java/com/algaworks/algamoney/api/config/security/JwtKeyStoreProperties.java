package com.algaworks.algamoney.api.config.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
public class JwtKeyStoreProperties {

//	@NotBlank
//	private String path;
	
//	Trocamos para Resource para usar o classpath:... no application.properties
	
	@NotNull
	private Resource jksLocation;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String keypairAlias;

	public Resource getJksLocation() {
		return jksLocation;
	}

	public void setJksLocation(Resource jksLocation) {
		this.jksLocation = jksLocation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeypairAlias() {
		return keypairAlias;
	}

	public void setKeypairAlias(String keypairAlias) {
		this.keypairAlias = keypairAlias;
	}
}
