import java.util.ArrayList;
import java.util.Properties;
import java.io.*;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;

public class MultipartEmail {
    /**
     * Keeps number of parts
     */
    private int numberOfParts;

    /**
     * number of email parts that are text
     */
    private int textParts;

    /**
     * keeps email text in order
     */
    private ArrayList<String> emailContents;

    public MultipartEmail(Message email){


        try {
            MimeMultipart mimeParts = (MimeMultipart) email.getContent();

            numberOfParts=mimeParts.getCount();

            for(int i =0;i<numberOfParts;i++){

                BodyPart bodyPart = mimeParts.getBodyPart(i);

                String result = "";
                if (bodyPart.isMimeType("text/plain")) {

                    emailContents.add((String) bodyPart.getContent());
                } else if (bodyPart.isMimeType("text/html")) {
                    String html = (String) bodyPart.getContent();
                    result = org.jsoup.Jsoup.parse(html).text();
                } else {

                }
            }





        }catch (Exception e){
            System.out.println("Email beak up failed");
        }

    }


    private ArrayList<String> getEmailContents() {
        return emailContents;
    }

    public String emailText(){
        StringBuilder result=new StringBuilder("");
        for(int i=0;i<getEmailContents().size()-1;i++){
            result.append(getEmailContents().get(i));
            result.append("\n");

        }
        return result.toString();
    }


}
