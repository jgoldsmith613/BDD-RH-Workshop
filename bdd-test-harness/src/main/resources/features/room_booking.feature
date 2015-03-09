Feature: Room Reservation

  # As a user
  # I need to be able to schedule a room
  # In order to reserve it for my meeting
  @wip
  Scenario: Book single open room
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time | End Time |
    When I try to book the following rooms:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |

  Scenario: Overlap in reservation on the left side
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the following rooms:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Joe           | one       | 02/01/2015 10:00 | 02/01/2015 11:00 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    And I expect the following error messages:
      | Message                                                                     |
      | Room one is booked for part or all of the period you attempted to book for. |

  Scenario: Overlap in reservation on the right side
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the following rooms:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Joe           | one       | 02/01/2015 11:00 | 02/01/2015 12:00 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    And I expect the following error messages:
      | Message                                                                     |
      | Room one is booked for part or all of the period you attempted to book for. |

  Scenario: End time of a reservation is exclusive so one meeting shoud be able to start at the time another one ends
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the following rooms:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Joe           | one       | 02/01/2015 11:30 | 02/01/2015 12:00 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
      | Joe           | one       | 02/01/2015 11:30 | 02/01/2015 12:00 |

  Scenario: Confirm no one else can get room at the same time as me
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the following rooms:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Joe           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    And I expect the following error messages:
      | Message                                                                     |
      | Room one is booked for part or all of the period you attempted to book for. |

  Scenario: Try to book room without conflict - different room has conflict at the same time
    Given the following rooms:
      | Name | Capacity | Tags |
      | one  | 5        |      |
      | two  | 5        |      |
    And the following schedules:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    When I try to book the following rooms:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Joe           | two       | 02/01/2015 10:30 | 02/01/2015 11:30 |
    Then I expect the following schedule to be confirmed:
      | Room Occupant | Room Name | Start Time       | End Time         |
      | Bob           | one       | 02/01/2015 10:30 | 02/01/2015 11:30 |
      | Joe           | two       | 02/01/2015 10:30 | 02/01/2015 11:30 |
