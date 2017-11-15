class Person {

   // Properties of the class...
   private String name;
   public int    age;
   private String favColour;

   // Constructor of the class...
   //public Person(String aName, int anAge, String aColour) {
   //   name = aName;
   //   age  = anAge;
   //   favColour = aColour;
   //}

   // Methods of the class...
   public void talk() {
      System.out.println("Hi, my name's " + name);
      System.out.println("and my age is " + age);
      System.out.println("My favourite colour is of course " + favColour);
      commentAboutAge();
      System.out.println();
   }
   public void commentAboutAge() {
      if (age < 5) {
         System.out.println("baby");
      }
      if (age == 5) {
         System.out.println("time to start school");
      }
      if (age > 60) {
         System.out.println("old person");
      }
   }

   // Adds one age to a Person's age
   public void growOlder() {
      age ++;
   }

   // Adds the specified amount of years to a Person's age
   public void growOlderBy(int numYears) {
      age += numYears;
   }

   // Grant knighthood to a person
   public void giveKnighthood() {
      name = "Sir " + name;
   }
}

class PersonTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      // create Persons with null parameters
      Person ls = new Person();
      Person wp = new Person();

      wp.growOlder();
      wp.giveKnighthood();

      ls.growOlderBy(10);

      ls.talk();
      wp.talk();

      // access Skywalker's age directly
      System.out.println("Luke Skywalker is " + ls.age + " years old.");

      // Interestingly, adding things to null seems to
      // result in null becoming the thing that was added.
      // Ex: null + 10 -> 10
   }

}

