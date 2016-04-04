/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculResultat;

import Agence.Agence;
import FonctionUtiles.FonctionUtiles;
import LieuFormation.LieuFormation;
import Solution.Solution;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Thibaud
 */

/*ensemble de fonctions permettant de calculer le prix d'une solution*/
public class CalculResultat {

    /* renvoi le nombre de Lieux de formation utilisés dans une solution */
    private static int nbLieux(Solution solution) {
        List<LieuFormation> list = new ArrayList();
        for (Map.Entry<Agence, LieuFormation> entry : solution.getCouple().entrySet()) {
            if (!list.contains(entry.getValue())) {
                list.add(entry.getValue());
            }
        }
        return list.size();
    }
    /* renvoi le Prix d'une solution en utilisant la fonction
    Cout=3000* nbLieu + Somme(distance (A(i),L(j))*0,4*NbPers(A(i)))*/
    public static int resultat(Solution solution) {
        int resultat=0;
        resultat+=3000*nbLieux(solution);
        for (Map.Entry<Agence, LieuFormation> entry : solution.getCouple().entrySet()) {
                resultat+=FonctionUtiles.DistanceTo(entry.getValue().getLatitude(),entry.getValue().getLongitude(), entry.getKey().getLatitude(), entry.getKey().getLongitude())*0.4*entry.getKey().getNbPersonnes();
        }
        return resultat;
    }
}
