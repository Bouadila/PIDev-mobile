/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Candidature;

import Entities.Candidature;
import com.codename1.components.ScaleImageLabel;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import services.ServiceCandidature;

/**
 *
 * @author A.L.I.C.E
 */
public class AfficherCandidatureEnt extends BaseForm {
    
    @Override
       protected boolean isCurrentCalendar() {
        return true;
       }

    Form current;
   String embededurl;

    public AfficherCandidatureEnt(Resources res)  {


        setLayout(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));

        
        super.installSidemenu(res);
        Button gotoAjouter = new Button("Ajouter Candidature");
        Resources theme = null;
        

        gotoAjouter.addActionListener(e -> {

               new AjouterCandidature(theme).show();
 
        });   
        
        getTitleArea().setUIID("Container");
        setUIID("Welcome");
        Tabs t = new Tabs();
        t.hideTabs();
        t.setUIID("Container");
        t.getContentPane().setUIID("Container");
        add(BorderLayout.CENTER, t);

        
        
        
        Form AjouterCandidature = null;

        //this.getAllStyles().setBgImage(theme.getImage("backgroundForm.jpg"));
        //this.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        getToolbar().addMaterialCommandToLeftBar(
//                "",
//                FontImage.MATERIAL_ARROW_BACK,
//                (ev) -> AjouterCandidature.show());


      

        ///affichage
        
        
        ArrayList<Candidature> list = ServiceCandidature.getInstance().AfficherCandidature();
        System.out.println(list);

        
        
         Container by = new Container(new GridLayout(2, 1));
         by.setScrollableY(true);
         Label space1 = new Label("   ");
         

          
          by.addAll(space1);
          
          
          Label space4 = new Label("  ");

        for (int i = 0; i < list.size(); i++) {
            
   
            
            // for(Candidature f : list){

            String urlImage = ("logo.png");

            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            

            int height = Display.getInstance().convertToPixels(11.5f);
            int width = Display.getInstance().convertToPixels(14f);

            Button img = new Button(urlim.fill(width, height));
            image.setUIID("Label");

            Container cnt = BorderLayout.west(image);

            Button delete = new Button("delete");
            Button update = new Button("update");
            
            
            Label space2= new Label(" ");
            

            
            
            
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

            

            
            int x = list.get(i).getId();
            
              delete.addActionListener(e -> {

               Dialog dig = new Dialog("Suppression");
               
               if(dig.show("Suppression","Vous voulez supprimer cette candidature?","Annuler","Oui")){
                   
                   dig.dispose();
               }
               else {
                   dig.dispose();

                    Candidature f = new Candidature();
                    f.setId(x);

                 if(ServiceCandidature.getInstance().deleteCandidature(f)){
                     
                      new AfficherCandidatureEnt(res).show();
                 }
 
               }
 
        });
              
              
        update.addActionListener(e -> {

                    Candidature f = new Candidature();
                    
                    //f.setNum((int)Float.parseFloat(num.getText()));
                    f.setStatus(status.getText());
                    f.setDiplome(diplome.getText());
                    f.setCv(cv.getText());
                    //f.setDate_candidature(date.getText());

                    f.setId(x);
                    new ModifierCandidature(res,f).show();

        });      
        
            
            
            cnt.add(BorderLayout.WEST, BoxLayout.encloseY(
                    //num,
                    status,diplome,cv,date,BoxLayout.encloseX(update,delete)));


       
        by.addAll(
                
               cnt,
               //url,
               space2
               
                
        );
        
            
        }

       add(BorderLayout.NORTH, space4);
       add(BorderLayout.NORTH, gotoAjouter);
       add(BorderLayout.CENTER,by);

    }
    
}
