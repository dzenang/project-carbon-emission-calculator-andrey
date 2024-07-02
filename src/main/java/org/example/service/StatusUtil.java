package org.example.service;

import org.example.enums.Status;

public class StatusUtil {

  public static Status fromString(String status) {
    return Status.valueOf(status.toUpperCase());
  }

  public static String toString(Status status) {
    return status.name().toLowerCase();
  }
}
