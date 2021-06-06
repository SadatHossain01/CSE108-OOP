public class League {
    private Match[] matches;
    private int matchCount;
    private int clubCount;
    // add your variables here, if required
    private Club[] clubs;
    private Club[] standings;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public League() {
        // assume a league can have at most 5 clubs, add code for initialization accordingly
        clubCount = 0;
        matchCount = 0;
        clubs = new Club[5];
        standings = new Club[5];
    }

    public void printLeagueInfo(){
        System.out.println("League : " + name);
        printClubs();
    }

    public void printClubs(){
        System.out.println("Clubs:");
        // print the name of the clubs of this league, each one on a line
        for (int i=0; i<clubCount; i++){
            System.out.println(clubs[i].getName());
        }
    }

    public void scheduleMatches(){
        matchCount = (clubCount*(clubCount-1));
        matches = new Match[matchCount];
        int matchNo = 0;
        for (int i=0; i<clubCount; i++){
            for (int j=0; j<clubCount; j++){
                // check the constructor of the Match class and add your code here
                // note that there will be two matches between club A and club B
                // in the first match, club A will play as the home team and in the second match, as the away team
                if (j == 0) clubs[i].setPoint(0);
                if (i == j) continue;
                matches[matchNo++] = new Match(matchNo, clubs[i], clubs[j]);
            }
        }
    }

    public void simulateMatches(){
        for (int i=0; i<matchCount; i++){
            matches[i].play();
        }
    }

    public void showStandings(){
        // sort the clubs in descending order of points
        // note that, the sequence in which clubs were added to the league, should be unchanged
        // check the given sample output for clarification
        // (carefully observe the output of showStandings() followed by printLeagueInfo() method calls
        // you can use additional arrays if needed
        updateStandings();
        // print the clubs in descending order of points
        System.out.println("Sl. - Club - Points");
        for (int i=0; i<clubCount; i++){
            System.out.printf("%d. %s %d\n", i+1, standings[i].getName(), standings[i].getPoint());
        }
    }
    // add your methods here, if required

    public void Merge(Club[] standings, int l, int m, int r){
        int len1 = m - l + 1;
        int len2 = r - m;
        Club[] c1 = new Club[len1];
        Club[] c2 = new Club[len2];
        for (int i=0; i<len1; i++){
            c1[i] = standings[l+i];
        }
        for (int j=0; j<len2; j++){
            c2[j] = standings[m+j+1];
        }
        int index = l;
        int lx = 0, rx = 0;
        while (lx < len1 && rx < len2){
            if (c1[lx].getPoint() > c2[rx].getPoint()){
                standings[index++] = c1[lx++];
            }
            else standings[index++] = c2[rx++];
        }
        while (lx < len1){
            standings[index++] = c1[lx++];
        }
        while (rx < len2){
            standings[index++] = c2[rx++];
        }
    }

    public void MergeSort(Club[] standings, int l, int r){
        if (l >= r) return;
        int middle = l + (r - l) / 2;
        MergeSort(standings, l, middle);
        MergeSort(standings, middle+1, r);
        Merge(standings, l, middle, r);
    }

    public void SelectionSort(Club[] standings){
        int n = clubCount;
        for (int i=0; i<n; i++){
            for (int j=i+1; j<n; j++){
                if (standings[i].getPoint() < standings[j].getPoint()){
                    var temp = standings[i];
                    standings[i] = standings[j];
                    standings[j] = temp;
                }
            }
        }
    }

    public void updateStandings(){
        standings = clubs;
//        MergeSort(standings, 0, clubCount-1);
        SelectionSort(standings);
    }

    public void addClub(Club c){
        clubs[clubCount++] = new Club(c);
    }

    public void removeFrom(Club c, Club[] clubs){
        for (int i=0; i<clubCount; i++){
            if (clubs[i].getId() == c.getId() && clubs[i].getName().equals(c.getName())){
                for (int j=i+1; j<clubCount; j++){
                    clubs[j-1] = clubs[j];
                }
                break;
            }
        }
    }

    public void removeClub(Club c){
        removeFrom(c, clubs);
        removeFrom(c, standings);
        clubCount--;
    }

    public void printMatches(){
        System.out.println("Matches:");
        for (int i=0; i<matchCount; i++){
            matches[i].showResult();
        }
    }
}

