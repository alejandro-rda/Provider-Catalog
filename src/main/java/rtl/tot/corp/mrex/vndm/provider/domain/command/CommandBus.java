package rtl.tot.corp.mrex.vndm.provider.domain.command;

import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;

public interface CommandBus {
  
  public boolean executeCreate(Provider object);
  
  public boolean executeUpdate(Provider object);
  
}
