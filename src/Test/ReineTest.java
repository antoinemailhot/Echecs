package Test;

import Model.Reine;
import Model.TypeCouleur;
import Model.TypePiece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReineTest {
    private Reine reine;
    private TypeCouleur.Couleur noir;
    private TypePiece.Piece typePiece;

    /*
     * Initialisation de l'environnement.
     */
    @Before
    public void miseEnPlaceEnvironnement() {
        this.noir = TypeCouleur.Couleur.Noir;
        this.typePiece = TypePiece.Piece.Reine;
        this.reine = new Reine(typePiece,noir);
    }

    /*
     * Test creation chevalier.
     */
    @Test
    public void testCreationReine() {
        assertEquals(typePiece, reine.getType());
    }

    @Test
    public void testGetToString() {
        assertEquals("Reine", reine.toString());
    }

    @Test
    public void testGetToStringComplet() {
        assertEquals("ReineNoir", reine.toStringComplet());
    }

}
