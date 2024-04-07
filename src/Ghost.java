import bagel.*;

import java.util.Random;

/**
 * This Ghost class holds basic information for each ghost.
 * This class also function as a parent class for subclasses corresponding to different types of
 * ghosts in level 1.
 */
public class Ghost {
    private final Image GHOST = new Image("res/ghostRed.png");
    private final Image FREEZY_GHOST = new Image("res/ghostFrenzy.png");
    /**
     * This attribute provides the height value for the ghost image.
     */
    public final double GHOST_HEIGHT = GHOST.getHeight();
    /**
     * This attribute provides the width value for the ghost image.
     */
    public final double GHOST_WIDTH = GHOST.getWidth();
    private double x;
    private double y;
    private double initialX;
    private double initialY;
    private int directionNum;
    private Image currGhost;
    private double currStep;
    private Random random = new Random();
    private int num;
    private boolean direction = false;
    private boolean wasEaten;

    /**
     * This constructor hold basic information for each ghost's position, movement behaviour and collision state.
     * @param xCoordinate This parameter corresponds to the x-value of ghost position
     * @param yCoordinate This parameter corresponds to the y-value of ghost position
     * @param EatenChecker This parameter indicates whether the ghost has been eaten by the player
     */
    public Ghost(double xCoordinate, double yCoordinate, boolean EatenChecker) {
        x = xCoordinate;
        y = yCoordinate;
        initialX = xCoordinate;
        initialY = yCoordinate;
        directionNum = 0;
        wasEaten = EatenChecker;
        currGhost = GHOST;
        currStep = 0;
    }

    /**
     * This method updates the status of the ghost and renders it on the game screen.
     * @param freezyModeChecker This parameter indicates whether frenzy mode has been activated
     */
    public void Update(boolean freezyModeChecker) {
        GHOST.drawFromTopLeft(x, y);
    }

    /**
     * This method is used to pick a random number that will select a movement direction for some ghosts.
     * @return int This returns the randomly selected value that decides movement direction for some ghosts.
     */
    public int selectDirection() {
        num = random.nextInt(4);
        return num;
    }

    /**
     * This method is used to identify the type of the ghost by their step size in some situations.
     * @return double This returns the step size to identify the ghost, when required.
     */
    public double ghostIdentify() {
        return 0;
    }

    /**
     * This method moves the ghost back to its initial position.
     * @param initialX This parameter corresponds to the x-value of the ghost's starting position
     * @param initialY This parameter corresponds to the y-value of the ghost's starting position
     */
    public void resetPosition(double initialX, double initialY) {
        x = initialX;
        y = initialY;
    }

    /**
     * This method moves the ghost back to its very recent position.
     * @param prevX This parameter corresponds to the x-value of the ghost's very recent position
     * @param prevY This parameter corresponds to the y-value of the ghost's very recent position
     */
    public void bounceBackPosition(double prevX, double prevY) {
        x = prevX;
        y = prevY;
    }

    /**
     * This method provides the image of frenzy mode ghost.
     * @return Image This returns the image of frenzy mode ghost
     */
    public Image getFreezyGhostImage() { return FREEZY_GHOST; }

    /**
     * This method provides the x-value of the ghost's current position.
     * @return double This returns the x-value of the ghost's position
     */
    public double getX() { return x; }

    /**
     * This method provides the y-value of the ghost's current position.
     * @return double This returns the y-value of the ghost's position
     */
    public double getY() { return y; }

    /**
     * This method sets the new x-value of the ghost's position after moving by certain amount of pixels horizontally.
     * @param step This parameter indicates the amount of pixels that the ghost need to move horizontally
     */
    public void setX(double step) { this.x = x + step; }

    /**
     * This method sets the new y-value of the ghost's position after moving by certain amount of pixels vertically.
     * @param step This parameter indicates the amount of pixels that the ghost need to move vertically
     */
    public void setY(double step) { this.y = y + step; }

    /**
     * This method provides the x-value of the ghost's starting position.
     * @return double This returns the x-value of the ghost's starting position
     */
    public double getInitialX() { return initialX; }

    /**
     * This method provides the y-value of the ghost's starting position.
     * @return double This returns the y-value of the ghost's starting position
     */
    public double getInitialY() { return initialY; }

    /**
     * This method provides the boolean that indicates the direction that the ghost is moving towards.
     * @return boolean This returns the value that corresponds to movement direction of the ghost
     */
    public boolean getDirection() { return direction; }

    /**
     * This method sets the new movement direction for the ghost.
     * @param directionSetter This parameter indicates the required movement direction of the ghost
     */
    public void setDirection(boolean directionSetter) { this.direction = directionSetter; }

    /**
     * This method indicates whether the ghost has been eaten by the player.
     * @return boolean This returns the value that indicates player-ghost collision state
     */
    public boolean getWasEaten() { return wasEaten; }

    /**
     * This method sets the player-ghost collision state.
     * @param wasEatenSetter This parameter corresponds to the player-ghost collision state
     */
    public void setWasEaten(boolean wasEatenSetter) { this.wasEaten = wasEatenSetter; }

    /**
     * This method sets an image to render, which corresponds to the current status of the ghost.
     * @param currGhostSetter This parameter provides the image at current ghost status
     */
    public void setCurrGhost(Image currGhostSetter) { this.currGhost = currGhostSetter; }

    /**
     * This method sets the step size for the ghost, based on its current status.
     * @param currStepSetter This parameter provides the step size for the ghost at current status
     */
    public void setCurrStep(double currStepSetter) {this.currStep = currStepSetter; }

    /**
     * This method provides the step size of the ghost at current status.
     * @return double This returns the current step size of the ghost
     */
    public double getCurrStep() { return currStep; }

    /**
     * This method provides the image of the ghost at current status.
     * @return Image This returns the current image of the ghost
     */
    public Image getCurrGhost() { return currGhost; }

    /**
     * This method sets the new number that indicates the direction that the ghost moves towards to.
     * @param directionNumSetter This parameter corresponds to the movement direction of the ghost
     */
    public void setDirectionNum(int directionNumSetter) { this.directionNum = directionNumSetter; }

    /**
     * This method provides the value that indicates the direction that the ghost moves towards to.
     * @return int This returns the value that corresponds to the movement direction of the ghost
     */
    public int getDirectionNum() { return directionNum; }
}

