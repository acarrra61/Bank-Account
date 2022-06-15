/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the concrete class InvalidPasswordException is to create a user
 * defined exception that can be used to handle cases in which a bank customer tries to log into
 * the system but fails to provide the correct password associated with his or her account.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public class InvalidPasswordException extends Exception{
    private String message;

    /**
     * The default constructor of an InvalidPasswordException. Taken from Alonso Monarrez.
     */
    public InvalidPasswordException(){
        super();
    }

    /**
     * A constructor of an InvalidPasswordException that includes the message to be displayed when
     * this exception is caught. Taken from Alonso Monarrez.
     * @param messageIn The user-defined exception's message to be displayed.
     */
    public InvalidPasswordException(String messageIn){
        this.message = messageIn;
    }

    /**
     * A method that returns a message related to the InvalidPasswordException whenever it is
     * caught. Taken from Alonso Monarrez.
     * @return message Returns the message of the caught user-defined exception.
     */
    public String toString(){
        return this.message;
    }
}