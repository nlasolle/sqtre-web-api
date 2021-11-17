package org.ahp.sqtrl.web.model;

import org.ahp.sqtrlengine.model.TransformationNode;

public class TransformationNodeEntity {
	private String id;

	//Direct parent node
	private String parentId;
	
	//Sum of all required transformation costs
	private double globalCost; 
	
	//Local transformation cost (last applied transformation rule cost) 
	private double localCost;

	//starting at 1 for indicating that it is the first state which has been reached
	private int position; 

	private int level;



	private String ruleIri; // Id of the transformation rule;
	private String leftTriples;
	private String rightTriples;

	private String initialQuery;
	private String generatedQuery;

	private String explanation;

	//Adapt the transformation node to keep only relevant fields for application interfaces. (and to enable serialization)
	public TransformationNodeEntity(TransformationNode node) {
		this.level = node.getLevel();
		this.globalCost = node.getGlobalCost();
		this.setLocalCost(node.getGlobalCost() - node.getParentNode().getGlobalCost());
		this.position = node.getPosition();
		this.id = node.getId();
		this.parentId = id.substring(0, id.length()-1);
		this.explanation = node.getApplication().getExplanation();
		this.leftTriples = node.getApplication().getLeftTriples().toString();
		this.rightTriples = node.getApplication().getRightTriples().toString();
		this.initialQuery = node.getApplication().getInitialQuery().toString();
		this.generatedQuery = node.getApplication().getGeneratedQuery().toString();
		this.ruleIri = node.getApplication().getRuleIri();
	}

	public double getGlobalCost() {
		return globalCost;
	}

	public void setGlobalCost(double globalCost) {
		this.globalCost = globalCost;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRuleIri() {
		return ruleIri;
	}

	public void setRuleIri(String ruleIri) {
		this.ruleIri = ruleIri;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getLeftTriples() {
		return leftTriples;
	}

	public void setLeftTriples(String leftTriples) {
		this.leftTriples = leftTriples;
	}

	public String getRightTriples() {
		return rightTriples;
	}

	public void setRightTriples(String rightTriples) {
		this.rightTriples = rightTriples;
	}

	public String getInitialQuery() {
		return initialQuery;
	}

	public void setInitialQuery(String initialQuery) {
		this.initialQuery = initialQuery;
	}

	public String getGeneratedQuery() {
		return generatedQuery;
	}

	public void setGeneratedQuery(String generatedQuery) {
		this.generatedQuery = generatedQuery;
	}

	public double getLocalCost() {
		return localCost;
	}

	public void setLocalCost(double localCost) {
		this.localCost = localCost;
	}

}
