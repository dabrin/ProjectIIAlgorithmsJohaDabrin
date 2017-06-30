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
import javax.swing.JTextField;

/**
 *
 * @author dabri
 */
public class DialogSearchWord extends JDialog implements ActionListener{

    private JButton jbtnOk;
    private JLabel jlMessage;
    private JTextField jtfMessage;
    private Font font;
    private MyPanel panel;
    public String word;
    
    public DialogSearchWord(JFrame parent, boolean modal) {
        super(parent, modal);
        this.setSize(350, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setUndecorated(true);
        init();
    }//constructor
    
    public void init(){
        
        this.panel = new MyPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 350, 200);
        
        this.font = new Font("Verdana", Font.BOLD, 13);
        
        this.jlMessage = new JLabel("Enter the word to search");
        this.jlMessage.setForeground(Color.black);
        this.jlMessage.setFont(font);
        this.jlMessage.setBorder(null);
        this.jlMessage.setBounds(83, 17, 300, 20);
        
        this.jtfMessage = new JTextField();
        this.jtfMessage.setBorder(null);
        this.jtfMessage.setBounds(125, 85, 100, 20);
        
        this.jbtnOk = new JButton("Search");
        this.jbtnOk.setFont(font);
        this.jbtnOk.setBorder(null);
        this.jbtnOk.setBounds(125, 150, 100, 20);
        this.jbtnOk.setBackground(Color.black);
        this.jbtnOk.addActionListener(this);
        
        this.panel.add(this.jlMessage);
        this.panel.add(this.jbtnOk);
        this.panel.add(this.jtfMessage);
        
        this.add(this.panel);
    }//init
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.jbtnOk){
            if(!this.jtfMessage.getText().equals("")){
                this.word = this.jtfMessage.getText();
                this.dispose();
            }//validar textfield
        }//boton seleccionado
    }//actionPerformed
    
}//class
