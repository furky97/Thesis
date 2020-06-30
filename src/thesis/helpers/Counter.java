package thesis.helpers;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;

import crypto.rules.CrySLRule;

public class Counter {
	
	private ArrayList<CrySLRule> rules; 
	private String api, path;
	
	public Counter(String api, String path) throws MalformedURLException, CoreException {
		RuleReader rr = new RuleReader();
		this.path = path;
		this.api = api;
		this.rules = (ArrayList<CrySLRule>) rr.readRules(path);
	}
	
	/**
	 * counts amounts of rules for each case and return hashmap
	 * */
	public HashMap<String, Integer> getOccurences() {
		int o,c,f,p,r,u; o = c = f = r = p = u = 0;
		
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
	
	public ArrayList<CrySLRule> getRules() {
		return this.rules;
	}
}
