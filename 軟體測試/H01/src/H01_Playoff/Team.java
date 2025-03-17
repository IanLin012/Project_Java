package H01_Playoff;

/**
 * Team class include team name, division, wins, losss, seed number
 */
public class Team {
    private final String teamName;
    private final String division;
    private final int wins;
    private final int losses;
    private int seed;

    /**
     * Constructor
     *
     * @param teamName
     * @param division
     * @param wins
     * @param losses
     */
    public Team(final String teamName, final String division, final int wins, final int losses) {
        this.teamName = teamName;
        this.division = division;
        this.wins = wins;
        this.losses = losses;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDivision() {
        return division;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(final int seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return teamName + " seed:" + seed;
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

    /**
     * Inspect two team object equal with team name。
     *
     * @param obj comparison object
     * @return Equal return true, otherwise false
     */
    @Override
    public boolean equals(final Object obj) {
        boolean isEqual = false;
        if (this == obj) {
            isEqual = true;
        } else if (obj != null && getClass() == obj.getClass()) {
            final Team other = (Team) obj;
            isEqual = teamName.equals(other.teamName);
        }
        return isEqual;
    }
}
