package org.unipisa.pr2cheli;

import java.util.Iterator;

/*
 * Main
 */
public class App {
    public static void main(String[] args) {
        try {
            System.out.println("\n===> Batteria di test di esempio");

            // Costanti
            String USER_INVALID = "--asdgUTU";
            String TEXT_INVALID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            String PW_ALE       = "passwordbella";
            String PW_GIORGIO   = "password2";
            String PW_CHIARA    = "abcabc";
            String PW_SARA      = "qwerty";
            String PW_INVALID   = TEXT_INVALID;

            // variabili locali
            Board<DataElement> temp_board;
            Board<DataElement> board_ale =     new Board<DataElement>("ale",       PW_ALE);
            Board<DataElement> board_giorgio = new Board<DataElement>("giorgio",   PW_GIORGIO);
            Board<DataElement> board_chiara =  new Board<DataElement>("chiara",    PW_CHIARA);
            Board<DataElement> board_sara =    new Board<DataElement>("sara",      PW_SARA);
            DataElement temp_element;
            DataElement element_tolike;
            DataElement element_tolike2;

            /* ===========================
                TEST BOARDS
               =========================== */
            System.out.println("\n===> Aggiungo board con utenti di test...");
            try {
                System.out.println("\n===> Inserisco un username invalido. Mi aspetto InvalidDataException");
                temp_board = new Board<DataElement>(USER_INVALID, "INVALID");
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Inserisco un username vuoto. Mi aspetto InvalidDataException");
                temp_board = new Board<DataElement>("", "INVALID");
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Inserisco un username troppo lungo. Mi aspetto InvalidDataException");
                temp_board = new Board<DataElement>(PW_INVALID, "INVALID");
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Inserisco una password invalida. Mi aspetto InvalidDataException");
                temp_board = new Board<DataElement>("INVALID", PW_INVALID);
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Inserisco una password vuota. Mi aspetto InvalidDataException");
                temp_board = new Board<DataElement>("INVALID", "");
            } catch (Exception e) {
                System.err.println(e);
            }

            /* ===================================================
                TEST CATEGORIES
               ================================================= */
            System.out.println("\n===> Aggiungo categorie alle board");
            board_ale.createCategory("main",    PW_ALE);
            board_ale.createCategory("cani",    PW_ALE);
            board_ale.createCategory("gatti",       PW_ALE);
            board_giorgio.createCategory("main",    PW_GIORGIO);
            board_giorgio.createCategory("cani",    PW_GIORGIO);
            board_giorgio.createCategory("gatti",   PW_GIORGIO);
            board_chiara.createCategory("main",     PW_CHIARA);
            board_chiara.createCategory("cani",     PW_CHIARA);
            board_chiara.createCategory("gatti",    PW_CHIARA);
            board_sara.createCategory("main",       PW_SARA);
            board_sara.createCategory("cani",       PW_SARA);
            board_sara.createCategory("gatti",      PW_SARA);
            board_sara.createCategory("remove",     PW_SARA);
            board_sara.removeCategory("remove",     PW_SARA);
            try {
                System.out.println("\n===> Inserisco una categoria già esistente. Mi aspetto DuplicateDataException");
                board_sara.createCategory("gatti",  PW_SARA);
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Rimuovo una categoria non esistente. Mi aspetto DataNotFoundException");
                board_ale.removeCategory("nonesisto",  PW_SARA);
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Inserisco una password errata. Mi aspetto UnauthorizedLoginException");
                board_ale.createCategory("invalid", PW_GIORGIO);
            } catch (Exception e) {
                System.err.println(e);
            }

            /* ===================================================
                TEST POSTS
               ================================================= */
            System.out.println("\n===> Aggiungo post alla bacheca di ale");
            temp_element = new DataElement("ale", "hello world");
            // Utilizzato nel test dei like
            element_tolike = temp_element;
            System.out.println(temp_element.display());
            board_ale.put(PW_ALE, temp_element, "main");
            element_tolike.setCategory("main");
            temp_element = new DataElement("ale", "hello gatto");
            System.out.println(temp_element.display());
            board_ale.put(PW_ALE, temp_element, "gatti");
            temp_element = new DataElement("ale", "hello cane");
            System.out.println(temp_element.display());
            board_ale.put(PW_ALE, temp_element, "cani");
            System.out.println("\n===> Inserisco un post duplicato in due categorie diverse");
            temp_element = new DataElement("ale", "duplicato 1");
            System.out.println(temp_element.display());
            board_ale.put(PW_ALE, temp_element, "main");
            board_ale.put(PW_ALE, temp_element, "gatti");
            System.out.println("\n===> Ritiro di un post dalla board");
            temp_element = board_ale.get(PW_ALE, temp_element);
            System.out.println(temp_element.display());
            System.out.println("\n===> Rimuovo l'ultimo post dalla board");
            temp_element = board_ale.remove(PW_ALE, temp_element);
            try {
                System.out.println("\n===> Provo ad accedere al post rimosso. Mi aspetto DataNotFoundException");
                board_ale.get(PW_ALE, temp_element);
                System.out.println(temp_element.display());
            } catch (Exception e) {
                System.err.println(e);
            }

            try {
                System.out.println("\n===> Inserisco un post con testo errato. Mi aspetto InvalidDataException");
                temp_element = new DataElement("ale", TEXT_INVALID);
                board_ale.put(PW_ALE, temp_element, "main");
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Inserisco un post con autore diverso dall'owner della board. Mi aspetto InvalidDataException");
                temp_element = new DataElement("giorgio", "non sono alessandro");
                board_ale.put(PW_ALE, temp_element, "main");
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Inserisco un post in una categoria non esistente. Mi aspetto DataNotFoundException");
                temp_element = new DataElement("ale", "categoria errata");
                board_ale.put(PW_ALE, temp_element, "nonesisto");
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Inserisco un post duplicato. Mi aspetto DuplicateDataException");
                temp_element = new DataElement("ale", "duplicato 2");
                board_ale.put(PW_ALE, temp_element, "main");
                board_ale.put(PW_ALE, temp_element, "main");
            } catch (Exception e) {
                System.err.println(e);
            }

            element_tolike2 = temp_element;
            element_tolike2.setCategory("main");

            try {
                System.out.println("\n===> Recupero un post non esistente dalla board. Mi aspetto DataNotFoundException");
                temp_element = new DataElement("ale", "nonesistoperniente");
                board_ale.get(PW_ALE, temp_element);
                System.out.println(temp_element.display());
            } catch (Exception e) {
                System.err.println(e);
            }
            try {
                System.out.println("\n===> Rimuovo un post non esistente dalla board. Mi aspetto DataNotFoundException");
                temp_element = new DataElement("ale", "nonesistoperniente");
                board_ale.get(PW_ALE, temp_element);
                System.out.println(temp_element.display());
            } catch (Exception e) {
                System.err.println(e);
            }

            /* ===================================================
                TEST FRIENDS
               ================================================= */

            System.out.println("\n===> Aggiungo amici alla bacheca di ale");
            board_ale.addFriend("main", PW_ALE, "giorgio");
            board_ale.addFriend("main", PW_ALE, "sara");
            board_ale.addFriend("main", PW_ALE, "chiara");
            board_ale.removeFriend("main", PW_ALE, "chiara");

            try {
                System.out.println("\n===> Rimuovo un amico non esistente dalla categoria. Mi aspetto DataNotFoundException");
                board_ale.removeFriend("main", PW_ALE, "chiara");
            } catch (Exception e) {
                System.err.println(e);
            }

            try {
                System.out.println("\n===> Aggiungo un amico ad una categoria non esistente. Mi aspetto DataNotFoundException");
                board_ale.addFriend("nonesisto", PW_ALE, "giorgio");
            } catch (Exception e) {
                System.err.println(e);
            }

            try {
                System.out.println("\n===> Aggiungo un amico duplicato alla categoria. Mi aspetto DuplicateDataException");
                board_ale.addFriend("main", PW_ALE, "giorgio");
            } catch (Exception e) {
                System.err.println(e);
            }

            /* ===================================================
                TEST LIKES
               ================================================= */

            System.out.println("\n===> Aggiungo like a post nella bacheca \"main\" di ale");
            System.out.println("Il post a cui metto like è\n" + element_tolike.display());
            board_ale.insertLike("giorgio", element_tolike);
            element_tolike.addLike("giorgio");
            board_ale.insertLike("sara", element_tolike);
            board_ale.insertLike("giorgio", element_tolike2);

            try {
                System.out.println("\n===> Aggiungo un like duplicato al post. Mi aspetto DuplicateDataException");
                board_ale.insertLike("giorgio", element_tolike);
            } catch (Exception e) {
                System.err.println(e);
            }

            try {
                System.out.println("\n===> Aggiungo un like da un utente non presente negli amici della categoria. Mi aspetto DataNotFoundException");
                board_ale.insertLike("chiara", element_tolike);
            } catch (Exception e) {
                System.err.println(e);
            }

            /* ===================================================
                TEST ITERATORS
               ================================================= */

            System.out.println("\n===> Test dell'iteratore della bacheca di ale");
            Iterator<DataElement> itr = board_ale.getIterator(PW_ALE);
            while(itr.hasNext()) {
                DataElement el = itr.next();
                System.out.println(el.display());
            }

            System.out.println("\n===> Test dell'iteratore friends della bacheca di ale");
            itr = board_ale.getFriendIterator("giorgio");
            while(itr.hasNext()) {
                DataElement el = itr.next();
                System.out.println(el.display());
            }

            System.out.println("\n===> Test dell'iteratore friends di un amico non presente negli amici della bacheca di ale\nUn risultato vuoto è corretto");
            itr = board_ale.getFriendIterator("chiara");
            while(itr.hasNext()) {
                DataElement el = itr.next();
                System.out.println(el.display());
            }

        } catch (Exception e) {
            System.err.println("FATAL ERROR - THIS SHOULD NOT HAVE HAPPENED:\n" + e);
        }
    }
}