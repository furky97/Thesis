package thesis.main;

import java.net.MalformedURLException;

import org.eclipse.core.runtime.CoreException;

import crypto.rules.CrySLRule;
import thesis.helpers.Counter;


public class Main {
	private static String CRYPTO_API_PATH = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle-JCA/src";

	public static void main(String[] args) throws MalformedURLException, CoreException {
		// TODO Auto-generated method stub
		Counter c = new Counter("BouncyCastle-JCA", CRYPTO_API_PATH);
		
		System.out.println("Amount of Rules for BouncyCastle-JCA: " + c.getRules().size());
		System.out.println(c.getOccurences());
		
//		CrySLRule r = c.getRules().get(0);
		
//		System.out.println(r.getConstraints().get(0).getLocation());
	
	}

}
