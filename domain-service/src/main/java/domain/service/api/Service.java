package domain.service.api;

import java.util.concurrent.CompletableFuture;

public interface Service {

  DomainObject saveDataSomewhere(String value);

  CompletableFuture<DomainObject> saveDataSomewhereAsync(String value);
}
