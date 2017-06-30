/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author monge55
 */
public class Word {

    public int ASCII;
    public String word;
    public String positon;

    public Word(int ASCII, String word, String positon) {
        this.ASCII = ASCII;
        this.word = word;
        this.positon = positon;
    }//constructor

    public static Word[] selectionWords(Word[] words) {
        
          int i, j,  pos;
          Word word, tmp;
          for (i = 0; i < words.length - 1; i++) { // tomamos como menor el primero
                word = words[i]; // de los elementos que quedan por ordenar
                pos = i; // y guardamos su posición
                for (j = i + 1; j < words.length; j++){ // buscamos en el resto
                      if (words[j].ASCII < word.ASCII) { // del array algún elemento
                          word = words[j]; // menor que el actual
                          pos = j;
                      }
                }
                if (pos != i){ // si hay alguno menor se intercambia
                    tmp = words[i];
                    words[i] = words[pos];
                    words[pos] = tmp;
                }
          }
        return words;
    }//selectionWords

}//class
