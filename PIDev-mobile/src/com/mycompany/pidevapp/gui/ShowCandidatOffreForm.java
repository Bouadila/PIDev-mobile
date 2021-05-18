/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.ListSerie.MyApplication;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.entities.Offre;
import com.mycompany.pidevapp.services.ServiceOffre;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brahm
 */
public class ShowCandidatOffreForm extends BaseForm {
      @Override
    protected boolean isCurrentCandidatOffre() {
        return true;
    }
    public ShowCandidatOffreForm() {
        installSidemenu(theme);
        setLayout(new BorderLayout());
        getToolbar().setTitleComponent(
                      FlowLayout.encloseCenterMiddle(
                                new Label("Les offres d'emploi disponibles", "Title")
                        )
                );
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage addIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        ArrayList<Offre> list = ServiceOffre.getInstance().getAllOffres();
        Form form = this;
        Container ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                List<Offre> data = list;
                MultiButton[] cmps = new MultiButton[data.size()]; 
                for(int iter = 0 ; iter < cmps.length ; iter++) {
                Offre currentListing = data.get(iter);
                if(currentListing == null) {
                    return null;
                }
                String nom = (String)currentListing.getPost();
                cmps[iter] = new MultiButton(nom);
                Image img ;
                img = MyApplication.theme.getImage("nous-recrutons.png").scaled(300, 400);
                cmps[iter].setIcon(img);
                cmps[iter].setTextLine2("Description : "+currentListing.getDescription());
                cmps[iter].setTextLine3("Domaine : "+currentListing.getDomaine());
                cmps[iter].setTextLine4("Competence : "+currentListing.getCompetences());
                cmps[iter].addActionListener(evt -> {
                    Form hi = null;
                    hi = new OffreCandidatDetailsForm(currentListing, form);
                    hi.show();
 
                });
            }
                return cmps;
        };
    };
//        System.out.println(list.toString());
//        System.out.println(ServiceOffre.getInstance().getOffre(7).toString());
        this.add(BorderLayout.CENTER, ic);
    
}
}
