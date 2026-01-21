package intro.fxml;

import intro.fxml.api.ViewModel;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Not an MVC controller. FXML + FXMLController = FXView. It only knows the ViewModel, which is used
 * to bind controls. The FXMLController knows only that handlers related to business logic
 * exist, nothing more. <p>
 * May contain event handlers related to layout.
 */
@Singleton
final class MainViewController {

  private final ViewModel viewModel;

  private final EventHandler<ActionEvent> saveHandler;

  @FXML
  private Button saveButton;

  @FXML
  private TextField value1Field;

  @FXML
  private TextField value2Field;

  @Inject
  MainViewController(ViewModel viewModel, @Named("save") EventHandler<ActionEvent> saveHandler) {
    this.viewModel = viewModel;
    this.saveHandler = saveHandler;
  }

  @FXML
  private void initialize() {
    value1Field
        .textProperty()
        .bindBidirectional(viewModel.property1Property());
//    value2Field.textProperty().bindBidirectional(viewModel.property2Property());
    value2Field
        .textProperty()
        .bind(viewModel.property2Property());
    saveButton
        .disableProperty()
        .bind(Bindings.createBooleanBinding(() -> !viewModel
                .property3Property()
                .get() || viewModel.getSaveTaskRunning(), viewModel.property3Property(),
            viewModel.saveTaskRunningProperty()));
  }

  @FXML
  private void onSave(ActionEvent event) {
    saveHandler.handle(event);
  }
}
