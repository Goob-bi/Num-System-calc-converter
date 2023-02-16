/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numsystemconverter;

import java.util.Scanner;

/**
 *
 * @author andreas lees
 */
public class NumSystemConverter {
        static Scanner scnr = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
        
    }
    public static void menu() {
        String userIn;
        System.out.println("[C]onvert a number"
                + "\n[A]dd or subtract numbers"
                + "\n[Q]uit");
        userIn = scnr.next();
        switch(userIn) {
            case "C":
                convert();
            break;
            case "A":
                addition();
            break;
            case "Q":
                System.exit(0);
            break;
            default:
                System.out.println("Not a valid option");
                menu();
             }
        
    }
    public static void addition() {
                //take user input about base system
                //take three strings, first number, operator +- and the last number
                //convert both numbers to decimal, perform the operation
                //convert the result back to original base system
                int num1 = 0;
                int num2 = 0;
                int result = 0;
                int firstBase = 0;
                System.out.println("Type the base number");
                scnr.nextLine();
                String base = scnr.nextLine();
                try {
                    firstBase = Integer.parseInt(base);
                } catch(Exception e) {
                    System.err.println("Thats not an integer!");
                    System.out.println("----------------------------");   
                    menu();
                }
                System.out.println("Type the first number, with the value of each position seperated by spaces, remember \neach spot cannot hold a number equal to or larger than the base system");
                System.out.println("If it is a negative number first type a - followed by a space");
                
                String numbers = scnr.nextLine();
                System.out.println("Type the operator (+ or -)");
                String operator = scnr.nextLine();
                System.out.println("Type the second number, with the value of each position seperated by spaces, remember \neach spot cannot hold a number equal to or larger than the base system");
                
                String numbers2 = scnr.nextLine();
                //step through the string and seperate the two numbers and operator
                //convert each number to base 10, if subtracting make the second number negative
                //add the two numbers
                //convert the result back to original base
                
                //convert to decimal
                num1 = convertDec(firstBase, numbers);
                switch (operator) {
                    case "+":
                        num2 = convertDec(firstBase, numbers2);
                    break;
                    case "-":
                        num2 = -1 * convertDec(firstBase, numbers2);
                    break;
                    default:
                    break;
                }
                //sum the results
                result= num1 + num2;
                //turn the result into a string for the converter to use
                String resultString = separateNumbers(result);
                //convert from decimal back to original base
                resultString= convertAny(10, resultString, firstBase);
                if (resultString.isBlank())
                        resultString = "0";
                                
                System.out.println("Answer in base "+ firstBase + ": " + resultString);
                System.out.println("----------------------------");   
                menu();
        
    }
    public static String separateNumbers(int number) {
        String numberString = Integer.toString(number);
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < numberString.length(); i++) {
            char currentChar = numberString.charAt(i);
            resultBuilder.append(currentChar);
            if (i != numberString.length() - 1) {
                resultBuilder.append(" ");
            }
        }

        return resultBuilder.toString();
    }
    public static void convert() {
        
        
                int firstBase = 0;
                int secondBase = 0;
                System.out.println("Type the base number");
                scnr.nextLine();
                String base = scnr.nextLine();
                try {
                    firstBase = Integer.parseInt(base);
                } catch(Exception e) {
                    System.err.println("Thats not an integer!");
                    System.out.println("----------------------------");   
                    menu();
                }
                System.out.println("Type the value of each position seperated by spaces, remember \neach spot cannot hold a number equal to or larger than the base system");
                System.out.println("If it is a negative number first type a - followed by a space");
                String numbers = scnr.nextLine();
                
                System.out.println("Type the new base number to convert to");
                String newBase = scnr.nextLine();
                try {
                    secondBase = Integer.parseInt(newBase);
                } catch(Exception e) {
                    System.err.println("Thats not an integer!");
                    System.out.println("----------------------------");   
                    menu();
                }
                if (firstBase <= 1 || secondBase <= 1) {
                        System.err.println("Your bases must be equal or above 2!");
                        System.out.println("----------------------------");   
                        menu();
                    
                }
                System.out.println("Answer in base "+ secondBase + ": " + convertAny(firstBase, numbers, secondBase));
                System.out.println("----------------------------");   
                menu();
        
    }
    public static String convertBinary(String numbers) {
        //takes a binary input and asks if it is in sign magnitude or twos complement
        //SM if positive leave alone
        //SM if negative with (-) leave alone
        //SM if negative with (1) as MSB then change it to (-) (converter can handle - but not 1)
        //TC if positive leave alone (same as SM)
        //TC if negative convert to SM
        //TC if negative with (1) as MSB then change it to (-) convert to SM
        
            System.out.println("Looks like your input is binary, is it in \n[S]ign magnitude or [T]wo's Complement?"); 
            System.out.println("----------------------------"); 
            String choice = scnr.nextLine();
            switch (choice) {
                case "S":
                    //converts leading (1) to (-)
                    if (String.valueOf(numbers.charAt(0)).equals("1")) {
                        numbers = "-" + numbers.substring(1);
                    }
                break;
                    
                case "T":
                    System.out.println(String.valueOf(numbers.charAt(0)) + String.valueOf(numbers.charAt(0)).equals("1"));
                    if (String.valueOf(numbers.charAt(0)).equals("1") || String.valueOf(numbers.charAt(0)).equals("-")) {
                        //replaces (1) in MSB to (-)
                        numbers = "-" + numbers.substring(1);
                        //converts to sign magnitude
                        boolean foundOne1 = false;
                        String[] parts = numbers.split(" ");
                        numbers = "";
                        for (int i = parts.length - 1; i >= 0; i--) {
                            if (foundOne1 == true) {
                                switch (parts[i]) {
                                    case "0":
                                        parts[i] = "1";
                                    break;
                                    case "1":
                                        parts[i] = "0";
                                    break;
                                    case "-":
                                    break;
                                    default:
                                        System.out.println("you shouldnt be here");
                                        menu();
                                    break;
                                }
                            } 
                            numbers = parts[i] + " " + numbers;
                            if (foundOne1 == false && parts[i].contains("1")) {
                                foundOne1 = true;
                            }
                        }
                        break;
                    }
                    break;
                    
                default:
                    System.out.println("Not a valid option, defaulting to sign magnitude"); 
                    System.out.println("----------------------------");
                break;
            }
            return numbers;
    }
    public static String convertAny(int firstBase, String numbers, int secondBase) {
        //take any base as string seperated by space
        //search through the spaces and convert to decimal
        //convert decimal to whichever base
        //-----------------------
        String sign = "";
        String signBinary = "0 ";
        String result = "";
        //logic for a binary input
        
        if (firstBase == 2) {
            numbers = convertBinary(numbers);
        }
        //converts string numbers into a decimal number (base 10)
        //note: assumes sign magnitude form for binary
        int dividend = convertDec(firstBase, numbers);
        //logic for negative numbers
        if (dividend < 0 ) {
            dividend = dividend * -1;
            sign = "- ";
            signBinary = "1 ";
        }
        int divisor = secondBase;
        int quotient = dividend;
        int remainder = 0;
        //converts the new decimal number into the desired base
        while (quotient != 0){
            quotient = dividend / divisor;
            remainder = dividend % divisor;
            dividend = quotient;
                result = remainder + " " + result;
            
        }
        if (secondBase == 2) {
            System.out.println("Looks like you want binary, would you like the answer in \n[S]ign magnitude or [T]wo's Complement?"); 
            System.out.println("----------------------------"); 
            String choice = scnr.nextLine();
            switch (choice) {
                case "S":
                    return signBinary + result;
                    
                case "T":
                    if (signBinary == "1 ") {
                        boolean foundOne = false;
                        String[] parts = result.split(" ");
                        result = "";
                        for (int i = parts.length - 1; i >= 0; i--) {
                            if (foundOne == true) {
                                switch (parts[i]) {
                                    case "0":
                                        parts[i] = "1";
                                    break;
                                    case "1":
                                        parts[i] = "0";
                                    break;
                                    default:
                                        System.out.println("you shouldnt be here");
                                        menu();
                                    break;
                                }
                            } 
                            result = parts[i] + " " + result;
                            if (foundOne == false && parts[i].contains("1")) {
                                foundOne = true;
                            }
                        }
                        return signBinary + result;
                    }
                    return signBinary + result;
                    
                default:
                    System.out.println("Not a valid option, defaulting to sign magnitude"); 
                    System.out.println("----------------------------");
                    return signBinary + result;
            }
            
        } else {
            return sign + result;
        }
    }
    public static int convertDec(int base, String numbers) {
        //converts any base into decimal equivalent
        String[] parts = numbers.split(" ");
        int signChange = 1;
        int result = 0;
        for (int i = 0; i < parts.length; i++) {
            if (i==0 && parts[i].matches("-")) {
                signChange = -1;
            } else {
            try {
                //add handling of negative numbers
                int num = Integer.parseInt(parts[i]);
                if (num >= base) {
                            System.err.println("Each position cannot be the same or greater than your base.\nBase was: " + base + ", but one spot held value: " + parts[i]);
                            Thread.sleep(100);
                            System.out.println("----------------------------");   
                            menu();

                }
                result += num * Math.pow(base, parts.length - i - 1);
            } catch(Exception e) {
                        System.err.println("One of your inputs was not an integer: " + parts[i]);
                        System.out.println("----------------------------");   
                        menu();
            }
            }
        }
    return result * signChange;
    }
}
