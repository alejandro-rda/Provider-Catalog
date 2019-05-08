package rtl.tot.corp.mrex.vndm.provider.domain.publisher;

public enum EventType {

  PROVIDER_CREATED("CREATE"),
  PROVIDER_UPDATED("UPDATE"), 
  PROVIDER_DELETED("DELETE");

  private final String name;

  EventType(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
