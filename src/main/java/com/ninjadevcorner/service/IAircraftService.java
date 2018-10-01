/**
 * 
 */
package com.ninjadevcorner.service;

import com.ninjadevcorner.model.Aircraft;

import reactor.core.publisher.Mono;

/**
 * @author Med
 * 26 sept. 2018
 */
public interface IAircraftService {
	
	Mono<Aircraft> aircraftDetails(String icao);
}
