package Test;

import Model.Roi;
import Model.TypeCouleur;
import Model.TypePiece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoiTest {
    private Roi roi;
    private TypeCouleur.Couleur noir;
    private TypePiece.Piece typePiece;

    /*
     * Initialisation de l'environnement.
     */
    @Before
    public void miseEnPlaceEnvironnement() {
        this.noir = TypeCouleur.Couleur.Noir;
        this.typePiece = TypePiece.Piece.Roi;
        this.roi = new Roi(typePiece,noir);
    }

    /*
     * Test creation chevalier.
     */
    @Test
    public void testCreationRoi() {
        assertEquals(typePiece, roi.getType());
    }

    @Test
    public void testGetToString() {
        assertEquals("Roi", roi.toString());
    }

    @Test
    public void testGetToStringComplet() {
        assertEquals("RoiNoir", roi.toStringComplet());
    }

}
