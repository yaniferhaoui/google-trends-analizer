package com.yferhaoui.google_trends_analyser;

import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;

public class CSVReader {

	String cvsSplitBy = ",";
	String pathCSVChilds;
	ListWordTrend listWordTrend;

	public CSVReader(String pathCSVChilds) {
		this.pathCSVChilds = pathCSVChilds;
		this.listWordTrend = new ListWordTrend();
	}

	// First method to call
	public void setListWordTrends(String pathCSVMain) {
		String line = "";
		int nbWords = 0;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(pathCSVMain));
			while ((line = br.readLine()) != null) {
				String[] rowWord = line.split(this.cvsSplitBy);
				if (nbWords > 0) {
					this.listWordTrend.addWord(new WordTrend(rowWord[0], Integer.valueOf(rowWord[1]), rowWord[2]));
				}
				nbWords++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	// Second method to call
	public void setWordsTrend() {
		ListWordTrend localWordTrend = new ListWordTrend();
		for (WordTrend wordTrend : this.listWordTrend.getWordsTrend()) {
			localWordTrend
					.addWord(this.getChildTrend(wordTrend.getName(), wordTrend.getRatio(), wordTrend.getRelation()));
		}
		this.listWordTrend = localWordTrend;
	}

	public WordTrend getChildTrend(String name, Integer ratio, String relation) {
		String line = "";
		int nbWords = 0;
		BufferedReader br = null;
		WordTrend wordTrend = new WordTrend(name, ratio, relation);

		try {
			br = new BufferedReader(new FileReader(this.pathCSVChilds + name + ".csv"));
			while ((line = br.readLine()) != null) {
				String[] row = line.split(this.cvsSplitBy);
				if (nbWords > 0) {
					wordTrend.addValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row[0]),
							Integer.valueOf(row[1]), ratio);
				}
				nbWords++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return wordTrend;
	}

	// Last method to call
	public ListWordTrend getListWordTrend() {
		return this.listWordTrend;
	}

}