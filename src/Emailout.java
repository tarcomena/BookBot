

import com.google.api.client.util.DateTime;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Emailout {

    private Map<String,String> info;

    private DateTime EndDate;

    public Emailout(){
        info= new HashMap<String, String>();

    }

    public void sendReminder(){

        // Recipient's email ID needs to be mentioned.
        String to = "tarconator@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "tarconator@gmail.com";
        final String username = "tarconator@gmail.com";//change accordingly
        final String password = "Halo12344";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject
            message.setSubject("Remember to Return book");

            // Now set the actual message
            message.setText("Return "+info.get("Title")+"\n Due by:"+info.get("Due Date"));

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (Exception e) {
            System.out.println("");
        }

    }



    public void refineInfo(ArrayList<String> rawData){

        for (int i=0;i<rawData.size();i++){
            String temp =rawData.get(i);

            if(temp.contains("Title:")){
                //System.out.println(temp);

                temp=temp.substring(7);
                info.put("Title",temp);

                //System.out.println(info.get("Title"));
            }
            else if(temp.contains("Barcode:")){
                //System.out.println(temp);

                temp=temp.substring(9);
                info.put("Barcode",temp);

                //System.out.println(info.get("Barcode"));
            }
            else if(temp.contains("Due Date")){
                //System.out.println(temp);

                temp=temp.substring(10);
                info.put("Due Date",temp);

               // System.out.println(info.get("Due Date"));
            }

        }


    }

    // mm/dd/yyyy,HH:MM to YYYY:MM:DDTHH:MM:SS-06:00
    public String apiFormatedTime(String dateAndTime){
        StringBuilder formatedString= new StringBuilder("");


        String date[] = dateAndTime.split(",")[0].split("/");

        String time[] = dateAndTime.split(",")[1].split(":");


        formatedString.append(date[2]);
        formatedString.append(":");
        formatedString.append(("00"+date[0]).substring(date[0].length()));
        formatedString.append(":");
        formatedString.append(("00"+date[1]).substring(date[1].length()));

        formatedString.append("T");

        formatedString.append(("00"+time[0]).substring(time[0].length()));
        formatedString.append(":");

        String temp=("00"+time[1]).substring(time[1].length()-1,time[1].length()+1);
        formatedString.append(temp);




        //central time
        formatedString.append(":00-06:00");

        return formatedString.toString();







    }

    public Map<String, String> getInfo() {
        return info;
    }
}
