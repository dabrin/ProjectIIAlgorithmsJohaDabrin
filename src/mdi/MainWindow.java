/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdi;

import business.MessageBusiness;
import domain.Message;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import tree.AVLNode;
import tree.AVLTree;

/**
 *
 * @author dabri
 */
public class MainWindow extends JFrame implements ActionListener {

    private Font font;

    private JButton jbtnOpen;
    private JButton jbtnSave;
    private JButton jbtnSearchWord;
    private JButton jbtnCreateTree;
    private JButton jbtnBuildText;

    private JTextArea jtaMessage;
    private JScrollPane scroll;
    private JScrollPane scrollTree;

    private JFileChooser chooser;
    private MessageBusiness business;

    private MyPanel panel;
    private MyTreeGraphics graphics;
    private DialogSearchWord searchWord;
    private DialogWordShow wordShow;

    private Message message;

    private AVLTree tree;

    public MainWindow() {
        super("File reader in trees");
        Dimension dim=super.getToolkit().getScreenSize();
        super.setSize(dim);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
    }//constructor

    public void init() {

        this.font = new Font("Verdana", Font.BOLD, 13);

        this.jbtnOpen = new JButton("Open File");
        this.jbtnOpen.setFont(font);
        this.jbtnOpen.setBorder(null);
        this.jbtnOpen.setBounds(10, 150, 110, 30);
        this.jbtnOpen.setBackground(Color.white);
        this.jbtnOpen.addActionListener(this);

        this.jbtnSave = new JButton("Save File");
        this.jbtnSave.setFont(font);
        this.jbtnSave.setBorder(null);
        this.jbtnSave.setBounds(10, 200, 110, 30);
        this.jbtnSave.setBackground(Color.white);
        this.jbtnSave.addActionListener(this);
        this.jbtnSave.setEnabled(false);

        this.jbtnCreateTree = new JButton("Create Tree");
        this.jbtnCreateTree.setFont(font);
        this.jbtnCreateTree.setBorder(null);
        this.jbtnCreateTree.setBounds(10, 250, 130, 30);
        this.jbtnCreateTree.setBackground(Color.white);
        this.jbtnCreateTree.addActionListener(this);
        this.jbtnCreateTree.setEnabled(false);
        
        this.jbtnBuildText = new JButton("Build Text");
        this.jbtnBuildText.setFont(font);
        this.jbtnBuildText.setBorder(null);
        this.jbtnBuildText.setBounds(10, 300, 110, 30);
        this.jbtnBuildText.setBackground(Color.white);
        this.jbtnBuildText.addActionListener(this);

        this.jbtnSearchWord = new JButton("Search Word");
        this.jbtnSearchWord.setFont(font);
        this.jbtnSearchWord.setBorder(null);
        this.jbtnSearchWord.setBounds(10, 350, 110, 30);
        this.jbtnSearchWord.setBackground(Color.white);
    
        this.jbtnSearchWord.addActionListener(this);
        this.jbtnSearchWord.setEnabled(false);
       

        this.jtaMessage = new JTextArea();
        this.jtaMessage.setLineWrap(true);

        this.scroll = new JScrollPane(this.jtaMessage);
        this.scroll.setBorder(null);
        this.scroll.setBounds(new Rectangle(200, 90, 1000, 600));
        this.scroll.setVisible(false);

        this.scrollTree = new JScrollPane();
        this.scrollTree.setBounds(new Rectangle(200, 90, 1000, 600));
        this.scrollTree.setBorder(null);
        this.scrollTree.setOpaque(false);
        this.scrollTree.setVisible(false);

        this.panel = new MyPanel();
        this.panel.setSize(new Dimension(1366, 768));
        this.panel.setLayout(null);

        this.panel.add(this.jbtnOpen);
        this.panel.add(this.jbtnSave);
        this.panel.add(this.jbtnCreateTree);
        this.panel.add(this.jbtnBuildText);
        this.panel.add(this.jbtnSearchWord);
        this.panel.add(this.scroll);
        this.panel.add(this.scrollTree);

        this.add(this.panel);
        try {
            this.jbtnOpen.setIcon(new ImageIcon((Image) ImageIO.read(getClass().getResourceAsStream("/assets/folder1.png"))));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.jbtnSave.setIcon(new ImageIcon((Image) ImageIO.read(getClass().getResourceAsStream("/assets/saveF.png"))));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.jbtnBuildText.setIcon(new ImageIcon((Image) ImageIO.read(getClass().getResourceAsStream("/assets/back.png"))));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.jbtnSearchWord.setIcon(new ImageIcon((Image) ImageIO.read(getClass().getResourceAsStream("/assets/search.png"))));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            this.jbtnCreateTree.setIcon(new ImageIcon((Image) ImageIO.read(getClass().getResourceAsStream("/assets/create.png"))));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    

    }//init
     private void saveFileChooser() {
        String rute;
        chooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("txt", "TXT");
        chooser.setFileFilter(extensionFilter);
        try {
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                rute = chooser.getSelectedFile().getAbsolutePath();
                AVLTree tree1=new AVLTree();
               // tree1.saveTree(aVLNode);
            }//if
        } catch (Exception ex) {
        }//try-catch

    }
    boolean flag = true;
    int [] guardarPosi;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbtnOpen) {
            MyPanel.image = true;
            this.chooser = new JFileChooser();
            String rute;
            if (this.chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    rute = this.chooser.getSelectedFile().getAbsolutePath();
                    business = new MessageBusiness(rute);
                    business.readMessage();
                    this.jtaMessage.setText(business.getMessage());
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.scroll.setVisible(true);
                this.scrollTree.setVisible(false);
                this.jbtnSearchWord.setEnabled(false);
                this.jbtnSave.setEnabled(false);
                this.jbtnCreateTree.setEnabled(true);
                this.flag = true;
            } else {
            }//if para saber si se escogio algún archivo
        } else if (e.getSource() == this.jbtnSave) {
            MyPanel.image = true;
            this.chooser = new JFileChooser();
            String rute;
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("TXT", "txt");
            chooser.setFileFilter(extensionFilter);

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    rute = chooser.getSelectedFile().getAbsolutePath();
                    business = new MessageBusiness(rute);
                    String[] ruteFile = rute.split("/");
                    String fileName = ruteFile[ruteFile.length-1];
                    this.message.saveTree(fileName);
                    business.saveMessage(this.message.buildMessage2, 1);
                 
                } catch (Exception ex) {
                }
                this.scroll.setVisible(false);
                this.scrollTree.setVisible(true);
                this.jbtnSave.setEnabled(false);
                JOptionPane.showMessageDialog(null, "File save");
            } else {
            }//if para saber si guardo el archivo
        } else if (e.getSource() == this.jbtnCreateTree) {
            MyPanel.image = true;
            this.message = new Message();
            this.message.createTree(this.jtaMessage.getText());
            this.tree = this.message.tree;
            this.scroll.setVisible(false);
            this.graphics = new MyTreeGraphics(tree);
            this.scrollTree.setViewportView(this.graphics);
            this.scrollTree.getViewport().setView(this.graphics);
            this.graphics.setPreferredSize(new Dimension(9000, 9000));
            this.scrollTree.setVisible(true);
            this.panel.repaint();
            this.panel.revalidate();
            if (flag) {
                this.jbtnSearchWord.setEnabled(true);
                this.jbtnSave.setEnabled(true);
                this.jbtnCreateTree.setEnabled(false);
                flag = false;
            } else {
                this.jbtnSearchWord.setEnabled(true);
                this.jbtnSave.setEnabled(false);
                this.jbtnCreateTree.setEnabled(false);
            }
        } else if (e.getSource() == this.jbtnBuildText) {
            MyPanel.image = true;
            this.chooser = new JFileChooser();
            String rute;
            String fileName = "";
            if (this.chooser.showDialog(null, "Escoja el archivo 1") == JFileChooser.APPROVE_OPTION) {   
            this.message = new Message();
            String message1 = "";
            String message2 = "";
                try {
                    rute = this.chooser.getSelectedFile().getAbsolutePath();
                    business = new MessageBusiness(rute);
                    business.readMessage();
                    message1 = business.getMessage();
                    String[] ruteFile = rute.split("/");
                    fileName = ruteFile[ruteFile.length-1];
                    char[] temp = fileName.toCharArray();
                    if(temp[temp.length - 1] == '1'){
                        temp[temp.length - 1] = '2';
                    }
                    
                    String filenew = "";
                    for (int i = 0; i < temp.length; i++) {
                        filenew += temp[i];
                    }
                    ruteFile[ruteFile.length -1] = filenew;
                    
                    String rute2 = "";
                    for (int i = 0; i < ruteFile.length; i++) {
                        rute2 += "/"+ruteFile[i];
                    }
                    business = new MessageBusiness(rute2);
                    business.readMessage();
                    message2 = business.getMessage();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.message.buildMessage(message1, message2);
                this.jtaMessage.setText(this.message.buildMessage2);
                this.scroll.setVisible(true);
                this.scrollTree.setVisible(false);
                this.jbtnCreateTree.setEnabled(true);
                this.jbtnSave.setEnabled(false);
                this.jbtnSearchWord.setEnabled(false);
                this.panel.repaint();
                this.panel.revalidate();
            } else {
            }//if para saber si se escogio algún archivo
        } else if (e.getSource() == this.jbtnSearchWord) {
            MyPanel.image = false;
            this.searchWord = new DialogSearchWord(this, true);
            this.searchWord.setLocationRelativeTo(null);
            this.searchWord.setVisible(true);
            this.tree.getNodes(this.tree.getRoot());
            int size = this.tree.queue.size;
            boolean l= true;
            for (int i = 0; i < size; i++) {
                AVLNode node = this.tree.queue.dequeue().nodeTree;
                if (node.word.equals(this.searchWord.word)) {
                    this.wordShow = new DialogWordShow(this, true, node.word + " [" + node.position + "]");
                    this.wordShow.setLocationRelativeTo(null);
                    this.wordShow.setVisible(true);
                    i = size;
                    l=false;
                }
            }
            if(l){
                this.wordShow = new DialogWordShow(this, true, "The word it's not found");
                    this.wordShow.setLocationRelativeTo(null);
                    this.wordShow.setVisible(true);
            }
        }//botones
    }//actionPerformed

}//class
