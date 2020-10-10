package com.rebates.dao;

import com.rebates.model.Provider;

import java.util.List;
//Dao benefit: loose coupling,Separation of concerns, service doesn't know the concrete implementation
public interface ProviderDao {
    Provider save (Provider provider);
    Provider update (Provider provider);
    boolean deleteByName(String providerName);
    boolean delete (Provider provider);
    List<Provider> findAllProviders();
    Provider getProviderById(Long id);
}
