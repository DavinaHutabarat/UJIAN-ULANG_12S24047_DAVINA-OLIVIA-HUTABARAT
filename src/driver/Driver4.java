package smartwallet.driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import smartwallet.model.*;

/**
 * @author 12S24047 Davina Olivia
 */
public class Driver4 {

    public static void main(String[] _args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        List<String> transactionsOutput = new ArrayList<>();
        int transactionId = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("---")) {
                break;
            }

            String[] tokens = line.split("#");
            String command = tokens[0];

            try {
                if (command.equals("create-account")) {
                    accounts.add(new Account(tokens[1], tokens[2]));
                } else if (command.equals("deposit")) {
                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(tokens[1])) {
                            double amount = Double.parseDouble(tokens[2]);
                            acc.deposit(amount);
                            transactionId++;
                            transactionsOutput.add(tokens[1] + "|" + transactionId + "|deposit|" + amount + "|" + tokens[3] + "|" + tokens[4]);
                        }
                    }
                } else if (command.equals("withdraw")) {
                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(tokens[1])) {
                            double amount = Double.parseDouble(tokens[2]);
                            if (!acc.withdraw(amount)) {
                                throw new NegativeBalanceException("Insufficient balance");
                            }
                            transactionId++;
                            transactionsOutput.add(tokens[1] + "|" + transactionId + "|withdraw|-" + amount + "|" + tokens[3] + "|" + tokens[4]);
                        }
                    }
                } else if (command.equals("show-account")) {
                    for (Account acc : accounts) {
                        if (acc.getUsername().equals(tokens[1])) {
                            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + String.format("%.1f", acc.getBalance()));
                            for (String t : transactionsOutput) {
                                if (t.startsWith(tokens[1] + "|")) {
                                    String[] tParts = t.split("\\|");
                                    System.out.println(tParts[1] + "|" + tParts[2] + "|" + tParts[3] + "|" + tParts[4] + "|" + tParts[5]);
                                }
                            }
                        }
                    }
                }
            } catch (NegativeBalanceException e) {
                // Sesuai instruksi: Program tidak boleh berhenti
            }
        }
        scanner.close();
    }
}