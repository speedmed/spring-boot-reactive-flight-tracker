# Reactive Programming Use Case: Building a Flight Tracker REST API with Spring Boot and Reactor

Reactive Programming still attracts developer community attention and gains more and more popularity in the last few years because of its ability to change the way of building applications, from imperative to a declarative approach, resulting in a more responsive, resilient applications. So, Reactive Programming aims essentially to create non-blocking applications based on event-driven and asynchronous architecture and simplifying the scalability at the same time. In this tutorial we are going to see a real world reactive example of building a real time flight tracker API using Spring boot and Spring Reactor.


# Explanation Link

You will find the full explanation to implement the project on my Blog.
https://ninjadevcorner.blogspot.com/2018/10/build-reactive-flight-tracker-api-with-spring-boot-and-webflux.html

# Reactive Streams

Reactive Streams describe an API specification that aims to standardize asynchronous stream processing with non-blocking back pressure on the JVM. This specification is defined in the Reactive Manifesto. With the backpressure feature, Reactive Streams allows for the subscriber and publisher to control the data processing rate and help avoiding the out-of-memory problems.
The Reactive Streams API has officially been introduced in Java 9 as java.util.concurrent.Flow.
