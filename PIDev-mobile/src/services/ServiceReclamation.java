/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Reclamation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Djap_ii
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> reclamations;
    public static ServiceReclamation instance = null;
    public boolean resultOK;

    private final ConnectionRequest req;

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addRec(Reclamation r) {
        //création de l'URL
        String url = Statics.BASE_URL + "/api_reclamation_new?title=" + r.getTitle()
                + "&description_reclamation=" + r.getDescRec();
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

    public boolean deleteRec(Reclamation r) {
        String url = Statics.BASE_URL + "/api/reclamation/delete/" + r.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public ArrayList<Reclamation> parseRecs(String jsonText) throws ParseException{
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> recsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)recsListJson.get("root");
           
            for (Map<String,Object> obj : list){
                
                Reclamation r = new Reclamation();
                
                float id = Float.parseFloat(obj.get("id").toString());
                String title = obj.get("title").toString();
                String date_Reclamation = obj.get("date_Reclamation").toString();
                String description_Reclamation = obj.get("description_Reclamation").toString();
                String type = obj.get("type").toString();
                String email = obj.get("email").toString();
                String status = obj.get("status").toString();
                float id_user = Float.parseFloat(obj.get("id_user").toString());
                
                r.setId((int)id);
                r.setTitle(title);
                r.setDateRec(date_Reclamation);
                r.setDescRec(description_Reclamation);
                r.setType(type);
                r.setEmail(email);
                r.setStatus(status);
                r.setId_user((int)id_user);
                
                reclamations.add(r);
            }   
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }
     
     public ArrayList<Reclamation> getAllRecs(){
        String url = Statics.BASE_URL+"/api/reclamation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    reclamations = parseRecs(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
     public boolean updateRec(Reclamation r) {
        String url = Statics.BASE_URL + "/api/reclamation/update/" + r.getId()
                +"?title=" + r.getTitle()
                + "&description_Reclamation=" + r.getDescRec();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
