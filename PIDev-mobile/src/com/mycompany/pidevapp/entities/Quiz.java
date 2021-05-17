/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.entities;

import java.util.ArrayList;

/**
 *
 * @author Bou3dila
 */
public class Quiz {
    private int id;
    private String nom_quiz;
    private int nomb_question;
    private ArrayList<Question> questions;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> qs) {
        this.questions = qs;
    }
    
    

    public Quiz(int id, String nom_quiz, int nomb_question) {
        this.id = id;
        this.nom_quiz = nom_quiz;
        this.nomb_question = nomb_question;
    }

    public Quiz(String nom_quiz, int nomb_question) {
        this.nom_quiz = nom_quiz;
        this.nomb_question = nomb_question;
    }

    public Quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_quiz() {
        return nom_quiz;
    }

    public void setNom_quiz(String nom_quiz) {
        this.nom_quiz = nom_quiz;
    }

    public int getNomb_question() {
        return nomb_question;
    }

    public void setNomb_question(int nomb_question) {
        this.nomb_question = nomb_question;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", nom_quiz=" + nom_quiz + ", nomb_question=" + nomb_question + '}';
    }
    
    
}
