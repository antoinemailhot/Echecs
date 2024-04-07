package View;

import Model.Jeu;
import Controller.JeuController;

public class MVCSwingEchec {

    public static void main(String[] args) {
        // Instance Model
        Jeu jeu = new Jeu();

        // Instance Views
        JeuView jeuVue = new JeuView();

        // Instance Controller  
        JeuController jeuController = new JeuController(jeu, jeuVue);
    }
    
}