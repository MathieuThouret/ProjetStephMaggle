package Agence;

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
public class ListeAgences {
    private List<Agence> la;

    public ListeAgences(String file){
        this.la = lireFichier(file);
    }
    
    public ListeAgences(){
        this.la = lireFichier("ressources/ListeAgences_100.txt");
    }
    
    public List<Agence> getList(){
        return la;
    }
    
    private List<Agence> lireFichier(String f){
        List<Agence> listAgences;
        listAgences = new ArrayList<>();
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
            int nb_pers;
            br.readLine();//la premiere ligne ne nous interesse pas ici
            while ((ligne=br.readLine())!= null){
                String[] parts = ligne.split(";");
                id = parts[0].substring(1, parts[0].length()-1);
                nom = parts[1].substring(1, parts[1].length()-1);
                cp = Integer.parseInt(parts[2].substring(1, parts[2].length()-1).split("-")[0]);
                longi = Double.parseDouble(parts[3]);
                lati = Double.parseDouble(parts[4]);
                nb_pers = Integer.parseInt(parts[5]);
                Agence la = new Agence(id, nom, cp, longi, lati, nb_pers);
                listAgences.add(la);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListeAgences.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListeAgences.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAgences;
    }
    
    @Override
    public String toString(){
        String str="";
        for(Agence l: la){
            str+=l.getId() + " + " + l.getNom() + " + " + l.getCodePostal() + " + " + l.getLongitude() + " + " + l.getLatitude()+" + " + l.getNbPersonnes() +"\n";
        }
        return str;
    }
}
