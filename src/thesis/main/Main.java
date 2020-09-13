package thesis.main;

import java.util.ArrayList;

import crypto.interfaces.ISLConstraint;
import crypto.rules.CrySLConstraint;
import crypto.rules.CrySLRule;
import thesis.helpers.MetricsGenerator;
import thesis.helpers.RuleReader;


public class Main {
	private static String CRYPTO_API_1 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle-JCA/src";
	private static String CRYPTO_API_2 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle/src";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CrySLRule> rules = (ArrayList<CrySLRule>) new RuleReader().readRules(CRYPTO_API_1 + "/tmp");
		CrySLRule rule = rules.get(0);
		MetricsGenerator c = new MetricsGenerator(rule);
		
//		System.out.println("Amount of Rules for BouncyCastle-JCA: " + c.getRules().size());
		
//		System.out.println("Number of total Methods: " + c.getNumberOfMethods());
//		System.out.println("Number of total Constraints: " + c.getConstraintParser().getNumber());
		System.out.println("Average EPS (AEPS): " + c.getAEPS());
		System.out.println("Average PPM (APPM): " + c.getAPPM());
		System.out.println("Ratio Accepted Orders (RAO): "+ c.getRAO());
//		System.out.println(c.getRule().getForbiddenMethods());
//		System.out.println(c.getRule().getRequiredPredicates());
//		System.out.println(c.getRule().getPredicates());
		
		
		for (int i=0; i<c.getConstraintParser().getNumber(); i++) {
			System.out.print(c.getConstraintParser().getConstraintComponents().get(i));
			System.out.println(c.getConstraintParser().getOperators().get(i));
		}
		
		
		
		
		
	}

}
