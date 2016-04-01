/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LieuFormation;

/**
 *
 * @author mathieu
 */
public class EffectifLieuFormation extends LieuFormation{
    int effectif;

    public EffectifLieuFormation(String id, String nom, int codePostal, double longitude, double latitude) {
        super( id,  nom,  codePostal,  longitude,  latitude);
        this.effectif = 0;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }    
}
