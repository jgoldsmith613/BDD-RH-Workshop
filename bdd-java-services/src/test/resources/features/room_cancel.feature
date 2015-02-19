Feature: Room Cancelations

  # As a user
  # I need to be able to cancel a reserved room
  # In order for someone else to be able to sign up
  Scenario: Cancel a reservation
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the cancel the following reservation:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time | End Time |
    And I expect no error messages

  Scenario: Cancel a non existent reservation - wrong time
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the cancel the following reservation:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:00 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    And I expect the following error messages:
      | Message                                                             |
      | No reservation exists to cancel for room one at the specified time. |

  Scenario: Cancel a non existent reservation - wrong room name
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
      | two  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the cancel the following reservation:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | two       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    And I expect the following error messages:
      | Message                                                             |
      | No reservation exists to cancel for room two at the specified time. |

  Scenario: Cancel a non existent reservation - wrong user
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the cancel the following reservation:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Joe           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    And I expect the following error messages:
      | Message                                                             |
      | No reservation exists to cancel for room one at the specified time. |

  @wip
  Scenario: Cancel a reservation - and Someone else books it
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the cancel the following reservation:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    And I try to book the following rooms:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Joe           | one       | 02/01/2015 10:00 | 02/01/2015 11:00 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Joe           | one       | 02/01/2015 10:00 | 02/01/2015 11:00 |
    And I expect no error messages
