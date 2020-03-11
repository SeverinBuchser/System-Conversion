import java.util.*;
import java.lang.*;

class ConvertBaseValue {

  // ALPHABET is important if you have a base that allows bits to be greater than 9
  // this list helps with converting letters to integers
  private static List<String> ALPHABET = Arrays.asList("abcdefghijklmnopqrstuvwxyz".split(""));


  // tries to parse the base to an integer
  public static Integer getBase (String baseString) throws NumberFormatException {
    return Integer.parseInt(baseString);
  }


  //tries to convert a strign into a value
  public static ArrayList<Integer> getValue (String valueString) throws Exception {

    //first we split up the string to create bits
    String[] valueStringArray = valueString.split("");
    ArrayList<Integer> value = new ArrayList<Integer>();

    //then we iterate over the bits
    for (String var : valueStringArray) {

      //try to parse the var string (single bit in value) into an integer
      try {
        value.add(Integer.parseInt(var));
      } catch (NumberFormatException e) {

        // if var cannot be parsed into an integer check if var is in ALPHABET
        if (ALPHABET.contains(var.toLowerCase())) {

          // if var is in ALPHABET find the corresponding integer to var
          value.add(ALPHABET.indexOf(var.toLowerCase())+10);

        // if var is not a valid input throw exception
        } else {
          throw new Exception();
        }
      }
    }
    return value;
  }

  // Checks the value for incorrect Bits / Numbers.
  // The numbers must be smaller than the Base.

  public static void checkValue (Integer base, ArrayList<Integer> value) throws ArithmeticException {
    for (Integer var : value) {
      if (var >= base) {
        throw new ArithmeticException();
      }
    }
  }

}
