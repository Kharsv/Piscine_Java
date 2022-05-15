import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

import java.io.InputStream;
import java.util.Properties;

class IllegalParametersException extends RuntimeException {}

@Parameters(separators = "=")
public class Program {
    @Parameter(names="--enemiesCount")
    private static Integer enemiesCount;
    @Parameter(names="--wallsCount")
    private static Integer wallsCount;
    @Parameter(names="--size")
    private static Integer size;
    @Parameter(names="--profile")
    private static String profile;
    private static Properties properties;
    private static final String HINT = "IllegalArgument";

    public static void main(String[] args) {
        Program program = new Program();

        try {
            JCommander.newBuilder().addObject(program).build().parse(args);
            program.parseArgs();
            program.run();
        } catch (ParameterException e) {
            System.out.println(HINT);
            System.exit(-1);
        }
    }

    public void parseArgs() {
        String profileFile;
        InputStream properties;

        if (Program.enemiesCount == null || Program.wallsCount == null || Program.size == null
                || Program.profile == null) {
            System.out.println(HINT);
            System.exit(-1);
        }

        profileFile = String.format("/application-%s.properties", Program.profile);
        properties = Program.class.getResourceAsStream(profileFile);

        if (properties == null) {
            System.out.printf("Error reading parameters file: %s\n", profileFile);
            System.exit(-1);
        }

        try {
            Program.properties = new Properties();
            Program.properties.load(properties);
        } catch (Exception e) {
            System.out.printf("Error parsing parameters file: %s\n", profileFile);
            System.exit(-1);
        }
    }

    public void run() {
        Map[] map = Game.getMap();

        Game.buildMap();
        //noinspection InfiniteLoopStatement
        while (true) {
            //noinspection StatementWithEmptyBody
            while (!Game.playerMove());
            //noinspection StatementWithEmptyBody
            while (!Game.enemyMove());
        }
    }

    public static Integer getEnemiesCount() {
        return Program.enemiesCount;
    }

    public static Integer getWallsCount() {
        return Program.wallsCount;
    }

    public static Integer getSize() {
        return Program.size;
    }

    public static Properties getProperties() {
        return Program.properties;
    }
}
