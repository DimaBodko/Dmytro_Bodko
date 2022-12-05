Scenario: Homepage
    Given user is on EPAM homepage
    When user click on EPAM button
    Then user redirected to homepage

  Scenario: Region and language
    Given user is on EPAM homepage
    When user click on Region(Language) button
    And select Region(Language)
    Then user is redirected regional EPAM page

  Scenario: Cookies
    Given user is on homepage
    When user click ACCEPT at the bottom
    Then user's browser will save cookies

  Scenario: Consult and Design sound
    Given user is on Consult and Design page
    When user click on sound button
    Then user listen to video sound

  Scenario: Search
    Given user is on EPAM homepage
    When user click on Search button
    And click writing field
    And write request
    And click on FIND button
    Then user see search result page

  Scenario: Image scaling
    Given user is on EPAM homepage
    When user hover on string or workspace or symbols images
    Then user see scaled images

  Scenario: Required fields
    Given user is on Contact Us
    When user click on SUBMIT button
    Then user see red marked empty required fields
    
  Scenario: Dropping blocks
    Given user is on FAQ page
    When user click on any block with blue "+"
    Then user see corresponding dropped block 
