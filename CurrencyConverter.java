import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=============================================");
        System.out.println("    💱 REAL-TIME CURRENCY CONVERTER 💱    ");
        System.out.println("=============================================");
        System.out.println("Examples: USD, INR, EUR, GBP, AUD, CAD");
        
        // 1. User se input lena
        System.out.print("\nEnter Source Currency (e.g. USD): ");
        String fromCode = sc.next().toUpperCase();
        
        System.out.print("Enter Target Currency (e.g. INR): ");
        String toCode = sc.next().toUpperCase();
        
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        // 2. API Call Logic
        try {
            
            String apiKey = "a3b62071312a9669a4896517"; 
            String urlString = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + fromCode + "/" + toCode;
            
            URL url = new URL(urlString);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // 3. Response Check
            int responseCode = request.getResponseCode();

            if (responseCode == 200) { // 200 Matlab "OK"
                // Data Read karo
                BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 4. JSON se Rate nikalna 
                String json = response.toString();
                
                // API response me "conversion_rate": 84.23 aisa likha hota hai
                int index = json.indexOf("\"conversion_rate\":");
                
                if(index != -1) {
                    // Value extract karna
                    String temp = json.substring(index + 18); // "conversion_rate": ke baad ka hissa
                    temp = temp.substring(0, temp.indexOf("}")); // Agle bracket tak
                    temp = temp.trim(); // Extra space safai
                    
                    double exchangeRate = Double.parseDouble(temp);
                    double convertedAmount = amount * exchangeRate;

                    // Result Display
                    System.out.println("\n---------------------------------------------");
                    System.out.println("✅ Conversion Successful!");
                    System.out.printf("   Current Rate: 1 %s = %.2f %s\n", fromCode, exchangeRate, toCode);
                    System.out.printf("   💰 Total: %.2f %s = %.2f %s\n", amount, fromCode, convertedAmount, toCode);
                    System.out.println("---------------------------------------------");
                } else {
                    System.out.println("\n❌ Error: Currency code galat hai ya Rate nahi mila.");
                }

            } else {
                System.out.println("\n❌ Error: API connection failed. (Code: " + responseCode + ")");
            }

        } catch (Exception e) {
            System.out.println("\n❌ Internet Error: Please check your connection.");
        }
        
        sc.close();
    }
}