/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.pidevapp.gui;

import Entities.User;
import GUI.Candidature.AfficherCandidatureEnt;
import GUI.Formation.AfficherFormation;
import static GUI.User.Login.idaffiche;
import GUI.User.compte;
import GUI.User.profil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import services.UserService;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    User listu = UserService.getInstance().profilUser(idaffiche);
    //listu.getRoles()
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");

        Image CandidatOffre = null;
        if (isCurrentCandidatOffre()) {
            CandidatOffre = selection;
        }

        Image ProfilImage = null;
        if (isCurrentProfil()) {
            ProfilImage = selection;
        }

        Image calendarImage = null;
        if (isCurrentCalendar()) {
            calendarImage = selection;
        }

        Image FormationImage = null;
        if (isCurrentFormation()) {
            FormationImage = selection;
        }

        Image offreImage = null;
        if (isCurrentOffre()) {
            offreImage = selection;
        }
        Image mapImage = null;
        if (isCurrentMapImage()) {
            mapImage = selection;
        }
         Image CandidatCandidature = null;
        if (isCurrentCandidature()) {
            CandidatCandidature = selection;
        }

//        Button inboxButton = new Button("Inbox", inboxImage);
//        inboxButton.setUIID("SideCommand");
//        inboxButton.getAllStyles().setPaddingBottom(0);
//        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
//                new Label("18", "SideCommandNumber"));
//        inbox.setLeadComponent(inboxButton);
//        inbox.setUIID("SideCommand");
//        inboxButton.addActionListener(e -> new InboxForm().show());
//        getToolbar().addComponentToSideMenu(inbox);
       if(this.listu.getRoles().equals("Employeur")){
            getToolbar().addCommandToSideMenu("Offre", offreImage, e -> {
            new ShowOffreForm().show();
        });
       }else{
           getToolbar().addCommandToSideMenu("Offre", CandidatOffre, e -> {
            new ShowCandidatOffreForm().show();
        });
       }
     
        getToolbar().addCommandToSideMenu("Candidature", CandidatCandidature, e -> {
             new AfficherCandidatureEnt(res).show();
        });
        getToolbar().addCommandToSideMenu("Formation", FormationImage, e -> {
            new AfficherFormation(res).show();
        });
        getToolbar().addCommandToSideMenu("Noter Rendez Vous", calendarImage, e -> new CalendarForm(res).show());
        getToolbar().addCommandToSideMenu("Map", mapImage, e -> {
            new AboutUs().show();
        });

        getToolbar().addCommandToSideMenu("Profil", ProfilImage, e -> {
            new profil(idaffiche).show();
        });

        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("user-male.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label(listu.getName()+" "+listu.getPrenom(), "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label(listu.getRoles(), "SideCommandSmall"));
    }

    protected boolean isCurrentCandidatOffre() {
        return false;
    }

    protected boolean isCurrentProfil() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentFormation() {
        return false;
    }

    protected boolean isCurrentOffre() {
        return false;
    }
    protected boolean isCurrentMapImage() {
        return false;
    }
     protected boolean isCurrentCandidature() {
        return false;
    }
}
