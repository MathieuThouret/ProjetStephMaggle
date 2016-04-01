/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recherche;

import Agence.Agence;
import Agence.ListeAgences;
import FonctionUtiles.FonctionUtiles;
import LieuFormation.LieuFormation;
import LieuFormation.ListeLieuxFormation;
import Solution.X;
import java.util.List;

/**
 *
 * @author mathieu
 */
public class Initialisation {
    
    int[] effectifLieu = new int[1947];

    public static void main(String[] args){
        X couple = new X();
        ListeAgences listAgence = new ListeAgences();
        ListeLieuxFormation listForm = new ListeLieuxFormation();
        for (Agence a: listAgence.getList()){
            
        }
    }
    
    
    public LieuFormation getClosest(Agence a, List<LieuFormation> listForm){
        double longi = a.getLongitude();
        double lati = a.getLatitude();
        double dist = 1500.0; //plus grand que la france
        for(LieuFormation lf: listForm){
            if (FonctionUtiles.DistanceTo(lati, longi, lf.getLatitude(), lf.getLongitude())<dist ){
                
            }
        }
    }
}
