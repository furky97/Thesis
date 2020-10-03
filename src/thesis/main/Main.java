package thesis.main;

import java.io.PrintWriter;
import java.util.ArrayList;

import crypto.interfaces.ISLConstraint;
import crypto.rules.CrySLConstraint;
import crypto.rules.CrySLRule;
import crypto.rules.CrySLValueConstraint;
import thesis.helpers.AverageMetricGenerator;
import thesis.helpers.MetricsGenerator;
import thesis.helpers.RuleReader;


public class Main {
	private static String CRYPTO_API_1 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/JavaCryptographicArchitecture/src";
	private static String CRYPTO_API_2 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle/src";
	private static String CRYPTO_API_3 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/Tink/src";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CrySLRule> rules = (ArrayList<CrySLRule>) new RuleReader().readRules(CRYPTO_API_3);
		ArrayList<AverageMetricGenerator> ags = new ArrayList<AverageMetricGenerator>();
		ags.add(new AverageMetricGenerator(CRYPTO_API_1));
		ags.add(new AverageMetricGenerator(CRYPTO_API_2));
		ags.add(new AverageMetricGenerator(CRYPTO_API_3));
		
		for (AverageMetricGenerator ag : ags) {
			System.out.println("AAPPM: " + ag.getAAPPM());
			System.out.println("AAEPR: " + ag.getAAEPR());
			System.out.println("AAEPS: " + ag.getAAEPS());
			System.out.println("ACASL: " + ag.getACASL());
			System.out.println("ARAO: " + ag.getARAO());
			System.out.println("ANFM: " + ag.getANFM());
			System.out.println();
		}
		
		/*
		PrintWriter writer =  new PrintWriter("test.txt", "UTF-8");
		for (CrySLRule rule : rules) {
			
			MetricsGenerator c = new MetricsGenerator(rule);
			System.out.println(rule.getClassName());
//			writer.println(c.getAEPR());
			writer.println(c.getCASL());
		}
		writer.close();
		*/
	
		
	}

}
