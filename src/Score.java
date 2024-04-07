import bagel.*;

/**
 * This Score class holds the information about the score and renders it on the game screen.
 * This controls the score to increase based on the item consumed by the player during the game.
 */
public class Score {
    private final Font SCORE_FONT = new Font("res/FSO8BITR.TTF", 20);
    private final int DOT_INCREMENT = 10; // Each dot corresponds to 10 points.
    private final int CHERRY_INCREMENT = 20; // Each cherry corresponds to 20 points.
    private final int GHOST_INCREMENT = 30; // Each ghost during frenzy mode corresponds to 30 points.
    private int scoreCounter;
    private double xCoordinate, yCoordinate = 25;

    /**
     * This constructor holds the record of the score.
     */
    public Score() {
        scoreCounter = 0;
    }

    /**
     * This method renders the score on the game screen.
     * This controls the amount of points increased, based on the type of entity/ item consumed by the player.
     * @param type This parameter indicates the type of entity/ item consumed (e.g. ghost, dot and cherry)
     */
    public void Update(String type) {
        // Each time the player intersects another dot, the score increases.
        if (type.equals("dot")) {
            scoreCounter = scoreCounter + DOT_INCREMENT;
        }
        else if (type.equals("cherry")) {
            scoreCounter = scoreCounter + CHERRY_INCREMENT;
        }
        else if (type.equals("ghost")) {
            scoreCounter = scoreCounter + GHOST_INCREMENT;
        }

        SCORE_FONT.drawString("SCORE " + scoreCounter, xCoordinate, yCoordinate);
    }
}

