/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.pidevapp.entities.Question;
import com.mycompany.pidevapp.entities.Quiz;
import com.mycompany.pidevapp.services.ServiceQuiz;
import java.util.ArrayList;

/**
 *
 * @author brahm
 */
public class QuizAddForm extends BaseForm {

    public QuizAddForm() {

        setLayout(new BorderLayout());
        ((BorderLayout) getLayout()).setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Ajouter  Quizz", "Title")
                )
        );

        ServiceQuiz sq = new ServiceQuiz();

        Container con = new Container();

        TextComponent titre = new TextComponent().label("Titre");
        con.add(titre);
        TextField tf = new TextField();
        TextField tf1 = new TextField();
        tf.setVisible(false);
        tf1.setVisible(false);
        tf.setText("2");
        tf1.setText("0");
        Button btn = new Button();
        Button btnFinish = new Button("Terminer");
        btn.setText("Suivant");
        int x = 0;
        Container btnCon = new Container(BoxLayout.xCenter());
        Container ct1 = new Container(new TableLayout(1, 2));
        TableLayout tl = new TableLayout(3, 3);
        Container ct = new Container(new TableLayout(4, 3));
        Container c = new Container(tl);

        TextComponent q1 = new TextComponent().label("Question 1");
        TextComponent q2 = new TextComponent().label("Question 2");
        TextComponent q3 = new TextComponent().label("Question 3");
        TextComponent q4 = new TextComponent().label("Question 4");
        RadioButton r1 = new RadioButton();
        RadioButton r2 = new RadioButton();
        RadioButton r3 = new RadioButton();
        RadioButton r4 = new RadioButton();
        ButtonGroup btnGroupe = new ButtonGroup(r1, r2, r3, r4);
        int nbr = 2;
        TextComponent text = new TextComponent().label("Contenu");

        Button btn2 = new Button("-");
        Button btn1 = new Button("-");

        btnFinish.addActionListener(e -> {

            Question ques = new Question();
            ques.setContenu_ques(text.getText());
            text.text("");
            ArrayList<String> list = new ArrayList();
            list.add(q1.getText());
            q1.text("");
            list.add(q2.getText());
            q2.text("");
            if (nbr > 2) {
                list.add(q3.getText());
                q3.text("");
            }
            if (nbr > 3) {
                list.add(q4.getText());
                q4.text("");

            }
            int vrai = 1;
            r1.setSelected(false);
            if (r2.isSelected()) {
                vrai = 2;
                r2.setSelected(false);
            }
            if (r3.isSelected()) {
                vrai = 3;
                r3.setSelected(false);
            }
            if (r4.isSelected()) {
                vrai = 4;
                r4.setSelected(false);
            }
            q3.text("");
            q4.text("");

            sq.addQuestion(ques, list, vrai);
            ////okhrejjjjj
        });

        btn1.addActionListener(e -> {
            int nombre = Integer.parseInt(tf.getText());
            if (nombre == 3) {
                q3.setVisible(false);
                r3.setVisible(false);
                btn1.setVisible(false);
                q3.text("");
            }
            if (nombre == 4) {
                q4.setVisible(false);
                r4.setVisible(false);
                btn2.setVisible(false);
                q4.text("");

            }

            tf.setText(String.valueOf(nombre - 1));
            revalidate();

        });
        btn2.addActionListener(e -> {
            int nombre = Integer.parseInt(tf.getText());
            if (nombre == 3) {
                q3.setVisible(false);
                r3.setVisible(false);
                btn1.setVisible(false);
                q3.text("");
            }
            if (nombre == 4) {
                q4.setVisible(false);
                r4.setVisible(false);
                btn2.setVisible(false);
                q4.text("");

            }

            tf.setText(String.valueOf(nombre - 1));
            revalidate();

        });

        Button plus = new Button("+");

        plus.addActionListener(e -> {
            int nombre = Integer.parseInt(tf.getText());
            if (nombre > 4) {

            } else {
                tf.setText(String.valueOf(nombre + 1));
                nombre++;
                if (nombre == 3) {
                    q3.setVisible(true);
                    r3.setVisible(true);
                    btn1.setVisible(true);
                }
                if (nombre == 4) {
                    q4.setVisible(true);
                    r4.setVisible(true);
                    btn2.setVisible(true);
                }
                revalidate();
            }

        });

        ct
                .add(tl.createConstraint().widthPercentage(10), r1)
                .add(tl.createConstraint().widthPercentage(80), q1)
                .add(tl.createConstraint().widthPercentage(10), new Label())
                .add(tl.createConstraint().widthPercentage(10), r2)
                .add(tl.createConstraint().widthPercentage(80), q2)
                .add(tl.createConstraint().widthPercentage(10), new Label())
                .add(tl.createConstraint().widthPercentage(10), r3)
                .add(tl.createConstraint().widthPercentage(80), q3)
                .add(tl.createConstraint().widthPercentage(10), btn1)
                .add(tl.createConstraint().widthPercentage(10), r4)
                .add(tl.createConstraint().widthPercentage(80), q4)
                .add(tl.createConstraint().widthPercentage(10), btn2);
        q3.setVisible(false);
        r3.setVisible(false);
        btn1.setVisible(false);
        q4.setVisible(false);
        r4.setVisible(false);
        btn2.setVisible(false);

        System.out.println(btnCon.getComponentCount());
        btn.addActionListener(event -> {

            int test = Integer.parseInt(tf1.getText());
            if (test == 0) {
                Quiz q = new Quiz();
                q.setNom_quiz(titre.getText());
                System.out.println(sq.addQuiz(q));
                con.removeComponent(titre);
                ct1.addAll(text, plus);
                con.add(ct1);
                con.add(ct);
                btnCon.add(btnFinish);
                revalidate();
                tf1.setText(String.valueOf(test + 1));

            } else {
                int nombre = Integer.parseInt(tf.getText());
                Question ques = new Question();
                ques.setContenu_ques(text.getText());
                text.text("");
                ArrayList<String> list = new ArrayList();
                list.add(q1.getText());
                q1.text("");
                list.add(q2.getText());
                q2.text("");
                if (nombre > 2) {
                    list.add(q3.getText());
                    q3.text("");
                }
                if (nombre > 3) {
                    list.add(q4.getText());
                    q4.text("");

                }
                int vrai = 1;
                r1.setSelected(false);
                if (r2.isSelected()) {
                    vrai = 2;
                    r2.setSelected(false);
                }
                if (r3.isSelected()) {
                    vrai = 3;
                    r3.setSelected(false);
                }
                if (r4.isSelected()) {
                    vrai = 4;
                    r4.setSelected(false);
                }
                q3.text("");
                q4.text("");
                q3.setVisible(false);
                r3.setVisible(false);
                btn1.setVisible(false);
                q4.setVisible(false);
                r4.setVisible(false);
                btn2.setVisible(false);

                sq.addQuestion(ques, list, vrai);
                tf.setText(String.valueOf(2));

            }

//                c.addAll(text, plus, new RadioButton(),new TextComponent().label("reponse"), new RadioButton(), new TextComponent().label("reponse"));
        });

        btnCon.add(btn);
        add(BorderLayout.CENTER, con);
        add(BorderLayout.SOUTH, btnCon);

    }

    public void increment(int x) {
        x = x + 1;
        System.out.println(x);
    }

    public void decrement(int x) {
        x = x - 1;
        System.out.println(x);
    }

}
