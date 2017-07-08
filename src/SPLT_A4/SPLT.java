package SPLT_A4;

public class SPLT implements SPLT_Interface{
 protected BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

  
  
  
// ------------------------------------------ My Implementations ---------------------------------------------------------------
@Override
public void insert(String s) {
	if (empty()) {
		root = new BST_Node(s);
		size += 1;
	}
	
	else {
		size += 1;
		root = root.insertNode(s);
	}
}

@Override
public void remove(String s) {
	if (contains(s)) {
		if (root.data.equals(s) && size == 1) {  // the node looking for is the root
			root = null;
			size -= 1;
		} else {			// look for the node by delegating to the removeNode method
			size -= 1;
//			root.removeNode(s);
			
			// YOU HAVE THE ROOT (node you want to delete at the root)
			BST_Node lefter = root.left;
			BST_Node righter = root.right;
			//lefter.par = null;
			//righter.par = null;
			
//			//JIC 
			root= null;
//			lefter.par = null;
//			righter.par = null;
			
			
			if ((lefter != null) && (righter != null)){
				
				lefter.findMax();
				root = lefter;
				root.right = righter;
				
			}
			else if (righter != null){   // only right subtree exists
				righter.par = null;
				righter.findMin();
				root = righter;
				
			}
			else if (lefter != null){  // only left subtree exists
				lefter.par = null;
				lefter.findMax();
				root = lefter;
			}
			else{
				root = null;
			}
			
		}
	}
	
}


// [DONE]
@Override
public String findMin() {
	if (empty()) {
		return null;
	} else {
		root = root.findMin();
		return root.data;
	}
}

// [DONE]
@Override
public String findMax() {
	if (empty()) {
		return null;
	} else {
		root = root.findMax();
		return root.data;
	}
}

@Override
public boolean empty() {
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

@Override
public boolean contains(String s) {
	if (empty()) {
		return false;
	}
	
	root = root.containsNode(s);
	if (root.data.equals(s)){
		return true;
	}
	else{
		return false;
	}
	//return root.containsNode(s);
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return size;
}

@Override
public int height() {
	if (empty()) {
		return -1;
	} else {
		return root.getHeight();
	}
}  

}