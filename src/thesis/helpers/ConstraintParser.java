package thesis.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	private ArrayList<String> algos;
	private List<String> noAlgos;
	private int number;
	private CrySLRule rule;

	public ConstraintParser(CrySLRule rule) {
		this.rule = rule;
		this.normalConstraints = new ArrayList<CrySLConstraint>();
		this.arithmeticConstraints = new ArrayList<CrySLArithmeticConstraint>();
		this.valueConstraints = new ArrayList<CrySLValueConstraint>();
		this.comparisonConstraints = new ArrayList<CrySLComparisonConstraint>();
		this.algos = new ArrayList<String>();
		this.noAlgos = Arrays.asList(new String[]{"0","1","2","3","4","5","6","7","8","9", "CBC", "CTR", "CTS", "CFB", "OFB", "CCM", "GCM", "ECB"});

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
		for (ArrayList<ISLConstraint> islList : this.getConstraintComponents()) {
			for (ISLConstraint isl : islList) {
				if (isl instanceof CrySLValueConstraint)
					this.algos.addAll(((CrySLValueConstraint) isl).getValueRange());
			}
		}
		this.algos = filterAlgos(noAlgos);
	}

	/**
	 * analyzes CrySLConstraint recursively, finds nested classes
	 * 
	 * @param cons
	 */
	public void extractClasses(CrySLConstraint cons, ArrayList<ArrayList<ISLConstraint>> consList,
			ArrayList<ArrayList<String>> ops, int i) {
		if (!(cons.getLeft() instanceof CrySLConstraint)) {
			consList.get(i).add(cons.getLeft());
		} else if (cons.getLeft() instanceof CrySLConstraint) {
			extractClasses((CrySLConstraint) cons.getLeft(), consList, ops, i);
		}
		ops.get(i).add(cons.getOperator().toString());
		if (!(cons.getRight() instanceof CrySLConstraint)) {
			consList.get(i).add(cons.getRight());
		} else if (cons.getRight() instanceof CrySLConstraint) {
			extractClasses((CrySLConstraint) cons.getRight(), consList, ops, i);
		}
	}
	
	public ArrayList<String> filterAlgos(List<String> noAlgos2) {
		ArrayList<String> tmp = new ArrayList<String>();
		for (String string : this.algos) {
			if (noAlgos2.contains(string) || string.contains("Padding") || string.isEmpty()) 
				continue;
			else
				tmp.add(string);
		}
		//remove duplicates and return the filtered list
		return (ArrayList<String>) tmp.stream().distinct().collect(Collectors.toList());
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

	public ArrayList<String> getAlgos() {
		return this.algos;
	}

	public ArrayList<ArrayList<String>> getOperators() {
		return this.operators;
	}
}
