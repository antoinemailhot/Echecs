package Model;

public class Position {
    // Les attributs:
    private int x;
    private int y;

    // Le constructeur par défaut.
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Ajuste la position x.
     * @param x Prends la position x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Ajuste la position y.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Obtient la position du x.
     * @return Retourne la position du x.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Obtient la position du y.
     * @return Retourne la position du y.
     */
    public int getY() {
        return y;
    }
}
