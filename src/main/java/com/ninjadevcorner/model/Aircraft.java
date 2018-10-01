/**
 * 
 */
package com.ninjadevcorner.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mohamed Makkaoui
 * 23 sept. 2018
 */
@Data
@AllArgsConstructor
@Document(collection="aircraft")
public class Aircraft {

    @Id
    private String icao;
    private String registration, manufacturericao, manufacturername, model, owner, operator, reguntil, engines, built;
}
