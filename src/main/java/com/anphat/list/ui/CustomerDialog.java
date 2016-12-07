/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.cms.component.CommonDialog;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.GridOneButton;
import com.cms.dto.CustomerStatusDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quyen
 */
public class CustomerDialog extends CommonDialog {

    private CommonTableFilterPanel panelCustomer;
    private final static String TABLE_CAPTION = BundleUtils.getString("customer.dialog.map.table.caption");
    private final static LinkedHashMap<String, CustomTable.Align> HEADER = BundleUtils.getHeadersFilter("customer.status.header");
    private BeanItemContainer container;
    private Map<String, String> mapServices;
    private Map<String, String> mapCustomerServiceStatus;

    public CustomerDialog(Map<String, String> mapServices, Map<String, String> mapCustomerServiceStatus) {
        setInfo("70%", "-1px", BundleUtils.getString("customer.dialog.caption"));
        this.mapServices = mapServices;
        this.mapCustomerServiceStatus = mapCustomerServiceStatus;
        buildMainLayout();
        buildTablePanel();

    }

    private void buildMainLayout() {
        panelCustomer = new CommonTableFilterPanel();
        panelCustomer.getToolbar().setVisible(false);
        mainLayout.addComponent(panelCustomer);
        GridOneButton btnCancel = new GridOneButton(Constants.BUTTON_CANCEL);
        btnCancel.getBtnCommon().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                closeDialog();
            }
        });
        mainLayout.addComponent(btnCancel);
    }

    private void closeDialog() {
        this.close();
    }

    private void buildTablePanel() {
        container = new BeanItemContainer<>(CustomerStatusDTO.class);
        CommonFunctionTableFilter.initTable(panelCustomer, HEADER, container, TABLE_CAPTION, 5, "customerStatusForm");
        CommonUtils.convertFieldAppParamTable(panelCustomer.getMainTable(),
                "service", Constants.APP_PARAMS.SERVICE_TYPE, mapServices);
        CommonUtils.convertFieldAppParamTable(panelCustomer.getMainTable(),
                "status", Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS, mapCustomerServiceStatus);
    }

    /**
     * Truyen du lieu vao cho bang chi tiet
     *
     * @param lstMaps
     */
    public void setData2Table(List<CustomerStatusDTO> lstMaps) {
        container.removeAllItems();
        container.addAll(lstMaps);
        CommonFunctionTableFilter.refreshTable(panelCustomer, HEADER, container);

    }
}
