import java.util.*; 
import java.io.*;
// Justin Choi
// 3/3/2016
// CSE143
// TA: Meredith Port
// Section BU
// Assignment #7 (20 Questions)
//
// This class implements a yes/no guessing game
// using binary trees that asks the client a series
// of yes or no questions. If answer is not in tree,
// the binary tree will be updated with the answer and
// a question that corresponds to that answer. 

public class QuestionTree {
   // root of the binary tree storing the questions and answers
   // of the game
   private QuestionNode rootOfTree;
   // Scanner used to record user input. 
   private Scanner console; 
   
   // constructs a question tree with one leaf node (answer node)
   // representing the object computer.
   public QuestionTree() {
      rootOfTree = new QuestionNode("computer"); 
      console = new Scanner(System.in); 
   }
   
   // replaces the current tree available by reading entire
   // line of input to construct a tree based on a file. 
   public void read(Scanner input) {
      while(input.hasNextLine()) {
         rootOfTree = readHelper(input); 
      }
   }
   // helper method that reads entire lines of input to 
   // construct a tree based on a file. 
   private QuestionNode readHelper(Scanner input) {
      String type = input.nextLine();
      String data = input.nextLine();
      QuestionNode root = new QuestionNode(data);  
  
      if (type.contains("Q:")) {
         root.yesNode = readHelper(input);
         root.noNode = readHelper(input);   
      }
      return root; 
   }
   
   // Stores the current tree to an output file. If PrintStream
   // is not open for writing, throws an IllegalArgumentException
   public void write(PrintStream output) {
      if (output == null) {
         throw new IllegalArgumentException(); 
      }
      writeTree(rootOfTree, output);
   }
   
   // Private helper method that stores content of current tree to
   // an input file.
   private void writeTree(QuestionNode rootOfTree, PrintStream output) {
      if (isAnswerNode(rootOfTree)) {
         output.println("A:"); 
         output.println(rootOfTree.data);
      } else {
         output.println("Q:");
         output.println(rootOfTree.data);
         writeTree(rootOfTree.yesNode, output);
         writeTree(rootOfTree.noNode, output); 
      }   
   }
   
   // Uses the current tree to ask the user a series of yes/no questions
   // until the program guesses their object correctly or fails.
   // Upon failing, expands the tree to include their object and a new
   // question to distinguish their object from the others within the
   // tree. 
   public void askQuestions() {
      rootOfTree = askQuestions(rootOfTree); 
   }
   // With the tree (current), private helper method that asks 
   // the user a series of yes/no questions. If it fails to guess that 
   // object, expands the tree to include their object and a new question
   // via user input from Scanner (console). 
   private QuestionNode askQuestions(QuestionNode current) {
      if (isAnswerNode(current)) {
         if (yesTo("Would your object happen to be " + current.data +"?")) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            QuestionNode answer = new QuestionNode(console.nextLine());
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> "); 
            String question = console.nextLine(); 
            if (yesTo("And what is the answer for your object?")) {
               current = new QuestionNode(question, answer, current); 
            } else {
               current = new QuestionNode(question, current, answer); 
            }   
         }
     
      } else {
         if (yesTo(current.data)) {
            current.yesNode = askQuestions(current.yesNode);
         } else {
            current.noNode = askQuestions(current.noNode); 
         }   
      } 
      return current;
   }
   
   // post: asks the user a question, forcing an answer of "y " or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
          System.out.println("Please answer y or n.");
          System.out.print(prompt + " (y/n)? ");
          response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }   
   // private method that determines whether a specific node 
   // is an answer node (a leaf node)
   private boolean isAnswerNode(QuestionNode node) {
      return (node.yesNode == null || node.noNode == null);
   }
}












