/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerInfomationDTO;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:55 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface CustomerService {

    @WebMethod(operationName = "getListCustomerDTO")
//    @WebResult(name = "customerDTO", targetNamespace = "http://service.cms.com")
    public List<CustomerDTO> getListCustomerDTO(@WebParam(name = "customerDTO") CustomerDTO customerDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateCustomer")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String updateCustomer(@WebParam(name = "customerDTO") CustomerDTO customerDTO);

    //
    @WebMethod(operationName = "deleteCustomer")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteCustomer(@WebParam(name = "customerDTOId") Long id);

    //
    @WebMethod(operationName = "deleteListCustomer")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteListCustomer(@WebParam(name = "customerListDTO") List<CustomerDTO> customerListDTO);

    //
    @WebMethod(operationName = "findCustomerById")
//    @WebResult(name = "customer", targetNamespace = "http://service.cms.com")
    public CustomerDTO findCustomerById(@WebParam(name = "customerDTOId") Long id);

    //
    @WebMethod(operationName = "insertCustomer")
//    @WebResult(name = "resultDTO", targetNamespace = "http://service.cms.com")
    public ResultDTO insertCustomer(@WebParam(name = "customerDTO") CustomerDTO customerDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListCustomer")
//    @WebResult(name = "insertListCustomer", targetNamespace = "http://service.cms.com")
    public String insertOrUpdateListCustomer(@WebParam(name = "customerDTO") List<CustomerDTO> customerDTO);

    //
    @WebMethod(operationName = "getSequenseCustomer")
//    @WebResult(name = "getSequense", targetNamespace = "http://service.cms.com")
    public List<String> getSequenseCustomer(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod(operationName = "getListCustomerByCondition")
//    @WebResult(name = "CustomerDTO", targetNamespace = "http://service.cms.com")
    public List<CustomerDTO> getListCustomerByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "saveOrUpdateAndReturnErrors")
//    @WebResult(name = "CustomerDTO", targetNamespace = "http://service.cms.com")
    public List<CustomerDTO> saveOrUpdateAndReturnErrors(@WebParam(name = "lstCustomer") List<CustomerDTO> lstCustomer);

//    @WebMethod(operationName = "getCustUserInfor")
//    @WebResult(name = "CustomerUserInforDTO", targetNamespace = "http://service.cms.com")
//    public CustomerUserInfoDTO getCustUserInfor(@WebParam(name = "userCode") String userCode);
    @WebMethod(operationName = "getCustInfo")
    public CustomerInfomationDTO getCustInfo(@WebParam(name = "taxCode") String taxCode, @WebParam(name = "staffCode") String staffCode);

    @WebMethod(operationName = "searchCustomers")
    public List<CustomerDTO> searchCustomers(CustomerDTO customerDTO, int maxResult);

    @WebMethod(operationName = "getListCustomerFromTermInfo")
    public List<CustomerDTO> getListCustomerFromTermInfo(List<ConditionBean> lstConditions);
    
    @WebMethod(operationName = "getCustomerExisted")
    public List<CustomerDTO> getCustomerExisted(@WebParam(name = "taxCodes") List<String> taxCodes);
}
