import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private static boolean isDisplayRefresh = true;
    private static boolean isEnemyMoveAuto = true;
    protected static Component enemy;
    protected static Component player;
    protected static Component wall;
    protected static Component goal;
    protected static Component empty;
    protected static Map[] map;
    private static Map[] enemies;
    private static Map player1 = null;
    private static Map goal1 = null;

    static {
        if (Program.getProperties().getProperty("displayRefresh.bool").equals("false")) {
            Game.isDisplayRefresh = false;
        }
        if (Program.getProperties().getProperty("enemyMoveAuto.bool").equals("false")) {
            Game.isEnemyMoveAuto = false;
        }
        Game.enemy = new Component("enemy.char", "enemy.colour");
        Game.player = new Component("player.char", "player.colour");
        Game.wall = new Component("wall.char", "wall.colour");
        Game.goal = new Component("goal.char", "goal.colour");
        Game.empty = new Component("empty.char", "empty.colour");
    }

    public static Map[] getMap() {
        return map;
    }

    public static void buildMap() throws IllegalParametersException {
        int squareSize = Program.getSize() * Program.getSize();
        int i = squareSize;
        int j;
        int enemiesCount = Program.getEnemiesCount();
        int wallsCount = Program.getWallsCount();
        int endlessLoopError = 0;

        Game.map = new Map[Program.getSize() * Program.getSize()];

        for (int k = 0; k < Game.map.length; k++) {
            Game.map[k] = new Map(k);
        }

        Game.enemies = new Map[Program.getEnemiesCount()];

        while (i > 0) {
            if (!Game.isDisplayRefresh) {
                Game.renderFrame();
                System.out.println();
            }

            j = ThreadLocalRandom.current().nextInt(0, squareSize);

            while (Game.map[j].getType() != empty || Game.badCell(map[j])) {
                if (++j >= squareSize) {
                    j = 0;

                    if (++endlessLoopError > 1) {
                        System.out.println("Map could not be built: decrease enemies and walls or increase size");
                        throw new IllegalParametersException();
                    }
                }
            }

            endlessLoopError = 0;

            if (Game.player1 == null) {
                Game.map[j].setType(Game.player);
                Game.player1 = Game.map[j];
            } else if (Game.goal1 == null) {
                Game.map[j].setType(Game.goal);
                Game.goal1 = Game.map[j];
            } else if (enemiesCount > 0) {
                Game.map[j].setType(Game.enemy);
                Game.enemies[Program.getEnemiesCount() - enemiesCount] = Game.map[j];
                enemiesCount--;
            } else if (wallsCount > 0) {
                Game.map[j].setType(Game.wall);
                wallsCount--;
            } else {
                break;
            }
            i--;
        }
    }

    private static boolean badCell(Map cell) {
        return ((((cell.up() != null && Map.notEmpty(cell.up().left()))
                || Map.notEmpty(cell.up())
                || (cell.up() != null && Map.notEmpty(cell.up().right())))
                && ((cell.down() != null && Map.notEmpty(cell.down().left())))
                || Map.notEmpty(cell.down())
                || (cell.down() != null && Map.notEmpty(cell.down().right())))
                || (((cell.left() != null && Map.notEmpty(cell.left().up()))
                || Map.notEmpty(cell.left())
                || (cell.left() != null && Map.notEmpty(cell.left().down())))
                && ((cell.right() != null && Map.notEmpty(cell.right().up())))
                || Map.notEmpty(cell.right())
                || (cell.right() != null && Map.notEmpty(cell.right().down()))));
    }

    public static void renderFrame() {
        ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false).build();

        if (Game.isDisplayRefresh) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        for (int y = 0; y < Program.getSize(); y++) {
            for (int x = 0; x < Program.getSize(); x++) {
                coloredPrinter.print(String.format(" %s ", Game.map[y * Program.getSize() + x].getType().ch),
                        Ansi.Attribute.NONE, Ansi.FColor.NONE,
                        Ansi.BColor.valueOf(Game.map[y * Program.getSize() + x].getType().bg));
            }
            coloredPrinter.println("", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.NONE);
        }
    }

    public static boolean playerMove() {
        Scanner scanner = new Scanner(System.in);
        int move;

        Game.renderFrame();
        System.out.println("Input: 1 - left, 2 - up, 3 - right, 4 - down, 9 - give up");

        try {
            move = scanner.nextInt();

            if (move >= 1 && move <= 4) {
                if (Game.movePlayerTo(Game.movePlayer(Game.player1, move))) {
                    return true;
                } else {
                    System.out.println("Couldn't make a move there. Please try again");
                }
            } else if (move == 9) {
                Game.endGame("You gave up!");
            } else {
                System.out.println("Couldn't read input. Please try again");
            }
        } catch (Exception e) {
            System.out.println("Couldn't read input. Please try again");
        }

        return false;
    }

    private static Map movePlayer(Map player, int move) {
        switch (move) {
            case 1:
                return player.left();
            case 2:
                return player.up();
            case 3:
                return player.right();
            case 4:
                return player.down();
            default:
                return null;
        }
    }

    private static boolean movePlayerTo(Map target) {
        if (target == null || target.getType() == Game.wall) {
            return false;
        } else if (target.getType() == Game.goal) {
            target.setType(Game.player);
            Game.player1.setType(Game.empty);
            Game.renderFrame();
            Game.endGame("Congratulations! You have won!");
        } else if (target.getType() == Game.enemy) {
            Game.player1.setType(Game.empty);
            Game.renderFrame();
            Game.endGame("Oops! You have died...");
        } else {
            target.setType(Game.player);
            Game.player1.setType(Game.empty);
        }

        Game.player1 = target;

        return true;
    }

    public static boolean enemyMove() {
        Scanner scanner = new Scanner(System.in);

        Game.renderFrame();
        System.out.println();

        try {
            if (!Game.isEnemyMoveAuto) {
                System.out.println("Developer mode: 8 - continue");

                if (scanner.nextInt() != 8) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Couldn't read input. Please try again");

            return false;
        }

        for (int i = 0; i < Game.enemies.length; i++) {
            Map target;
            Map enemy = Game.enemies[i];
            int left = Game.enemyMoveCount(enemy.left(), 0);
            int up = Game.enemyMoveCount(enemy.up(), 0);
            int right = Game.enemyMoveCount(enemy.right(), 0);
            int down = Game.enemyMoveCount(enemy.down(), 0);
            int chaseLogic = ChaseLogic.enemyMoveLogic(left, up, right, down);

            target = Game.movePlayer(enemy, chaseLogic);

            if (target != null) {
                target.setType(Game.enemy);
                enemy.setType(Game.empty);
                Game.enemies[i] = target;
            }
        }

        return true;
    }

    private static int enemyMoveCount(Map direction, int count) {
        if (count > 15 || direction == null || (direction.getType() != Game.empty && direction.getType() != Game.player)) {
            return Integer.MAX_VALUE;
        } else if (direction.getType() == Game.player) {
            return count;
        } else {
            int left = enemyMoveCount(direction.left(), count + 1);
            int up = enemyMoveCount(direction.up(), count + 1);
            int right = enemyMoveCount(direction.right(), count + 1);
            int down = enemyMoveCount(direction.down(), count + 1);

            return Integer.min(left, Integer.min(up, Integer.min(right, down)));
        }
    }

    private static void endGame(String message) {
        System.out.println(message);
        System.exit(0);
    }
}

