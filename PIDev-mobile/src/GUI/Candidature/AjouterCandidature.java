/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Candidature;

import Entities.Candidature;
import static GUI.User.Login.idaffiche;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.util.Date;
import services.ServiceCandidature;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;


import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.gui.BaseForm;
import com.mycompany.pidevapp.gui.ShowCandidatOffreForm;
import com.mycompany.pidevapp.gui.TakeQuiz;
import com.mycompany.pidevapp.services.ServiceQuiz;
import java.util.Map;
/**
 *
 * @author A.L.I.C.E
 */
public class AjouterCandidature extends BaseForm {
    
    ServiceCandidature cs = ServiceCandidature.getInstance();
    
    public AjouterCandidature(Resources res, int idOffre)  {
 
        super.installSidemenu(res);
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        Button img1 = new Button("upload cv");

//        getToolbar().addMaterialCommandToLeftBar(
//                "",
//                FontImage.MATERIAL_ARROW_BACK,
//                (ev) ->new  AfficherCandidatureCand(theme).show());
//        
        
//        CheckBox multiSelect = new CheckBox("Multi-select");
//        img1.addActionListener((ActionEvent e) -> {
//            
//            if (FileChooser.isAvailable()) {
//                FileChooser.setOpenFilesInPlace(true);
//                FileChooser.showOpenDialog(".pdf", (ActionEvent e2) -> {
//                    if (e2 == null || e2.getSource() == null) {
//                        add("No file was selected");
//                        revalidate();
//                        return;
//                    }
//                    if (multiSelect.isSelected()) {
//                        String[] paths = (String[]) e2.getSource();
//                        for (String path : paths) {
//                            System.out.println(path);
//                            CN.execute(path);
//                        }
//                        return;
//                    }
//
//                    String file = (String) e2.getSource();
//                    if (file == null) {
//                        add("No file was selected");
//                        revalidate();
//                    } else {
//                        Image logo;
//
//                        try {
//                            logo = Image.createImage(file).scaledHeight(500);;
//                            add(logo);
//                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";
//
//                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
//                                System.out.println(imageFile);
//                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
//                            } catch (IOException err) {
//                            }
//                        } catch (IOException ex) {
//                        }
//
//                        String extension = null;
//                        if (file.lastIndexOf(".") > 0) {
//                            extension = file.substring(file.lastIndexOf(".") + 1);
//                            StringBuilder hi = new StringBuilder(file);
//                            if (file.startsWith("file://")) {
//                                hi.delete(0, 7);
//                            }
//                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
//                            Log.p(hi.toString());
//                            String ext = hi.toString().substring(lastIndexPeriod);
//                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
//                            try {
//                                String namePic = saveFileToDevice(file, ext);
//                                System.out.println(namePic);
//                            } catch (IOException ex) {
//                            }
//
//                            revalidate();
//
//                        
//                    }
//                    }
//                        });
//            }
//                });
//        
        
        
        Label FormTitle = new Label("Ajouter Candidature");
        FormTitle.getAllStyles().setAlignment(CENTER);
        
//        TextField num = new TextField("", "Num", 15, TextField.EMAILADDR);
//        addStringValue("num",num);
//        num.setUIID("SignInForm");
        
//        TextField status = new TextField("", "Status", 15, TextField.EMAILADDR);
//        addStringValue("status",status);
        Label lbStatus = new Label("Status : ");
        String[] elements = {"------Sélectionner un element--------","Employé(e)","Sans emploi","Indépendant"};
        ComboBox <String> status = new ComboBox();
        for(int i=0; i<elements.length;i++){
            status .addItem(elements[i]);
        }
//        TextField diplome = new TextField("", "Diplome", 15, TextField.EMAILADDR);
//        addStringValue("diplome",diplome);
        Label lbDiplome = new Label("Diplome : ");
         String[] dp = {"------Sélectionner un element--------","Bac","Doctorat","Ingénieur","Master"};
        ComboBox <String> diplome = new ComboBox();
        for(int i=0; i<dp.length;i++){
            diplome.addItem(dp[i]);
        }
        TextField Numéro  = new TextField("", "Numéro de Télephone", 15, TextField.EMAILADDR);
        addStringValue("Numéro de Télephone",Numéro);
        TextField cv = new TextField("", "cv", 15, TextField.EMAILADDR);
        addStringValue("cv",cv);
        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());

        Button Post = new Button("Ajouter");
        addStringValue("",Post);
        
//        Post.getAllStyles().setAlignment(RIGHT);
//       // Postvideo.setUIID("FormationButton");
        Post.addActionListener((ActionEvent e) -> {
                
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
                

                Candidature f = new Candidature(String.valueOf(status.getSelectedItem()), String.valueOf(diplome.getSelectedItem()), String.valueOf(cv.getText()));
                f.setCandidat_id(idaffiche);
                f.setOffre_id(idOffre);
                f.setNum(Integer. parseInt(Numéro.getText()));
                System.out.println("data candidature =="+f);
                if( cs.AjouterCandidature(f))
                { 
                    Dialog.show("Success","Candidature Ajouter",new Command("OK"));
//                    refreshTheme();
                    ServiceQuiz sq = new ServiceQuiz();
                    int idQuiz =sq.getOffreQuizId(idOffre);
                    if( idQuiz != 0){
                        new TakeQuiz(idQuiz).show();
                    }else{
                        new ShowCandidatOffreForm().show();
                    } 
                        
                    
                  }else
                {Dialog.show("ERROR", "Server error", new Command("OK"));}
                
//                iDialog.dispose();
//                new AfficherCandidatureCand(res).show();
                

        });
        addAll(lbStatus, status,lbDiplome ,diplome,Numéro,cv, dateLabel,img1, Post);
//        
//        Container labels = new Container(BoxLayout.y()).addAll(
//                //num,
//                status,diplome,cv, img1,dateLabel, Post);
//        Container by = new Container(new GridLayout(2, 1));
//        by.addAll(
//                labels
//        );
//
//        add(BorderLayout.NORTH,FormTitle);
//        add(BorderLayout.CENTER, by);
//      //  add(BorderLayout.SOUTH, Post);
    }
    
    private void addStringValue(String s , Component v){

//       add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        //add(BorderLayout.CENTER,createLineSeparator(0xeeeeee));
        
    }
    
    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }

    }    
    

