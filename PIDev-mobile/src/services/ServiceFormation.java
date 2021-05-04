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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author User
 */
public class ServiceFormation {
    
    
        
     public ArrayList<Formation> formations;
    public static ServiceFormation instance;
    public boolean resultOK;
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

