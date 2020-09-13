package thesis.helpers;

import java.util.ArrayList;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
	private ArrayList<ArrayList<ISLConstraint>> constraintComponents;
	private ArrayList<ArrayList<String>> operators;
	private int number;
	private CrySLRule rule;
	
	
	public ConstraintParser(CrySLRule rule) {
		this.rule = rule;
		this.normalConstraints = new ArrayList<CrySLConstraint>();
		this.arithmeticConstraints = new ArrayList<CrySLArithmeticConstraint>();
		this.valueConstraints = new ArrayList<CrySLValueConstraint>();
		this.comparisonConstraints = new ArrayList<CrySLComparisonConstraint>();
		
		this.parseConstraints(rule);
		this.number = rule.getConstraints().size();
		this.constraintComponents = createEmptyComponentList();
		this.operators = createEmptyOpsList();
		this.fillCryslComponents();
	}
	
	public ArrayList<ArrayList<ISLConstraint>> getConstraintComponents() {
		return constraintComponents;
	}

	private ArrayList<ArrayList<ISLConstraint>> createEmptyComponentList() {
		ArrayList<ArrayList<ISLConstraint>> res = new ArrayList<ArrayList<ISLConstraint>>(this.number);
		for (int i = 0; i < this.number; i++) {
			res.add(new ArrayList<ISLConstraint>());
		}
		return res;
	}

	private ArrayList<ArrayList<String>> createEmptyOpsList() {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>(this.number);
		for (int i = 0; i < this.number; i++) {
			res.add(new ArrayList<String>());
		}
		return res;
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
	
	
	public void fillCryslComponents() {
		for (int i = 0; i < this.getNormalConstraints().size(); i++) {
			CrySLConstraint cons = this.getNormalConstraints().get(i);
			extractClasses(cons, this.getConstraintComponents(), this.operators, i);
		}
		
	}
	
	/**
	 * analyzes CrySLConstraint recursively, finds nested classes
	 * @param cons
	 */
	public void extractClasses(CrySLConstraint cons, ArrayList<ArrayList<ISLConstraint>> consList, ArrayList<ArrayList<String>> ops, int i) {
		if(!(cons.getLeft() instanceof CrySLConstraint)) {
			consList.get(i).add(cons.getLeft());
		} else if(cons.getLeft() instanceof CrySLConstraint) {
			extractClasses((CrySLConstraint) cons.getLeft(), consList, ops, i);
		}
		ops.get(i).add(cons.getOperator().toString());
		if(!(cons.getRight() instanceof CrySLConstraint)) {
			consList.get(i).add(cons.getRight());
		} else if(cons.getRight() instanceof CrySLConstraint) {
			extractClasses((CrySLConstraint) cons.getRight(), consList, ops, i);
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
	
	public int getNumber() {
		return this.number;
	}

	public ArrayList<ArrayList<String>> getOperators() {
		return this.operators;
	}
}
