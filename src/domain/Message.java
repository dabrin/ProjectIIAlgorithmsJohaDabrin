/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import structs.MyNode;
import java.util.ArrayList;
import java.util.Collections;
import tree.AVLTree;

/**
 *
 * @author dabri
 */
public class Message {

    public int[] ASCII;
    public String originalMessage;
    public String[] separatedMessage;
    public String[] finalMessage;
    public String[] wordPosition;
    public String[] buildMessage;
    public String[] guardarPosi;
    public String buildMessage2;
    public String unix;
    public AVLTree tree;

    public Message() {
        this.tree = new AVLTree();
    }//contructor

    public void createTree(String message) {
        this.originalMessage = message;
        this.separatedMessage = this.originalMessage.split("[ \n]");
        String reducedMessage = "";

        //quitar las palabras repetidas
        for (int i = 0; i < this.separatedMessage.length; i++) {
            String[] temp = reducedMessage.split(" ");
            int count = 0;
            for (int j = 0; j < temp.length; j++) {
                if (!this.separatedMessage[i].equals(temp[j])) {
                    count++;
                }
            }//recorrer el mensaje reducido para comparalo con el mensaje original
            if (count == temp.length) {
                reducedMessage += this.separatedMessage[i] + " ";
            }
        }//recorrer cada palabra del mensaje original
        reducedMessage = reducedMessage.substring(0, reducedMessage.length() - 1);

        //ver en cuales posiciones esta una palabra
        this.finalMessage = reducedMessage.split(" ");
        this.wordPosition = new String[this.finalMessage.length];
        for (int i = 0; i < finalMessage.length; i++) {
            String position = "";
            for (int j = 0; j < this.separatedMessage.length; j++) {
                if (finalMessage[i].equals(this.separatedMessage[j])) {
                    position += j + ",";
                }//preguntamos en que posciones se encuentra la palabra para guardar sus pocisiones
            }////recorrer cada palabra del mensaje original
            position = position.substring(0, position.length() - 1);
            wordPosition[i] = position;
        }////recorrer cada palabra del mensaje reducido

        //crear ASCII
        ASCII = new int[this.finalMessage.length];
        int count = 0;
        int temp = 0;

        for (int i = 0; i < this.finalMessage.length; i++) {
            for (int j = 0; j < ASCII.length; j++) {
                if(!finalMessage[i].equals("")){
                while (ASCII[j] == finalMessage[i].codePointAt(count) + temp) {
                    j = 0;
                    count++;
                    if (count >= finalMessage[i].length()) {
                        temp++;
                        j = 0;
                        count--;
                    }//preguntamos que si el contador es mas grande que el tamaño de la palabra
                }//comparar el nuevo codigo con los codigos ya guardados
                }
            }//recorrer los codigos ya guardados
            if(!finalMessage[i].equals("")){
                int r = finalMessage[i].codePointAt(count) + temp;
                ASCII[i] = r;
                System.out.println(finalMessage[i]);
                System.out.println(ASCII[i]);
            }
            count = 0;
            temp = 0;

        }////recorrer cada palabra del mensaje reducido

        Word[] words = new Word[ASCII.length];
        for (int i = 0; i < ASCII.length; i++) {
            words[i] = new Word(ASCII[i], finalMessage[i], wordPosition[i]);
        }//Llenar arbol
        words = Word.selectionWords(words);
        for (int i = 0; i < words.length; i++) {
            tree.insert(words[i].ASCII, words[i].word, words[i].positon);
        }

    }//createTree

