package com.learning.payload.response;

import java.util.List;

public class JwtResponse {
	  private String token; // it is nothing but a encrypted string which will help us to access the secured end points from the server.
	  private String type = "Bearer";
	  private int id;
	  private String email;
	  private List<String> roles;

	  public JwtResponse(String accessToken, int id, String email, List<String> roles) {
	    this.token = accessToken;
	    this.id = id;
	    this.email = email;
	    this.roles = roles;
	  }

	  public String getAccessToken() {
	    return token;
	  }

	  public void setAccessToken(String accessToken) {
	    this.token = accessToken;
	  }

	  public String getTokenType() {
	    return type;
	  }

	  public void setTokenType(String tokenType) {
	    this.type = tokenType;
	  }

	  public int getId() {
	    return id;
	  }

	  public void setId(int id) {
	    this.id = id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public List<String> getRoles() {
	    return roles;
	  }
}
