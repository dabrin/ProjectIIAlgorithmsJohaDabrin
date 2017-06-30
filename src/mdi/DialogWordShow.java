/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author monge55
 */
public class DialogWordShow extends JDialog implements ActionListener {

    private JButton jbtnOk;
    private JLabel jlMessage;
    private Font font;
    private MyPanel panel;
    public String word;

    public DialogWordShow(JFrame parent, boolean modal, String word) {
        super(parent, modal);
        this.setSize(350, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setUndecorated(true);
        this.word = word;
        init();
    }//constructor

    public void init() {
        //rgb(0,128,0)
        this.panel = new MyPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 350, 200);

        this.font = new Font("Verdana", Font.BOLD, 13);

        this.jlMessage = new JLabel(this.word);
        this.jlMessage.setForeground(Color.black);
        this.jlMessage.setFont(font);
        this.jlMessage.setBorder(null);
        this.jlMessage.setBounds(125, 17, 300, 20);

        this.jbtnOk = new JButton("Accept");
        this.jbtnOk.setFont(font);
        this.jbtnOk.setBorder(null);
        this.jbtnOk.setBounds(125, 100, 100, 20);
        this.jbtnOk.setBackground(Color.black);
        this.jbtnOk.addActionListener(this);

        this.panel.add(this.jlMessage);
        this.panel.add(this.jbtnOk);

        this.add(this.panel);
    }//init

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbtnOk) {
            this.dispose();
        }//boton seleccionado
    }//actionPerformed

}//class
