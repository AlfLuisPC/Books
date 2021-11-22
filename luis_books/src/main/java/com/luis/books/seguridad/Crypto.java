package com.luis.books.seguridad;

import java.security.Key;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Crypto {

	public static String hmac(String mensaje) {
		
		try {
			Mac magic = Mac.getInstance("HmacSHA512");
	
			String sal = "melibooks";
			byte[] salt = sal.getBytes("UTF-8");
			byte[] valueBytes = mensaje.getBytes("UTF-8");
			Key key = new SecretKeySpec(salt, "HmacSHA512");
			magic.init(key);
			return Base64.encodeBase64String(magic.doFinal(valueBytes));
			
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
}

