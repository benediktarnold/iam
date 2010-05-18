package de.metafinanz.campus.iam.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import de.metafinanz.campus.iam.entities.WikiPage;

@Named("pageCreator")
@Scope("request")
public class PageCreatorAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String newPage() {
		WikiPage newPage = null;
		try {
			long timeInMillis = Calendar.getInstance().getTimeInMillis();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(Long.toHexString(timeInMillis).getBytes());
			byte[] digest = md.digest();
			StringBuilder hexString = new StringBuilder();
			for (byte b : digest) {
				hexString.append(Integer.toHexString(0xFF & b));
			}
			System.out.println(hexString);
			newPage = new WikiPage(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return "page?faces-redirect=true&magnet=" + newPage.getMagnet();
	}
}
