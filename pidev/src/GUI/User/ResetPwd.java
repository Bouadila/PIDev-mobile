/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;


import Entities.User;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.Date;
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
public class ResetPwd  extends Form  {
  public static  TextField emailee;
     Form current;
   public static int randomCode;
   public static String kk ;
   public static String jj ;

    public ResetPwd() {
        current = this;
        setTitle("Mot de passe oublier");
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
        ImageViewer Logo1 = null;
        try {
            Logo1 = new ImageViewer(Image.createImage("/back.jpg"));
        } catch (IOException ex) {
        }
        loginStyle.setBgImage(Logo1.getImage());

        /**
         * ***********************************************************************************************
         */
        Style logoStyle = Logo.getAllStyles();
        logoStyle.setMargin(Component.TOP,60);
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);

        /**
         * ***********************************************************************************************
         */
        TextField emailee = new TextField("", "Saisir votre email");
        Style userStyle = emailee.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStyle.setBgColor(0xffffff);
        userStyle.setBgTransparency(255);
        userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStyle.setMargin(Component.TOP, 5);

          
        /**
         * ***********************************************************************************************
         */
     
        cnt2.addAll(emailee);
        cnt1.add(Logo);
        /**
         * ***********************************************************************************************
         */
        Button btnval = new Button("Valider email ");
        Style butStyle = btnval.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
       butStyle.setBgColor(0x9b0244);
       butStyle.setBgTransparency(255);
    
       butStyle.setFgColor(0xffffff);

      
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.LEFT, 10);
        butStyle.setMargin(Component.RIGHT, 10);
        butStyle.setMargin(Component.BOTTOM, 13);

        //cnt5.add(btnval);

        /**
         * ***********************************************************************************************
         */
    TextField code = new TextField("", "Saisir le code");
        Style userStylecode = code.getAllStyles();
        Stroke borderStrokecode = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        userStylecode.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        userStylecode.setBgColor(0xffffff);
        userStylecode.setBgTransparency(255);
        userStylecode.setMarginUnit(Style.UNIT_TYPE_DIPS);
        userStylecode.setMargin(Component.BOTTOM, 3);

        ////////////////////
        Button btnvalcode = new Button("Valider code ");
        Style butStylecode = btnvalcode.getAllStyles();
        butStylecode.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
       butStylecode.setBgColor(0x9b0244);
       butStylecode.setBgTransparency(255);
    
       butStylecode.setFgColor(0xffffff);

      
        butStylecode.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStylecode.setMargin(Component.BOTTOM, 10);
        butStylecode.setMargin(Component.LEFT, 10);
        butStylecode.setMargin(Component.RIGHT, 10);
        
       
          cnt3.addAll(code);
        /**
         * ***********************************************************************************************
         */
        Button inscrire = new Button("s'inscrire");
        Style butStyle2 = inscrire.getAllStyles();
        butStyle2.setFgColor(0xffffff);
        butStyle2.setBgTransparency(0);
        butStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle2.setMargin(Component.TOP, 0);
     
         Button login = new Button("Login");
        Style butStyle3 = login.getAllStyles();
        butStyle3.setFgColor(0xffffff);
        butStyle3.setBgTransparency(0);
        butStyle3.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle3.setMargin(Component.TOP, 0);
        /**
         * ***********************************************************************************************
         */
        addAll(cnt1, cnt2, btnval,cnt3, cnt5, btnvalcode,login, inscrire);
        /**
         * ***********************************************************************************************
         */
        
           
       
        
        btnval.requestFocus();
        
        btnval.addActionListener(e-> {
                
                   String u = UserService.getInstance().getPasswordByEmail(emailee.getText());
                   System.out.println("password = " + u);

                          if(u.equals("user not found"))
                        { 
                                  
                       Dialog.show("Erreur","l'adress mail n'existe pas",new Command("ok"));
                            
                           
                        }
                       
                                  /* else if (BCrypt.checkpw(tpassword.getText(), pw) == false) {
                            Dialog.show("ERROR", "Mot de passe invalide", new Command("OK"));
                        }*/
                         else  {
                              try{
    Dialog.show("Succés","nous avons envoyer un email",new Command("ok"));
                   
           Random rand =new Random();
        randomCode=rand.nextInt(999999);
        kk=  Integer.toString(randomCode);

        String host ="smtp.gmail.com" ;
        String from ="pidevtestad@gmail.com" ;
        String password ="pidevtestad123456" ;
        String to = emailee.getText();
        jj = emailee.getText();
        String sub ="Mot de passe oublier " ;
        String msg ="Ton code est : " +randomCode ;
 

          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.starttls.enable", "true");    
          props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

          props.put("mail.smtp.port", "587");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });  
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
                    + "    <title>Mot de passe oublier</title>\n"
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
                    + "                        <span style=\"font-weight: bold\">Chèr(e) client </span>\n"
                    + "                         \n"
                    + "                        <p>Afin de pouvoir réinitialiser votre mot de passe, nous devons valider votre adresse e-mail.</p>\n"
                    + "                        <p> Récrire simplement le code envoyer.</p>\n"
                    + "                        "
                    + " <span style=\"font-weight: bold;color: red\">" + msg + "  </span>\n"
                    + "<p>Pour plus d'information n'hésitez pas à nous contacter sur : </p>\n"
                    + "                           <a href=\"mailto:recrutini@gmail.com?subject=réinitialiser%20mot%20de%20passe\">\n"
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
                         System.out.println("message didn't sent successfully");    

              throw new RuntimeException(exx);}    
              
                              
    Dialog.show("Succés","consulter votre boite mail",new Command("ok"));
         // System.out.println("user id = " +u.getId());
                        }catch(Exception ex){
                          Dialog.show("Erreur","l'adress email est incorrect ",new Command("ok"));

                                }
                          }
   
        });
   
      
        /**
         * ***********************************************************************************************
         */

               

             btnvalcode.requestFocus();
             btnvalcode.addActionListener (e-> {
                InfiniteProgress ip = new InfiniteProgress() ;
                final Dialog ipDialog =ip.showInfiniteBlocking();

                        if(code.getText().equals(kk))  {                   
                        ipDialog.dispose();
                        Dialog.show("Succés","le code est correct",new Command("ok"));
                        new ResetPwdCode().show();
                        }
                       
                         else  {
                         ipDialog.dispose();
                         Dialog.show("Erreur","le code est incorrect ",new Command("ok"));
            }
        }); 
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
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
    
    }
    public void sendMail(){ }
   
    
    
   
      
}
