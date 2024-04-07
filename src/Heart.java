import bagel.*;

/**
 * This Heart class holds information about the amount of lives left and renders the corresponding amount of the
 * hearts on the game screen.
 */
public class Heart {
    private final Image HEART = new Image("res/heart.png");
    private final double SPACE_SIZE = 30; // Horizontal space between each heart drawn on screen
    private double life;
    private double xCoordinate = 900;
    private double yCoordinate = 10;

    /**
     * This constructor holds the information about the amount of lives left.
     * @param lifeRemaining This parameter indicates the remaining number of lives left
     */
    public Heart(double lifeRemaining) {
        life = lifeRemaining;
    }

    /**
     * This method renders the hearts on the game screen, based on the remaining amount of lives.
     */
    public void Update() {
        for (int h=0; h<life; h++) {
            HEART.drawFromTopLeft(xCoordinate, yCoordinate);
            xCoordinate = xCoordinate + SPACE_SIZE;
        }
    }
}
