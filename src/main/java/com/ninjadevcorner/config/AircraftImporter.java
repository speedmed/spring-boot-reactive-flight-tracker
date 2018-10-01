/**
 * 
 */
package com.ninjadevcorner.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.ninjadevcorner.model.Aircraft;
import com.ninjadevcorner.repository.AircraftRepository;

/**
 * @author Mohamed Makkaoui
 * 28 sept. 2018
 */
@Component
public class AircraftImporter implements CommandLineRunner {

	private static final Logger LOGGER= LoggerFactory.getLogger(AircraftImporter.class);
	@Autowired
	private AircraftRepository aircraftRepo;
	@Autowired
	private ResourceLoader resourceLoader;
	private List<Aircraft> aircraftList = new ArrayList<Aircraft>();
	private CSVParser parser;
	
	private int recordNumber = 1;
	
	@Override
	public void run(String... args) throws Exception {
		
		Resource resource = resourceLoader.getResource("classpath:aircraftDatabase.csv");
		LOGGER.info("Start importing aircrafts ...");
		InputStream is = resource.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		parser = new CSVParser(br, CSVFormat.EXCEL.withHeader().withTrim());
		
		Iterable<CSVRecord> records = parser.getRecords();
		for (CSVRecord r : records ){
			
			//save just the first 100 records for testing
			if(this.recordNumber > 100){
		    	break;
		    }
			
		    if (!r.get("icao24").isEmpty()){
		    	aircraftList.add(
		                new Aircraft(r.get("icao24"),
		                        r.get("registration"),
		                        r.get("manufacturericao"),
		                        r.get("manufacturername"),
		                        r.get("model"),
		                        r.get("owner"),
		                        r.get("operator"),
		                        r.get("reguntil"),
		                        r.get("engines"),
		                        r.get("built")
		                ));
		    	this.recordNumber++;
		    }
		    
		};
		LOGGER.info("Clear database before inserting ...");
		this.aircraftRepo.deleteAll().subscribe(v -> LOGGER.info("Delete {}", v),
				e -> LOGGER.error("Delete Failed", e),
				() -> LOGGER.info("Clearing Data Complete!"));
		LOGGER.info("Inserting the new aircraft list in database ...");
		this.aircraftRepo.saveAll(aircraftList)
			.subscribe(v -> LOGGER.info("saving {}", v),
						e -> LOGGER.error("Saving Failed", e),
						() -> LOGGER.info("Importing Data Complete!"));
	}

}
