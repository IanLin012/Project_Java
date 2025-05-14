package Playoff;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Read team data
 */
public final class TeamLoader {
    private static final Logger LOGGER = Logger.getLogger(TeamLoader.class.getName());
    private static final int MIN_TOKENS = 4;
    private static final int TOT_GAMES = 162;

    // Private constructor avoid to be instanced
    private TeamLoader() {}

    /**
     * Use NIO to read team data in csv
     *
     * @param csvFile csv file location
     * @return Team list
     */
    public static List<Team> loadTeams(final String csvFile) {
        final List<Team> teams = new ArrayList<>();
        int exitStatus = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFile), StandardCharsets.UTF_8)) {
            // Pass headline
            reader.readLine();
            String line = reader.readLine();

            String teamName, division;
            int wins, losses;

            while (line != null) {
                final String[] tokens = line.split(",");
                if (tokens.length >= MIN_TOKENS) {
                    teamName = tokens[0].trim();
                    division = tokens[1].trim();
                    wins = Integer.parseInt(tokens[2].trim());
                    losses = Integer.parseInt(tokens[3].trim());
                    if ((wins + losses) != TOT_GAMES) {
                        final String finalTeamName = teamName;
                        final int finalWins = wins;
                        final int finalLosses = losses;
                        LOGGER.log(Level.WARNING, () -> "Team " + finalTeamName + " total games unequal to 162: "
                                + TOT_GAMES + " - " + finalWins + " wins, " + finalLosses + " losses");
                    }
                    teams.add(new Team(teamName, division, wins, losses));
                } else {
                    final String finalLine = line;
                    LOGGER.log(Level.WARNING, () -> "Missing partial data: " + finalLine);
                }
                line = reader.readLine();
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, () -> "Failed to read file: " + csvFile + ", reason: " + e.getMessage());
            exitStatus = 1;
        } catch (final NumberFormatException e) {
            LOGGER.log(Level.SEVERE, () -> "Invalid number format: " + e.getMessage());
            exitStatus = 1;
        }
        if (exitStatus == 1) {
            System.exit(exitStatus);
        }
        return teams;
    }
}
