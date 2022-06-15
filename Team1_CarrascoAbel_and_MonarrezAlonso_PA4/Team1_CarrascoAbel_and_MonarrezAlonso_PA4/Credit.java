/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the concrete class Credit is to implement methods
 * in order to create a credit account that has access to the attributes and methods that are
 * common to every type of account in a banking system as well as its own.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public class Credit extends Account{
    private int creditMax;

    /**
     * The default constructor of a Credit account. Taken from Alonso Monarrez.
     */
    public Credit(){
        super();
    }

    /**
     * A constructor of a Credit account that includes the attributes common to every account as well as its own.
     * Taken from Alonso Monarrez.
     * @param accountNumberIn A credit account's account number.
     * @param accountBalanceIn A credit account's account balance.
     * @param creditMaxIn A credit account's credit max.
     */
    public Credit(int accountNumberIn, double accountBalanceIn, int creditMaxIn){
        super(accountNumberIn, accountBalanceIn);
        this.creditMax = creditMaxIn;
    }

    /**
     * A setter method that sets a credit account's credit max to the given parameter.
     * Taken from Alonso Monarrez.
     * @param creditMaxIn A credit account's credit max.
     */
    public void setCreditMax(int creditMaxIn) {
        this.creditMax = creditMaxIn;
    }

    /**
     * A getter method that returns a credit account's credit max. Taken from Alonso Monarrez.
     * @return creditMax Returns the credit max of a credit account.
     */
    public int getCreditMax() {
        return this.creditMax;
    }
}