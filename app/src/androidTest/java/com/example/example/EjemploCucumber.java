package com.example.example;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = {"features"},
        glue = {"com.sniper.bdd.cucumber.steps"},
        tags = {"@e2e", "@smoke"}
)
public class EjemploCucumber {
}