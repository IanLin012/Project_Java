package org.example;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
/**
 *MLB 世界大賽票價
 */
public class MLB {
    /**
     *set logger
     */
    private static final Logger LOGGER = Logger.getLogger(MLB.class.getName());
    /* default */ MLB() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }
    /**
     *getPrice function
     */
    public int getPrice (final int day, final String seatType, final String webSite) throws DateException {
        if ((day < 1) || (day > 7)) {
            throw new DateException("日期不對: " + day);
        }
        int price = 20_000;
        final String infield = "infield", outfield = "outfield", vip = "VIP";
        if (infield.equals(seatType) || outfield.equals(seatType) || vip.equals(seatType)) {
            if (day == 6 || day == 7) {
                price = 25_000;
            }
            if ("infield".equals(seatType)) {
                price += 5_000;
            }
            if ("Ticketmaster".equals(webSite)) {
                price *= 0.9;
            }
        }
        else {
            LOGGER.log(Level.SEVERE, () -> "座位型態錯誤: " + seatType);
        }
        return price;
    }

    /**
     * main
     * @param args
     * @throws DateException
     */
    public static void main(final String[] args) throws DateException {
        final MLB mlb = new MLB();
        final Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("請輸入日期(週幾，數字形式): ");
        final int day = scanner.nextInt();
        System.out.print("請輸入座位型態(infield, outfield, VIP): ");
        final String seatType = scanner.next();
        System.out.print("請輸入購買網站: ");
        final String website = scanner.next();
        System.out.println(mlb.getPrice(day, seatType, website));
        scanner.close();
    }
}