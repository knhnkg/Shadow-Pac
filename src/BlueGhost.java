import bagel.Image;

/**
 * This BlueGhost class is a subclass of Ghost class, which holds information that is specific to blue ghost, on
 * top of the basic features of the ghost.
 * This class allows to control the movement behaviour of the blue ghost and render it on the game screen.
 */
public class BlueGhost extends Ghost{
    private final Image BLUE_GHOST = new Image("res/ghostBlue.png");
    private final double STEP_SIZE = 2; // Amount of pixels moved at each frame, when frenzy mode if off.
    private final double FREEZY_STEP = 1.5; // Amount of pixels moved at each frame, when frenzy mode is on.

    /**
     * This constructor inherits the basic information for the ghost (e.g. position, movement behaviour and collision state).
     * @param xCoordinate This parameter corresponds to the x-value of ghost position
     * @param yCoordinate This parameter corresponds to the y-value of ghost position
     * @param EatenChecker This parameter indicates whether the ghost has been eaten by the player
     */
    public BlueGhost(double xCoordinate, double yCoordinate, boolean EatenChecker) {
        super(xCoordinate, yCoordinate, EatenChecker);
    }

    /**
     * This method overrides from the superclass Ghost, to implement and render the movement behaviour that is
     * distinctive to the blue ghost in level 1.
     * This method first checks for whether the frenzy mode has been activated, to decide the appropriate image
     * and step size to render on the screen.
     * Then this method controls the blue ghost to move vertically.
     * @param freezyModeChecker This parameter indicates whether frenzy mode has been activated
     */
    @Override
    public void Update(boolean freezyModeChecker) {
        if (freezyModeChecker) {
            this.setCurrGhost(getFreezyGhostImage());
            this.setCurrStep(FREEZY_STEP);
        }
        else if (!freezyModeChecker) {
            this.setCurrGhost(BLUE_GHOST);
            this.setCurrStep(STEP_SIZE);
        }

        getCurrGhost().drawFromTopLeft(this.getX(), this.getY());
        if (this.getDirection() == false) {
            this.setY(+getCurrStep());
        }
        else if (this.getDirection() == true) {
            this.setY(-getCurrStep());
        }
    }
}

