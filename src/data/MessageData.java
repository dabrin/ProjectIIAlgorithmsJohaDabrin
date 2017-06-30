/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author dabri
 */
public class MessageData {

    private String nameFile;
    private String message;

    public MessageData(String nameFile) {
        this.nameFile = nameFile;
        this.message = "";
    }//constructor

    public void saveMessage(String message, int r) throws FileNotFoundException {
        File file = new File(this.nameFile + r);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.print(message);
        printStream.close();
    }//saveMessage

    public void readMessage() throws FileNotFoundException, IOException {
        File file = new File(this.nameFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        setMessage("");
        while ((linea = bufferedReader.readLine()) != null) {
            this.message += linea;
            this.message += "\n";
        }//while
        this.message = this.message.substring(0, this.message.length()-1);
    }//leerMensaje

    public String getMessage() {
        return message;
    }//getMessage

    public void setMessage(String message) {
        this.message = message;
    }//setMessage

}//class
