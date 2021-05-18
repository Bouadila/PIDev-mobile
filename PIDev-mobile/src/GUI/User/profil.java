/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entities.User;
import static GUI.User.Login.idaffiche;
import static GUI.User.Login.idlogin;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import java.io.IOException;
import services.UserService;
import static services.UserService.a;

/**
 *
 * @author USER
 */
public class profil  extends Form {
    Form current;
    public profil(int id) {
        current = this;
        setTitle("Acceuil");
        setLayout(BoxLayout.y());
        Style loginStyle = getAllStyles();
      

          
        ImageViewer Logo1 = null;
        try {
            Logo1 = new ImageViewer(Image.createImage("/back.jpg"));
        } catch (IOException ex) {
        }
        loginStyle.setBgImage(Logo1.getImage());

        ImageViewer Logo = null;
        try {
            Logo = new ImageViewer(Image.createImage("/recruitini-logo.png"));
        } catch (IOException ex) {
        }

        /**
         * ***********************************************************************************************
         */
        Style logoStyle = Logo.getAllStyles();
        logoStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        logoStyle.setMargin(Component.TOP, 10);
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);

        
        /**
         * ***********************************************************************************************
         */
            Button btLogout = new Button("Se déconnecter");
        Style butStyle = btLogout.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x9b0244).
                strokeOpacity(120)
        );

        butStyle.setBgTransparency(255);
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 3);

        butStyle.setMargin(Component.TOP, 1);

        butStyle.setMargin(Component.LEFT, 10);
        butStyle.setMargin(Component.RIGHT, 10);
        /**
         * ***********************************************************************************************
         */
        
        Button btCOURSES = new Button("Quiz");
        Style butStyle1 = btCOURSES.getAllStyles();
        butStyle1.setBorder(RoundRectBorder.create().
                strokeColor(0x9b0244).
                strokeOpacity(120)
        );

        butStyle1.setBgTransparency(255);
        butStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle1.setMargin(Component.BOTTOM, 3);

        butStyle1.setMargin(Component.TOP, 1);

        butStyle1.setMargin(Component.LEFT, 10);
        butStyle1.setMargin(Component.RIGHT, 10);
        /**
         * ***********************************************************************************************
         */
        Button btABOONNEMENTS = new Button("Modifier profile");
        Style butStyle2 = btABOONNEMENTS.getAllStyles();
        butStyle2.setBorder(RoundRectBorder.create().
                strokeColor(0x9b0244).
                strokeOpacity(120)
        );
 
        butStyle2.setBgTransparency(255);
        butStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle2.setMargin(Component.BOTTOM, 3);
        butStyle2.setMargin(Component.TOP, 1);

        butStyle2.setMargin(Component.LEFT, 10);
        butStyle2.setMargin(Component.RIGHT, 10);
        /**
         * ***********************************************************************************************
         */
        Button btCompteUser = new Button("Profile");
        Style butStyle3 = btCompteUser.getAllStyles();
        butStyle3.setBorder(RoundRectBorder.create().
                strokeColor(0x9b0244).
                strokeOpacity(120));

        butStyle3.setBgTransparency(255);
        butStyle3.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle3.setMargin(Component.BOTTOM, 3);

        butStyle3.setMargin(Component.TOP,13);

        butStyle3.setMargin(Component.LEFT, 10);
        butStyle3.setMargin(Component.RIGHT, 10);
        /**
         * ***********************************************************************************************
         */
        Button btProfil = new Button("Desactiver Compte");
        Style butStyle4 = btProfil.getAllStyles();
        butStyle4.setBorder(RoundRectBorder.create().
                strokeColor(0x9b0244).
                strokeOpacity(120)
        );
      
        butStyle4.setBgTransparency(255);
        butStyle4.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle4.setMargin(Component.BOTTOM, 3);

        butStyle4.setMargin(Component.TOP, 1);

        butStyle4.setMargin(Component.LEFT, 10);
        butStyle4.setMargin(Component.RIGHT, 10);
        /**
         * ***********************************************************************************************
         */

        /**
         * ***********************************************************************************************
         */
        addAll( Logo,btCompteUser,btABOONNEMENTS,btLogout,btProfil);

        /**
         * ***********************************************************************************************
         */
        btProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          String activU = UserService.getInstance().desactiveUser(idlogin);
    try {
                        a =1;
                        new Login().show();
                    } catch (Exception ex) {

                    }
            }
        });
        
         btLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {a =1;
                    new Login().show();
                } catch (Exception ex) {
                }
    
            }
        });
          
        btABOONNEMENTS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        new modif(id).show();

            }
        });
        btCompteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        new compte().show();
            }
        });

        /**
         * ***********************************************
         */
        getToolbar().addCommandToOverflowMenu("se déconnecter",
                null, ev -> {
                    try {
                        a =1;
                        new Login().show();
                    } catch (Exception ex) {

                    }
                });
        
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });

    }
      public  void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("recruitini-logo.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom()
        ));
        
        tb.addMaterialCommandToSideMenu("Desactiver compte ", FontImage.MATERIAL_UPDATE, e -> { String activU = UserService.getInstance().desactiveUser(idlogin);
    try {         System.out.println("idaffiche profil : " +idaffiche);

                        a =1;
                        new Login().show();
                    } catch (Exception ex) {

                    }});
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {  try {a =1;
                    new Login().show();
                } catch (Exception ex) {
                }});
        
    }
}
