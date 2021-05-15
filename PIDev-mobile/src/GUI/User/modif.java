/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entities.User;
import static GUI.User.Login.idaffiche;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import services.UserService;

/**
 *
 * @author USER
 */
public class modif  extends Form{
     Form current;
    public modif(){
    current=this;
     setTitle("Modifier Compte");
        setLayout(BoxLayout.y());
        Style loginStyle = getAllStyles();
        
        Container c0 = new Container(BoxLayout.x());
        Label lbemail = new Label("Email : ");
        TextField txemail = new TextField("", "Email");
        c0.add(lbemail);
        c0.add(txemail);
    
        Container c1 = new Container(BoxLayout.x());
        Label lbusername = new Label("Nom : ");
        TextField txusername = new TextField("", "Nom");
        c1.add(lbusername);
        c1.add(txusername);
        
        Container c2 = new Container(BoxLayout.x());
        Label lbpsw = new Label("Mot de passe : ");
        TextField txpsw = new TextField("", "Mot de passe");
        txpsw.setConstraint(TextField.PASSWORD);
        c2.add(lbpsw);
        c2.add(txpsw);
        
        Container c3 = new Container(BoxLayout.x());
        Label lbprenom = new Label("Prenom : ");
        TextField txprenom = new TextField("", "Prenom");
        c3.add(lbprenom);
        c3.add(txprenom);
        
        Container c4 = new Container(BoxLayout.x());
        Label lbimg = new Label("Image : ");
        TextField tximg = new TextField("", "Image");
        c4.add(lbimg);
        c4.add(tximg);
        
        Container c5 = new Container(BoxLayout.x());
        Label lbgover = new Label("Governorat : ");
        TextField txtgover = new TextField("", "Governorat");
        c5.add(lbgover);
        c5.add(txtgover);
     
        Container c6 = new Container(BoxLayout.x());
        Label lbspecial = new Label("Specialité : ");
        TextField txtspecial = new TextField("", "Specialité");
        c6.add(lbspecial);
        c6.add(txtspecial);
        
        
        Container c_f = new Container(BoxLayout.y());
        c_f.addAll(c1,c3,c0,c2,c4,c5,c6);
        
                Button btValider = new Button("Valider");
     Style butStyle = btValider.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120)
                );
       butStyle.setBgColor(0x9b0244);
       butStyle.setBgTransparency(255);
    
       butStyle.setFgColor(0xffffff);

      
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 3);
        butStyle.setMargin(Component.TOP, 10);
        butStyle.setMargin(Component.LEFT, 10);
        butStyle.setMargin(Component.RIGHT, 10);
             btValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((txpsw.getText().length()==0)||(txusername.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   try{


                  String Role="Candidat";
                  
                        System.out.println("password"+idaffiche);
                        User u = UserService.getInstance().editUser(idaffiche,txemail.getText(),txusername.getText(),txpsw.getText());
                                      new  profil(idaffiche).show();                        

                   }
                catch (NumberFormatException e) {
                        Dialog.show("ERROR", "erreur", new Command("OK"));
                    }
                    
                }
                
                
            }


        }); 
       c_f.add(btValider);
        
        add(c_f);
        
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });
      
        
    }
}
