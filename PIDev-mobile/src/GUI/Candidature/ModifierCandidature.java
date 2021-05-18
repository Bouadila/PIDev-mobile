/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Candidature;

import Entities.Candidature;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.Date;
import services.ServiceCandidature;
import static com.mycompany.ListSerie.MyApplication.theme;

/**
 *
 * @author A.L.I.C.E
 */
public class ModifierCandidature extends BaseForm {
    String embededurl;
    Form current;
    ServiceCandidature cs = ServiceCandidature.getInstance();
    
    public ModifierCandidature(Resources res, Candidature f)  {

        setLayout(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));
        super.installSidemenu(res);
        
        Form AfficherCandidature = null;
        //this.getAllStyles().setBgImage(theme.getImage("backgroundForm.jpg"));
        //this.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        getToolbar().addMaterialCommandToLeftBar(
                "",
                FontImage.MATERIAL_ARROW_BACK,
                (ev) -> AfficherCandidature.show());
        
        Label FormTitle = new Label("Modifier Candidature");
        FormTitle.getAllStyles().setAlignment(CENTER);
        
        //TextField num = new TextField(f.getNum(), "Num", 15, TextField.ANY);
        //num.setUIID("SignInForm");
        
        TextField status = new TextField(f.getStatus(), "Status", 15, TextField.ANY);
        status.setUIID("SignInForm");
        
        TextField diplome = new TextField(f.getDiplome(), "Diplome", 15, TextField.ANY);
        diplome.setUIID("SignInForm");
        
        TextField cv = new TextField(f.getCv(), "Cv", 15, TextField.ANY);
        cv.setUIID("SignInForm");
        
        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());        
        
        //num.setSingleLineTextArea(true);
        status.setSingleLineTextArea(true);
        diplome.setSingleLineTextArea(true);
        cv.setSingleLineTextArea(true);
        
        Button Post = new Button("Enregistrer");
       // Postvideo.setUIID("LoginButton");
        Post.addActionListener(e -> {
            
                //Formation f = new Formation();
                //f.setNum(num.getNum());
                f.setStatus(status.getText());
                f.setDiplome(diplome.getText());
                f.setCv(cv.getText());
                
                
                if(ServiceCandidature.getInstance().modifierCandidature(f)){
                    new AfficherCandidature(res).show();
                }
                
        Button btnAnnuler = new Button("Annuler");
                btnAnnuler.addActionListener(l ->{
                new AfficherCandidature(res).show();
                });
                

        
        Container labels = new Container(BoxLayout.y()).addAll(
                //num,
                status,diplome,cv, dateLabel, Post,btnAnnuler);
        Container by = new Container(new GridLayout(2, 1));
        by.addAll(
                labels
        );
        add(BorderLayout.NORTH, FormTitle);
        add(BorderLayout.CENTER, by);


        });
        
        
        

    }
    
}
