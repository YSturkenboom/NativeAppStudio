class Flea {

   // Properties of the class...
   public String name;

   // Constructor of the class...
   public Flea(String aName) {
      name = aName;
   }

   // Methods of the class...
   public String toString() {
      return "I am a flea called " + name;
   }

}

class Dog {

   // Properties of the class...
   public String name;
   private int    age;
   public Flea   dogsFlea;

   // Constructor of the class...
   public Dog(String aName, int anAge, Flea aFlea) {
      name     = aName;
      age      = anAge;
      dogsFlea = aFlea;
   }

   public String toString() {

      // Just printing the Flea object apparently results in the
      // Flea object being expressed as a String, which automatically
      // calls the Flea's toString method.
      return "I am a dog called " + name + ".\nI am " + age
         + " years old.\nMy flea buddy's name is " + dogsFlea.name
            + ".\n";
   }

}

class DogOwner {

   // Properties of the class...
   private String name;
   private int    salary;
   public Dog    ownersDog;

   // Constructor of the class...
   public DogOwner(String aName, int aSalary, Dog aDog) {
      name      = aName;
      salary    = aSalary;
      ownersDog = aDog;
   }

   public String toString() {
      return "I am a dude called " + name + ".\nI make " + salary
         + " eurodollars.\nMy dog's name is " + ownersDog.name
            + ".\n";
   }
}


class DogTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {
      Flea pop = new Flea("Pop");
      Flea squeak = new Flea("Squeak");
      Flea zip = new Flea("Zip");

      Dog rex = new Dog("Rex", 4, pop);
      Dog jimbo = new Dog("Jimbo", 6, squeak);
      Dog fido = new Dog("Fido", 1, zip);

      DogOwner angus = new DogOwner("Angus", 1, rex);
      DogOwner brian = new DogOwner("Brian", 8381249, jimbo);
      DogOwner charles = new DogOwner("Charles", 400, fido);

      System.out.println(rex.toString());
      System.out.println(jimbo.toString());
      System.out.println(fido.toString());

      System.out.println(angus.toString());
      System.out.println(brian.toString());
      System.out.println(charles.toString());

      // should print Pop's stuff
      angus.ownersDog.dogsFlea.toString();
   }
}

