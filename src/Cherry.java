import bagel.*;

/**
 * This Cherry class is a subclass of EatableEntity class, which holds information that is specific to a cherry, on
 * top of the basic features of the consumable items.
 * This class allows to control the behaviour of a cherry after its collision with the player and render it on the game screen.
 */
public class Cherry extends EatableEntity {
    private final Image CHERRY = new Image("res/cherry.png");
    /**
     * This attribute provides the height value for the cherry image.
     */
    public final double DOT_HEIGHT = CHERRY.getHeight();
    /**
     * This attribute provides the width value for the cherry image.
     */
    public final double DOT_WIDTH = CHERRY.getWidth();

    /**
     * This constructor inherits the basic information for the cherry (e.g. position and collision state).
     * @param xCoordinate This parameter corresponds to the x-value of cherry position
     * @param yCoordinate This parameter corresponds to the y-value of cherry position
     * @param eatenChecker This parameter indicates whether the cherry has been eaten by the player
     */
    public Cherry(double xCoordinate, double yCoordinate, boolean eatenChecker) {
        super(xCoordinate, yCoordinate, eatenChecker);
    }

    /**
     * This method renders the cherry on the screen at a specific position.
     */
    @Override
    public void Update() {
        CHERRY.drawFromTopLeft(this.getEntityX(), this.getEntityY());
    }
}
