package intro.fxml;

import jakarta.inject.Singleton;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;

/**
 * The ViewModel is the data representation of the “State” of the GUI. It’s just a POJO with the fields
 * composed of JavaFX Observable types. There’s no logic, or any other code that’s not directly
 * related to sharing the data fields.
 * <p>
 * You can see from the diagram above that all of the other three components have access to the
 * ViewModel. In fact, this is the main dependency in MVCI as the View, the Controller and the
 * Interactor all have it as a dependency. As a POJO, it’s going to consist entirely of a bunch of
 * field declarations, plus all of the accompanying getters and setters to allow other objects to
 * access those fields. I’ve never seen a reason to have any constructor parameters in a ViewModel.
 */
@Singleton
final class ViewModel {

  private final StringProperty property1 = new SimpleStringProperty("");
  private final StringProperty property2 = new SimpleStringProperty("");
  private final BooleanProperty property3 = new SimpleBooleanProperty(false);

  public String getProperty1() {
    return property1.get();
  }

  public StringProperty property1Property() {
    return property1;
  }

  public void setProperty1(String property1) {
    this.property1.set(property1);
  }

  public String getProperty2() {
    return property2.get();
  }

  public StringProperty property2Property() {
    return property2;
  }

  public void setProperty2(String property2) {
    this.property2.set(property2);
  }

  public void bindProperty3(BooleanBinding binding) {
    property3.bind(binding);
  }

  public ObservableBooleanValue property3Property() {
    return property3;
  }

  public boolean getProperty3() {
    return property3.get();
  }
}
