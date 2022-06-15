/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the concrete class Savings is to implement methods
 * in order to create a savings account that has access to the attributes and methods that are
 * common to every type of account in a banking system.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public class Savings extends Account{
    /**
     * The default constructor of a Savings account. Taken from Alonso Monarrez.
     */
    public Savings(){
        super();
    }

    /**
     * A constructor of a Savings account that includes the attributes common to every account.
     * Taken from Alonso Monarrez.
     * @param accountNumberIn A savings' account account number.
     * @param accountBalanceIn A savings' account account balance.
     */
    public Savings(int accountNumberIn, double accountBalanceIn){
        super(accountNumberIn, accountBalanceIn);
    }
}