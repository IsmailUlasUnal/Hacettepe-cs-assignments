import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.Arrays;

public class ChartManager {

    public void createChart(String chartName, double[][] yArr, String[] names) throws IOException {
        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};

        // Save the char as .png and show it
        showAndSaveChart(chartName, inputAxis, yArr, names);
    }

    private void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, String[] names) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        for (int i = 0; i < yAxis.length; i++)
            chart.addSeries(names[i], doubleX, yAxis[i]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }

    public void drawChartForSort(double[][][] timesForGraphs) throws IOException {
        String[] names = {"selection", "quick", "bubble"};
        createChart("unsorted array sort", timesForGraphs[0], names);
        createChart("sorted array sort", timesForGraphs[1], names);
        createChart("reverse sorted array sort", timesForGraphs[2], names);
    }

    public void drawChartForSearch(double[][] timesForGraphs) throws IOException {
        String[] names = {"linear unsorted", "linear sorted", "binary"};
        createChart("linear and binary search comparison", timesForGraphs, names);
    }




}


