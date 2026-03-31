package smartwallet.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import smartwallet.model.Account;

/**
 * @author 12S24047 Davina Olivia Hutabarat
 * @author 12S24047 Davina Olivia Hutabarat
 */

public class Driver1 {

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
                String name = tokens[1];
                String username = tokens[2];
                accounts.add(new Account(name, username));
            }
        }

        for (Account account : accounts) {
            System.out.println(account.getUsername() + "|" + account.getName() + "|" + account.getBalance());
        }
        
        scanner.close();
    }
}
