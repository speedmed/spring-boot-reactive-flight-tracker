/**
 * 
 */
package com.ninjadevcorner.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ninjadevcorner.model.Flight;
import com.ninjadevcorner.model.FlightState;
import com.ninjadevcorner.service.IFlightService;

import reactor.core.publisher.Mono;

/**
 * @author Mohamed Makkaoui
 * 26 sept. 2018
 */
@Service
public class FlightServiceImpl implements IFlightService{

    @Value("${opensky.root_url}")
    private String root_url;
    @Value("${opensky.all_states}")
    private String allStates;
    @Value("${opensky.all_flights}")
    private String allFlights;

    @Bean
    private WebClient client(){
        return WebClient.create(root_url);
    }
    
	@Override
	public Mono<FlightState> fetchFlightState() {
		
		return client().get()
				.uri(allStates)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(resp -> resp.bodyToMono(FlightState.class));
		
	}

	@Override
	public Mono<Flight[]> fetchFlightsByInterval(int begin, int end) {
		
		return client().get()
				.uri(uriBuilder -> uriBuilder.path(allFlights)
						.queryParam("begin", Integer.toString(begin))
						.queryParam("end", Integer.toString(end))
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(resp -> resp.bodyToMono(Flight[].class));
	}

}
