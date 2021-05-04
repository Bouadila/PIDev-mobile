/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formation;

import Entities.Formation;
import com.codename1.components.FloatingHint;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import java.util.Date;
import services.ServiceFormation;
import com.codename1.ui.layouts.FlowLayout;
import static com.mycompany.ListSerie.MyApplication.theme;

/**
 *
 * @author User
 */
public class AjouterFormation extends Form {
//    
    String embededurl;
    ServiceFormation cs = ServiceFormation.getInstance();

    public AjouterFormation()  {
        
          
        
//         super(new BorderLayout());
//        Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//        tb.setUIID("Container");
//        getTitleArea().setUIID("Container");
//        Form previous = Display.getInstance().getCurrent();
//        tb.setBackCommand("", e -> previous.showBack());
//        setUIID("SignIn");
//                
//        TextField username = new TextField("", "Username", 20, TextField.ANY);
//        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
//        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
//        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
//        username.setSingleLineTextArea(false);
//        email.setSingleLineTextArea(false);
//        password.setSingleLineTextArea(false);
//        confirmPassword.setSingleLineTextArea(false);
//        Button next = new Button("Next");
//        Button signIn = new Button("Sign In");
//        signIn.addActionListener(e -> previous.showBack());
//        signIn.setUIID("Link");
//        Label alreadHaveAnAccount = new Label("Already have an account?");
//        
//        Container content = BoxLayout.encloseY(
//                new Label("Sign Up", "LogoLabel"),
//                new FloatingHint(username),
//               // createLineSeparator(),
//                new FloatingHint(email),
//                //createLineSeparator(),
//                new FloatingHint(password),
//                //createLineSeparator(),
//                new FloatingHint(confirmPassword)
//                //createLineSeparator()
//        );
//        content.setScrollableY(true);
//        add(BorderLayout.CENTER, content);
//        add(BorderLayout.SOUTH, BoxLayout.encloseY(
//                next,
//                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
//        ));
//        next.requestFocus();
//        next.addActionListener(e -> new AjouterFormation().show());
//        
        

        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        
        
        ServiceFormation cs = ServiceFormation.getInstance();
        Formation v;
        Form parentForm = null;
        this.getAllStyles().setBgImage(theme.getImage("backgroundForm.jpg"));
        this.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        getToolbar().addMaterialCommandToLeftBar(
                "",
                FontImage.MATERIAL_ARROW_BACK,
                (ev) -> parentForm.showBack());
        Label FormTitle = new Label("Add New Video");
        FormTitle.getAllStyles().setAlignment(CENTER);
        TextField url = new TextField("", "URL", 15, TextField.EMAILADDR);
        TextField title = new TextField("", "Title", 15, TextField.EMAILADDR);
        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());
        TextField description = new TextField("", "Description", 15, TextField.EMAILADDR);
        TextField domaine = new TextField("", "Domaine", 15, TextField.EMAILADDR);
        
        
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
        Button Postvideo = new Button("Post Video");
        Postvideo.getAllStyles().setAlignment(RIGHT);
        Postvideo.setUIID("LoginButton");
        Postvideo.addActionListener(e -> {
            if ((url.getText().length() == 0) || (title.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all fields", new Command("OK"));
            } else {
                //Formation v = new Formation();
                Formation f = new Formation(embededurl, title.getText(),  new Date(System.currentTimeMillis()),domaine.getText(),description.getText());

            }
        });
        Container labels = new Container(BoxLayout.yCenter()).addAll(title,
                url,domaine,description, dateLabel, Postvideo);
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
