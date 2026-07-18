/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inscription;

import java.util.Date;

/**
 *
 * @author hp
 */
public class Model {
    private int id;
    private String nom;
    private String prénoms;
    private String mention;
    private String parcour;
    private String niveau;
    private Date dateNaissance;
    private int télephone;
    private int argent;
    //constructeur
    public Model(int id,String nom,String prénoms,String mention,String parcour,String niveau,Date dateNaissance,int télephone,int argent){
        this.id = id;
        this.nom = nom;
        this.prénoms = prénoms;
        this.mention = mention;
        this.parcour = parcour;
        this.niveau = niveau;
        this.dateNaissance = dateNaissance;
        this.télephone = télephone;
        this.argent = argent;
    }
    
    // Getters et Setters
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNom(){
        return nom;
        
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public String getPrénoms(){
        return prénoms;
    }
    public void setPrénoms(String prénoms){
        this.prénoms = prénoms;
    }
    public String getMention(){
        return mention;
    }
    public void setMention(String mention){
        this.mention = mention;
    }
    public String getParcour(){
        return parcour;
    }
    public void setParcour(String parcour){
        this.parcour = parcour;
    }
    public String getNiveau(){
        return niveau;
    }
    public void setNiveau(String niveau){
        this.niveau = niveau;
    }
    public Date getDateNaissance(){
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance){
        this.dateNaissance = dateNaissance;
    }
    public int getTélephone(){
        return télephone;
    }
    public void setTélephone(int télephone){
        this.télephone = télephone;
    }
    public int getArgent(){
        return argent;
    }
    public void setArgent(int argent){
        this.argent = argent;
    }
    
    
}
