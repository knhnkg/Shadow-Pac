import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 1, 2023
 *
 * Please enter your name below
 * Hyunkyung Kwon
 */

/**
 * This ShadowPac class function as the main control centre for the overall progression of the game.
 * This class controls the storing and updating of every entity.
 * This also detects the level completion, win and lose status of the game.
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    private final static int TITLE_FONT_SIZE = 64;
    private final static int INSTRUCTION_FONT_SIZE = 24;
    private final static int LEVEL1_INSTRUCTION_FONT_SIZE = 40;
    private final static int ENDING_FONT_SIZE = 64;
    private final static int LEVEL_COMPLETE_FONT_SIZE = 64;
    private final static int TITLE_X = 260;
    private final static int TITLE_Y = 250;
    private final static int INS_X_OFFSET = 60;
    private final static int INS_Y_OFFSET = 190;
    private final static int LEVEL_UP_FRAME = 300;
    private final static String INSTRUCTION_MESSAGE = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE";
    private final static String LEVEL1_INSTRUCTION_MESSAGE = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE\nEAT THE PELLET TO ATTACK";
    private final static String END_MESSAGE = "GAME OVER!";
    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String LEVEL_COMPLETE_MESSAGE = "LEVEL COMPLETE!";
    private final Font TITLE_FONT = new Font("res/FSO8BITR.ttf", TITLE_FONT_SIZE);
    private final Font INSTRUCTION_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_FONT_SIZE);
    private final Font LEVEL1_INSTRUCTION_FONT = new Font("res/FSO8BITR.ttf", LEVEL1_INSTRUCTION_FONT_SIZE);
    private final Font LEVEL_COMPLETE_FONT = new Font("res/FSO8BITR.ttf", LEVEL_COMPLETE_FONT_SIZE);
    private final Font ENDING_FONT = new Font("res/FSO8BITR.TTF", ENDING_FONT_SIZE);

    private final double TOTAL_FREEZY_FRAME = 1000;

    private final static File level0 = new File("res/level0.csv");
    private final static File level1 = new File("res/level1.csv");
    private File worldFile;

    private Player pacman;
    private Score score = new Score();
    private Heart heart;
    private Pellet pellet;
    private Rectangle pelletRect;
    private double playerPrevX;
    private double playerPrevY;
    private double ghostPrevX;
    private double ghostPrevY;
    private int pinkGhostIndex;

    // The following four arraylists records newly created objects of corresponding entity.
    private ArrayList<Ghost> Ghosts = new ArrayList<Ghost>();
    private ArrayList<Wall> Walls = new ArrayList<Wall>();
    private ArrayList<Dot> Dots = new ArrayList<Dot>();
    private ArrayList<Cherry> Cherries = new ArrayList<Cherry>();

    // The following four arraylists records newly created rectangles of corresponding entity.
    private ArrayList<Rectangle> GhostRect = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> WallRect = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> DotRect = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> CherryRect = new ArrayList<Rectangle>();

    private boolean gameStarted = false;
    private boolean gameEnded = false;
    private boolean levelUp = false;
    private boolean spaceKeyActive = true;
    private boolean freezyModeOn = false;

    private double lifeRemaining = 3;
    private double frameCounter = 0;
    private double LevelWinFrameCounter = 0;
    private int scoreCounter = 0;

    /**
     * This constructor contains the basic information about the size of the game window and the game title.
     */
    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        readCSV();
    }

    /**
     * This method is used to the csv read file, create objects and contain them in the array is required.
     * As the method reads each line of the csv file, the position coordinate of each entity is created.
     * For the entities that are multiple in amount, they are all recorded in one array and corresponding rectangles
     * are also created and contained in another array.
     */
    private void readCSV() {
        if (levelUp) { worldFile = level1; }
        else { worldFile = level0; }

        try (BufferedReader br = new BufferedReader(new FileReader(worldFile))) {
            String text;

            while((text = br.readLine()) != null) {
                String cells[] = text.split(",");

                String entity = cells[0];
                double xCoordinate = Double.parseDouble(cells[1]);
                double yCoordinate = Double.parseDouble(cells[2]);

                if (entity.equals("Player")) {
                    pacman = new Player(xCoordinate, yCoordinate);
                }
                if (entity.equals("Wall")) {
                    Wall wall = new Wall(xCoordinate, yCoordinate);
                    Rectangle wallRect = new Rectangle(xCoordinate, yCoordinate, wall.WALL_WIDTH, wall.WALL_HEIGHT);
                    Walls.add(wall);
                    WallRect.add(wallRect);
                }
                if (entity.equals("Ghost")) {
                    Ghost ghost = new Ghost(xCoordinate, yCoordinate, false);
                    Rectangle ghostRect = new Rectangle(xCoordinate, yCoordinate, ghost.GHOST_WIDTH, ghost.GHOST_HEIGHT);
                    Ghosts.add(ghost);
                    GhostRect.add(ghostRect);
                }
                if (entity.equals("GhostRed")) {
                    RedGhost redGhost = new RedGhost(xCoordinate, yCoordinate, false);
                    Ghosts.add(redGhost);
                }
                if (entity.equals("GhostBlue")) {
                    BlueGhost blueGhost = new BlueGhost(xCoordinate, yCoordinate, false);
                    Ghosts.add(blueGhost);
                }
                if (entity.equals("GhostGreen")) {
                    GreenGhost greenGhost = new GreenGhost(xCoordinate, yCoordinate, false);
                    Ghosts.add(greenGhost);
                }
                if (entity.equals("GhostPink")) {
                    PinkGhost pinkGhost = new PinkGhost(xCoordinate, yCoordinate, false);
                    Ghosts.add(pinkGhost);
                }
                if (entity.equals("Dot")) {
                    Dot dot = new Dot(xCoordinate, yCoordinate, false);
                    Rectangle dotRect = new Rectangle(xCoordinate, yCoordinate, dot.DOT_WIDTH, dot.DOT_HEIGHT);
                    Dots.add(dot);
                    DotRect.add(dotRect);
                }
                if (entity.equals("Cherry")) {
                    Cherry cherry = new Cherry(xCoordinate, yCoordinate, false);
                    Rectangle cherryRect = new Rectangle(xCoordinate, yCoordinate, cherry.DOT_WIDTH, cherry.DOT_HEIGHT);
                    Cherries.add(cherry);
                    CherryRect.add(cherryRect);
                }
                if (entity.equals("Pellet")) {
                    pellet = new Pellet(xCoordinate, yCoordinate, false);
                    pelletRect = new Rectangle(xCoordinate, yCoordinate, pellet.DOT_WIDTH, pellet.DOT_HEIGHT);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the entry point for the program, where the game gets started.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.run();
    }

    /**
     * This method is used to order the progressive state update of every entity for the game to run.
     * This method allows the game to exit at any point, when the escape key is pressed.
     * This method detects level completion, winning or losing of the game.
     * This method allows the player to proceed to the next level and renders the game world.
     * This method controls the behaviour of all entities based on the activation status of frenzy mode.
     * @param input This parameter corresponds to the keyboard key pressed by the player
     */
    @Override
    protected void update(Input input) {
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        // Game can be exited when the escape key is pressed.
        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        // Game can jump straight to level 1 when W key is pressed.
        if (input.wasPressed(Keys.W)){
            scoreCounter = Dots.size()*10;
        }
        // Starting screen is drawn when the game has not been started at all.
        if (!gameStarted && !gameEnded && !levelUp) {
            drawStartScreen();
        }
        // Level completion screen and level 1 instruction screens are drawn in order when the player
        // successfully completes level 0.
        if (!gameStarted && !gameEnded && levelUp) {
            if (!(LevelWinFrameCounter == LEVEL_UP_FRAME)) {
                drawLevelCompleteScreen();
                LevelWinFrameCounter += 1;
                spaceKeyActive = false;
            }
            else {
                drawLevelInstructionScreen();
                spaceKeyActive = true;
            }
        }
        // Once the space key is pressed, the game starts.
        if (input.wasPressed(Keys.SPACE) && spaceKeyActive){
            gameStarted = true;
        }
        // As the game starts, each entity is drawn on the screen, following the behaviours implemented.
        if (gameStarted && !gameEnded) {

            // Draws the player on the screen.
            pacman.Update(input, freezyModeOn);
            Rectangle playerRect = new Rectangle(pacman.getX(), pacman.getY(), pacman.PLAYER_WIDTH, pacman.PLAYER_HEIGHT);

            // Draws and keep track of the score.
            score.Update("");

            // Draws the remaining heart(s) on the screen.
            heart = new Heart(lifeRemaining);
            heart.Update();

            // Draws the ghosts on the screen.
            // During level 0, the wasEaten state of each ghost is irrelevant as frenzy mode is impossible.
            // But during level 1, especially during the frenzy mode, only the ghosts that has not collided with
            // the player gets drawn on the game screen.
            for (int g=0; g<Ghosts.size(); g++) {
                if (!Ghosts.get(g).getWasEaten()) {
                    Ghosts.get(g).Update(freezyModeOn);
                    Rectangle ghostRect = new Rectangle(Ghosts.get(g).getX(), Ghosts.get(g).getY(), Ghosts.get(g).GHOST_WIDTH, Ghosts.get(g).GHOST_HEIGHT);
                    GhostRect.add(ghostRect);
                }
                else if (Ghosts.get(g).getWasEaten()) {
                    Rectangle ghostRect = new Rectangle(0, 0, Ghosts.get(g).GHOST_WIDTH, Ghosts.get(g).GHOST_HEIGHT);
                    GhostRect.add(ghostRect);
                }
                if (Ghosts.get(g).ghostIdentify() == 3) {
                    pinkGhostIndex = g;
                }
            }

            // Draws the walls on the screen.
            for (int w=0; w<Walls.size(); w++) {
                Walls.get(w).Update();
            }

            // Draws each dots on the screen.
            // Condition is applied, in order to only draw the dots that has not been consumed by the player.
            for (int d=0; d<Dots.size(); d++) {
                if (!Dots.get(d).getWasEaten()) {
                    Dots.get(d).Update();
                }
                if (DotRect.get(d).intersects(playerRect)) {
                    // Marks the dot as eaten when intersected, so it is not drawn in the next frame.
                    Dots.get(d).setWasEaten();
                    scoreCounter += 10;
                    // The corresponding rectangle is no longer needed, hence move to inaccessible position.
                    DotRect.get(d).moveTo(new Point(0,0));
                    score.Update("dot"); // Increase the score by corresponding points
                }
            }

            // The following are only applicable for level 1.
            if (levelUp) {

                // Draw cherries on the screen.
                // Logic applied for rendering dots has been used.
                for (int c = 0; c < Cherries.size(); c++) {
                    if (!Cherries.get(c).getWasEaten()) {
                        Cherries.get(c).Update();
                    }
                    if (CherryRect.get(c).intersects(playerRect)) {
                        Cherries.get(c).setWasEaten();
                        scoreCounter += 20;
                        CherryRect.get(c).moveTo(new Point(0, 0));
                        score.Update("cherry"); // Increase the score by corresponding points
                    }
                }

                // Draw cherries on the screen, if not yet been collided by the player.
                if (!pellet.getWasEaten()) {
                    pellet.Update();
                }
                // When consumed by the player, the frenzy mode is activated.
                if (pelletRect.intersects(playerRect)) {
                    pellet.setWasEaten();
                    freezyModeOn = true;
                    pelletRect.moveTo(new Point(0, 0));
                }
                // Count the frames to know when to finish the frenzy mode.
                if (freezyModeOn == true) {
                    freezyFrameCounter();
                }

                // Control the behaviours of the moving ghosts, when collided with the wall.
                ghostWallIntersection();
            }

            // Control the behaviour of the player, when collided with the wall.
            playerWallIntersection(playerRect);
            // Control the behaviour of the player and ghosts, when they collide.
            playerGhostIntersection(playerRect);
            // Record the very recent position of the player.
            playerGetRecentPosition();
            // Record the very recent position of the pink ghost, due to its movement behaviour.
            ghostGetRecentPosition(Ghosts.get(pinkGhostIndex));

            GhostRect.clear();
        }

        // Level0 is successfully completed when all dots are consumed.
        // Proceed to level 1.
        if (!levelUp && scoreCounter == Dots.size()*10) {
            gameStarted = false;
            levelUp = true;
            score = new Score();
            levelReset();
        }
        // Level 1 is successfully completed.
        // Draw the winning screen.
        else if (levelUp && scoreCounter == 800) {
            gameEnded = true;
            drawWinScreen();
        }
        // All remaining lives has been used.
        // Draw the ending screen.
        else if (lifeRemaining == 0) {
            gameEnded = true;
            drawEndScreen();
        }
    }

    // This method is used to draw the starting screen of the game.
    private void drawStartScreen() {
        TITLE_FONT.drawString(GAME_TITLE, TITLE_X, TITLE_Y);
        INSTRUCTION_FONT.drawString(INSTRUCTION_MESSAGE,TITLE_X + INS_X_OFFSET, TITLE_Y + INS_Y_OFFSET);
    }

    // This method is used to draw the level completion screen after the successful completion of level 0.
    private void drawLevelCompleteScreen() {
        double levelWinMessageX = (Window.getWidth()/2.0) - (LEVEL_COMPLETE_FONT.getWidth(LEVEL_COMPLETE_MESSAGE)/2.0);
        double levelWinMessageY = (LEVEL_COMPLETE_FONT_SIZE/2.0) + (Window.getHeight()/2.0);
        LEVEL_COMPLETE_FONT.drawString(LEVEL_COMPLETE_MESSAGE, levelWinMessageX, levelWinMessageY);
    }

    // This method is used to draw the level 1 instruction screen.
    private void drawLevelInstructionScreen() {
        LEVEL1_INSTRUCTION_FONT.drawString(LEVEL1_INSTRUCTION_MESSAGE, 200, 350);
    }

    // This method is used to draw the winning screen after the successful completion of level 1.
    private void drawWinScreen() {
        double winMessageX = (Window.getWidth()/2.0) - (ENDING_FONT.getWidth(WIN_MESSAGE)/2.0);
        double winMessageY = (ENDING_FONT_SIZE/2.0) + (Window.getHeight()/2.0);
        ENDING_FONT.drawString(WIN_MESSAGE, winMessageX, winMessageY);
    }

    // This method is used to draw the ending screen after the use of all remaining lives.
    private void drawEndScreen() {
        double endMessageX = (Window.getWidth()/2.0) - (ENDING_FONT.getWidth(END_MESSAGE)/2.0);
        double endMessageY = (ENDING_FONT_SIZE/2.0) + (Window.getHeight()/2.0);
        ENDING_FONT.drawString(END_MESSAGE, endMessageX, endMessageY);
    }

    // This method is used to control the player's behaviour after colliding with a wall.
    // When a player hits a wall, they instantly get moved back to the very recent position, in order to
    // prevent the player from moving through walls and escaping the game screen boundaries.
    private void playerWallIntersection(Rectangle rect) {
        for (int i=0; i<Walls.size(); i++) {
            if (WallRect.get(i).intersects(rect)) {
                pacman.bounceBackPosition(playerPrevX, playerPrevY);
            }
        }
    }

    // This method is used to control the ghosts' behaviour after colliding with a wall.
    // Red, blue and green ghosts reverse their movement direction when collided with a wall.
    // Pink ghost bounce back to its very recent position and reset the movement direction when collided with a wall.
    private void ghostWallIntersection() {
        for (int p=0; p<Walls.size(); p++) {
            for (int q=0; q<GhostRect.size(); q++) {
                if (WallRect.get(p).intersects(GhostRect.get(q))) {
                    if (Ghosts.get(q).ghostIdentify() == 3) {
                        Ghosts.get(q).bounceBackPosition(ghostPrevX, ghostPrevY);
                        Ghosts.get(q).setDirectionNum(Ghosts.get(q).selectDirection());
                    }
                    if (!Ghosts.get(q).getDirection()) {
                        Ghosts.get(q).setDirection(true);
                    }
                    else if (Ghosts.get(q).getDirection()) {
                        Ghosts.get(q).setDirection(false);
                    }
                }
            }
        }
    }

    // This method is used to control player and ghost behaviours when they collide.
    // When the frenzy mode is off, both entity with move back to their starting position and the player will lose
    // one life.
    // When the frenzy mode is on, the player will consume the ghost and the score will increase by corresponding
    // amount of points.
    private void playerGhostIntersection(Rectangle rect) {
        for (int j=0; j<GhostRect.size(); j++) {
            if (GhostRect.get(j).intersects(rect)) {
                if (!freezyModeOn) {
                    lifeRemaining -= 1;
                    pacman.resetPosition();
                    Ghosts.get(j).resetPosition(Ghosts.get(j).getInitialX(), Ghosts.get(j).getInitialY());
                }
                else if (freezyModeOn) {
                    Ghosts.get(j).setWasEaten(true);
                    scoreCounter += 30;
                    GhostRect.get(j).moveTo(new Point(0,0));
                    score.Update("ghost");
                }
            }
        }
    }

    // This method records the x and y values of the player's very recent position.
    private void playerGetRecentPosition() {
        playerPrevX = pacman.getX();
        playerPrevY = pacman.getY();
    }

    // This method records the x and y values of the ghost's very recent position.
    private void ghostGetRecentPosition(Ghost touchedGhost) {
        ghostPrevX = touchedGhost.getX();
        ghostPrevY = touchedGhost.getY();
    }

    // This method counts the frame, once the frenzy mode has been activated.
    // After the maximum frenzy mode frames has been reached, this method inactivates the frenzy mode and
    // resets the ghosts to their initial position.
    private void freezyFrameCounter() {
        if (frameCounter == TOTAL_FREEZY_FRAME) {
            freezyModeOn = false;
            for (int a=0; a<Ghosts.size(); a++) {
                Ghosts.get(a).setWasEaten(false);
                Ghosts.get(a).resetPosition(Ghosts.get(a).getInitialX(), Ghosts.get(a).getInitialY());
            }
        }
        frameCounter += 1;
    }

    // This method clears the arraylists and variables that records and keep track of entities.
    private void levelReset() {
        levelUp = true;
        Ghosts.clear();
        GhostRect.clear();
        Walls.clear();
        WallRect.clear();
        Dots.clear();
        DotRect.clear();
        lifeRemaining = 3;
        scoreCounter = 0;
        readCSV();
    }
}
