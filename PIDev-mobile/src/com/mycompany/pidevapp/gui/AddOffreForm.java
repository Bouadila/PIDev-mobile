/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.entities.Contrat;
import com.mycompany.pidevapp.entities.Offre;
import com.mycompany.pidevapp.services.ServiceOffre;
import java.util.Date;

/**
 *
 * @author brahm
 */
public class AddOffreForm  extends BaseForm{
   
     public AddOffreForm() {
        //installSidemenu(theme);
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        getToolbar().setTitleComponent(
                      FlowLayout.encloseCenterMiddle(
                                new Label("Ajouter une nouvelle offre", "Title")
                        )
                );
        getToolbar().addMaterialCommandToLeftBar(
                "",
                FontImage.MATERIAL_ARROW_BACK,
                (ev) ->new ShowOffreForm().show());
        //setTitle("Ajouter une nouvelle offre");
        TextComponent post = new TextComponent().label("Post");
        TextComponent objectif = new TextComponent().label("Objectif");
        TextComponent competence = new TextComponent().label("Competence");
        String elements [] = {"--------------","Aéronautique Et Espace",
                    "Agriculture - Agroalimentaire",
                    "Artisanat",
                    "Audiovisuel, Cinéma",
                    "Audit, Comptabilité, Gestion",
                    "Automobile",
                    "Banque, Assurance",
                    "Bâtiment, Travaux Publics",
                    "Biologie, Chimie, Pharmacie",
                    "Commerce, Distribution",
                    "Communication",
                    "Création, Métiers art",
                    "Culture, Patrimoine",
                    "Défense, Sécurité, Armée",
                    "Documentation, Bibliothèque",
                    "Droit",
                    "Edition, Livre",
                    "Enseignement",
                    "Environnement",
                    "Ferroviaire",
                    "Foires, Salons Et Congrès",
                    "Fonction Publique",
                    "Hôtellerie, Restauration",
                    "Humanitaire",
                    "Immobilier",
                    "Industrie",
                    "Informatique, Télécoms, Web",
                    "Jeu Vidéo",
                    "Journalisme",
                    "Langues",
                    "Marketing, Publicité",
                    "Médical",
                    "Mode-Textile",
                    "Paramédical",
                    "Propreté Et Services Associés",
                    "Psychologie",
                    "Ressources Humaines",
                    "Sciences Humaines Et Sociales",
                    "Secrétariat",
                    "Social",
                    "Spectacle - Métiers De La Scène",
                    "Sport",
                    "Tourisme",
                    "Transport-Logistique"};
        Label lbDomaine = new Label("Domaine");
        ComboBox <String> cbnDomaine = new ComboBox();
        for(int i=0; i<elements.length;i++){
            cbnDomaine.addItem(elements[i]);
        }
        Label lbContrat = new Label("Contrat");
        ComboBox <String> cbnContrat = new ComboBox();
        cbnContrat.addItem("--------------");
        cbnContrat.addItem("Contrat à durée indéterminée");
        cbnContrat.addItem("Contrat à durée déterminée");
        TextComponent description = new TextComponent().label("Description").multiline(true);
        TextComponent salaire = new TextComponent().label("Salaire");
        //PickerComponent dateExpiration = PickerComponent.createDate(new Date()).label("Date");
        Picker dateExpiration = new Picker();
        TextComponent nbPlace = new TextComponent().label("Nombre Place");
        Label lbExpiration = new Label("Experience");
        TextComponent min = new TextComponent().label("Min");
        TextComponent max = new TextComponent().label("Max");
        Container c = new Container(new TableLayout(2, 2));
        c.add(min);
        c.add(max);
        Validator val = new Validator();
        val.addConstraint(post, new LengthConstraint(2));
        val.addConstraint(objectif, new LengthConstraint(2));
        val.addConstraint(competence, new LengthConstraint(2));
        val.addConstraint(description, new LengthConstraint(2));
        val.addConstraint(salaire, new NumericConstraint(true));
        val.addConstraint(nbPlace, new NumericConstraint(true));
        val.addConstraint(min, new NumericConstraint(true));
        val.addConstraint(max, new NumericConstraint(true));
        Button btn = new Button();
        btn.setText("save");
        btn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_NOTE_ADD, btn.getUnselectedStyle()));
        addAll(post,objectif,competence,lbContrat,cbnContrat,lbDomaine,cbnDomaine,description,salaire,dateExpiration,nbPlace,lbExpiration,c,btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((post.getText().length()==0)||(objectif.getText().length()==0)
                        ||(competence.getText().length()==0)||(description.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Offre o = new Offre();
                        o.setPost(post.getText());
                        o.setObjectif(objectif.getText());
                        o.setCompetences(competence.getText());
                        o.setDomaine(cbnDomaine.getSelectedItem());
                        o.setDescription(description.getText());
                        o.setSalaire(Integer.parseInt(salaire.getText()));
                        o.setDateExpiration(dateExpiration.getDate());
                        o.setNombrePlace(Integer.parseInt(nbPlace.getText()));
                        o.setExperienceMin(Integer.parseInt(min.getText()));
                        o.setExperienceMax(Integer.parseInt(max.getText()));
                        Contrat contrat = new Contrat();
                        if(cbnContrat.getSelectedItem().equals("Contrat à durée indéterminée")){
                            contrat.setId(1);
                        }else{
                            contrat.setId(2);
                        }
                        o.setContrat(contrat);
                        o.setDateDepot(new Date());
                        System.out.println(o.toString());
                        if( ServiceOffre.getInstance().addOffre(o))
                        { Dialog.show("Success","Connection accepted",new Command("OK"));
                          post.text("");
                          objectif.text("");
                          competence.text("");
                          description.text("");
                          salaire.text("");
                          nbPlace.text("");
                          min.text("");
                          max.text("");
                          new ShowOffreForm().show();
                        }
                        else
                        {Dialog.show("ERROR", "Server error", new Command("OK"));}
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                //System.out.println(so.getAllOffres().toString());
            }
        });
        
    }

//    @Override
//    protected boolean isCurrentInbox() {
//        return true;
//    }


}
