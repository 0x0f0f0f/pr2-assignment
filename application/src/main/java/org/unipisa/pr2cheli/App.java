package org.unipisa.pr2cheli;

import main.java.org.unipisa.pr2cheli.Data;

/*
 * Main
 */
public class App {
    public static void main(String[] args) {
        try {
            Data x = new Data("ale", "prova testo satana 666");
            System.out.println(x.display());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}