/**
 * 
 */
package com.ninjadevcorner.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * @author Mohamed Makkaoui
 * 23 sept. 2018
 */
@Data
@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public class StateVector {

	private String icao24;
    private String callsign;
    private String originCountry;
    private Double lastPositionUpdate;
    private Double lastContact;
    private Double longitude;
    private Double latitude;
    private Double geoAltitude;
    private boolean onGround;
    private Double velocity;
    private Double heading;
    private Double verticalRate;
    private Set<Integer> sensors;
    private Double baroAltitude;
    private String squawk;
    private boolean spi;
    private PositionSource positionSource;
}

enum PositionSource {
    ADS_B(0),
    ASTERIX(1),
    MLAT(2),
    FLARM(3),
    UNKNOWN(4);

    private final int number;

    PositionSource(final int number) {
        this.number = number;
    }

    @JsonValue
    int getNumber() {
        return this.number;
    }
}
