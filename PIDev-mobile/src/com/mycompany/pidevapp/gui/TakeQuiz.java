/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import static com.mycompany.ListSerie.MyApplication.theme;
import com.mycompany.pidevapp.entities.Question;
import com.mycompany.pidevapp.entities.Quiz;
import com.mycompany.pidevapp.entities.Reponse;
import com.mycompany.pidevapp.services.ServiceQuiz;
import java.util.ArrayList;

/**
 *
 * @author brahm
 */
public class TakeQuiz extends BaseForm {

    Container cnt = new Container(BoxLayout.y());
    TextField tf = new TextField();
    Quiz z;
    ButtonGroup btnGroup = new ButtonGroup();

    public TakeQuiz() {
        installSidemenu(theme);
        ServiceQuiz sq = new ServiceQuiz();
        z = sq.takeQuiz(50);

        tf.setText("0");
        tf.setVisible(false);
        fill();

        System.out.println(z.getQuestions());
        setLayout(new BorderLayout());
        ((BorderLayout) getLayout()).setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Passer  Quizz", "Title")
                )
        );
        Label lb = new Label(z.getNom_quiz());
        Button btn = new Button("Suivant");

        btn.addActionListener(event -> {

            fill();
            revalidate();
        });

        
        
        add(BorderLayout.CENTER, cnt);

        add(BorderLayout.NORTH, lb);
        add(BorderLayout.SOUTH, btn);

    }

    public void fill() {
          Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        int nb = Integer.parseInt(tf.getText());
        cnt.removeAll();
        if (nb < z.getQuestions().size()) {
                    
           
            Question question = z.getQuestions().get(nb);
            ArrayList<Reponse> reponses = question.getReponses();
              cnt.add(createForFont(largeBoldSystemFont, question.getContenu_ques()));
            for (int i = 0; i < reponses.size(); i++) {
                RadioButton rd = new RadioButton(reponses.get(i).getContenu_rep());
                btnGroup.add(rd);
                cnt.add(rd);
            }
            tf.setText(String.valueOf(nb + 1));
        }
        else
        {
            /////okhrej mel interface
        }

        
        
    }
    private Label createForFont(Font fnt, String s) {
  Label l = new Label(s);
  l.getUnselectedStyle().setFont(fnt);
  return l;
}
}
