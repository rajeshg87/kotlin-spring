package com.rajesh.kotlin.stockui;

import com.rajesh.kotlin.stockclient.StockClient;
import com.rajesh.kotlin.stockclient.StockPrices;
import com.rajesh.kotlin.stockclient.WebStockClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ChartController implements Consumer<StockPrices> {

    @FXML
    public LineChart<String, Double> chart;
    private ObservableList<XYChart.Data<String, Double>> seriesData = FXCollections.observableArrayList();
    private StockClient stockClient;

    public ChartController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @FXML
    public void initialize() {
        String symbol="TSLA";
        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        data.add(new XYChart.Series<>(seriesData));
        chart.setData(data);

        stockClient.pricesFor(symbol).subscribe(this);
    }

    @Override
    public void accept(StockPrices stockPrices) {
        Platform.runLater(() -> seriesData.add
                (new XYChart.Data<>(String.valueOf(stockPrices.getTime().getSecond()), stockPrices.getPrice())));
    }
}
