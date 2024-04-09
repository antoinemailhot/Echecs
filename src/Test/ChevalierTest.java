package Test;

import Model.CaseEchec;
import Model.Chevalier;
import Model.TypeCouleur;
import Model.TypePiece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChevalierTest {
    private Chevalier chevalier;
    private TypeCouleur.Couleur noir;
    private TypePiece.Piece typePiece;

    /*
    * Initialisation de l'environnement.
    */
    @Before
    public void miseEnPlaceEnvironnement() {
        this.noir = TypeCouleur.Couleur.Noir;
        this.typePiece = TypePiece.Piece.Chevalier;
        this.chevalier = new Chevalier(typePiece,noir);
    }

    /*
    * Test creation chevalier.
    */
    @Test
    public void testCreationChevalier() {
        assertEquals(typePiece, chevalier.getType());
    }

    @Test
    public void testGetToString() {
        assertEquals("Chevalier", chevalier.toString());
    }

    @Test
    public void testGetToStringComplet() {
        assertEquals("ChevalierNoir", chevalier.toStringComplet());
    }

}
