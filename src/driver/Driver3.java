package fintech.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import fintech.model.Account;

/**
 * @author 12S24047 Davina Olivia
 */
public class Driver3 {

    public static void main(String[] _args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("---")) {
                break;
            }

            String[] tokens = line.split("#");
            String command = tokens[0];

            if (command.equals("create-account")) {
                accounts.add(new Account(tokens[1], tokens[2]));
            } else if (command.equals("deposit")) {
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(tokens[1])) {
                        acc.deposit(Double.parseDouble(tokens[2]));
                    }
                }
            } else if (command.equals("transfer")) {
                Account sender = null, receiver = null;
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(tokens[1])) sender = acc;
                    if (acc.getUsername().equals(tokens[2])) receiver = acc;
                }
                if (sender != null && receiver != null) {
                    double amount = Double.parseDouble(tokens[3]);
                    if (sender.withdraw(amount)) {
                        receiver.deposit(amount);
                    }
                }
            }
        }

        for (Account acc : accounts) {
            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + String.format("%.1f", acc.getBalance()));
        }
        
        scanner.close();
    }
}