import bagel.Image;

/**
 * This GreenGhost class is a subclass of Ghost class, which holds information that is specific to green ghost, on
 * top of the basic features of the ghost.
 * This class allows to control the movement behaviour of the green ghost and render it on the game screen.
 */
public class GreenGhost extends Ghost{
    private final Image GREEN_GHOST = new Image("res/ghostGreen.png");
    private final double STEP_SIZE = 4; // Amount of pixels moved at each frame, when frenzy mode if off.
    private final double FREEZY_STEP = 3.5; // Amount of pixels moved at each frame, when frenzy mode is on.

    /**
     * This constructor inherits the basic information for the ghost (e.g. position, movement behaviour and collision state).
     * This also sets and holds the  direction that the green ghost will move towards to.
     * @param xCoordinate This parameter corresponds to the x-value of ghost position
     * @param yCoordinate This parameter corresponds to the y-value of ghost position
     * @param EatenChecker This parameter indicates whether the ghost has been eaten by the player
     */
    public GreenGhost(double xCoordinate, double yCoordinate, boolean EatenChecker) {
        super(xCoordinate, yCoordinate, EatenChecker);
        setDirectionNum(selectDirection());
    }

    /**
     * This method overrides from the superclass Ghost, to implement and render the movement behaviour that is
     * distinctive to the green ghost in level 1.
     * This method first checks for whether the frenzy mode has been activated, to decide the appropriate image
     * and step size to render on the screen.
     * Then this method controls the green ghost to move towards the chosen direction.
     * @param freezyModeChecker This parameter indicates whether frenzy mode has been activated
     */
    @Override
    public void Update(boolean freezyModeChecker) {
        if (freezyModeChecker) {
            this.setCurrGhost(getFreezyGhostImage());
            this.setCurrStep(FREEZY_STEP);
        }
        else if (!freezyModeChecker) {
            this.setCurrGhost(GREEN_GHOST);
            this.setCurrStep(STEP_SIZE);
        }

        getCurrGhost().drawFromTopLeft(this.getX(), this.getY());
        if (getDirectionNum() == 0 || getDirectionNum() == 1) {
            if (this.getDirection() == false) {
                this.setX(+getCurrStep());
            }
            else if (this.getDirection() == true) {
                this.setX(-getCurrStep());
            }
        }
        if (getDirectionNum() == 2 || getDirectionNum() == 3) {
            if (this.getDirection() == false) {
                this.setY(+getCurrStep());
            }
            else if (this.getDirection() == true) {
                this.setY(-getCurrStep());
            }
        }
    }
}

