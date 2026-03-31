package smartwallet.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import smartwallet.model.Account;

/**
 * @author 12S24047 Davina Olivia Hutabarat
 * @author 12S24047 Davina Olivia Hutabarat
 */

public class Driver2 {

    public static void main(String[] _args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("---")) {
                break;
            }

            String[] tokens = line.split("#");
            if (tokens[0].equals("create-account")) {
                accounts.add(new Account(tokens[1], tokens[2]));
            } else if (tokens[0].equals("deposit")) {
                String username = tokens[1];
                double amount = Double.parseDouble(tokens[2]);
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(username)) {
                        acc.deposit(amount);
                    }
                }
            } else if (tokens[0].equals("withdraw")) {
                String username = tokens[1];
                double amount = Double.parseDouble(tokens[2]);
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(username)) {
                        acc.withdraw(amount);
                    }
                }
            }
        }

        for (Account account : accounts) {
            System.out.println(account.getUsername() + "|" + account.getName() + "|" + account.getBalance());
        }
        
        scanner.close();
    }
}