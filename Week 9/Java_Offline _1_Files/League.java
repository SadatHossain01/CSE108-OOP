public class League {
    private Match[] matches;
    private Club[] clubs;
    private Club[] standings;
    private int matchCount;
    private int clubCount;
    private String name;
    // add your variables here, if required

    public void setName(String name) {
        this.name = name;
    }

    public League() {
        // assume a league can have at most 5 clubs, add code for initialization accordingly
        clubCount = 0;
        matchCount = 0;
        clubs = new Club[5];
        standings = new Club[5];
        matches = new Match[20];
    }

    public void printLeagueInfo(){
        System.out.println("League : " + name);
        printClubs();
    }

    public void printClubs(){
        System.out.println("Clubs:");
        for (int i=0; i<clubCount; i++){
            System.out.println(clubs[i].getName());
        }
        // print the name of the clubs of this league, each one on a line
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
                Match thisMatch = new Match(matchCount, clubs[i], clubs[j]);
                matches[matchCount++] = thisMatch;
                thisMatch = new Match(matchCount, clubs[j], clubs[i]);
                matches[matchCount++] = thisMatch;
            }
        }
    }

    public void simulateMatches(){
        for (int i=0; i<matchCount; i++){
            matches[i].play();
        }
    }

    public void Merge(Club[] clubs, int l, int m, int r){
        int len1 = m - l + 1;
        int len2 = r - m;
        Club[] c1 = new Club[len1];
        Club[] c2 = new Club[len2];
        for (int i=0; i<len1; i++){
            c1[i] = clubs[l+i];
        }
        for (int j=0; j<len2; j++){
            c2[j] = clubs[m+j+1];
        }
        int index = l;
        int lx = 0, rx = 0;
        while (lx < len1 && rx < len2){
            if (c1[lx].getPoint() > c2[rx].getPoint()){
                clubs[index++] = c1[lx++];
            }
            else clubs[index++] = c2[rx++];
        }
        while (lx < len1){
            clubs[index++] = c1[lx++];
        }
        while (rx < len2){
            clubs[index++] = c2[rx++];
        }
    }

    public void MergeSort(Club[] clubs, int l, int r){
        if (l >= r) return;
        int middle = l + (r - l) / 2;
        MergeSort(clubs, l, middle);
        MergeSort(clubs, middle+1, r);
        Merge(clubs, l, middle, r);
    }

    public void updateStandings(){
        MergeSort(standings, 0, clubCount-1);
    }

    public void showStandings(){
        // sort the clubs in descending order of points
        // note that, the sequence in which clubs were added to the league, should be unchanged
        // check the given sample output for clarification
        // (carefully observe the output of showStandings() followed by printLeagueInfo() method calls
        // you can use additional arrays if needed
        updateStandings();
        System.out.println("Sl. - Club - Points");
        for (int i=0; i<clubCount; i++){
            System.out.printf("%d - %s - %d\n", i+1, standings[i].getName(), standings[i].getPoint());
        }
        // print the clubs in descending order of points
    }

    public void addClub(Club c){
        clubs[clubCount++] = c;
    }

    public void removeClub(Club c){
        for (int i=0; i<clubCount; i++){
            if (clubs[i].getId() == c.getId()){
                for (int j=i+1; j<clubCount; j++){
                    clubs[i] = clubs[j];
                }
                break;
            }
        }
        clubCount--;
    }

    public void printMatches(){
        for (int i=0; i<matchCount; i++){
            matches[i].showResult();
        }
    }
    // add your methods here, if required
}

