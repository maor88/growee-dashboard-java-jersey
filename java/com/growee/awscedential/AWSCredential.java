/**
 * 
 */
package com.growee.awscedential;

import com.amazonaws.auth.AWSCredentials;

/**
 * @author Maor
 *
 */
public class AWSCredential {

	
	public static final String acccess_key_id = "";
	public static final String secret_access_key = "";

	public static String bucketName = "groweeappimage";
	public static String keyName = "sample";
	AWSCredentials credentials = new AWSCredentials() {

		@Override
		public String getAWSSecretKey() {
			return "";
		}

		@Override
		public String getAWSAccessKeyId() {
			return "";
		}
	};
	
}
