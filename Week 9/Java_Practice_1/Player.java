public class Player {
    private int number;
    private String name;
    private double salary;

	// you are not allowed to write any other constructor

    public Player(int number, String name) {
        setNumber(number);
        setName(name);
    }

    // add your code here

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
