import java.io.*;
import java.util.*;
/**
 * Instructor: Daniel Mejia, Course: CS 3331
 * Lab Assignment: Programming Assignment 4
 * Description: The purpose of the concrete class RunBank is to implement the necessary methods
 * to create and run a program that represents different activities that are typically done by a
 * customer and a manager of a banking system.
 * Honesty Statement: We confirm that the work of this assignment is completely our own.
 * By turning in this assignment, we declare that we did not receive unauthorized assistance.
 * Moreover, all deliverables including, but not limited to the source code, lab report, and
 * output files were written and produced by my partner and I, alone.
 * @author Abel Carrasco and Alonso Monarrez
 * @version 1.7
 * @since July 15, 2020
 */
public class RunBank{
    /**
     * A method that reads a file containing the account information of several bank users and stores said
     * information into a hash map of customers. Taken from Alonso Monarrez.
     * @param customersFile The original file of bank customers to be read.
     * @return customersHashMap Returns a hash map containing customers' account information.
     * @throws IOException if the bank users file cannot be found or accessed.
     */
    public static HashMap<String, Customer> storeCustomersInfo(String customersFile) throws IOException{
        // Create the appropriate data structure to store the bank account information
        // of all customers found in the original input file.
        HashMap<String, Customer> customersHashMap = new HashMap<>();

        FileReader fr = new FileReader(customersFile);
        BufferedReader br = new BufferedReader(fr);

        // Store the fields of the original file's header in order to process
        // the fields for each customer accordingly.
        String fileHeader = br.readLine();
        String[] headerFields = fileHeader.split("\\t");

        String[] customerInfo;
        Customer storedCustomer;
        String customerFullName;
        Checking customerCheckingAcct;
        Savings customerSavingsAcct;
        Credit customerCreditAcct;
        ArrayList<String> customerTransactionsList;

        String line;
        while((line = br.readLine()) != null){
            customerInfo = line.split("\\t");
            storedCustomer = new Customer();

            // Create each type of account for each customer in order to add
            // the corresponding account information to each account.
            customerCheckingAcct = new Checking();
            storedCustomer.setCheckingAccount(customerCheckingAcct);
            customerSavingsAcct = new Savings();
            storedCustomer.setSavingsAccount(customerSavingsAcct);
            customerCreditAcct = new Credit();
            storedCustomer.setCreditAccount(customerCreditAcct);

            // Create the appropriate data structure in order to store
            // each customer's list of transactions.
            customerTransactionsList = new ArrayList<>();
            storedCustomer.setTransactionsList(customerTransactionsList);

            // Traverse through the original file's header in order to determine the
            // position for each element of the customer's account information and process
            // each element accordingly.
            for(int index = 0; index < headerFields.length; index++){
                if(headerFields[index].equals("First Name")){
                    String customerFirstName = customerInfo[index];
                    storedCustomer.setFirstName(customerFirstName);
                }else if(headerFields[index].equals("Last Name")){
                    String customerLastName = customerInfo[index];
                    storedCustomer.setLastName(customerLastName);
                }else if(headerFields[index].equals("Date of Birth")){
                    String customerDOB = customerInfo[index];
                    storedCustomer.setDateOfBirth(customerDOB);
                }else if(headerFields[index].equals("Address")){
                    String customerAddress = customerInfo[index];
                    storedCustomer.setAddress(customerAddress);
                }else if(headerFields[index].equals("Phone Number")){
                    String customerPhoneNum = customerInfo[index];
                    storedCustomer.setPhoneNumber(customerPhoneNum);
                }else if(headerFields[index].equals("Identification Number")){
                    int customerIdNum = Integer.parseInt(customerInfo[index]);
                    storedCustomer.setIdNumber(customerIdNum);
                }else if(headerFields[index].equals("Email")){
                    String customerEmail = customerInfo[index];
                    storedCustomer.setEmail(customerEmail);
                }else if(headerFields[index].equals("Password")){
                    String customerPwd = customerInfo[index];
                    storedCustomer.setPassword(customerPwd);
                }else if(headerFields[index].equals("Checking Account Number")){
                    int customerCheckingAcctNum = Integer.parseInt(customerInfo[index]);
                    customerCheckingAcct.setAccountNumber(customerCheckingAcctNum);
                }else if(headerFields[index].equals("Checking Starting Balance")){
                    double customerCheckingBalance = Double.parseDouble(customerInfo[index]);
                    customerCheckingAcct.setAccountBalance(customerCheckingBalance);
                }else if(headerFields[index].equals("Savings Account Number")){
                    int customerSavingsAcctNum = Integer.parseInt(customerInfo[index]);
                    customerSavingsAcct.setAccountNumber(customerSavingsAcctNum);
                }else if(headerFields[index].equals("Savings Starting Balance")){
                    double customerSavingsBalance = Double.parseDouble(customerInfo[index]);
                    customerSavingsAcct.setAccountBalance(customerSavingsBalance);
                }else if(headerFields[index].equals("Credit Account Number")){
                    int customerCreditAcctNum = Integer.parseInt(customerInfo[index]);
                    customerCreditAcct.setAccountNumber(customerCreditAcctNum);
                }else if(headerFields[index].equals("Credit Starting Balance")){
                    double customerCreditBalance = Double.parseDouble(customerInfo[index]);
                    customerCreditAcct.setAccountBalance(customerCreditBalance);
                }else if(headerFields[index].equals("Credit Max")){
                    int customerCreditMax = Integer.parseInt(customerInfo[index]);
                    customerCreditAcct.setCreditMax(customerCreditMax);
                }
            }
            // For each customer in the original file, store the corresponding Customer object
            // by the customer's full name.
            customerFullName = storedCustomer.getFirstName() + " " + storedCustomer.getLastName();
            customersHashMap.put(customerFullName, storedCustomer);
        }
        br.close();
        return customersHashMap;
    }

    /**
     * A method that searches for a customer by the first and last names provided. Taken from Abel Carrasco.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @param firstName The first name of the customer to be searched.
     * @param lastName The last name of the customer to be searched.
     * @return Returns the customer from the hash map whose name matches the one provided to the method.
     */
    public static Customer searchCustomer(HashMap<String, Customer> customersDatabase,String firstName,String lastName){
        // Given the account information of all currently stored customers, search for the specified customer by
        // his or her full name.
        String searchFullName = firstName + " " + lastName;
        return customersDatabase.get(searchFullName);
    }

    /**
     * A method that prompts a customer to enter the information needed to process his or her
     * requested balance inquiry. Taken from Alonso Monarrez.
     * @param validCustomer The customer requesting the balance inquiry.
     * @param logFile The file to which the successful balance inquiry is logged.
     * @throws IOException if the log file cannot be found or accessed.
     */
    public static void requestBalanceInquiry(Customer validCustomer, File logFile) throws IOException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("ENTER THE FOLLOWING INFORMATION TO INQUIRE BALANCE");
        System.out.print("For what account are you inquiring balance? ");
        String inputAcctType = userInput.next();