    public void buildMessage(String message, String messageUnix) {
        String[] text = message.split("\n");
        String[] text2 = messageUnix.split(" ");
        int quantityWords = 0;
        
        //saber el tamaño del texto original
        for (int i = 0; i < text.length; i++) {
            String[] temp = text[i].split(" ");
            String[] quantityPosition = temp[1].split(",");
            quantityWords += quantityPosition.length;
        }//se recorre el texto que viene con todo y posiciones
        quantityWords += text2.length;

        //armar texto
        buildMessage = new String[quantityWords];

        for (int i = 0; i < text.length; i++) {
            String[] temp = text[i].split(" ");
            String[] position = temp[1].split(",");
            for (int j = 0; j < position.length; j++) {
                switch (temp[0]) {
                    case "*":
                        this.buildMessage[Integer.parseInt(position[j])] = "de";
                        break;
                    case "+":
                        this.buildMessage[Integer.parseInt(position[j])] = "la";
                        break;
                    case "_":
                        this.buildMessage[Integer.parseInt(position[j])] = "el";
                        break;
                    case "&":
                        this.buildMessage[Integer.parseInt(position[j])] = "las";
                        break;
                    case "%":
                        this.buildMessage[Integer.parseInt(position[j])] = "los";
                        break;
                    case "$":
                        this.buildMessage[Integer.parseInt(position[j])] = "un";
                        break;
                    case "#":
                        this.buildMessage[Integer.parseInt(position[j])] = "en";
                        break;
                    case "°":
                        this.buildMessage[Integer.parseInt(position[j])] = "que";
                        break;
                    case "|":
                        this.buildMessage[Integer.parseInt(position[j])] = "con";
                        break;
                    case "^":
                        this.buildMessage[Integer.parseInt(position[j])] = "al";
                        break;
                    case "~":
                        this.buildMessage[Integer.parseInt(position[j])] = "es";
                        break;
                    case "<":
                        this.buildMessage[Integer.parseInt(position[j])] = "para";
                        break;
                    case ">":
                        this.buildMessage[Integer.parseInt(position[j])] = "por";
                        break;
                    case "t":
                        this.buildMessage[Integer.parseInt(position[j])] = "La";
                        break;
                    case "f":
                        this.buildMessage[Integer.parseInt(position[j])] = "una";
                        break;
                    case "w":
                        this.buildMessage[Integer.parseInt(position[j])] = "del";
                        break;
                    case "ñ":
                        this.buildMessage[Integer.parseInt(position[j])] = "Para";
                        break;
                    case "g":
                        this.buildMessage[Integer.parseInt(position[j])] = "su";
                        break;
                    case "v":
                        this.buildMessage[Integer.parseInt(position[j])] = "El";
                        break;
                    case "j":
                        this.buildMessage[Integer.parseInt(position[j])] = "este";
                        break;
                    case "k":
                        this.buildMessage[Integer.parseInt(position[j])] = "entre";
                        break;
                    default:
                        this.buildMessage[Integer.parseInt(position[j])] = temp[0];
                        break;
                }//traducir texto
            }//recorrer las posiciones de cada palabra
        }//recorrer los nodos que tiene el arbol

        int cont = 0;
        for (int i = 0; i < buildMessage.length; i++) {
            if (buildMessage[i] == null) {
                switch (text2[cont]) {
                    case "*":
                        this.buildMessage[i] = "de";
                        break;
                    case "+":
                        this.buildMessage[i] = "la";
                        break;
                    case "_":
                        this.buildMessage[i] = "el";
                        break;
                    case "&":
                        this.buildMessage[i] = "las";
                        break;
                    case "%":
                        this.buildMessage[i] = "los";
                        break;
                    case "$":
                        this.buildMessage[i] = "un";
                        break;
                    case "#":
                        this.buildMessage[i] = "en";
                        break;
                    case "°":
                        this.buildMessage[i] = "que";
                        break;
                    case "|":
                        this.buildMessage[i] = "con";
                        break;
                    case "^":
                        this.buildMessage[i] = "al";
                        break;
                    case "~":
                        this.buildMessage[i] = "es";
                        break;
                    case "<":
                        this.buildMessage[i] = "para";
                        break;
                    case ">":
                        this.buildMessage[i] = "por";
                        break;
                    case "t":
                        this.buildMessage[i] = "La";
                        break;
                    case "f":
                        this.buildMessage[i] = "una";
                        break;
                    case "w":
                        this.buildMessage[i] = "del";
                        break;
                    case "ñ":
                        this.buildMessage[i] = "Para";
                        break;
                    case "g":
                        this.buildMessage[i] = "su";
                        break;
                    case "v":
                        this.buildMessage[i] = "El";
                        break;
                    case "j":
                        this.buildMessage[i] = "este";
                        break;
                    case "k":
                        this.buildMessage[i] = "entre";
                        break;
                    default:
                        this.buildMessage[i] = text2[cont];
                        break;
                }//traducir texto2
                cont++;
            }//saber si la posicion del vector esta vacia
        }//recorrer el vector en donde estamos almacenando el texto 

        //Construir texto apartir del vector
        buildMessage2 = "";

        for (int i = 0; i < this.buildMessage.length; i++) {
            buildMessage2 += this.buildMessage[i] + " ";
        }//llenar el mensaje
        buildMessage2 = buildMessage2.substring(0, buildMessage2.length() - 1);

    }//createMessage

