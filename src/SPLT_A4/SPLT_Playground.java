package SPLT_A4;

public class SPLT_Playground {
  public static void main(String[] args){
//    genTest();
//	  zigZag();
//	  containsTest();
//	  heightTest();
//	  findMinFindMaxTest();
//	  size();
	  removeTest();
//	  insertTest();
  }
  
  public static void genTest(){
    SPLT tree= new SPLT();
    tree.insert("hello");
    tree.insert("world");
    tree.insert("my");
    tree.insert("name");
    tree.insert("is");
    tree.insert("blank");
//    tree.remove("hello");
    System.out.println("size is "+tree.size());
    
    printLevelOrder(tree);
  }
  
  public static void zigZag(){
	    SPLT tree= new SPLT();
	    tree.insert("E");
	    tree.insert("C");
	    tree.insert("F");
	    tree.insert("B");
	    tree.insert("D");
//	    tree.remove("hello");
	    System.out.println("size is "+tree.size());
	    
	    printLevelOrder(tree);
	  }

  public static void containsTest(){
	    SPLT tree= new SPLT();
	    tree.insert("B");
	    tree.insert("C");
//	    tree.insert("D");
//	    tree.insert("C");
//	    tree.insert("E");
//	    tree.contains("D");
	    System.out.println(tree.contains("A"));
//	    System.out.println("Contains: " + tree.contains("D"));
//	    tree.remove("hello");
	    System.out.println("size is "+tree.size());
	    
	    printLevelOrder(tree);
	  }
  
  public static void heightTest(){
	    SPLT tree= new SPLT();
	    tree.insert("B");
	    tree.insert("A");
	    System.out.println(tree.height());
	    
	    System.out.println("size is "+tree.size());
	    printLevelOrder(tree);
 }
  
  public static void findMinFindMaxTest(){
	    SPLT tree= new SPLT();
	    tree.insert("A");
	    tree.insert("B");
	    tree.insert("C");
//	    System.out.println("FindMax: " + tree.findMax());
	    System.out.println("FindMin: " + tree.findMin());
	    
	    System.out.println("size is "+tree.size());
	    printLevelOrder(tree);
}
  
  public static void size(){
	    SPLT tree= new SPLT();
	    tree.insert("a");
	    tree.insert("a");
//	    tree.insert("C");
	    
	    System.out.println("size is "+tree.size());
	    printLevelOrder(tree);
}
  	
  public static void removeTest(){
	    SPLT tree= new SPLT();
	    tree.insert("0");
	    tree.insert("C");
	    tree.insert("A");
	    tree.insert("B");
	    tree.insert("E");
	    tree.insert("D");
	    
	    System.out.println("size is "+tree.size());
	    printLevelOrder(tree);
	    
	    System.out.println();
	    tree.remove("C");
	    tree.remove("A");
	    tree.remove("D");
	    System.out.println("size is "+tree.size());
	    printLevelOrder(tree);
}
  
  public static void insertTest(){
	    SPLT tree= new SPLT();
	    tree.insert("B");
	    tree.insert("A");
	    tree.insert("D");
	    tree.insert("C");
	    tree.insert("E");
	    tree.insert("0");
	    
	    
	    System.out.println("size is "+tree.size());
	    printLevelOrder(tree);
	    
}
  
  
  
    static void printLevelOrder(SPLT tree){ 
    //will print your current tree in Level-Order...Requires a correct height method
    //https://en.wikipedia.org/wiki/Tree_traversal
      int h=tree.getRoot().getHeight();
      for(int i=0;i<=h;i++){
        System.out.print("Level "+i+":");
        printGivenLevel(tree.getRoot(), i);
        System.out.println();
      }
      
    }
    static void printGivenLevel(BST_Node root,int level){
      if(root==null)return;
      if(level==0)System.out.print(root.data+" ");
      else if(level>0){
        printGivenLevel(root.left,level-1);
        printGivenLevel(root.right,level-1);
      }
    }
   static void printInOrder(BST_Node root){
      if(root!=null){
      printInOrder(root.getLeft());
      System.out.print(root.getData()+" ");
      printInOrder(root.getRight());
      }
  }
  
}