/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.services;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.pidevapp.entities.Question;
import com.mycompany.pidevapp.entities.Quiz;
import com.mycompany.pidevapp.entities.Reponse;
import com.mycompany.pidevapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author brahm
 */
public class ServiceQuiz {

    public static ServiceQuiz instance = null;
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

    public boolean addQuiz(Quiz quiz) {

        String url = Statics.BASE_URL + "api/quiz/new?nom_quiz=" + quiz.getNom_quiz() + "&nb=" + 0;
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

    public boolean addQuestion(Question ques, ArrayList<String> Reps, int rep) {

        String u = "nom=" + ques.getContenu_ques() + "&nb=" + Reps.size();

        for (int i = 0; i < Reps.size(); i++) {
            int x = i + 1;
            u += "&rep" + x + "=" + Reps.get(i);
        }

        u += "&rep=" + rep;
        System.out.println(u);

        String url = Statics.BASE_URL + "api/ques/new?" + u;
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

    public Quiz takeQuiz(int id) {

        String url = Statics.BASE_URL + "api/quiz/" + id;
        req.setUrl(url);

        req.setPost(false);
        Quiz q = new Quiz();

        req.addResponseListener(((evt) -> {
            ArrayList<Question> ql = new ArrayList();
            ArrayList<Reponse> rl = new ArrayList();
            try {
                JSONParser jsonp = new JSONParser();
                String str = new String(req.getResponseData());
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                q.setNom_quiz(obj.get("nom_quiz").toString());

                JSONObject myjson = new JSONObject(str);
                JSONArray the_json_array = myjson.getJSONArray("questions");
//                 System.out.println(the_json_array);

                Question qu;
                for (int i = 0; i < the_json_array.length(); i++) {
                    str = the_json_array.getString(i);
                    obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                    qu = new Question();
                    qu.setContenu_ques(obj.get("contenu_ques").toString());
                    qu.setId((int) (Float.parseFloat(obj.get("id").toString())));
                    obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                    myjson = new JSONObject(str);
                    JSONObject jo = myjson.getJSONObject("rep_just");
                    qu.setRep_just_id((int)Float.parseFloat(jo.get("id").toString()));
                    JSONArray ja = myjson.getJSONArray("reponses");
                    for (int j = 0; j < ja.length(); j++) {
                        Reponse rp = new Reponse();
                        str = ja.getString(j);
                        obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                        rp.setContenu_rep(obj.get("contenu_rep").toString());
                        rp.setId((int) (Float.parseFloat(obj.get("id").toString())));
                        rp.setId_ques_id(qu.getId());
                        rl.add(rp);

                    }

                    

                    qu.setReponses(rl);
                    rl = new ArrayList();

                    //System.out.println(ja);
                    ql.add(qu);
                }
                q.setQuestions(ql);
//                 Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//                 
//                 formation.setUrl(obj.get("url").toString());
//                 formation.setTitle(obj.get("title").toString());
//                 formation.setDomaine(obj.get("domaine").toString());
//                 formation.setDescription(obj.get("description").toString());
            } catch (JSONException ex) {
            } catch (IOException ex) {
            }

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);
        return q;

    }
    
    public boolean addQuizResult(int quizid, int score, HashMap<Integer, Integer> map){
        
        String u = "quiz="+quizid+"&score="+score;
        int i = 0;
        for(int x : map.keySet()){
            i++;
            u+="&question"+i+"="+x+"&reponse"+i+"="+map.get(x);
        }
        
         String url = Statics.BASE_URL + "api/take?" + u;
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
        System.out.println(resultOK);
        return resultOK;
        
    }

}
