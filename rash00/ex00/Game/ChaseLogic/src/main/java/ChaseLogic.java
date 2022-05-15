public class ChaseLogic {
    public static int enemyMoveLogic(int left, int up, int right, int down) {
        if (left == Integer.MAX_VALUE && up == left && right == left && down == left) {
            return 0;
        } else if (left <= up && left <= right && left <= down) {
            return 1;
        } else if (up <= right && up <= down) {
            return 2;
        } else if (right <= down) {
            return 3;
        } else {
            return 4;
        }
    }
}
