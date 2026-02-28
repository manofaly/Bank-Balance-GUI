/* This is a java application that uses the previous BankAccount class together with
   a simple GUI that enables making depoits, withdrawals, and displaying current balance. 
   If user clicks the Display Balance button, the current balance is displayed.
   If user clicks the Deposit button, the amount in the amountField will be deposited
   If user clicks the Withdraw button, the amount in the amountField will be withdrawn
   If the user clicks the Exit button, current balance is displayed and application is closed */

   
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public static class GUIBankBalance {
    private String firstName;
    private String lastName;
    private int accountID;
    private double balance;

    // Parameterized constructor
    public GUIBankBalance() {
        this.balance = 0.0;
    }

    public void deposit(double depositAmount) {
        // A method to allow deposits to bank account. Accepts a double amount.

        if (depositAmount > 0) {
            this.balance += depositAmount;             // Add deposit amount to current balance
            System.out.println("An amount of $" + depositAmount + " has been deposited to your bank account successfully.");
        
        } else {                                
            System.out.println("Deposit amount must be greater than 0");

        }
        System.out.println("=======================================================");

    }

    public void withdrawal(double withdrawAmount) {
        // A method to allow withdrawals from ban account

        if (withdrawAmount <= 0 | withdrawAmount > this.balance) {                 
            System.out.println("Withdraw amount can not be less than 0 or greater than current balance.");

        } else {
            this.balance -= withdrawAmount;           // subtract withdraw amount from current balance
            System.out.println("An amount of $" + withdrawAmount + " has been withdrawn from your bank account successfully.");
            System.out.println("Remaining balance: $" + this.balance);
            
        }
        System.out.println("=======================================================");

    }

    // Setter method for firstName
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;

    }

    // Setter method for lasttName
    public void setLastName(String newLastName) {
        this.lastName = newLastName;

    }

    // Setter method for accountID
    public void setAccountID(int newAccountID) {
        this.accountID = newAccountID;

    }

    protected void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    // Getter method for firstName
    public String getFirstName() {
        return this.firstName;

    } 

    // Getter method for lastName
    public String getLastName() {
        return this.lastName;

    } 

    // Getter method for accountID
    public int getAccountID() {
        return this.accountID;

    } 

    // Getter method for balance
    public double getBalance() {
        return this.balance;
    
    }
}


public static void main(String[] args) {

    GUIBankBalance account = new GUIBankBalance();     //Creating a BankAccount object      
    account.setFirstName("Omer");         // Setting firstname
    account.setLastName("Manofaly");       // Setting lastname
    account.setAccountID(12345);          // Setting ID
    
    String fullName = account.firstName + " " + account.lastName;

    JFrame frame = new JFrame("Bank Account Application");         //Creating the GUI frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 // Setting frame to exit when close button is clicked
    frame.setSize(1000, 700);                               // Setting frame size

    JPanel panel = new JPanel();                                          // Creating a panel placed inside the frame
    panel.setLayout(null);                                           // Setting layout to nul to enable positioning components
    panel.setBackground(new Color(255, 222, 173));                 // Setting background color

    String title = "Welcome to the Bank Account GUI application";
    JLabel titleLabel = new JLabel(title);
    titleLabel.setBounds(300, 5, 380, 100);             // Setting the titleLabel position
    titleLabel.setFont(new Font("Times New Roman", Font.BOLD,18));

    String details = "Account holder's name: " + fullName + "                                   " + "Account ID: " + account.accountID;
    JLabel detailsLabel = new JLabel(details);
    detailsLabel.setBounds(200, 50, 700, 100);
    detailsLabel.setFont(new Font("Times New Roman", Font.BOLD,18));


    JLabel amountLabel = new JLabel("Enter amount: $");
    amountLabel.setBounds(200, 200, 250, 100);
    amountLabel.setFont(new Font("Times New Roman", Font.BOLD,24));

    JTextField amountField = new JTextField(30);          // Amount to be deposited or withdrawn
    amountField.setBounds(380, 225, 250, 50);

    // Creating a button that displays current balance
    JButton balanceButton = new JButton("Display Balance");
    balanceButton.setBounds(200, 400, 200, 75);
    balanceButton.addActionListener(e -> {
        double balance = account.getBalance();
        JOptionPane.showMessageDialog(null, "Current balance is: $" + balance);
    });

    // Creating a button that performs deposit of the given amount
    JButton depositButton = new JButton("Deposit");
    depositButton.setBounds(550, 400, 200, 75);
    depositButton.addActionListener(e -> {
       try {
            double amount = Double.parseDouble(amountField.getText().trim());      // Try converting given amount to a double
            if (amount <= 0) {                                                     
                JOptionPane.showMessageDialog(null, "Deposit amount must be greater than zero.");
            
            } else {
                account.deposit(amount);                // perform deposit using the deposit() method of BankAccount class
                JOptionPane.showMessageDialog(null, "An amount of $" + amount + " has been successfully deposited.");
            }
       } catch (NumberFormatException ex){             // If given amount is not  double
            JOptionPane.showMessageDialog(null, "Please enter a valid deposit amount.");
       }
    });

    //Creting a button to perform withdraw of the given amount
    JButton withdrawButton = new JButton("Withdraw");
    withdrawButton.setBounds(200, 500, 200, 75);
    withdrawButton.addActionListener(e -> {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());             // Try converting given amount to a double
            if (account.getBalance() == 0) {
                JOptionPane.showMessageDialog(null, "Account out of balance. Try making deposit first.");

            } else if (account.getBalance() < amount) {
                JOptionPane.showMessageDialog(null, "Withdrawal amount can not be greater than current balance.");

            } else if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Withdrawal amount must be greater than zero.");

            } else {
                account.withdrawal(amount);
                JOptionPane.showMessageDialog(null, "An amount of $" + amount + " has been successfully withdrawn.");
            }

        } catch (NumberFormatException ex) {        // If given amount is not valid
            JOptionPane.showMessageDialog(null, "Please enter a valid withdrawal amount.");

        }

    });

    // Creating a button to display balance then exit
    JButton exitButton = new JButton("Exit");
    exitButton.setBounds(550, 500, 200, 75);
    exitButton.addActionListener(e -> {
        double balance = account.getBalance();
        JOptionPane.showMessageDialog(null, "Current balance is: $" + balance + "\nApplication is exiting. Goodbye.");
        System.exit(0);               // exit application


    });

    // adding components to panel
    panel.add(titleLabel);
    panel.add(detailsLabel);
    panel.add(amountLabel);
    panel.add(amountField);
    panel.add(balanceButton);
    panel.add(depositButton);
    panel.add(withdrawButton);
    panel.add(exitButton);

    //adding panel to frame and setting frame to be visible
    frame.add(panel);
    frame.setVisible(true);

}
