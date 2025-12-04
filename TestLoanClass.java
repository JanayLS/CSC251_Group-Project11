package groupproject2025;
import java.util.Scanner;

public class TestLoanClass {
  /** Main method */
  public static void main(String[] args) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Enter yearly interest rate
    System.out.print(
      "Enter annual interest rate, for example, 8.25: ");
    double annualInterestRate = input.nextDouble();

    // Enter number of years
    System.out.print("Enter number of years as an integer: ");
    int numberOfYears = input.nextInt();

    // Enter loan amount
    System.out.print("Enter loan amount, for example, 120000.95: ");
    double loanAmount =  input.nextDouble();
    
    // Enter extra payment amount
    System.out.print("Enter extra payment amount, for example, 1000.00: ");
    double extraPaymentAmount =  input.nextDouble();
	
	// Close the scanner
	input.close();

    // Create Loan object
    Loan loan =
      new Loan(annualInterestRate, numberOfYears, loanAmount);

	System.out.println();
    // Display loan date, monthly payment, and total payment, and total interest
    System.out.printf("The loan was created on %s%n" +
      "The monthly payment is %.2f%nThe total payment is %.2f%nThe total interest is %.2f%n%n",
      loan.getLoanDate().toString(),
      loan.getMonthlyPayment(), 
      loan.getTotalPayment(),
      loan.getTotalInterest());
    
	// Show different interest amounts with different extra payment options
	if(extraPaymentAmount > 0) {
		extraPayFirstMonth(loan, extraPaymentAmount);
		System.out.println();
		extraPayEveryMonth(loan, extraPaymentAmount);
	}
	// Tell the user this if they aren't paying extra per month
	else {
		System.out.println("Total interest must be payed.");
	}
  }
  
  /** Extra payment first month only */
  public static void extraPayFirstMonth(Loan l, double extraPay) {
	  System.out.printf("Extra payment only first month $%.2f%n", extraPay);
	  double myLoanAmount = l.getLoanAmount();
	  double myMonthlyInterestRate = l.getAnnualInterestRate()/1200;
	  double myMonthlyPayment = l.getMonthlyPayment();
	  int month = 0;
	  double interest = 0.0;
	  double principal = 0.0;
	  double totalInterest = 0.0;

	  while (myLoanAmount >= myMonthlyPayment) {
		// Calculate this month's interest and add it to the total interest payed
		interest = myLoanAmount * myMonthlyInterestRate;
		totalInterest += interest;

		// Calculate the principal payment for this month and subtract it from
		// the amount left to be payed
		principal = myMonthlyPayment - interest;
		myLoanAmount -= principal;

		// Subtract the extra payment from the loan amount without interest if the
		// first month
		if(month == 0) {
			myLoanAmount -= extraPay;
		}
		
		// Increment month
		++month;

	  }
	  if (myLoanAmount < myMonthlyPayment) {
		// Calculate this month's interest and add it to the toal interest payed
		interest = myLoanAmount * myMonthlyInterestRate;
		totalInterest += interest;
		// Increment month
		++month;
	  }

	  double interestSaved = l.getTotalInterest() - totalInterest;
	  /*
	  System.out.println("total interest " + totalInterest);
	  System.out.println("interest saved " + interestSaved);
	  System.out.println("total month " + month);
	  */
	  System.out.printf("Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",
			  totalInterest, interestSaved, month);	  
  }

  /** Extra payment every month */
  public static void extraPayEveryMonth(Loan l, double extraPay) {
	  System.out.printf("Extra payment every month $%.2f%n", extraPay);
	  double myLoanAmount = l.getLoanAmount();
	  double myMonthlyInterestRate = l.getAnnualInterestRate()/1200;
	  double myMonthlyPayment = l.getMonthlyPayment();
	  int month = 0;
	  double interest = 0.0;
	  double principal = 0.0;
	  double totalInterest = 0.0;
	  
	  while (myLoanAmount >= (myMonthlyPayment + extraPay)) {
		// Calculate this month's interest and add it to the total interest payed
		interest = myLoanAmount * myMonthlyInterestRate;
		totalInterest += interest;

		// Calculate the principal payment for this month and subtract it and
		// the extra payment from the amount left to be payed
		principal = myMonthlyPayment - interest;
		myLoanAmount -= (principal + extraPay);

		// Increment month
		++month;

	  }
	  while (myLoanAmount >= myMonthlyPayment) {
		// Calculate this month's interest and add it to the total interest payed
		interest = myLoanAmount * myMonthlyInterestRate;
		totalInterest += interest;

		// Calculate the principal payment for this month and subtract it from
		// the amount left to be payed
		principal = myMonthlyPayment - interest;
		myLoanAmount -= principal;

		// Increment month
		++month;

	  }
	  if (myLoanAmount < myMonthlyPayment) {
		  // Calculate this month's interest and add it to the total interest payed
		  interest = myLoanAmount * myMonthlyInterestRate;
		  totalInterest += interest;
		  // Increment month
		  ++month;
	  }
	  double interestSaved = l.getTotalInterest() - totalInterest;
	  System.out.printf("Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",
			  totalInterest, interestSaved, month);	  
  }
}
/*
Enter annual interest rate, for example, 8.25: 5.75
Enter number of years as an integer: 15
Enter loan amount, for example, 120000.95: 25000
The loan was created on Sat Oct 21 08:29:24 EDT 2023
The monthly payment is 207.60
The total payment is 37368.45
*/
/*
Enter annual interest rate, for example, 8.25: 5
Enter number of years as an integer: 4
Enter loan amount, for example, 120000.95: 20000
The loan was created on Sun Nov 09 13:57:33 EST 2025
The monthly payment is 460.59
The total payment is 22108.12
*/