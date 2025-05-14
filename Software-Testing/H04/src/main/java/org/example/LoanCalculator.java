package org.example;

public class LoanCalculator {
    private LoanCalculator(){} //防止實例化

    public static double calculateInterestRate(
            int loanYears, double loanAmount, boolean isYouth, boolean isMarried) {
        double baseRate = 5.0; //基礎利率
        double discount = 0.0;

        // 年限折扣
        if (loanYears > 20) {
            discount += 1.0;
        } else if (loanYears > 10) {
            discount += 0.5;
        }

        // 金額折扣
        if (loanAmount >= 5_000_000) {
            double over = (loanAmount - 5_000_000);
            discount += Math.ceil(over / 1_000_000) * 0.1;
            System.out.println(discount);
        }

        // 青年折扣
        if (isYouth) {
            discount += 1.0;
        }

        // 已婚折扣
        if (isMarried) {
            discount += 0.5;
        }

        // 最終利率
        double finalRate = baseRate - discount;

        // 最低利率限制
        if (finalRate < 2.0) {
            finalRate = 2.0;
        }

        return finalRate;
    }
}
