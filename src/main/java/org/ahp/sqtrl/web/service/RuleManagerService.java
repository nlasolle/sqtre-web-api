package org.ahp.sqtrl.web.service;


import java.io.File;
import java.util.List;

import org.ahp.sqtrl.web.model.TransformationNodeEntity;
import org.ahp.sqtrlengine.exception.InvalidRuleFileException;
import org.ahp.sqtrlengine.model.Prefix;
import org.ahp.sqtrlengine.model.TransformationNode;
import org.ahp.sqtrlengine.model.TransformationRule;
import org.ahp.sqtrlengine.service.CostBasedTransformationProcess;
import org.ahp.sqtrlengine.service.XMLRuleParser;
import org.ahp.sqtrlengine.utils.RuleUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "RuleManagerService")
public class RuleManagerService implements InitializingBean {
	CostBasedTransformationProcess process;
	
	@Value("${custom.ruleFile}")
	private String defaultRuleFile;
	
	@Value("${custom.endpoint}")
	private String defaultEndpoint;
	
	@Value("${custom.maxCost}")
	private String defaultMaxCost;

	private List<TransformationRule> rules;

	//The default rule file is loaded, other rules files can be loaded 
	public void afterPropertiesSet() throws InvalidRuleFileException {
		loadRuleFile(defaultRuleFile);
	}
	
	//Init a transformation process, which uses pruning to prevent full tree exploring
	public void initTransformationProcess(String query) {
		process = new CostBasedTransformationProcess(Double.parseDouble(defaultMaxCost), rules, query, defaultEndpoint, true);
		process.sortRules();
	}

	public void loadRuleFile(String file) throws InvalidRuleFileException {
		File validFile = new File(defaultRuleFile);
		XMLRuleParser parser = new XMLRuleParser(validFile);
		parser.loadXMLDocument();
		rules = parser.parseRuleFile();
		List<Prefix> prefixes = parser.parsePrefixes();
		RuleUtils.replacePrefixes(rules, prefixes);
	}

	public List<Prefix> getPrefixes() {
		return null;
	}

	public List<TransformationRule> getRules() {
		return rules;
	}

	public TransformationNodeEntity getNextNode() {
		TransformationNode node = process.getNextNode();
		TransformationNodeEntity nodeEntity = new TransformationNodeEntity(node);
		return nodeEntity;
	}

	public void updateSPARQLEndpoint(String endpoint) {
		process.setSparqlEndpoint(endpoint);
	}

}
