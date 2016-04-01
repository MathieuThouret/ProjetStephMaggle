/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculResultat;

import Agence.Agence;
import FonctionUtiles.FonctionUtiles;
import LieuFormation.LieuFormation;
import Solution.X;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Thibaud
 */
public class CalculResultat {

    private static int nbLieux(X solution) {
        List<LieuFormation> list = new ArrayList();
        for (Map.Entry<Agence, LieuFormation> entry : solution.getCouple().entrySet()) {
            if (!list.contains(entry.getValue())) {
                list.add(entry.getValue());
            }
        }
        return list.size();
    }
    //Cout=3000* nbLieu + Somme( distance (A(i),L(i))*0,4)
    public static int resultat(X solution) {
        int resultat=0;
        resultat+=3000*nbLieux(solution);
        for (Map.Entry<Agence, LieuFormation> entry : solution.getCouple().entrySet()) {
                resultat+=FonctionUtiles.DistanceTo(entry.getValue().getLatitude(),entry.getValue().getLongitude(), entry.getKey().getLatitude(), entry.getKey().getLongitude())*0.4*entry.getKey().getNbPersonnes();
        }
        return resultat;
    }
}
