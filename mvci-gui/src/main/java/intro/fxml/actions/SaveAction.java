package intro.fxml.actions;

import intro.fxml.api.Interactor;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import javafx.concurrent.Task;

@Singleton
@Named("save")
final class SaveAction implements Runnable {

  private final Interactor interactor;

  @Inject
  SaveAction(Interactor interactor) {
    this.interactor = interactor;
  }

  /**
   * Called from FXAT.
   */
  @Override
  public void run() {
    Task<Void> saveTask = new Task<>() {
      /**
       * Running in the ForkJoinPool.commonPool()
       * @return Void
       */
      @Override
      protected Void call() {
        interactor.save();
        return null;
      }
    };
    CompletableFuture.runAsync(saveTask);
  }
}
