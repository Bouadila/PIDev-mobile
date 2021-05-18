/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.mycompany.ListSerie.MyApplication;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.entities.Offre;
import com.mycompany.pidevapp.services.ServiceOffre;
import java.io.IOException;

/**
 *
 * @author brahm
 */
public class OffreDetailsForm extends BaseForm {

    public OffreDetailsForm(Offre currentListing, Form form) throws IOException {
        installSidemenu(theme);
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        getToolbar().setTitleComponent(
                      FlowLayout.encloseCenterMiddle(
                                new Label("offres d'emploi détails", "Title")
                        )
                );
        getToolbar().addMaterialCommandToLeftBar(
                "",
                FontImage.MATERIAL_ARROW_BACK,
                (ev) ->form.show());
        Offre offre = ServiceOffre.getInstance().getOffre(currentListing.getId());
        System.out.println(offre.toString());
        ImageViewer Logo = new ImageViewer(MyApplication.theme.getImage("nous-recrutons.png"));
        Style logoStyle = Logo.getAllStyles();
        logoStyle.setMargin(Component.TOP, 100);
        Label post = new Label();
        post.setText("Post : "+offre.getPost());
        Label Domaine = new Label();
        Domaine.setText("Domaine : "+offre.getDomaine());
        Label objectif=new Label();
        objectif.setText("Objectif : "+offre.getObjectif());
        Label Experience=new Label();
        Experience.setText("Experience :");
        Label ExperienceMin=new Label();
        ExperienceMin.setText("Minimale : "+offre.getExperienceMin());
        Label ExperienceMax=new Label();
        ExperienceMax.setText("Maximale : "+offre.getExperienceMax());
        Label contrat=new Label();
        contrat.setText("Type Contrat : "+offre.getContrat().getType());
        Label sailaire=new Label();
        sailaire.setText("Sailaire : "+offre.getSalaire()+" DT");
        Label Competences=new Label();
        Competences.setText("Competence : "+offre.getCompetences());
        Label Description=new Label();
        Description.setText("Description : ");
        Label DescriptionText=new Label();
        DescriptionText.setText(offre.getDescription());
        Button delete = new Button("Supprimer");
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        Style butStyle = delete.getAllStyles();
                butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
        delete.addActionListener(e->{
            if( ServiceOffre.getInstance().deleteOffre(offre))
                { 
                  Dialog.show("Success","Connection accepted",new Command("OK"));
                  new CalendarForm().show();
                }
                else
                {Dialog.show("ERROR", "Server error", new Command("OK"));}
            
        });
        Button update = new Button("Modifir");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateOffreForm f = new UpdateOffreForm(offre);
                f.show();
            }
        });
        Style butStyleUpdate = update.getAllStyles();
                butStyleUpdate.setBorder(RoundRectBorder.create().
                strokeColor(0x00000).
                strokeOpacity(120).
                stroke(borderStroke));
//        post.setText("Post : "+offre.getPost());
        addAll(Logo,BoxLayout.encloseX(new Label("         "),update,delete),post,objectif,contrat,Experience,BoxLayout.encloseX(ExperienceMin,ExperienceMax),sailaire,Competences,Description,DescriptionText);
    }

    
}
