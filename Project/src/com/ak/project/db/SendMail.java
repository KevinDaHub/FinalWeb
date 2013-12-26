package com.ak.project.db;

import com.ak.project.activities.Mail;

import android.os.AsyncTask;
import android.util.Log;

public class SendMail extends AsyncTask<String, String, String> {

	private String subject;
	
	public SendMail(String subject) {
		this.subject = subject;
	}

	@Override
	protected String doInBackground(String... params) {
		String text =  "order not sent";
		Mail m = new Mail("appquickorder@gmail.com", "quickorder1");

		String[] to = { "appquickorder@gmail.com" };
		m.setTo(to);
		m.setFrom("appquickorder@gmail.com");
		m.setSubject("bestelling");
		m.setBody(subject);

		try {
			if (m.send()) {
				text = "order sent";
			}
		} catch (Exception e) {
			// Toast.makeText(MailApp.this,
			// "There was a problem sending the email.",
			// Toast.LENGTH_LONG).show();
			Log.e("MailApp", "Could not send email", e);
		}
		return text;
	}

}
