package temp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class convertor {
    private static ArrayList<String> validOptions = new ArrayList<>(Arrays.asList("celsius","kevin","fahrenheit"));
    private static Scanner scanner;

    public static void main(String[] args) throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        String from = getUnitFrom();
        String to = getUnitTo(from);
        double value = getUserValue();
        String uri = String.format("http://127.0.0.1:5000/convert?unit_from=%s&unit_to=%s&value=%s", from, to, value);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            double outcome = jsonResponse.get("outcome").getAsDouble();

            System.out.println("Conversion result: " + outcome);
        } else {
            System.out.println("Error: " + response.statusCode());
        }

    }

    private static double getUserValue() {
        double value = 0;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter a value: ");
            value = scanner.nextDouble();
        }catch (InputMismatchException e){
            System.out.println("Invalid entry");
        }
        return value;
    }

    private static String getUnitTo(String from) {
        try {
            scanner = new Scanner(System.in);
            System.out.print("Select an option to convert to: ");
            String option = scanner.nextLine();

            boolean check = isVaildOption(option.toLowerCase(),from);

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

    private static boolean isVaildOption(String option, String from) {
        return validOptions.contains(option) && !option.equals(from);
    }

    private static String getUnitFrom() {
        int count = 1;
        System.out.println("Available Options:");
        for (String opt : validOptions){
            System.out.println(count+". "+opt);
            count++;
        }

        try {
            scanner = new Scanner(System.in);
            System.out.print("\nSelect an option to convert From: ");
            String option = scanner.nextLine();

            boolean check = isVaildOption(option.toLowerCase(), "");

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
}
