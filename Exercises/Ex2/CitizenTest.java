class Citizen {

   // Properties of the class...
   private String name;
   private int    salary;
   private int    savings;
   private int    loan;

   // Constructor of the class...
   public Citizen(String aName, int aSalary, int aSavings, int aLoan) {
      name    = aName;
      salary  = aSalary;
      savings = aSavings;
      loan    = aLoan;
   }

   public Citizen(String aName){
      name = aName;
   }

   // Methods of the class...
   public int getSalary() {
      return salary;
   }
   public void setSalary(int aSalary) {
      salary = aSalary;
   }
   public void salaryRise(int amount) {
      salary += amount;
   }
   public int netWorth() {
      return savings - loan;
   }
   public String toString() {
      return "name: " + name + " \nsalary: " + salary
         + " \nsavings: " + savings + " \nloan: " + loan;
   }
}

class CitizenTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      Citizen e = new Citizen("Ernie", 50000, 2000,   0);
      Citizen b = new Citizen("Bert", 100000,10000,5000);

      // Ernie finds a job in IT at this point.
      e.salaryRise(10000);

      System.out.println("Ernie's salary is: " + e.getSalary());
      System.out.println("Ernie's net worth is " + e.netWorth());

      /* Answer to Q4: Because there is nothing inherently wrong
         about executing the method, and for all Java knows there
         could be a side-effect to running the method, such as
         a print statement in the method. Or maybe we want to
         sometimes use the return value, and sometimes not. */

      System.out.println(e.toString());

      Citizen f = new Citizen("Fred");

      // apparently ints default to 0?
      System.out.println(f.toString());
   }
}
