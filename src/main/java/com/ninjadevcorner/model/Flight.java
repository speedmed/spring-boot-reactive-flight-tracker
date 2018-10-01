/**
 * 
 */
package com.ninjadevcorner.model;

import lombok.Data;

/**
 * @author Mohamed Makkaoui
 * 23 sept. 2018
 */
@Data
public class Flight {

	private String icao24;
	private int firstSeen;
	private String estDepartureAirport;
	private int lastSeen;
	private String estArrivalAirport;
	private String callsign;
	private int estDepartureAirportHorizDistance;
	private int estDepartureAirportVertDistance;
	private int estArrivalAirportHorizDistance;
	private int estArrivalAirportVertDistance;
	private int departureAirportCandidatesCount;
	private int arrivalAirportCandidatesCount;
}
