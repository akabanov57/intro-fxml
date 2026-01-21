package intro.fxml.impl;

import domain.service.api.DomainObject;
import domain.service.api.Service;
import intro.fxml.api.Interactor;
import intro.fxml.api.ViewModel;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.concurrent.ExecutionException;
import javafx.beans.binding.Bindings;

@Singleton
final class InteractorImpl implements Interactor {

  private int propertyLength = 0;

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

  private void beforeStartSave() {
    viewModel.setProperty2("");
    viewModel.setSaveTaskRunning(true);
  }

  private void afterStartSave() {
    viewModel.setProperty1("");
    propertyLength = 0;
    viewModel.setSaveTaskRunning(false);
  }

  @Override
  public void save() {
    beforeStartSave();
    // Exactly. Not whenComplete. Block thread here.
    final DomainObject domainObject;
    try {
      domainObject = service
          .saveDataSomewhereAsync(viewModel.getProperty1() + " --> " + propertyLength)
          .get();
      viewModel.setProperty2(domainObject.getSomeValue());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    afterStartSave();
  }

  private void updatePropertyLength(int value) {
    propertyLength = value;
  }
}
