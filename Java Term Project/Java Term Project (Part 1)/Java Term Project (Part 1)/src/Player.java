public class Player {
    private String name, country, club, position;
    private int age, number;
    private double height, WeeklySalary;

    public Player(){ }

    public Player(String name, String country, int age, double height, String club, String position, int number, double WeeklySalary){
        this.name = FormatPlayerName(name);
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.WeeklySalary = WeeklySalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = FormatPlayerName(name);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeeklySalary() {
        return WeeklySalary;
    }

    public void setWeeklySalary(double WeeklySalary) {
        this.WeeklySalary = WeeklySalary;
    }

    public String FormatPlayerName(String name){
        return League.FormatName(name);
    }

    public void showDetails(){
        System.out.println("Name: " + name);
        System.out.println("Country: " + country);
        System.out.println("Age(Year): " + age);
        System.out.println("Height: " + height);
        System.out.println("Club: " + club);
        System.out.println("Position: " + position);
        System.out.println("Number: " + number);
        System.out.println("Weekly Salary: " + WeeklySalary);
    }
}
