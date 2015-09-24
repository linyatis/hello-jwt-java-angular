package br.com.flyra.examples.simple_java_angular_jwt.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

public class JWTUtil {

	private static final String SECRET = "my secret";

	/**
	 * 
	 * @param username Custom param for the middle part of the token.
	 * @return The token string.
	 */
	public static String createToken(String username) {
		JWTSigner signer = new JWTSigner(SECRET);

		HashMap<String, Object> claims = new HashMap<String, Object>();

		claims.put("user", username);

		String token = signer.sign(claims, new JWTSigner.Options()
				.setExpirySeconds(60 * 60).setIssuedAt(true));

		return token;

	}

	/**
	 * 
	 * @param The token string to be decoded.
	 * @return A map with the token params.
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalStateException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws JWTVerifyException
	 */
	public static Map<String, Object> decode(String token)
			throws InvalidKeyException, NoSuchAlgorithmException,
			IllegalStateException, SignatureException, IOException,
			JWTVerifyException {
		JWTVerifier verifier = new JWTVerifier(SECRET);

		Map<String, Object> map = verifier.verify(token);

		return map;
	}

}
