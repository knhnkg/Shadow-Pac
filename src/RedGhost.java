import bagel.Image;

/**
 * This RedGhost class is a subclass of Ghost class, which holds information that is specific to red ghost, on
 * top of the basic features of the ghost.
 * This class allows to control the movement behaviour of the red ghost and render it on the game screen.
 */
public class RedGhost extends Ghost{
    private final Image RED_GHOST = new Image("res/ghostRed.png");
    private final double STEP_SIZE = 1; // Amount of pixels moved at each frame, when frenzy mode if off.
    private final double FREEZY_STEP = 0.5; // Amount of pixels moved at each frame, when frenzy mode is on.

    /**
     * This constructor inherits the basic information for the ghost (e.g. position, movement behaviour and collision state).
     * @param xCoordinate This parameter corresponds to the x-value of ghost position
     * @param yCoordinate This parameter corresponds to the y-value of ghost position
     * @param EatenChecker This parameter indicates whether the ghost has been eaten by the player
     */
    public RedGhost(double xCoordinate, double yCoordinate, boolean EatenChecker) {
        super(xCoordinate, yCoordinate, EatenChecker);
    }

    /**
     * This method overrides from the superclass Ghost, to implement and render the movement behaviour that is
     * distinctive to the red ghost in level 1.
     * This method first checks for whether the frenzy mode has been activated, to decide the appropriate image
     * and step size to render on the screen.
     * Then this method controls the red ghost to move horizontally.
     * @param freezyModeChecker This parameter indicates whether frenzy mode has been activated
     */
    @Override
    public void Update(boolean freezyModeChecker) {
        if (freezyModeChecker) {
            this.setCurrGhost(getFreezyGhostImage());
            this.setCurrStep(FREEZY_STEP);
        }
        else if (!freezyModeChecker) {
            this.setCurrGhost(RED_GHOST);
            this.setCurrStep(STEP_SIZE);
        }

        getCurrGhost().drawFromTopLeft(this.getX(), this.getY());
        if (this.getDirection() == false) {
            this.setX(+getCurrStep());
        }
        else if (this.getDirection() == true) {
            this.setX(-getCurrStep());
        }
    }
}

