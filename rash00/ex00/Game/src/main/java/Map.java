public class Map extends Game {
    private final int index;
    private final int x;
    private final int y;
    private Component type;

    public Map(int index) {
        this.index = index;
        this.y = this.index / Program.getSize();
        this.x = this.index % Program.getSize();
        this.type = Game.empty;
    }

    public Component getType() {
        return type;
    }

    public void setType(Component type) {
        this.type = type;
    }

    public Map up() {
        if (this.y == 0) {
            return null;
        } else {
            return Game.map[this.index - Program.getSize()];
        }
    }

    public Map down() {
        if (this.y == Program.getSize() - 1) {
            return null;
        } else {
            return Game.map[this.index + Program.getSize()];
        }
    }

    public Map left() {
        if (this.x == 0) {
            return null;
        } else {
            return Game.map[this.index - 1];
        }
    }

    public Map right() {
        if (this.x == Program.getSize() - 1) {
            return null;
        } else {
            return Game.map[this.index + 1];
        }
    }

    public static boolean notEmpty(Map cell) {
        return cell == null || cell.type != Game.empty;
    }
}
