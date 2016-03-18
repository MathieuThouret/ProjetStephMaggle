package LieuFormation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathieu
 */
public class LieuFormation {

    private String id;
    private String nom;
    private int[] codePostal;
    private double longitude;
    private double latitude;
    
    public LieuFormation(String id, String nom, int[] codePostal, double longitude, double latitude) {
        this.id = id;
        this.nom = nom;
        this.codePostal = codePostal;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int[] getCodePostal() {
        return codePostal;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodePostal(int[] codePostal) {
        this.codePostal = codePostal;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    
    
    
    
    
}
