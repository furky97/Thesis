package thesis.main;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;

import crypto.rules.CrySLRule;
import thesis.helpers.RuleReader;


public class Main {
	private static String CRYPTO_API_PATH = "C:/cygwin64/home/furka/rootDev/Thesis/res/repos/Crypto-API-Rules/BouncyCastle-JCA/src";

	public static void main(String[] args) throws MalformedURLException, CoreException {
		// TODO Auto-generated method stub
		RuleReader rr = new RuleReader();
		ArrayList<CrySLRule> list = (ArrayList<CrySLRule>) rr.readRules(CRYPTO_API_PATH);
		System.out.println(list.size());
	}

}
