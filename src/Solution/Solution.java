/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

import Agence.Agence;
import LieuFormation.LieuFormation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mathieu
 */

/*Type de la solution calculées par l'algorithme, contient uniquement
l'ensemble des couples Agence/LieuFormation
Les autres informations utiles (nombre de lieux de formation, prix de la solution)
sont calculés séparément
*/
public class Solution {
    
    private Map<Agence, LieuFormation> couple;
    
    public Solution() {
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
    
    public List<Agence> getListAgence() {
        List<Agence> list = new ArrayList();
        for (Map.Entry<Agence, LieuFormation> entry : couple.entrySet()) {
                list.add(entry.getKey());
        }
        return list;
    }
    
    public Solution clone() {
        Solution s0= new Solution();
        s0.couple=new HashMap<>(this.couple);
        return s0;
    }
}