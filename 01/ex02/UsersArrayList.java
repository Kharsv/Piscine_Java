package ex02;
class UserNotFoundException extends RuntimeException {}
public class UsersArrayList implements UsersList {
    private User[] arr;
    private Integer count;

    private static Integer DEFAULT_SIZE = 10;

    public UsersArrayList() {
        arr = new User[DEFAULT_SIZE];
        count = 0;
    }

    public void add(User user) {
        if (arr.length == count) {
            User[] newArr = new User[count + count];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[count] = user;
        count++;
    }

    public Integer getCount() {
        return count;
    }


    public User getById(Integer id) {
        for (int i = 0; i < count; i++) {
            if (id.equals(arr[i].getId())) {
                return arr[i];
            }
        }
        throw new UserNotFoundException();
    }


    public User getByIndex(Integer id) {
        if (id < 0 || id >= count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[id];
    }

}
