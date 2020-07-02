package thesis.main;

import crypto.rules.CrySLRule;
import thesis.helpers.Counter;


public class Main {
	private static String CRYPTO_API_1 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle-JCA/src";
	private static String CRYPTO_API_2 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle/src";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Counter c = new Counter("BouncyCastle-JCA", CRYPTO_API_1 + "/tmp");
		Counter c2 = new Counter("BouncyCastle", CRYPTO_API_2);
		
//		System.out.println("Amount of Rules for BouncyCastle-JCA: " + c.getRules().size());
//		System.out.println(c.getOccurences());
		
		CrySLRule r = c.getRules().get(0);
		System.out.println("Number of total Methods: " + c.getNumberOfMethods());
		System.out.println("Number of total Parameters: " + c.getNumberOfParameters());
	
	}

}
