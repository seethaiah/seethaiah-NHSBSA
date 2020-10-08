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
    And User selects the Yes or No radio button in PartnerClaimBenefit Page "<PartnerClaimBenefit>"
    And User clicks Next button
    And User selects the Yes or No radio button in UniversalCreditClaim Page "<UniversalCreditClaim>"
    And User clicks Next button
    And User selects Yes or No radio button in JointUniversalCredit Page "<JointUniversalCredit>"
    And User clicks Next button
    And User selects Yes or No radio button in CombinedHomePay Page "<CombinedTakeHomepay>"
    When User clicks Next button
    Then User Eligibility is checked and successfull message displayed as "You get help with NHS costs"

    Examples: 
      | Day | Month | Year | Partner | PartnerClaimBenefit | UniversalCreditClaim | JointUniversalCredit | CombinedTakeHomepay |
      |  12 |    08 | 1980 | Yes     | Yes                 | Yes                  | Yes                  | Yes                 |
      |  01 |    08 | 1970 | Yes     | Yes                 | Yes                  | Yes                  | Yes                 |
      |  01 |    01 | 2000 | Yes     | Yes                 | Yes                  | Yes                  | Yes                 |
