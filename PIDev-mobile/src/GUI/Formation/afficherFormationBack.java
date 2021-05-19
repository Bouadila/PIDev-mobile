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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.gui.BaseForm;
import java.util.ArrayList;
import services.ServiceFormation;

/**
 *
 * @author User
 */
public class afficherFormationBack extends BaseForm {
    

          
    public afficherFormationBack(Resources res) {
        
        setLayout(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));

        
        super.installSidemenu(res);
        


      

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
          //  Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            

            int height = Display.getInstance().convertToPixels(11.5f);
            int width = Display.getInstance().convertToPixels(14f);

            Button img = new Button(urlim.fill(width, height));
            image.setUIID("Label");

            Container cnt = BorderLayout.west(image);


            
            
            Label space2= new Label(" ");
            

            
            
            
            TextArea title = new TextArea("Titre : "+list.get(i).getTitle());
            title.setUIID("NewsTopLine");
            title.setEditable(false);
            
            BrowserComponent url = new BrowserComponent();
             url.setURL(list.get(i).getUrl());
             url.setVisible(true);
      
            TextArea domaine = new TextArea("Domaine : "+list.get(i).getDomaine());
            domaine.setUIID("NewsTopLine");
            domaine.setEditable(false);
            
            TextArea description = new TextArea("Description : "+list.get(i).getDescription());
            description.setUIID("NewsTopLine");
            description.setEditable(false);
            
            TextArea date = new TextArea("Date : "+list.get(i).getPublish_date());
            date.setUIID("NewsTopLine");
            date.setEditable(false);


            
            cnt.add(BorderLayout.WEST, BoxLayout.encloseY(title,domaine,date,description));


       
        by.addAll(
                
               cnt,
               url,
               space2
               
                
        );
        
            
        }

       add(BorderLayout.NORTH, space4);
       add(BorderLayout.CENTER,by);

    }
    
}
