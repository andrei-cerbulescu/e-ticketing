package eticketing;

public class person {
    protected int age, ID;
    protected String name, surname;

    public int getAge() {
        return age;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString(){
        return this.name+" "+this.surname;
    }
}
