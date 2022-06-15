import java.io.*;
/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the interface Printable is to define the methods to be used for
 * printing a bank customer's account/transaction information to the console or to a file.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public interface Printable{
    /**
     * An abstract method that is used to create a String containing a customer's account
     * information to be printed to the console or to a file. Taken from Abel Carrasco.
     * @return Returns a String containing a customer's account information.
     */
    String print();

    /**
     * An abstract method that is used to create a String containing information about a
     * transaction to be printed to different files. Taken from Abel Carrasco.
     * @param transaction The transaction to be formatted and printed.
     * @return Returns a String containing the formatted transaction to be printed.
     */
    String printTransaction(String transaction);

    /**
     * An abstract method that is used to create a bank statement to be printed to a
     * file. Taken from Abel Carrasco.
     * @param outputFile The file in which the bank statement is printed.
     * @throws IOException if the file cannot be found or accessed.
     * @throws ArrayIndexOutOfBoundsException if the index of an array cannot be accessed.
     */
    void printBankStatement(File outputFile) throws IOException, ArrayIndexOutOfBoundsException;
}