import java.util.*;
import java.lang.*;

class NumberSystem {
  private static List<String> ALPHABET = Arrays.asList("abcdefghijklmnopqrstuvwxyz".split(""));

  private Integer base;
  private ArrayList<Integer> value;

  // Constructing the Class and checking the Input values.

  public NumberSystem (Integer Base, ArrayList<Integer> Value) {
    this.base = Base;
    this.value = Value;
  }

  // conversion to other Bases

  // Conversion to the Decimal-System

  public Integer convertToDecimal () {
    Integer out = 0;
    for (int i = 0 ; i < this.value.size() ; i++) {
      Double powerD = Math.pow(Double.valueOf(this.base),Double.valueOf(this.value.size()-1-i));
      Integer power = powerD.intValue();
      out += this.value.get(i) * power;
    }
    return out;
  }

  // conversion to another base than just ten

  public NumberSystem convertToSystem (Integer convertBase) {

    // convert the number into decimal
    Integer decimalValue = this.convertToDecimal();

    ArrayList<Integer> convertValue = new ArrayList<Integer>();

    // then devide the decimal value with the base and take the reminder
    // the reminders are the new values but in reverse, so with
    // collections.reverse() we reverse the convertValue
    Integer tempResult = decimalValue;
    while (tempResult!=0) {
      convertValue.add(tempResult % convertBase);
      tempResult = (tempResult - tempResult % convertBase)/convertBase;
    }
    Collections.reverse(convertValue);
    return new NumberSystem(convertBase, convertValue);
  }

  public static NumberSystem add (NumberSystem num1, NumberSystem num2) {

    // convert both numbers to decimal
    Integer IntNum1 = num1.convertToDecimal();
    Integer IntNum2 = num2.convertToDecimal();

    // add both numbers and convert them to a string
    String IntNum3 = "" + (IntNum1 + IntNum2);
    ArrayList<Integer> IntNum3ValueBTen = new ArrayList<Integer>();

    // iterate over every digit in the string and then add the parsed
    // digits (to integer) to the new number value
    for (String var : Arrays.asList(IntNum3.split(""))) {
      IntNum3ValueBTen.add(Integer.parseInt(var));
    }
    return new NumberSystem(10, IntNum3ValueBTen);
  }

  // getters

  public Integer getBase () {return this.base;}
  public ArrayList<Integer> getValue () {return this.value;}

  // toString of Base and Value like "Base: ..., Value: ..."

  public String toString () {
    String out = new String();

    out += "Base: " + Integer.toString(this.base) + ", Value: ";

    for (int i = 0 ; i < this.value.size() ; i++) {
      if (this.value.get(i)>9) {
        out += ALPHABET.get(this.value.get(i)-10).toUpperCase();
      } else {
        out += Integer.toString(this.value.get(i));
      }
    }
    // out += value.toString();

    return out;
  }
}
