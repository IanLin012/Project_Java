import java.io.StringReader;

class ReportGenerator {

    private void generateReport(String head, String content, String timeString, String end) {
        System.out.println(head);
        System.out.println(content);
        System.out.println(timeString);
        System.out.println(end);
    }

    public void generateTextReport(String data) {
        String head = ("--- 文字報告 ---");
        String content = ("報告數據：" + data);
        String timeString = ("報告生成時間：" + java.time.LocalDateTime.now());
        String end = ("--- 報告結束 ---");
        generateReport(head, content, timeString, end);
    }

    public void generateCsvReport(String data) {
        String head = (",,, CSV 報告 ,,,");
        String content = ("數據, " + data);
        String timeString = ("生成時間, " + java.time.LocalDateTime.now());
        String end = (",,, 報告結束 ,,,");
        generateReport(head, content, timeString, end);
    }

    public void generateJsonReport(String data) {
        String head = ("{{{ JSON 報告 }}}");
        String content = ("\"data\": \"" + data + "\"");
        String timeString = ("\"time\": \"" + java.time.LocalDateTime.now() + "\"");
        String end = ("{{{ 報告結束 }}}");
        generateReport(head, content, timeString, end);
    }

    public static void main(String[] args) {
        ReportGenerator generator = new ReportGenerator();
        String reportData = "測試數據";
        generator.generateTextReport(reportData);
        generator.generateCsvReport(reportData);
        generator.generateJsonReport(reportData);
    }
}
