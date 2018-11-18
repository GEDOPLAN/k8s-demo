package de.gedoplan.demo;

import java.util.concurrent.Semaphore;

import org.apache.meecrowave.Meecrowave;

public class Main {

  @SuppressWarnings("resource")
  public static void main(String[] args) throws Exception {
    try (Meecrowave meecrowave = new Meecrowave().bake()) {
      new Semaphore(-1).acquire();
    }
  }

}
