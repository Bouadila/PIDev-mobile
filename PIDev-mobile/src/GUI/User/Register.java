/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Entities.User;
import static GUI.User.ResetPwd.jj;
import static GUI.User.ResetPwd.kk;
import static GUI.User.ResetPwd.randomCode;
import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.UserService;

/**
 *
 * @author USER
 */
public class Register extends Form {
    Form current;
    public static String kkactivation ;
    public static String jjactive ;
    private FileChooser fileChooser ;
   // private File file ;
    private Stage stage ;
    private Image image ;
    private String imgp;
    private URI imguriUri;
    TextField  lbimg ;
 
    public Register(){
    current=this;
        setTitle("S'inscrire");
        setLayout(BoxLayout.y());
        Style loginStyle = getAllStyles();
//        ImageViewer Logo1 = null;
//        try {
//            Logo1 = new ImageViewer(com.codename1.ui.Image.createImage("/back.jpg"));
//        } catch (IOException ex) {
//        }
//        loginStyle.setBgImage(Logo1.getImage());
        
        Container c0 = new Container(BoxLayout.x());
        Label lbemail = new Label("Adress Email : ");
        TextField txemail = new TextField("", "Email");
        Style userStyle = txemail.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle.setBgColor(0xffffff);
        userStyle.setBgTransparency(255);
        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c0.add(lbemail);
        c0.add(txemail);
      
        Container c1 = new Container(BoxLayout.x());
        Label lbusername = new Label("Nom :                 ");
        TextField txusername = new TextField("", "Nom");
        Style userStyle1 = txusername.getAllStyles();
        Stroke borderStroke1 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle1.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle1.setBgColor(0xffffff);
        userStyle1.setBgTransparency(255);
        userStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c1.add(lbusername);
        c1.add(txusername);
        
        Container c2 = new Container(BoxLayout.x());
        Label lbpsw = new Label("Mot de passe :");
        TextField txpsw = new TextField("", "Mot de passe");
        txpsw.setConstraint(TextField.PASSWORD);
        Style userStyle2 = txpsw.getAllStyles();
        Stroke borderStroke2 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle2.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle2.setBgColor(0xffffff);
        userStyle2.setBgTransparency(255);
        userStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c2.add(lbpsw);
        c2.add(txpsw);
        
        Container c3 = new Container(BoxLayout.x());
        Label lbprenom = new Label("Prenom :           ");
        TextField txprenom = new TextField("", "Prenom");
        Style userStyle3 = txprenom.getAllStyles();
        Stroke borderStroke3 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle3.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke3));
        userStyle3.setBgColor(0xffffff);
        userStyle3.setBgTransparency(255);
        userStyle3.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c3.add(lbprenom);
        c3.add(txprenom);
        
        Container c4 = new Container(BoxLayout.x());
        Label lbimg = new Label("Image :              ");
        TextField tximg = new TextField("", "Image");
        Style userStyle4 = tximg.getAllStyles();
        Stroke borderStroke4 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle4.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke4));
        userStyle4.setBgColor(0xffffff);
        userStyle4.setBgTransparency(255);
        userStyle4.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c4.add(lbimg);
        c4.add(tximg);
        
        Container c5 = new Container(BoxLayout.x());
        Label lbgover = new Label("Governorat :     ");
        TextField txtgover = new TextField("", "Governorat");
        Style userStyle5 = txtgover.getAllStyles();
        Stroke borderStroke5 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle5.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke4));
        userStyle5.setBgColor(0xffffff);
        userStyle5.setBgTransparency(255);
        userStyle5.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c5.add(lbgover);
        c5.add(txtgover);
     
        Container c6 = new Container(BoxLayout.x());
        Label lbspecial = new Label("Specialité :       ");
        TextField txtspecial = new TextField("", "Specialité");
        Style userStyle6 = txtspecial.getAllStyles();
        Stroke borderStroke6 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle6.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke4));
        userStyle6.setBgColor(0xffffff);
        userStyle6.setBgTransparency(255);
        userStyle6.setMarginUnit(Style.UNIT_TYPE_DIPS);
        c6.add(lbspecial);
        c6.add(txtspecial);
        Switch gender = new Switch();
        Label lab1 = new Label(" Employeur ");
        Label lab2 = new Label(" Candidat "); 
        
        Style userStyle7 = gender.getAllStyles();
        Stroke borderStroke7 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle7.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke4));
        userStyle7.setBgColor(0xf97878);
        userStyle7.setBgTransparency(255);
        userStyle7.setMarginUnit(Style.UNIT_TYPE_DIPS);
    
        Style userStyle8 = lab1.getAllStyles();
        userStyle8.setBorder(RoundRectBorder.create().
        strokeOpacity(120) );
//        userStyle8.setFgColor(0xffffff);
         
        Style userStyle9 = lab2.getAllStyles();
        userStyle9.setBorder(RoundRectBorder.create().
        strokeOpacity(120) );