    public void saveTree(String fileName) {

        this.tree.getNodes(this.tree.getRoot());

        int size = this.tree.queue.size;

        buildMessage2 = "";
        unix = "";

        String savePositions = "";

        for (int i = 0; i < size; i++) {
            MyNode node = this.tree.queue.dequeue();
            this.tree.queue.enqueue(node.nodeTree);
            int tamaño = node.nodeTree.word.length() + node.nodeTree.position.length();
            String[] posiciones = node.nodeTree.position.split(",");
            int tamaño2 = node.nodeTree.word.length() * posiciones.length;
            if (tamaño <= tamaño2) {
                switch (node.nodeTree.word) {
                    case "de":
                        buildMessage2 += "*" + " " + node.nodeTree.position + "\n";
                        break;
                    case "la":
                        buildMessage2 += "+" + " " + node.nodeTree.position + "\n";
                        break;
                    case "el":
                        buildMessage2 += "_" + " " + node.nodeTree.position + "\n";
                        break;
                    case "las":
                        buildMessage2 += "&" + " " + node.nodeTree.position + "\n";
                        break;
                    case "los":
                        buildMessage2 += "%" + " " + node.nodeTree.position + "\n";
                        break;
                    case "un":
                        buildMessage2 += "$" + " " + node.nodeTree.position + "\n";
                        break;
                    case "en":
                        buildMessage2 += "#" + " " + node.nodeTree.position + "\n";
                        break;
                    case "que":
                        buildMessage2 += "°" + " " + node.nodeTree.position + "\n";
                        break;
                    case "con":
                        buildMessage2 += "|" + " " + node.nodeTree.position + "\n";
                        break;
                    case "al":
                        buildMessage2 += "^" + " " + node.nodeTree.position + "\n";
                        break;
                    case "es":
                        buildMessage2 += "~" + " " + node.nodeTree.position + "\n";
                        break;
                    case "para":
                        buildMessage2 += "<" + " " + node.nodeTree.position + "\n";
                        break;
                    case "por":
                        buildMessage2 += ">" + " " + node.nodeTree.position + "\n";
                        break;
                    case "La":
                        buildMessage2 += "t" + " " + node.nodeTree.position + "\n";
                        break;
                    case "una":
                        buildMessage2 += "f" + " " + node.nodeTree.position + "\n";
                        break;
                    case "del":
                        buildMessage2 += "w" + " " + node.nodeTree.position + "\n";
                        break;
                    case "Para":
                        buildMessage2 += "ñ" + " " + node.nodeTree.position + "\n";
                        break;
                    case "su":
                        buildMessage2 += "g" + " " + node.nodeTree.position + "\n";
                        break;
                    case "El":
                        buildMessage2 += "v" + " " + node.nodeTree.position + "\n";
                        break;
                    case "este":
                        buildMessage2 += "j" + " " + node.nodeTree.position + "\n";
                        break;
                    case "entre":
                        buildMessage2 += "k" + " " + node.nodeTree.position + "\n";
                        break;
                    default:
                        buildMessage2 += node.nodeTree.word + " " + node.nodeTree.position + "\n";
                        break;
                }//traducir texto para lograr menos bits
            } else {
                for (int j = 0; j < posiciones.length; j++) {
                    savePositions += posiciones[j] + ",";
                }
            }//if-else verificar si es mejor guardar la palabra sola o con las posiciones
        }//recorrer los nodos que tiene el arbol

        buildMessage2 = buildMessage2.substring(0, buildMessage2.length() - 1);

        //acomodar las palabras en el orden que van
        String[] ordernUnix1 = savePositions.split(",");
        ArrayList<Integer> orderUnix = new ArrayList<>();
        for (int i = 0; i < ordernUnix1.length; i++) {
            orderUnix.add(Integer.parseInt(ordernUnix1[i]));
        }//se convierte las posiciones de string a int

        Collections.sort(orderUnix);

        for (int i = 0; i < size; i++) {
            MyNode node = this.tree.queue.dequeue();
            this.tree.queue.enqueue(node.nodeTree);
            int tamaño = node.nodeTree.word.length() + node.nodeTree.position.length();
            String[] posiciones = node.nodeTree.position.split(",");
            int tamaño2 = node.nodeTree.word.length() * posiciones.length;
            if (tamaño > tamaño2) {
                for (int j = 0; j < posiciones.length; j++) {
                    for (int k = 0; k < orderUnix.size(); k++) {
                        if (orderUnix.get(k) == Integer.parseInt(posiciones[j])) {
                            ordernUnix1[k] = node.nodeTree.word;
                        }//validar la posicion de la palabra de acuerdo a la que tiene el vector
                    }//se recorre el vector de posiciones ya ordenado
                }//recorrer las posiciones de la palabra
            }//si es mejor guardar la palabra solo o con las posiciones
        }//recuorro el temaño del texto original

        for (int i = 0; i < ordernUnix1.length; i++) {
            switch (ordernUnix1[i]) {
                case "de":
                    unix += "*" + " ";
                    break;
                case "la":
                    unix += "+" + " ";
                    break;
                case "el":
                    unix += "_" + " ";
                    break;
                case "las":
                    unix += "&" + " ";
                    break;
                case "los":
                    unix += "%" + " ";
                    break;
                case "un":
                    unix += "$" + " ";
                    break;
                case "en":
                    unix += "#" + " ";
                    break;
                case "que":
                    unix += "°" + " ";
                    break;
                case "con":
                    unix += "|" + " ";
                    break;
                case "El":
                    unix += "v" + " ";
                    break;
                case "este":
                    unix += "j" + " ";
                    break;
                case "entre":
                    unix += "k" + " ";
                    break;
                case "al":
                    unix += "^" + " ";
                    break;
                case "La":
                    unix += "t" + " ";
                    break;
                case "una":
                    unix += "f" + " ";
                    break;
                case "del":
                    unix += "w" + " ";
                    break;
                case "Para":
                    unix += "ñ" + " ";
                    break;
                case "su":
                    unix += "g" + " ";
                    break;
                case "es":
                    unix += "~" + " ";
                    break;
                case "para":
                    unix += "<" + " ";
                    break;
                case "por":
                    unix += ">" + " ";
                    break;
                default:
                    unix += ordernUnix1[i] + " ";
                    break;
            }//traducir texto para lograr menos bits
        }//se recorre el texto que almacena las palabras solas

        unix = unix.substring(0, unix.length() - 1);
    }//saveTree

    public String[] getGuardarPosi() {
        return guardarPosi;
    }//getGuardarPosi

    public void setGuardarPosi(String[] guardarPosi) {
        this.guardarPosi = guardarPosi;
    }//setGuardarPosi

}//class
