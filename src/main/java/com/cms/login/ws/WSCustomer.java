/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerInfomationDTO;
import com.cms.service.CustomerServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSCustomer {

    List<CustomerDTO> lstCustomerDTO;
    List<CustomerDTO> lstCustomerConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "service";

    //Lay toan bo danh sach kho
    public static List<CustomerDTO> getListCustomerDTO(CustomerDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListCustomerDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<CustomerDTO> getListCustomerByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListCustomerByCondition";
//            //
//            Map<String, Object> mapCustomer = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapCustomer.put("lstCondition", lstCon);
//            mapCustomer.put("rowStart", rowStart);
//            mapCustomer.put("maxRow", maxRow);
//            mapCustomer.put("sortType", sortType);
//            mapCustomer.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapCustomer);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSCustomer.class, "lstCustomerConditionBean")
//                    .alias(parseObject, WSCustomer.class, "ns2:getListCustomerByConditionResponse")
//                    .alias(parseObject, CustomerDTO.class, "ns2:CustomerDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSCustomer wsList = (WSCustomer) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstCustomerConditionBean;
//
//        } catch (Exception e) {
//            throw e;
//        }
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListCustomerByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert Customer
    public static ResultDTO insertCustomer(CustomerDTO departmentDTO) {
//        try {
//            String functionWS = "cms:insertCustomer";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomer = new HashMap<>();
//            mapCustomer.put("departmentDTO", departmentDTO);
//            wsConfig.setBodyArgAlias(mapCustomer);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, ResultDTO.class, "ns2:resultDTO");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            ResultDTO message = (ResultDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return message;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.insertCustomer(departmentDTO);
    }

    //Update Customer
    public static String updateCustomer(CustomerDTO departmentDTO) {
//        try {
//            String functionWS = "cms:updateCustomer";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomer = new HashMap<>();
//            mapCustomer.put("departmentDTO", departmentDTO);
//            wsConfig.setBodyArgAlias(mapCustomer);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, String.class, "ns2:message");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            String message = (String) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return message;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "FAIL";
//        }
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.updateCustomer(departmentDTO);
    }

    //Delete Customer
    public static String deleteCustomer(String id) {
//        try {
//            String functionWS = "cms:deleteCustomer";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomer = new HashMap<>();
//            mapCustomer.put("departmentDTOId", id);
//            wsConfig.setBodyArgAlias(mapCustomer);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, String.class, "ns2:message");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            String message = (String) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return message;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "FAIL";
//        }
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.deleteCustomer(Long.parseLong(id));
    }

    //find Customer by id
    public static CustomerDTO findCustomerById(String id) {
//        try {
//            String functionWS = "cms:findCustomerById";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomer = new HashMap<>();
//            mapCustomer.put("departmentDTOId", id);
//            wsConfig.setBodyArgAlias(mapCustomer);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, CustomerDTO.class, "ns2:department");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            CustomerDTO departmentDTO = (CustomerDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return departmentDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.findCustomerById(Long.parseLong(id));
    }

    // xoa nhieu Customer
    public static String deleteLstCustomer(List<CustomerDTO> lstCustomerDTO) {
//        try {
//            String functionWS = "cms:deleteListCustomer";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomer = new HashMap<>();
//            mapCustomer.put("departmentListDTO", lstCustomerDTO);
//            wsConfig.setBodyArgAlias(mapCustomer);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, String.class, "ns2:message");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            String message = (String) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return message;
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return "FAIL";
//        }
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.deleteListCustomer(lstCustomerDTO);
    }

    // Them moi hoac cap nhat 1 danh sach Customer
    public static String insertOrUpdateListCustomer(List<CustomerDTO> lstCustomerDTO) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.insertOrUpdateListCustomer(lstCustomerDTO);
    }

    // Lay thong tin cua khach hang
    public static CustomerInfomationDTO getCustInfo(String taxCode, String staffCode) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getCustInfo(taxCode, staffCode);
    }

    // Lay thong tin cua khach hang
    public static List<CustomerDTO> searchCustomers(CustomerDTO customerDTO, int maxResult) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.searchCustomers(customerDTO, maxResult);
    }

    public static List<CustomerDTO> getListCustomerFromTermInfo(List<ConditionBean> lstConditions) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListCustomerFromTermInfo(lstConditions);
    }

    public static List<CustomerDTO> getCustomerExisted(List<String> taxCodes) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getCustomerExisted(taxCodes);
    }

}
