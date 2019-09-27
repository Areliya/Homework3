public class CompanyType {
    private int id;
    private String name_short;
    private String name_full;


    @Override
    public String toString() {
        return "CompanyType{" +
                "id=" + id +
                ", name_short='" + name_short + '\'' +
                ", name_full='" + name_full + '\'' +
                '}';
    }
}
