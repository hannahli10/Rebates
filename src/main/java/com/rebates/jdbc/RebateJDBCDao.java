package com.rebates.jdbc;

import com.rebates.model.Rebate;

import java.util.List;

public interface RebateJDBCDao {
    Rebate save (Rebate rebate);
    Rebate update (Rebate rebate);
    Boolean deleteByName(String rebateName);
    Boolean delete (Rebate rebate);
    List<Rebate> getRebates();
    Rebate getRebateById(Long id);
}
