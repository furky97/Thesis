package thesis.main;

import java.util.ArrayList;

import crypto.rules.CrySLConstraint;
import crypto.rules.CrySLRule;
import crypto.rules.CrySLValueConstraint;
import fj.data.List;
import thesis.helpers.Counter;
import thesis.helpers.RuleReader;


public class Main {
	private static String CRYPTO_API_1 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle-JCA/src";
	private static String CRYPTO_API_2 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle/src";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CrySLRule> rules = (ArrayList<CrySLRule>) new RuleReader().readRules(CRYPTO_API_1 + "/tmp");
		CrySLRule rule = rules.get(0);
		Counter c = new Counter(rule);
		
//		System.out.println("Amount of Rules for BouncyCastle-JCA: " + c.getRules().size());
//		System.out.println(c.getOccurences());
		
		System.out.println("Number of total Methods: " + c.getNumberOfMethods());
		System.out.println("Number of total Parameters: " + c.getNumberOfParameters());
		System.out.println("Average Parameter/Method: " + c.paramPerMethod());
		
		System.out.println("Constraints: " + c.getValueConstraints());
//		System.out.println("Constraints: " + rule.getPredicates());
//		System.out.println("Constraints: " + ((CrySLValueConstraint)((CrySLConstraint) rule.getConstraints().get(4)).getRight()).getValueRange());
		System.out.println("Constraints: " + c.getArithmeticConstraints());
		System.out.println("Constraints: " + c.getComparisonConstraints());
		System.out.println("Constraints: " + ((CrySLValueConstraint) c.getNormalConstraints().get(2).getRight()).getValueRange());
	
	}

}
