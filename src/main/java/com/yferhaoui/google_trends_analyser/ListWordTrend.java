package com.yferhaoui.google_trends_analyser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListWordTrend {

	private List<WordTrend> listWordTrend;
	private Set<String> relations;

	public ListWordTrend() {
		this.listWordTrend = new ArrayList<WordTrend>();
		this.relations = new HashSet<String>();
	}

	public void addWord(WordTrend wordTrend) {
		this.listWordTrend.add(wordTrend);
		this.relations.add(wordTrend.getRelation());
	}

	public List<WordTrend> getWordsTrend() {
		return this.listWordTrend;
	}

	public List<WordTrend> getWordsTrend(String relation) {
		List<WordTrend> localList = new ArrayList<WordTrend>();
		for (WordTrend word : this.listWordTrend) {
			if (word.getRelation().equals(relation)) {
				localList.add(word);
			}
		}
		return localList;
	}

	public Set<String> getRelateds() {
		return this.relations;
	}

	public Integer getTotalRatio() {
		Integer ratio = 0;
		for (WordTrend word : this.getWordsTrend()) {
			ratio += word.getRatio();
		}
		return ratio;
	}

	public Integer getTotalRatio(String relation) {
		Integer ratio = 0;
		for (WordTrend word : this.getWordsTrend(relation)) {
			ratio += word.getRatio();
		}
		return ratio;
	}
}
