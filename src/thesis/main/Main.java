package thesis.main;

import java.util.ArrayList;

import crypto.interfaces.ISLConstraint;
import crypto.rules.CrySLConstraint;
import crypto.rules.CrySLRule;
import crypto.rules.CrySLValueConstraint;
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
		
		System.out.println("Average EPS (AEPS): " + c.getAEPS());
		System.out.println("Average PPM (APPM): " + c.getAPPM());
		System.out.println("Ratio Accepted Orders (RAO): "+ c.getRAO());
		
		
		System.out.println(c.getConstraintParser().getAlgos());
		
		
		
		
		
	}

}
