package Test;

import Model.Fou;
import Model.TypeCouleur;
import Model.TypePiece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FouTest {
    private Fou fou;
    private TypeCouleur.Couleur noir;
    private TypePiece.Piece typePiece;

    /*
     * Initialisation de l'environnement.
     */
    @Before
    public void miseEnPlaceEnvironnement() {
        this.noir = TypeCouleur.Couleur.Noir;
        this.typePiece = TypePiece.Piece.Fou;
        this.fou = new Fou(typePiece,noir);
    }

    /*
     * Test creation chevalier.
     */
    @Test
    public void testCreationFou() {
        assertEquals(typePiece, fou.getType());
    }

    @Test
    public void testGetToString() {
        assertEquals("Fou", fou.toString());
    }

    @Test
    public void testGetToStringComplet() {
        assertEquals("FouNoir", fou.toStringComplet());
    }

}
