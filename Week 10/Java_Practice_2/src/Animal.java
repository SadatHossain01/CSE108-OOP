public class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        String ans = name + " is a " + getClass().getSimpleName() + ", aged " + age;
        return ans;
    }
}