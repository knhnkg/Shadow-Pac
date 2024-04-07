import bagel.*;

/**
 * This EatableEntity class holds the basic information for and function as the superclass for each item that the
 * player may consume during the game.
 * This also holds the frame for methods that controls and renders the items on the game screen.
 */
public class EatableEntity {
    private double x, y;
    private boolean wasEaten;

    /**
     * This constructor holds the basic information for each consumable item's position and collision state.
     * @param xCoordinate This parameter corresponds to the x-value of item position
     * @param yCoordinate This parameter corresponds to the y-value of item position
     * @param eatenChecker This parameter indicates whether the item has been eaten by the player
     */
    public EatableEntity(double xCoordinate, double yCoordinate, boolean eatenChecker) {
        x = xCoordinate;
        y = yCoordinate;
        wasEaten = eatenChecker;
    }

    /**
     * This method updates the status of the item and renders it on the game screen.
     * This method is specific to each consumable item with their specific behaviour.
     */
    public void Update() { }

    /**
     * This method provides the x-value of the position of the item.
     * @return double This returns the x-value of the item position
     */
    public double getEntityX() { return x; }

    /**
     * This method provides the y-value of the position of the item.
     * @return double This returns the y-value of the item position
     */
    public double getEntityY() { return y; }

    /**
     * This method indicates whether the item has been eaten by the player.
     * @return boolean This returns the value that indicates player-item collision state
     */
    public boolean getWasEaten() { return wasEaten;};

    /**
     * This method sets the player-ghost collision state.
     */
    public void setWasEaten() { this.wasEaten = true; }

}
