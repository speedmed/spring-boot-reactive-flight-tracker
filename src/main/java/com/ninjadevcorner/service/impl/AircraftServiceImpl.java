/**
 * 
 */
package com.ninjadevcorner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjadevcorner.model.Aircraft;
import com.ninjadevcorner.repository.AircraftRepository;
import com.ninjadevcorner.service.IAircraftService;

import reactor.core.publisher.Mono;

/**
 * @author Mohamed Makkaoui
 * 26 sept. 2018
 */
@Service
public class AircraftServiceImpl implements IAircraftService {

	@Autowired
	private AircraftRepository aircraftRepo;
	
	@Override
	public Mono<Aircraft> aircraftDetails(String icao) {
		
		return this.aircraftRepo.getByIcao(icao);
	}

}
