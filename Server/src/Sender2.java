import java.io.IOException;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


public class Sender2 {
	static String myApiKey = "AIzaSyCX3YDJ7TMG8cpfSzZiBz1NhgaP1byxssY";
	static String regId = "APA91bFZmd2OCbGTP4QXnAnUGkleDhdrjzGFmK0DZUb9MY4YI4tdhlOoLAG4XeTvoVIgVH6xoLMDChdzJ4KaO0UpMJLt0oTmP6kq_GIWHujQ9_moN7J-Mj8bGkP9ItIuBvmVvRRvDaKK";

	public static void main(String[] args) {
		Sender sender = new Sender(myApiKey);
//		Message message = new Message.Builder(regId).build();
//		Result result = sender.send(message, 5);
		
        String registrationId = regId;
//        Message message = new Message.Builder().build();
        Message message = new Message.Builder()
        .collapseKey("collapseKey"+System.currentTimeMillis())
        .timeToLive(3)
        .delayWhileIdle(true)
        .addData("message", "ddddd"+System.currentTimeMillis())
        .build();
        
        Result result;
		try {
			result = sender.send(message, registrationId, 5);

			System.out.println("======= Send ======");
			
			if (result.getMessageId() != null) {
				String canonicalRegId = result.getCanonicalRegistrationId();
				System.out.println("canonicalRegId : " + canonicalRegId);
				if (canonicalRegId != null) {
					// same device has more than on registration ID: update database
					System.out.println("same device has more than on registration ID: update database");
				}
			} else {
				String error = result.getErrorCodeName();
				System.out.println("[ERROR]"+error);
				if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
					// application has been removed from device - unregister
					// database
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("======= END ======");
	}
}
