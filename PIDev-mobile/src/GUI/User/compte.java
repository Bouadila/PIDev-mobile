/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entities.User;
import static GUI.User.Login.idaffiche;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.Switch;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import services.UserService;

/**
 *
 * @author USER
 */
public class compte extends Form {

       Form current;
    public compte(){
    current=this;
     setTitle("S'inscrire");
        setLayout(BoxLayout.y());
        Style loginStyle = getAllStyles();
          User listu = UserService.getInstance().profilUser(idaffiche);
         System.out.println("name" + listu.getName());
         System.out.println("email" + listu.getEmail());
         System.out.println("gover" + listu.getGover());
         System.out.println("special" + listu.getSpecial());
         System.out.println("roles" + listu.getRoles());

  
        Container cont = new Container(new GridLayout(2, 1));
         Container by = new Container(new GridLayout(2, 1));
         by.setScrollableY(true);
         Label space = new Label("   ");

          
          by.addAll(space);
                      
            
            String urlImage = ("/back.jpg");

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        
//        tb.addSearchCommand(e -> {});
        
        
        Image img = theme.getImage("account.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", theme.getImage("account.jpg"), "BottomPad");
        Label twitter = new Label("486 followers", theme.getImage("account.jpg"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
//        
//        
//
//        TextField username = new TextField("sandeep");
//        username.setUIID("TextFieldBlack");
//        addStringValue("Username", username);
//
//        TextField email = new TextField("sandeep@gmail.com", "E-Mail", 20, TextField.EMAILADDR);
//        email.setUIID("TextFieldBlack");
//        addStringValue("E-Mail", email);
//        
//        TextField password = new TextField("sandeep", "Password", 20, TextField.PASSWORD);
//        password.setUIID("TextFieldBlack");
//        addStringValue("Password", password);
//
//        CheckBox cb1 = CheckBox.createToggle(theme.getImage("account.jpg"));
//        cb1.setUIID("Label");
//        cb1.setPressedIcon(theme.getImage("account.jpg"));
//        CheckBox cb2 = CheckBox.createToggle(theme.getImage("account.jpg"));
//        cb2.setUIID("Label");
//        cb2.setPressedIcon(theme.getImage("account.jpg"));
//        
//        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
//        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
// 
//        Button btProfil = new Button("Profil");
//        Style butStyle4 = btProfil.getAllStyles();
//        butStyle4.setBorder(RoundRectBorder.create().
//                strokeColor(0x9b0244).
//                strokeOpacity(120)
//        );
//      
//        butStyle4.setBgTransparency(255);
//        butStyle4.setMarginUnit(Style.UNIT_TYPE_DIPS);
//        butStyle4.setMargin(Component.BOTTOM, 3);
//
//        butStyle4.setMargin(Component.TOP, 1);
//
//        butStyle4.setMargin(Component.LEFT, 10);
//        butStyle4.setMargin(Component.RIGHT, 10);
//        /**
//         * ***********************************************************************************************
//         */
//
//        /**
//         * ***********************************************************************************************
//         */
//   
//          
//          by.addAll(space);
     

        TextField nom = new TextField(listu.getName());
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);

        TextField prenom = new TextField(listu.getEmail());
        prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", prenom);

        TextField email = new TextField(listu.getGover(), "Email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("Email", email);

        TextField adresse = new TextField(listu.getRoles());
        adresse.setUIID("TextFieldBlack");
        addStringValue("Adresse", adresse);

      

    

   

    

    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
    


        
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });
      getToolbar().addCommandToOverflowMenu("Login",
                null, ev -> {
                    try {
                        new Login().show();
                    } catch (IOException ex) {

                    }
                });
 
 
    }

    private void addButton(int id, String name, String gover, String email) {
        Container cnt=new Container();
        TextArea ta = new TextArea(email);
        ta.setUIID("news"); 
        ta.setEditable(false);
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(ta));
        add(cnt);
    }
    
}

             