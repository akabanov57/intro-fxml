package intro.fxml.handlers;

import intro.fxml.api.Interactor;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

@Singleton
@Named("save")
final class SaveHandler implements EventHandler<ActionEvent> {

  private final Interactor interactor;

  @Inject
  SaveHandler(Interactor interactor) {
    this.interactor = interactor;
  }

  @Override
  public void handle(ActionEvent event) {
    Task<Void> saveTask = new Task<>() {

      @Override
      protected Void call() {
        interactor.save();
        return null;
      }
    };
    CompletableFuture.runAsync(saveTask);
  }
}
