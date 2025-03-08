// 美國大聯盟季後賽排程- 防禦性程式設計
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        // csv file location
        String csvFile = "C:\\Users\\drlin\\Project_Java\\軟體測試\\H01\\mlb_standings.csv";
        if (args.length > 0) {
            csvFile = args[0];
        }

        // build team list
        List<Team> allTeams = readCSV(csvFile);
        if (allTeams.isEmpty()) {
            logger.warning("Team list is empty");
        } else {
            logger.info("Team list includes " + allTeams.size() + " teams");
        }

        // divide league
        Map<String, List<Team>> leagueMap = new HashMap<>();
        leagueMap.put("AL", new ArrayList<>());
        leagueMap.put("NL", new ArrayList<>());

        for (Team t : allTeams) {
            if (t.getDivision().startsWith("AL")) {
                leagueMap.get("AL").add(t);
            } else if (t.getDivision().startsWith("NL")) {
                leagueMap.get("NL").add(t);
            } else {
                logger.warning("Team " + t.getTeam() + " division unclear: " + t.getDivision());
            }
        }

        int ALTeams = leagueMap.get("AL").size(), NLTeams = leagueMap.get("NL").size();
        if (ALTeams < 6) {
            logger.severe("AL lack of " + (6 - ALTeams) + " teams");
            System.exit(1);
        } else if (NLTeams < 6) {
            logger.severe("NL lack of " + (6 - NLTeams) + " teams");
            System.exit(1);
        } else {
            logger.info("AL contains " + leagueMap.get("AL").size() + " teams, " + "NL contains " + leagueMap.get("NL").size() + " teams");
        }

        // choose league seeds
        Map<String, List<Team>> leagueSeedsMap = new HashMap<>();
        for (String league : leagueMap.keySet()) {
            List<Team> leagueTeams = leagueMap.get(league);
            List<Team> seeds = getSeeds(leagueTeams);
            leagueSeedsMap.put(league, seeds);
            logger.info(league + " selected seeds: " + seeds);
        }

        // print schedule
        printSchedule("AMERICAN", leagueSeedsMap.get("AL"), true);
        printSchedule("NATIONAL", leagueSeedsMap.get("NL"), false);
    }

    private static List<Team> readCSV(String csvFile) {
        List<Team> teams = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); //skip title line
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 4) {
                    String teamName = tokens[0].trim();
                    String division = tokens[1].trim();
                    int wins = Integer.parseInt(tokens[2].trim());
                    int losses = Integer.parseInt(tokens[3].trim());
                    if (wins + losses != 162) {
                        logger.warning("Team " + teamName + " total games unequal to 162: " + wins + " wins, " + losses + " losses");
                    }
                    teams.add(new Team(teamName, division, wins, losses));
                } else {
                    logger.warning("Missing partial data: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            logger.severe("File not found");
            System.exit(1);
        } catch (IOException e) {
            logger.severe("Failed to read file");
            System.exit(1);
        } catch (NumberFormatException e) {
            logger.severe("Invalid number format");
            System.exit(1);
        }
        return teams;
    }

    private static List<Team> getSeeds(List<Team> leagueTeams) {
        // find division champion
        Map<String, Team> divisionChamps = new HashMap<>();
        for (Team t : leagueTeams) {
            String division = t.getDivision();
            if (!divisionChamps.containsKey(division) || t.getWins() > divisionChamps.get(division).getWins()) {
                divisionChamps.put(division, t);
            }
        }
        List<Team> divisionWinners = new ArrayList<>(divisionChamps.values());
        divisionWinners.sort((a, b) -> b.getWins() - a.getWins());

        // build wildcard list
        Set<Team> champSet = new HashSet<>(divisionWinners);
        List<Team> wildcards = new ArrayList<>();
        for (Team t : leagueTeams) {
            if (!champSet.contains(t)) {
                wildcards.add(t);
            }
        }
        wildcards.sort((a, b) -> b.getWins() - a.getWins());

        // find top three of wildcard
        if (wildcards.size() > 3) {
            wildcards = wildcards.subList(0, 3);
        }

        // build seed list in order
        List<Team> allSeeds = new ArrayList<>();
        allSeeds.addAll(divisionWinners); //seed 1~3
        allSeeds.addAll(wildcards); //seed 4~6

        // set seed number
        for (int i = 0; i < allSeeds.size(); i++) {
            allSeeds.get(i).setSeed(i + 1);
        }
        return allSeeds;
    }

    private static void printSchedule(String leagueName, List<Team> seeds, boolean isAL) {
        Team s1 = seeds.get(0);
        Team s2 = seeds.get(1);
        Team s3 = seeds.get(2);
        Team s4 = seeds.get(3);
        Team s5 = seeds.get(4);
        Team s6 = seeds.get(5);

        if (isAL) {
            System.out.println("(" + leagueName + " LEAGUE)");
            System.out.println("| WILDCARD | ALDS | ALCS | WORLD SERIES |");
            System.out.printf("%6s", getSeedString(s6));
            System.out.println(" ---");
            System.out.printf("%6s", getSeedString(s3));
            System.out.println(" --- ? ----");
            System.out.printf("%12s", getSeedString(s2));
            System.out.println(" ---- ? ----");
            System.out.printf("%6s", getSeedString(s5));
            System.out.println(" ---");
            System.out.printf("%6s", getSeedString(s4));
            System.out.println(" --- ? ----");
            System.out.printf("%12s", getSeedString(s1));
            System.out.println(" ---- ? ---- ? ------------");
            System.out.println("                                        ?");
        } else {
            System.out.printf("%6s", getSeedString(s6));
            System.out.println(" --- ? ---- ? ---- ? ------------");
            System.out.printf("%6s", getSeedString(s3));
            System.out.println(" ---");
            System.out.printf("%12s", getSeedString(s2));
            System.out.println(" ----");
            System.out.printf("%6s", getSeedString(s5));
            System.out.println(" --- ? ---- ? ----");
            System.out.printf("%6s", getSeedString(s4));
            System.out.println(" ---");
            System.out.printf("%12s", getSeedString(s1));
            System.out.println(" ----");
            System.out.println("| WILDCARD | NLDS | NLCS | WORLD SERIES |");
            System.out.println("(" + leagueName + " LEAGUE)");
        }
    }

    // return team name and seed number
    private static String getSeedString(Team t) {
        return t.getTeam() + " " + t.getSeed();
    }
}

class Team {
    private String team;
    private String division;
    private int wins;
    private int losses;
    private int seed;

    public Team(String team, String division, int wins, int losses) {
        this.team = team;
        this.division = division;
        this.wins = wins;
        this.losses = losses;
    }

    public String getTeam() {
        return team;
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
    public void setSeed(int seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return team + " seed:" + seed;
    }

    // avoid team name repeat
    @Override
    public int hashCode() {
        return team.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Team other = (Team) obj;
        return team.equals(other.team);
    }
}
