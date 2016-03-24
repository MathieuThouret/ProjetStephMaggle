package Agence;

import LieuFormation.LieuFormation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathieu
 */
public class Agence extends LieuFormation {
    
    private int nbPersonnes;
    

    public Agence(String id, String nom, int codePostal, double longitude, double latitude, int nbPersonnes) {

        super(id, nom, codePostal, longitude, latitude);
        this.nbPersonnes = nbPersonnes;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }
    
}
