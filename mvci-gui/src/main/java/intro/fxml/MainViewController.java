package intro.fxml;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

@Singleton
final class MainViewController {

  private final ViewModel viewModel;

  private final Interactor interactor;

  @FXML
  private Button saveButton;

  @FXML
  private TextField value1Field;

  @FXML
  private TextField value2Field;

  @Inject
  MainViewController(ViewModel viewModel, Interactor interactor) {
    this.viewModel = viewModel;
    this.interactor = interactor;
  }

  @FXML
  private void initialize() {
    value1Field.textProperty().bindBidirectional(viewModel.property1Property());
    viewModel.property1Property().addListener((_, _, newValue) -> {
      if (!newValue.isEmpty()) {
        interactor.updatePropertyLength(newValue.length());
      }
    });
//    value2Field.textProperty().bindBidirectional(viewModel.property2Property());
    value2Field.textProperty().bind(viewModel.property2Property());
    saveButton.disableProperty().bind(value1Field.textProperty().isEmpty());
  }

  @FXML
  private void onSave(ActionEvent event) {
    Task<Void> saveTask = new Task<>() {

      @Override
      protected Void call() {
        interactor.save();
        return null;
      }
    };
    saveTask.setOnSucceeded(_ -> {
      saveButton.disableProperty().unbind();
      saveButton.disableProperty().bind(value1Field.textProperty().isEmpty());
    });
    saveTask.setOnRunning(_ -> {
      saveButton.disableProperty().unbind();
      saveButton.disableProperty().bind(saveTask.runningProperty());
    });
    CompletableFuture.runAsync(saveTask);
  }
}
