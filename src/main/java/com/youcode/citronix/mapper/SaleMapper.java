package com.youcode.citronix.mapper;

import com.youcode.citronix.entity.Sale;
import com.youcode.citronix.vm.SaleVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleMapper {

    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);


    SaleVM saleToSaleVM(Sale sale);

    Sale saleVMToSale(SaleVM saleVM);
}
