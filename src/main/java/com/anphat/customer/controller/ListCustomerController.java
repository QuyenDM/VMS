/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.cms.login.ws.WSCustomer;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomTable;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CommonValueChangeListener;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.data.Property;
import com.vwf5.base.utils.ConditionBean;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class ListCustomerController implements Serializable {

    private final CommonTableFilterPanel panel;
    private CustomPageTableFilter tblCustomer;
    private BeanItemContainer tblContainer;
    private final LinkedHashMap<String, CustomTable.Align> HEADER = BundleUtils.getHeadersFilter("search.customer.header");
    static final String CAPTION = BundleUtils.getString("tbl.caption.list.customer");
    static final String LANG = "cms.common.columnheader.customers";
    static final int SIZE = 10;
    private List<CustomerDTO> lstSearchedDTO;
    private List<AppParamsDTO> lstCustomerStatus;
    private Map<String, String> mapCustomerStatus;

    public ListCustomerController(CommonTableFilterPanel panel) {
        this.panel = panel;
        getDatas();
        initTable();
    }

    private void getDatas() {
        lstCustomerStatus = DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        try {
            mapCustomerStatus = DataUtil.buildHasmap(lstCustomerStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTable() {
        tblCustomer = panel.getMainTable();
        tblCustomer.setMultiSelect(false);
        tblContainer = new BeanItemContainer<>(CustomerDTO.class);
        CommonFunctionTableFilter.initTable(panel, HEADER, tblContainer, CAPTION, 10, LANG);
        CommonUtils.convertFieldAppParamTable(tblCustomer, "status", Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS, mapCustomerStatus);
    }

    private void setData2Table(List<CustomerDTO> lstDatas) {
        tblContainer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstDatas)) {
            tblContainer.addAll(lstDatas);
        }
        CommonFunctionTableFilter.refreshTable(panel, HEADER, tblContainer);
    }

    public void doSearchCustomerDTO(CustomerDTO searchDTO, int maxResult) {
        try {
//            lstSearchedDTO = WSCustomer.getListCustomerDTO(searchDTO, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.CUSTOMER.NAME);
            lstSearchedDTO = WSCustomer.searchCustomers(searchDTO, maxResult);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ListCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (DataUtil.isListNullOrEmpty(lstSearchedDTO)) {
            CommonMessages.showDataNotFound();
        }
        setData2Table(lstSearchedDTO);
    }

    public void doSearchCustomerDTO(List<ConditionBean> lstConditionBeans) {
        try {
            lstSearchedDTO = WSCustomer.getListCustomerByCondition(lstConditionBeans, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.CUSTOMER.NAME);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ListCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (DataUtil.isListNullOrEmpty(lstSearchedDTO)) {
            CommonMessages.showDataNotFound();
        }
        setData2Table(lstSearchedDTO);
    }

    public void doValueChangedListener(Property.ValueChangeListener e) {
        tblCustomer.addValueChangeListener(e);
    }
}
