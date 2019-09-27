import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ReadJson {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите путь к файлу с данными:");
            String fileName = bufferedReader.readLine();//"C:\\Users\\Tamara\\Downloads\\Education\\test2.json";
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            //Чтение из файла
            Organisation[] organisations = gson.fromJson(reader,Organisation[].class);
            for (int i = 0; i < organisations.length; i++) {
                organisations[i].setDate();
            }
            System.out.println("Список компаний:");
            Arrays.stream(organisations).forEach(organisation ->
                    System.out.println(organisation.getName_short()
                            + " Дата основания "
                            + new SimpleDateFormat("dd/MM/yy").format(organisation.getDate())));
            int counter = 0;
            System.out.println("Просроченные бумаги");
            for (int i = 0; i < organisations.length; i++) {
                counter += organisations[i].oldShares();
            }
            System.out.println("Всего просроченных бумаг: " + counter);
            System.out.println("Введите дату («ДД.ММ.ГГГГ», «ДД.ММ,ГГ», «ДД/ММ/ГГГГ» и «ДД/ММ/ГГ»), чтобы найти все организации основанные после.");
            Date testDate = dataTransformer(bufferedReader.readLine());
            if (testDate!=null){
                System.out.println("Список организаций, удовлетворяющих условию:");
                for (int i = 0; i < organisations.length; i++) {
                    organisations[i].isOlder(testDate);
                }
                System.out.println("Конец списка организаций.");
            }
            else {
                System.out.println("Неверный ввод!");
            }
            System.out.println("Введите код валюты для поиска ценных бумаг.");
            String chosenCurrency = bufferedReader.readLine();
            Boolean isUse = false;
            for (int i = 0; i < organisations.length; i++) {
                if (organisations[i].useCurrency(chosenCurrency.toLowerCase())){
                    isUse = true;
                }
            }
            if (!isUse){
                System.out.println("Нет ценных бумаг в такой валюте!");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    private static Date dataTransformer(String dateInString){
        Date date = null;
        try {
            boolean isNormal = true;
            //Проверка корректности ввода даты
            if (dateInString.length()>5) {
                int day = Integer.parseInt(dateInString.substring(0, 2));
                int month = Integer.parseInt(dateInString.substring(3, 5));
                int year = Integer.parseInt(dateInString.substring(6));
                switch (month){
                    case (1):
                    case (3):
                    case (5):
                    case (7):
                    case (8):
                    case (10):
                    case (12):
                        if ((day < 1)||(day > 31)) {
                            System.out.println("N");
                            isNormal = false;
                        }
                        break;
                    case (4):
                    case (6):
                    case (9):
                    case (11):
                        if ((day < 1) || ( day > 30)) {
                            isNormal = false;
                            System.out.println("C");
                        }
                        break;
                    case (2):
                        if ((year % 4) == 0){
                            if ((day < 1) || ( day > 29)) {
                                isNormal = false;
                                System.out.println("F1");
                            }
                            break;
                        }
                        else {
                            if ((day < 1) || ( day > 28)) {
                                isNormal = false;
                                System.out.println("F2");

                            }
                            break;
                        }
                        default:
                            isNormal = false;
                            System.out.println("yghjgu");
                            break;
                }
            }
            //Проверка корректности формата
            if ((dateInString.length() == 8) && isNormal) {
                if (dateInString.contains("/")) {
                    date = new SimpleDateFormat("dd/MM/yy").parse(dateInString);
                } else if (dateInString.contains(".")) {
                    date = new SimpleDateFormat("dd.MM.yy").parse(dateInString);
                } else {
                    System.out.println("Невозможно преоброзовать дату!");
                }
            } else if ((dateInString.length() == 10) && isNormal) {
                if (dateInString.contains("/")) {
                    date = new SimpleDateFormat("dd/MM/y").parse(dateInString);
                } else if (dateInString.contains(".")) {
                    date = new SimpleDateFormat("dd.MM.y").parse(dateInString);
                } else {
                    System.out.println("Невозможно преоброзовать дату!");
                }
            } else {
                System.out.println("Невозможно преоброзовать дату!");
            }
        }catch (ParseException e){
            System.out.println(e.getStackTrace());
        }
        return date;
    }

}