        // Verify that the customer entered one of the three valid account types.
        Account customerAccount = validCustomer.getCustomerAccount(inputAcctType);
        if(customerAccount != null){
            int validAcctNumber = customerAccount.getAccountNumber();
            double inquiredBalance = customerAccount.inquireBalance();
            String roundedInquiredBalance = String.format("%.2f", inquiredBalance);
            String inquiredBalanceWithDollarSign = "$" + roundedInquiredBalance;
            // Add the balance inquiry to the list of the customer's transactions.
            String transaction = "Balance Inquiry" + "\t" + inputAcctType + "\t" + inquiredBalanceWithDollarSign;
            validCustomer.getTransactionsList().add(transaction);
            // Log the balance inquiry to the transactions log.
            logTransaction(logFile,validCustomer,transaction);
            // Display the customer's current balance for the specified account.
            System.out.println("Current balance for " + inputAcctType + "-" + validAcctNumber + ": " +
                                inquiredBalanceWithDollarSign);
            // Display a message that indicates the successful balance inquiry.
            System.out.println("Balance inquiry for " + inputAcctType + "-" + validAcctNumber + " successful!\n");
        }else{
            System.out.println("ERROR: Invalid account! Verify the entered account type and try again.\n");
        }
    }

    /**
     * A method that prompts a customer to enter the information needed to process his or her
     * requested deposit. Taken from Alonso Monarrez.
     * @param validCustomer The customer requesting the deposit.
     * @param logFile The file to which the successful deposit is logged.
     * @throws InputMismatchException if the user enters the incorrect type for the prompted input.
     * @throws IOException if the log file cannot be found or accessed.
     */
    public static void requestDeposit(Customer validCustomer, File logFile) throws InputMismatchException, IOException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("ENTER THE FOLLOWING INFORMATION TO DEPOSIT MONEY");
        System.out.print("To what account are you depositing money? ");
        String inputAcctType = userInput.next();

        // Verify that the customer entered one of the three valid account types.
        Account customerAccount = validCustomer.getCustomerAccount(inputAcctType);
        if(customerAccount != null){
            try{
                // Verify that the transaction amount is set only if it is positive.
                double inputAmount;
                do{
                    System.out.print("How much money will you deposit to this account? $");
                    inputAmount = userInput.nextDouble();
                }while(inputAmount < 0);
                String roundedAmount = String.format("%.2f", inputAmount);
                String amountWithDollarSign = "$" + roundedAmount;
                // Process the customer's balance of the specified account before the deposit is completed.
                double prevBalance = customerAccount.getAccountBalance();
                String roundedPrevBalance = String.format("%.2f", prevBalance);
                String prevBalanceWithDollarSign = "$" + roundedPrevBalance;
                // Complete the deposit of money.
                customerAccount.depositMoney(inputAmount);
                // Process the customer's balance of the specified account after the deposit is completed.
                double newBalance = customerAccount.getAccountBalance();
                String roundedNewBalance = String.format("%.2f", newBalance);
                String newBalanceWithDollarSign = "$" + roundedNewBalance;
                // Add the deposit to the list of the customer's transactions.
                String transaction = "Deposit" + "\t" + inputAcctType + "\t" + prevBalanceWithDollarSign + "\t" + "+" +
                                      amountWithDollarSign + "\t" + newBalanceWithDollarSign;
                validCustomer.getTransactionsList().add(transaction);
                // Log the deposit to the transactions log.
                logTransaction(logFile,validCustomer,transaction);
                // Display a message that indicates the successful deposit.
                System.out.println("Deposit of " + amountWithDollarSign + " successful!");
            }catch(InputMismatchException e){
                System.out.print("ERROR: Invalid input! ");
                System.out.println("Verify the prompt and try again to enter the appropriate information.");
            }finally{
                System.out.println();
            }
        }else{
            System.out.println("ERROR: Invalid account! Verify the entered account type and try again.\n");
        }
    }

    /**
     * A method that prompts a customer to enter the information needed to process his or her
     * requested withdrawal. Taken from Alonso Monarrez.
     * @param validCustomer The customer requesting the withdrawal.
     * @param logFile The file to which the successful withdrawal is logged.
     * @throws InputMismatchException if the user enters the incorrect type for the prompted input.
     * @throws IOException if the log file cannot be found or accessed.
     */
    public static void requestWithdrawal(Customer validCustomer, File logFile)
            throws InputMismatchException, IOException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("ENTER THE FOLLOWING INFORMATION TO WITHDRAW MONEY");
        System.out.print("From what account are you withdrawing money? ");
        String inputAcctType = userInput.next();

        // Verify that the customer entered one of the three valid account types.
        Account customerAccount = validCustomer.getCustomerAccount(inputAcctType);
        if(customerAccount != null){
            try{
                double inputAmount;
                do{
                    System.out.print("How much money will you withdraw from this account? $");
                    inputAmount = userInput.nextDouble();
                }while(inputAmount < 0);
                String roundedAmount = String.format("%.2f", inputAmount);
                String amountWithDollarSign = "$" + roundedAmount;
                // Process the customer's balance of the specified account before the withdrawal is completed.
                double prevBalance = customerAccount.getAccountBalance();
                String roundedPrevBalance = String.format("%.2f", prevBalance);
                String prevBalanceWithDollarSign = "$" + roundedPrevBalance;
                // Verify whether it is possible to complete the withdrawal of money.
                if(customerAccount.withdrawMoney(inputAmount)){
                    // Process the customer's balance of the specified account after the withdrawal is completed.
                    double newBalance = customerAccount.getAccountBalance();
                    String roundedNewBalance = String.format("%.2f", newBalance);
                    String newBalanceWithDollarSign = "$" + roundedNewBalance;
                    // Add the withdrawal to the list of the customer's transactions.
                    String transaction = "Withdrawal" + "\t" + inputAcctType + "\t" + prevBalanceWithDollarSign +
                                         "\t" + "-" + amountWithDollarSign + "\t" + newBalanceWithDollarSign;
                    validCustomer.getTransactionsList().add(transaction);
                    // Log the withdrawal to the transactions log.
                    logTransaction(logFile,validCustomer,transaction);
                    // Display a message that indicates the successful withdrawal.
                    System.out.println("Withdrawal of " + amountWithDollarSign + " successful!");
                }else{
                    System.out.println("Withdrawal of " + amountWithDollarSign + " failed! Customer's " +
                                        inputAcctType + " account lacks funds to complete withdrawal.");
                }
            }catch(InputMismatchException e){
                System.out.print("ERROR: Invalid input! ");
                System.out.println("Verify the prompt and try again to enter the appropriate information.");
            }finally{
                System.out.println();
            }
        }else{
            System.out.println("ERROR: Invalid account! Verify the entered account type and try again.\n");
        }
    }

    /**
     * A method that prompts a customer to enter the information needed to process his or her
     * requested transfer. Taken from Alonso Monarrez.
     * @param validCustomer The customer requesting the transfer.
     * @param logFile The file to which the successful transfer is logged.
     * @throws InputMismatchException if the user enters the incorrect type for the prompted input.
     * @throws IOException if the log file cannot be found or accessed.
     */
    public static void requestTransfer(Customer validCustomer, File logFile) throws InputMismatchException, IOException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("ENTER THE FOLLOWING INFORMATION TO TRANSFER MONEY");
        System.out.print("From what account are you transferring money? ");
        String inputSourceAcctType = userInput.next();

        // Verify that the customer entered a valid account type for his or her source account.
        Account customerSourceAccount = validCustomer.getCustomerAccount(inputSourceAcctType);
        if(customerSourceAccount != null){
            System.out.print("To what account are you transferring money? ");
            String inputDestAcctType = userInput.next();

            // Verify that the customer entered a valid account type for his or her destination account.
            Account customerDestAccount = validCustomer.getCustomerAccount(inputDestAcctType);
            if(customerDestAccount != null){
                try{
                    double inputAmount;
                    do{
                        System.out.print("How much money will you transfer between accounts? $");
                        inputAmount = userInput.nextDouble();
                    }while(inputAmount < 0);
                    String roundedAmount = String.format("%.2f", inputAmount);
                    String amountWithDollarSign = "$" + roundedAmount;
                    // Process the customer's balance of the specified source account before the transfer
                    // is completed.
                    double prevSourceBalance = customerSourceAccount.getAccountBalance();
                    String roundedPrevSourceBalance = String.format("%.2f", prevSourceBalance);
                    String prevSourceBalanceWithDollarSign = "$" + roundedPrevSourceBalance;
                    // Process the customer's balance of the specified destination account before the transfer
                    // is completed.
                    double prevDestBalance = customerDestAccount.getAccountBalance();
                    String roundedPrevDestBalance = String.format("%.2f", prevDestBalance);
                    String prevDestBalanceWithDollarSign = "$" + roundedPrevDestBalance;
                    // Verify whether it is possible to complete the transfer of money.
                    if(customerSourceAccount.transferMoney(customerDestAccount, inputAmount)){
                        // Process the customer's balance of the specified source account after the transfer
                        // is completed.
                        double newSourceBalance = customerSourceAccount.getAccountBalance();
                        String roundedNewSourceBalance = String.format("%.2f", newSourceBalance);
                        String newSourceBalanceWithDollarSign = "$" + roundedNewSourceBalance;
                        // Add the transfer from the source account to the list of the customer's transactions.
                        String sourceTransaction = "Transfer" + "\t" + inputSourceAcctType + "\t" +
                                                    prevSourceBalanceWithDollarSign + "\t" + "-" +
                                                    amountWithDollarSign + "\t" + newSourceBalanceWithDollarSign;
                        validCustomer.getTransactionsList().add(sourceTransaction);
                        // Process the customer's balance of the specified destination account after the transfer
                        // is completed.
                        double newDestBalance = customerDestAccount.getAccountBalance();
                        String roundedNewDestBalance = String.format("%.2f", newDestBalance);
                        String newDestBalanceWithDollarSign = "$" + roundedNewDestBalance;
                        // Add the transfer to the destination account to the list of the customer's transactions.
                        String destTransaction = "Transfer" + "\t" + inputDestAcctType + "\t" +
                                                  prevDestBalanceWithDollarSign + "\t" + "+" + amountWithDollarSign +
                                                 "\t" + newDestBalanceWithDollarSign;
                        validCustomer.getTransactionsList().add(destTransaction);
                        // Log the transfer between the two accounts to the transactions log.
                        logTransaction(logFile,validCustomer,sourceTransaction);
                        logTransaction(logFile,validCustomer,destTransaction);
                        // Display a message that indicates the successful transfer.
                        System.out.println("Transfer of " + amountWithDollarSign + " successful!");
                    }else{
                        System.out.println("Transfer of " + amountWithDollarSign + " failed! Customer's " +
                                            inputSourceAcctType + " account lacks funds to complete transfer.");
                    }
                }catch(InputMismatchException e){
                    System.out.print("ERROR: Invalid input! ");
                    System.out.println("Verify the prompt and try again to enter the appropriate information.");
                }finally{
                    System.out.println();
                }
            }else{
                System.out.print("ERROR: Invalid destination account! ");
                System.out.println("Verify the entered account type and try again.\n");
            }
        }else{
            System.out.println("ERROR: Invalid source account! Verify the entered account type and try again.\n");
        }
    }

    /**
     * A method that prompts a customer to enter the information needed to process his or her
     * requested payment. Taken from Alonso Monarrez.
     * @param validSender The customer requesting the payment.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @param logFile The file to which the successful payment is logged.
     * @throws InputMismatchException if the user enters the incorrect type for the prompted input.
     * @throws IOException if the log file cannot be found or accessed.
     */
    public static void requestPayment(Customer validSender, HashMap<String, Customer> customersDatabase, File logFile)
            throws InputMismatchException, IOException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("ENTER THE FOLLOWING INFORMATION TO MAKE A PAYMENT");
        System.out.print("From what account are you withdrawing money to pay someone? ");
        String inputSenderAcctType = userInput.next();

        // Verify that the sender entered a valid account type for his or her account.
        Account senderAccount = validSender.getCustomerAccount(inputSenderAcctType);
        if(senderAccount != null){
            System.out.print("Receiver's first name: ");
            String inputReceiverFirstName = userInput.next();
            System.out.print("Receiver's last name: ");
            String inputReceiverLastName = userInput.next();

            // Given the input above, search for the receiver to whom the sender makes the payment.
            Customer validReceiver = searchCustomer(customersDatabase,inputReceiverFirstName,inputReceiverLastName);
            if(validReceiver != null){
                System.out.print("To what account are you depositing money to pay this customer? ");
                String inputReceiverAcctType = userInput.next();

                // Verify that the sender entered a valid account type for the receiver's account.
                Account receiverAccount = validReceiver.getCustomerAccount(inputReceiverAcctType);
                if(receiverAccount != null){
                    try{
                        String receiverFullName = validReceiver.getFirstName() + " " + validReceiver.getLastName();
                        double inputAmount;
                        do{
                            System.out.print("How much money will you pay to " + receiverFullName + "? $");
                            inputAmount = userInput.nextDouble();
                        }while(inputAmount < 0);
                        String roundedAmount = String.format("%.2f", inputAmount);
                        String amountWithDollarSign = "$" + roundedAmount;
                        // Process the sender's balance of the specified account before the payment is completed.
                        double prevSenderBalance = senderAccount.getAccountBalance();
                        String roundedPrevSenderBalance = String.format("%.2f", prevSenderBalance);
                        String prevSenderBalanceWithDollarSign = "$" + roundedPrevSenderBalance;
                        // Process the receiver's balance of the specified account before the payment is completed.
                        double prevReceiverBalance = receiverAccount.getAccountBalance();
                        String roundedPrevReceiverBalance = String.format("%.2f", prevReceiverBalance);
                        String prevReceiverBalanceWithDollarSign = "$" + roundedPrevReceiverBalance;
                        // Verify whether it is possible to complete the payment of money.
                        if(senderAccount.paySomeone(validReceiver,inputReceiverAcctType,inputAmount)){
                            // Process the sender's balance of the specified account after the payment is completed.
                            double newSenderBalance = senderAccount.getAccountBalance();
                            String roundedNewSenderBalance = String.format("%.2f", newSenderBalance);
                            String newSenderBalanceWithDollarSign = "$" + roundedNewSenderBalance;
                            // Add the payment from the sender's account to the list of the sender's transactions.
                            String senderTransaction = "Payment" + "\t" + inputSenderAcctType + "\t" +
                                                        prevSenderBalanceWithDollarSign + "\t" + "-" +
                                                        amountWithDollarSign + "\t" + newSenderBalanceWithDollarSign;
                            validSender.getTransactionsList().add(senderTransaction);
                            // Process the receiver's balance of the specified account after the payment is completed.
                            double newReceiverBalance = receiverAccount.getAccountBalance();
                            String roundedNewReceiverBalance = String.format("%.2f", newReceiverBalance);
                            String newReceiverBalanceWithDollarSign = "$" + roundedNewReceiverBalance;
                            // Add the payment to the receiver's account to the list of the receiver's transactions.
                            String receiverTransaction = "Payment" + "\t" + inputReceiverAcctType + "\t" +
                                                          prevReceiverBalanceWithDollarSign + "\t" + "+" +
                                                          amountWithDollarSign + "\t" +
                                                          newReceiverBalanceWithDollarSign;
                            validReceiver.getTransactionsList().add(receiverTransaction);
                            // Log the payment between the two customers to the transactions log.
                            logTransaction(logFile,validSender,senderTransaction);
                            logTransaction(logFile,validReceiver,receiverTransaction);
                            // Display a message that indicates the successful payment.
                            System.out.println("Payment of " + amountWithDollarSign + " successful!");
                        }else{
                            System.out.println("Payment of " + amountWithDollarSign + " failed! Sender's " +
                                                inputSenderAcctType + " account lacks funds to complete payment.");
                        }
                    }catch(InputMismatchException e){
                        System.out.print("ERROR: Invalid input! ");
                        System.out.println("Verify the prompt and try again to enter the appropriate information.");
                    }finally{
                        System.out.println();
                    }
                }else{
                    System.out.print("ERROR: Invalid receiver account! ");
                    System.out.println("Verify the entered account type and try again.\n");
                }
            }else{
                System.out.println("ERROR: Invalid receiver! Verify the entered name and try again.\n");
            }
        }else{
            System.out.println("ERROR: Invalid sender account! Verify the entered account type and try again.\n");
        }
    }

    /**
     * A method that allows a bank manager to create a new bank user by entering the new user's customer
     * and account information. Taken from Abel Carrasco.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @throws InputMismatchException if the user enters the incorrect type for the prompted input.
     */
    public static void addNewBankUser(HashMap<String, Customer> customersDatabase) throws InputMismatchException{
        try{
            boolean addNewBankUserSelection;
            do{
                Scanner userInput = new Scanner(System.in);

                // Create the Customer object to represent the added bank user.
                Customer newBankUser = new Customer();

                System.out.println("ENTER THE FOLLOWING INFORMATION TO ADD A NEW BANK USER");
                System.out.print("First name: ");
                String newBankUserFirstName = userInput.next();
                newBankUser.setFirstName(newBankUserFirstName);
                System.out.print("Last name: ");
                String newBankUserLastName = userInput.next();
                newBankUser.setLastName(newBankUserLastName);
                System.out.print("Date of birth: ");
                userInput.nextLine();
                String inputDOB = userInput.nextLine();
                String newBankUserDOB = "\"" + inputDOB + "\"";
                newBankUser.setDateOfBirth(newBankUserDOB);
                System.out.print("Address: ");
                String inputAddress = userInput.nextLine();
                String newBankUserAddress = "\"" + inputAddress + "\"";
                newBankUser.setAddress(newBankUserAddress);
                System.out.print("Phone number: ");
                String newBankUserPhoneNum = userInput.nextLine();
                newBankUser.setPhoneNumber(newBankUserPhoneNum);
                // Call the method below to increment the identification number of the last bank user entered.
                int newBankUserIdNum = findLargestIdentificationNumber(customersDatabase) + 1;
                newBankUser.setIdNumber(newBankUserIdNum);
                System.out.print("Email: ");
                String newBankUserEmail = userInput.nextLine();
                newBankUser.setEmail(newBankUserEmail);
                System.out.print("Bank account password: ");
                String newBankUserPwd = userInput.nextLine();
                newBankUser.setPassword(newBankUserPwd);

                Savings newBankUserSavingsAcct = new Savings();
                newBankUser.setSavingsAccount(newBankUserSavingsAcct);
                // Call the method below to increment the largest savings account number of the current bank users.
                int newBankUserSavingsAcctNum = findLargestSavingsAcctNumber(customersDatabase) + 1;
                newBankUserSavingsAcct.setAccountNumber(newBankUserSavingsAcctNum);
                // Enter a value for the new bank user's savings account balance.
                // The value of the savings account balance will only be set once it is positive.
                double newBankUserSavingsAcctBalance;
                do{
                    System.out.print("Savings account balance (only positive values are allowed): $");
                    newBankUserSavingsAcctBalance = userInput.nextDouble();
                }while(newBankUserSavingsAcctBalance < 0);
                newBankUserSavingsAcct.setAccountBalance(newBankUserSavingsAcctBalance);

                // Provide the bank manager the option to add a checking account to the new bank user.
                System.out.print("Would you like to create a checking account for this user? (true/false): ");
                boolean addCheckingAcctSelection = userInput.nextBoolean();
                if(addCheckingAcctSelection){
                    Checking newBankUserCheckingAcct = new Checking();
                    newBankUser.setCheckingAccount(newBankUserCheckingAcct);
                    // Compute the new bank user's checking account number by subtracting 1000 from his or her
                    // savings account number.
                    int newBankUserCheckingAcctNum = newBankUserSavingsAcctNum - 1000;
                    newBankUserCheckingAcct.setAccountNumber(newBankUserCheckingAcctNum);
                    // Enter a value for the new bank user's checking account balance.
                    // The value of the checking account balance will only be set once it is positive.
                    double newBankUserCheckingAcctBalance;
                    do{
                        System.out.print("Checking account balance (only positive values are allowed): $");
                        newBankUserCheckingAcctBalance = userInput.nextDouble();
                    }while(newBankUserCheckingAcctBalance < 0);
                    newBankUserCheckingAcct.setAccountBalance(newBankUserCheckingAcctBalance);
                }

                // Provide the bank manager the option to add a credit account to the new bank user.
                System.out.print("Would you like to create a credit account for this user? (true/false): ");
                boolean addCreditAcctSelection = userInput.nextBoolean();
                if(addCreditAcctSelection){
                    Credit newBankUserCreditAcct = new Credit();
                    newBankUser.setCreditAccount(newBankUserCreditAcct);
                    // Compute the new bank user's credit account number by adding 1000 to his or her savings
                    // account number.
                    int newBankUserCreditAcctNum = newBankUserSavingsAcctNum + 1000;
                    newBankUserCreditAcct.setAccountNumber(newBankUserCreditAcctNum);
                    // Enter a value for the new bank user's credit account balance.
                    System.out.print("Credit account balance: $");
                    double newBankUserCreditAcctBalance = userInput.nextDouble();
                    newBankUserCreditAcct.setAccountBalance(newBankUserCreditAcctBalance);
                    // Enter a value for the new bank user's credit max
                    // The value of the checking max will only be set once it is between $3000 and $5000.
                    int newBankUserCreditMax;
                    do{
                        System.out.print("Credit max (enter an integer value between 3000 and 5000): $");
                        newBankUserCreditMax = userInput.nextInt();
                    }while(newBankUserCreditMax < 3000 || newBankUserCreditMax > 5000);
                    newBankUserCreditAcct.setCreditMax(newBankUserCreditMax);
                }
                // Create the appropriate data structure in order to store the new bank user's list of transactions.
                ArrayList<String> newBankUserTransactionsList = new ArrayList<>();
                newBankUser.setTransactionsList(newBankUserTransactionsList);

                // Once the fields for the new bank user are set, store the new bank user by his or her full name.
                String newBankUserFullName = newBankUserFirstName + " " + newBankUserLastName;
                customersDatabase.put(newBankUserFullName, newBankUser);

                // Provide the bank manager the option to add multiple new bank users.
                System.out.print("Would you like to add another new bank user? (true/false): ");
                addNewBankUserSelection = userInput.nextBoolean();
            }while(addNewBankUserSelection);
        }catch(InputMismatchException e){
            System.out.print("ERROR: Invalid input! ");
            System.out.println("Verify the prompt and try again to enter the appropriate information.");
        }finally{
            System.out.println();
        }
    }

    /**
     * A method that returns the latest identification number recorded for the last customer added
     * to the customers database. Taken from Abel Carrasco.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @return maxIdNumber Returns the identification number of the most recently added customer.
     */
    public static int findLargestIdentificationNumber(HashMap<String, Customer> customersDatabase){
        int maxIdNumber = Integer.MIN_VALUE;
        for(Map.Entry<String, Customer> currCustomer : customersDatabase.entrySet()){
            int currIdNumber = currCustomer.getValue().getIdNumber();
            if(currIdNumber > maxIdNumber){
                maxIdNumber = currIdNumber;
            }
        }
        return maxIdNumber;
    }

    /**
     * A method that returns the largest savings account number recorded for the last customer added
     * to the customers database. Taken from Abel Carrasco.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @return maxSavingsAcctNumber Returns the savings account number of the most recently added customer.
     */
    public static int findLargestSavingsAcctNumber(HashMap<String, Customer> customersDatabase){
        int maxSavingsAcctNumber = Integer.MIN_VALUE;
        for(Map.Entry<String, Customer> currCustomer : customersDatabase.entrySet()){
            int currSavingsAcctNumber = currCustomer.getValue().getSavingsAccount().getAccountNumber();
            if(currSavingsAcctNumber > maxSavingsAcctNumber){
                maxSavingsAcctNumber = currSavingsAcctNumber;
            }
        }
        return maxSavingsAcctNumber;
    }

    /**
     * A method that searches for a customer by name to retrieve and print all of the information of the associated
     * account at the request of the bank manager. Taken from Abel Carrasco.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     */
    public static void inquireAccountByName(HashMap<String, Customer> customersDatabase){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Who's account would you like to inquire about?");
        System.out.print("First name: ");
        String inquiredFirstName = userInput.next();
        System.out.print("Last name: ");
        String inquiredLastName = userInput.next();

        // Given the input above, search for the customer by his or her first and last names.
        Customer inquiredCustomer = searchCustomer(customersDatabase, inquiredFirstName, inquiredLastName);

        if(inquiredCustomer != null){
            System.out.println(inquiredCustomer.print());
        }else{
            System.out.println("There is no account associated with the name provided.\n");
        }
    }

    /**
     * A method that searches for a customer by account type and number to retrieve and print all of the
     * information of the associated account at the request of the bank manager. Taken from Abel Carrasco.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     */
    public static void inquireAccountByTypeAndNumber(HashMap<String, Customer> customersDatabase){
        Scanner userInput = new Scanner(System.in);
        System.out.print("What account type? ");
        String inputAcctType = userInput.next();
        System.out.print("What is the account number? ");
        int inputAcctNum = userInput.nextInt();

        // Given the input above, search for the customer by his or her account type and number.
        Customer inquiredCustomer = searchAccount(customersDatabase,inputAcctType,inputAcctNum);

        if(inquiredCustomer != null){
            System.out.println(inquiredCustomer.print());
        }else{
            System.out.println("There is no account associated with the type and/or number provided.\n");
        }
    }

    /**
     * A method that traverses through the hash map of customers to find and return the account of the customer
     * whose account type and number matches those given by the bank manager. Taken from Alonso Monarrez.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @param acctType The account type requested by the bank manager.
     * @param acctNum The account number requested by the bank manager.
     * @return foundCustomerAccount Returns the account of the customer that matches the given criteria.
     */
    public static Customer searchAccount(HashMap<String, Customer> customersDatabase,String acctType,int acctNum){
        Customer foundCustomerAccount = null;
        // Verify that the entered type of the inquired account is one of the three valid types.
        if(acctType.equals("Checking") || acctType.equals("Savings") || acctType.equals("Credit")){
            for(Map.Entry<String, Customer> currCustomer : customersDatabase.entrySet()){
                // Verify that each customer has the specified type of account.
                Account currCustomerAcct = currCustomer.getValue().getCustomerAccount(acctType);
                if(currCustomerAcct != null){
                    if(currCustomerAcct.getAccountNumber() == acctNum){
                        foundCustomerAccount = currCustomer.getValue();
                    }
                }
            }
        }
        return foundCustomerAccount;
    }

    /**
     * A method that prints the account information of all customers associated with the bank.
     * Taken from Abel Carrasco.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     */
    public static void printAllAccounts(HashMap<String, Customer> customersDatabase){
        System.out.println("Here is a list of all accounts associated with this bank.");
        // Store all of the customers within the bank's database in alphabetical order.
        TreeMap<String, Customer> sortedCustomers = new TreeMap<>(customersDatabase);
        // Traverse through the hash map to display the account information of all customers to the console.
        for(Map.Entry<String, Customer> currCustomer : sortedCustomers.entrySet()){
            System.out.println(currCustomer.getValue().print());
        }
    }

    /**
     * A method that reads and processes all transactions from a file to update accordingly
     * the account information of all customers involved in successful transactions. Taken from Abel Carrasco.
     * @param transactionsFile The file containing the transactions to be read and processed.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @param logFile The file to which the successful transactions are logged.
     * @throws IOException if the transactions file or the log file cannot be found or accessed.
     * @throws ArrayIndexOutOfBoundsException if the index of an array cannot be accessed.
     */
    public static void readTransactions(String transactionsFile,HashMap<String, Customer> customersDatabase,
                                        File logFile) throws IOException, ArrayIndexOutOfBoundsException{
        FileReader fr = new FileReader(transactionsFile);
        BufferedReader br = new BufferedReader(fr);

        // Skip the header of the transaction actions file.
        br.readLine();

        String[] transaction;

        String line;
        while((line = br.readLine()) != null){
            // For each transaction, assume that its components are written in the file
            // in a pre-defined manner, such that their position, which is indicated by
            // a numerical index, matches that of the header's tags at the top of the file.
            transaction = line.split("\\t");
            switch(transaction[3]){
                case "inquires" -> {
                    String firstName = transaction[0];
                    String lastName = transaction[1];
                    String accountType = transaction[2];

                    // Search for the customer listed in the transaction that requests a balance inquiry.
                    Customer customer = searchCustomer(customersDatabase, firstName, lastName);

                    // Verify that the customer listed in the transaction exists in the bank customers database.
                    if(customer != null){
                        String fullName = customer.getFirstName() + " " + customer.getLastName();
                        int accountNumber = customer.getCustomerAccount(accountType).getAccountNumber();
                        double inquiredBalance = customer.getCustomerAccount(accountType).inquireBalance();
                        String roundedInquiredBalance = "$" + String.format("%.2f", inquiredBalance);
                        // Add the balance inquiry to the list of the customer's transactions.
                        String writtenTransaction = "Balance Inquiry" + "\t" + accountType + "\t" +
                                                     roundedInquiredBalance;
                        customer.getTransactionsList().add(writtenTransaction);
                        // Log the balance inquiry to the transactions log.
                        logTransaction(logFile, customer, writtenTransaction);
                        // Display the customer's current balance for the specified account along with a message
                        // indicating the successful balance inquiry for the specified customer.
                        System.out.print("Current balance for " + accountType + "-" + accountNumber + ": " +
                                          roundedInquiredBalance + "; ");
                        System.out.println("Balance inquiry for " + fullName + " successful!");
                    }else{
                        System.out.println("Balance inquiry failed due to an invalid bank customer.");
                    }
                    break;
                }
                case "deposits" -> {
                    String firstName = transaction[4];
                    String lastName = transaction[5];
                    String accountType = transaction[6];

                    // Search for the customer listed in the transaction that requests a deposit.
                    Customer customer = searchCustomer(customersDatabase, firstName, lastName);

                    // Verify that the customer listed in the transaction exists in the bank customers database.
                    if(customer != null){
                        String fullName = customer.getFirstName() + " " + customer.getLastName();
                        double actionAmount = Double.parseDouble(transaction[7]);
                        String roundedAmount = "$" + String.format("%.2f", actionAmount);
                        // Process the customer's balance of the specified account before the deposit is completed.
                        double prevBalance = customer.getCustomerAccount(accountType).getAccountBalance();
                        String roundedPrevBalance = "$" + String.format("%.2f", prevBalance);
                        // Complete the deposit of money.
                        customer.getCustomerAccount(accountType).depositMoney(actionAmount);
                        // Process the customer's balance of the specified account after the deposit is completed.
                        double newBalance = customer.getCustomerAccount(accountType).getAccountBalance();
                        String roundedNewBalance = "$" + String.format("%.2f", newBalance);
                        // Add the deposit to the list of the customer's transactions.
                        String writtenTransaction = "Deposit" + "\t" + accountType + "\t" + roundedPrevBalance + "\t" +
                                                    "+" + roundedAmount + "\t" + roundedNewBalance;
                        customer.getTransactionsList().add(writtenTransaction);
                        // Log the deposit to the transactions log.
                        logTransaction(logFile, customer, writtenTransaction);
                        // Display a message that indicates the successful deposit.
                        System.out.println("Deposit of " + roundedAmount + " to the " + accountType + " account of " +
                                            fullName + " successful!");
                    }else{
                        System.out.println("Deposit failed due to an invalid bank customer.");
                    }
                    break;
                }
                case "withdraws" -> {
                    String firstName = transaction[0];
                    String lastName = transaction[1];
                    String accountType = transaction[2];

                    // Search for the customer listed in the transaction that requests a withdrawal.
                    Customer customer = searchCustomer(customersDatabase, firstName, lastName);

                    // Verify that the customer listed in the transaction exists in the bank customers database.
                    if(customer != null){
                        String fullName = customer.getFirstName() + " " + customer.getLastName();
                        double actionAmount = Double.parseDouble(transaction[7]);
                        String roundedAmount = "$" + String.format("%.2f", actionAmount);
                        // Process the customer's balance of the specified account before the withdrawal is completed.
                        double prevBalance = customer.getCustomerAccount(accountType).getAccountBalance();
                        String roundedPrevBalance = "$" + String.format("%.2f", prevBalance);
                        // Verify whether it is possible to complete the withdrawal of money.
                        if(customer.getCustomerAccount(accountType).withdrawMoney(actionAmount)){
                            // Process the customer's balance of the specified account after the withdrawal
                            // is completed.
                            double newBalance = customer.getCustomerAccount(accountType).getAccountBalance();
                            String roundedNewBalance = "$" + String.format("%.2f", newBalance);
                            // Add the withdrawal to the list of the customer's transactions.
                            String writtenTransaction = "Withdrawal" + "\t" + accountType + "\t" + roundedPrevBalance +
                                                        "\t" + "-" + roundedAmount + "\t" + roundedNewBalance;
                            customer.getTransactionsList().add(writtenTransaction);
                            // Log the withdrawal to the transactions log.
                            logTransaction(logFile, customer, writtenTransaction);
                            // Display a message that indicates the successful withdrawal.
                            System.out.println("Withdrawal of " + roundedAmount + " from the " + accountType +
                                               " account of " + fullName + " successful!");
                        }else{
                            System.out.println("Withdrawal of " + roundedAmount + " failed! Customer's " + accountType +
                                               " account lacks funds to complete withdrawal.");
                        }
                    }else{
                        System.out.println("Withdrawal failed due to an invalid bank customer.");
                    }
                    break;
                }
                case "transfers" -> {
                    String firstName = transaction[0];
                    String lastName = transaction[1];
                    String sourceAccountType = transaction[2];
                    String destAccountType = transaction[6];

                    // Search for the customer listed in the transaction that requests a transfer.
                    Customer customer = searchCustomer(customersDatabase, firstName, lastName);

                    // Verify that the customer listed in the transaction exists in the bank customers database.
                    if(customer != null){
                        String fullName = customer.getFirstName() + " " + customer.getLastName();
                        double actionAmount = Double.parseDouble(transaction[7]);
                        String roundedAmount = "$" + String.format("%.2f", actionAmount);
                        // Process the customer's balance of the specified source account before the transfer
                        // is completed.
                        Account sourceAccount = customer.getCustomerAccount(sourceAccountType);
                        double prevSourceBalance = sourceAccount.getAccountBalance();
                        String roundedPrevSourceBalance = "$" + String.format("%.2f", prevSourceBalance);
                        // Process the customer's balance of the specified destination account before the transfer
                        // is completed.
                        Account destAccount = customer.getCustomerAccount(destAccountType);
                        double prevDestBalance = destAccount.getAccountBalance();
                        String roundedPrevDestBalance = "$" + String.format("%.2f", prevDestBalance);
                        // Verify whether it is possible to complete the transfer of money.
                        if(sourceAccount.transferMoney(destAccount, actionAmount)){
                            // Process the customer's balance of the specified source account after the transfer
                            // is completed.
                            double newSourceBalance = sourceAccount.getAccountBalance();
                            String roundedNewSourceBalance = "$" + String.format("%.2f", newSourceBalance);
                            // Process the customer's balance of the specified destination account after the transfer
                            // is completed.
                            double newDestBalance = destAccount.getAccountBalance();
                            String roundedNewDestBalance = "$" + String.format("%.2f", newDestBalance);
                            // Add the transfer between the two accounts to the list of the customer's transactions.
                            String sourceTransaction = "Transfer" + "\t" + sourceAccountType + "\t" +
                                                        roundedPrevSourceBalance + "\t" + "-" + roundedAmount + "\t" +
                                                        roundedNewSourceBalance;
                            customer.getTransactionsList().add(sourceTransaction);
                            String destTransaction = "Transfer" + "\t" + destAccountType + "\t" +
                                                      roundedPrevDestBalance + "\t" + "+" + roundedAmount + "\t" +
                                                      roundedNewDestBalance;
                            customer.getTransactionsList().add(destTransaction);
                            // Log the transfer between the two accounts to the transactions log.
                            logTransaction(logFile, customer, sourceTransaction);
                            logTransaction(logFile, customer, destTransaction);
                            // Display a message that indicates the successful transfer.
                            System.out.println("Transfer of " + roundedAmount + " from the " + sourceAccountType +
                                               " account to the " + destAccountType + " account of " + fullName +
                                               " successful!");
                        }else{
                            System.out.println("Transfer of " + roundedAmount + " failed! Customer's " +
                                                sourceAccountType + " account lacks funds to complete transfer.");
                        }
                    }else{
                        System.out.println("Transfer failed due to an invalid bank customer.");
                    }
                    break;
                }
                case "pays" -> {
                    String senderFirstName = transaction[0];
                    String senderLastName = transaction[1];
                    String senderAccountType = transaction[2];
                    String receiverFirstName = transaction[4];
                    String receiverLastName = transaction[5];
                    String receiverAccountType = transaction[6];

                    // Search for both customers listed in the transaction that request a payment.
                    Customer sender = searchCustomer(customersDatabase, senderFirstName, senderLastName);
                    Customer receiver = searchCustomer(customersDatabase, receiverFirstName, receiverLastName);

                    // Verify that both customers listed in the transaction exist in the bank customers database.
                    if(sender != null && receiver != null){
                        double actionAmount = Double.parseDouble(transaction[7]);
                        String roundedAmount = "$" + String.format("%.2f", actionAmount);
                        // Process the sender's balance of the specified account before the payment is completed.
                        String senderFullName = sender.getFirstName() + " " + sender.getLastName();
                        Account senderAccount = sender.getCustomerAccount(senderAccountType);
                        double prevSenderBalance = senderAccount.getAccountBalance();
                        String roundedPrevSenderBalance = "$" + String.format("%.2f", prevSenderBalance);
                        // Process the receiver's balance of the specified account before the payment is completed.
                        String receiverFullName = receiver.getFirstName() + " " + receiver.getLastName();
                        Account receiverAccount = receiver.getCustomerAccount(receiverAccountType);
                        double prevReceiverBalance = receiverAccount.getAccountBalance();
                        String roundedPrevReceiverBalance = "$" + String.format("%.2f", prevReceiverBalance);
                        // Verify whether it is possible to complete the payment of money.
                        if(senderAccount.paySomeone(receiver, receiverAccountType, actionAmount)){
                            // Process the sender's balance of the specified account after the payment is completed.
                            double newSenderBalance = senderAccount.getAccountBalance();
                            String roundedNewSenderBalance = "$" + String.format("%.2f", newSenderBalance);
                            // Process the receiver's balance of the specified account after the payment is completed.
                            double newReceiverBalance = receiverAccount.getAccountBalance();
                            String roundedNewReceiverBalance = "$" + String.format("%.2f", newReceiverBalance);
                            // Add the payment between both customers to their list of transactions.
                            String senderTransaction = "Payment" + "\t" + senderAccountType + "\t" +
                                                        roundedPrevSenderBalance + "\t" + "-" + roundedAmount + "\t" +
                                                        roundedNewSenderBalance;
                            sender.getTransactionsList().add(senderTransaction);
                            String receiverTransaction = "Payment" + "\t" + receiverAccountType + "\t" +
                                                          roundedPrevReceiverBalance + "\t" + "+" + roundedAmount +
                                                         "\t" + roundedNewReceiverBalance;
                            receiver.getTransactionsList().add(receiverTransaction);
                            // Log the payment between the two customers to the transactions log.
                            logTransaction(logFile, sender, senderTransaction);
                            logTransaction(logFile, receiver, receiverTransaction);
                            // Display a message that indicates the successful payment.
                            System.out.println("Payment of " + roundedAmount + " from " + senderFullName + " to " +
                                                receiverFullName + " successful!");
                        }else{
                            System.out.println("Payment of " + roundedAmount + " failed! Sender's " +
                                                senderAccountType + " account lacks funds to complete payment.");
                        }
                    }else{
                        System.out.println("Payment failed due to an invalid bank customer.");
                    }
                }
            }
        }
        br.close();
        System.out.println();
    }

    /**
     * A method that writes a bank statement for a customer at the request of a bank manager who searches
     * for said customer by name. Taken from Abel Carrasco.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @throws IOException if the bank statement file cannot be found or accessed.
     * @throws ArrayIndexOutOfBoundsException if the index of an array cannot be accessed.
     */
    public static void writeBankStatement(HashMap<String, Customer> customersDatabase) throws IOException,
            ArrayIndexOutOfBoundsException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("For whom would you like to write a bank statement?");
        System.out.print("First name: ");
        String firstName = userInput.next();
        System.out.print("Last name: ");
        String lastName = userInput.next();

        // Search for the customer whose bank statement will be written.
        Customer bankStatementCustomer = searchCustomer(customersDatabase,firstName,lastName);

        // Verify that the entered customer exists in the bank customers database.
        if(bankStatementCustomer != null) {
            // Create the customer's bank statement file.
            String validFirstName = bankStatementCustomer.getFirstName();
            String validLastName = bankStatementCustomer.getLastName();
            File bankStmtFile = new File("Bank Statement " + validFirstName + " " + validLastName + ".txt");
            clearOutputFile(bankStmtFile);

            bankStatementCustomer.printBankStatement(bankStmtFile);

            System.out.println("The bank statement for " + validFirstName + " " + validLastName + " has been " +
                               "successfully created.\n");
        }else{
            System.out.println("There is no such customer for which to write a bank statement.\n");
        }

    }

    /**
     * A method that displays a customer's transaction to the transaction log. Taken from Alonso Monarrez.
     * @param logFile The file to which the transaction is logged.
     * @param currentCustomer The customer that makes the transaction.
     * @param transaction The transaction made by the customer.
     * @throws IOException if the log file cannot be found or accessed.
     */
    public static void logTransaction(File logFile,Customer currentCustomer,String transaction)
            throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
        // Create a header that shows the different aspects of the customer's transaction.
        String header = String.format("%-25s", "Name") + String.format("%-18s", "Action") +
                        String.format("%-18s", "Account") + String.format("%-18s", "Start Balance") +
                        String.format("%-18s", "Action Amount") + String.format("%-18s", "End Balance");
        // Write the header to the transactions log file.
        bw.write(header);
        bw.newLine();
        // Write the customer's transaction to the file.
        String loggedTransaction = currentCustomer.printTransaction(transaction);
        bw.write(loggedTransaction);
        bw.newLine();
        bw.newLine();
        bw.close();
    }

    /**
     * A method that updates the original balance sheet by displaying all current customers' updated account
     * information to a new balance sheet. Taken from Alonso Monarrez.
     * @param balanceSheet The file in which the new balance sheet is written.
     * @param customersDatabase The hash map containing all of the customers associated with the bank.
     * @throws IOException if the new balance sheet file cannot be found or accessed.
     */
    public static void updateBalanceSheet(File balanceSheet,HashMap<String, Customer> customersDatabase)
        throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(balanceSheet, true));
        // Create a header that indicates the different aspects of each customer's banking account.
        String header = String.format("%-14s", "FirstName") + String.format("%-14s", "LastName") +
                        String.format("%-25s", "DateOfBirth") + String.format("%-25s", "IdentificationNumber") +
                        String.format("%-44s", "Address") + String.format("%-18s", "PhoneNumber") +
                        String.format("%-37s", "Email") + String.format("%-32s", "Password") +
                        String.format("%-23s", "CheckingAcctNumber") + String.format("%-24s", "CheckingAcctBalance") +
                        String.format("%-22s", "SavingsAcctNumber") + String.format("%-23s", "SavingsAcctBalance") +
                        String.format("%-21s", "CreditAcctNumber") + String.format("%-22s", "CreditAcctBalance") +
                        String.format("%-14s", "CreditMax");
        // Write the header to the updated balance sheet file.
        bw.write(header);
        bw.newLine();

        // Store all of the customers within the bank's database in alphabetical order.
        TreeMap<String, Customer> sortedCustomers = new TreeMap<>(customersDatabase);
        // Traverse through the hash map to display the updated account information for each customer in the balance
        // sheet file.
        for(Map.Entry<String, Customer> currCustomer : sortedCustomers.entrySet()){
            String updatedCustomer = currCustomer.getValue().displayUpdates();
            bw.write(updatedCustomer);
            bw.newLine();
        }
        bw.close();
    }

    /**
     * A method that clears the contents of teh given file. Taken from Abel Carrasco.
     * @param outputFile The file to be cleared of its contents.
     * @throws IOException if the given file cannot be found or accessed.
     */
    public static void clearOutputFile(File outputFile) throws IOException{
        FileWriter fw = new FileWriter(outputFile, false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }

    /**
     * The main method in which users, such as a customer and a manager, interact with the program to perform
     * various activities that are typical of a banking system, such as making monetary transactions, creating
     * new bank users, inquiring account information, processing transactions from a file, and writing bank statements.
     * These activities serve to display and update the bank's data based on the users' selection of activity.
     * Taken from Alonso Monarrez.
     * @param args The system arguments.
     */
    public static void main(String[] args){
        try{
            // Read the bank users file and store their account information
            // in a hash map of Customer objects that have their kek set to each
            // customer's full name.
            String currBankUsersInputFile = "Bank Users 4.txt";
            HashMap<String, Customer> currBankCustomers = storeCustomersInfo(currBankUsersInputFile);

            // Initialize the String variable containing the file to be read
            // in order to process all transactions in the file.
            String transactionsInputFile = "Transaction Actions.txt";

            // Create the file that logs all successful transactions within a single run of the program.
            File transactionsLog = new File("Transaction Log.txt");
            // Clear the contents of the file above to overwrite the transactions written during
            // the program's previous run.
            clearOutputFile(transactionsLog);

            Scanner userInput = new Scanner(System.in);

            System.out.println("Welcome to the ACAM University Bank!\n");

            String selection = "";
            while(!selection.equals("QUIT")){
                System.out.println("Who is currently using this system?");
                System.out.println("1. A bank customer");
                System.out.println("2. A bank manager\n");

                System.out.println("Type QUIT to exit.\n");

                System.out.print("Enter your choice of user: ");
                selection = userInput.next();
                System.out.println();

                if(selection.equals("1")){
                    System.out.println("ENTER THE FOLLOWING INFORMATION TO ACCESS BANK ACCOUNT");
                    System.out.print("First name: ");
                    String inputFirstName = userInput.next();
                    System.out.print("Last name: ");
                    String inputLastName = userInput.next();

                    // Given the input above, search for the customer requesting to perform a bank action.
                    Customer foundCustomer = searchCustomer(currBankCustomers, inputFirstName, inputLastName);

                    if(foundCustomer != null){
                        try{
                            System.out.print("Password: ");
                            String inputPassword = userInput.next();
                            // Verify that the entered password matches that of the specified customer.
                            if(foundCustomer.getPassword().equals(inputPassword)){
                                boolean customerContinue;
                                do{
                                    String customerFullName = foundCustomer.getFirstName() + " " +
                                                              foundCustomer.getLastName();
                                    System.out.print("Hello, " + customerFullName + "! ");
                                    System.out.println("What would you like to do today?");
                                    System.out.println("A. Inquire balance");
                                    System.out.println("B. Deposit money");
                                    System.out.println("C. Withdraw money");
                                    System.out.println("D. Transfer money");
                                    System.out.println("E. Pay someone\n");

                                    System.out.print("Select your choice of action: ");
                                    String customerSelection = userInput.next();
                                    System.out.println();

                                    if(customerSelection.equals("A")){
                                        requestBalanceInquiry(foundCustomer,transactionsLog);
                                    }else if(customerSelection.equals("B")){
                                        requestDeposit(foundCustomer,transactionsLog);
                                    }else if(customerSelection.equals("C")){
                                        requestWithdrawal(foundCustomer,transactionsLog);
                                    }else if(customerSelection.equals("D")){
                                        requestTransfer(foundCustomer,transactionsLog);
                                    }else if(customerSelection.equals("E")){
                                        requestPayment(foundCustomer,currBankCustomers,transactionsLog);
                                    }else{
                                        System.out.print("ERROR: Invalid bank customer action selection! ");
                                        System.out.println("Verify action selection and try again.\n");
                                    }
                                    System.out.print("Would you like to perform another action? (true/false): ");
                                    customerContinue = userInput.nextBoolean();
                                }while(customerContinue);
                            }else{
                                String msg = "ERROR: Invalid password! Verify the entered password and try again. " +
                                             "Returning to the main menu.";
                                throw new InvalidPasswordException(msg);
                            }
                        }catch(InvalidPasswordException e){
                            System.out.println(e.toString());
                        }catch(InputMismatchException e){
                            System.out.println("ERROR: Invalid input! Returning to the main menu.");
                            userInput.nextLine();
                        }catch(IOException e){
                            System.out.print("ERROR: Unable to access the file needed to log transactions! ");
                            System.out.println("Verify that all files are valid. Returning to the main menu.");
                            userInput.nextLine();
                        }finally{
                            System.out.println();
                        }
                    }else{
                        System.out.println("ERROR: Invalid customer! Verify the entered name and try again.\n");
                    }
                }else if(selection.equals("2")){
                    try{
                        boolean managerContinue;
                        do{
                            System.out.println("Hello, bank manager! What would you like to do today?");
                            System.out.println("A. Add a new bank user");
                            System.out.println("B. Inquire account by name");
                            System.out.println("C. Inquire account by type and number");
                            System.out.println("D. Inquire and print all accounts");
                            System.out.println("E. Use the transaction reader to process all transactions from a file");
                            System.out.println("F. Write a bank statement for a specific customer\n");

                            System.out.print("Select your choice of action: ");
                            String managerSelection = userInput.next();
                            System.out.println();

                            if(managerSelection.equals("A")){
                                addNewBankUser(currBankCustomers);
                            }else if(managerSelection.equals("B")){
                                inquireAccountByName(currBankCustomers);
                            }else if(managerSelection.equals("C")){
                                inquireAccountByTypeAndNumber(currBankCustomers);
                            }else if(managerSelection.equals("D")){
                                printAllAccounts(currBankCustomers);
                            }else if(managerSelection.equals("E")){
                                readTransactions(transactionsInputFile,currBankCustomers,transactionsLog);
                            }else if(managerSelection.equals("F")){
                                writeBankStatement(currBankCustomers);
                            }else{
                                System.out.print("ERROR: Invalid bank manager action selection! ");
                                System.out.println("Verify action selection and try again.\n");
                            }
                            System.out.print("Would you like to perform another action? (true/false): ");
                            managerContinue = userInput.nextBoolean();
                        }while(managerContinue);
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.print("ERROR: Unable to access information stored in an array! ");
                        System.out.print("Verify the means of accessing information in an array. ");
                        System.out.println("Returning to the main menu.");
                        userInput.nextLine();
                    }catch(InputMismatchException e){
                        System.out.println("ERROR: Invalid input! Returning to the main menu.");
                        userInput.nextLine();
                    }catch(IOException e){
                        System.out.print("ERROR: Unable to access the files needed to read transactions, log " +
                                         "transactions and/or write a bank statement! ");
                        System.out.println("Verify that all files are valid. Returning to the main menu.");
                        userInput.nextLine();
                    }finally{
                        System.out.println();
                    }
                }else if(selection.equals("QUIT")){
                    System.out.println("Thank you for visiting the ACAM Bank. Have a nice day!");
                }else{
                    System.out.print("ERROR: Invalid user selection! ");
                    System.out.println("Enter the correct number designated to its corresponding user.\n");
                }
            }
            // Once the user quits the program, create the file needed to update the balance sheet.
            File updatedBalanceSheet = new File("New Balance Sheet.txt");
            // Clear the contents of file above to overwrite the updated balance sheet of the program's previous run.
            clearOutputFile(updatedBalanceSheet);
            // Update the balance sheet file for the current run of the program.
            updateBalanceSheet(updatedBalanceSheet,currBankCustomers);
        }catch(IOException e){
            System.out.print("ERROR: Unable to access the files needed to store customers' account information " +
                             "and/or update the balance sheet! ");
            System.out.println("Verify that all files are valid and re-run the program.\n");
        }
    }
}