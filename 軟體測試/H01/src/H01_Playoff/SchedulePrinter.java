package H01_Playoff;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Print AL & NL playoff schedule
 */
public final class SchedulePrinter {

    // Format string
    private static final String FORMAT_AL_6 = "%6s ---\n";
    private static final String FORMAT_AL_6_Q = "%6s --- ? ----\n";
    private static final String FORMAT_AL_12_Q = "%12s ---- ? ----\n";
    private static final String FORMAT_NL_6 = "%6s --- ? ---- ? ---- ? ------------\n";
    private static final String FORMAT_NL_12 = "%12s ----\n";

    // Private constructor avoid to be instanced
    private SchedulePrinter() {}

    /**
     * Print both leagues' schedule
     *
     * @param leagueSeeds Comparison list of league and seed
     */
    public static void printSchedules(final Map<String, List<Team>> leagueSeeds) {
        printSchedule("AMERICAN", leagueSeeds.get("AL"), true);
        printSchedule("NATIONAL", leagueSeeds.get("NL"), false);
    }

    /**
     * Print specify schedule
     *
     * @param leagueName AMERICAN or NATIONAL
     * @param seeds league seed list
     * @param isAL AL return true, otherwise false
     */
    public static void printSchedule(final String leagueName, final List<Team> seeds, final boolean isAL) {
        final Team seed1 = seeds.get(0);
        final Team seed2 = seeds.get(1);
        final Team seed3 = seeds.get(2);
        final Team seed4 = seeds.get(3);
        final Team seed5 = seeds.get(4);
        final Team seed6 = seeds.get(5);
        if (isAL) {
            System.out.println("(" + leagueName + " LEAGUE)");
            System.out.println("| WILDCARD | ALDS | ALCS | WORLD SERIES |");
            System.out.printf(FORMAT_AL_6, formatSeed(seed6));
            System.out.printf(FORMAT_AL_6_Q, formatSeed(seed3));
            System.out.printf(FORMAT_AL_12_Q, formatSeed(seed2));
            System.out.printf(FORMAT_AL_6, formatSeed(seed5));
            System.out.printf(FORMAT_AL_6_Q, formatSeed(seed4));
            System.out.printf("%12s ---- ? ---- ? ------------\n", formatSeed(seed1));
            System.out.println("                                        ?");
        } else {
            System.out.printf(FORMAT_NL_6, formatSeed(seed6));
            System.out.printf(FORMAT_AL_6, formatSeed(seed3));
            System.out.printf(FORMAT_NL_12, formatSeed(seed2));
            System.out.printf("%6s --- ? ---- ? ----\n", formatSeed(seed5));
            System.out.printf(FORMAT_AL_6, formatSeed(seed4));
            System.out.printf(FORMAT_NL_12, formatSeed(seed1));
            System.out.println("| WILDCARD | NLDS | NLCS | WORLD SERIES |");
            System.out.println("(" + leagueName + " LEAGUE)");
        }
    }

    /**
     * Format team name & seed number
     *
     * @param team team obj
     * @return Formatted string
     */
    private static String formatSeed(final Team team) {
        return team.getTeamName() + " " + team.getSeed();
    }
}
