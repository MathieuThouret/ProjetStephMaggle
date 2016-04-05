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

/*Type décrivant un Lieu de formation
On conserve en plus le nombre de personnes déjà attribuées au lieu de formation
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

    public void setEffectif(int _effectif) {
        this.effectif = _effectif;
    }
    
    public void subEffectif(int _effectif) {
        this.effectif -= _effectif;
    }
    
    public LieuFormation getLieuFormation(){
        return new LieuFormation(this.getId(), this.getNom(), this.getCodePostal(), this.getLongitude(), this.getLatitude());    }
}
