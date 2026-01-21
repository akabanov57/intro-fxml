package intro.fxml.api;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;

/**
 * The ViewModel is the data representation of the “State” of the GUI. It’s just a POJO with the fields
 * composed of JavaFX Observable types. There’s no logic, or any other code that’s not directly
 * related to sharing the data fields.
 */
public interface ViewModel {

  String getProperty1();

  StringProperty property1Property();

  void setProperty1(String property1);

  String getProperty2();

  StringProperty property2Property();

  void setProperty2(String property2);

  void bindProperty3(BooleanBinding binding);

  ObservableBooleanValue property3Property();

  boolean getProperty3();

  ObservableBooleanValue saveTaskRunningProperty();

  boolean getSaveTaskRunning();

  void setSaveTaskRunning(boolean running);
}
