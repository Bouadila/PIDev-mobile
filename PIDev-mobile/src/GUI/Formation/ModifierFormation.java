/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formation;

import Entities.Formation;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import java.util.Date;
import services.ServiceFormation;

/**
 *
 * @author User
 */
public class ModifierFormation extends Form{
    
    
     String embededurl;
    ServiceFormation cs = ServiceFormation.getInstance();
    Form current;

    public ModifierFormation(Resources res)  {
        



        
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        

        
        


        Form AfficherFormation = null;
        this.getAllStyles().setBgImage(theme.getImage("backgroundForm.jpg"));
        this.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        getToolbar().addMaterialCommandToLeftBar(
                "",
                FontImage.MATERIAL_ARROW_BACK,
                (ev) -> AfficherFormation.show());
   
        
        Toolbar tb = new Toolbar(true);

        current = this;
        setToolbar(tb);
        
        tb.addSearchCommand(e -> {
            
        });
        
        
        
        
        Label FormTitle = new Label("Modifier Video");
        FormTitle.getAllStyles().setAlignment(CENTER);
        TextField url = new TextField("", "URL", 15, TextField.EMAILADDR);
        TextField title = new TextField("", "Title", 15, TextField.EMAILADDR);
        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());
        TextField description = new TextField("", "Description", 15, TextField.EMAILADDR);
        TextField domaine = new TextField("", "Domaine", 15, TextField.EMAILADDR);
       // addStringValue("domaine",domaine);
        

        
        BrowserComponent browser = new BrowserComponent();
        browser.setVisible(false);
        url.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                if (!url.getText().isEmpty()) {
                    embededurl = "https://www.youtube.com/embed/";
                    String code = url.getText().substring(url.getText().length() - 11);
                    embededurl = embededurl + code;
                    browser.setURL(embededurl);
                    browser.setVisible(true);
                }
            }
        });
        Button Postvideo = new Button("Enregistrer");
        Postvideo.setUIID("LoginButton");
        Postvideo.addActionListener(e -> {
            if ((url.getText().length() == 0) || (title.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all fields", new Command("OK"));
            } else {
                Formation f = new Formation();
                f.setUrl(embededurl);
                f.setTitle(title.getText());
                f.setDomaine(domaine.getText());
                f.setDescription(description.getText());
                
                if(ServiceFormation.getInstance().modifierFormation(f)){
                    new AfficherFormation(res).show();
                }
                
               
               //Formation f = new Formation(embededurl, title.getText(),  new Date(System.currentTimeMillis()),domaine.getText(),description.getText());

            }
        });
         
         Button btnAnnuler = new Button();
                btnAnnuler.addActionListener(l ->{
                new AfficherFormation(res).show();
                });
                

        
        Container labels = new Container(BoxLayout.yCenter()).addAll(title,
                url,domaine,description, dateLabel, Postvideo,btnAnnuler);
        Container by = new Container(new GridLayout(2, 1));
        by.addAll(
                labels,
                browser
        );
        add(BorderLayout.NORTH, FormTitle);
        add(BorderLayout.CENTER, by);
      //  add(BorderLayout.SOUTH, Postvideo);
    }
}
