/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entities.User;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
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
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;
import static services.UserService.a;
/**
 *
 * @author USER
 */
public class Login  extends Form {
    Form current;
    static  int userexist; 
    public static  int idaffiche;
    public static  int idlogin;
    public Login() {
        
        current = this;
        setTitle("Se connecter");
        setLayout(BoxLayout.y());
        addSideMenu(theme);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });
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
        ImageViewer Logo1 = null;
        try { Logo1 = new ImageViewer(Image.createImage("/back.jpg"));
        } catch (IOException ex) {
        }  loginStyle.setBgImage(Logo1.getImage());

        /**
         * ***********************************************************************************************
         */
        Style logoStyle = Logo.getAllStyles();
        logoStyle.setMargin(Component.TOP, 100);
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);

        /**
         * ***********************************************************************************************
         */
        TextField Username = new TextField("", " Email ");
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
        userStyle.setMargin(Component.TOP, 10);
        /**
         * ***********************************************************************************************
         */
        TextField tpassword = new TextField();
        Style passwordStyle = tpassword.getAllStyles();
        passwordStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(50).
                stroke(borderStroke));
        passwordStyle.setBgColor(0xffffff);
        passwordStyle.setBgTransparency(255);
        tpassword.setHint(" Mot de passe");
        tpassword.setConstraint(TextField.PASSWORD);
        cnt2.addAll(Username);
        cnt3.add(tpassword);
        cnt1.add(Logo);       
        /**
         * ***********************************************************************************************
         */  
        Button btnval = new Button("Se connecter ");
        Style butStyle = btnval.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        butStyle.setBgColor(0x9b0244);
        butStyle.setBgTransparency(255);
        butStyle.setFgColor(0xffffff);
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 3);
        butStyle.setMargin(Component.TOP, 10);
        butStyle.setMargin(Component.LEFT, 10);
        butStyle.setMargin(Component.RIGHT, 10);

        /**
         * ***********************************************************************************************
         */
        Button motOublier = new Button("Mot de passe oublié ?");
        Style butStyle1 = motOublier.getAllStyles();

        butStyle1.setFgColor(0xffffff);
        butStyle1.setBgTransparency(0);
        butStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle1.setMargin(Component.TOP, 10);
        cnt5.add(motOublier);
        /**
         * ***********************************************************************************************
         */
        Button inscrire = new Button("s'inscrire");
        Style butStyle2 = inscrire.getAllStyles();
        butStyle2.setFgColor(0xffffff);
        butStyle2.setBgTransparency(0);
        butStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle2.setMargin(Component.TOP, 0);
        /**
         * ***********************************************************************************************
         */
        addAll(cnt1, cnt2, cnt3, btnval, cnt5, inscrire);
        /**
         * ***********************************************************************************************
         */      

        btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    if ((Username.getText().length() == 0) || (tpassword.getText().length() == 0)) {
                    Dialog.show("Alert", "vous devez saisir votre email et mot de passe", new Command("OK"));
                    } else 
                    {           
                        User u = UserService.getInstance().SignIn(Username.getText(), tpassword.getText());    
                        if((a==1)|| (u.getId()== 0))
                        {Dialog.show("ERROR", "Mot de passe ou email incorrect ", new Command("OK"));
                        userexist=0;  
                        } else {
                        String use = UserService.getInstance().activetoken(Username.getText());
                        String actU = UserService.getInstance().activeetat(Username.getText());
                        String reactU = UserService.getInstance().reactiverCompte(u.getId());                       
                        
                        idaffiche = u.getId(); 
                        idlogin= u.getId(); 
                        if( (use.equals("existe")))
                                { userexist = 1;    
                                if(actU.equals("active"))
                                { new  profil(idaffiche).show();              
                                } else{
                                Dialog.show("Compte désactiver", "vous voulez reactiver votre compte ", new Command(reactU),new Command("cancel"));                
                                } }                                   
                        else  {Dialog.show("ERROR", "Mot de passe ou email incorrect", new Command("OK"));
                            userexist=0;  }  
                        }}}
        });

            motOublier.addActionListener (new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                    try {
                    new  ResetPwd().show();                        
                    } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    } }  });
              
        /**
         * ***********************************************************************************************
         */
            inscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    try {
                        User u = UserService.getInstance().SignIn(Username.getText(), tpassword.getText());                      
                        new Register().show();                        
                        } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        }       }
        });
               
        /**
         * ***********************************************************************************************
         */
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> { Display.getInstance().exitApplication();
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
    try {
                        a =1;
                        new Login().show();
                    } catch (Exception ex) {

                    }});
        tb.addMaterialCommandToSideMenu("Compte", FontImage.MATERIAL_SETTINGS, e -> {  new compte().show();});
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {  try {a =1;
                    new Login().show();
                } catch (Exception ex) {
                }});
    }
}