//        userStyle9.setFgColor(0xffffff);
       
        Container cnt1 = new Container(new FlowLayout(CENTER, CENTER));
        Container c_f = new Container(BoxLayout.y());
        cnt1.addAll(lab1, gender ,lab2);

        c_f.addAll(c1,c3,c0,c2,c4,c5,c6,cnt1);
               
        Button btValider = new Button("S'inscrire");
        Style butStyle = btValider.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
        strokeColor(0x00000).
        strokeOpacity(120) );
        butStyle.setBgColor(0x9b0244);
        butStyle.setBgTransparency(255);
        butStyle.setFgColor(0xffffff);
     
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 20);
        butStyle.setMargin(Component.TOP, 5);
        butStyle.setMargin(Component.LEFT, 10);
        butStyle.setMargin(Component.RIGHT, 10);
        
        btValider.addActionListener(new ActionListener() {
        @Override
                public void actionPerformed(ActionEvent evt) {
                String Role="";
                if ((txpsw.getText().length()==0)||(txusername.getText().length()==0)||(txemail.getText().length()==0)||(txprenom.getText().length()==0)||(txtgover.getText().length()==0)||(tximg.getText().length()==0))
                {   Dialog.show("Alert", "vous devez Remplir tt les champs", new Command("OK"));}
                else {
//                       if (tximg.getText()!=null) {
//            try {
//                Files.copy(Paths.get(imguriUri), Paths.get("C:\\Users\\USER\\Desktop\\PIDev-java\\PIDev_java\\src\\image\\" + tximg.getText()));
//                Files.copy(Paths.get(imguriUri), Paths.get("C:\\Users\\USER\\Documents\\pidev\\ProjPiDev\\public\\uploads\\image\\" + imgp));
//            } catch (IOException ex) {
//            }
//        }
                   try{
            if(gender.isOn()){
                Role +=  "Candidat\n";
            }else{
                Role +=  "Employeur\n";
            }

                        User t = new User(txemail.getText(),txusername.getText(),txprenom.getText(),txtspecial.getText(),txtgover.getText(),tximg.getText(),txpsw.getText(),Role);
                        boolean use = UserService.getInstance().SignUp(t);
                        Dialog.show("Activation compte","nous avons envoyer un email",new Command("ok"));

                        new ActiveUser().show();                   
                        Random rand =new Random();
                        randomCode=rand.nextInt(999999);
                        kkactivation=  Integer.toString(randomCode);

        String host ="smtp.gmail.com" ;
        String from ="pidevtestad@gmail.com" ;
        String password ="pidevtestad123456" ;
        String to = txemail.getText();
        jjactive = txemail.getText();
        String sub ="Activer compte " ;
        String msg ="Ton code est : " +randomCode ;
 

          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.starttls.enable", "true");    
          props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
          props.put("mail.smtp.port", "587");    
          Session session = Session.getDefaultInstance(props,    
          new javax.mail.Authenticator() {    
          protected PasswordAuthentication getPasswordAuthentication() {    
          return new PasswordAuthentication(from,password);  
           } });  
           System.out.println("message en cour successfully");    

          //compose message    
           try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           message.setContent("<!doctype html>\n"
                    + "<html>\n"
                    + "  <head>\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width\" />\n"
                    + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "    <title>Activez votre compte</title>\n"
                    + "    <style>\n"
                    + "\n"
                    + "      body {\n"
                    + "        background-color: #f6f6f6;\n"
                    + "        font-family: sans-serif;\n"
                    + "        -webkit-font-smoothing: antialiased;\n"
                    + "        font-size: 14px;\n"
                    + "        line-height: 1.4;\n"
                    + "        margin: 0;\n"
                    + "        padding: 0;\n"
                    + "        -ms-text-size-adjust: 100%;\n"
                    + "        -webkit-text-size-adjust: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      table {\n"
                    + "        border-collapse: separate;\n"
                    + "        mso-table-lspace: 0pt;\n"
                    + "        mso-table-rspace: 0pt;\n"
                    + "        width: 100%; }\n"
                    + "        table td {\n"
                    + "          font-family: sans-serif;\n"
                    + "          font-size: 14px;\n"
                    + "          vertical-align: top; \n"
                    + "      }\n"
                    + "\n"
                    + "\n"
                    + "      .body {\n"
                    + "        background-color: #f6f6f6;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      .container {\n"
                    + "        display: block;\n"
                    + "        margin: 0 auto !important;\n"
                    + "        /* makes it centered */\n"
                    + "        max-width: 580px;\n"
                    + "        padding: 10px;\n"
                    + "        width: 580px; \n"
                    + "      }\n"
                    + "\n"
                    + "      .content {\n"
                    + "        box-sizing: border-box;\n"
                    + "        display: block;\n"
                    + "        margin: 0 auto;\n"
                    + "        max-width: 580px;\n"
                    + "        padding: 10px; \n"
                    + "      }\n"
                    + "\n"
                    + "      .main {\n"
                    + "        background: #ffffff;\n"
                    + "        border-radius: 3px;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      .wrapper {\n"
                    + "        box-sizing: border-box;\n"
                    + "        padding: 20px; \n"
                    + "      }\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "      .footer {\n"
                    + "        clear: both;\n"
                    + "        margin-top: 10px;\n"
                    + "        text-align: center;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "        .footer td,\n"
                    + "        .footer p,\n"
                    + "        .footer span,\n"
                    + "        .footer a {\n"
                    + "          color: #999999;\n"
                    + "          font-size: 12px;\n"
                    + "          text-align: center; \n"
                    + "      }\n"
                    + "\n"
                    + "    </style>\n"
                    + "  </head>\n"
                    + "  <body class=\"\">\n"
                    + "    <span class=\"preheader\"></span>\n"
                    + "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\n"
                    + "      <tr>\n"
                    + "        <td class=\"container\">\n"
                    + "          <div class=\"content\">\n"
                    + "\n"
                    + "\n"
                    + "            <table role=\"presentation\" class=\"main\">\n"
                    + "\n"
                    + "              <tr>\n"
                    + "                <td class=\"wrapper\">\n"
                    + "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                    <tr>\n"
                    + "                      <td>\n"
                    + "                          <p> <img src=\"https://i.ibb.co/tmMX6Hn/recruitini-logo.png?fbclid=IwAR2tFKmk1cpPbl36C3txf49oGSWoSIiWMUlvLUUOyzMhlEWH8a-00RXKtW8\" style=\"display: block;margin-left: auto;margin-right: auto; width: 50%\"> </p>\n"
                    + "                        <span style=\"font-weight: bold\">Chèr(e) </span>\n"
                    + "                        <span style=\"font-weight: bold;color: red\">" + txprenom.getText() + " , </span>\n"
                    + "                         \n"
                    + "                        <p>Afin de pouvoir activer votre compte, nous devons valider votre adresse e-mail.</p>\n"
                    + "                        <p> Récrire simplement le code envoyer.</p>\n"
                    + "                        "
                    + " <span style=\"font-weight: bold;color: red\">" + msg + "  </span>\n"
                    + "<p>Pour plus d'information n'hésitez pas à nous contacter sur : </p>\n"
                    + "                           <a href=\"mailto:recrutini@gmail.com?subject=Compte%20de%20" + txprenom.getText() + "\">\n"
                    + "                            <img src=\"https://upload.wikimedia.org/wikipedia/commons/archive/4/4e/20160129092800%21Gmail_Icon.png\" height=\"30\" width=\"30\" style=\"border: none;display: inline-block;color: white;\"></a>\n"
                    + "                          <a href=\"https://www.facebook.com/bureau.alphaservice/\" target=\"_blank\"> <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Facebook-icon-1.png/900px-Facebook-icon-1.png\" height=\"30\" width=\"30\"> </a>\n"
                    + "                          \n"
                    + "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\n"
                    + "                          <tbody>\n"
                    + "                            <tr>\n"
                    + "                              <td align=\"left\">\n"
                    + "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                                  <tbody>\n"
                    + "                                  </tbody>\n"
                    + "                                </table>\n"
                    + "                              </td>\n"
                    + "                            </tr>\n"
                    + "                          </tbody>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                    </tr>\n"
                    + "                  </table>\n"
                    + "                </td>\n"
                    + "              </tr>\n"
                    + "\n"
                    + "            </table>\n"
                    + "\n"
                    + "            <div class=\"footer\">\n"
                    + "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                <tr>\n"
                    + "                  <td class=\"content-block\">\n"
                    + "                    <span class=\"apple-link\">2202 Residence El Amen,Ariana</span>\n"
                    + "                   \n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                  <td class=\"content-block powered-by\">\n"
                    + "                    Agence <a href=\"http://htmlemail.io\">Recrutini</a>.\n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "              </table>\n"
                    + "            </div>\n"
                    + "\n"
                    + "          </div>\n"
                    + "        </td>\n"
                    + "      </tr>\n"
                    + "    </table>\n"
                    + "  </body>\n"
                    + "</html>", "text/html; charset=utf-8");

           //send message  
           Transport.send(message); 
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");

          } catch (MessagingException exx) {
                        Dialog.show("ERROR", "cette Adress email n'existe pas", new Command("OK"));

              throw new RuntimeException(exx);}    
                                           
    Dialog.show("Activation compte","Consulter votre email ",new Command("ok"));
                       
                    }
                catch (NumberFormatException e) {
                        Dialog.show("ERROR", "cette Adress email n'existe pas", new Command("OK"));
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
      getToolbar().addCommandToOverflowMenu("Login",
                null, ev -> {
                    new Login().show();
                });
        
    }
    
//      private void imgchose(javafx.event.ActionEvent event) {
////  stage = (Stage)anchorPane.getScene().getWindow();
////  file =fileChooser.showOpenDialog(stage);
////  if(file != null){
////    System.out.println(""+file.getAbsolutePath());
////    image =new Image(file.getAbsoluteFile().toURI().toString());    
////    imageview.setPreserveRatio(true);}
//    FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
// 
//        final FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(imageFilter);
//        Window stage = null;
//        File file = fileChooser.showOpenDialog(stage);
//        if (file != null) {
//            imgp = file.getName();
//            imguriUri = file.toURI();
//            lbimg.setText(imgp);
//        }
//    }
    
}
