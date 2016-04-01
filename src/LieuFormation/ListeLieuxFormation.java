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
public class ListeLieuxFormation {
    private List<EffectifLieuFormation> llf;
    
    public ListeLieuxFormation(String file){
        this.llf = lireFichier(file);
    }
    
    public ListeLieuxFormation(){
        this.llf = lireFichier("resources/LieuxPossibles.txt");
    }
    
    public List<EffectifLieuFormation> getList(){
        return llf;
    }
    
    private List<EffectifLieuFormation> lireFichier(String f){
        List<EffectifLieuFormation> listLieux;
        listLieux = new ArrayList<>();
        try{
            InputStream is = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String ligne;
            String id;
            String nom;
            int cp;
            double longi;
            double lati;
            br.readLine();//la premiere ligne ne nous interesse pas ici
            while ((ligne=br.readLine())!= null){
                String[] parts = ligne.split(";");
                id = parts[0].substring(1, parts[0].length()-1);
                nom = parts[1].substring(1, parts[1].length()-1);
                cp = Integer.parseInt(parts[2].substring(1, parts[2].length()-1).split("-")[0]);
                longi = Double.parseDouble(parts[3]);
                lati = Double.parseDouble(parts[4]);
                EffectifLieuFormation elf = new EffectifLieuFormation(id, nom, cp, longi, lati);
                listLieux.add(elf);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeLieuxFormation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListeLieuxFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLieux;
    }
    
    @Override
    public String toString(){
        String str="";
        for(EffectifLieuFormation l: llf){
            str+=l.getId() + " + " + l.getNom() + " + " + l.getCodePostal() + " + " + l.getLongitude() + " + " + l.getLatitude()+"\n";
        }
        return str;
    }
}
