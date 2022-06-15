import java.io.*;
import java.util.*;
/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the concrete class BankStatement is to implement the necessary
 * attributes and methods to create a bank statement that contains information related to all
 * transactions done for any customer associated with the bank.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public class BankStatement implements Printable{
    private Customer customerInfo;
    private Account accountInfo;
    private String startingBalance;
    private String endingBalance;
    private ArrayList<String> customerTransactions;
    private Date transactionsDate;

    /**
     * The default constructor of a BankStatement. Taken from Alonso Monarrez.
     */
    public BankStatement(){

    }

    /**
     * A constructor of a BankStatement that contains the attributes common to every bank statement.
     * Taken from Alonso Monarrez.
     * @param customerInfoIn A bank statement's customer information.
     * @param accountInfoIn A bank statement's account information.
     * @param startingBalanceIn A bank statement's starting balance.
     * @param endingBalanceIn A bank statement's ending balance.
     * @param customerTransactionsIn A bank statement's list of transactions.
     * @param transactionsDateIn A bank statement's date of transactions.
     */
    public BankStatement(Customer customerInfoIn, Account accountInfoIn, String startingBalanceIn,
                         String endingBalanceIn, ArrayList<String> customerTransactionsIn, Date transactionsDateIn){
        this.customerInfo = customerInfoIn;
        this.accountInfo = accountInfoIn;
        this.startingBalance = startingBalanceIn;
        this.endingBalance = endingBalanceIn;
        this.customerTransactions = customerTransactionsIn;
        this.transactionsDate = transactionsDateIn;
    }

    /**
     * A setter method that sets a bank statement's customer information to the given parameter.
     * Taken from Alonso Monarrez.
     * @param customerInfoIn A bank statement's customer information.
     */
    public void setCustomerInfo(Customer customerInfoIn){
        this.customerInfo = customerInfoIn;
    }

    /**
     * A setter method that sets a bank statement's account information to the given parameter.
     * Taken from Alonso Monarrez.
     * @param accountInfoIn A bank statement's account information.
     */
    public void setAccountInfo(Account accountInfoIn){
        this.accountInfo = accountInfoIn;
    }

    /**
     * A setter method that sets a bank statement's starting balance to the given parameter.
     * Taken from Alonso Monarrez.
     * @param startingBalanceIn A bank statement's starting balance.
     */
    public void setStartingBalance(String startingBalanceIn){
        this.startingBalance = startingBalanceIn;
    }

    /**
     * A setter method that sets a bank statement's ending balance to the given parameter.
     * Taken from Alonso Monarrez.
     * @param endingBalanceIn A bank statement's ending balance.
     */
    public void setEndingBalance(String endingBalanceIn){
        this.endingBalance = endingBalanceIn;
    }

    /**
     * A setter method that sets a bank statement's list of transactions to the given parameter.
     * Taken from Alonso Monarrez.
     * @param customerTransactionsIn A bank statement's list of transactions.
     */
    public void setCustomerTransactions(ArrayList<String> customerTransactionsIn){
        this.customerTransactions = customerTransactionsIn;
    }

    /**
     * A setter method that sets a bank statement's date of transactions to the given parameter.
     * Taken from Alonso Monarrez.
     * @param transactionsDateIn A bank statement's date of transactions.
     */
    public void setTransactionsDate(Date transactionsDateIn){
        this.transactionsDate = transactionsDateIn;
    }

    /**
     * A getter method that returns a bank statement's customer information. Taken from Alonso Monarrez.
     * @return customerInfo Returns the customer information of a bank statement.
     */
    public Customer getCustomerInfo() {
        return this.customerInfo;
    }

    /**
     * A getter method that returns a bank statement's account information. Taken from Alonso Monarrez.
     * @return accountInfo Returns the account information of a bank statement.
     */
    public Account getAccountInfo() {
        return this.accountInfo;
    }

    /**
     * A getter method that returns a bank statement's starting balance. Taken from Alonso Monarrez.
     * @return startingBalance Returns the starting balance of a bank statement.
     */
    public String getStartingBalance() {
        return this.startingBalance;
    }

    /**
     * A getter method that returns a bank statement's ending balance. Taken from Alonso Monarrez.
     * @return endingBalance Returns the ending balance of a bank statement.
     */
    public String getEndingBalance() {
        return this.endingBalance;
    }

    /**
     * A getter method that returns a bank statement's list of transactions. Taken from Alonso Monarrez.
     * @return customerTransactions Returns the list of transactions of a bank statement.
     */
    public ArrayList<String> getCustomerTransactions() {
        return this.customerTransactions;
    }

    /**
     * A getter method that returns a bank statement's date of transactions. Taken from Alonso Monarrez.
     * @return transactionsDate Returns the date of transactions of a bank statement.
     */
    public Date getTransactionsDate() {
        return this.transactionsDate;
    }

    /**
     * A method that checks the very first transaction in a list of transactions for a given account to return
     * the starting balance of said account. Taken from Alonso Monarrez.
     * @param accountIn The account involved in the transactions.
     * @param accountTransactions The list of transactions for a given account.
     * @return startBalance Returns the starting balance of the transactions list for a given account type.
     * @throws ArrayIndexOutOfBoundsException if the index of an array cannot be accessed.
     */
    public String extractStartBalance(Account accountIn, ArrayList<String> accountTransactions)
            throws ArrayIndexOutOfBoundsException{
        String startBalance = "";
        if(accountIn != null){
            startBalance = "$" + String.format("%.2f", accountIn.getAccountBalance());
            if(!accountTransactions.isEmpty()){
                // Retrieve the first transaction listed in the transactions list.
                String firstTransaction = accountTransactions.get(0);
                // Assume that the starting balance is indicated as the third element of the
                // transaction regardless of the transaction that was performed.
                String[] firstTransactionDetails = firstTransaction.split("\\t");
                startBalance = firstTransactionDetails[2];
            }
        }
        return startBalance;
    }

    /**
     * A method that checks the very last transaction in a list of transactions for a given account to return
     * the ending balance of said account. Taken from Alonso Monarrez.
     * @param accountIn The account involved in the transactions.
     * @param accountTransactions The list of transactions for a given account.
     * @return endBalance Returns the ending balance of the transactions list for a given account type.
     * @throws ArrayIndexOutOfBoundsException if the index of an array cannot be accessed.
     */
    public String extractEndBalance(Account accountIn, ArrayList<String> accountTransactions)
            throws ArrayIndexOutOfBoundsException{
        String endBalance = "";
        if(accountIn != null){
            endBalance = "$" + String.format("%.2f", accountIn.getAccountBalance());
            if(!accountTransactions.isEmpty()){
                // Retrieve the last transaction listed in the transactions list.
                String lastTransaction = accountTransactions.get(accountTransactions.size() - 1);
                // Assume that the ending balance is indicated as the last element of the
                // transaction regardless of the transaction that was performed.
                String[] lastTransactionDetails = lastTransaction.split("\\t");
                endBalance = lastTransactionDetails[lastTransactionDetails.length - 1];
            }
        }
        return endBalance;
    }

    /**
     * A method that formats a customer's personal information in order to print it to the bank statement
     * for that customer. Taken from Abel Carrasco.
     * @return Returns the customer's personal information to be printed to the bank statement.
     */
    @Override
    public String print(){
        return "Name: " + this.getCustomerInfo().getFirstName() + " " + this.getCustomerInfo().getLastName() + "\n" +
                "Date of birth: " + this.getCustomerInfo().getDateOfBirth() + "\n" +
                "Address: " + this.getCustomerInfo().getAddress() + "\n" +
                "Phone number: " + this.getCustomerInfo().getPhoneNumber() + "\n" +
                "Email: " + this.getCustomerInfo().getEmail() + "\n" +
                "Bank identification number: " + this.getCustomerInfo().getIdNumber() + "\n";
    }

    /**
     * A method that formats a transaction in order to print it to the bank statement. Taken from Abel Carrasco.
     * @param transaction The transaction to be formatted and printed.
     * @return Returns the formatted transaction to be printed to the bank statement.
     */
    @Override
    public String printTransaction(String transaction){
        StringBuilder writtenTransaction = new StringBuilder();
        String[] transactionInfo = transaction.split("\\t");
        for(String transactionAspect : transactionInfo){
            String formattedTransactionAspect = String.format("%-18s", transactionAspect);
            writtenTransaction.append(formattedTransactionAspect);
        }
        return writtenTransaction.toString();
    }

    /**
     * A method that prints all available customer, account, and transaction information to
     * a bank statement. Taken from Abel Carrasco.
     * @param outputFile The file that represents the newly created bank statement.
     * @throws IOException if the bank statement file cannot be found or accessed.
     * @throws ArrayIndexOutOfBoundsException if the index of an array cannot be accessed.
     */
    @Override
    public void printBankStatement(File outputFile) throws IOException, ArrayIndexOutOfBoundsException{
        // Verify whether the bank statement has access to the three types of accounts.
        if(this.getAccountInfo() != null){
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true));
            // Write the customer's information to the top of the account's bank statement.
            String customerInfo = this.print();
            bw.write(customerInfo);
            bw.newLine();
            bw.write("Date of transactions: " + this.getTransactionsDate());
            bw.newLine();
            bw.newLine();
            bw.write("----------------------------------------------------------------------------------------");
            bw.newLine();
            bw.write("Account number: " + this.getAccountInfo().getAccountNumber());
            bw.newLine();
            bw.write("----------------------------------------------------------------------------------------");
            bw.newLine();
            bw.write("Starting balance: " + this.getStartingBalance());
            bw.newLine();
            if(!this.getCustomerTransactions().isEmpty()){
                String header = String.format("%-18s", "Action") + String.format("%-18s", "Account") +
                                String.format("%-18s", "Start Balance") + String.format("%-18s", "Action Amount") +
                                String.format("%-18s", "End Balance");
                bw.write(header);
                bw.newLine();
                for(String transaction : this.getCustomerTransactions()){
                    String displayedTransaction = this.printTransaction(transaction);
                    bw.write(displayedTransaction);
                    bw.newLine();
                }
            }else{
                bw.write("No transactions have been made at this time!");
                bw.newLine();
            }
            bw.write("Ending balance: " + this.getEndingBalance());
            bw.newLine();
            bw.write("----------------------------------------------------------------------------------------");
            bw.newLine();
            bw.newLine();
            bw.close();
        }
    }
}