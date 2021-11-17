package org.ahp.sqtrl.web.controller;

import java.util.List;

import org.ahp.sqtrl.web.model.TransformationNodeEntity;
import org.ahp.sqtrl.web.service.RuleManagerService;
import org.ahp.sqtrlengine.model.Prefix;
import org.ahp.sqtrlengine.model.TransformationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring controller class to manage transformation rules (visualization and application)
 * @author Nicolas Lasolle
 *
 */
@RestController
public class RulesController {
	@Autowired
	RuleManagerService service;
	
	@CrossOrigin
	@RequestMapping(value = "/init-process", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public void initProcess(@RequestBody String query) {
		service.initTransformationProcess(query);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rules-load", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public void loadRuleFile(@RequestBody String file) {
		service.loadRuleFile(file);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rules-list", method = RequestMethod.GET)
	public ResponseEntity<List<TransformationRule>> getLists() {
		List<TransformationRule> rules = service.getRules();
		
		return new ResponseEntity<List<TransformationRule>>(rules, HttpStatus.OK);
	}

	
	@CrossOrigin
	@RequestMapping(value = "/prefixes-list", method = RequestMethod.GET)
	public ResponseEntity<List<Prefix>> listPrefixes() {
		List<Prefix> rules = service.getPrefixes();
		
		return new ResponseEntity<List<Prefix>>(rules, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/next-node", method = RequestMethod.GET)
	public ResponseEntity<TransformationNodeEntity> getNextNode(){
		TransformationNodeEntity node = service.getNextNode();
		return new ResponseEntity<TransformationNodeEntity>(node, HttpStatus.OK);
	}

}
