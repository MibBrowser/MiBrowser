/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Components;

import Domain.UsageRate;
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        if (this.series.getItemCount() > 5) {
            this.series.clear();
            this.dates.clear();
            this.series = new XYSeries("Goals Scored");
            this.dataSet.removeSeries(this.series.getKey());
            this.count = 0;
        }
        // update X axis
        Date date = new Date(Math.round(data.getLastRead().getTime()));
        this.dates.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date));
        String[] array = this.dates.toArray(new String[0]);
        SymbolAxis domainAxis = new SymbolAxis("Datas", array);
        domainAxis.setTickUnit(new NumberTickUnit(1.0));
        domainAxis.setRange(0, this.dates.size());
        plot.setDomainAxis(domainAxis);
        series.add(this.count++, data.getValor());
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
