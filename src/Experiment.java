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

public class Experiment
{
    private static int[] n = {1, 10, 100, 1000, 10000, 20000, 30000,35000, 40000,45000,50000};

    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        DB db = new DB();
        //String request = "DELETE * FROM [Search];";
        String request = "DROP TABLE [Insert];";
        db.update(request);
        //db.update(request);
        request = "DROP TABLE [Search];";
        db.update(request);
        request = "CREATE TABLE Insert (\n" +
                "    Sample_Size int,\n" +
                "    Number_Comparison int\n" +
                ");";
        db.update(request);
        //request = "DELETE * FROM [Insert];";
        request = "CREATE TABLE Search (\n" +
                "    Sample_Size int,\n" +
                "    Number_Comparison int\n" +
                ");";
        db.update(request);
        GenericsKbAVLApp experiment = new GenericsKbAVLApp();
        for (int i : n)
        {
            experiment.experimentSearch(i);
        }
        Graph.main(args);
    }
}