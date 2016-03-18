package LieuFormation;


import LieuFormation.LieuFormation;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathieu
 */
public class LectureLieuxFormation {
    List<LieuFormation> llf;
    
    public LectureLieuxFormation(String file){
        this.llf = lireFichier(file);
    }
    
    public LectureLieuxFormation(){
        this.llf = lireFichier("../LieuxPossibles.txt");
    }
    
    private List<LieuFormation> lireFichier(String f){
        List<LieuFormation> listLieux = new ArrayList<LieuFormation>();
        try{
            InputStream is = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String ligne = "";
            String id = "";
            String nom = "";
            int cp[]={};
            double longi;
            double lati;
            br.readLine();//la premiere ligne ne nous interesse pas ici
            while ((ligne=br.readLine())!= null){
                String[] parts = ligne.split(";");
                id = parts[0].substring(1, parts[0].length()-1);
                nom = parts[1].substring(1, parts[1].length()-1);
                cp[0] = Integer.parseInt(parts[2].substring(1, parts[2].length()-1));// à gérer
                longi = Integer.parseInt(parts[3].substring(1, parts[3].length()-1));
                lati = Integer.parseInt(parts[4].substring(1, parts[4].length()-1));
                LieuFormation lf = new LieuFormation(id, nom, cp, longi, lati);
                listLieux.add(lf);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LectureLieuxFormation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LectureLieuxFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLieux;
    }
    
    public String toString(){
        for(LieuFormation l: llf){
            System.out.println(l.getId() + " + " + l.getNom() + " + " + l.getCodePostal() + " + " + l.getLongitude() + " + " + l.getLatitude());
        }
        return "";
    }
}
