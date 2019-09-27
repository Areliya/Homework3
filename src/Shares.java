import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Shares {
    private int id;
    private String code;
    private String name_full;
    private String cfi;
    private String date_to;
    private String state_reg_date;
    private StatesOfShares state;
    private Currency currency;
    private Date date;

    public String getDate_to() {
        return date_to;
    }

    public String getCode() {
        return code;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getId() {
        return id;
    }

    public void setDates() {
        try {
            this.date = new SimpleDateFormat("y-MM-dd").parse(date_to);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        return date;
    }
}
