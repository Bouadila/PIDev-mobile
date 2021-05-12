/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.pidevapp.entities.Offre;
import com.mycompany.pidevapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author brahm
 */
public class ServiceOffre {
    public ArrayList<Offre> offres;
    public static ServiceOffre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ServiceOffre() {
         req = new ConnectionRequest();
    }

    public static ServiceOffre getInstance() {
        if (instance == null) {
            instance = new ServiceOffre();
        }
        return instance;
    }
     public boolean addOffre(Offre o) {
         //création de l'URL
         String url = Statics.BASE_URL +"/api/offre/new?description="+o.getDescription()
                 +"&salaire="+o.getSalaire()
                 + "&dateExpiration="+o.getDateExpiration()
                 + "&nombrePlace="+o.getNombrePlace()
                 + "&contrat="+o.getContrat().getId()
                 + "&post="+o.getPost()
                 + "&objectif="+o.getObjectif()
                 + "&competences="+o.getCompetences()
                 + "&domaine="+o.getDomaine()
                 + "&experienceMin="+o.getExperienceMin()
                 + "&experienceMax="+o.getExperienceMax()
                 + "&entpreprise=1"; 
         req.setUrl(url);// Insertion de l'URL de notre demande de connexion
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
}
}
