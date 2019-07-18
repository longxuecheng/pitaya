package org.lxc.mall.common.utils.token;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

/**
 * Token maker based on jwt
 * @author lxc
 *
 */
public class JwtTokenMaker {
	
	private static final String issuer = "geluxiya_issuer";
	
	public static final String UserClaimKey = "user";
	
	//HMAC
	private static Algorithm algorithmHS = Algorithm.HMAC256("secret");
	
	//Reusable verifier instance
	private static JWTVerifier verifier;
	
	static {
		verifier = JWT.require(algorithmHS)
		        .withIssuer(issuer)
		        .build();
	}
	
	public static class UserClaim {
		public UserClaim(Long id,String name) {
			this.id = id;
			this.name = name;
		}
		
		public UserClaim() {
			
		}
		
		public Long id;
		
		public String name;
		
		public String[] perms;

		@Override
		public String toString() {
			return "UserClaim [id=" + id + ", name=" + name + ", perms=" + Arrays.toString(perms) + "]";
		}
		
	}
	
	public static String signToken(UserClaim user,Date expiresAt) {
		Map<String,Object> headerClaim = new HashMap<>();
		headerClaim.put(UserClaimKey, user);
		String token = JWT.create()
				.withIssuer(issuer)
				.withHeader(headerClaim)
				.withExpiresAt(expiresAt)
				.sign(algorithmHS);
		return token;
	}
	
	public static UserClaim getClaim(String token) throws JWTVerificationException{
	    DecodedJWT jwt = verifier.verify(token);
	    Claim c = jwt.getHeaderClaim(UserClaimKey);
	    UserClaim uc = c.as(UserClaim.class);
		return uc;
	}
	
}
