import bagel.*;

/**
 * This Wall class holds the information about the position of the walls that function as the boundaries and obstacles
 * during the play and renders them on the game screen.
 */
public class Wall {
    private final Image WALL = new Image("res/wall.png");
    /**
     * This attribute provides the height value for the wall image.
     */
    public final double WALL_HEIGHT = WALL.getHeight();
    /**
     * This attribute provides the width value for the wall image.
     */
    public final double WALL_WIDTH = WALL.getWidth();
    private double x, y;

    /**
     * This constructor holds the information about the position of a wall.
     * @param xCoordinate This parameter indicates the x-value of the position of a wall
     * @param yCoordinate This parameter indicates the y-value of the position of a wall
     */
    public Wall(double xCoordinate, double yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
    }

    /**
     * This method renders a wall at a specific position.
     */
    public void Update() {
        WALL.drawFromTopLeft(x, y);
    }
}
