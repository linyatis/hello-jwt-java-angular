package br.com.flyra.examples.simple_java_angular_jwt.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import br.com.flyra.examples.simple_java_angular_jwt.exception.TokenException;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

public class JWTUtil {

	private static final String SECRET = "my secret";

	/**
	 * 
	 * @param username
	 *            Custom param for the middle part of the token.
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
	 * @param token
	 *            The token string to be decoded.
	 * @return A map with the token params.
	 * @throws TokenException
	 */
	public static Map<String, Object> decode(String token)
			throws TokenException {

		JWTVerifier verifier = new JWTVerifier(SECRET);

		Map<String, Object> map = null;
		try {
			map = verifier.verify(token);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| IllegalStateException | SignatureException | IOException
				| JWTVerifyException e) {

			throw new TokenException(e.getLocalizedMessage(), e.getCause());
		}

		return map;
	}

}
