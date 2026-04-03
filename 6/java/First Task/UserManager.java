import java.util.*;

class UserManager {

    private Map<String, String> users = new HashMap<>();

    // додати
    public void addUser(String login, String password) {
        users.put(login, password);
    }

    // видалити
    public void removeUser(String login) {
        users.remove(login);
    }

    // перевірка (Stream API)
    public boolean exists(String login) {
        return users.keySet()
                .stream()
                .anyMatch(l -> l.equals(login));
    }

    // змінити логін
    public void changeLogin(String oldLogin, String newLogin) {
        if (users.containsKey(oldLogin)) {
            String pass = users.remove(oldLogin);
            users.put(newLogin, pass);
        }
    }

    // змінити пароль
    public void changePassword(String login, String newPass) {
        users.computeIfPresent(login, (k, v) -> newPass);
    }

    public void printAll() {
        users.forEach((l, p) -> System.out.println(l + ":" + p));
    }
}