package org.ahp.sqtrl.web.controller;

import java.util.List;
import java.util.Map;

import org.ahp.sqtrl.web.model.QueryManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring controller class to manage query execution requests
 * @author Nicolas Lasolle
 *
 */
@RestController
public class QueryExecutionController {
	@Autowired
	QueryManagerService service;
	
	@CrossOrigin
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ResponseEntity<List<Map<String, String>>> executeQuery(@RequestBody String query) {
		List<Map<String, String>> set = service.getQueryResults(query);
		return new ResponseEntity<List<Map<String, String>>>(set, HttpStatus.OK);
	}

}
