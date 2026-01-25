package intro.fxml.impl;

import domain.service.api.DomainObject;
import domain.service.api.Service;
import intro.fxml.api.Interactor;
import intro.fxml.api.ViewModel;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javafx.beans.binding.Bindings;

@Singleton
final class InteractorImpl implements Interactor {

  private final AtomicInteger propertyLength = new AtomicInteger(0);

  private final AtomicReference<DomainObject> domainObject = new AtomicReference<>(new DomainObject(""));

  /**
   * The model should only be accessed from FXAT.
   */
  private final ViewModel viewModel;

  private final Service service;

  @Inject
  InteractorImpl(ViewModel viewModel, Service service) {
    this.viewModel = viewModel;
    this.service = service;
    // To call a business method, the interactor needs to know the length of the string.
    this.viewModel
        .property1Property()
        .addListener((_, _, newValue) -> {
          if (!newValue.isEmpty()) {
            updatePropertyLength(newValue.length());
          }
        });
    // We bind properties related to business logic in the interactor. In this case, the example is
    // somewhat contrived.
    this.viewModel.bindProperty3(Bindings.createBooleanBinding(() -> !viewModel
        .getProperty1()
        .isEmpty(), viewModel.property1Property()));
  }

  @Override
  public void beforeStartSave() {
    viewModel.setProperty2("");
    viewModel.setSaveTaskRunning(true);
  }

  @Override
  public void afterRunSave() {
    viewModel.setProperty2(domainObject.get().getSomeValue());
    viewModel.setProperty1("");
    propertyLength.set(0);
    viewModel.setSaveTaskRunning(false);
  }

  @Override
  public void save() {
    // Exactly. Not whenComplete. Block thread here.
    try {
      domainObject.set(service
          .saveDataSomewhereAsync(viewModel.getProperty1() + " --> " + propertyLength.get())
          .get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  private void updatePropertyLength(int value) {
    propertyLength.set(value);
  }
}
