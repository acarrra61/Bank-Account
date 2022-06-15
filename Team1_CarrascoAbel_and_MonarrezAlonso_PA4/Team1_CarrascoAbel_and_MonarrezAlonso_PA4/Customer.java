import java.io.*;
import java.util.*;
/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the concrete class Customer is to implement the attributes and
 * methods that are common to every customer involved in a banking system, including those that
 * are common to every individual person.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public class Customer extends Person implements Printable{
    private int idNumber;
    private Checking checkingAccount;
    private Savings savingsAccount;
    private Credit creditAccount;
    private String password;
    private ArrayList<String> transactionsList;

    /**
     * The default constructor of an Customer. Taken from Abel Carrasco.
     */
    public Customer(){
        super();
    }

    /**
     * A constructor of a Customer that includes the attributes common to every customer,
     * including those common to every person. Taken from Abel Carrasco.
     * @param firstNameIn A customer's first name.
     * @param lastNameIn A customer's last name.
     * @param dateOfBirthIn A customer's date of birth.
     * @param addressIn A customer's home address.
     * @param phoneNumberIn A customer's phone number.
     * @param emailIn A customer's email address.
     * @param idNumberIn A customer's identification number.
     * @param checkingAccountIn A customer's checking account.
     * @param savingsAccountIn A customer's savings account.
     * @param creditAccountIn A customer's credit account.
     * @param passwordIn A customer's account password.
     * @param transactionsListIn A customer's list of transactions.
     */
    public Customer(String firstNameIn, String lastNameIn, String dateOfBirthIn, String addressIn, String phoneNumberIn,
                    String emailIn, int idNumberIn, Checking checkingAccountIn, Savings savingsAccountIn,
                    Credit creditAccountIn, String passwordIn, ArrayList<String> transactionsListIn){
        super(firstNameIn, lastNameIn, dateOfBirthIn, addressIn, phoneNumberIn, emailIn);
        this.idNumber = idNumberIn;
        this.checkingAccount = checkingAccountIn;
        this.savingsAccount = savingsAccountIn;
        this.creditAccount = creditAccountIn;
        this.password = passwordIn;
        this.transactionsList = transactionsListIn;
    }

    /**
     * A setter method that sets a customer's identification number to the given parameter.
     * Taken from Abel Carrasco.
     * @param idNumberIn A customer's identification number.
     */
    public void setIdNumber(int idNumberIn) {
        this.idNumber = idNumberIn;
    }

    /**
     * A setter method that sets a customer's checking account to the given parameter.
     * Taken from Abel Carrasco.
     * @param checkingAccountIn A customer's checking account.
     */
    public void setCheckingAccount(Checking checkingAccountIn) {
        this.checkingAccount = checkingAccountIn;
    }

    /**
     * A setter method that sets a customer's savings account to the given parameter.
     * Taken from Abel Carrasco.
     * @param savingsAccountIn A customer's savings account.
     */
    public void setSavingsAccount(Savings savingsAccountIn) {
        this.savingsAccount = savingsAccountIn;
    }

    /**
     * A setter method that sets a customer's credit account to the given parameter.
     * Taken from Abel Carrasco.
     * @param creditAccountIn A customer's credit account.
     */
    public void setCreditAccount(Credit creditAccountIn) {
        this.creditAccount = creditAccountIn;
    }

    /**
     * A setter method that sets a customer's password to the given parameter.
     * Taken from Abel Carrasco.
     * @param passwordIn A customer's account password.
     */
    public void setPassword(String passwordIn) {
        this.password = passwordIn;
    }

    /**
     * A setter method that sets a customer's transactions list to the given parameter.
     * Taken from Abel Carrasco.
     * @param transactionsListIn A customer's list of transactions.
     */
    public void setTransactionsList(ArrayList<String> transactionsListIn) {
        this.transactionsList = transactionsListIn;
    }

    /**
     * A getter method that returns a customer's identification number. Taken from Abel Carrasco.
     * @return idNumber Returns the identification number of a customer.
     */
    public int getIdNumber() {
        return this.idNumber;
    }

    /**
     * A getter method that returns a customer's checking account. Taken from Abel Carrasco.
     * @return checkingAccount Returns the checking account of a customer.
     */
    public Checking getCheckingAccount() {
        return this.checkingAccount;
    }

    /**
     * A getter method that returns a customer's savings account. Taken from Abel Carrasco.
     * @return savingsAccount Returns the savings account of a customer.
     */
    public Savings getSavingsAccount() {
        return this.savingsAccount;
    }

    /**
     * A getter method that returns a customer's credit account. Taken from Abel Carrasco.
     * @return creditAccount Returns the credit account of a customer.
     */
    public Credit getCreditAccount() {
        return this.creditAccount;
    }

    /**
     * A getter method that returns a customer's password. Taken from Abel Carrasco.
     * @return password Returns the account password of a customer.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * A getter method that returns a customer's transactions list. Taken from Abel Carrasco.
     * @return transactionsList Returns the list of transactions of a customer.
     */
    public ArrayList<String> getTransactionsList() {
        return this.transactionsList;
    }

    /**
     * A method that returns a customer's account based on the type of account passed
     * to the method. Taken from Alonso Monarrez.
     * @param accountType The type of the customer's chosen account.
     * @return customerAcct Returns the customer's corresponding account.
     */
    public Account getCustomerAccount(String accountType){
        Account customerAcct = null;
        if(accountType.equals("Checking")){
            // Determine whether the customer has a checking account.
            if(this.getCheckingAccount() != null){
                customerAcct = this.getCheckingAccount();
            }
        }else if(accountType.equals("Savings")){
            // Assume that all customers have at least a savings account.
            customerAcct = this.getSavingsAccount();
        }else if(accountType.equals("Credit")){
            // Determine whether the customer has a credit account.
            if(this.getCreditAccount() != null){
                customerAcct = this.getCreditAccount();
            }
        }
        return customerAcct;
    }

    /**
     * A method that returns all of the available account information for a customer that is
     * to be written to the new balance sheet. Taken from Alonso Monarrez.
     * @return updatedCustomer Returns the customer's information to be written to the balance sheet.
     */
    public String displayUpdates(){
        String updatedCustomer = String.format("%-14s", this.firstName) + String.format("%-14s", this.lastName) +
                                 String.format("%-25s", this.dateOfBirth) + String.format("%-25s", this.idNumber) +
                                 String.format("%-44s", this.address) + String.format("%-18s", this.phoneNumber) +
                                 String.format("%-37s", this.email) + String.format("%-32s", this.password);
        if(this.checkingAccount != null){
            String checkingAcctBalance = String.format("%.2f", this.checkingAccount.getAccountBalance());
            updatedCustomer += String.format("%-23s", this.checkingAccount.getAccountNumber()) +
                               String.format("%-24s", "$" + checkingAcctBalance);
        }else{
            updatedCustomer += String.format("%-23s", "N/A") + String.format("%-24s", "N/A");
        }
        String savingsAcctBalance = String.format("%.2f", this.savingsAccount.getAccountBalance());
        updatedCustomer += String.format("%-22s", this.savingsAccount.getAccountNumber()) +
                           String.format("%-23s", "$" + savingsAcctBalance);
        if(this.creditAccount != null){
            String creditAcctBalance = String.format("%.2f", this.creditAccount.getAccountBalance());
            updatedCustomer += String.format("%-21s", this.creditAccount.getAccountNumber()) +
                               String.format("%-22s", "$" + creditAcctBalance) +
                               String.format("%-14s", "$" + this.creditAccount.getCreditMax());
        }else{
            updatedCustomer += String.format("%-21s", "N/A") + String.format("%-22s", "N/A") +
                               String.format("%-14s", "N/A");
        }
        return updatedCustomer;
    }

    /**
     * A method that returns a customer's account information to be printed to the console.
     * Taken from Abel Carrasco.
     * @return customerInfo Returns the customer's account information to be printed to the console.
     */
    @Override
    public String print(){
        String customerInfo = "First name: " + this.firstName + "\n" + "Last name: " + this.lastName + "\n" +
                              "Date of birth: " + this.dateOfBirth + "\n" + "Identification number: " + this.idNumber +
                              "\n" + "Address: " + this.address + "\n" + "Phone number: " + this.phoneNumber + "\n" +
                              "Email: " + this.email + "\n" + "Password: " + this.password + "\n";
        if(this.checkingAccount != null){
            String checkingAcctBalance = String.format("%.2f", this.checkingAccount.getAccountBalance());
            customerInfo += "Checking account number: " + this.checkingAccount.getAccountNumber() + "\n" +
                            "Checking account balance: $" + checkingAcctBalance + "\n";
        }
        String savingsAcctBalance = String.format("%.2f", this.savingsAccount.getAccountBalance());
        customerInfo += "Savings account number: " + this.savingsAccount.getAccountNumber() + "\n" +
                        "Savings account balance: $" + savingsAcctBalance + "\n";
        if(this.creditAccount != null){
            String creditAcctBalance = String.format("%.2f", this.creditAccount.getAccountBalance());
            customerInfo += "Credit account number: " + this.creditAccount.getAccountNumber() + "\n" +
                            "Credit account balance: $" + creditAcctBalance + "\n" +
                            "Credit max: $" + this.creditAccount.getCreditMax() + "\n";
        }
        return customerInfo;
    }

    /**
     * A method that returns a customer's formatted transaction to be printed to the transactions log.
     * Taken from Abel Carrasco.
     * @param transaction The transaction to be formatted and printed.
     * @return Returns the formatted transaction to be printed to the transactions log file.
     */
    @Override
    public String printTransaction(String transaction){
        // Create a formatted transaction that contains the customer's name and
        // the different aspects of his or her completed transaction.
        String customerFullName = String.format("%-25s", this.firstName + " " + this.lastName);
        StringBuilder loggedTransaction = new StringBuilder(customerFullName);
        String[] transactionInfo = transaction.split("\\t");
        for(String transactionAspect : transactionInfo){
            String formattedTransactionAspect = String.format("%-18s", transactionAspect);
            loggedTransaction.append(formattedTransactionAspect);
        }
        return loggedTransaction.toString();
    }

    /**
     * A method that creates a bank statement for each type of account that a customer has.
     * Taken from Abel Carrasco.
     * @param outputFile The file in which the bank statement is printed.
     * @throws IOException if the bank statement file cannot be found or accessed.
     * @throws ArrayIndexOutOfBoundsException if the index of an array cannot be accessed.
     */
    @Override
    public void printBankStatement(File outputFile) throws IOException, ArrayIndexOutOfBoundsException{
        // Create the bank statement for each of the three types of accounts.
        BankStatement checkingBankStmt = new BankStatement();
        BankStatement savingsBankStmt = new BankStatement();
        BankStatement creditBankStmt = new BankStatement();

        // Process the customer's personal information for each of the three bank statements.
        Customer customerInfo = new Customer();
        customerInfo.setFirstName(this.firstName);
        customerInfo.setLastName(this.lastName);
        customerInfo.setDateOfBirth(this.dateOfBirth);
        customerInfo.setIdNumber(this.idNumber);
        customerInfo.setAddress(this.address);
        customerInfo.setPhoneNumber(this.phoneNumber);
        customerInfo.setEmail(this.email);

        checkingBankStmt.setCustomerInfo(customerInfo);
        savingsBankStmt.setCustomerInfo(customerInfo);
        creditBankStmt.setCustomerInfo(customerInfo);

        // Process each of the three account types into its corresponding bank statement.
        Checking checkingAccount = this.checkingAccount;
        Savings savingsAccount = this.savingsAccount;
        Credit creditAccount = this.creditAccount;

        checkingBankStmt.setAccountInfo(checkingAccount);
        savingsBankStmt.setAccountInfo(savingsAccount);
        creditBankStmt.setAccountInfo(creditAccount);

        // Process the customer's list of transactions into the different transactions lists
        // for each account type.
        ArrayList<String> customerTransactions = this.transactionsList;

        ArrayList<String> checkingTransactions = new ArrayList<>();
        ArrayList<String> savingsTransactions = new ArrayList<>();
        ArrayList<String> creditTransactions = new ArrayList<>();

        if(!customerTransactions.isEmpty()){
            String[] transactionDetails;
            for(String transaction : customerTransactions){
                // Assume that the account involved in each transaction is indicated as the
                // second element of the transaction, which was written after the transaction was completed.
                transactionDetails = transaction.split("\\t");
                switch (transactionDetails[1]) {
                    case "Checking" -> checkingTransactions.add(transaction);
                    case "Savings" -> savingsTransactions.add(transaction);
                    case "Credit" -> creditTransactions.add(transaction);
                }
            }
        }

        // Set the different transactions lists into their corresponding bank statement.
        checkingBankStmt.setCustomerTransactions(checkingTransactions);
        savingsBankStmt.setCustomerTransactions(savingsTransactions);
        creditBankStmt.setCustomerTransactions(creditTransactions);

        // Process each account's list of transactions in order to obtain its start and end
        // balances.
        String checkingStartBalance = checkingBankStmt.extractStartBalance(checkingAccount,checkingTransactions);
        String checkingEndBalance = checkingBankStmt.extractEndBalance(checkingAccount,checkingTransactions);

        String savingsStartBalance = savingsBankStmt.extractStartBalance(savingsAccount,savingsTransactions);
        String savingsEndBalance = savingsBankStmt.extractEndBalance(savingsAccount,savingsTransactions);

        String creditStartBalance = creditBankStmt.extractStartBalance(creditAccount,creditTransactions);
        String creditEndBalance = creditBankStmt.extractEndBalance(creditAccount,creditTransactions);

        // Set the start and end balances into their corresponding bank statement.
        checkingBankStmt.setStartingBalance(checkingStartBalance);
        checkingBankStmt.setEndingBalance(checkingEndBalance);

        savingsBankStmt.setStartingBalance(savingsStartBalance);
        savingsBankStmt.setEndingBalance(savingsEndBalance);

        creditBankStmt.setStartingBalance(creditStartBalance);
        creditBankStmt.setEndingBalance(creditEndBalance);

        // Process the date of transactions into each of the bank statements.
        Date transactionsDate = new Date();

        checkingBankStmt.setTransactionsDate(transactionsDate);
        savingsBankStmt.setTransactionsDate(transactionsDate);
        creditBankStmt.setTransactionsDate(transactionsDate);

        // Print the bank statement for each account to the file created for the customer.
        checkingBankStmt.printBankStatement(outputFile);
        savingsBankStmt.printBankStatement(outputFile);
        creditBankStmt.printBankStatement(outputFile);
    }
}