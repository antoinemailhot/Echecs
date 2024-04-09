package View;
import Model.Jeu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreNomsJoueurs extends JFrame {
    // Déclaration des composants
    private JTextField player1TextField;
    private JTextField player2TextField;
    private JButton continueButton;

    public FenetreNomsJoueurs() {

        // Configuration de la fenêtre
        setTitle("Entrée des noms des joueurs");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5)); // GridLayout avec 3 lignes et 2 colonnes

        // Création des étiquettes et des champs de texte
        add(new JLabel(Jeu.joueurs[0].getNom()));
        player1TextField = new JTextField();
        add(player1TextField);

        add(new JLabel(Jeu.joueurs[1].getNom()));
        player2TextField = new JTextField();
        add(player2TextField);

        // Création et ajout du bouton de continuation
        continueButton = new JButton("Continuer");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mettre a jour les noms des joueurs
                Jeu.joueurs[0].setNom(player1TextField.getText());
                Jeu.joueurs[1].setNom(player2TextField.getText());
                
                // Fermer la fenêtre
                dispose();
            }
        });
        add(continueButton);
        
        // Rendre la fenêtre visible
        setVisible(true);

        
    }

  public JTextField getPlayer1TextField() {
    return this.player1TextField;
  }
  public JTextField getPlayer2TextField() {
    return this.player2TextField;
  }

  public JButton getContinueButton() {
    return this.continueButton;
  }
}
