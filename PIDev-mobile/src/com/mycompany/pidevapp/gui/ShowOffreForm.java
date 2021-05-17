/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.gui;

import com.codename1.io.Log;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.entities.Offre;
import com.mycompany.pidevapp.services.ServiceOffre;
import java.util.ArrayList;

/**
 *
 * @author brahm
 */
public class ShowOffreForm extends BaseForm{
     @Override
    protected boolean isCurrentOffre() {
        return true;
    }

    public ShowOffreForm() {
        installSidemenu(theme);
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        getToolbar().setTitleComponent(
                      FlowLayout.encloseCenterMiddle(
                                new Label("Les offres d'emploi disponibles", "Title")
                        )
                );
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage addIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        getToolbar().addCommandToRightBar("", addIcon, (e) -> new AddOffreForm().show());
        ArrayList<Offre> list = ServiceOffre.getInstance().getAllOffres();
        System.out.println(list.toString());
    }
    
    
}
