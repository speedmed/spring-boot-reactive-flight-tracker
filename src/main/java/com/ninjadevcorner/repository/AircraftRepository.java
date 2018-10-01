/**
 * 
 */
package com.ninjadevcorner.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.ninjadevcorner.model.Aircraft;

import reactor.core.publisher.Mono;

/**
 * @author Mohamed Makkaoui
 * 26 sept. 2018
 */
public interface AircraftRepository extends ReactiveCrudRepository<Aircraft, Long>{

	Mono<Aircraft> getByIcao(String icao);
}
