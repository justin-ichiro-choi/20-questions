// Justin Choi
// 3/3/2016
// CSE143
// TA: Meredith Port
// Section BU
// Assignment #7 (20 Questions)
//
// This class implements an binary tree system based on yes/no responses
// and constructors. 

public class QuestionNode {
   // data that will hold either a object or a question that corresponds to the object
   public String data;
   // right branch of the binary tree, representing the path the tree would go if the user responded "no"
   public QuestionNode yesNode;
   
   public QuestionNode noNode;
   
   // constructs a leaf node with given data
   // In terms of 20 Questions, this is an "answer"
   // node that stores an answer.
   
   public QuestionNode(String data) {
      this(data,null, null); 
   }
   
   // constructs a branch node with given data, left subtree,
   // and right subtree. 
   // In terms of 20 Questions, this is an "question" node that
   // stores an question 
   public QuestionNode(String data, QuestionNode yesNode,
                      QuestionNode noNode) {
      this.data = data;
      this.yesNode = yesNode;
      this.noNode = noNode; 
   }                      
                      











}