/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.services;

import static GUI.User.Login.idaffiche;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.pidevapp.entities.Contrat;
import com.mycompany.pidevapp.entities.Offre;
import com.mycompany.pidevapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;





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
                 + "&entpreprise="+idaffiche; 
         req.setUrl(url);// Insertion de l'URL de notre demande de connexion
         //req.setPost(true);
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
     public ArrayList<Offre> parseOffres(String jsonText) throws ParseException, JSONException{
        try {
            offres=new ArrayList<>();
            JSONParser j = new JSONParser();
            JSONArray jsonArray = new JSONArray(jsonText);
            for(int i =0 ; i <jsonArray.length();i++ ){
                JSONObject jsonData = new JSONObject(jsonArray.getJSONObject(i).toString());
                Offre o = new Offre();
                float id = Float.parseFloat(jsonData.get("id").toString());
                String description = jsonData.get("description").toString();
                float salaire = Float.parseFloat(jsonData.get("salaire").toString());
//                String dateDepoString =  obj.get("dateDepo").toString();
//                SimpleDateFormat format = new SimpleDateFormat(DateFormatPatterns.ISO8601);
//                Date dateDepo = format.parse(dateDepoString );
//                String dateExpirationString =  obj.get("dateExpiration").toString();
//                Date dateExpiration = format.parse(dateExpirationString );
            float nombrePlace = Float.parseFloat(jsonData.get("nombrePlace").toString());
                //contrait
                String post = jsonData.get("post").toString();
                String objectif=jsonData.get("objectif").toString();
                String competences=jsonData.get("competences").toString();
                String domaine=jsonData.get("domaine").toString();
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
                JSONObject json = new JSONObject(jsonData.get("contrat").toString());
                float contratId = Float.parseFloat(json.getString("id"));
                String contrarDescription = json.getString("description");
                String contrarType = json.getString("type");
                Contrat c = new Contrat();
                c.setId((int)contratId);
                c.setDescription(contrarDescription);
                c.setType(contrarType);
                o.setContrat(c);
                offres.add(o);
            }
            
        } catch (Exception ex) {
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
                } catch (JSONException ex) {
                    System.out.println(ex.getMessage());;
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
    }
     public Offre getOffre(int id){
         Offre o = new Offre();
         String url =Statics.BASE_URL+"api/offre/"+id;
         req.setUrl(url);
         req.setPost(false);
         //String str = new String(req.getResponseData());
         //System.out.println(str);
         req.addResponseListener((new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 JSONParser jsonp = new JSONParser();
                 try {
                     JSONObject jsonData = new JSONObject(new String(req.getResponseData()));
                     float id = Float.parseFloat(jsonData.get("id").toString());
                     String description = jsonData.get("description").toString();
                     float salaire = Float.parseFloat(jsonData.get("salaire").toString());
                     float nombrePlace = Float.parseFloat(jsonData.get("nombrePlace").toString());
                     float ExperienceMin = Float.parseFloat(jsonData.get("experienceMin").toString());
                     float ExperienceMax = Float.parseFloat(jsonData.get("experienceMax").toString());
                     String post = jsonData.get("post").toString();
                     String objectif=jsonData.get("objectif").toString();
                     String competences=jsonData.get("competences").toString();
                     String domaine=jsonData.get("domaine").toString();
                     o.setId((int)id);
                     o.setDescription(description);
                     o.setSalaire((int)salaire);
                     o.setNombrePlace((int)nombrePlace);
                     o.setPost(post);
                     o.setObjectif(objectif);
                     o.setCompetences(competences);
                     o.setDomaine(domaine);
                     o.setExperienceMin((int) ExperienceMin);
                     o.setExperienceMax((int) ExperienceMax);
                     JSONObject json = new JSONObject(jsonData.get("contrat").toString());
                     float contratId = Float.parseFloat(json.getString("id"));
                     String contrarDescription = json.getString("description");
                     String contrarType = json.getString("type");
                    Contrat c = new Contrat();
                    c.setId((int)contratId);
                    c.setDescription(contrarDescription);
                    c.setType(contrarType);
                    o.setContrat(c); 
          
                 } catch (JSONException ex) {
                     System.out.println(ex.getMessage());
                 }
             }
         }));
         
          NetworkManager.getInstance().addToQueueAndWait(req);
         
         return o;
         
     }
      public boolean updateOffre(Offre o) {
         //création de l'URL
         SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = format1.format(o.getDateExpiration());
         String url = Statics.BASE_URL +"api/offre/update/"+o.getId()+"?description="+o.getDescription()
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
      public boolean deleteOffre (Offre o)
     {
         String url = Statics.BASE_URL+"api/offre/delete/"+o.getId();
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
