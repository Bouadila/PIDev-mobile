/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import Entities.Reclamation;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import services.ServiceReclamation;

/**
 *
 * @author Djap_ii
 */
public class ModifierReclamation extends BaseForm {

    ServiceReclamation cs = ServiceReclamation.getInstance();
    Form current;

    public ModifierReclamation(Resources res, Reclamation r) {

        setLayout(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));
        super.installSidemenu(res);

        Label FormTitle = new Label("Modifier Reclamation");
        FormTitle.getAllStyles().setAlignment(CENTER);

        TextField title = new TextField(r.getTitle(), "Title", 15, TextField.ANY);
        title.setUIID("SignInForm");

        TextField description = new TextField(r.getDescRec(), "Description", 15, TextField.ANY);
        description.setUIID("SignInForm");

        title.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);

        Button ModifierRec = new Button("Enregistrer");
        ModifierRec.addActionListener(e -> {

            r.setTitle(title.getText());
            r.setDescRec(description.getText());

            if (ServiceReclamation.getInstance().updateRec(r)) {
                new AfficherReclamation(res).show();
            }

        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l -> {
            new AfficherReclamation(res).show();
        });

        Container labels = new Container(BoxLayout.yCenter()).addAll(title, description, ModifierRec, btnAnnuler);
        labels.setUIID("SignInForm");
        Container by = new Container(new GridLayout(2, 1));
        by.addAll(
                labels
        );
        add(BorderLayout.NORTH, FormTitle);
        add(BorderLayout.CENTER, by);

    }
}
