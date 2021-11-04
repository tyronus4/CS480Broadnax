import static java.lang.Math.*;
import java.util.Scanner;
import java.util.Stack;
import java.lang.String;
import java.lang.Character;

public class calculator {
   
   //This method goes through the string and replaces any operator or parentheses with spaces to aid in 
   //formatting for RPN
   public static String replacing(String s) {
   String[] chars = {"+", "-", "/", "^", "sqrt", "(", ")", "[", "]", "{", "}"};

   for (String character : chars) {
      if (s.contains(character)) {
         s = s.replace(character, " " + character + " ");
      }
   }
   return s;
   }
   
   //Gets the precedence for operators from the string, assigns a int value used later
   public static int precedence(String c) {
      
      if (c.equals("+") || c.equals("-")){
         return 1;
      }
      else if (c.equals("*") || c.equals("/")){
         return 2;
      }
      else if (c.equals("^") || c.equals("sqrt")){
         return 3;
      }
      else
         return -1;
   }
   
   //Checks if the string is an open/close parentheses, square bracket, or curly brace
   //Also checks if it is a number with associated parentheses, i.e. Sin(), Cos(), ln()
   //assigns a int value accordingly
   public static int bracket(String c) {
   
      if (c.equals("(") || c.equals(")") || c.equals("[") || c.equals("]") || c.equals("{") || c.equals("}")){
         return 4;
      }
      else if(c.equals("ln") || c.equals("log") || c.equals("sin") || c.equals("cos") ||
      c.equals("tan") || c.equals("arcsin") || c.equals("arccos") || c.equals("arctan")) {
         return 5;
      }
      
      else
         return -1;
      }
   
   //RPN method. Goes through and converts the string into Reverese polish notation order
   public static String form(String expression) {
   
   Stack<String> stack = new Stack<>();
   String output = new String("");
   expression = replacing(expression);
   String[] split = expression.split(" ");

   
   for (int i = 0; i<split.length; i++) {
   
      String k = split[i];
   
      if(precedence(k) == -1 && bracket(k) == -1){
         output = output+k+" ";
      }
      
      //following section goes through and checks for paranthesis, square brackets and curly braces
      else if(k.equals("(") || k.equals("[") || k.equals("{")) {
         stack.push(k);
      }
      else if(k.equals(")")) {
         while(!stack.isEmpty() && !stack.peek().equals("(")) {
            output = output + stack.pop();
         }
         stack.pop();
         if(bracket(stack.peek()) == 5){
            output = output + stack.pop();
         }
      }
      else if(k.equals("]")) {
         while(!stack.isEmpty() && !stack.peek().equals("[")) {
            output = output + stack.pop();
         }
         stack.pop();
         if(bracket(stack.peek()) == 5){
            output = output + stack.pop();
         }
      }
      else if(k.equals("}")) {
         while(!stack.isEmpty() && !stack.peek().equals("{")) {
            output = output + stack.pop();
         }
         stack.pop();
         if(bracket(stack.peek()) == 5){
            output = output + stack.pop();
         }
      }
      //all trig functions act as extra parenthesis, push to stack if present and then pop if at top of stack again
      else if(bracket(k) == 5) {
         stack.push(k); 
      }
      
      else{
         while(!stack.isEmpty() && precedence(k) <= precedence(stack.peek())) {
            output = output + stack.pop();
         }
         stack.push(k);
      }
   
   }
   
   while(!stack.isEmpty()) {
      if(stack.peek().equals("(") || stack.peek().equals("[") || stack.peek().equals("{") || bracket(stack.peek()) == 5){
         return "This is not a valid expression";
      }
      output = output + stack.pop();
   }
   
   return output;
   }
   
   
   
   //method to calculate the total of the given expression
   public static double calculation(String expression) {
   
   double input1 = 0;
   double input2 = 0;
   Stack<Double> total = new Stack<>();

   //fixing the strings whitespaces    
   expression = replacing(expression);
   expression = expression.replaceAll("\\s+", " ");

   
   String[] split = expression.split(" ");
   
   //goes through the string and does the necessary operations
   //in RPN order.
   for (int i = 0; i<split.length; i++) {
   
      String k = split[i];
      
      if(k.equals("+")) {
         input1 = total.pop();
         input2 = total.pop();
         total.push(input1+input2);
      }
      else if(k.equals("-")) {
         input1 = total.pop();
         total.push(input1*-1);
      }
      else if(k.equals("*")) {
         input1 = total.pop();
         input2 = total.pop();
         total.push(input1*input2);
      }
      else if(k.equals("/")) {
         input1 = total.pop();
         input2 = total.pop();
         total.push(input2/input1);
      }
      else if(k.equals("^")) {
         input1 = total.pop();
         input2 = total.pop();
         total.push(Math.pow(input2, input1));
      }
      else if(k.equals("sqrt")) {
         input1 = total.pop();
         total.push(Math.sqrt(input1));
      }
      else if(k.equals("ln")) {
         input1 = total.pop();
         total.push(Math.log(input1));
      }
      else if(k.equals("log")) {
         input1 = total.pop();
         total.push(Math.log10(input1));
      }
      else if(k.equals("sin")) {
         input1 = total.pop();
         input1 = Math.toRadians(input1);
         total.push(Math.sin(input1));
      }
      else if(k.equals("cos")) {
         input1 = total.pop();
         input1 = Math.toRadians(input1);
         total.push(Math.cos(input1));
      }
      else if(k.equals("tan")) {
         input1 = total.pop();
         input1 = Math.toRadians(input1);
         total.push(Math.tan(input1));
      }
      else if(k.equals("arcsin")) {
         input1 = total.pop();
         input1 = Math.toRadians(input1);
         total.push(Math.asin(input1));
      }
      else if(k.equals("arccos")) {
         input1 = total.pop();
         input1 = Math.toRadians(input1);
         total.push(Math.acos(input1));
      }
      else if(k.equals("arctan")) {
         input1 = total.pop();
         input1 = Math.toRadians(input1);
         total.push(Math.atan(input1));
      }                                          
      else {
         try {
            total.push(Double.parseDouble(k));
         }
         catch (NumberFormatException nfe) {
            System.out.println("An invalid value was attempted to be parsed: "+ k);
            System.out.println("Please correct the given mathematical expression");
         }
         
      }                            
   
   }
   
   input1 = 0;
   
   while(!total.isEmpty()){
      input1 += total.pop();
   }
   
      
   return input1;
   
   }


   //main method, takes in command line argument and parses it. 
   //Needs to be a single string, no spaces allowed for this implementation
   public static void main(String[] args) {
   
   String answer = args[0];
   answer = form(answer);
   if (answer.equals("This is not a valid expression")) {
      System.out.println("An invalid value was attempted to be parsed");
      System.out.println("Please correct the given mathematical expression");
   }
   else {
      double output = calculation(answer);
      System.out.println(output);
   }
   
   }



}