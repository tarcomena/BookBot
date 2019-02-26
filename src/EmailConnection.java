import java.util.Properties;

import javax.mail.*;

public class EmailConnection {

    private  String email;

    private String password;

    private Properties connectionProperies;

    private String port;





    public EmailConnection(String email, String password,String port ){
        this.email=email;
        this.password=password;
        this.port=port;
        connectionProperies=System.getProperties();
        connectionProperies.setProperty("mail.store.protocol", "imaps");
        connectionProperies.put("mail.imap-mail.outlook.com.ssl.enable", "true");
        connectionProperies.put("mail.pop3.host", "outlook.com");
        connectionProperies.put("mail.pop3.port", port);
        connectionProperies.put("mail.pop3.starttls.enable", "true");
        try {
            Session session = Session.getInstance(connectionProperies, null);
            Store store = session.getStore();
            store.connect("imap-mail.outlook.com", "myEmail@live.dk", "MyPassword");
            session.setDebug(true);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);


            SearchTerm sender = new FromTerm(new InternetAddress("myMail@live.dk"));

            Message[] messages = inbox.search(sender);
            System.out.println(messages);

            for (int i = 0 ; i < messages.length ; i++) {

                System.out.println(messages[i].getSubject());
                if (messages[i].getSubject().equals(null)) {
                    System.out.println("null in subject");
                    break;
                }
                else if (messages[i].getSubject().contains("Ordretest")){
                    System.out.println("1 match found");
                }
                else {
                    System.out.println("no match");
                }
            }
            System.out.println("no more messages");
            store.close();

        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }

}
