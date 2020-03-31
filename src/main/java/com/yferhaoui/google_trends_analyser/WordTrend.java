package com.yferhaoui.google_trends_analyser;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Date;

public class WordTrend {

	private String name;
	private String relation;
	private Integer ratio;

	private TreeMap<Date, Integer> values;

	public WordTrend(String name, Integer ratio, String relation) {
		this.relation = relation;
		this.name = name;
		this.ratio = ratio;
		this.values = new TreeMap<Date, Integer>(new DateComparator());
	}

	public void addValue(Date theDate, Integer interest, Integer ratio) {
		if (this.values.get(theDate) == null) {
			this.values.put(theDate, interest * ratio);
		} else {
			this.values.replace(theDate, this.values.get(theDate) + (interest * ratio));
		}

	}

	public void addRatio(Integer ratio) {
		this.ratio += ratio;
	}

	public void upgrade() {
		Double standardDev = this.getSpace();
		Iterator<Entry<Date, Integer>> it = this.values.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Date, Integer> e = it.next();
			Date theDate = e.getKey();
			Integer theCurrentValue = e.getValue();
			this.values.replace(theDate, (int) (theCurrentValue * standardDev));
		}
	}

	public TreeMap<Date, Integer> getTrends() {
		TreeMap<Date, Integer> finishValues = new TreeMap<Date, Integer>(new DateComparator());
		Iterator<Entry<Date, Integer>> it = this.values.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Date, Integer> e = it.next();
			Date theDate = e.getKey();
			Integer theCurrentValue = e.getValue();
			finishValues.put(theDate, theCurrentValue / this.ratio);
		}
		return finishValues;
	}

	public Integer getMaxArray() {
		ArrayList<Integer> theArrayValues = new ArrayList<Integer>(this.getTrends().values());
		Integer max = null;
		for (Integer elem : theArrayValues) {
			if (max == null || elem > max) {
				max = elem;
			}
		}
		return max;
	}

	public Double getSpace() {
		return 100.0 / this.getMaxArray();
	}

	public String getName() {
		return this.name;
	}

	public String getRelation() {
		return this.relation;
	}

	public Integer getRatio() {
		return this.ratio;
	}
}
