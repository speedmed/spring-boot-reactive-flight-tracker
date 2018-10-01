/**
 * 
 */
package com.ninjadevcorner.service;

import com.ninjadevcorner.model.Flight;
import com.ninjadevcorner.model.FlightState;

import reactor.core.publisher.Mono;

/**
 * @author Med
 * 26 sept. 2018
 */
public interface IFlightService {

	Mono<FlightState> fetchFlightState();
	
	Mono<Flight[]> fetchFlightsByInterval(int begin, int end);

}
