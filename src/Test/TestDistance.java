/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import FonctionUtiles.FonctionUtiles;

/**
 *
 * @author Thibaud
 */
public class TestDistance {
    public static void main(String[] args) {
         double DistParisTlse = 588.15;
         double LatTlse=43.604362;
         double LongTlse=1.433333;
         double LatParis= 48.866667;
         double LongParis= 2.333333;
         
         System.out.println("Distance internet : "+DistParisTlse);
         System.out.println("Distance calculée : "+FonctionUtiles.DistanceTo(LatTlse,LongTlse,LatParis,LongParis));
    }
}
