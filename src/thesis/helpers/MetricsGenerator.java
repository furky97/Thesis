package thesis.helpers;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.core.runtime.CoreException;

import crypto.rules.CrySLForbiddenMethod;
import crypto.rules.CrySLMethod;
import crypto.rules.CrySLRule;
import crypto.rules.TransitionEdge;

/**
 * 
 * @author furkan
 *
 */
public class MetricsGenerator {

	private CrySLRule rule;
	private ArrayList<Integer> numberOfParameters;
	private HashSet<CrySLMethod> methods;
	private ConstraintParser constraintParser;
	private CryptoSecurityLevel casl;
	private int AEPS, AEPR;

	private ArrayList<CrySLForbiddenMethod> forbiddenMethods;

	/**
	 * Constructor
	 * 
	 * @param rule
	 * @throws MalformedURLException
	 * @throws CoreException
	 */
	public MetricsGenerator(CrySLRule rule) throws MalformedURLException, CoreException {
		this.rule = rule;
		this.constraintParser = new ConstraintParser(rule);
		this.parseMethods();
		this.parseNumberOfParameters();
		this.parseForbiddenMethods();
		this.casl = new CryptoSecurityLevel(constraintParser.getAlgos());
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

	/*
	 * returns Ratio of Accepted Orders (RAO)
	 */
	public double getRAO() {
		int iAll = rule.getUsagePattern().getNodes().size();
		int iAcc = rule.getUsagePattern().getAcceptingStates().size();
		return (double) iAcc / iAll;
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

	public int getCASL() {
		return this.casl.getCASL();
	}

	public ArrayList<Integer> getNumberOfParameters() {
		return this.numberOfParameters;
	}

	public int getNumberOfMethods() {
		return this.methods.size();
	}

	public int getAPPM() {
		return this.numberOfParameters.stream().mapToInt(Integer::intValue).sum() / this.numberOfParameters.size();
	}

	public ArrayList<CrySLForbiddenMethod> getForbiddenMethods() {
		return forbiddenMethods;
	}

	/**
	 * calculate AEPS
	 * count if elements exist within that section
	 * @return
	 */
	public int getAEPS() {
		int div = 0;
		AEPS = (rule.getObjects().size() + rule.getConstraints().size() + rule.getForbiddenMethods().size()
				+ rule.getPredicates().size() + rule.getRequiredPredicates().size()
				+ rule.getUsagePattern().getNodes().size());
		if(rule.getObjects().size()!=0) div+=1;
		if(rule.getConstraints().size()!=0) div+=1;
		if(rule.getForbiddenMethods().size()!=0) div+=1;
		if(rule.getUsagePattern().getNodes().size()!=0) div+=1;
		if(rule.getPredicates().size()!=0) div+=1;
		if(rule.getRequiredPredicates().size()!=0) div+=1;
		return AEPS/div;
	}
	
	public int getAEPR() {
		AEPR = rule.getObjects().size() + rule.getConstraints().size() + rule.getForbiddenMethods().size()
				+ rule.getPredicates().size() + rule.getRequiredPredicates().size()
				+ rule.getUsagePattern().getNodes().size();
		return AEPR;
	}

	public CrySLRule getRule() {
		return rule;
	}

	public ConstraintParser getConstraintParser() {
		return this.constraintParser;
	}
}
