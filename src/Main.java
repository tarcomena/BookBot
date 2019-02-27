public class Main {

    public static void main(String[] args) {

        String emailuserName="tarconator@gmail.com";
        String password="Halo12344";

        EmailConnection test = new EmailConnection(emailuserName,password);
        //hardcoded test not for final use

        for(int i=0; i<test.getBookAndInfo().size();i++){


            for(int j=0;j<test.getBookAndInfo().get(i).size();j++){
                System.out.println(test.getBookAndInfo().get(i).get(j));

            }
        }

    }
}
