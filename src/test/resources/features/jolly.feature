Feature: Assignment on Jolly

  Scenario: User should search
    Given User is on the HomePage
    When User makes a search for a reservation to "Antalya" from "10 Haziran 2020" to "8 Temmuz 2020" for "4" adult and "2" children
    Then User should see "görüntülediniz" on HomePage

  Scenario: User should search with lesser people for the same dates
    Given User is on the HomePage
    When User makes a search for a reservation to "Antalya" from "10 Haziran 2020" to "8 Temmuz 2020" for "1" adult and "1" children
    Then User should see "görüntülediniz" on HomePage