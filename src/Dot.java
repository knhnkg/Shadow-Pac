import bagel.*;

/**
 * This Dot class is a subclass of EatableEntity class, which holds information that is specific to a dot, on
 * top of the basic features of the consumable items.
 * This class allows to control the behaviour of a dot after its collision with the player and render it on the game screen.
 */
public class Dot extends EatableEntity{
    private final Image DOT = new Image("res/dot.png");
    /**
     * This attribute provides the height value for the dot image.
     */
    public final double DOT_HEIGHT = DOT.getHeight();
    /**
     * This attribute provides the width value for the dot image.
     */
    public final double DOT_WIDTH = DOT.getWidth();

    /**
     * This constructor inherits the basic information for the dot (e.g. position and collision state).
     * @param xCoordinate This parameter corresponds to the x-value of dot position
     * @param yCoordinate This parameter corresponds to the y-value of dot position
     * @param eatenChecker This parameter indicates whether the dot has been eaten by the player
     */
    public Dot(double xCoordinate, double yCoordinate, boolean eatenChecker) {
        super(xCoordinate, yCoordinate, eatenChecker);
    }

    /**
     * This method renders the dot on the screen at a specific position.
     */
    @Override
    public void Update() {
        DOT.drawFromTopLeft(this.getEntityX(), this.getEntityY());
    }
}

