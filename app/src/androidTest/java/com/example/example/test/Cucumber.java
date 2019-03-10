package com.example.example.test;


import cucumber.api.CucumberOptions;

@CucumberOptions(features = "features",
                 glue = {"com.example.example.test"})
public class Cucumber {
}
