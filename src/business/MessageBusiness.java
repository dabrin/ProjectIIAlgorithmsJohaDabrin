/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.MessageData;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author dabri
 */
public class MessageBusiness {

    private MessageData data;
    private String message;

    public MessageBusiness(String nameFile) {
        this.data = new MessageData(nameFile);
        this.message = "";
    }//constructor

    public void saveMessage(String message, int r) throws FileNotFoundException {
        this.data.saveMessage(message, r);
    }//saveMessage
    
    public void readMessage() throws IOException {
        data.readMessage();
        this.message = this.data.getMessage();
    }//leerMensaje

    public String getMessage() {
        return message;
    }//getMessage

    public void setMessage(String message) {
        this.message = message;
    }//setMessage

}//class
