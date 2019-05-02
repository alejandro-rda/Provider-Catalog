package rtl.tot.corp.mrex.prcn.catalog.provider.domain.command;

import rtl.tot.corp.mrex.prcn.catalog.provider.domain.entity.Provider;

public interface CommandBus {
  
  public boolean execute(Provider object);
  
  
  
}
