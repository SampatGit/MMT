#Author Sampat Sahu
Feature: Launch Website
Scenario: Launch MMT website successfully

Given I have the URL for MMT website
When I launch it using Chrome Browser
Then The website should load successfully
