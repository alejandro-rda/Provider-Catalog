package rtl.tot.corp.mrex.prcn.catalog.provider.domain.publisher;

public enum EventType {

  PROVIDER_CREATED("providerCreated"),
  PROVIDER_UPDATED("providerUpdated"), 
  PROVIDER_DELETED("providerDeleted");

  private final String name;

  EventType(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
