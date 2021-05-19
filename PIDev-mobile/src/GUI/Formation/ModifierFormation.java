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
import com.mycompany.pidevapp.gui.BaseForm;
import java.util.Date;
import services.ServiceFormation;

/**
 *
 * @author User
 */
public class ModifierFormation extends BaseForm{
    
    
     String embededurl;
    ServiceFormation cs = ServiceFormation.getInstance();
    Form current;

   //  Formation f = new Formation();
    public ModifierFormation(Resources res, Formation f)  {

         setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
         super.installSidemenu(res);

        
    
        
        Label FormTitle = new Label("Modifier Video");
        FormTitle.getAllStyles().setAlignment(CENTER);
        
        TextField url = new TextField(f.getUrl(), "URL", 15, TextField.ANY);
        
        
        TextField title = new TextField(f.getTitle(), "Title", 15, TextField.ANY);
        
        
        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());
        
        TextField description = new TextField(f.getDescription(), "Description", 15, TextField.ANY);
       
        
        TextField domaine = new TextField(f.getDomaine(), "Domaine", 15, TextField.ANY);
      

        
       url.setSingleLineTextArea(true);
       title.setSingleLineTextArea(true);
       description.setSingleLineTextArea(true);
       domaine.setSingleLineTextArea(true);
       

        
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
       // Postvideo.setUIID("LoginButton");
        Postvideo.addActionListener(e -> {

                f.setUrl(embededurl);
                f.setTitle(title.getText());
                f.setDomaine(domaine.getText());
                f.setDescription(description.getText());
                
                if(ServiceFormation.getInstance().modifierFormation(f)){
                    new AfficherFormation(res).show();
                }


        });
         
         Button btnAnnuler = new Button("Annuler");
                btnAnnuler.addActionListener(l ->{
                new AfficherFormation(res).show();
                });
                

        
//        Container labels = new Container(BoxLayout.yCenter()).addAll(title,
//                url,domaine,description, dateLabel, Postvideo,btnAnnuler);
//        labels.setUIID("SignInForm");
        Container by = new Container(new GridLayout(2, 1));
        by.addAll(
                browser
        );
        addAll(FormTitle,title,
                url,domaine,description, dateLabel, Postvideo,btnAnnuler,by);
//        add(BorderLayout.NORTH, );
//        add(BorderLayout.CENTER, by);

    }
}
