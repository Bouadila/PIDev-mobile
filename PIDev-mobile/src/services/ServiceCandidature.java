/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Candidature;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;


/**
 *
 * @author A.L.I.C.E
 */
public class ServiceCandidature {
    
    public ArrayList<Candidature> candidatures;
    public static ServiceCandidature instance=null;
    public static boolean resultOK = true;
   
    private ConnectionRequest req;
    
    
     public ServiceCandidature() {
        req = new ConnectionRequest();

    }
     
     
     public static ServiceCandidature getInstance() {
        if (instance == null) {
            instance = new ServiceCandidature();
        }
        return instance;
    }
     
     
     
     public boolean AjouterCandidature(Candidature candidature)
     {
         String url= Statics.BASE_URL+"/candidature/add_candidature?num="+candidature.getNum()+"&status="+candidature.getStatus()+"&diplome="+candidature.getDiplome()+"&cv="+candidature.getCv()+"&offre_id="+candidature.getOffre_id()+"&candidat_id="+candidature.getCandidat_id();
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
     
     
     
     public ArrayList<Candidature>AfficherCandidature()
     {
         ArrayList<Candidature> result =  new ArrayList<>();
         String url =Statics.BASE_URL+"/candidature/view_all_candidature"; 
         req.setUrl(url);
         
         req.addResponseListener(new ActionListener<NetworkEvent>()
         {
             @Override
             public void actionPerformed(NetworkEvent evt){
                 
              JSONParser jsonp;
              jsonp = new JSONParser();
              
              try {
                  Map<String,Object>mapCandidature = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapCandidature.get("root");
                  
                  for(Map<String,Object> obj : listOfMaps){
                      Candidature cnd = new Candidature();
                      
                      float id = Float.parseFloat(obj.get("id").toString());
                      float num = Float.parseFloat(obj.get("num").toString());
                      String status= obj.get("status").toString();
                      String diplome= obj.get("diplome").toString();
                      String cv= obj.get("cv").toString();
                      //float offre_id = Float.parseFloat(obj.get("offre_id").toString());
                      //float candidat_id = Float.parseFloat(obj.get("candidat_id").toString());

                      
                       cnd.setId((int)id);
                       cnd.setNum((int)num);
                       cnd.setStatus(status);
                       cnd.setDiplome(diplome);
                       cnd.setCv(cv);
                       //cnd.setOffre_id((int)offre_id);
                       //cnd.setCandidat_id((int)candidat_id);

                       //date
                       
                       String DateConverter = obj.get("date_candidature").toString().substring(0,10);                       
                       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                       java.util.Date date = sdf.parse(DateConverter);
                       Date sqlDate = new Date(date.getTime()); 
                       
                       cnd.setDate_candidature(sqlDate);
                       
                       //insert data into arrayList result
                       
                       result.add(cnd);
                  }
              
              
              } catch(ParseException | IOException | NumberFormatException ex){
                  ex.printStackTrace();
              }
              
             }
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return result;
     }
     
     
     
     public Candidature DetailCandidature(int id , Candidature candidature){
        
         String url = Statics.BASE_URL+"/candidature/list_candidature_detail?id="+id;
         req.setUrl(url);
         
         String str = new String(req.getResponseData());
         
         req.addResponseListener((new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 JSONParser jsonp = new JSONParser();                  
                     Map<String,Object>obj = null;
                 try {
                     obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                 } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                 }
                     
                     candidature.setNum(Integer.parseInt(obj.get("num").toString()));
                     candidature.setStatus(obj.get("status").toString());
                     candidature.setDiplome(obj.get("diplome").toString());
                     candidature.setCv(obj.get("cv").toString());
                     
//                     String DateConverter = obj.get("date_candidature").toString().substring(0,10);
//                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//                     java.util.Date date = null;
//                     date = sdf.parse(DateConverter);
//                     Date sqlDate = new Date(date.getTime());
//                     candidature.setDate_candidature(sqlDate);

             }
         }));
         
          NetworkManager.getInstance().addToQueueAndWait(req);
         
         return candidature;
         
     }
     
     
     
     public boolean deleteCandidature (Candidature candidature)
     {
         String url = Statics.BASE_URL+"/candidature/delete_candidature?id="+candidature.getId();
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
     
     
     
    public boolean modifierCandidature (Candidature candidature)  {
       
       String url = Statics.BASE_URL+"/Candidature/update_candidature?id="+candidature.getId()+"num="+candidature.getNum()+"&status="+candidature.getStatus()+"&diplome="+candidature.getDiplome()+"&cv="+candidature.getCv();
       req.setUrl(url);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
             
             @Override
             public void actionPerformed(NetworkEvent evt){
                 
                resultOK = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
             }
             
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);
       return resultOK;
      
    }
     
     
     
      public ArrayList<Candidature> parseCandidatures(String jsonText) throws ParseException {
        try {
            candidatures = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> candidaturesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) candidaturesListJson.get("root");

            for(Map<String,Object> obj : list){
                Candidature v = new Candidature();
          
                
                v.setId((int)Float.parseFloat(obj.get("id").toString()));
                v.setNum((int)Float.parseFloat(obj.get("num").toString()));
                v.setStatus(obj.get("status").toString());           
                v.setDiplome(obj.get("diplome").toString());
                v.setCv(obj.get("cv").toString());
                 String DateConverter = obj.get("date_candidature").toString().substring(0,10);                       
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                 java.util.Date date = sdf.parse(DateConverter);
                 Date sqlDate = new Date(date.getTime()); 
                v.setDate_candidature(sqlDate);

                candidatures.add(v);
            }
        } catch (IOException ex) {
            
        }
        return candidatures;

    }
    
    
}