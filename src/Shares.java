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
}
