public class Club {
    private int Id;
    private String name;
    private int point;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Club(){
        this.Id = 0;
        this.name = "";
        this.point = 0;
    }

    public Club(Club c){
        this.Id = c.Id;
        this.name = c.name;
        this.point = c.point;
    }

    public Club(int Id, String name, int point){
        this.Id = Id;
        this.name = name;
        this.point = point;
    }
}
