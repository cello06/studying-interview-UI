@car_rental @smoke
  Feature: Car Rental Tab

    Background:
      Given the user on the Inar Academy Home page

      Scenario: Validate that user is on the Booking home page
        When the user clicks on the Booking link
        Then the user sees Booking Home page

      Scenario: Validate that Car Rentals head is visible
        When the user clicks on the Booking link
        And the user clicks on the Car rentals tab
        Then the user validates that "Rent a Car for Your Trip" message is visible

      Scenario: Validate that search cars button is functioning properly
        When the user clicks on the Booking link
        And the user clicks on the Car rentals tab
        And the user clicks on the search button
        Then the user validates that car rental page is visible


      Scenario Outline: Validate the information in the booking home page correctly matches with the information in the Car rental page
        When the user clicks on the Booking link
        And the user clicks on the Car rentals tab
        And the user enter "<pick up location>","<pickup_date>","<pickup_hour>","<drop-off_date>" and "<drop_hour>"
        And the user clicks on the search button
        Then the user face with correct "<pickup_date>","<drop-off_date>" and "<pick up location>" in Car Rental filtering page
        Examples:
          | pick up location | pickup_date | pickup_hour | drop-off_date | drop_hour |
          | Heathrow Airport | 03/15/2024  | 08:00       | 03/25/2024    | 20:00     |
          | Taj Mahal        | 04/15/2024  | 09:00       | 04/25/2024    | 21:00     |
          | Grand Bazaar     | 05/15/2024  | 10:00       | 05/25/2024    | 22:00     |
          | Machu Picchu     | 06/15/2024  | 11:00       | 07/25/2024    | 23:00     |

    Scenario Outline: Validate that the user see error message when the user enter invalid inputs
      When the user clicks on the Booking link
      And the user clicks on the Car rentals tab
      And the user enter "<pick up location>","<pickup_date>","<pickup_hour>","<drop-off_date>" and "<drop_hour>"
      And the user clicks on the search button
      Then the user face with "Please select a valid drop-off date after the pickup date and today."
      Examples:
        | pick up location | pickup_date | pickup_hour | drop-off_date | drop_hour |
        | Heathrow Airport | 02/02/2023  | 08:00       | 03/27/2024    | 12:00     |
        | Heathrow Airport | 03/15/2024  | 09:00       | 03/25/2023    | 12:00     |
        | Heathrow Airport | 03/15/2024  | 12:00       | 03/15/2024    | 08:00     |

    Scenario Outline: Validate the Price Range check-boxes are selectable
      When the user clicks on the Booking link
      And the user clicks on the Car rentals tab
      And the user clicks on the search button
      And the user selects the "<price_range>"
      Then the user validates that "<price_range>" is selected
      When the user selects the "<car_specs>"
      Then the user validates that "<car_specs>" is selected
      When the user selects the "<transmission>"
      Then the user validates that "<transmission>" is selected
      When the user selects the "<car_category>"
      Then the user validates that "<car_category>" is selected
      Examples:
        | price_range | car_category | car_specs        | transmission |
        | 0-50        | Small        | Air Conditioning | Automatic    |
        | 200-250     | Large        | GPS Navigation   | Manual       |

    Scenario Outline: Validate that the car in the filtering page matches with entered criteria
      When the user clicks on the Booking link
      And the user clicks on the Car rentals tab
      And the user clicks on the search button
      And the user enters "<pick_up_location>","<price_range>","<car_spec>","<transmission>" and "<car_category>"
      And the user clicks on search button in filtering page
      Then the user validates that "<pick_up_location>","<price_range>","<transmission>" and "<car_category>" matches with displayed cars' information
      Examples:
        | pick_up_location | price_range | car_spec               | transmission | car_category |
        | Heathrow Airport | 0-50        | Bluetooth Connectivity | Manual       | Small        |
        | Central Park     | 50-100      | Leather Seats          | Manual       | Medium       |
        | Grand Bazaar     | 100-150     | Sunroof                | Automatic    | Large        |
        | Taj Mahal        | 50-100      | Keyless Entry          | Automatic    | Minivan      |
        | Machu Picchu     | 50-100      | Backup Camera          | Automatic    | SUV          |

    Scenario: Validate that sort buttons are functioning properly
      When the user clicks on the Booking link
      And the user clicks on the Car rentals tab
      And the user clicks on the search button
      And the user clicks on price highest sort button
      Then the user validates that cars are sorted from highest to lowest
      When the user clicks on price lowest sort button
      Then the user validates that cars are sorted from lowest to highest

    Scenario Outline: Validate that the chosen car in the filtering page can be booked successfully with valid credentials
      When the user clicks on the Booking link
      And the user clicks on the Car rentals tab
      And the user clicks on the search button
      And the user enters "<pick_up_location>","<price_range>","<car_spec>","<transmission>" and "<car_category>"
      And the user clicks on search button in filtering page
      And the user clicks View Dial button of the #1 element
      Then the user validates that the name of selected car is true
      And the user validates that "<pick_up_location>", price, "<transmission>" and "<car_category>" of displayed car in detail_page matches with the selected car in filtering_page
      And the user validates that total_price calculation is true
      When the user clicks on the What is covered button
      Then the user validates that total_price is increased by amount total_cover_price
      When the user clicks on go_to_book button
      Then the user validates that "<pick_up_location>","<transmission>" and "<car_category>" of displayed car in checkout_page matches with the selected car in detail_page
      When the user enters user information "<firstName>","<lastName>","<phoneNumber>","<country>","<address>","<city>", "<postalCode>","<cardholderName>","<cardNumber>","<expirationDate>","<cvv>"
      And the user clicks on the Book_now button
      Then the user validates that booking_successful "Thank you for your booking ! <firstName> <lastName>" is displayed
      And the user validates that "<pick_up_location>" is true
      When the user clicks on the close button
      Then the user sees Booking Home page
      Examples:
        | pick_up_location | price_range | car_spec      | transmission | car_category | firstName | lastName | cardholderName | phoneNumber | cardNumber       | country | address           | city   | postalCode | expirationDate | cvv |
        | Central Park     | 50-100      | Leather Seats | Manual       | Medium       | Gurol     | Gokyar   | Inar Academy   | 5674567839  | 1234123412341234 | Turkey  | Köroğlu mahallesi | Ankara | 06360      | 06/28          | 324 |

    Scenario Outline: Validate element required error messages are displayed in checkout page
      When the user clicks on the Booking link
      And the user clicks on the Car rentals tab
      And the user clicks on the search button
      And the user enters "Heathrow Airport","0-50","Manual" and "Small"
      And the user clicks on search button in filtering page
      And the user clicks View Dial button of the #1 element
      And the user clicks on go_to_book button
      When the user enters user information "<firstName>","<lastName>","<phoneNumber>","<country>","<address>","<city>", "<postalCode>","<cardholderName>","<cardNumber>","<expirationDate>","<cvv>"
      Then the user validates that "<true_error_message>" of the "<element_name>" is displayed
      Examples:
        | firstName  | lastName | phoneNumber | country | address  | city   | postalCode | cardholderName | cardNumber       | expirationDate | cvv | element_name | true_error_message      |
        |            | Akturk   | 1234567891  | Turkey  | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | first name   | First name is required  |
        | Celalettin |          | 1234567891  | Turkey  | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | last name    | Last name is required   |
        | Celalettin | Akturk   | 12          | Turkey  | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | phone number | Invalid phone number    |
        | Celalettin | Akturk   | 1234567891  |         | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | country      | Country is required     |
        | Celalettin | Akturk   | 1234567891  | Turkey  |          | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | address      | Address is required     |
        | Celalettin | Akturk   | 1234567891  | Turkey  | A street |        | 12345      | cello          | 1234123412341234 | 06/28          | 324 | city         | City is required        |
        | Celalettin | Akturk   | 1234567891  | Turkey  | A street | Ankara |            | cello          | 1234123412341234 | 06/28          | 324 | postal code  | Postal code is required |


    Scenario Outline: Validate element invalid input error messages are displayed in checkout page
      When the user clicks on the Booking link
      And the user clicks on the Car rentals tab
      And the user clicks on the search button
      And the user enters "Heathrow Airport","0-50","Manual" and "Small"
      And the user clicks on search button in filtering page
      And the user clicks View Dial button of the #1 element
      And the user clicks on go_to_book button
      When the user enters user information "<firstName>","<lastName>","<phoneNumber>","<country>","<address>","<city>", "<postalCode>","<cardholderName>","<cardNumber>","<expirationDate>","<cvv>"
      Then the user validates that "<element_name>" has invalid input error message
      Examples:
        | firstName  | lastName | phoneNumber | country | address  | city   | postalCode | cardholderName | cardNumber       | expirationDate | cvv | element_name    |
        | &&&&&&&&&& | Akturk   | 1234567891  | Turkey  | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | first name      |
        | Celalettin | &&&&&&   | 1234567891  | Turkey  | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | last name       |
        | Celalettin | Akturk   | 12!!!!!!!!  | Turkey  | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | phone number    |
        | Celalettin | Akturk   | 1234567891  | !!!!!!  | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | country         |
        | Celalettin | Akturk   | 1234567891  | Turkey  | ^^^^^^^^ | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 324 | address         |
        | Celalettin | Akturk   | 1234567891  | Turkey  | A street | $$$$$$ | 12345      | cello          | 1234123412341234 | 06/28          | 324 | city            |
        | Celalettin | Akturk   | 1234567891  | Turkey  | A street | Ankara | %%%%%      | cello          | 1234123412341234 | 06/28          | 324 | postal code     |
        | Celalettin | Akturk   | 1234567891  | Turkey  | A street | Ankara | 12345      | /////          | ?*?*?*?*?*?*?*?* | 06/28          | 324 | card number     |
        | Celalettin | Akturk   | 1234567891  | Turkey  | A street | Ankara | 12345      | cello          | 1234123412341234 | ab/12          | 324 | expiration date |
        | Celalettin | Akturk   | 1234567891  | Turkey  | A street | Ankara | 12345      | cello          | 1234123412341234 | 06/28          | 1   | cvv/cvc         |