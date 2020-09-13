package thesis.helpers;

import java.util.HashMap;

public class CryptoSecurityLevel {
	
	
	private HashMap<String, Integer> assignments;
	private HashMap<Integer, String> ref;
	
	
	/**
	 * Constructor
	 */
	public CryptoSecurityLevel() {
		this.assignments = new HashMap<String, Integer>();
		this.ref = new HashMap<Integer, String>();
		assignValues();
	}

	private void assignValues() {
		this.assignments.put("MD5", 2);
		this.assignments.put("RSA", 3);
		this.assignments.put("RC5", 3);
		this.assignments.put("DES", 3);
		this.assignments.put("3DES", 3);
		this.assignments.put("SKIPJACK", 3);
		this.assignments.put("EC", 4);
		this.assignments.put("DH", 4);
		this.assignments.put("ElGamal", 4);
		this.assignments.put("McEliece", 5);
		this.assignments.put("McEliecePointcheval", 5);
		this.assignments.put("McElieceKobaraImai", 5);
		this.assignments.put("McElieceFujisaki", 5);
		this.assignments.put("Blowfish", 2);
		this.assignments.put("Twofish", 4);
		this.assignments.put("Camellia", 4);
		this.assignments.put("Shacal-2", 2);
		this.assignments.put("Shacal2", 2);
		this.assignments.put("Tnepres", 2);
		this.assignments.put("RIJNDAEL", 3);
		this.assignments.put("Serpent", 4);
		this.assignments.put("DHIESwithAES-CBC", 2);
		this.assignments.put("RIJNDAEL", 2);
		this.assignments.put("ECIESwithAES-CBC", 2);
		this.assignments.put("SHA-1", 2);
		this.assignments.put("SHA-2", 3);
		this.assignments.put("SHA-3", 4);
		
		this.ref.put(1, "VW (Very Weak)");
		this.ref.put(2, "W (Weak)");
		this.ref.put(3, "CCS (Conditionally Computationally Secure)");
		this.ref.put(4, "CS (Computationally Secure)");
		this.ref.put(5, "US (Unconditionally Secure)");
		
		
	}
	
	
	
	
}
