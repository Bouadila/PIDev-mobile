/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.pidevapp.entities.Offre;
import com.mycompany.pidevapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author brahm
 */
public class ServiceOffre {
    public ArrayList<Offre> offres;
    public static ServiceOffre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceOffre() {
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
         SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = format1.format(o.getDateExpiration());
         String url = Statics.BASE_URL +"api/offre/new?description="+o.getDescription()
                 +"&salaire="+o.getSalaire()
                 + "&dateExpiration="+date1
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
     public ArrayList<Offre> parseOffres(String jsonText) throws ParseException{
        try {
            offres=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> offresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)offresListJson.get("root");
            for(Map<String,Object> obj : list){
                Offre o = new Offre();
                float id = Float.parseFloat(obj.get("id").toString());
                String description = obj.get("description").toString();
                float salaire = Float.parseFloat(obj.get("salaire").toString());
//                String dateDepoString =  obj.get("dateDepo").toString();
//                SimpleDateFormat format = new SimpleDateFormat(DateFormatPatterns.ISO8601);
//                Date dateDepo = format.parse(dateDepoString );
//                String dateExpirationString =  obj.get("dateExpiration").toString();
//                Date dateExpiration = format.parse(dateExpirationString );
                float nombrePlace = Float.parseFloat(obj.get("nombrePlace").toString());
                //contrait
                String post = obj.get("post").toString();
                String objectif=obj.get("objectif").toString();
                String competences=obj.get("competences").toString();
                String domaine=obj.get("domaine").toString();
                o.setId((int)id);
                o.setDescription(description);
                o.setSalaire((int)salaire);
//                o.setDateDepot(dateDepo);
//                o.setDateExpiration(dateExpiration);
                o.setNombrePlace((int)nombrePlace);
                o.setPost(post);
                o.setObjectif(objectif);
                o.setCompetences(competences);
                o.setDomaine(domaine);
//                t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
//                t.setName(obj.get("name").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                offres.add(o);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }
     public ArrayList<Offre> getAllOffres(){
        String url = Statics.BASE_URL+"api/offre";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    offres = parseOffres(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
    }
      public boolean deleteOffre (Offre o)
     {
         String url = Statics.BASE_URL+"offre/delete/"+o.getId();
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             
             @Override
             public void actionPerformed(NetworkEvent evt){
                 
                 req.removeResponseCodeListener(this);
             }
             
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
     }
     
}
