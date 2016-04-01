/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recherche;

import Agence.Agence;
import Agence.ListeAgences;
import FonctionUtiles.FonctionUtiles;
import LieuFormation.EffectifLieuFormation;
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
            couple.putLieuFormation(a,getClosest(a, listForm.getList()));
        }
    }
    
    
    public static LieuFormation getClosest(Agence a, List<EffectifLieuFormation> listForm){
        double longi = a.getLongitude();
        double lati = a.getLatitude();
        double min = 1500.0; //plus grand que la france
        EffectifLieuFormation closest = listForm.get(0);
        for(EffectifLieuFormation elf: listForm){
            boolean rempli = (a.getNbPersonnes()+elf.getEffectif())>60;
            double dist = FonctionUtiles.DistanceTo(lati, longi, elf.getLatitude(), elf.getLongitude());
            if (dist<min && !rempli){
                min = Math.min(min, dist);
                closest = elf;
            }
        }
        return closest;
    }
}
