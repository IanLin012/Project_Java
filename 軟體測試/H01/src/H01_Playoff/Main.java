package H01_Playoff;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Schedule AL & NL playoff
 */
public final class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    // Private constructor avoid to be instanced
    private Main() {}

    /**
     * Execute all function
     *
     * @param args
     */
    public static void main(final String[] args) {
        int exitStatus = 0;
        final String csvFile = args.length > 0
                ? args[0]
                : "mlb_standings.csv";

        final List<Team> teams = TeamLoader.loadTeams(csvFile);
        if (teams.isEmpty()) {
            LOGGER.log(Level.WARNING, () -> "Team list is empty");
            exitStatus = 1;
        }
        LOGGER.log(Level.INFO, () -> "Team list includes " + teams.size() + " teams");

        final Map<String, List<Team>> leagueMap = LeagueManager.groupTeamsByLeague(teams);
        if (LeagueManager.validateLeagueTeams(leagueMap)) {
            final Map<String, List<Team>> leagueSeeds = LeagueManager.computeLeagueSeeds(leagueMap);
            SchedulePrinter.printSchedules(leagueSeeds);
        } else {
            exitStatus = 1;
        }
        System.exit(exitStatus);
    }
}
