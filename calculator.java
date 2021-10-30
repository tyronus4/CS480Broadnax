import static java.lang.Math.*;
import java.util.Scanner;
import java.util.Stack;
import java.lang.String.*;

public class calculator {


   public double Add(double input, double input2) {
   
   double c = input+input2;
   return c;
   
   }
   
   public double Sub(double input, double input2) {
   
   double c = input-input2;
   return c;
   
   }
   
   public double Multi(double input, double input2) {
   
   double c = input*input2;
   return c;
   
   }
   
   public double Divide(double input, double input2) {
   
   double c = input/input2;
   return c;
   
   }
   
   public double Exponent(double input, double input2) {
   
   double c = Math.pow(input, input2);
   return c;
   
   }
   
   public double logn(double input) {
   
   double c = Math.log(input);
   
   return c;
   
   }
   
   public double log10(double input) {
   
   double c = Math.log10(input);
   
   return c;
   
   }
   
   //check these methods if they should automatically convert input to radians
   //Have a check if the value is between -1 and 1
   //Nope just do degrees only, include that with the read me
   public double Sin(double input) {
   
   double c = Math.toRadians(input);
   c = Math.sin(c);
   
   return c;
   
   }
   
   public double Cos(double input) {
   
   double c = Math.toRadians(input);
   c = Math.cos(c);
   
   return c;
   
   }
   
   public double Tan(double input) {
   
   double c = Math.toRadians(input);
   c = Math.tan(c);
   
   return c;
   
   }
   
   public double ASin(double input) {
   
   double c = Math.toRadians(input);
   c = Math.asin(c);
   
   return c;
   
   }
   
   public double ACos(double input) {
   
   double c = Math.toRadians(input);
   c = Math.acos(c);
   
   return c;
   
   }
   
   public double ATan(double input) {
   
   double c = Math.toRadians(input);
   c = Math.atan(c);
   
   return c;
   
   }



   public static void main(String[] arg) {
   
   //Work on figuring out delimiter
   
   String s = "(((40*3)-3)+3-(-3))+3";
   
   s = s.substring(s.indexOf("(") + 1);
   s = s.substring(0, s.indexOf(")"));
   
   System.out.println(s);
   
   }




}