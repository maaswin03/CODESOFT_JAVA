import java.util.Scanner;

public class Main {
    public String accname[] = {"VIRAT KOHLI", "DHONI", "KL RAHUL"};
    public int accno[] = {9876543, 45839801, 57943290};
    public int accpin[] = {2005, 2004, 1234};
    public int balance[] = {2023, 1000, 50000};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Main mainObj = new Main();
        int accountIndex = -1;

        while (true) {
            if (accountIndex == -1) {
                System.out.print("ENTER ACCOUNT NO:");
                int accountNumber = scanner.nextInt();

                System.out.print("ENTER PIN NO:");
                int pin = scanner.nextInt();

                accountIndex = mainObj.authenticate(accountNumber, pin);

                if (accountIndex == -1) {
                    System.out.println("INVALID ACCOUNT NUMBER OR PASSWORD");
                    continue;
                }
            }

            System.out.println("SELECT ONE OPTION BELOW");
            System.out.println("1. WITHDRAW");
            System.out.println("2. DEPOSIT");
            System.out.println("3. CHECK BALANCE");
            System.out.println("4. EXIT");
            System.out.print("ENTER OPTION: ");
            int option = scanner.nextInt();

            if (option == 4) {
                System.out.println("Exiting... Thank you!");
                break;
            }

            mainObj.atm(accountIndex, option);
        }
    }

    public int authenticate(int accountNumber, int pin) {
        for (int i = 0; i < accno.length; i++) {
            if (accountNumber == accno[i] && pin == accpin[i]) {
                return i;
            }
        }
        return -1;
    }

    public void atm(int i, int option) {
        switch (option) {
            case 1:
                withdraw(i);
                break;
            case 2:
                deposit(i);
                break;
            case 3:
                checkBalance(i);
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void checkBalance(int i) {
        System.out.println("AVAILABLE BALANCE IS: " + balance[i]);
    }

    private void deposit(int i) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ENTER AMOUNT TO DEPOSIT: ");
        int amount = scanner.nextInt();
        balance[i] += amount;
        System.out.println("DEPOSIT SUCCESSFUL. NEW BALANCE: " + balance[i]);
    }

    private void withdraw(int i) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ENTER AMOUNT TO WITHDRAW: ");
        int amount = scanner.nextInt();
        if (amount <= balance[i]) {
            balance[i] -= amount;
            System.out.println("WITHDRAWAL SUCCESSFUL. NEW BALANCE: " + balance[i]);
        } else {
            System.out.println("INSUFFICIENT FUNDS");
        }
    }
}

