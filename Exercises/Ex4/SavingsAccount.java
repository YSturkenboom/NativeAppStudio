
public class SavingsAccount {

   private int balance;

   public SavingsAccount() {
      balance  = 0;
   }

   public SavingsAccount(int initialBalance) {
      balance = initialBalance;
   }

   public void greet() {
      System.out.println("Hi there.");
   }

   public void deposit(int howMuch){
      if (howMuch >= 0) {
         balance = balance + howMuch;
      }
      else {
         System.out.println("ERROR: specifed amount is negative.");
      }
   }

   public void withdraw(int howMuch){
      if (howMuch >= 0) {
         if (balance >= howMuch) {
            balance = balance - howMuch;
         }
      }
      else {
         System.out.println("ERROR: specifed amount is negative.");
      }
   }

   public void showBalance() {
      System.out.println(balance);
   }
}
