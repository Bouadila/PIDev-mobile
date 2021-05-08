/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formation;

import Entities.Formation;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import java.util.ArrayList;
import services.ServiceFormation;

/**
 *
 * @author User
 */
public class AfficherFormation extends Form {



    public AfficherFormation(Resources res) {
        super(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));

        
       
        getTitleArea().setUIID("Container");
        setUIID("Welcome");
        Tabs t = new Tabs();
        t.hideTabs();
        t.setUIID("Container");
        t.getContentPane().setUIID("Container");
        add(BorderLayout.CENTER, t);

        
        
        
        Form AjouterFormation = null;

        this.getAllStyles().setBgImage(theme.getImage("backgroundForm.jpg"));
        this.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        getToolbar().addMaterialCommandToLeftBar(
                "",
                FontImage.MATERIAL_ARROW_BACK,
                (ev) -> AjouterFormation.show());

        Toolbar tb = new Toolbar(true);


        setToolbar(tb);

        tb.addSearchCommand(e -> {

        });
        
        
//        Container c3 = new Container(BoxLayout.x());
//        BrowserComponent map = new BrowserComponent() {
//            @Override
//            protected Dimension calcPreferredSize() {
//                Dimension d = super.calcPreferredSize();
//                d.setHeight(Display.getInstance().getDisplayHeight() * 50 / 100);
//                d.setWidth(Display.getInstance().getDisplayWidth());
//                return d;
//            }
//        };
//        map.setURL("https://goo.gl/maps/1w7XZMhiYdubNxV98");
//        c3.add(map);
//
//        ButtonGroup bg = new ButtonGroup();
//        int size = Display.getInstance().convertToPixels(1);
//        Image unselectedWalkthru = Image.createImage(size, size, 0);
//        Graphics g = unselectedWalkthru.getGraphics();
//        g.setColor(0xcccccc);
//        g.setAntiAliased(true);
//        g.fillArc(0, 0, size, size, 0, 360);
//        Image selectedWalkthru = Image.createImage(size, size, 0);
//        g = selectedWalkthru.getGraphics();
//        g.setColor(0xff2d55);
//        g.setAntiAliased(true);
//        g.fillArc(0, 0, size, size, 0, 360);
//        RadioButton[] rbs = new RadioButton[t.getTabCount()];
//        FlowLayout flow = new FlowLayout(CENTER);
//        flow.setValign(CENTER);
//        Container radioContainer = new Container(flow);
//        for (int iter = 0; iter < rbs.length; iter++) {
//            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
//            rbs[iter].setPressedIcon(selectedWalkthru);
//            rbs[iter].setUIID("Label");
//            radioContainer.add(rbs[iter]);
//        }
//
//        Button skip = new Button("Skip");
//        skip.setUIID("SkipButton");
//        // skip.addActionListener(e -> new AjouterFormation().show());
//
//        Container welcomeNoteArea = BoxLayout.encloseY(
//                LayeredLayout.encloseIn(
//                        radioContainer,
//                        BorderLayout.east(skip)
//                )
//        );
//        welcomeNoteArea.setUIID("WelcomeNoteArea");
//        add(BorderLayout.SOUTH, welcomeNoteArea);

        ///affichage
        
        
        ArrayList<Formation> list = ServiceFormation.getInstance().AfficherFormation();
        System.out.println(list);

        
        
         Container by = new Container(new GridLayout(2, 1));
         by.setScrollableY(true);
         Label space1 = new Label("   ");
         

          
          by.addAll(space1);
          
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

            Button delete = new Button("delete");
            
            
            Label space2= new Label(" ");
            Label space3= new Label(" ");

            
            
            
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
                    
 
                   
                   ServiceFormation.getInstance().deleteFormation(f); 
                   
//                       new AfficherFormation(res);
//                       
//                   }
               }
              //  cs.AjouterFormation(f);
                
               
                
           
        });
        
            
            
            cnt.add(BorderLayout.WEST, BoxLayout.encloseY(title,domaine,description,date));
            cnt.add(BorderLayout.EAST, BoxLayout.encloseX(delete));

            
        by.addAll(
                
               cnt,
               url,
               space2
               
                
        );
        
            
        }

       add(BorderLayout.CENTER,by);





 
        

    }

   

}
