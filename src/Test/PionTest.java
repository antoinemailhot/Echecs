package Test;

import Model.Pion;
import Model.TypeCouleur;
import Model.TypeDirection;
import Model.TypePiece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PionTest {
    private Pion pion;
    private TypeCouleur.Couleur noir;
    private TypePiece.Piece typePiece;
    private TypeDirection.Direction direction;

    /*
     * Initialisation de l'environnement.
     */
    @Before
    public void miseEnPlaceEnvironnement() {
        this.noir = TypeCouleur.Couleur.Noir;
        this.typePiece = TypePiece.Piece.Pion;
        this.direction = TypeDirection.Direction.Direction_Origin_Noir;
        this.pion = new Pion(noir, direction);
    }

    /*
     * Test creation chevalier.
     */
    @Test
    public void testCreationPion() {
        assertEquals(typePiece, pion.getType());
    }

    @Test
    public void testGetToString() {
        assertEquals("Pion", pion.toString());
    }

    @Test
    public void testGetToStringComplet() {
        assertEquals("PionNoir", pion.toStringComplet());
    }

}
