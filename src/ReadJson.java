import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadJson {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите путь к файлу с данными:");
            String fileName = bufferedReader.readLine();//"C:\\Users\\Tamara\\Downloads\\Education\\test.json";
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            //Чтение из файла
            Organisation[] organisations = gson.fromJson(reader,Organisation[].class);
            System.out.println("Список организаций:");
            for (int i = 0; i < organisations.length; i++) {
                organisations[i].orgInfo();
            }
            System.out.println("Конец списка организаций.");
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
            System.out.println("Список ценных бумаг:");
            for (int i = 0; i < organisations.length; i++) {
                organisations[i].useCurrency(chosenCurrency.toLowerCase());
            }
            System.out.println("Конец списка ценных бумаг.");
            bufferedReader.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    public static Date dataTransformer(String dateInString){
        Date date = null;
        try {
            if (dateInString.length() == 8) {
                if (dateInString.contains("/")) {
                    return date = new SimpleDateFormat("dd/MM/yy").parse(dateInString);
                } else if (dateInString.contains(".")) {
                    return date = new SimpleDateFormat("dd.MM.yy").parse(dateInString);
                } else {
                    System.out.println("Невозможно преоброзовать дату!");
                    return date;
                }
            } else if (dateInString.length() == 10) {
                if (dateInString.contains("/")) {
                    return date = new SimpleDateFormat("dd/MM/y").parse(dateInString);
                } else if (dateInString.contains(".")) {
                    return date = new SimpleDateFormat("dd.MM.y").parse(dateInString);
                } else {
                    System.out.println("Невозможно преоброзовать дату!");
                    return date;
                }
            } else {
                System.out.println("Невозможно преоброзовать дату!");
                return date;
            }
        }catch (ParseException e){
            System.out.println("Невозможно преоброзовать дату!");
            return date;
        }
    }

}
