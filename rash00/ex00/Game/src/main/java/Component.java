public class Component {
    public final String ch;
    public final String bg;

    public Component(String character, String colour) {
        if (Program.getProperties().getProperty(character).equals("")) {
            this.ch = " ";
        } else {
            this.ch = Program.getProperties().getProperty(character);
        }
        this.bg = Program.getProperties().getProperty(colour);

        if (this.ch == null) {
            System.out.printf("Error parsing parameters file: %s missing\n", character);
            System.exit(-1);
        }

        if (this.bg == null) {
            System.out.printf("Error parsing parameters file: %s missing\n", colour);
            System.exit(-1);
        }
    }
}
