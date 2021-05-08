/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Formation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
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
 * @author User
 */
public class ServiceFormation {
    
    
        
     public ArrayList<Formation> formations;
    public static ServiceFormation instance=null;
    public static boolean resultOK = true;
   
    private ConnectionRequest req;
    
    
     public ServiceFormation() {
        req = new ConnectionRequest();

    }
     
     
     public static ServiceFormation getInstance() {
        if (instance == null) {
            instance = new ServiceFormation();
        }
        return instance;
    }
     
     
     
     public void AjouterFormation(Formation Formation)
     {
         String url= Statics.BASE_URL+"/add_video?url="+Formation.getUrl()+"&title="+Formation.getTitle()+"&description="+Formation.getDescription()+"&domaine="+Formation.getDomaine();
         //&id_cand=1
         req.setUrl(url);
         req.addResponseListener((e)->{
             
           String str = new String(req.getResponseData()) ; 
           System.out.println("data =="+str);
           
           
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);
     }
     
     
     
     public ArrayList<Formation>AfficherFormation()
     {
         ArrayList<Formation> result =  new ArrayList<>();
         String url =Statics.BASE_URL+"/view_All_vid"; 
         req.setUrl(url);
         
         req.addResponseListener(new ActionListener<NetworkEvent>()
         {
             @Override
             public void actionPerformed(NetworkEvent evt){
                 
              JSONParser jsonp;
              jsonp = new JSONParser();
              
              try {
                  Map<String,Object>mapFormation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapFormation.get("root");
                  
                  for(Map<String,Object> obj : listOfMaps){
                      Formation forma = new Formation();
                      
                      float id = Float.parseFloat(obj.get("id").toString());
                      String url= obj.get("url").toString();
                      String title= obj.get("title").toString();
                      String domaine= obj.get("domaine").toString();
                      String description= obj.get("description").toString();

                       forma.setId((int)id);
                       forma.setUrl(url);
                       forma.setTitle(title);
                       forma.setDomaine(domaine);
                       forma.setDescription(description);
                       
                       //date
                       
                      // String DateConverter = obj.get("publishDate").toString().substring(obj.get("publishDate").toString().indexOf("timestamp") + 10 , obj.get("publishDate").toString().lastIndexOf("}"));
                      String DateConverter = obj.get("publishDate").toString().substring(0,10);


                      
                      // Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                       
                       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                       
                       String dateString = formatter.format(DateConverter);
                       
                       forma.setPublish_date(dateString);
                       
                       //insert data into arrayList result
                       
                       result.add(forma);
                  }
              
              
              } catch(Exception ex){
                  ex.printStackTrace();
              }
              
             }
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);
         
         return result;
     }
     
     
     
     public Formation DetailFormation(int id , Formation formation){
        
         String url = Statics.BASE_URL+"/list_video_detail?"+id;
         req.setUrl(url);
         
         String str = new String(req.getResponseData());
         
         req.addResponseListener(((evt)->{
            
             
             JSONParser jsonp = new JSONParser();
             try{
                 
                 Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                 
                 formation.setUrl(obj.get("url").toString());
                 formation.setTitle(obj.get("title").toString());
                 formation.setDomaine(obj.get("domaine").toString());
                 formation.setDescription(obj.get("description").toString());
                 
                 
             }catch(IOException ex){
                 
                 System.out.println("error related to sql : (" +ex.getMessage());
                 
             }
             
             System.out.println("data === "+str);
             
         }));
         
          NetworkManager.getInstance().addToQueueAndWait(req);
         
         return formation;
         
     }
     
     
     
     public boolean deleteFormation (Formation formation)
     {
         String url = Statics.BASE_URL+"/delete_video?id="+formation.getId();
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
     
     
     
    public boolean modifierFormation (Formation formation)  {
       
       String url = Statics.BASE_URL+"/update_video?id"+formation.getId()+"&url="+formation.getUrl()+"&title="+formation.getTitle()+"&domaine="+formation.getDomaine()+"&description="+formation.getDescription();
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
     
     
     
      public ArrayList<Formation> parseFormations(String jsonText) {
        try {
            formations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> videosListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) videosListJson.get("root");

            for(Map<String,Object> obj : list){
                Formation v = new Formation();
          
                v.setId((int)Float.parseFloat(obj.get("id").toString()));
                v.setUrl(obj.get("url").toString());
                v.setTitle(obj.get("title").toString());
                v.setTitle(obj.get("domaine").toString());
                v.setTitle(obj.get("description").toString());

                formations.add(v);
            }
        } catch (IOException ex) {
            
        }
        return formations;

    }
      
      
      
       public ArrayList<Formation> getFormation(int owner){
        String url = Statics.BASE_URL+"/videos/owner="+owner;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                formations = parseFormations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return formations;
    }
    
}

