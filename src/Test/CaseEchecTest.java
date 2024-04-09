package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import Model.Pion;
import Model.Piece;
import Model.TypeDirection;
import org.junit.Before;
import org.junit.Test;

import Model.CaseEchec;
import Model.TypeCouleur;

public class CaseEchecTest {
    private TypeCouleur.Couleur noir;
    private TypeCouleur.Couleur blanc;
    private CaseEchec caseEchec;
    private CaseEchec caseEchec2;


    @Before
    public void miseEnPlaceEnvironnement() {
        this.noir = TypeCouleur.Couleur.Noir;
        this.blanc = TypeCouleur.Couleur.Blanc;
        this.caseEchec = new CaseEchec(noir, 0,0);
        this.caseEchec2 = new CaseEchec(blanc, 1,1);
    }

    /*
    * Création d'une case d'échec.
    */
    @Test
    public void testCaseEchecCreation() {
        assertEquals(noir, this.caseEchec.getCouleur());
    }

    /*
    * Changement de la couleur.
    */
    @Test
    public void testCaseEchecMiseAJourCouleur() {
        this.caseEchec.setCouleur(blanc);
        assertEquals(blanc, this.caseEchec.getCouleur());
        this.caseEchec.setCouleur(null);
        assertEquals(blanc, this.caseEchec.getCouleur());
    }

    @Test
    public void testCaseEchecDeplacement() {
        caseEchec.placerPiece(new Pion(noir, TypeDirection.Direction.Direction_Origin_Noir));
        Piece pieceTest = caseEchec.getPiece();
        caseEchec.deplacerPiece(caseEchec2);
        assertEquals(pieceTest, this.caseEchec2.getPiece());
        assertNull(caseEchec.getPiece());
    }

}
