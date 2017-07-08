package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
//note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
  public BST_Node containsNode(String s){ 
	if(data.equals(s)){           // found what is looking for
		BST_Node newRoot = splay(this);
		return newRoot;
	}
	if(data.compareTo(s)>0){       //s lexiconically less than data (So go left)
		if(left==null){
			return splay(this);
		}
		return left.containsNode(s);  // keep going left
	}
	if(data.compareTo(s)<0){   //(Go right)
		if(right==null){
			return splay(this);
		}
		return right.containsNode(s);  // Keep going right
	}
	return null; //shouldn't hit
  
  } 
  
  public BST_Node insertNode(String s){  
	  if(data.compareTo(s)>0){
			if(left==null){
				left=new BST_Node(s);
				left.par = this;
				BST_Node newRoot = splay(left);
				return newRoot;
			}
			return left.insertNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null){
				right=new BST_Node(s);
				right.par = this;
				BST_Node newRoot = splay(right);
				return newRoot;
			}
			return right.insertNode(s);
		}
		return this.containsNode(s);//ie we have a duplicate
	  
	  
	  
  } //Really same logic as above note
  
  public boolean removeNode(String s){
	  if(data==null)return false;
		if(data.equals(s)){
			if(left!=null){
				data=left.findMax().data;
				left.removeNode(data);
				if(left.data==null)left=null;
			}
			else if(right!=null){
				data=right.findMin().data;
				right.removeNode(data);
				if(right.data==null)right=null;
			}
			else data=null;
			return true;
		}
		else if(data.compareTo(s)>0){
			if(left==null)return false;
			if(!left.removeNode(s))return false;
			if(left.data==null)left=null;
			return true;
		}
		else if(data.compareTo(s)<0){
			if(right==null)return false;
			if(!right.removeNode(s))return false;
			if(right.data==null)right=null;
			return true;
		}
		return false;
	  
	  
	  
  } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
  
 // [DONE]
  public BST_Node findMin(){ 
	  if(left!=null)return left.findMin();
		return splay(this);
	  
	  
  } 
  // [DONE]
  public BST_Node findMax(){ 
		if(right!=null)return right.findMax();
		return splay(this);
	  
  }
  
  public int getHeight(){
	  int l=0;
		int r=0;
		if(left!=null)l+=left.getHeight()+1;
		if(right!=null)r+=right.getHeight()+1;
		return Integer.max(l, r);
  }
  
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}

  private BST_Node splay(BST_Node toSplay) {
	  while (toSplay.par != null)
      {
          BST_Node Parent = toSplay.par;
          BST_Node GrandParent = Parent.par;
          if (GrandParent == null)
          {
              if (toSplay == Parent.left)
                  rotateRight(toSplay, Parent);
              else
            	  rotateLeft(toSplay, Parent);                 
          } 
          else
          {
              if (toSplay == Parent.left)
              {
                  if (Parent == GrandParent.left)
                  {
                	  rotateRight(Parent, GrandParent);
                	  rotateRight(toSplay, Parent);
                  }
                  else 
                  {
                	  rotateRight(toSplay, toSplay.par);
                      rotateLeft(toSplay, toSplay.par);
                  }
              }
              else 
              {
                  if (Parent == GrandParent.left)
                  {
                	  rotateLeft(toSplay, toSplay.par);
                	  rotateRight(toSplay, toSplay.par);
                  } 
                  else 
                  {
                	  rotateLeft(Parent, GrandParent);
                	  rotateLeft(toSplay, Parent);
                  }
              }
          }
      }
  
	  return toSplay;
	  
  } //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  
  
  public void rotateLeft(BST_Node thePivot, BST_Node theRoot){ //makerightchild
	
	   if (theRoot.par != null){  // if theRoot has a parent
		   if (theRoot == theRoot.par.left){
			   theRoot.par.left = thePivot;
		   }
		   else{
			   theRoot.par.right = thePivot;
		   }
	   }
	   if (thePivot.left != null) { // if thePivot has a left child
		   thePivot.left.par = theRoot;
	   }
	   
	   thePivot.par = theRoot.par;
	   theRoot.par = thePivot;
	   theRoot.right = thePivot.left;
	   thePivot.left = theRoot;   
	   
  }
  
  public void rotateRight(BST_Node thePivot, BST_Node theRoot){  //makeleftchild
	  
	if (theRoot.par != null){   // if theRoot has a parent
		if (theRoot == theRoot.par.left){
			theRoot.par.left = thePivot;
		}
		else{
			theRoot.par.right = thePivot;
		}
	}
	if (thePivot.right != null){   // if thePivot has a right child
		thePivot.right.par = theRoot;
	}
	
	thePivot.par = theRoot.par;
	theRoot.par = thePivot;  
	theRoot.left = thePivot.right;
	thePivot.right = theRoot;
	  
  }
  // --- end example methods --------------------------------------

  
  

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  
}