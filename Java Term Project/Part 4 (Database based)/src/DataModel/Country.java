package DataModel;

public class Country {
    private String name;
    private int count;

    public Country(String name) {
        setName(name);
    }

    public Country(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = FormatCountryName(name);
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    @Override
    public String toString() {
        return name;
    }

    public String FormatCountryName(String name) {
        return League.FormatName(name);
    }
}
