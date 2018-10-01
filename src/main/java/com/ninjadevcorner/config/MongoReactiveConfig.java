/**
 * 
 */
package com.ninjadevcorner.config;

import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

/**
 * @author Med
 * 27 sept. 2018
 */
@EnableReactiveMongoRepositories
public class MongoReactiveConfig extends AbstractReactiveMongoConfiguration {
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "flights-db";
    }
}
