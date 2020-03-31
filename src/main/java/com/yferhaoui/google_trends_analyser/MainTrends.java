package com.yferhaoui.google_trends_analyser;

import java.io.File;

public class MainTrends {

	public static void main(String[] args) {

		CSVReader csv = new CSVReader("CSV" + File.separator + "csv-google-trends-output" + File.separator);
		csv.setListWordTrends("CSV" + File.separator + "words.csv");
		csv.setWordsTrend();
		ListWordTrend listWordTrend = csv.getListWordTrend();

		GraphGenerator graphG = new GraphGenerator(listWordTrend);
		graphG.drawWords();
		graphG.drawRelateds();
		graphG.drawTotalMean();

	}
}
