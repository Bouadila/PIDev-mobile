/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entities.User;
import static GUI.User.Login.idaffiche;
import static GUI.User.Register.jjactive;
import static GUI.User.Register.kkactivation;
import static GUI.User.ResetPwd.randomCode;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
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
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.UserService;
import static services.UserService.a;

/**
 *
 * @author USER
 */
public class compte extends Form {

       Form current;
    public compte(){
    current=this;
    
        setTitle("Compte");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });
                Style loginStyle = getAllStyles();
          User listu = UserService.getInstance().profilUser(idaffiche);

           ImageViewer Logo1 = null;
        try {
            Logo1 = new ImageViewer(Image.createImage("/back.jpg"));
        } catch (IOException ex) {
        }
        loginStyle.setBgImage(Logo1.getImage());
//        super.addSideMenu(theme);
         System.out.println("idaffiche profil : " +idaffiche);

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("office.png"), spacer1, "15 J'aimes  ", "85 Commentaire", " ");
        addTab(swipe, theme.getImage("back.jpg"), spacer2, "100 J'aimes  ", "66 Commentaire", "");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
      
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4)
        ));
ImageViewer Logo = null;
        try {
            Logo = new ImageViewer(Image.createImage("/recruitini-logo.png"));
        } catch (IOException ex) {
        }
       
        loginStyle.setBgImage(Logo1.getImage());
                Container c9 = new Container(BoxLayout.x());
c9.add(Logo);
        
        Container c0 = new Container(BoxLayout.x());
        Label lbemail = new Label("Adress Email  :  "+listu.getEmail());
        Style userStyle = lbemail.getAllStyles();
        userStyle.setBorder(RoundRectBorder.create().
                strokeOpacity(120));
        userStyle.setFgColor(0xffffff);
        userStyle.setMargin(Component.BOTTOM, 0);
        userStyle.setMargin(Component.TOP, 0);

        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c0.add(lbemail);

        Container c1 = new Container(BoxLayout.x());
        Label lbusername = new Label("Nom  :  "+listu.getName());
        Style userStyle1 = lbusername.getAllStyles();
        userStyle1.setBorder(RoundRectBorder.create().
                strokeOpacity(120));
        userStyle1.setFgColor(0xffffff);
        userStyle1.setMargin(Component.BOTTOM, 0);
        userStyle1.setMargin(Component.TOP, 0);

        userStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c1.add(lbusername);        
        
        Container c3 = new Container(BoxLayout.x());
        Label lbprenom = new Label("Prenom  :  "+listu.getPrenom());
        Style userStyle3 = lbprenom.getAllStyles();
        userStyle3.setBorder(RoundRectBorder.create().
                strokeOpacity(120));
        userStyle3.setFgColor(0xffffff);
        userStyle3.setMarginUnit(Style.UNIT_TYPE_DIPS);
                userStyle3.setMargin(Component.BOTTOM, 0);
        userStyle3.setMargin(Component.TOP, 0);

        c3.add(lbprenom);
        
        Container c4 = new Container(BoxLayout.x());
        Label lbimg = new Label("Role  :  "+listu.getRoles());
        Style userStyle4 = lbimg.getAllStyles();
        userStyle4.setBorder(RoundRectBorder.create().
                strokeOpacity(120));
        userStyle4.setFgColor(0xffffff);
         userStyle4.setMargin(Component.BOTTOM, 0);
         userStyle4.setMargin(Component.TOP, 0);
     
        userStyle4.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c4.add(lbimg);
        
        Container c5 = new Container(BoxLayout.x());
        Label lbgover = new Label("Governorat  :  "+listu.getGover());
        Style userStyle5 = lbgover.getAllStyles();
        userStyle5.setBorder(RoundRectBorder.create().
                strokeOpacity(120) );
        userStyle5.setFgColor(0xffffff);
        userStyle5.setMargin(Component.BOTTOM, 0);
         userStyle5.setMargin(Component.TOP, 0);

        userStyle5.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c5.add(lbgover);
     
        Container c6 = new Container(BoxLayout.x());
        Label lbspecial = new Label("Specialité  :  "+listu.getSpecial());
        Style userStyle6 = lbspecial.getAllStyles();
        userStyle6.setBorder(RoundRectBorder.create().
                strokeOpacity(120));
        userStyle6.setMargin(Component.BOTTOM, 0);
         userStyle6.setMargin(Component.TOP, 0);

        userStyle6.setFgColor(0xffffff);
        userStyle6.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c6.add(lbspecial);


       
        Container cnt1 = new Container(new FlowLayout(CENTER, CENTER));
        Container c_f = new Container(BoxLayout.y());

        Button btValider = new Button("Paramètres du  profil");
        Style butStyle = btValider.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
        strokeColor(0x00000).
        strokeOpacity(120) );
        butStyle.setBgColor(0x9b0244);
        butStyle.setBgTransparency(255);
        butStyle.setFgColor(0xffffff);
         butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 1);
        butStyle.setMargin(Component.TOP, 0);
     
        
        
          Style Stylec = cnt1.getAllStyles();
        Stylec.setBorder(RoundRectBorder.create().
        strokeColor(0x00000).
        strokeOpacity(120) );
        Stylec.setBgColor(0x9b0244);
        Stylec.setBgTransparency(255);
        Stylec.setFgColor(0xffffff);
        Stylec.setMarginUnit(Style.UNIT_TYPE_DIPS);
        Stylec.setMargin(Component.BOTTOM, 1);
        Stylec.setMargin(Component.TOP, 0);
        Stylec.setMargin(Component.RIGHT, 0);
                Stylec.setMargin(Component.LEFT, 0);

            c_f.addAll(c9,c1,c3,c0,c4,c5,c6);
        
        add(c_f);       
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });
       
        
    
          btValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        new profil(idaffiche).show();
    
            }
        });
          
           getToolbar().addCommandToRightBar("Retour",
                null, ev -> {
                    try {    
                        new profil(idaffiche).show();
                    } catch (Exception ex) {

                    }
                });     }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight((int) (Display.getInstance().getDisplayHeight() / 2.5));
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
        
  
    }
    }

    
    


//    private void addButton(int id, String name, String gover, String email) {
//        Container cnt=new Container();
//        TextArea ta = new TextArea(email);
//        ta.setUIID("news"); 
//        ta.setEditable(false);
//        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(ta));
//        add(cnt);
//    }


             