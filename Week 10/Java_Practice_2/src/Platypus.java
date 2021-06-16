public class Platypus extends Mammal implements Venomous{
    public Platypus(String name, int age) {
        super(name, age);
    }

    @Override
    public boolean isLethalToAdultHumans() {
        return false;
    }
}