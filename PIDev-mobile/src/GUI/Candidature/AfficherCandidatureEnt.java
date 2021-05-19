/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Candidature;

import Entities.Candidature;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.pidevapp.gui.BaseForm;
import java.util.ArrayList;
import services.ServiceCandidature;

/**
 *
 * @author A.L.I.C.E
 */
public class AfficherCandidatureEnt extends BaseForm {
    
     @Override
     protected boolean isCurrentCandidature() {
        return true;
    }
   Form current;
   String embededurl;

    public AfficherCandidatureEnt(Resources res)  {


        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        super.installSidemenu(res);
        getToolbar().setTitleComponent(
                      FlowLayout.encloseCenterMiddle(
                                new Label("Candidature", "Title")
                        )
                );
        Form hi = new Form("PDF Viewer", BoxLayout.y());
        Form AjouterCandidature = null;

        ArrayList<Candidature> list = ServiceCandidature.getInstance().AfficherCandidature();
        Container  cnt = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        for (int i = 0; i < list.size(); i++) {
//            Button delete = new Button("delete");
//            Button update = new Button("update");
            Button voircv = new Button("voir cv");
            TextArea num = new TextArea("Num : "+list.get(i).getNum());
            num.setUIID("NewsTopLine");
            num.setEditable(false);
            TextArea status = new TextArea("Status : "+list.get(i).getStatus());
            status.setUIID("NewsTopLine");
            status.setEditable(false);
            TextArea diplome = new TextArea("Diplome : "+list.get(i).getDiplome());
            diplome.setUIID("NewsTopLine");
            diplome.setEditable(false);
            TextArea cv = new TextArea("CV : "+list.get(i).getCv());
            cv.setUIID("NewsTopLine");
            cv.setEditable(false);
            TextArea date = new TextArea("Date de candidature : "+list.get(i).getDate_candidature());
            date.setUIID("NewsTopLine");
            date.setEditable(false);
            String fileName = list.get(i).getCv();
            voircv.addActionListener(e -> {
                FileSystemStorage fs = FileSystemStorage.getInstance();
                
                Display.getInstance().execute(fileName);
            });
            int x = list.get(i).getId();
//              delete.addActionListener(e -> {
//
//               Dialog dig = new Dialog("Suppression");
//               
//               if(dig.show("Suppression","Vous voulez supprimer cette candidature?","Annuler","Oui")){
//                   
//                   dig.dispose();
//               }
//               else {
//                   dig.dispose();
//
//                    Candidature f = new Candidature();
//                    f.setId(x);
//
//                 if(ServiceCandidature.getInstance().deleteCandidature(f)){
//                     
//                      new AfficherCandidatureEnt(res).show();
//                 }
// 
//               }
// 
//        });
//              
//              
//        update.addActionListener(e -> {
//
//                    Candidature f = new Candidature();
//                    
//                    //f.setNum((int)Float.parseFloat(num.getText()));
//                    f.setStatus(status.getText());
//                    f.setDiplome(diplome.getText());
//                    //f.setCv(cv.getText());
//                    //f.setDate_candidature(date.getText());
//
//                    f.setId(x);
//                    new ModifierCandidature(res,f).show();
//
//        });      
        
            
            
            cnt.addAll(status,diplome,cv,voircv,date);


       
       
        
            
        }

       add(cnt);

    }
    
}
