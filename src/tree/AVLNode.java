/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author monge55
 */
public class AVLNode {

    public int datum, balanceFactor;
    public String word;
    private String chain;
    private String quantityPositions;
    public String position;
    public AVLNode leftSon;
    public AVLNode rightSon;

    public AVLNode(int datum, String word, String position) {
        this.datum = datum;
        this.word = word;
        this.position = position;
        this.balanceFactor = 0;
        this.leftSon = null;
        this.rightSon = null;
        this.quantityPositions = "";
    }//constructor

    public AVLNode(String chain) {
        this.balanceFactor = 0;
        this.leftSon = null;
        this.rightSon = null;
        this.chain = chain;
        this.quantityPositions = "";
    }

    public String getChain() {
        return chain;
    }

    public String getQuantityPositions() {
        return quantityPositions;
    }

    @Override
    public String toString() {
        return this.word + "[" + position + "]";
    }//toString

}//class
