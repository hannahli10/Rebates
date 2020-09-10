package com.rebates.jdbc;

import com.rebates.model.Provider;

import java.util.List;
//Dao benefit: loose coupling,Separation of concerns, service doesn't know the concrete implementation
public interface ProviderJDBCDao {
    Provider save (Provider provider);
    Provider update (Provider provider);
    Boolean deleteByName(String providerName);
    Boolean delete (Provider provider);
    List<Provider> getProviders();
    Provider getProviderById(Long id);
}
