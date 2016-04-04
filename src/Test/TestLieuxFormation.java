/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import LieuFormation.*;

/**
 *
 * @author Thibaud
 */
//Test de la fonction de lecture du fichier LieuxFormation
public class TestLieuxFormation {
    public static void main(String[] args) {
        ListeLieuxFormation Lecture = new ListeLieuxFormation();
        System.out.println(Lecture.toString());
    }
}
