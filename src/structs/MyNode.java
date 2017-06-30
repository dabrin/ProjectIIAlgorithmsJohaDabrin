/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

import tree.AVLNode;

/**
 *
 * @author monge55
 */
public class MyNode {

    public AVLNode nodeTree;
    public MyNode back;
    public MyNode front;

    public MyNode(AVLNode nodeTree){
        this.nodeTree = nodeTree;
        this.back = null;
        this.front = null;
    }//constructor

}//class
