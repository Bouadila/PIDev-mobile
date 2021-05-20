/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import Entities.Reclamation;
import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.pidevapp.gui.ShowOffreForm;
import java.util.Date;
import services.ServiceReclamation;
import com.mycompany.pidevapp.gui.BaseForm;

/**
 *
 * @author Djap_ii
 */
public class AjouterReclamation extends BaseForm {

    ServiceReclamation cs = ServiceReclamation.getInstance();
    Form current;

    public AjouterReclamation(Resources res) {
        setLayout(new BorderLayout((BorderLayout.CENTER_BEHAVIOR_CENTER)));
        getToolbar().addMaterialCommandToLeftBar(
                "",
                FontImage.MATERIAL_ARROW_BACK,
                (ev) ->new AfficherReclamation(res).show());

        Label FormTitle = new Label("Ajouter Reclamation");
        FormTitle.getAllStyles().setAlignment(CENTER);

        TextField title = new TextField("", "Title", 15, TextField.EMAILADDR);
        addStringValue("title", title);

        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());

        TextField description = new TextField("", "Description", 15, TextField.EMAILADDR);
        addStringValue("description", description);

        Button addRec = new Button("Ajouter");
        addStringValue("", addRec);

        addRec.getAllStyles().setAlignment(CENTER);

        addRec.addActionListener(e -> {
            try {
                if (title.getText().length() == 0) {
                    Dialog.show("Alert", "Please fill all fields", new Command("OK"));
                } else {

                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    Reclamation r = new Reclamation(title.getText().toString(),
                            "Suggestion", format.format(new Date()), 
                            String.valueOf(description.getText()).toString(), 
                            "Suggestion", "pidevadmintest@gmail.com",1);

                    System.out.println("data formation ==" + r);

                    cs.addRec(r);

                    iDialog.dispose();

                    new AfficherReclamation(res).show();

                    refreshTheme();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Container labels = new Container(BoxLayout.yCenter()).addAll(title,
                 description, dateLabel, addRec);
        labels.setUIID("SignInForm");
        Container by = new Container(new GridLayout(2, 1));
        by.addAll(
                labels
        );

        add(BorderLayout.NORTH, FormTitle);
        add(BorderLayout.CENTER, by);

    }

    private void addStringValue(String s, Component v) {

//       add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        //add(BorderLayout.CENTER,createLineSeparator(0xeeeeee));
    }
}
