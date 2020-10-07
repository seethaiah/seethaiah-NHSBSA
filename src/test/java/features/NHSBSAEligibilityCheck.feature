@FindingEligbility
Feature: Eligibility Checker

  Scenario Outline: Wales Test
    Given User is on Homepage "https://services.nhsbsa.nhs.uk/check-for-help-paying-nhs-costs/start/"
    And User clicks on Start Button
    And User on Where You Live Page with title "Which country do you live in?"
    And User Selects " Wales" Radio button from the list
    And User clicks Next button
    And User enters Date of Birth as "<Day>", "<Month>" ,"<Year>"
    And User clicks Next button
    And User selects the Yes or No radio button as "<Partner>"
    And User clicks Next button
    

    Examples: 
      | Day | Month | Year | Partner |
      |  12 |    08 | 1980 | Yes      |
			|  12 |    08 | 1990 | No      |