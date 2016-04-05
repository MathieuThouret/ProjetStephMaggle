/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recherche;

import Agence.Agence;
import Agence.ListeAgences;
import CalculResultat.CalculResultat;
import FonctionUtiles.FonctionUtiles;
import LieuFormation.EffectifLieuFormation;
import LieuFormation.LieuFormation;
import LieuFormation.ListeLieuxFormation;
import Solution.Solution;
import java.util.List;

/**
 *
 * @author mathieu
 */
public class Initialisation {
    
    int[] effectifLieu = new int[1947];

    
    /*
    On prend pour solution initiale l'ensemble des lieux de formation les plus proches
    de chaque agence    
    */
    public static void main(String[] args){
        Solution couple = new Solution();
        ListeAgences listAgence = new ListeAgences();
        ListeLieuxFormation listForm = new ListeLieuxFormation();
        int resultat;
        for (Agence a: listAgence.getList()){
            LieuFormation closest=getClosest(a, listForm.getList());
            listForm.addPeople(closest, a.getNbPersonnes());
            couple.putLieuFormation(a,closest);
        }
        resultat=CalculResultat.resultat(couple);
        System.out.println("Nous trouvons un résultat initial de "+resultat+" €");
        
        int k=0;
        int kmax=1000;
        while (k<kmax) {
            Solution Sn=voisin(couple, listForm.getList());
            int En=CalculResultat.resultat(Sn);
            if (TestRandom(resultat,En,k,kmax)) {
                
            }
        }
    }
    
    /*
    Renvoi le lieu de formation libre (ayant suffisament de place)
    le plus proche de l'agence donnée
    */
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
    /*
    Renvoi un lieu de formation aléatoire situé à une distance inférieure à maxAccept KM.
    Si on ne trouve pas de tel lieu après 1000 itérations, on renvoi le lieu le plus proche.
    */
    public static LieuFormation getCloseEnough(Agence a, List<EffectifLieuFormation> listForm)  {
        double maxAccept = 300;
        EffectifLieuFormation close = null;
        EffectifLieuFormation randLieu;
        int k=0;
        while (close==null && k < 1000) {
        randLieu=FonctionUtiles.randomFromList(listForm);
            if(FonctionUtiles.DistanceTo(a.getLatitude(), a.getLongitude(), randLieu.getLatitude(), randLieu.getLongitude()) < maxAccept){
                close=randLieu;
            }
        }
        if (k<1000) {
            return close;
        }
        else {
            return getClosest( a, listForm);
        }
    }
    
    public static Solution voisin(Solution solution, List<EffectifLieuFormation> listForm) {                                                                                                                                      
        List<Agence> agences=solution.getListAgence();
        Agence a=FonctionUtiles.randomFromList(agences);
        solution.putLieuFormation(a,getCloseEnough(a, listForm));
        return solution;
    }
    
    public static boolean TestRandom(int E, int En, int k, int kmax) {
        boolean test=(En<E);
        return test;
    }
}
