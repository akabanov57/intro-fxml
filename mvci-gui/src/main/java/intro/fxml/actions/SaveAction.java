package intro.fxml.actions;

import intro.fxml.api.Interactor;
import intro.fxml.api.ViewModel;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import javafx.concurrent.Task;

@Singleton
@Named("save")
final class SaveAction implements Runnable {

  private final ViewModel viewModel;

  private final Interactor interactor;

  @Inject
  SaveAction(ViewModel viewModel, Interactor interactor){
    this.viewModel = viewModel;
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
    // The model update from the interactor side must occur in FXAT.
    saveTask.setOnRunning((_ -> {
      interactor.beforeStartSave();
      viewModel.setSaveTaskRunning(true);
    }));
    // The model update from the interactor side must occur in FXAT.
    saveTask.setOnSucceeded((_ -> {
      viewModel.setSaveTaskRunning(false);
      interactor.afterRunSave();
    }));
    // The model update from the interactor side must occur in FXAT.
    saveTask.setOnFailed((_ -> {
      viewModel.setSaveTaskRunning(false);
      interactor.afterRunSave();
    }));
    CompletableFuture.runAsync(saveTask);
  }
}
