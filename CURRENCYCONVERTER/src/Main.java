import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the base currency code (e.g., USD): ");
        String baseCurrency = scanner.nextLine();

        System.out.print("Enter the target currency code (e.g., EUR): ");
        String targetCurrency = scanner.nextLine();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        
        String appId = "d8c30e750365d9a20225980fb287c9f9";

        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency + "?app_id=" + appId;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner responseScanner = new Scanner(connection.getInputStream());

            StringBuilder response = new StringBuilder();
            while (responseScanner.hasNextLine()) {
                response.append(responseScanner.nextLine());
            }
            responseScanner.close();

            String responseBody = response.toString();

            
            if (responseBody.contains("\"error\"")) {
                System.out.println("Error in API response: " + responseBody);
                return;
            }

        
            int startIndex = responseBody.indexOf("\"" + targetCurrency + "\":") + targetCurrency.length() + 4;
            int endIndex = responseBody.indexOf(",", startIndex);
            String rateSubstring = responseBody.substring(startIndex, endIndex);

            double exchangeRate = Double.parseDouble(rateSubstring);

            double convertedAmount = amount * exchangeRate;

            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


