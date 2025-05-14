package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * MLB 類別的 JUnit 測試
 */
public class MLBTest {

    /**
     * 測試平日(infield)且非 Ticketmaster 購票時價格計算
     */
    @Test
    public void testWeekdayInfieldWithoutTicketmaster() throws DateException {
        MLB mlb = new MLB();
        int price = mlb.getPrice(3, "infield", "SomeOtherSite");
        assertEquals(25000, price, "Weekday infield ticket should cost 25000 when not purchased via Ticketmaster.");
    }

    /**
     * 測試平日(outfield)且非 Ticketmaster 購票時價格計算
     */
    @Test
    public void testWeekdayOutfieldWithoutTicketmaster() throws DateException {
        MLB mlb = new MLB();
        int price = mlb.getPrice(2, "outfield", "AnotherSite");
        assertEquals(20000, price, "Weekday outfield ticket should cost 20000.");
    }

    /**
     * 測試假日(VIP)且非 Ticketmaster 購票時價格計算。
     */
    @Test
    public void testWeekendVIPWithoutTicketmaster() throws DateException {
        MLB mlb = new MLB();
        int price = mlb.getPrice(7, "VIP", "OtherSite");
        assertEquals(25000, price, "Weekend VIP ticket should cost 25000 when not purchased via Ticketmaster.");
    }

    /**
     * 測試假日(infield)且透過 Ticketmaster 購票時價格計算。
     */
    @Test
    public void testWeekendInfieldWithTicketmaster() throws DateException {
        MLB mlb = new MLB();
        int price = mlb.getPrice(6, "infield", "Ticketmaster");
        assertEquals(27000, price, "Weekend infield ticket purchased via Ticketmaster should cost 27000.");
    }

    /**
     * 測試假日(outfield)且透過 Ticketmaster 購票時價格計算。
     */
    @Test
    public void testWeekendOutfieldWithTicketmaster() throws DateException {
        MLB mlb = new MLB();
        int price = mlb.getPrice(6, "outfield", "Ticketmaster");
        assertEquals(22500, price, "Weekend outfield ticket purchased via Ticketmaster should cost 27000.");
    }

    /**
     * 測試假日(VIP)且透過 Ticketmaster 購票時價格計算。
     */
    @Test
    public void testWeekendVIPWithTicketmaster() throws DateException {
        MLB mlb = new MLB();
        int price = mlb.getPrice(6, "VIP", "Ticketmaster");
        assertEquals(22500, price, "Weekend VIP ticket purchased via Ticketmaster should cost 27000.");
    }

    /**
     * 測試日期異常情形
     */
    @Test
    public void testInvalidDayThrowsException() {
        MLB mlb = new MLB();
        Exception exception = assertThrows(DateException.class, () -> {
            mlb.getPrice(0, "infield", "Ticketmaster");
        });
        String expectedMessage = "日期不對";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Exception message should indicate invalid date.");
    }

    /**
     * 測試座位型態錯誤
     */
    @Test
    public void testInvalidSeatTypeDoesNotApplyDiscountOrAddFees() throws DateException {
        MLB mlb = new MLB();
        int price = mlb.getPrice(4, "balcony", "Ticketmaster");
        // 預期返回初始價格 20000，因為無效的座位型態不會進入條件處理
        assertEquals(20000, price, "Invalid seat type should result in default price without any modifications.");
    }
}
