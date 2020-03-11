import java.lang.System;
import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    ArrayList<NumberSystem> numbers = new ArrayList<NumberSystem>();

    // add the initial input to numbers
    numbers.add(NumberInput(args[0], args[1]));

    Boolean goOn = true;


    // all possible user actions:
    while (goOn) {
      System.out.println("Wath would you like to do next? The options are:");
      System.out.println("show number (s)");
      System.out.println("show all numbers (sa)");
      System.out.println("convert to another base (c)");
      System.out.println("new number (n)");
      System.out.println("add 2 numbers (a)");
      System.out.println("exit (e)");
      switch (in.nextLine()) {

        case "s":
          System.out.println("Which number would you like to see? (from 1 to " + numbers.size() + ")");
          System.out.println(numbers.get(IndexInput(in.nextLine(), numbers)));
          break;

        case "sa":
          for (int i = 0 ; i < numbers.size() ; i++) {
            System.out.println("Number " + (i+1) + ": " + numbers.get(i));
          }
          break;

        case "c":
          System.out.println("Which number would you like to convert? (from 1 to " + numbers.size() + ")");
          Integer index = IndexInput(in.nextLine(), numbers);

          System.out.println("In what base would you like to convert to?");
          Integer convertBase = ConvertBaseValue.getBase(in.nextLine());

          numbers.add(numbers.get(index).convertToSystem(convertBase));

          System.out.println("The number looks as follows:");
          System.out.println(numbers.get(numbers.size()-1));
          break;

        case "n":
          System.out.println("Please enter a new base:");
          String BaseNew = in.nextLine();
          System.out.println("Please enter a new value:");
          String ValueNew = in.nextLine();
          numbers.add(NumberInput(BaseNew, ValueNew));
          break;

        case "a":
          System.out.println("Select the first number. (from 1 to " + numbers.size() + ")");
          NumberSystem num1 = numbers.get(IndexInput(in.nextLine(), numbers));
          System.out.println("Select the second number. (from 1 to " + numbers.size() + ")");
          NumberSystem num2 = numbers.get(IndexInput(in.nextLine(), numbers));
          numbers.add(NumberSystem.add(num1,num2));
          break;

        case "e":
          goOn = false;
          break;

        default:
          goOn = false;
          break;
      }
      System.out.println();
    }
  }


  // Check if a user Input for a number is valid

  private static NumberSystem NumberInput (String Base, String Value) {
    Scanner in = new Scanner(System.in);
    Integer base = null;
    ArrayList<Integer> value = null;
    NumberSystem number = null;


    // while the input is not valid ask again for an input
    while (true) {
      try {

        // first try to convert the strings into a base and value (throws exception)
        base = ConvertBaseValue.getBase(Base);
        value = ConvertBaseValue.getValue(Value);

        // then check if the value is in the right boundaries
        ConvertBaseValue.checkValue(base, value);

        // at last assign the new number
        number = new NumberSystem(base, value);
        break;
      } catch (Exception e) {

        // ask again if the base or value cannot be accepted
        System.out.println("Your input is not valid.");
        System.out.println("Please enter a correct base:");
        Base = in.nextLine();
        System.out.println("Please enter a correct value:");
        Value = in.nextLine();
        continue;
      }
    }
    return number;
  }



  // Check the user input for an index of a number from numbers

  private static Integer IndexInput (String indexString, ArrayList<NumberSystem> numbers) {
    Scanner in = new Scanner(System.in);
    Integer index = null;

    // while the number can not be accepted ask for an input again and again until the input gets accepted
    while(true) {
      try {
        // first try to parse the input as an Integer, if not possible throw an exception
        index = Integer.parseInt(indexString);
        // check if a number for this input exists, if not throw an exception
        if (index < 0 || index > numbers.size()) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        // catch the above exceptions and ask for a number again
        System.out.println("Please enter a value (from 1 to " + numbers.size() + ")");
        indexString = in.nextLine();
        continue;
      }
    }
    return index-1;
  }
}
