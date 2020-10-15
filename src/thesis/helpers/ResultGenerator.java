package thesis.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ResultGenerator {

	private ArrayList<AverageMetricGenerator> list;
	private ArrayList<ArrayList<Double>> minResults;
	private ArrayList<ArrayList<Double>> maxResults;
	private HashMap<Integer, Integer> rating;
	private int iLibs, iMin, iMax;

	public ResultGenerator(ArrayList<AverageMetricGenerator> list) {
		this.list = list;
		this.iLibs = list.size();
		this.minResults = new ArrayList<ArrayList<Double>>();
		this.maxResults = new ArrayList<ArrayList<Double>>();
		this.rating = new HashMap<Integer, Integer>();

		generateRating();
	}

	private void fillLists() {
		for (AverageMetricGenerator ag : this.list) {
			ArrayList<Double> tmp = new ArrayList<Double>();
			ArrayList<Double> tmp2 = new ArrayList<Double>();
			// add metrics, where the less the better
			tmp.add(ag.getAAEPR());
			tmp.add(ag.getAAEPS());
			tmp.add(ag.getAAPPM());
			tmp.add(ag.getAEPSCons());
			tmp.add((double) ag.getANFM());
			// add metrcis, where the more the better
			tmp2.add(ag.getARAO());
			tmp2.add((double) ag.getACASL());
			this.minResults.add(tmp);
			this.maxResults.add(tmp2);
		}
		this.iMin = this.minResults.get(0).size();
		this.iMax = this.maxResults.get(0).size();
		// fill HashMap
		for (int i = 0; i < iLibs; i++)
			this.rating.put(i, 0);

	}

	private ArrayList<Integer> getIndexPositionsOf(ArrayList<Double> list, double x) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			double d = list.get(i);
			if (x == d)
				res.add(i);
		}
		return res;
	}

	private void generateRating() {
		fillLists();
		// find mins
		for (int i = 0; i < iMin; i++) {
			ArrayList<Double> tmp = new ArrayList<Double>();
			for (int j = 0; j < iLibs; j++) {
				tmp.add(this.minResults.get(j).get(i));
			}
			double min = Collections.min(tmp);
			ArrayList<Integer> minIndexes = getIndexPositionsOf(tmp, min);
			for (Integer k : minIndexes) {
				this.rating.put(k, this.rating.get(k) + 1);
			}
		}
		// find maxes
		for (int i = 0; i < iMax; i++) {
			ArrayList<Double> tmp = new ArrayList<Double>();
			for (int j = 0; j < iLibs; j++) {
				tmp.add(this.maxResults.get(j).get(i));
			}
			double max = Collections.max(tmp);
			ArrayList<Integer> maxIndexes = getIndexPositionsOf(tmp, max);
			for (Integer k : maxIndexes) {
				this.rating.put(k, this.rating.get(k) + 1);
			}
		}
	}

	public String toString() {
		String s = "";
		for (Integer i : this.rating.keySet()) {
			s += "The library rule set with index: " + i + " has " + this.rating.get(i) + " wins.\n";
		}
		return s;
	}

}
