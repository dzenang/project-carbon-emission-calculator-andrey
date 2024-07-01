package org.example.model;

import java.time.LocalDate;

public class EmissionGoal {

  long goalId;
  long userId;
  double targetEmission;
  LocalDate startDate;
  LocalDate endDate;
  String status;
}
