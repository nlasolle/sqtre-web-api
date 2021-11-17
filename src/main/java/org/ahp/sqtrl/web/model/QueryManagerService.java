package org.ahp.sqtrl.web.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ahp.sqtrlengine.dao.JenaWrapper;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "QueryManagerService")
public class QueryManagerService implements InitializingBean {

	@Value("${custom.endpoint}")
	private String endpoint;

	JenaWrapper wrapper;

	public void afterPropertiesSet() throws Exception {
		wrapper = new JenaWrapper(endpoint);
	}

	/**
	 * Get results of a SPARQL query with the form [{id, variable, value}, ...].
	 * @param the SPARQL query to be executed.
	 * @return list of results
	 **/
	public List<Map<String, String>> getQueryResults(String query) {			
		List<Map<String, String>> formattedResults = new ArrayList<>();
		ResultSet results = wrapper.executeRemoteSelectQuery(query, endpoint);

		//Format and save results
		for ( ; results.hasNext() ; ) {
			QuerySolution soln = (QuerySolution) results.next() ;
			Iterator<String> varNames = soln.varNames();
			Map<String, String> currentLine = new HashMap<>();
			//If this resource has already been created

			for(;varNames.hasNext();) {

				String var = varNames.next();
				String value = soln.get(var).toString();

				if(value.contains("@")){
					value = value.substring(0, value.indexOf("@"));
				}
				
				currentLine.put(var, value);

			}

			formattedResults.add(currentLine);

		}



		wrapper.closeExecution();
		return formattedResults;
	}
}
