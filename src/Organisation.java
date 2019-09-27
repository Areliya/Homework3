import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Date date;


    public Organisation() {}

    public String getName_short() {
        return name_short;
    }

    public Date getDate() {
        return date;
    }

    public Shares[] getSecurities() {
        return securities;
    }

    public int oldShares (){
        int counter = 0;
        long today = new Date().getTime();
        for (int i = 0; i < securities.length; i++) {
            securities[i].setDates();
            if (securities[i].getDate().getTime() < today){
                System.out.println(securities[i].getCode() + " " + securities[i].getDate_to() + " " + name_full);
                counter++;
            }
        }
        return counter;
    }

    public void isOlder (Date testDate){
            if (date.getTime() > testDate.getTime()){
                System.out.println(this.name_short + " Дата основания " + new SimpleDateFormat("dd/MM/yy").format(date));
            }

    }

    public Boolean useCurrency(String chosenCurrency){
        Boolean isItSo = false;
        for (int i = 0; i < securities.length; i++) {
            if (chosenCurrency.equals(securities[i].getCurrency().getCode())){
                System.out.println(name_short + " " + securities[i].getId() + " " + securities[i].getCode());
                isItSo = true;
            }
        }
        return isItSo;
    }

    public void setDate() {
        try {
            this.date = new SimpleDateFormat("y-MM-dd").parse(egrul_date);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
    }
}
