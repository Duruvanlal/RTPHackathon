/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class TokenResponseDTO implements Serializable{

	private static final long serialVersionUID = 7273927209542774023L;
Status status;
String access_token;
String refresh_token;
String expires_in;
String token_type;
public Status getStatus() {
	return status;
}
public void setStatus(Status status) {
	this.status = status;
}
public String getAccess_token() {
	return access_token;
}
public void setAccess_token(String access_token) {
	this.access_token = access_token;
}
public String getRefresh_token() {
	return refresh_token;
}
public void setRefresh_token(String refresh_token) {
	this.refresh_token = refresh_token;
}
public String getExpires_in() {
	return expires_in;
}
public void setExpires_in(String expires_in) {
	this.expires_in = expires_in;
}
public String getToken_type() {
	return token_type;
}
public void setToken_type(String token_type) {
	this.token_type = token_type;
}
@Override
public String toString() {
	return "TokenResponseDTO [status=" + status + ", access_token=" + access_token + ", refresh_token=" + refresh_token
			+ ", expires_in=" + expires_in + ", token_type=" + token_type + "]";
}

}
