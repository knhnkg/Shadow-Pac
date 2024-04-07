import bagel.Image;

/**
 * This Pellet class is a subclass of EatableEntity class, which holds information that is specific to a pellet, on
 * top of the basic features of the consumable items.
 * This class allows to control the behaviour of a pellet after its collision with the player and render it on the game screen.
 */
public class Pellet extends EatableEntity{
    private final Image PELLET = new Image("res/pellet.png");
    /**
     * This attribute provides the height value for the pellet image.
     */
    public final double DOT_HEIGHT = PELLET.getHeight();
    /**
     * This attribute provides the width value for the pellet image.
     */
    public final double DOT_WIDTH = PELLET.getWidth();

    /**
     * This constructor inherits the basic information for the pellet (e.g. position and collision state).
     * @param xCoordinate This parameter corresponds to the x-value of pellet position
     * @param yCoordinate This parameter corresponds to the y-value of pellet position
     * @param eatenChecker This parameter indicates whether the pellet has been eaten by the player
     */
    public Pellet(double xCoordinate, double yCoordinate, boolean eatenChecker) {
        super(xCoordinate, yCoordinate, eatenChecker);
    }

    /**
     * This method renders the pellet on the screen at a specific position.
     */
    @Override
    public void Update() {
        PELLET.drawFromTopLeft(this.getEntityX(), this.getEntityY());
    }
}

