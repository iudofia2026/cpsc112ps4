
//*******************************************************************
//
//   File: Cashier.java          Assignment No.: 4
//
//   Author: iau2
//
//   Class: CPSC 112
// 
//   Time spent on this problem: 2 hours
//   --------------------
//      Please give a description about your design. 
//
//*******************************************************************
import java.util.*;


public class Cashier {

    public static String correctEnglish(int numBanknotes, String banknote){
        if (numBanknotes != 1){
            banknote += "s";
        }

        return banknote;
    }

    public static String getChange(double owed, double paid) {
        //creating a variable that stores the value of change and making it 
        //an int value that counts total cents so it is easier to do int division and mod
        double change = paid - owed;
        change*=1000;
        change = Math.round(change);
        change /= 10;
        int totalCents = (int)change;
        
        //creating int values that will store the value of each type of banknotes and setting them all to zero

        int fiftyDollarBills = 0;
        int twentyDollarBills = 0;
        int tenDollarBills = 0;
        int fiveDollarBills = 0;
        int oneDollarBills = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;

        //the if statements descend from the largest banknote to the smallest bank note
        //and if an amount of change is able to be taken out using this banknote, the number will be 
        //the totalCents/banknote and total cents will be changed to the remaning amount using mod
        if (totalCents/5000 >= 1){
            fiftyDollarBills = totalCents/5000;
            totalCents %= 5000;
        }
        if (totalCents/2000 >= 1){
            twentyDollarBills = totalCents/2000;
            totalCents %= 2000;
        }
        if (totalCents/1000 >= 1){
            tenDollarBills = totalCents/1000;
            totalCents %= 1000;
        }
        if (totalCents/500 >= 1){
            fiveDollarBills = totalCents/500;
            totalCents %= 500;
        }
        if (totalCents/100 >= 1){
            oneDollarBills = totalCents/100;
            totalCents %= 100;
        }
        if (totalCents/25 >= 1){
            quarters = totalCents/25;
            totalCents %= 25;
        }
        if (totalCents/10 >= 1){
            dimes = totalCents/10;
            totalCents %= 10;
        }
        if (totalCents/5 >= 1){
            nickels = totalCents/5;
            totalCents %= 5;
        }
        if (totalCents/1 >= 1){
            pennies = totalCents/1;
            totalCents %= 1;
        }

        if (totalCents > 0){
            System.out.print("--------------------------------ERROR IN CALCULATION--------------------------------");
            System.out.printf("--------------------------------%d CENTS LEFT--------------------------------", totalCents);
        }

        //put all the values through the correctenglish method and storing their outputs as strings
        //couldn't use method on penny because pluralization is different so had to write it its own if-else statement
        String fiftyDollarBillString = correctEnglish(fiftyDollarBills, "50 dollar bill");
        String twentyDollarBillString = correctEnglish(twentyDollarBills, "20 dollar bill");
        String tenDollarBillString = correctEnglish(tenDollarBills, "10 dollar bill");
        String fiveDollarBillString = correctEnglish(fiveDollarBills, "5 dollar bill");
        String oneDollarBillString = correctEnglish(oneDollarBills, "1 dollar bill");
        String quarterString = correctEnglish(quarters, "quarter");
        String dimeString = correctEnglish(dimes, "dime");
        String nickelString = correctEnglish(nickels, "nickel");
        String penniesString;
        if (pennies != 1){
            penniesString = "pennies";
        }
        else{
            penniesString = "penny";
        }
        
        //creating a formatted string to print out all the correct english banknote amounts and the int values of each that have been stored throughout the method
        String changeOwed = String.format("%d "+ fiftyDollarBillString + "\n%d "+ twentyDollarBillString + "\n%d "+ tenDollarBillString + "\n%d "+ fiveDollarBillString + "\n%d "+ oneDollarBillString + "\n%d "+ quarterString + "\n%d "+ dimeString + "\n%d "+ nickelString +"\n%d "+ penniesString+"\n", fiftyDollarBills, twentyDollarBills, tenDollarBills, 
        fiveDollarBills, oneDollarBills, quarters, dimes, nickels, pennies);


        //return the formatted string which gets printed by main
        return changeOwed;
        


    }

    public static void main(String[] args) {
        System.out.println("Welcome to Cashier!");
        System.out.println();

        double unitPrice;
        double quantity;
        double taxRate;
        double paidAmount;
        Scanner in = new Scanner(System.in);

        //the lines above initiate variables and scanner for input
        //the lines below take in the user input to get the price that the user will owe
        System.out.print("Enter unit price: ");
        unitPrice = in.nextDouble();
        System.out.print("Enter quantity: ");
        quantity = in.nextDouble();
        double totalPrice = unitPrice * quantity;
        System.out.print("Enter sales tax rate: ");
        taxRate = in.nextDouble();

        double taxRatePercent = taxRate / 100;
        double owedAmount = totalPrice + totalPrice * taxRatePercent;

        //formatted print to show how much the user owes
        System.out.printf("The total owed amount is $%.2f ($%.2f plus %.2f%% tax) \n", owedAmount, totalPrice, taxRate);

        // rounding owed amount to 100s place to get correct cent value for comparison
        // with user input of amount paid
        owedAmount = Math.round(owedAmount * 100);
        owedAmount /= 100;

        //taking in the amount the user wants to pay
        System.out.print("Enter the amount paid: ");
        paidAmount = in.nextDouble();

        //three if statements that handle each of the three possibilities for the user's input
        //if the owedAmount is less than paidAmount, getChange() is called
        if (owedAmount > paidAmount) {
            System.out.printf("You still owe $%.2f", owedAmount - paidAmount);
        }
        if (owedAmount < paidAmount) {
            System.out.printf("Your change of $%.2f is given as: \n", paidAmount-owedAmount);
            System.out.print(getChange(owedAmount, paidAmount));
        }
        if (owedAmount == paidAmount) {
            System.out.print("Thank you for paying the exact amount!");
        }

        in.close();
    }

}
