/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

import tree.AVLNode;

/**
 *
 * @author dabri
 */
public class MyQueue {
    
    public MyNode head; 
    public MyNode tail; 
    public int size = 0;
   /**
    * 
    */
    public MyQueue() {
        this.head = null;
        this.tail = null;
    }//constructr
    /**
     * 
     * @param nodeTree 
     */
    public void enqueue(AVLNode nodeTree){
        MyNode newNode = new MyNode(nodeTree);
        if(this.size == 0){
            this.head = newNode;
            this.tail = newNode;
            this.head.back = this.tail;
            this.tail.front = this.head;
        }else{
            newNode.front = this.tail;
            this.tail.back = newNode;
            this.tail = newNode;
        }
        this.size++;
    }//enqueue
    /**
     * 
     * @return 
     */
    public MyNode dequeue(){
        if(this.head == null){
            return null;
        }else{
            MyNode temp = this.head;
            this.head = this.head.back;
            this.size--;
            return temp;
        }
    }//dequeue
    /**
     * 
     * @return 
     */
    public MyNode first(){
        return this.head;
    }//first
    
}//class
