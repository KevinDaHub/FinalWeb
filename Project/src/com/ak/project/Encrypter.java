package com.ak.project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class Encrypter {

	String salt = "sutyvqnpsoeij,qoeiosUQEGHRUEHueh<uohOUEHtUehfOUeht126546randomshizzlesalt";

	public String encrypt(String text) {
		MessageDigest md;
		StringBuffer hexString = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update((text + "" + salt).getBytes());
			byte[] byteData = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}


			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hexString.toString();

	}
}
