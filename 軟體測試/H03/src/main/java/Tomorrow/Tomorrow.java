package Tomorrow;

import java.util.Calendar;

public class Tomorrow {
    public Date tomorrow(Date date) {
        int tempYear = date.year, tempMonth = date.month, tempDay = date.day;
        if(date.month < 1 || date.month > 12) {
            throw new IllegalStateException("月份超出邊界");
        }
        if(date.day < 1) { throw new IllegalStateException("日期小於邊界"); }
        if(date.month == 4 || date.month == 6 ||
                date.month == 9 || date.month == 11) { //小月
            if(date.day < 30) { //非月末
                tempDay = date.day + 1;
            } else if(date.day == 30) { //月末
                tempMonth = date.month + 1;
                tempDay = 1;
            } else { throw new IllegalStateException("日期大於邊界"); }
        } else if(date.month == 2) { //2月
            if(((date.year % 4 == 0) && (date.year % 100 != 0)) ||
                    (date.year % 400 == 0)) { //閏年
                if(date.day < 29) { tempDay = date.day + 1; } //非月末
                else if(date.day == 29) { //月末
                    tempMonth = date.month + 1;
                    tempDay = 1;
                }
                else { throw new IllegalStateException("日期大於邊界"); }
            } else { //非閏年
                if(date.day < 28) { tempDay = date.day + 1; }
                else if(date.day == 28) {
                    tempMonth = date.month + 1;
                    tempDay = 1;
                }
                else { throw new IllegalStateException("日期大於邊界"); }
            }
        } else { //大月
            if(date.day < 31) { //非月末
                tempDay = date.day + 1;
            } else if(date.day == 31) { //月末
                if(date.month == 12) {
                    tempYear = date.year + 1;
                    tempMonth = 1;
                } else { tempMonth = date.month + 1; }
                tempDay = 1;
            } else { throw new IllegalStateException("日期大於邊界"); }
        }
        Date nextDate = new Date(tempYear, tempMonth, tempDay);
        return nextDate;
    }

    public String getWeekday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(date.year, date.month-1, date.day);

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:    return "Sunday";
            case Calendar.MONDAY:    return "Monday";
            case Calendar.TUESDAY:   return "Tuesday";
            case Calendar.WEDNESDAY: return "Wednesday";
            case Calendar.THURSDAY:  return "Thursday";
            case Calendar.FRIDAY:    return "Friday";
            case Calendar.SATURDAY:  return "Saturday";
            default:
                throw new IllegalStateException("星期錯誤");
        }
    }
}
