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
  exports intro.fxml;
}