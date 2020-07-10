package thesis.helpers;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.runtime.CoreException;

import crypto.interfaces.ISLConstraint;
import crypto.rules.CrySLArithmeticConstraint;
import crypto.rules.CrySLComparisonConstraint;
import crypto.rules.CrySLConstraint;
import crypto.rules.CrySLForbiddenMethod;
import crypto.rules.CrySLMethod;
import crypto.rules.CrySLRule;
import crypto.rules.CrySLValueConstraint;
import crypto.rules.TransitionEdge;
import de.darmstadt.tu.crossing.validation.CrySLValidator;

/**
 * 
 * @author furkan
 *
 */
public class Counter {

	private CrySLRule rule;
	private ArrayList<Integer> numberOfParameters;
	private HashSet<CrySLMethod> methods;

	private ArrayList<CrySLConstraint> normalConstraints;
	private ArrayList<CrySLArithmeticConstraint> arithmeticConstraints;
	private ArrayList<CrySLValueConstraint> valueConstrasints;
	private ArrayList<CrySLComparisonConstraint> comparisonConstrains;

	private ArrayList<CrySLForbiddenMethod> forbiddenMethods;

	/**
	 * Constructor
	 * 
	 * @param rule
	 * @throws MalformedURLException
	 * @throws CoreException
	 */
	public Counter(CrySLRule rule) throws MalformedURLException, CoreException {
		this.rule = rule;
		this.normalConstraints = new ArrayList<CrySLConstraint>();
		this.arithmeticConstraints = new ArrayList<CrySLArithmeticConstraint>();
		this.valueConstrasints = new ArrayList<CrySLValueConstraint>();
		this.comparisonConstrains = new ArrayList<CrySLComparisonConstraint>();

		this.parseMethods();
		this.parseNumberOfParameters();
		this.parseConstraints();
		this.parseForbiddenMethods();
	}

	/**
	 * counts amounts of rules for each case
	 * 
	 * @return HashMap
	 */
	public HashMap<String, Integer> getOccurences() {
		int o, c, f, p, r, u;
		o = c = f = r = p = u = 0;

		o += rule.getObjects().size();
		c += rule.getConstraints().size();
		f += rule.getForbiddenMethods().size();
		p += rule.getPredicates().size();
		r += rule.getRequiredPredicates().size();
		u += rule.getUsagePattern().getNodes().size();

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
	 * parses this.methods to obtain numbers for each method
	 */
	public void parseNumberOfParameters() {
		ArrayList<Integer> iParams = new ArrayList<Integer>();
		for (CrySLMethod method : this.methods) {
			iParams.add(method.getParameters().size());
		}
		this.numberOfParameters = iParams;
	}

	/*
	 * returns all methods in CrySLRule (removes duplicates)
	 */
	public void parseMethods() {
		HashSet<CrySLMethod> set = new HashSet<CrySLMethod>();
		for (TransitionEdge edge : rule.getUsagePattern().getEdges()) {
			for (CrySLMethod method : edge.getLabel()) {
				set.add(method);
			}
		}
		this.methods = set;
	}

	/**
	 * parses constraints of any given type and fills respective lists
	 */
	public void parseConstraints() {
		for (ISLConstraint islConstraint : rule.getConstraints()) {
			if (islConstraint instanceof CrySLConstraint) {
				this.normalConstraints.add((CrySLConstraint) islConstraint);
			} else if (islConstraint instanceof CrySLComparisonConstraint) {
				this.comparisonConstrains.add((CrySLComparisonConstraint) islConstraint);
			} else if (islConstraint instanceof CrySLValueConstraint) {
				this.valueConstrasints.add((CrySLValueConstraint) islConstraint);
			} else if (islConstraint instanceof CrySLArithmeticConstraint) {
				this.arithmeticConstraints.add((CrySLArithmeticConstraint) islConstraint);
			}
		}
	}

	/**
	 * parse Forbidden Methods
	 */
	private void parseForbiddenMethods() {
		this.forbiddenMethods = (ArrayList<CrySLForbiddenMethod>) rule.getForbiddenMethods();
	}

	// GETTERS
	public HashSet<CrySLMethod> getMethods() {
		return this.methods;
	}

	public ArrayList<Integer> getNumberOfParameters() {
		return this.numberOfParameters;
	}

	public int getNumberOfMethods() {
		return this.methods.size();
	}

	public int paramPerMethod() {
		return this.numberOfParameters.stream().mapToInt(Integer::intValue).sum() / this.numberOfParameters.size();
	}

	public ArrayList<CrySLConstraint> getNormalConstraints() {
		return normalConstraints;
	}

	public ArrayList<CrySLArithmeticConstraint> getArithmeticConstraints() {
		return arithmeticConstraints;
	}

	public ArrayList<CrySLValueConstraint> getValueConstrasints() {
		return valueConstrasints;
	}

	public ArrayList<CrySLComparisonConstraint> getComparisonConstrains() {
		return comparisonConstrains;
	}

	public ArrayList<CrySLForbiddenMethod> getForbiddenMethods() {
		return forbiddenMethods;
	}
}
