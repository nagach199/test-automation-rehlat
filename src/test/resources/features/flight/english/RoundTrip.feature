#src/test/resources/features/flight/english/RoundTrip.feature
Feature: Round Trip functionality
  As a User to be able to use Flights functionality
  I want to be able to successfully login to Flights
	
Background:
	Given I am on login page of Flights
	

@flight_healthcheck
Scenario Outline: Validate if the user can sigh-in gmail account and book the ticket in Round Trip
	 When I go to signUp button
	 When I go to login gmail account
     When I go to select Round Radio button
     When I can enter From and To city names RoundTrip
     |fromCity		|<fromCity>|
     |toCity		|<toCity>|
     When I can select date and traveller information RoundTrip
     |adults|<adults>|
     |childs|<childs>|
     |infant|<infant>|
     When I can verify text and select the filter
     When I can verify price details in flightdetails Pop up page in Round Trip
     When I can verify add on check box 
     |addOnBaggage|<addOnBaggage>|
     When I can enter email and coupon code and verify the show more details popUp
     |CouponCodeStatus|<CouponCodeStatus>|
     |EnterCoupon|<EnterCoupon>|
     When I can navigative to traveller Information page
     And I can enter Traveller details
     |adults|<adults>|
     |childs|<childs>|
     |infant|<infant>|
    And I can enter contact details and Click PaySecurel button
    Examples:
	|userName	|passWord	|fromCity	|toCity	|adults|childs|infant|addOnBaggage|CouponCodeStatus|EnterCoupon|
	|******		|****** 	|LON		|HYD	|2		|1	  | 1    |NO			|NO              |		|
	