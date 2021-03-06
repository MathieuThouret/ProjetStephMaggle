/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FonctionUtiles;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Thibaud
 */
public class FonctionUtiles {
    /* fonction permettant de trouver la distance entre 2 points en utilisant leurs coordonnées*/
    public static double DistanceTo(double lat1, double lon1, double lat2, double lon2) {
        double rlat1 = Math.PI * lat1 / 180;
        double rlat2 = Math.PI * lat2 / 180;

        double theta = lon1 - lon2;
        double rtheta = Math.PI * theta / 180;

        double dist = Math.sin(rlat1) * Math.sin(rlat2) + Math.cos(rlat1) * Math.cos(rlat2) * Math.cos(rtheta);
        dist = Math.acos(dist);
        dist = dist * 180 / Math.PI;
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344;

        return dist;
    }
    
    public static <T> T randomFromList(List<T> list) {
        Random randomizer = new Random();
        return list.get(randomizer.nextInt(list.size()));
    }
}
