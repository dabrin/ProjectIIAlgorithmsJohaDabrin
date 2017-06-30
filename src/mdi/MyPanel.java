/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdi;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author dabri
 */
public class MyPanel extends JPanel {

    public static boolean image = true;
    private BufferedImage bi;

    @Override
    protected void paintComponent(Graphics g) {
        if (image) {
            try {
                this.bi = ImageIO.read(getClass().getResourceAsStream("/assets/font.jpg"));
            } catch (IOException ex) {
                Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            g.drawImage(bi, 0, 0, 1366, 768, null);
        } else {
            try {
                this.bi = ImageIO.read(getClass().getResourceAsStream("/assets/treeSearch.png"));
            } catch (IOException ex) {
                Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            g.drawImage(bi, 0, 0, 350, 200, null);
        }//tipo de imagen y tamaño a pintar
    }//paintComponent

}//class
