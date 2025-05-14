package Playoff;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Divide league & Choose seeds
*/
public final class LeagueManager {
    private static final Logger LOGGER = Logger.getLogger(LeagueManager.class.getName());
    private static final int REQ_TEAMS = 6;
    private static final int WILDCARD_SEEDS = 3;

    // Private constructor avoid to be instanced
    private LeagueManager() {}

    /**
     * Divide teams with league
     *
     * @param teams Team list
     * @return Comparison list of league and team
     */
    public static Map<String, List<Team>> groupTeamsByLeague(final List<Team> teams) {
        final Map<String, List<Team>> leagueMap = new ConcurrentHashMap<>();
        leagueMap.put("AL", new ArrayList<>());
        leagueMap.put("NL", new ArrayList<>());

        for (final Team t : teams) {
            final String div = t.getDivision();
            if (div.startsWith("AL")) {
                leagueMap.get("AL").add(t);
            } else if (div.startsWith("NL")) {
                leagueMap.get("NL").add(t);
            } else {
                LOGGER.log(Level.WARNING, () -> "Team " + t.getTeamName() + " division unclear: " + div);
            }
        }
        return leagueMap;
    }

    /**
     * Verify both leagues at least have 6 teams
     *
     * @param leagueMap Comparison list of league and team
     * @return Enough return true, otherwise false
     */
    public static boolean validateLeagueTeams(final Map<String, List<Team>> leagueMap) {
        final int alTeamCount = leagueMap.get("AL").size();
        final int nlTeamCount = leagueMap.get("NL").size();
        boolean valid = true;
        if (alTeamCount < REQ_TEAMS) {
            LOGGER.log(Level.SEVERE, () -> "AL lack of " + (REQ_TEAMS - alTeamCount) + " teams");
            valid = false;
        }
        if (nlTeamCount < REQ_TEAMS) {
            LOGGER.log(Level.SEVERE, () -> "NL lack of " + (REQ_TEAMS - nlTeamCount) + " teams");
            valid = false;
        }
        if (valid) {
            LOGGER.log(Level.INFO, () -> "AL contains " + alTeamCount +
                    " teams, NL contains " + nlTeamCount + " teams");
        }
        return valid;
    }

    /**
     * Calculate seed teams
     *
     * @param leagueMap Comparison list of league and team
     * @return Comparison list of league and seed
     */
    public static Map<String, List<Team>> computeLeagueSeeds(final Map<String, List<Team>> leagueMap) {
        final Map<String, List<Team>> leagueSeeds = new ConcurrentHashMap<>();
        for (final String league : leagueMap.keySet()) {
            final List<Team> seeds = getSeeds(leagueMap.get(league));
            leagueSeeds.put(league, seeds);
            LOGGER.log(Level.INFO, () -> league + " selected seeds: " + seeds);
        }
        return leagueSeeds;
    }

    /**
     * Choose seed teams
     *
     * @param leagueTeams league team list
     * @return seed team list
     */
    public static List<Team> getSeeds(final List<Team> leagueTeams) {
        final Map<String, Team> divisionChampions = new ConcurrentHashMap<>();
        for (final Team t : leagueTeams) {
            final String division = t.getDivision();
            if (!divisionChampions.containsKey(division) || t.getWins() > divisionChampions.get(division).getWins()) {
                divisionChampions.put(division, t);
            }
        }
        final List<Team> divisionWinners = new ArrayList<>(divisionChampions.values());
        Collections.sort(divisionWinners, (final Team t1, final Team t2) ->
                Integer.compare(t2.getWins(), t1.getWins()));
        final Set<Team> championSet = ConcurrentHashMap.newKeySet();
        championSet.addAll(divisionWinners);
        final List<Team> wildcards = new ArrayList<>();
        for (final Team t : leagueTeams) {
            if (!championSet.contains(t)) {
                wildcards.add(t);
            }
        }
        Collections.sort(wildcards, (final Team t1, final Team t2) ->
                Integer.compare(t2.getWins(), t1.getWins()));
        if (wildcards.size() > WILDCARD_SEEDS) {
            final List<Team> limitedWildcards = new ArrayList<>(wildcards.subList(0, 3));
            wildcards.clear();
            wildcards.addAll(limitedWildcards);
        }
        final List<Team> allSeeds = new ArrayList<>();
        allSeeds.addAll(divisionWinners);
        allSeeds.addAll(wildcards);
        for (int i = 0; i < allSeeds.size(); i++) {
            allSeeds.get(i).setSeed(i + 1);
        }
        return allSeeds;
    }
}
