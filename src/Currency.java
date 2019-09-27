public class Currency {
    private int id;
    private String code;
    private String name_short;
    private String name_full;

    public String getCode() {
        return code.toLowerCase();
    }
}
