/**
 * 
 */
package com.ninjadevcorner.model;

import java.util.Collection;

import lombok.Data;

/**
 * @author Mohamed Makkaoui
 * 23 sept. 2018
 */
@Data
public class FlightState {
    
	private int time;
	private Collection<StateVector> states;
}
