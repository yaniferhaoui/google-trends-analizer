package com.yferhaoui.google_trends_analyser;

import java.util.Map.Entry;
import java.util.Iterator;
import java.io.File;
import java.util.Date;

public class GraphGenerator {

	private ListWordTrend listWordTrend;

	public GraphGenerator(ListWordTrend listWordTrend) {
		this.listWordTrend = listWordTrend;
	}

	public void drawGraph(WordTrend word, String pathFile) {
		WriteGraphImage writeGraphImage = new WriteGraphImage(word);
		writeGraphImage.drawGraph();
		writeGraphImage.createImage(pathFile);
	}

	public void drawWords() {
		for (WordTrend word : this.listWordTrend.getWordsTrend()) {
			this.drawGraph(word, "Graphs" + File.separator + "word" + File.separator + word.getName() + ".png");
		}
	}

	public void drawRelateds() {
		for (String related : this.listWordTrend.getRelateds()) {
			WordTrend relatedTrade = new WordTrend("Related-" + related, 0, related);
			for (WordTrend word : this.listWordTrend.getWordsTrend(related)) {
				relatedTrade.addRatio(word.getRatio());
				Iterator<Entry<Date, Integer>> it = word.getTrends().entrySet().iterator();
				while (it.hasNext()) {
					Entry<Date, Integer> e = it.next();
					Date theCurrentDate = e.getKey();
					Integer theCurrentValue = e.getValue();
					relatedTrade.addValue(theCurrentDate, theCurrentValue, word.getRatio());
				}
			}
			relatedTrade.upgrade();
			this.drawGraph(relatedTrade,
					"Graphs" + File.separator + "related" + File.separator + relatedTrade.getName() + ".png");
		}
	}

	public void drawTotalMean() {
		WordTrend totalMean = new WordTrend("General", 0, "General");
		for (WordTrend word : this.listWordTrend.getWordsTrend()) {
			totalMean.addRatio(word.getRatio());
			Iterator<Entry<Date, Integer>> it = word.getTrends().entrySet().iterator();
			while (it.hasNext()) {
				Entry<Date, Integer> e = it.next();
				Date theCurrentDate = e.getKey();
				Integer theCurrentValue = e.getValue();
				totalMean.addValue(theCurrentDate, theCurrentValue, word.getRatio());
			}
		}
		totalMean.upgrade();
		this.drawGraph(totalMean, "Graphs" + File.separator + totalMean.getName() + ".png");
	}
}
