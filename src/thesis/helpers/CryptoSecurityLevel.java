package thesis.helpers;

import java.util.ArrayList;
import java.util.HashMap;

public class CryptoSecurityLevel {

	private HashMap<String, Integer> assignments;
	private HashMap<Integer, String> ref;
	private ArrayList<String> algos;

	/**
	 * Constructor
	 */
	public CryptoSecurityLevel(ArrayList<String> algos) {
		this.assignments = new HashMap<String, Integer>();
		this.ref = new HashMap<Integer, String>();
		this.algos = algos;
		assignValues();
	}

	private void assignValues() {
		this.assignments.put("MD5", 2);
		this.assignments.put("RSA", 3);
		this.assignments.put("RC5", 3);
		this.assignments.put("RC6", 4);
		this.assignments.put("AES", 2);
		this.assignments.put("DES", 2);
		this.assignments.put("3DES", 2);
		this.assignments.put("SKIPJACK", 2);
		this.assignments.put("EC", 4);
		this.assignments.put("DH", 4);
		this.assignments.put("DSA", 4);
		this.assignments.put("ECDSA", 4);
		this.assignments.put("ElGamal", 4);
		this.assignments.put("McEliece", 5);
		this.assignments.put("McEliecePointcheval", 5);
		this.assignments.put("McElieceKobaraImai", 5);
		this.assignments.put("McElieceFujisaki", 5);
		this.assignments.put("FrodoKEM-1344", 5);
		this.assignments.put("FrodoKEM-976", 5);
		this.assignments.put("Twofish", 4);
		this.assignments.put("Camellia", 4);
		this.assignments.put("RIJNDAEL", 4);
		this.assignments.put("Serpent", 4);
		this.assignments.put("SHA-0", 2);
		this.assignments.put("SHA-1", 2);
		this.assignments.put("SHA-2", 4);
		this.assignments.put("SHA-3", 4);

		this.ref.put(1, "VW (Very Weak)");
		this.ref.put(2, "W (Weak)");
		this.ref.put(3, "CCS (Conditionally Computationally Secure)");
		this.ref.put(4, "CS (Computationally Secure)");
		this.ref.put(5, "US (Unconditionally Secure)");

	}

	public int getCASL() {
		// init with non existing value
		int casl = 6;
		for (String algo : this.algos) {
			if (this.assignments.get(algo) != null && this.assignments.get(algo) < casl)
				casl = this.assignments.get(algo);
		}
		return casl;
	}

	public ArrayList<String> getAlgos() {
		return algos;
	}

	public void setAlgos(ArrayList<String> algos) {
		this.algos = algos;
	}

	// GETTERS and SETTERS
	public HashMap<String, Integer> getAssignments() {
		return assignments;
	}

	public void setAssignments(HashMap<String, Integer> assignments) {
		this.assignments = assignments;
	}

	public HashMap<Integer, String> getRef() {
		return ref;
	}

	public void setRef(HashMap<Integer, String> ref) {
		this.ref = ref;
	}

}
