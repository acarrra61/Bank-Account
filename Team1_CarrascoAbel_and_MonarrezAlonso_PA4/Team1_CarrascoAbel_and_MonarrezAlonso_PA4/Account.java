/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the abstract class Account is to implement the attributes and
 * methods that are common to every type of account in a banking system.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public abstract class Account {
    protected int accountNumber;
    protected double accountBalance;

    /**
     * The default constructor of an Account. Taken from Alonso Monarrez.
     */
    public Account(){

    }

    /**
     * A constructor of an Account that includes the attributes common to every account.
     * Taken from Alonso Monarrez.
     * @param accountNumberIn An account's account number.
     * @param accountBalanceIn An account's account balance.
     */
    public Account(int accountNumberIn, double accountBalanceIn){
        this.accountNumber = accountNumberIn;
        this.accountBalance = accountBalanceIn;
    }

    /**
     * A setter method that sets an account's account number to the given parameter.
     * Taken from Alonso Monarrez.
     * @param accountNumberIn An account's account number.
     */
    public void setAccountNumber(int accountNumberIn) { this.accountNumber = accountNumberIn; }

    /**
     * A setter method that sets an account's account balance to the given parameter.
     * Taken from Alonso Monarrez.
     * @param accountBalanceIn An account's account balance.
     */
    public void setAccountBalance(double accountBalanceIn) {
        this.accountBalance = accountBalanceIn;
    }

    /**
     * A getter method that returns an account's account number. Taken from Alonso Monarrez.
     * @return accountNumber Returns the account number of an account.
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * A getter method that returns an account's account balance. Taken from Alonso Monarrez.
     * @return accountBalance Returns the account balance of an account.
     */
    public double getAccountBalance() {
        return this.accountBalance;
    }

    /**
     * A method that returns the account balance of an account. Taken from Alonso Monarrez.
     * @return Returns the account balance of an account by calling the getter method getAccountBalance().
     */
    public double inquireBalance(){
        return this.getAccountBalance();
    }

    /**
     * A method that updates the account balance of an account after some money is deposited to it.
     * Taken from Alonso Monarrez.
     * @param transactionAmount The amount of money to be deposited to an account.
     */
    public void depositMoney(double transactionAmount){
        double prevBalance = this.getAccountBalance();
        double newBalance = prevBalance + transactionAmount;
        this.setAccountBalance(newBalance);
    }

    /**
     * A method that determines whether some money has been withdrawn from the account balance of an account.
     * Taken from Alonso Monarrez.
     * @param transactionAmount The amount of money to be withdrawn from an account.
     * @return successWithdrawal Returns whether or not the money was withdrawn from the account balance of an account.
     */
    public boolean withdrawMoney(double transactionAmount){
        boolean successWithdrawal;
        // Compare the account's current balance to the transaction amount.
        int balanceComparison = Double.compare(this.getAccountBalance(), transactionAmount);
        // If the account balance is less than the transaction amount, the specified withdrawal is not possible.
        // Otherwise, update the account balance after the withdrawal is completed.
        if(balanceComparison < 0){
            successWithdrawal = false;
        }else{
            double prevBalance = this.getAccountBalance();
            double newBalance = prevBalance - transactionAmount;
            this.setAccountBalance(newBalance);
            successWithdrawal = true;
        }
        return successWithdrawal;
    }

    /**
     * A method that determines whether some money has been transferred between two accounts.
     * Taken from Alonso Monarrez.
     * @param secondAccount The account that receives the money from the other account.
     * @param transactionAmount The amount of money to be transferred between accounts.
     * @return successTransfer Returns whether or not the money was transferred between the two accounts.
     */
    public boolean transferMoney(Account secondAccount, double transactionAmount){
        boolean successTransfer;
        // Determine whether the transaction amount can be withdrawn from the first account's balance.
        boolean allowWithdrawal = this.withdrawMoney(transactionAmount);
        // If the withdrawal above is possible, deposit the money to the second account.
        // Otherwise, the transfer is not possible.
        if(allowWithdrawal){
            secondAccount.depositMoney(transactionAmount);
            successTransfer = true;
        }else{
            successTransfer = false;
        }
        return successTransfer;
    }

    /**
     * A method that determines whether some money has been paid from one customer's account to another.
     * Taken from Alonso Monarrez.
     * @param receiver The customer that receives the payment from the other customer's account.
     * @param receiverAcctType The account type of the receiver's account to which the payment is made.
     * @param transactionAmount The amount of money to be paid to the receiving customer's account.
     * @return Returns whether or not the payment was made between two customers' accounts.
     */
    public boolean paySomeone(Customer receiver, String receiverAcctType, double transactionAmount){
        // Determine whether the transaction amount can be transferred between the two customers' accounts.
        // If the transfer below is possible, the payment has been made. Otherwise, the payment is not possible.
        return this.transferMoney(receiver.getCustomerAccount(receiverAcctType), transactionAmount);
    }
}