import java.util.ArrayList;
import java.util.Random;

public abstract class Person {

    private static ArrayList<Person> people = new ArrayList<Person>();

    private String id;
    private String name;
    private String email;

    private String username;
    private String password;

    public Person(
        Random random,
        String name,
        String email,
        String username,
        String password
    ) {
        people.add(this);
        this.id = String.valueOf(random.nextInt(1000000, 9999999));
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public static Person logIn(
        String username,
        String password
    ) {
        for (Person person : people) {
            if (person.username.equals(username)) {
                if (person.password.equals(password)) {
                    return person;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Person [email=" + email + ", id=" + id + ", name=" + name + ", password=" + password + "]";
    }

}
