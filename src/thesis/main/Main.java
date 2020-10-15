package thesis.main;

import java.util.ArrayList;

import thesis.helpers.AverageMetricGenerator;
import thesis.helpers.ResultGenerator;


public class Main {
	private static String CRYPTO_API_1 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/JavaCryptographicArchitecture/src";
	private static String CRYPTO_API_2 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/BouncyCastle/src";
	private static String CRYPTO_API_3 = "C:/cygwin64/home/furka/rootDev/repos/Crypto-API-Rules/Tink/src";

	public static void main(String[] args) throws Exception {
		
		ArrayList<AverageMetricGenerator> ags = new ArrayList<AverageMetricGenerator>();
		ags.add(new AverageMetricGenerator(CRYPTO_API_1));
		ags.add(new AverageMetricGenerator(CRYPTO_API_2));
		ags.add(new AverageMetricGenerator(CRYPTO_API_3));
		
		ResultGenerator rg = new ResultGenerator(ags);
		System.out.println(rg);
			
	}

}
