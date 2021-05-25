package epam.practical8.db.entity;


public class User {

    private int id;
    private String login;

    public User(String login){
        this.login = login;
    }

    public User(){

    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return this.login.equals(((User) obj).getLogin());
    }

    @Override
    public int hashCode() {
        return 31 * id + (login != null ? login.hashCode() : 0);
    }

    public static User createUser(String login) {
        User user = new User();
        user.setLogin(login);
        return user;
    }

}
