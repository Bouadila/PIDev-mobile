/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entities.User;
import static GUI.User.Register.jjactive;
import static GUI.User.Register.kkactivation;
import static GUI.User.ResetPwd.jj;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import services.UserService;
import static services.UserService.a;

/**
 *
 * @author USER
 */
public class ActiveUser extends Form {
     Form current;

    public ActiveUser() {
        current = this;
        setTitle("Activer Compte");
        setLayout(BoxLayout.y());
        Style loginStyle = getAllStyles();

        Container cnt1 = new Container(new FlowLayout(Container.CENTER));
        Container cnt4 = new Container(new FlowLayout(Container.CENTER));
        Container cnt5 = new Container(new FlowLayout(Container.CENTER));

        Container cnt2 = new Container(new FlowLayout(Container.CENTER));
        Container cnt3 = new Container(new FlowLayout(Container.CENTER));
        ImageViewer Logo = null;
        try {
            Logo = new ImageViewer(Image.createImage("/recruitini-logo.png"));
        } catch (IOException ex) {
        }
//                ImageViewer Logo1 = null;
//
//         try {
//            Logo1 = new ImageViewer(Image.createImage("/back.jpg"));
//        } catch (IOException ex) {
//        }
//        loginStyle.setBgImage(Logo1.getImage());

        /**
         * ***********************************************************************************************
         */
        Style logoStyle = Logo.getAllStyles();
        logoStyle.setMargin(Component.TOP, 120);
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);

        /**
         * ***********************************************************************************************
         */
                Label lbemail = new Label("Code : ");

        TextField Username = new TextField("", "Code envoyer");
        Style userStyle = Username.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle.setBgColor(0xffffff);
        userStyle.setBgTransparency(255);
        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle.setMargin(Component.BOTTOM, 3);
        userStyle.setMargin(Component.TOP, 17);
        
        
        /**
         * ***********************************************************************************************
         */
   
        cnt2.addAll(Username);
        cnt1.add(Logo);
        /**
         * ***********************************************************************************************
         */
        Button btnval = new Button("Valider code ");
         Style butStyle = btnval.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
       butStyle.setBgColor(0x9b0244);
       butStyle.setBgTransparency(255);
    
       butStyle.setFgColor(0xffffff);

      
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 10);
        butStyle.setMargin(Component.TOP, 7);
        butStyle.setMargin(Component.LEFT, 10);
        butStyle.setMargin(Component.RIGHT, 10);

        //cnt5.add(btnval);

        /**
         * ***********************************************************************************************
         */
       Button inscrire = new Button("s'inscrire");
       inscrire.setUIID("CenterLabel");
        Style butStyle2 = inscrire.getAllStyles();
//        butStyle2.setFgColor(0xffffff);
//        butStyle2.setBgTransparency(0);
        butStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle2.setMargin(Component.TOP, 0);
     
         Button login = new Button("Login");
          login.setUIID("CenterLabel");
        Style butStyle3 = login.getAllStyles();
//        butStyle3.setFgColor(0xffffff);
//        butStyle3.setBgTransparency(0);
        butStyle3.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle3.setMargin(Component.TOP, 0);
        /**
         * ***********************************************************************************************
         */
       
      
        addAll(cnt1, cnt2, cnt3, btnval,login, inscrire);
        /**
         * ***********************************************************************************************
         */
        btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Username.getText().length() == 0) ) {
                    Dialog.show("Alert", "vous devez saisir le code envoyer", new Command("OK"));
                } else {
                     if(Username.getText().equals(kkactivation)){
                    try {
                     System.out.println("kk"+kkactivation);    
                     System.out.println("jj"+jjactive);    
                     System.out.println("usern"+Username.getText());              
                        
                  Dialog.show("Succés", "le compte est activer ", new Command("OK"));
                 boolean use = UserService.getInstance().activercompte(jjactive);

                        System.out.println("exist");   
                                                 new  Login().show();                        

                     }
                                            

                     catch (Exception exc) {
                        try {                        
                            new  Login().show();
                        } catch (Exception ex) {
Dialog.show("ERROR", " yezii ", new Command("OK"));                        }

                     }
                     }else{Dialog.show("ERROR", "le code est incorrect ", new Command("OK"));

                        }

                }

            }
        });
        
        
          inscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                    try {

                       
                              new Register().show();                        

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                

            }
        });
        
          login.addActionListener (new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {                        
                    new  Login().show();
                } catch (Exception ex) {
                }
                   
            }
        });
        
        /**
         * ***********************************************************************************************
         */
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });

    
    }   
    
}
