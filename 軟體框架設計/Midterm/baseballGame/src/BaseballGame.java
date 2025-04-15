import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 球員資訊
 */
class Player {
    private String name;
    private String position; //防守位置
    private int hits; //安打數
    private int atBat; //擊球數

    public Player(String name, String position) {
        this.name = name;
        this.position = position;
        this.hits = 0;
        this.atBat = 0;
    }

    // 模擬每次擊球行為
    public void recordAtBat(boolean isHit) {
        atBat++;
        if (isHit) {
            hits++;
        }
    }

    //計算安打率
    public double getBattingAverage() {
        if (atBat == 0) return 0.0;
        return (double) hits / atBat;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getHits() {
        return hits;
    }

    public int getatBat() {
        return atBat;
    }

    //球員資訊字串
    @Override
    public String toString() {
        return String.format(
                "Name: %s, position: %s, at-bat: %d, hit: %d, hit-rate: %.3f",
                name, position, atBat, hits, getBattingAverage()
        );
    }
}

/**
 *球隊資訊
 */
class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public List<Player> getPlayers() {
        return players;
    }

    // 計算全隊安打率
    public double getTeamBattingAverage() {
        int totalHits = 0;
        int totalatBat = 0;
        for (Player p : players) {
            totalHits += p.getHits();
            totalatBat += p.getatBat();
        }
        if (totalatBat == 0) return 0.0;
        return (double) totalHits / totalatBat;
    }

    public String getName() {
        return name;
    }

    // 球隊名稱字串
    @Override
    public String toString() {
        return String.format("Team: %s", name);
    }
}

/**
 * 每次打擊紀錄
 */
class AtBatRecord {
    private Player player;
    private boolean isHit;

    public AtBatRecord(Player player, boolean isHit) {
        this.player = player;
        this.isHit = isHit;
    }

    public boolean isHit() {
        return isHit;
    }

    public Player getPlayer() {
        return player;
    }
}

/**
 * 每局資訊
 */
class Inning {
    private int inningNumber;
    private List<AtBatRecord> topHalfatBat; //上半局打擊紀錄
    private List<AtBatRecord> bottomHalfatBat; //下半局打擊紀錄

    public Inning(int inningNumber) {
        this.inningNumber = inningNumber;
        this.topHalfatBat = new ArrayList<>();
        this.bottomHalfatBat = new ArrayList<>();
    }

    public void addTopHalfAtBat(AtBatRecord record) {
        topHalfatBat.add(record);
    }

    public void addBottomHalfAtBat(AtBatRecord record) {
        bottomHalfatBat.add(record);
    }

    public int getInningNumber() {
        return inningNumber;
    }

    public List<AtBatRecord> getTopHalfatBat() {
        return topHalfatBat;
    }

    public List<AtBatRecord> getBottomHalfatBat() {
        return bottomHalfatBat;
    }
}

/**
 * 比賽資訊
 */
class Game {
    private String date; //比賽日期
    private Team teamA;
    private Team teamB;
    private List<Inning> innings;
    private Random random; //隨機擊球結果

    public Game(String date, Team teamA, Team teamB) {
        this.date = date;
        this.teamA = teamA;
        this.teamB = teamB;
        this.innings = new ArrayList<>();
        this.random = new Random();
    }

    // 比賽流程
    public void startGame() {
        for (int i = 1; i <= 9; i++) {
            Inning inning = new Inning(i);

            // 上半局
            for (Player p : teamA.getPlayers()) { //簡化為每位球員各打一次
                boolean isHit = random.nextBoolean();
                p.recordAtBat(isHit);
                inning.addTopHalfAtBat(new AtBatRecord(p, isHit));
            }

            // 下半局
            for (Player p : teamB.getPlayers()) { //簡化為每位球員各打一次
                boolean isHit = random.nextBoolean();
                p.recordAtBat(isHit);
                inning.addBottomHalfAtBat(new AtBatRecord(p, isHit));
            }

            innings.add(inning);
        }
    }

    // 輸出所有資訊
    public void printResult() {
        System.out.println("Game Date: " + date + "\n");

        // TeamA 結果
        System.out.println(teamA);
        for (Player p : teamA.getPlayers()) {
            System.out.println("  " + p);
        }
        System.out.printf("Team %s average hit-rate: %.3f\n",
                teamA.getName(), teamA.getTeamBattingAverage());

        System.out.println();

        // TeamB 結果
        System.out.println(teamB);
        for (Player p : teamB.getPlayers()) {
            System.out.println("  " + p);
        }
        System.out.printf("Team %s average hit-rate: %.3f\n",
                teamB.getName(), teamB.getTeamBattingAverage());
    }
}

public class BaseballGame {
    public static void main(String[] args) {
        // 建立兩隊Team
        Team teamA = new Team("Taiwan");
        Team teamB = new Team("Japan");

        // TeamA 先發球員
        teamA.addPlayer(new Player("Betts", "Pitcher"));
        teamA.addPlayer(new Player("Alice", "Catcher"));
        teamA.addPlayer(new Player("Curry", "1st Baseman"));
        teamA.addPlayer(new Player("David", "2nd Baseman"));
        teamA.addPlayer(new Player("Elen", "3rd Baseman"));
        teamA.addPlayer(new Player("Ford", "Short Stop"));
        teamA.addPlayer(new Player("George", "Left Fielder"));
        teamA.addPlayer(new Player("Harry", "Center Fielder"));
        teamA.addPlayer(new Player("Ian", "Right Fielder"));

        // TeamB 先發球員
        teamB.addPlayer(new Player("John", "Pitcher"));
        teamB.addPlayer(new Player("Kevin", "Catcher"));
        teamB.addPlayer(new Player("Lewis", "1st Baseman"));
        teamB.addPlayer(new Player("Melody", "2nd Baseman"));
        teamB.addPlayer(new Player("Nathan", "3rd Baseman"));
        teamB.addPlayer(new Player("Olga", "Short Stop"));
        teamB.addPlayer(new Player("Parry", "Left Fielder"));
        teamB.addPlayer(new Player("Quin", "Center Fielder"));
        teamB.addPlayer(new Player("Robert", "Right Fielder"));

        // 建立比賽
        Game game = new Game("2025-04-08", teamA, teamB);

        // 開始比賽(9局)
        game.startGame();

        // 列印結果
        game.printResult();
    }
}
