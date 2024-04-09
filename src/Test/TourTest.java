package Test;

import Model.Tour;
import Model.TypeCouleur;
import Model.TypePiece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TourTest {
    private Tour tour;
    private TypeCouleur.Couleur noir;
    private TypePiece.Piece typePiece;

    /*
     * Initialisation de l'environnement.
     */
    @Before
    public void miseEnPlaceEnvironnement() {
        this.noir = TypeCouleur.Couleur.Noir;
        this.typePiece = TypePiece.Piece.Tour;
        this.tour = new Tour(typePiece,noir);
    }

    /*
     * Test creation chevalier.
     */
    @Test
    public void testCreationTour() {
        assertEquals(typePiece, tour.getType());
    }

    @Test
    public void testGetToString() {
        assertEquals("Tour", tour.toString());
    }

    @Test
    public void testGetToStringComplet() {
        assertEquals("TourNoir", tour.toStringComplet());
    }

}
