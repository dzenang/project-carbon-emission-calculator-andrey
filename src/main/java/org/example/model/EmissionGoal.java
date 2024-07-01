package org.example.model;

import java.time.LocalDate;

public class EmissionGoal {

  Long goalId;
  Long userId;
  Double targetEmission;
  LocalDate startDate;
  LocalDate endDate;
  String status;
}
