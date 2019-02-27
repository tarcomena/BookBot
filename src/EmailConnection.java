import java.util.ArrayList;
import java.util.Properties;
import java.io.*;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;

public class EmailConnection {

    private  String email;



    private Properties properties;

    private String port;

    private ArrayList<ArrayList<String>> bookAndInfo;


    /**Basic connection for Gmail
     *
     * @param email
     * @param password
     */

    public EmailConnection(String email, String password){
        this.email=email;
        bookAndInfo=new ArrayList<>();


        properties=System.getProperties();
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.pop3.host", "pop.gmail.com");
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        //
        try {
            Session emailSession = Session.getDefaultInstance(properties);


            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");
            //connection is started
            // little if any security :(
            store.connect("pop.gmail.com", email, password);


            Folder inbox = store.getFolder("INBOX");

            inbox.open(Folder.READ_ONLY);







            // retrieve the messages from the folder in an array and print it
            Message[] messages = inbox.getMessages();
            System.out.println("messages.length---" + messages.length);
            for(int i =messages.length-1;i>messages.length-messages.length+95;i--){
               // System.out.println(messages[i].getContent().toString());

                if(messages[i].getSubject().contains("library checkout receipt")){

                    if(messages[i].isMimeType("multipart/*")){



                        //brakes up email parts
                        MimeMultipart mimeParts = (MimeMultipart) messages[i].getContent();


                        //MultipartEmail test =new MultipartEmail(messages[i]);

                        for(int j =0; j< mimeParts.getCount();j++){

                            //decodes email
                            if(mimeParts.getBodyPart(j).isMimeType("text/plain")){
                                String s = (String) mimeParts.getBodyPart(j).getContent();
                                String test[]=s.split("\n");

                                ArrayList<String> bookInfo =new ArrayList<String>();

                               //scrapes email for data on book and adds it to list of book info
                               for (int k=0;k<test.length-1;k++){
                                   if(test[k].contains("Title:")||test[k].contains("Barcode:")||test[k].contains("Due Date:")){

                                       bookInfo.add(test[k]);

                                       //System.out.println(test[k]);
                                   }
                               }
                               this.bookAndInfo.add(bookInfo);

                            }




                        }
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("no connection");
        }
    }

    public ArrayList<ArrayList<String>> getBookAndInfo() {
        return bookAndInfo;
    }

    public String getEmail() {
        return email;
    }
}
