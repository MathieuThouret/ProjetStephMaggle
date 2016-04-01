/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

import Agence.Agence;
import LieuFormation.EffectifLieuFormation;
import LieuFormation.LieuFormation;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mathieu
 */
public class X {
    
    private Map<Agence, LieuFormation> couple;
    
    public X() {
        couple = new HashMap<>();
        couple.clear();
    }
    
    public void putLieuFormation(Agence a, LieuFormation f){
        couple.put(a, f);
    }
    
    public LieuFormation getLieuFormation(Agence a){
        return couple.get(a);
    }
    
    public Map<Agence, LieuFormation> getCouple() {
        return couple;
    }
}