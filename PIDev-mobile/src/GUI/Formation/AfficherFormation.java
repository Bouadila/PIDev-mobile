/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formation;

import Entities.Formation;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.gui.BaseForm;
import java.util.ArrayList;
import services.ServiceFormation;


/**
 *
 * @author User
 */


public class AfficherFormation extends BaseForm {


       @Override
          protected boolean isCurrentFormation() {
        return true;
       }
          
    public AfficherFormation(Resources res) {
        
        setLayout(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));
        getToolbar().setTitleComponent(
                      FlowLayout.encloseCenterMiddle(
                                new Label("Formation Disponible", "Title")
                        )
                );
        super.installSidemenu(res);
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage addIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        getToolbar().addCommandToRightBar("", addIcon, (e) -> new AjouterFormation(theme).show());
//        Button gotoAjouter = new Button("Ajouter Formation");
//        
//
//        gotoAjouter.addActionListener(e -> {
//
//               new AjouterFormation(theme).show();
// 
//        });   
//        

      

        ///affichage
        
        
        ArrayList<Formation> list = ServiceFormation.getInstance().AfficherFormation();
        System.out.println(list);

        
        
         Container by = new Container(new GridLayout(2, 1));
         by.setScrollableY(true);
         Label space1 = new Label("   ");
         

          
          by.addAll(space1);
          
          
          Label space4 = new Label("  ");

        for (int i = 0; i < list.size(); i++) {
            
   
            
            // for(Formation f : list){

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

            Button delete = new Button("Supprimer");
            Button update = new Button("Modifier");
            
            
            Label space2= new Label(" ");
            

            
            
            
            TextArea title = new TextArea("Titre : "+list.get(i).getTitle());
            title.setUIID("Label");
            title.setEditable(false);
            
            BrowserComponent url = new BrowserComponent();
             url.setURL(list.get(i).getUrl());
             url.setVisible(true);
      
            TextArea domaine = new TextArea("Domaine : "+list.get(i).getDomaine());
            domaine.setUIID("Label");
            domaine.setEditable(false);
            
            TextArea description = new TextArea("Description : "+list.get(i).getDescription());
            description.setUIID("Label");
            description.setEditable(false);
            
            TextArea date = new TextArea("Date : "+list.get(i).getPublish_date());
            date.setUIID("Label");
            date.setEditable(false);

            

            
            int x = list.get(i).getId();
            
              delete.addActionListener(e -> {

               Dialog dig = new Dialog("Suppression");
               
               if(dig.show("Suppression","Vous voulez supprimer cette formation?","Annuler","Oui")){
                   
                   dig.dispose();
               }
               else {
                   dig.dispose();

                    Formation f = new Formation();
                    f.setId(x);

                 if(ServiceFormation.getInstance().deleteFormation(f)){
                     
                      new AfficherFormation(res).show();
                 }
 
               }
 
        });
              
              
        update.addActionListener(e -> {

                    Formation f = new Formation();

                    f.setTitle(title.getText());
                    f.setUrl(url.getURL());
                    f.setDomaine(domaine.getText());
                    f.setDescription(description.getText());

                    f.setId(x);
                    new ModifierFormation(res,f).show();

        });      
        
            
            
            cnt.add(BorderLayout.WEST, BoxLayout.encloseY(title,domaine,date,description,BoxLayout.encloseX(update,delete)));


       
        by.addAll(
                
               cnt,
               url,
               space2
                
               
                
        );
        
            
        }

       add(BorderLayout.NORTH, space4);
//       add(BorderLayout.NORTH, gotoAjouter);
       add(BorderLayout.CENTER,by);

    }

   

}
