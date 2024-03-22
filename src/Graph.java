package Assignment_2.src;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class Graph
    {
        private static int[] n = {1, 10, 100, 1000, 10000, 20000, 30000,35000, 40000,45000,50000};
        public static void main(String[] args) throws ClassNotFoundException, SQLException
        {
            List<XYChart> charts = new ArrayList<XYChart>();
            charts.add(getChartSearch());
            charts.add(getChartInsert());
            new SwingWrapper<XYChart>(charts).displayChartMatrix();
        }

        public static XYChart getChartInsert()
        {
            // generates Log data
            List<Integer> xData = null;
            List<Integer> yData = null;
            // Create Chart
            XYChart chart = new XYChartBuilder()
                    .width(800)
                    .height(600)
                    .theme(Styler.ChartTheme.Matlab)
                    .title("Insert Operation")
                    .xAxisTitle("N")
                    .yAxisTitle("Number of Comparisons")
                    .build();

            // Customize Chart
            chart.getStyler().setChartTitleVisible(true);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            chart.getStyler().setXAxisLabelRotation(45);
            try
            {
                xData = new ArrayList<Integer>();
                yData = new ArrayList<Integer>();
                DB db = new DB();
                for (int i : n)
                {
                    String statement = "SELECT AVG([Number_Comparison]) AS [Number_Comparison]\n" +
                            "FROM [Insert] \n" +
                            "WHERE Sample_Size = " + i + ";";
                    ResultSet rs = db.query(statement);

                    while (rs.next())
                    {
                        int c = rs.getInt("Number_Comparison");
                        xData.add(c);
                        yData.add(i);
                    }
                }
                // Series
                chart.addSeries("Insert Average Case", yData, xData);
                xData = new ArrayList<Integer>();
                yData = new ArrayList<Integer>();
                for (int i : n)
                {
                    String statement = "SELECT MAX([Number_Comparison]) AS [Number_Comparison]\n" +
                            "FROM [Insert] \n" +
                            "WHERE Sample_Size = " + i + ";";
                    ResultSet rs = db.query(statement);

                    while (rs.next())
                    {
                        int c = rs.getInt("Number_Comparison");
                        xData.add(c);
                        yData.add(i);
                    }
                }
                chart.addSeries("Insert Worst Case", yData, xData);
                xData = new ArrayList<Integer>();
                yData = new ArrayList<Integer>();
                for (int i : n)
                {
                    String statement = "SELECT MIN([Number_Comparison]) AS [Number_Comparison]\n" +
                            "FROM [Insert] \n" +
                            "WHERE Sample_Size = " + i + ";";
                    ResultSet rs = db.query(statement);

                    while (rs.next())
                    {
                        int c = rs.getInt("Number_Comparison");
                        xData.add(c);
                        yData.add(i);
                    }
                }
                chart.addSeries("Insert Best Case", yData, xData);
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e)
            {
            }

            List<Integer> logData = new ArrayList<Integer>();
            for (int i = 0; i < xData.size(); i++) {
                logData.add(2*(int)(Math.round(Math.log(yData.get(i)) / Math.log(2))));
            }
            chart.addSeries("2*LogN",yData,logData);
            chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
            return chart;
        }

        public static XYChart getChartSearch()
        {
            // generates Log data
            List<Integer> xData = null;
            List<Integer> yData = null;
            // Create Chart
            XYChart chart = new XYChartBuilder()
                    .width(800)
                    .height(600)
                    .theme(Styler.ChartTheme.Matlab)
                    .title("Search Operation")
                    .xAxisTitle("N")
                    .yAxisTitle("Number of Comparisons")
                    .build();

            // Customize Chart
            chart.getStyler().setChartTitleVisible(true);
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            chart.getStyler().setXAxisLabelRotation(45);
            try
            {
                xData = new ArrayList<Integer>();
                yData = new ArrayList<Integer>();
                DB db = new DB();
                for (int i : n)
                {
                    String statement = "SELECT AVG([Number_Comparison]) AS [Number_Comparison]\n" +
                            "FROM [Search] \n" +
                            "WHERE Sample_Size = " + i + ";";
                    ResultSet rs = db.query(statement);

                    while (rs.next())
                    {
                        int c = rs.getInt("Number_Comparison");
                        xData.add(c);
                        yData.add(i);
                    }
                }
                // Series
                chart.addSeries("Search Average Case", yData, xData);
                xData = new ArrayList<Integer>();
                yData = new ArrayList<Integer>();
                for (int i : n)
                {
                    String statement = "SELECT MAX([Number_Comparison]) AS [Number_Comparison]\n" +
                            "FROM [Search] \n" +
                            "WHERE Sample_Size = " + i + ";";
                    ResultSet rs = db.query(statement);

                    while (rs.next())
                    {
                        int c = rs.getInt("Number_Comparison");
                        xData.add(c);
                        yData.add(i);
                    }
                }
                chart.addSeries("Search Worst Case", yData, xData);
                xData = new ArrayList<Integer>();
                yData = new ArrayList<Integer>();
                for (int i : n)
                {
                    String statement = "SELECT MIN([Number_Comparison]) AS [Number_Comparison]\n" +
                            "FROM [Search] \n" +
                            "WHERE Sample_Size = " + i + ";";
                    ResultSet rs = db.query(statement);

                    while (rs.next())
                    {
                        int c = rs.getInt("Number_Comparison");
                        xData.add(c);
                        yData.add(i);
                    }
                }
                chart.addSeries("Search Best Case", yData, xData);
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e)
            {
            }

            List<Integer> logData = new ArrayList<Integer>();
            for (int i = 0; i < xData.size(); i++) {
                logData.add(2*(int)(Math.round(Math.log(yData.get(i)) / Math.log(2))));
            }
            chart.addSeries("2*LogN",yData,logData);
            chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
            return chart;
        }
}
