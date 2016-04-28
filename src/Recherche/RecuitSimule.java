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
import java.util.Scanner;

/**
 *
 * @author mathieu
 */
public class RecuitSimule {

    int[] effectifLieu = new int[1947];

    /*
     On prend pour solution initiale l'ensemble des lieux de formation les plus proches
     de chaque agence    
     */
    public static void main(String[] args) {
        ListeLieuxFormation listForm = new ListeLieuxFormation();
        ListeAgences listAgence;
        
        //Commenter pour faire des tests sur le temps de calcul
        System.out.println("Nombre d'agences : [100][300][500][Custom]");
        Scanner in = new Scanner(System.in);
        String s = in.next();
        switch (s) {
            case "":
                listAgence = new ListeAgences();
                break;
            case "100":
                listAgence = new ListeAgences();
                break;
            case "300":
                listAgence = new ListeAgences("ressources/ListeAgences_300.txt");
                break;
            case "500":
                listAgence = new ListeAgences("ressources/ListeAgences_500.txt");
                break;
            default:
                listAgence = new ListeAgences("ressources/"+s);
                break;
        }
        System.out.println("Donnez les paramètres du recuit souhaités : ");
        System.out.println("Nombre d'itération max kmax : ");
        int kmax = Integer.parseInt(in.next());
        System.out.println("Delta max df : ");
        double df = Double.parseDouble(in.next());
        System.out.println("Proportion de valeurs non optimales p");
        double p = Double.parseDouble(in.next());
        double ti = -df / Math.log(p);
        System.out.println("Température initiale ti : " + ti);
        System.out.println("Facteur de régression de la température u : ");
        double u = Double.parseDouble(in.next());
        System.out.println("Affichage de la solution : [0] Résultat simple [1] Par agence [2] Par lieu de formation");
        s = in.next();
        //
        /*
         //Décomenter pour faire des tests sur le temps de calcul
         int kmax=20000;
         double df=4000;
         double p=0.8;
         double ti=-df/Math.log(p);
         double u=0.6;
         listAgence = new ListeAgences();
         //listAgence = new ListeAgences("ressources/ListeAgences_300.txt");
         //listAgence = new ListeAgences("ressources/ListeAgences_500.txt");
         */
        Solution Si = trouverSolutionInitiale(listAgence, listForm);
        System.out.println("Nous trouvons un résultat initial de " + CalculResultat.resultat(Si) + " €");
        Solution Smin = trouverSolutionFinale(Si, listForm, kmax, ti, u);
        
        //Commenter pour faire des tests sur le temps de calcul
        switch (s) {
            case "1":
                System.out.println(Smin.toString());
                break;
            case "2":
                System.out.println(Smin.affichageParLieu());
                break;
            default:
                break;
        }
        //
        System.out.println("Nous trouvons un résultat final de " + CalculResultat.resultat(Smin) + " €");
    }

    public static Solution trouverSolutionInitiale(ListeAgences listAgence, ListeLieuxFormation listForm) {
        //On cherche un état itinial à l'algorithme,
        //pour cela on choisit le lieu de formation libre le plus proche de chaque agence
        Solution S = new Solution();
        int resultat;
        for (Agence a : listAgence.getList()) {
            LieuFormation closest = getClosest(a, listForm);
            //LieuFormation closest=getCloseEnough(a, listForm);
            listForm.addPeople(closest, a.getNbPersonnes());
            S.putLieuFormation(a, closest);
        }
        resultat = CalculResultat.resultat(S);

        return S;
    }

    public static Solution trouverSolutionFinale(Solution Si, ListeLieuxFormation listForm, int kmax, double ti, double u) {
        double t = ti; // Parametre température initiale
        int k = 0;
        Solution Sn; // Solution courante
        Solution Sp = Si.clone(); // Solution précedente
        Solution Smin = Si.clone(); // Solution optimale recherchée par l'algo
        int En; // Valeur de la solution courante 
        int delta;
        while (k < kmax) {
            Sn = voisin(Sp, listForm);
            En = CalculResultat.resultat(Sn);
            delta = En - CalculResultat.resultat(Sp);
            if (delta <= 0) {
                Sp = Sn.clone();
                if (En < CalculResultat.resultat(Smin)) {
                    Smin = Sn.clone();
                }
            } else {
                //System.out.println(Math.exp(-delta/t));
                if (Math.random() < Math.exp(-delta / t)) {
                    Sp = Sn.clone();
                }
            }
            t = u * t;
            k++;
        }
        return Smin;
    }
    /*
     Renvoi le lieu de formation libre (ayant suffisament de place)
     le plus proche de l'agence donnée
     */

    public static LieuFormation getClosest(Agence a, ListeLieuxFormation listForm) {
        double longi = a.getLongitude();
        double lati = a.getLatitude();
        double min = 1500.0; //plus grand que la france
        EffectifLieuFormation closest = listForm.getList().get(0);
        for (EffectifLieuFormation elf : listForm.getList()) {
            boolean rempli = (a.getNbPersonnes() + elf.getEffectif()) > 60;
            double dist = FonctionUtiles.DistanceTo(lati, longi, elf.getLatitude(), elf.getLongitude());
            if (dist < min && !rempli) {
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

    public static LieuFormation getCloseEnough(Agence a, ListeLieuxFormation listForm) {
        double maxAccept = 300;
        EffectifLieuFormation close = null;
        EffectifLieuFormation randLieu;
        int k = 0;
        while (close == null && k < 1000) {
            randLieu = FonctionUtiles.randomFromList(listForm.getList());
            if (FonctionUtiles.DistanceTo(a.getLatitude(), a.getLongitude(), randLieu.getLatitude(), randLieu.getLongitude()) < maxAccept) {
                close = randLieu;
            }
            k++;
        }
        if (k < 1000) {
            return close;
        } else {
            return getClosest(a, listForm);
        }
    }

    public static Solution voisin(Solution solution, ListeLieuxFormation listForm) {
        List<Agence> agences = solution.getListAgence();
        Agence a = FonctionUtiles.randomFromList(agences);
        Solution s0 = solution.clone();
        LieuFormation closeEnough = getCloseEnough(a, listForm);
        listForm.withdrowPeople(s0.getCouple().get(a), a.getNbPersonnes());
        listForm.addPeople(closeEnough, a.getNbPersonnes());
        s0.putLieuFormation(a, closeEnough);
        return s0;
    }

}
