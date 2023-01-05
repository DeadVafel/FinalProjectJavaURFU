import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

public class Graph extends JFrame {

    public Graph(List<String> infoFromDB) {
        initUI(infoFromDB);
    }

    private void initUI(List<String> infoFromDB) {

        CategoryDataset dataset = createDataset(infoFromDB);

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Среднее количество землятресений");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(List<String> informationForGraph) {

        var dataset = new DefaultCategoryDataset();

        for (String s : informationForGraph) {
            var year = s.split("&")[1];
            var averageValue = s.split("&")[0];
            dataset.setValue(Double.parseDouble(averageValue), year, "Годы");
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        return ChartFactory.createBarChart(
                "",
                "",
                "Среднее количество землятресений",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    public static void main(List<String> informationForGraph) {

        EventQueue.invokeLater(() -> {

            var ex = new Graph(informationForGraph);
            ex.setVisible(true);
        });
    }
}

