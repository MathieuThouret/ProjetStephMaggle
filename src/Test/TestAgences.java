/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import Agence.*;

/**
 *
 * @author Thibaud
 */

//Test de la fonction de lecture du fichier Agence
public class TestAgences {
    public static void main(String[] args) {
        ListeAgences Lecture = new ListeAgences();
        System.out.println(Lecture.toString());
    }
}
