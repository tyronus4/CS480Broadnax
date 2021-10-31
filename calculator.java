import static java.lang.Math.*;
import java.util.Scanner;
import java.util.Stack;
import java.lang.String;
import java.lang.Character;

public class calculator {

   private static double Add(double input, double input2) {
   
   double c = input+input2;
   return c;
   
   }
   
   //Sub method inverts the value of input2 and adds it to input.
   //This accomodates for both unary and binary operations
   //make sure this works well!
   private static double Sub(double input) {
   
   
   double c = input*-1;
   return c;
   
   }
   
   private static double Multi(double input, double input2) {
   
   double c = input*input2;
   return c;
   
   }
   
   private static double Divide(double input, double input2) {
   
   double c = input/input2;
   return c;
   
   }
   
   private static double Exponent(double input, double input2) {
   
   double c = Math.pow(input, input2);
   return c;
   
   }
   
   private static double logn(double input) {
   
   double c = Math.log(input);
   
   return c;
   
   }
   
   private static double log10(double input) {
   
   double c = Math.log10(input);
   
   return c;
   
   }
   
   //check these methods if they should automatically convert input to radians
   //Have a check if the value is between -1 and 1
   //Nope just do degrees only, include that with the read me
   private static double Sin(double input) {
   
   double c = Math.toRadians(input);
   c = Math.sin(c);
   
   return c;
   
   }
   
   private static double Cos(double input) {
   
   double c = Math.toRadians(input);
   c = Math.cos(c);
   
   return c;
   
   }
   
   private static double Tan(double input) {
   
   double c = Math.toRadians(input);
   c = Math.tan(c);
   
   return c;
   
   }
   
   private static double ASin(double input) {
   
   double c = Math.toRadians(input);
   c = Math.asin(c);
   
   return c;
   
   }
   
   private static double ACos(double input) {
   
   double c = Math.toRadians(input);
   c = Math.acos(c);
   
   return c;
   
   }
   
   private static double ATan(double input) {
   
   double c = Math.toRadians(input);
   c = Math.atan(c);
   
   return c;
   
   }
   
   public static String replacing(String s) {
   String[] chars = {"+", "-", "/", "^", "(", ")", "[", "]", "{", "}"};

   for (String character : chars) {
      if (s.contains(character)) {
         s = s.replace(character, " " + character + " ");
      }
   }
   return s;
   }   
   
   public static int precedence(String c) {
      
      if (c.equals("+") || c.equals("-")){
         return 1;
      }
      else if (c.equals("*") || c.equals("/")){
         return 2;
      }
      else if (c.equals("^")){
         return 3;
      }
      else
         return -1;
   }
   
   public static int bracket(String c) {
   
      if (c.equals("(") || c.equals(")") || c.equals("[") || c.equals("]") || c.equals("{") || c.equals("}")){
         return 4;
      }
      else
         return -1;
      }
   
   
   public static String form(String expression) {
   
   Stack<String> stack = new Stack<>();
   String output = new String("");
   expression = replacing(expression);
   String[] split = expression.split(" ");

   
   for (int i = 0; i<split.length; i++) {
   
      String k = split[i];
   
      if(precedence(k) == -1 && bracket(k) == -1){
         output = output+k;
      }
      
      //following section goes through and checks for paranthesis, square brackets and curly braces
      else if(k.equals("(")) {
         stack.push(k);
      }
      else if(k.equals(")")) {
         while(!stack.isEmpty() && !stack.peek().equals("(")) {
            output = output + stack.pop();
         }
         stack.pop();
      }
      else if(k.equals("[")) {
         stack.push(k);
      }
      else if(k.equals("]")) {
         while(!stack.isEmpty() && !stack.peek().equals("[")) {
            output = output + stack.pop();
         }
         stack.pop();
      }
      else if(k.equals("{")) {
         stack.push(k);
      }
      else if(k.equals("}")) {
         while(!stack.isEmpty() && !stack.peek().equals("{")) {
            output = output + stack.pop();
         }
         stack.pop();
      }
      
      else{
         while(!stack.isEmpty() && precedence(k) <= precedence(stack.peek())) {
            output = output + stack.pop();
         }
         stack.push(k);
      }
   
   }
   
   while(!stack.isEmpty()) {
      if(stack.peek().equals("(") || stack.peek().equals("[") || stack.peek().equals("{")){
         return "This is not a valid expression";
      }
      output = output + stack.pop();
   }
   
   return output;
   }
   
   
  
   
   
   
   
   
   



   public static void main(String[] arg) {
   

   //Shunting yard works, need to tell numbers with decimals apart from each other
   //how about making all numbers with decimals end with double 0 (00)? Easily detectable
   //unary opertors suck
   //have minus be multiply by -1, then at the end of the expression add all remaining elements together
   //a negative comes right after a negative, add the two previous elements together. If a negative follows a positive,
   //negate and add the elements together (figure this out a bit more)
   
   
   String test = "sin(30)+100+350.5900/3^2-(-5+log(3))";
   System.out.println(form("5+2/(3-8)^5^2"));
   test = form(test);
   System.out.println(test);
//    String[] split = test.split(" ");
//    System.out.println(test);
//    for (int i = 0; i< split.length; i++) {
//    
//    
//       System.out.println(split[i]);
//    
//       }
   }



}