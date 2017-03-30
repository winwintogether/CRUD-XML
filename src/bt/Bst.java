/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt;

/**
 *
 * @author RK
 */
public class Bst {
      public static  Node root;
	
	public Bst(){
		this.root = null;
	}
	
	public boolean find(String id){
		Node current = root;
		while(current!=null){
                    
                        int flag=current.data.compareTo(id);
			if(flag==0){
				return true;
			}else if(flag>0){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	public boolean delete(String id){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(!current.data.equals(id)){
			parent = current;
			if(current.data.compareTo(id)>0){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		//if i am here that means we have found the node
		//Case 1: if node to be deleted has no children
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			
			//now we have found the minimum element in the right sub tree
			Node successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public Node getSuccessor(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
//		successsorParent
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	public void insert(String id, String cs){
		Node newNode = new Node(id, cs);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(current.data.compareTo(id)>0){				
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
		}
	}
	public void display(Node root){
                
              CS_MN mn = new CS_MN();                
	      
              if(root!=null){
			display(root.left);
                        
                        // System.out.println(" " + root.data+":"+root.cs);
                        System.out.println("..................");
                           mn.ShowMenu(root.cs);
                          
			display(root.right);
		}
	}
	
}


