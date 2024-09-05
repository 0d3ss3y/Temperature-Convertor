package temp;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class convertor {
    private static ArrayList<String> validOptions = new ArrayList<>(Arrays.asList("celsius","kevin","fahrenheit"));
    private static Scanner scanner;

    public static void main(String[] args) throws Exception{
        String from = getUnitFrom();
        String To = getUnitTo();
        double value = getUserValue();

        HttpClient client = HttpClient.newHttpClient();


    }

    private static double getUserValue() {
        return 0;
    }

    private static String getUnitTo() {
        int count = 1;
        for (String opt : validOptions){
            System.out.println(count+". "+opt);
            count++;
        }

        try {
            scanner = new Scanner(System.in);
            System.out.print("Select an option: ");
            String option = scanner.nextLine();

            boolean check = isVaildOption(option.toLowerCase());

            if (check){
                return option.toLowerCase();
            }else {
                throw new Exception();
            }

        }catch (Exception e){
            System.out.println("Invalid option");
        }
        return null;
    }

    private static boolean isVaildOption(String option) {
        return validOptions.contains(option);
    }

    private static String getUnitFrom() {
        return "";
    }
}
