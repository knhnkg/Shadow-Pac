import bagel.*;

/**
 * This Player class holds the basic functioning information of a single player of the game.
 * This controls the opening of the player's mouth, rotation and movement behaviour of the player, before rendering
 * it on the game screen.
 */
public class Player {
    private final Image PLAYER = new Image("res/pac.png");
    private final Image OPEN_PLAYER = new Image("res/pacOpen.png");
    /**
     * This attribute provides the height value for the pac image.
     */
    public final double PLAYER_HEIGHT = PLAYER.getHeight();
    /**
     * This attribute provides the width value for the pac image.
     */
    public final double PLAYER_WIDTH = PLAYER.getWidth();
    private final double PLAYER_INITIAL_X = 474;  // x-coordinate of the player's starting position
    private final double PLAYER_INITIAL_Y = 662;  // y-coordinate of the player's starting position
    private final double STEP_SIZE = 3;  // Amount of pixels moved at each frame, when frenzy mode if off.
    private final double FREEZY_STEP = 4; // Amount of pixels moved at each frame, when frenzy mode is on.
    private double currStep;
    private double x, y;
    private int frameCounter = 0;
    private double rotate90Deg = 1.5708;
    private double rotate180Deg = 3.1416;
    private double rotate270Deg = 4.7124;
    private boolean isOpen = false;

    // The following two boolean variables are used together to keep track of four movement types.
    // Up, Down, Left and Right
    private boolean movingUp = false;
    private boolean movingLeft = false;

    /**
     * This constructor holds the information about the position of the player.
     * @param xCoordinate This parameter corresponds to the x-value of the player's current position
     * @param yCoordinate This parameter corresponds to the y-value of the player's current position
     */
    public Player(double xCoordinate, double yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
    }

    private DrawOptions drawTool = new DrawOptions();

    /**
     * This method is used to perform a progressive state update for the player entity.
     * This method first checks for whether the frenzy mode has been activated, to decide the appropriate image
     * and step size to render on the screen.
     * Then this method controls the drawing of open and closed images of the player.
     * This also keeps track of the movement and corresponding rotations of the player.
     * @param input This parameter corresponds to the keyboard key pressed by the player
     * @param freezyModeChecker This parameter corresponds to the indicator for frenzy mode status
     */
    public void Update(Input input, boolean freezyModeChecker) {

        if (freezyModeChecker) {
            currStep = FREEZY_STEP;
        }
        else if (!freezyModeChecker) {
            currStep = STEP_SIZE;
        }

        // The player open and close mouth every 15 frame.
        frameCounter += 1;

        if (frameCounter == 15) {
            if (!isOpen) {
                isOpen = true;
            }
            else if (isOpen) {
                isOpen = false;
            }
            frameCounter = 0;
        }

        // Two boolean variables are implemented to uniquely identify the movement type.
        // Each of the four movement types has unique combination of the two boolean variables.
        if (input.isDown(Keys.LEFT)) {
            movingLeft = true;
            movingUp = true;
        }
        if (input.isDown(Keys.RIGHT)) {
            movingLeft = false;
            movingUp = false;
        }
        if (input.isDown(Keys.UP)) {
            movingLeft = false;
            movingUp = true;
        }
        if (input.isDown(Keys.DOWN)) {
            movingLeft = true;
            movingUp = false;
        }

        // The player image is drawn with corresponding mouth and rotation, based on the mouth state (opened or closed)
        // of the player and the movement detected (up, down, right or left).
        if (!isOpen) {
            if (!movingLeft && !movingUp) {
                PLAYER.drawFromTopLeft(x, y);
            }
            if (!movingUp && movingLeft) {
                PLAYER.drawFromTopLeft(x, y, drawTool.setRotation(rotate90Deg));
            }
            if (movingLeft && movingUp) {
                PLAYER.drawFromTopLeft(x, y, drawTool.setRotation(rotate180Deg));
            }
            if (movingUp && !movingLeft) {
                PLAYER.drawFromTopLeft(x, y, drawTool.setRotation(rotate270Deg));
            }
        }
        else if (isOpen) {
            if (!movingLeft && !movingUp) {
                OPEN_PLAYER.drawFromTopLeft(x, y);
            }
            if (!movingUp && movingLeft) {
                OPEN_PLAYER.drawFromTopLeft(x, y, drawTool.setRotation(rotate90Deg));
            }
            if (movingLeft && movingUp) {
                OPEN_PLAYER.drawFromTopLeft(x, y, drawTool.setRotation(rotate180Deg));
            }
            if (movingUp && !movingLeft) {
                OPEN_PLAYER.drawFromTopLeft(x, y, drawTool.setRotation(rotate270Deg));
            }
        }

        // Based on the arrow key pressed, the player moves towards the corresponding direction.
        if (input.isDown(Keys.LEFT)) {
            x -= currStep;
        }
        if (input.isDown(Keys.RIGHT)) {
            x += currStep;
        }
        if (input.isDown(Keys.UP)) {
            y -= currStep;
        }
        if (input.isDown(Keys.DOWN)) {
            y += currStep;
        }
    }

    /**
     * This method moves the player back to its initial position.
     */
    public void resetPosition() {
        x = PLAYER_INITIAL_X;
        y = PLAYER_INITIAL_Y;
    }

    /**
     * This method moves the player back to its very recent position.
     * @param prevX This parameter corresponds to the x-value of the player's very recent position
     * @param prevY This parameter corresponds to the y-value of the player's very recent position
     */
    public void bounceBackPosition(double prevX, double prevY) {
        x = prevX;
        y = prevY;
    }

    /**
     * This method provides the x-value of the player's current position.
     * @return double This returns the x-value of the player's position
     */
    public double getX() {
        return x;
    }

    /**
     * This method provides the y-value of the player's current position.
     * @return double This returns the y-value of the player's position
     */
    public double getY() {
        return y;
    }
}


