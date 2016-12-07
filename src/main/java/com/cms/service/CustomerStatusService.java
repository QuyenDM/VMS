/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StatisticsCategoryListDTO;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 5:27 PM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface CustomerStatusService {

    @WebMethod
    public List<CustomerStatusDTO> getListCustomerStatusDTO(@WebParam(name = "customerStatusDTO") CustomerStatusDTO customerStatusDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateCustomerStatus(@WebParam(name = "customerStatusDTO") CustomerStatusDTO customerStatusDTO);

    //
    @WebMethod
    public String deleteCustomerStatus(@WebParam(name = "customerStatusDTOId") Long id);

    //
    @WebMethod
    public String deleteListCustomerStatus(@WebParam(name = "customerStatusListDTO") List<CustomerStatusDTO> customerStatusListDTO);

    //
    @WebMethod
    public CustomerStatusDTO findCustomerStatusById(@WebParam(name = "customerStatusDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertCustomerStatus(@WebParam(name = "customerStatusDTO") CustomerStatusDTO customerStatusDTO);

    //
    @WebMethod
    public String insertOrUpdateListCustomerStatus(@WebParam(name = "customerStatusDTO") List<CustomerStatusDTO> customerStatusDTO);

    //
    @WebMethod
    public List<String> getSequenseCustomerStatus(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<CustomerStatusDTO> getListCustomerStatusByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod
    public List<StatisticsCategoryListDTO> getStatisticsCategoryListByStaff(
            @WebParam(name = "staffCode") String staffCode,
            @WebParam(name = "categoryId") String categoryId,
            @WebParam(name = "beginLastUpdated") String beginLastUpdated,
            @WebParam(name = "endLastUpdated") String endLastUpdated);
}
