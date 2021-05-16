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
import com.codename1.ui.events.ActionListener;
import com.mycompany.pidevapp.entities.Question;
import com.mycompany.pidevapp.entities.Quiz;
import com.mycompany.pidevapp.entities.Reponse;
import com.mycompany.pidevapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author brahm
 */
public class ServiceQuiz {
    public static ServiceQuiz instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceQuiz() {
         req = new ConnectionRequest();
    }

    public static ServiceQuiz getInstance() {
        if (instance == null) {
            instance = new ServiceQuiz();
        }
        return instance;
    }
    
    
    
    public boolean addQuiz(Quiz quiz){
        
        String url = Statics.BASE_URL+"api/quiz/new?nom_quiz="+quiz.getNom_quiz()+"&nb="+0;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.setPost(true);
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
    
    
    public boolean addQuestion(Question ques, ArrayList<String> Reps, int rep){
        
        String u = "nom="+ques.getContenu_ques()+"&nb="+Reps.size();
        
        for(int i = 0; i < Reps.size(); i++){
            int x = i+1;
            u+="&rep"+x+"="+Reps.get(i);
        }
        
        u+="&rep="+rep;
        
        String url = Statics.BASE_URL+"api/ques/new?"+u;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.setPost(true);
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
