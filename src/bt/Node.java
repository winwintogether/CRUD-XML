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
class Node{
	String data;
        String cs;
	Node left;
	Node right;	
	public Node(String data, String cs){
		this.data = data;
                this.cs=cs;
		left = null;
		right = null;
	}
}
