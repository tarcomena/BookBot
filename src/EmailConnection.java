import java.util.Properties;
import java.io.*;

import javax.mail.*;

public class EmailConnection {

    private  String email;

    private String password;

    private Properties properties;

    private String port;





    public EmailConnection(String email, String password){
        this.email=email;
        this.password=password;
        this.port=port;
        properties=System.getProperties();
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.pop3.host", "pop.gmail.com");
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");

        try {
            Session session = Session.getInstance(properties, null);

            Store store = session.getStore();

            store.connect("pop.gmail.com", email, password);

            session.setDebug(true);

            Folder inbox = store.getFolder("INBOX");

            inbox.open(Folder.READ_ONLY);


            store = session.getStore("pop3s");




            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // retrieve the messages from the folder in an array and print it
            Message[] messages = inbox.getMessages();
            System.out.println("messages.length---" + messages.length);

        } catch (Exception e) {
            System.out.println("no connection");
        }
    }

}
