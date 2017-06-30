
package tree;

import structs.MyQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.JOptionPane;

/**
 *
 * @author monge55
 */
public class AVLTree {
    private String fileName;
    public AVLNode root;
    public MyQueue queue;

    public AVLTree() {
        this.root = null;
        this.queue = new MyQueue();
    }//constructor
     public AVLTree(String fileName) {
        this.fileName = fileName;
    }
    
    public AVLNode search(int d, AVLNode myNode){
        
        if(myNode == null){
            return null;
        }else if(myNode.datum == d){
            return myNode;
        }else if(myNode.datum < d){
            return search(d, myNode.rightSon);
        }else
            return search(d, myNode.leftSon);           
    }//search
    
    public int getBF (AVLNode x){
        
        if(x == null){
            return -1;
        }else
            return x.balanceFactor;
    }//getBF
    
    //simple rotations
     public void saveTree(AVLNode aVLNode) throws FileNotFoundException {

        File file = new File(this.fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(aVLNode.getChain() + "#" + aVLNode.getQuantityPositions());

    }
      public void saveNodes(AVLNode r, String rute) throws FileNotFoundException {
       // TreeBusiness treeBusiness = new TreeBusiness(rute);
          //JOptionPane.showMessageDialog(null, rute);
          System.err.println("2"+rute);
        if (r != null) {
            saveTree(r);
            saveNodes(r.leftSon, rute);
            saveNodes(r.rightSon, rute);
        }
    }
   public AVLNode leftRotation(AVLNode c){
        
        AVLNode aux = c.rightSon;
        c.rightSon = aux.leftSon;
        aux.leftSon = c;
        
        c.balanceFactor = Math.max(getBF(c.leftSon), getBF(c.rightSon)) + 1;
        
        aux.balanceFactor = Math.max(getBF(aux.leftSon), getBF(aux.rightSon));
        
        return aux;
    }//leftRotation
    
     public AVLNode rightRotation(AVLNode c){
        
        AVLNode aux = c.leftSon;
        c.leftSon = aux.rightSon;
        aux.rightSon = c;
        
        c.balanceFactor = Math.max(getBF(c.leftSon), getBF(c.rightSon)) + 1;
        aux.balanceFactor = Math.max(getBF(aux.leftSon), getBF(aux.rightSon));
        
        return aux;
   }//rightRotation
    
   // double rotations
    
    public AVLNode left_right_rotation(AVLNode c){
        
        AVLNode temp;
        c.leftSon = leftRotation(c.leftSon);
        temp = rightRotation(c);
        return temp;
        
    }//left_right_rotation
    
     public AVLNode right_left_rotation(AVLNode c){
        
        AVLNode temp;
        c.rightSon = rightRotation(c.rightSon);
        temp = leftRotation(c);
        return temp;
        
    }//right_left_rotation
    
     //insert method
   private AVLNode insertAVL(AVLNode new_, AVLNode subTree) {
        AVLNode newParent = subTree;

        if (new_.datum < subTree.datum) {
            if (subTree.leftSon == null) {
                subTree.leftSon = new_;
            } else {
                subTree.leftSon = insertAVL(new_, subTree.leftSon);

                if ((getBF(subTree.leftSon) - getBF(subTree.rightSon) == 2)) {
                    if (new_.datum < subTree.leftSon.datum) {
                        newParent = rightRotation(subTree);
                    } else {
                        newParent = left_right_rotation(subTree);
                    }
                }
            }
        } else if (new_.datum > subTree.datum) {
            if (subTree.rightSon == null) {
                subTree.rightSon = new_;
            } else {
                subTree.rightSon = insertAVL(new_, subTree.rightSon);

                if ((getBF(subTree.leftSon) - getBF(subTree.rightSon) == -2)) {
                    if (new_.datum > subTree.rightSon.datum) {
                        newParent = leftRotation(subTree);
                    } else {
                        newParent = right_left_rotation(subTree);
                    }
                }
            }
        } else {
            System.err.println(new_.datum);
            throw new Error("Duplicated node datum");
        }
        //updata bf
        if ((subTree.leftSon == null) && (subTree.rightSon != null)) {
            subTree.balanceFactor = subTree.rightSon.balanceFactor + 1;
        } else if ((subTree.rightSon == null) && (subTree.leftSon != null)) {
            subTree.balanceFactor = subTree.leftSon.balanceFactor + 1;
        } else {
            subTree.balanceFactor = Math.max(getBF(subTree.leftSon), getBF(subTree.rightSon)) + 1;
        }

        return newParent;
    }//insertAVL
        
    public void getNodes(AVLNode r){
        if(r != null){
            this.queue.enqueue(r);
            getNodes(r.leftSon);
            getNodes(r.rightSon);
        }
    }//getNodes
    
    public void insert(int d,String word, String position){
        AVLNode newNode = new AVLNode(d, word, position);
        if(this.root == null){
            root = newNode;
        }else
            root =insertAVL(newNode, root);
    }//insert
    
    public void preOrder(AVLNode r){
        
        if( r != null){
            System.out.print(r.datum + ", ");
            preOrder(r.leftSon);
            preOrder(r.rightSon);
        }
    }//preOrder

    public AVLNode getRoot() {
        return root;
    }//getRoot

    public void setRoot(AVLNode root) {
        this.root = root;
    }//setRoot
    
}//class
