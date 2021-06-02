public class Club {
    private int id;
    private String name;
    private Player[] players;
    private int playerCount;
    // add your code here

	// you are not allowed to write any other constructor
    public Club() {
        this.players = new Player[11];
        playerCount = 0;
    }

    public double getSalary() {
        double total = 0;
        for (int i=0; i<playerCount; i++) {
            total += players[i].getSalary();
        }
        return total;
    }

    // add your code here
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public void addPlayer(Player p){
        players[playerCount++] = p;
    }

    public Player getMaxSalaryPlayer(){
        Player p = null;
        double maxSalary = Double.MIN_VALUE;
        for (int i=0; i<playerCount; i++){
            if (maxSalary < players[i].getSalary()){
                maxSalary = players[i].getSalary();
                p = players[i];
            }
        }
        return p;
    }
}