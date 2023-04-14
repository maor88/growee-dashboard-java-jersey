/**
 * 
 */
package com.growee.codeGenerator;

/**
 * @author Maor
 *
 */

import java.security.SecureRandom;
import java.math.BigInteger;

public class StringCodeCreator {
	
	

	public static String generateForgotPassForURL() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	public static String generateFirstTimePassword() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(8);
	}
	
	public static String generateRegistrationCode() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(50, random).toString(32);
	}
	
	public static String generateCookiesSessionIdCode() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(50, random).toString(32);
	}
		
	
}
