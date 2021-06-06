public class League {
    private Match[] matches;
    private Club[] clubs;
    private int[] standings;
    private int[] pointTable;
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
        standings = new int[5];
        pointTable = new int[5];
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
                if (i == j) continue;
                Match thisMatch = new Match(matchNo, clubs[i], clubs[j]);
                thisMatch.setHomeTeamIndex(i);
                thisMatch.setAwayTeamIndex(j);
                matches[matchNo++] = thisMatch;
            }
        }
    }

    public void simulateMatches(){
        for (int i=0; i<matchCount; i++){
            matches[i].play();
            int homeIndex = matches[i].getHomeTeamIndex();
            int awayIndex = matches[i].getAwayTeamIndex();
            pointTable[homeIndex] += matches[i].getHomeTeamPoint();
            pointTable[awayIndex] += matches[i].getAwayTeamPoint();
        }
    }

    public void Merge(int[] standings, int l, int m, int r){
        int len1 = m - l + 1;
        int len2 = r - m;
        int[] c1 = new int[len1];
        int[] c2 = new int[len2];
        for (int i=0; i<len1; i++){
            c1[i] = standings[l+i];
        }
        for (int j=0; j<len2; j++){
            c2[j] = standings[m+j+1];
        }
        int index = l;
        int lx = 0, rx = 0;
        while (lx < len1 && rx < len2){
            if (pointTable[c1[lx]] > pointTable[c2[rx]]){
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

    public void MergeSort(int[] standings, int l, int r){
        if (l >= r) return;
        int middle = l + (r - l) / 2;
        MergeSort(standings, l, middle);
        MergeSort(standings, middle+1, r);
        Merge(standings, l, middle, r);
    }

    public void SelectionSort(int[] standings){
        int n = clubCount;
        for (int i=0; i<n; i++){
            for (int j=i+1; j<n; j++){
                if (pointTable[standings[i]] < pointTable[standings[j]]){
                    var temp = standings[i];
                    standings[i] = standings[j];
                    standings[j] = temp;
                }
            }
        }
    }

    public void updateStandings(){
        for (int i=0; i<clubCount; i++){
            standings[i] = i;
        }
//        MergeSort(standings, 0, clubCount-1);
        SelectionSort(standings);
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
            System.out.printf("%d. %s %d\n", i+1, clubs[standings[i]].getName(), pointTable[standings[i]]);
        }
    }
    // add your methods here, if required
    public void addClub(Club c){
        clubs[clubCount++] = c;
    }
    public void removeFrom(Club c, Club[] clubs){
        for (int i=0; i<clubCount; i++){
            if (clubs[i].getId() == c.getId()){
                for (int j=i+1; j<clubCount; j++){
                    clubs[j-1] = clubs[j];
                }
                break;
            }
        }
    }
    public void removeClub(Club c){
        removeFrom(c, clubs);
        clubCount--;
    }

    public void printMatches(){
        System.out.println("Matches:");
        for (int i=0; i<matchCount; i++){
            matches[i].showResult();
        }
    }
}

