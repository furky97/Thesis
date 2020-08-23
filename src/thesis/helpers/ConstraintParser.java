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
	
	
	public ConstraintParser(CrySLRule rule) {
		this.normalConstraints = new ArrayList<CrySLConstraint>();
		this.arithmeticConstraints = new ArrayList<CrySLArithmeticConstraint>();
		this.valueConstraints = new ArrayList<CrySLValueConstraint>();
		this.comparisonConstraints = new ArrayList<CrySLComparisonConstraint>();
		
		this.parseConstraints(rule);
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
}
