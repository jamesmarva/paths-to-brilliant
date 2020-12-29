package ch03;

import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

/**
 * 获取 流中的元素的数量、总和、最大值和平均值
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-02 00:56
 **/
public class Ch03Sec08 {


    public static void main(String[] args) {
        summaryStatisticsDemo00();

    }

    private static void summaryStatisticsDemo00() {
        DoubleSummaryStatistics doubleSummaryStatistics = DoubleStream.generate(Math::random)
                .limit(1000_000)
                .summaryStatistics();
        System.out.println("doubleSummaryStatistics: " + doubleSummaryStatistics);
        System.out.println("min: " + doubleSummaryStatistics.getMax());
        System.out.println("max: " + doubleSummaryStatistics.getMax());
        System.out.println("sum: " + doubleSummaryStatistics.getSum());
    }
}
