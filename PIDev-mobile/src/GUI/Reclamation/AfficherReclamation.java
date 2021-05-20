/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import Entities.Reclamation;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.gui.AddOffreForm;
import com.mycompany.pidevapp.gui.BaseForm;
import java.util.ArrayList;

import services.ServiceReclamation;

/**
 *
 * @author Djap_ii
 */
public class AfficherReclamation extends BaseForm {

       @Override
       protected boolean isCurrentReclamation() {
        return true;
        }
    public AfficherReclamation(Resources res) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        super.installSidemenu(res);
        getToolbar().setTitleComponent(
                      FlowLayout.encloseCenterMiddle(
                                new Label("Votre reclamation ", "Title")
                        )
                );
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage addIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        getToolbar().addCommandToRightBar("", addIcon, (e) -> new AjouterReclamation(theme).show());


        ///affichage
        ArrayList<Reclamation> list = ServiceReclamation.getInstance().getAllRecs();
        Container  cnt = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

        for (int i = 0; i < list.size(); i++) {
            
            Button delete = new Button("Supprimer");
            Button update = new Button("Modifier");
            TextArea title = new TextArea("Titre : " + list.get(i).getTitle());
            title.setUIID("Label");
            title.setEditable(false);
            TextArea description = new TextArea("Description : " + list.get(i).getDescRec());
            description.setUIID("Label");
            description.setEditable(false);
            TextArea date = new TextArea("Description : " + list.get(i).getDateRec());
            date.setUIID("Label");
            date.setEditable(false);

            TextArea status = new TextArea("status : " + list.get(i).getStatus());
            status.setUIID("Label");
            status.setEditable(false);

            int x = list.get(i).getId();

            delete.addActionListener(e -> {

                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer cette Reclamation?", "Annuler", "Oui")) {

                    dig.dispose();
                } else {
                    dig.dispose();

                    Reclamation r = new Reclamation();
                    r.setId(x);

                    if (ServiceReclamation.getInstance().deleteRec(r)) {

                        new AfficherReclamation(res).show();
                    }

                }

            });

            update.addActionListener(e -> {

                Reclamation r = new Reclamation();

                r.setTitle(title.getText());
                r.setDescRec(description.getText());

                r.setId(x);
                new ModifierReclamation(res, r).show();

            });

            cnt.addAll(title, description, status, date, BoxLayout.encloseX(new Label("      "),update, delete));


        }

        add(cnt);

    }
}
