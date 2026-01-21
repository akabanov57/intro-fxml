package intro.fxml.impl;

import intro.fxml.api.ViewModel;
import jakarta.inject.Singleton;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;

@Singleton
final class ViewModelImpl implements ViewModel {

  private final StringProperty property1 = new SimpleStringProperty("");
  private final StringProperty property2 = new SimpleStringProperty("");
  private final BooleanProperty property3 = new SimpleBooleanProperty(false);
  private final BooleanProperty saveTaskRunning = new SimpleBooleanProperty(false);

  @Override
  public String getProperty1() {
    return property1.get();
  }

  @Override
  public StringProperty property1Property() {
    return property1;
  }

  @Override
  public void setProperty1(String property1) {
    this.property1.set(property1);
  }

  @Override
  public String getProperty2() {
    return property2.get();
  }

  @Override
  public StringProperty property2Property() {
    return property2;
  }

  @Override
  public void setProperty2(String property2) {
    this.property2.set(property2);
  }

  @Override
  public void bindProperty3(BooleanBinding binding) {
    property3.bind(binding);
  }

  @Override
  public ObservableBooleanValue property3Property() {
    return property3;
  }

  @Override
  public boolean getProperty3() {
    return property3.get();
  }

  @Override
  public ObservableBooleanValue saveTaskRunningProperty() {
    return saveTaskRunning;
  }

  @Override
  public boolean getSaveTaskRunning() {
    return saveTaskRunning.get();
  }

  @Override
  public void setSaveTaskRunning(boolean running) {
    saveTaskRunning.set(running);
  }
}
