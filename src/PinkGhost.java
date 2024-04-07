import bagel.Image;

/**
 * This PinkGhost class is a subclass of Ghost class, which holds information that is specific to pink ghost, on
 * top of the basic features of the ghost.
 * This class allows to control the movement behaviour of the pink ghost and render it on the game screen.
 */
public class PinkGhost extends Ghost{
    private final Image PINK_GHOST = new Image("res/ghostPink.png");
    private final double STEP_SIZE = 3; // Amount of pixels moved at each frame, when frenzy mode if off.
    private final double FREEZY_STEP = 2.5; // Amount of pixels moved at each frame, when frenzy mode is on.

    /**
     * This constructor inherits the basic information for the ghost (e.g. position, movement behaviour and collision state).
     * This also sets and holds the first direction that the pink ghost will move towards to.
     * @param xCoordinate This parameter corresponds to the x-value of ghost position
     * @param yCoordinate This parameter corresponds to the y-value of ghost position
     * @param EatenChecker This parameter indicates whether the ghost has been eaten by the player
     */
    public PinkGhost(double xCoordinate, double yCoordinate, boolean EatenChecker) {
        super(xCoordinate, yCoordinate, EatenChecker);
        setDirectionNum(selectDirection());
    }

    /**
     * This method overrides from the superclass Ghost, to implement and render the movement behaviour that is
     * distinctive to the pink ghost in level 1.
     * This method first checks for whether the frenzy mode has been activated, to decide the appropriate image
     * and step size to render on the screen.
     * Then this method controls the pink ghost to move towards the chosen direction.
     * @param freezyModeChecker This parameter indicates whether frenzy mode has been activated
     */
    @Override
    public void Update(boolean freezyModeChecker) {
        if (freezyModeChecker) {
            this.setCurrGhost(getFreezyGhostImage());
            this.setCurrStep(FREEZY_STEP);
        }
        else if (!freezyModeChecker) {
            this.setCurrGhost(PINK_GHOST);
            this.setCurrStep(STEP_SIZE);
        }

        getCurrGhost().drawFromTopLeft(this.getX(), this.getY());

        if (getDirectionNum() == 0) {
            this.setX(+getCurrStep());
        }
        else if (getDirectionNum() == 1) {
            this.setX(-getCurrStep());
        }
        else if (getDirectionNum() == 2) {
            this.setY(+getCurrStep());
        }
        else if (getDirectionNum() == 3) {
            this.setY(-getCurrStep());
        }
    }

    /**
     * This method overrides from the superclass Ghost, to provide the distinctive step size value that identifies
     * the type of the ghost.
     * @return double This returns the step size that is distinctive to the pink ghost
     */
    @Override
    public double ghostIdentify() {
        return STEP_SIZE;
    }
}

