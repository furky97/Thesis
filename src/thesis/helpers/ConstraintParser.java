package thesis.helpers;

import java.util.ArrayList;

import crypto.interfaces.ISLConstraint;
import crypto.rules.CrySLArithmeticConstraint;
import crypto.rules.CrySLComparisonConstraint;
import crypto.rules.CrySLConstraint;
import crypto.rules.CrySLRule;
import crypto.rules.CrySLValueConstraint;

/**
 * 
 * @author furkan
 *
 */
public class ConstraintParser {

	
	private ArrayList<CrySLConstraint> normalConstraints;
	private ArrayList<CrySLArithmeticConstraint> arithmeticConstraints;
	private ArrayList<CrySLValueConstraint> valueConstraints;
	private ArrayList<CrySLComparisonConstraint> comparisonConstraints;
	private int number;
	
	
	public ConstraintParser(CrySLRule rule) {
		this.normalConstraints = new ArrayList<CrySLConstraint>();
		this.arithmeticConstraints = new ArrayList<CrySLArithmeticConstraint>();
		this.valueConstraints = new ArrayList<CrySLValueConstraint>();
		this.comparisonConstraints = new ArrayList<CrySLComparisonConstraint>();
		
		this.parseConstraints(rule);
		this.number = this.arithmeticConstraints.size() + this.comparisonConstraints.size() + this.normalConstraints.size() + this.valueConstraints.size();
	}
	
	/**
	 * parses constraints of any given type and fills respective lists
	 * 
	 * @param rule
	 */
	public void parseConstraints(CrySLRule rule) {
		for (ISLConstraint islConstraint : rule.getConstraints()) {
			if (islConstraint instanceof CrySLConstraint) {
				this.normalConstraints.add((CrySLConstraint) islConstraint);
			} else if (islConstraint instanceof CrySLComparisonConstraint) {
				this.comparisonConstraints.add((CrySLComparisonConstraint) islConstraint);
			} else if (islConstraint instanceof CrySLValueConstraint) {
				this.valueConstraints.add((CrySLValueConstraint) islConstraint);
			} else if (islConstraint instanceof CrySLArithmeticConstraint) {
				this.arithmeticConstraints.add((CrySLArithmeticConstraint) islConstraint);
			}
		}
	}
	
	public void extractAlgos(CrySLConstraint cons) {
		if(cons.getLeft() instanceof CrySLValueConstraint) {
			System.out.println(((CrySLValueConstraint) cons.getLeft()).getValueRange());
		} else if(cons.getLeft() instanceof CrySLConstraint) {
			extractAlgos((CrySLConstraint) cons.getLeft());
		}
		if(cons.getRight() instanceof CrySLValueConstraint) {
			System.out.println(((CrySLValueConstraint) cons.getRight()).getValueRange());
		} else if(cons.getRight() instanceof CrySLConstraint) {
			extractAlgos((CrySLConstraint) cons.getRight());
		}
	}
	
	public void extractClasses(CrySLConstraint cons) {
		if(!(cons.getLeft() instanceof CrySLConstraint)) {
			System.out.println(cons.getLeft().getClass());
		} else if(cons.getLeft() instanceof CrySLConstraint) {
			extractClasses((CrySLConstraint) cons.getLeft());
		}
		System.out.println(cons.getOperator());
		if(!(cons.getRight() instanceof CrySLConstraint)) {
			System.out.println(cons.getRight().getClass());
		} else if(cons.getRight() instanceof CrySLConstraint) {
			extractClasses((CrySLConstraint) cons.getRight());
		}
	}

	public ArrayList<ArrayList<String>> extractAlgorithms() {
		ArrayList<ArrayList<String>> algos = new ArrayList<ArrayList<String>>();
		for (CrySLValueConstraint valCon : this.valueConstraints) {
			algos.add((ArrayList<String>) valCon.getValueRange());
		}
		return algos;
	}
	
	public ArrayList<CrySLConstraint> getNormalConstraints() {
		return normalConstraints;
	}

	public ArrayList<CrySLArithmeticConstraint> getArithmeticConstraints() {
		return arithmeticConstraints;
	}

	public ArrayList<CrySLValueConstraint> getValueConstraints() {
		return valueConstraints;
	}

	public ArrayList<CrySLComparisonConstraint> getComparisonConstraints() {
		return comparisonConstraints;
	}
	
	public int getNumber() {
		return this.number;
	}
}
