class FredFlintstone {

   // Properties of the class...
   static String name            = "Fred Flintstone";
   static String favouriteColour = "blue";
   static int    favouriteNumber = 42;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class WilmaFlintstone {

   // Properties of the class...
   static String name            = "Wilma Flintstone";
   static String favouriteColour = "red";
   static int    favouriteNumber = 63;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class BarneyRubble {

   // Properties of the class...
   static String name            = "Barney Rubble";
   static String favouriteColour = "yellow";
   static int    favouriteNumber = 2;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class CartoonTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {
      CartoonCharacter ff = new CartoonCharacter("Fred Flintstone", "brown", 4);
      CartoonCharacter wf = new CartoonCharacter("Wilma Flintstone", "purple", 8);
      CartoonCharacter br = new CartoonCharacter("Barney Rubble", "green", 16);
      ff.displayMe();
      wf.displayMe();
      br.displayMe();
      System.out.println(br.favouriteNumber);
      System.out.println("amount " + CartoonCharacter.count);
      System.out.println(Math.PI);
   }
}

