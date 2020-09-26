package com.rebates.dao;

import com.rebates.model.Rebate;

import java.util.List;

public interface RebateDao {
    Rebate save (Rebate rebate);
    Rebate update (Rebate rebate);
    boolean deleteByName(String rebateName);
    boolean delete (Rebate rebate);
    List<Rebate> getRebates();
    Rebate getRebateById(Long id);
}
