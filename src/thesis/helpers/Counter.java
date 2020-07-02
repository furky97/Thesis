package thesis.helpers;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.runtime.CoreException;

import crypto.rules.CrySLMethod;
import crypto.rules.CrySLRule;
import crypto.rules.TransitionEdge;

public class Counter {

	private ArrayList<CrySLRule> rules;
	private HashSet<CrySLMethod> methods;
	private String api, path;

	public Counter(String api, String path) throws MalformedURLException, CoreException {
		RuleReader rr = new RuleReader();
		this.path = path;
		this.api = api;
		this.rules = (ArrayList<CrySLRule>) rr.readRules(path);
		this.parseMethods();
	}

	/**
	 * counts amounts of rules for each case and return hashmap
	 */
	public HashMap<String, Integer> getOccurences() {
		int o, c, f, p, r, u;
		o = c = f = r = p = u = 0;

		for (CrySLRule rule : this.rules) {
			o += rule.getObjects().size();
			c += rule.getConstraints().size();
			f += rule.getForbiddenMethods().size();
			p += rule.getPredicates().size();
			r += rule.getRequiredPredicates().size();
			u += rule.getUsagePattern().getNodes().size();
		}
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Objects", o);
		map.put("Constraints", c);
		map.put("ForbiddenMethods", f);
		map.put("Predicates", p);
		map.put("Required", r);
		map.put("UsagePatterns", u);
		return map;
	}


	/*
	 * returns a list of parameter numbers for all methods
	 */
	public ArrayList<Integer> getNumberOfParameters() {
		ArrayList<Integer> iParams = new ArrayList<Integer>();
		for (CrySLMethod method : this.methods) {
			iParams.add(method.getParameters().size());
		} 
		return iParams;
	}
	
	public int getNumberOfMethods(){
		return this.methods.size();
	}

	/*
	 * returns all methods in CrySLRule (removes duplicates)
	 */
	public void parseMethods() {
		HashSet<CrySLMethod> set = new HashSet<CrySLMethod>();
		for (CrySLRule rule : this.rules) {
			for (TransitionEdge edge : rule.getUsagePattern().getEdges()) {
				for (CrySLMethod method : edge.getLabel()) {
					set.add(method);
				}
			}
		}
		this.methods = set;
	}

	//GETTERS
	public ArrayList<CrySLRule> getRules() {
		return this.rules;
	}
	
	public HashSet<CrySLMethod> getMethods(){
		return this.methods;
	}
}
