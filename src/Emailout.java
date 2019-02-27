

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Emailout {

    private Map<String,String> info;

    private DateTime EndDate;

    public Emailout(){
        info= new HashMap<String, String>();

    }

    public static void sendReminder(ArrayList<String> reminderInFo){

        Event event = new Event();


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
    // dd/mm/yyyy,HH:MM
    public String apiFormatedTime(String dateAndTime){
        StringBuilder formatedString= new StringBuilder("");


        String date[] = dateAndTime.split(",")[0].split("/");

        String time[] = dateAndTime.split(",")[1].split(":");


        for(int i =date.length-1;i>=0;i--){
            System.out.println(date[i]+" "+i);
            formatedString.append(date[i]);

        }

        for(int i =time.length-1;i>=0;i--){
            System.out.println(time[i]+" "+i);
            formatedString.append(time[i]);
            formatedString.append(":");
        }
        formatedString.append("00-07:00");

        return formatedString.toString();







    }

    public Map<String, String> getInfo() {
        return info;
    }
}
