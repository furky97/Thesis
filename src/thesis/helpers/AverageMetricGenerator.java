package thesis.helpers;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;

import crypto.rules.CrySLRule;

public class AverageMetricGenerator {
	
	private ArrayList<CrySLRule> rules;
	private ArrayList<MetricsGenerator> metricsGenerators;
	private double AAEPS, AAEPR, AAPPM, ARAO, AEPSCons;
	private int ACASL, ANFM;
	
	public AverageMetricGenerator(String path) throws MalformedURLException, CoreException {
		this.rules = (ArrayList<CrySLRule>) new RuleReader().readRules(path);
		this.metricsGenerators = new ArrayList<MetricsGenerator>();
		this.AAEPR = 0;
		this.AAEPS = 0;
		this.AAPPM = 0;
		this.ARAO = 0;
		this.AEPSCons = 0;
		this.ANFM = 0;
		this.ACASL = 5;
		this.createMetricsGenerators();
		this.createMetrics();
	}
	
	/***
	 * parses MetricsGenerators for each rule in given Library
	 * 
	 * @throws MalformedURLException
	 * @throws CoreException
	 */
	private void createMetricsGenerators() throws MalformedURLException, CoreException {
		for (CrySLRule rule : rules) {
			this.metricsGenerators.add(new MetricsGenerator(rule));
			
		}
	}
	
	private void createMetrics() {
		for (MetricsGenerator mg : this.metricsGenerators) {
			this.AAEPS += mg.getAEPS();
			this.AAEPR += mg.getAEPR();
			this.AAPPM += mg.getAPPM();
			this.ARAO += mg.getRAO();
			this.ANFM += mg.getNFM();
			this.AEPSCons += mg.getConstraintParser().getNumber();
			this.ACASL = Math.min(this.ACASL, mg.getCASL());
		}
		this.AAEPR = this.AAEPR / this.rules.size();
		this.AAPPM = this.AAPPM / this.rules.size();
		this.AAEPS = this.AAEPS / this.rules.size();
		this.ARAO = this.ARAO / this.rules.size();
		this.AEPSCons = this.AEPSCons / this.rules.size();
	}
	
	public double getAEPSCons() {
		return this.AEPSCons;
	}
	
	public double getAAEPS() {
		return this.AAEPS;
	}

	public double getAAEPR() {
		return this.AAEPR;
	}

	public double getAAPPM() {
		return this.AAPPM;
	}
	
	public int getACASL() {
		return this.ACASL;
	}

	public double getARAO() {
		return this.ARAO;
	}

	public int getANFM() {
		return this.ANFM;
	}
}
