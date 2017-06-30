package mdi;

/**
 *
 * @author monge55
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.JPanel;
import tree.AVLNode;
import tree.AVLTree;

public class MyTreeGraphics extends JPanel {

    private AVLTree AVLtree;
    private HashMap poscitionNodes = null;
    private HashMap subtreeSizes = null;
    private boolean dirty;
    private boolean size=true;
    private BufferedImage bufferedImage;
    private int parent2child = 35, child2child = 50;
    private Dimension empty;
    private FontMetrics fontMetrics = null;

    public MyTreeGraphics(AVLTree miArbol) {
        this.AVLtree = miArbol;
        this.setPreferredSize(new Dimension(9000, 9000));
        this.empty = new Dimension(0, 0);
        this.setBackground(Color.white);
        this.bufferedImage = new BufferedImage(9000, 9000, BufferedImage.TYPE_BYTE_GRAY);
        poscitionNodes = new HashMap();
        subtreeSizes = new HashMap();
        dirty = true;
    }//constructor

 
    private void calcularPosiciones() {
        poscitionNodes.clear();
        subtreeSizes.clear();
        AVLNode root = this.AVLtree.getRoot();
        if (root != null) {
            calcularTamañoSubarbol(root);
            calculatePosition(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
    }

   
    private Dimension calcularTamañoSubarbol(AVLNode n) {
        if (n == null) {
            return new Dimension(0, 0);
        }
        Dimension leftSon = calcularTamañoSubarbol(n.leftSon);
        Dimension rigthSon = calcularTamañoSubarbol(n.rightSon);

        int heigth = fontMetrics.getHeight() + parent2child + Math.max(leftSon.height, rigthSon.height);
        int width = leftSon.width + child2child + rigthSon.width;

        Dimension d = new Dimension(width, heigth);
        subtreeSizes.put(n, d);

        return d;
    }

    
    private void calculatePosition(AVLNode n, int left, int right, int top) {
        if (n == null) {
            return;
        }

        Dimension dimension = (Dimension) subtreeSizes.get(n.leftSon);
        if (dimension == null) {
            dimension = empty;
        }

        Dimension rd = (Dimension) subtreeSizes.get(n.rightSon);
        if (rd == null) {
            rd = empty;
        }

        int center = 0;

        if (right != Integer.MAX_VALUE) {
            center = right - rd.width - child2child / 2;
        } else if (left != Integer.MAX_VALUE) {
            center = left + dimension.width + child2child / 2;
        }
        int width = fontMetrics.stringWidth(n.datum + "[" + n.position + "]");

        poscitionNodes.put(n, new Rectangle(center - width / 2 - 3, top, width + 6, fontMetrics.getHeight()));

        calculatePosition(n.leftSon, Integer.MAX_VALUE, center - child2child / 2, top + fontMetrics.getHeight() + parent2child);
        calculatePosition(n.rightSon, center + child2child / 2, Integer.MAX_VALUE, top + fontMetrics.getHeight() + parent2child);
    }//calculatePosition

    
    private void drawTree(Graphics2D g, AVLNode n, int puntox, int puntoy, int yoffs) {
        if (n == null) {
            return;
        }

        Rectangle r = (Rectangle) poscitionNodes.get(n);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(r.x, r.y - 10, n.toString().length()*8, 30);
        g.setColor(Color.black);
        g.drawString(n.toString() + "", r.x + 5, (r.y + yoffs)-2);
      
        g.setColor(Color.lightGray);
        if (puntox != Integer.MAX_VALUE) {
            g.drawLine(puntox, puntoy, (int) (r.x + r.width / 2), r.y);
        }
     
        drawTree(g, n.leftSon, (int) (r.x + r.width / 2), r.y + r.height, yoffs);
        drawTree(g, n.rightSon, (int) (r.x + r.width / 2), r.y + r.height, yoffs);

    }//drawTree

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, 9000, 9000, null);
        fontMetrics = g.getFontMetrics();

        if (dirty) {
            calcularPosiciones();
            dirty = false;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, parent2child);
        drawTree(g2d, this.AVLtree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE,
                fontMetrics.getLeading() + fontMetrics.getAscent());
        fontMetrics = null;
    }//paintComponent

}
