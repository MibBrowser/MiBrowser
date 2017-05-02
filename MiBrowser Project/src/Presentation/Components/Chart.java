/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Components;

import Domain.UsageRate;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author guilherme.toniello
 */
public class Chart {

    private JPanel panel;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private XYPlot plot;
    private XYSeries series;
    private ArrayList<String> dates;
    private DefaultXYDataset dataSet;
    private int count = 0;

    public Chart(JPanel chartPanel) {
        this.panel = chartPanel;
        this.dates = new ArrayList<String>();
        this.dataSet = new DefaultXYDataset();
        this.series = new XYSeries("Goals Scored");
        this.chart = ChartFactory.createXYLineChart(
                "", "Data", "Taxa de Uso",
                this.dataSet, PlotOrientation.VERTICAL, true, true, false);
        this.plot = (XYPlot) chart.getPlot();
        this.plot.setBackgroundAlpha(0.5f);
        DefaultXYItemRenderer renderer = new DefaultXYItemRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        this.plot.setRenderer(renderer);
        this.chartPanel = new ChartPanel(this.chart);
        this.chartPanel.setPreferredSize(new Dimension(this.panel.getWidth(), this.panel.getHeight()));
        this.panel.setLayout(new java.awt.BorderLayout());
        this.panel.add(this.chartPanel);
        this.panel.validate();
        this.panel.repaint();
    }

    public void addData(UsageRate data) {
        // update X axis
        this.dates.add("Time " + (this.count + 1));
        String[] array = this.dates.toArray(new String[0]);
        SymbolAxis rangeAxis = new SymbolAxis("Datas", array);
        rangeAxis.setTickUnit(new NumberTickUnit(1));
        rangeAxis.setRange(0, this.dates.size());
        plot.setDomainAxis(rangeAxis);
        // data, valor
        Random random = new Random();
        // series.add(data.getLastRead().getTime(), data.getValor());
        series.add(this.count++, random.nextGaussian() * -1);
        this.dataSet.addSeries("taxa de uso", this.series.toArray());
        this.plot.setDataset(this.dataSet);
    }

    public void clean() {
        this.series.clear();
        this.dates.clear();
        this.count = 0;
        this.dataSet.removeSeries("taxa de uso");
        this.dataSet.addSeries("taxa de uso", this.series.toArray());
        this.plot.setDataset(this.dataSet);
    }
}
