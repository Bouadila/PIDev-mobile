/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.User;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author USER
 */
public class UserService {
private ConnectionRequest req;
private boolean resultOK;
public static UserService  instance=null;
public ArrayList<User> users;
String msgActivation ="active" ;
public User user=new User();
public static int a;
public static String account="";
public static String activeaccount="";

private String mp = " " ;

    public UserService() {
         req = new ConnectionRequest() {
            @Override
            protected void handleErrorResponseCode(int code, String message) {
                if (code == 401) { // Unauthorized
                } else if (code == 404 || code == 409)
                { }
                else {
                    Dialog.show("Error", code + ": " + message, "Retry", "Cancel");
                }
            }
        };
    }
    
     public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
     

     
          public User parseUsersss(String jsonText){
       
                User u = new User();
              try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

           if(UserListJson.isEmpty()==false)
           {
                u.setPrenom(UserListJson.get("prenom").toString());

                u.setName(UserListJson.get("name").toString());
                                u.setImg(UserListJson.get("img").toString());

                u.setGover(UserListJson.get("gover").toString());
                u.setSpecial(UserListJson.get("special").toString());
                
           u.setRoles(UserListJson.get("roles").toString());

                users.add(u);
            
            }
            
        } catch (IOException ex) {
            
        }
        return u;
    }
     
//         public User getUser(String username){
//        String url = Statics.BASE_URL+"/signin/"+username;
//      
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {     
//                user = parseUser(new String(req.getResponseData()));              
//                req.removeResponseListener(this);
//            
//                        }
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return user;
//    }
//    
   
         
             
        public ArrayList<User> parseUsers(String jsonText){
             try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
    Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
          
              
           java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)UsersListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User u= new User();
                float id = Float.parseFloat(obj.get("id").toString());
                 u.setId((int)id);
                u.setName(obj.get("name").toString());
                u.setGover(obj.get("gover").toString());
                u.setEtat(0);
                u.setPassword(obj.get("password").toString());
                u.setEmail(obj.get("email").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setSpecial(obj.get("special").toString());
                u.setImg(obj.get("img").toString());
                u.setActivation_token(obj.get("activation_token").toString());

                u.setRoles(obj.get("roles").toString());
             

                //Ajouter la tâche extraite de la réponse Json à la liste
                 users.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return users;
    }
         
         
    
//        public ArrayList<User> getPartenaires(){
//        String url = Statics.BASE_URL+"/PARTENAIRES";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                users = parseUsers(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return users;
//    }
//        public boolean createUser(User user) {
//        String url = "http://127.0.0.1:8000/registrationmob?&email="+user.getEmail()+"&roles="+user.getRoles()+"&name="+user.getName()+"&prenom="+user.getPrenom()+"&password="+user.getPassword()+"&special="+user.getSpecial()+"&gover="+user.getGover()+"&img="+user.getImg()+"&etat="+user.getEtat();                       
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    } 


public User SignIn(String email , String password) {
        String url = "http://127.0.0.1:8000/signin" ;
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        req.setContentType("application/json");
        req.setRequestBody("{\n" +
                "    \"username\": \""+email+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}");
   

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                if (resultOK)
                { a= 0;
            
                    user = parseUser(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                    
                    
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }


// only id and roles
    public User parseUser(String jsonText){
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = null;
            try {
                tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            User user = new User() ;
            // get User Id
            double id = (double) tasksListJson.get("id");
            // Arraylist because we have two roles
            ArrayList<String> roles = (ArrayList<String>) tasksListJson.get("roles");
            // we need the first one
            String role = roles.get(0) ;

            // set info to user
            user.setId((int) id);
            user.setRoles(role);
            return user;
    }

//   public User parseUser(String jsonText){
//              User u = new User();
//              try {
//            users=new ArrayList<>();
//            JSONParser j = new JSONParser();
//           
//            Map<String,Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//           if(UserListJson.isEmpty()==false)
//           {  float id = Float.parseFloat(UserListJson.get("id").toString());
//                 u.setId((int)id);
//                u.setPrenom(UserListJson.get("prenom").toString());
//
//                u.setName(UserListJson.get("name").toString());
//                                u.setImg(UserListJson.get("img").toString());
//
//                u.setGover(UserListJson.get("gover").toString());
//                u.setSpecial(UserListJson.get("special").toString());
//               u.setActivation_token(obj.get("activation_token").toString());
//
//           u.setRoles(UserListJson.get("roles").toString());
//
//                users.add(u);
//            
//            } } catch (IOException ex) {
//            
//        }
//            return user;
//    }

public boolean SignUp(User user) {
            String url = "http://127.0.0.1:8000/newFsmb?&email="+user.getEmail()+"&name="+user.getName()+"&prenom="+user.getPrenom()+"&prenom="+user.getPrenom()+"&password="+user.getPassword()+"&special="+user.getSpecial()+"&gover="+user.getGover()+"&img="+user.getImg()+"&etat="+user.getEtat()+"&roles="+user.getRoles();                       
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


      