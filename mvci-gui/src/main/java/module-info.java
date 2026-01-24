module intro.fxml.mvci.gui {
  requires io.micronaut.micronaut_inject;
  requires jakarta.inject;
  requires jakarta.annotation;
  requires transitive javafx.controls;
  requires javafx.fxml;
//  requires javafx.graphics;
  requires micronaut.javafx.launcher;
  requires org.slf4j;
  requires intro.fxml.domain.service;
  opens intro.fxml to javafx.fxml;
  // We have to export to all. We're waiting for micronaut to be modularized.
  exports intro.fxml;
  exports intro.fxml.api;
  exports intro.fxml.impl;
  exports intro.fxml.actions;
}