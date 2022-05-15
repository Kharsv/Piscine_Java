package ex03;

public class UserIdsGenerator {
    private Integer lastId;
    public Integer MAX_SIZE =1000000;
    private static ex01.UserIdsGenerator userIdsGenerator;
    public UserIdsGenerator() {
        lastId = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null) {
            userIdsGenerator = new UserIdsGenerator();
        }
        return userIdsGenerator;
    }

    public Integer  generateId() {
        lastId++;
        if (lastId > MAX_SIZE)
            lastId = 0;
        return lastId;
    }

}
