/**
 * 
 */
package com.ninjadevcorner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ninjadevcorner.model.Aircraft;
import com.ninjadevcorner.model.Flight;
import com.ninjadevcorner.model.FlightState;
import com.ninjadevcorner.service.IAircraftService;
import com.ninjadevcorner.service.IFlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Mohamed Makkaoui
 * 26 sept. 2018
 */
@Component
public class FlightRouter {
	
	@Autowired
	private IFlightService flightService;
	@Autowired
	private IAircraftService aircraftService;
	
	@Bean
	public RouterFunction<ServerResponse> router() {
		
		return RouterFunctions
				.route(RequestPredicates.GET("/flightState")
						.and(RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM)), this::flightState)
				.andRoute(RequestPredicates.GET("/flights")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), this::flightsInTimeInterval)
				.andRoute(RequestPredicates.GET("/aircraft/{icao}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), this::aircraftDetails);
	}
	
	private Mono<ServerResponse> flightState(ServerRequest req) {
		
		Mono<FlightState> flightState = this.flightService.fetchFlightState();
		
		return flightState
				.flatMap(f -> {
					System.out.println("Get all Flights");
					return ServerResponse.ok().body(Flux.from(flightState), FlightState.class);
					})
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	private Mono<ServerResponse> flightsInTimeInterval(ServerRequest req) {
		int begin = Integer.valueOf(req.queryParam("begin").get());
		int end = Integer.valueOf(req.queryParam("end").get());
		Mono<Flight[]> flights = this.flightService.fetchFlightsByInterval(begin, end);
		
		return flights
				.flatMap(f -> {
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(flights, Flight[].class);
				})
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	private Mono<ServerResponse> aircraftDetails(ServerRequest req) {
		
		Mono<Aircraft> aircraft = this.aircraftService.aircraftDetails(req.pathVariable("icao"));
		
		return aircraft
				.flatMap(f -> {
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(aircraft, Aircraft.class);
				})
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}
