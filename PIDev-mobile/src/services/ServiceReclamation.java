/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Reclamation;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
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
        String url = Statics.BASE_URL + "api/reclamation/new?title=" + r.getTitle()
                + "description_Reclamation" + r.getDescRec()
                + "type" + r.getType()
                + "";
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

    public ArrayList<Reclamation> getAllRec() {
        String url = Statics.BASE_URL + "api/reclamation";
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
    
    public ArrayList<Reclamation> parseRecs(String jsonText) throws ParseException{
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
                float nombrePlace = Float.parseFloat(obj.get("nombrePlace").toString());
                //contrait
                String post = obj.get("post").toString();
                String objectif=obj.get("objectif").toString();
                String competences=obj.get("competences").toString();
                String domaine=obj.get("domaine").toString();
                o.setId((int)id);
                o.setDescription(description);
                o.setSalaire((int)salaire);
                o.setNombrePlace((int)nombrePlace);
                o.setPost(post);
                o.setObjectif(objectif);
                o.setCompetences(competences);
                o.setDomaine(domaine);
                //Ajouter la tâche extraite de la réponse Json à la liste
                offres.add(o);
            }
}
