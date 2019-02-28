public class Main {

    public static void main(String[] args) {

        String emailuserName="tarconator@gmail.com";
        String password="Halo12344";

        EmailConnection test = new EmailConnection(emailuserName,password);
        //hardcoded test not for final use

        Emailout eout = new Emailout();


        for(int i=0; i<test.getBookAndInfo().size();i++){


            eout.refineInfo(test.getBookAndInfo().get(i));
            eout.sendReminder();
           // System.out.println(eout.apiFormatedTime(eout.getInfo().get("Due Date")));
        }

    }
}
