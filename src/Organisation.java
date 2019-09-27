//import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
public class Organisation {
    private int id;
    private String code;
    private String name_full;
    private String name_short;
    private CompanyType company_type;
    private String inn;
    private String ogrn;
    private String egrul_date;
    private Country country;
    private String fio_head;
    private String address;
    private String phone;
    private String e_mail;
    private String www;
    private boolean isResident;
    private Shares[] securities;


    public Organisation() {}

    public void orgInfo (){
        try {
            Date date = new SimpleDateFormat("y-MM-dd").parse(this.egrul_date);
            System.out.println(this.name_short + " Дата основания " + new SimpleDateFormat("dd/MM/yy").format(date));
        }
        catch (ParseException e){

        }
    }

    public int oldShares (){
        int counter = 0;
        for (int i = 0; i < securities.length; i++) {
            Date today = new Date();
                try {
                    Date date = new SimpleDateFormat("y-MM-dd").parse(securities[i].getDate_to());
                    if (date.getTime() < today.getTime()){
                        System.out.println(securities[i].getCode() + " " +securities[i].getDate_to()+ " " + name_full);
                        counter++;
                    }
                }
                catch (ParseException e){

                }

        }
        return counter;
    }

    public void isOlder (Date testDate){
        try {
            Date date = new SimpleDateFormat("y-MM-dd").parse(egrul_date);
            if (date.getTime()>testDate.getTime()){
                this.orgInfo();
            }
        }
        catch (ParseException e){

        }
    }

    public void useCurrency(String chosenCurrency){
        for (int i = 0; i < securities.length; i++) {
            if (chosenCurrency.equals(securities[i].getCurrency().getCode())){
                System.out.println(name_short + " " + securities[i].getId() + " " + securities[i].getCode());
            }
        }
    }

}
