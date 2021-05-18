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
                u.setEmail(UserListJson.get("email").toString());
            }         
        } catch (IOException ex) {  }
        return u;
    }
      
         
             
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
      
        return users;
    }
         


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
            
                    user = parseUtiltry0(new String(req.getResponseData()));
                    req.removeResponseListener(this);
      
                }      }  });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }

  public User parseUtiltry0(String jsonText){    
                User u = new User();
              try {
            JSONParser j = new JSONParser();
            Map<String,Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            if(UserListJson.isEmpty()==false)
             {
double id = (double) UserListJson.get("id");
                                  u.setId((int) id);
               ArrayList<String> roles = (ArrayList<String>) UserListJson.get("roles");
            // we need the first one
            String role = roles.get(0) ;
                       u.setRoles(role);
            }         
        } catch (IOException ex) {  }
        return u;           
    }
 
   public User parseUtiltry(String jsonText){    
                User u = new User();
              try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            if(UserListJson.isEmpty()==false)
             {
double id = (double) UserListJson.get("id");
                                  user.setId((int) id);
               ArrayList<String> roles = (ArrayList<String>) UserListJson.get("roles");
            // we need the first one
            String role = roles.get(0) ;
                       user.setRoles(role);
                users.add(u);            
            }         
        } catch (IOException ex) {  }
        return u;           
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

// ////////////////////////forget pwd///////
public String getPasswordByEmail(String email) {
        String url = "http://127.0.0.1:8000/getPasswordByEmail?&email="+email;
          System.out.println("error related to sql : (" +email);
          System.out.println("error related to sql : (" +user.getEmail());

        req.setUrl(url);
          req.addResponseListener((e)-> {
                      JSONParser j = new JSONParser();
                        mp =new String(req.getResponseData())+ "";                   
            try {
                Map<String,Object> obj = j.parseJSON(new CharArrayReader(mp.toCharArray()));
            } catch (IOException ex) {
            }             
          });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return mp;       
}
 
public User passwordreset (String email,String password) {
        String url = "http://127.0.0.1:8000/getPasswordmobile?&email="+email+"&password="+password;
          System.out.println("error related to sql : (" +password);
          System.out.println("error related to sql : (" +user.getPassword());

        req.setUrl(url);
          req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                if (resultOK)
                { a= 0;
                    user = parseUtiltry(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
        //activecomptemobile


public boolean activercompte (String email) {
        String url = "http://127.0.0.1:8000/activecomptemobile?&email="+email;
          System.out.println("error related to sql : (" +email);
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

public String activetoken(String email) {
        String url = "http://127.0.0.1:8000/tokenlogin?email="+email ;
        req.setUrl(url);

        req.addResponseListener(e->{
             JSONParser j = new JSONParser();
              String str =new String(req.getResponseData())+"";
       
     
                if (str.equals("oui"))
                { 
                 account="existe";

                }
                else if (str.equals("non")) {
                 account="nonexiste";

            }
 System.out.println("acc" +account);

        });
     NetworkManager.getInstance().addToQueueAndWait(req);
        return account;
    }

////////////////////etat

public String activeetat(String email) {
        String url = "http://127.0.0.1:8000/etatcompte?email="+email ;
        req.setUrl(url);

        req.addResponseListener(e->{
             JSONParser j = new JSONParser();
              String str =new String(req.getResponseData())+"";
       
     
                if (str.equals("active"))
                { 
                 activeaccount="active";

                }
                else if (str.equals("nonactive")) {
                 activeaccount="nonactive";

            }
 System.out.println("acc" +activeaccount);

        });
     NetworkManager.getInstance().addToQueueAndWait(req);
        return activeaccount;
    }
//////////////////


 public User profilUser(int id){
      
        String url = "http://127.0.0.1:8000/aff_user_mobile?id="+id ;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                if (resultOK)
                { a = 0;
                    user = parseUsersss(new String(req.getResponseData()));

                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
 }
 

    public User editUser(int idaffiche, String email, String name, String pasword,String prenom, String Special, String Gover, String Img) {
     String url = "http://127.0.0.1:8000/user_ediUser?&id="+idaffiche+"&email="+email+"&name="+name+"&password="+pasword+"&prenom="+prenom+"&special="+Special+"&gover="+Gover+"&img="+Img;                               
     req.setUrl(url);// Insertion de l'URL de notre demande de connexion
              

            req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;

    }
    
      public String desactiveUser(int idaffiche) {

     String url = "http://127.0.0.1:8000/Supp_user_mobile?&id="+idaffiche;                       
      req.setUrl(url);// Insertion de l'URL de notre demande de connexion
                System.out.println("idaffiche " +idaffiche);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if(req.getResponseCode() == 200)
                { 
                    msgActivation ="desactive";
                }else
                {
                 msgActivation ="active";
                }
                req.removeResponseListener(this);
                 System.out.println("msgActivation : " +msgActivation);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return msgActivation;

    }
    
            public String reactiverCompte (int idaffiche) {

     String url = "http://127.0.0.1:8000/reactive_user_mobile?&id="+idaffiche;                       
      req.setUrl(url);// Insertion de l'URL de notre demande de connexion
                System.out.println("idaffiche " +idaffiche);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if(req.getResponseCode() == 200)
                { 
                    msgActivation ="Oui";
                }else
                {
                 msgActivation ="active";
                }
                req.removeResponseListener(this);
                 System.out.println("msgActivation : " +msgActivation);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return msgActivation;

    }
            
      
      
}


      