public abstract class Person {
    protected int id;
    protected String name;
    protected String email;

    public Person() {
        this(0, "Unknown", "unknown@example.com");
    }

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Person[id=%d, name=%s, email=%s]", id, name, email);
    }
}

