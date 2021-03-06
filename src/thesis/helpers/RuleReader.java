package thesis.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import crypto.cryslhandler.CrySLModelReader;
import crypto.rules.CrySLRule;

/**
 * 
 * @author furkan
 *
 */
public class RuleReader extends CrySLModelReader {

	/**
	 * Constructor
	 * @throws MalformedURLException
	 */
	public RuleReader() throws MalformedURLException {
		super();
	}

	/**
	 * reads rule in given path
	 * 
	 * @param resourcesPath
	 * @return List of CrySLRules
	 * @throws CoreException
	 */
	public List<CrySLRule> readRules(String resourcesPath) throws CoreException {
		List<CrySLRule> rules = new ArrayList<CrySLRule>();
		for (File a : ((new File(resourcesPath)).listFiles())) {
			if (!a.isDirectory() && a.exists() && a.canRead()) {
				CrySLRule rule = readRule(a);
				if (rule != null) {
					rules.add(rule);
				}
			}
		}

		return rules;
	}

}
