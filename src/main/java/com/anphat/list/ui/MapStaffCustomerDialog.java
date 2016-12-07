/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quyen
 */
public class MapStaffCustomerDialog extends CommonDialog {

    private CommonTableFilterPanel panelTblCustomer;
    private CommonTableFilterPanel panelTblCustomerStatus;
    private GridLayout searchLayout;
    private HorizontalLayout mapLayout;
    private VerticalLayout leftLayout;
    private VerticalLayout rightLayout;
    private VerticalLayout centerLayout;
    private Button btnSearch;
    private Button btnReset;
    private Button btnExecute;
    private Button btnSave;
    private static final String CAPTION = BundleUtils.getString("mapStaffCustomerForm.title");
    private static final String SEARCH_LAYOUT = BundleUtils.getString("customer.management.header.search");

    private ComboBox cbxService;
    private ComboBox cbxStatus;
    private ComboBox cbxProvider;
    private DateField dfFromStartTime;
    private DateField dfToStartTime;
    private DateField dfFromEndTime;
    private DateField dfToEndTime;
    private DateField dfFromDateRegister;
    private DateField dfToDateRegister;
    private ComboBox cbxCity;
    private ComboBox cbxMaxSearch;
    private ComboBox cbxMineName;

    private TextField tfMineName;

    public MapStaffCustomerDialog() {
        buildMainLayout();
    }

    private void buildMainLayout() {
        setInfo("98%", "-1px", CAPTION);
        buildSearchLayout();
        buildMapLayout();
        mainLayout.addComponent(mapLayout);
    }

    private void buildMapLayout() {
        // leftLayout
        mapLayout = new HorizontalLayout();
        mapLayout.setWidth("100%");
        mapLayout.setHeight("-1px");
        mapLayout.setImmediate(true);
        mapLayout.setMargin(true);
        mapLayout.setSpacing(true);
        buildFirstLayout();
        mapLayout.addComponent(leftLayout);
        mapLayout.setExpandRatio(leftLayout, 1.0f);

        // centerLayout
//        buildCenterLayout();
//        mapLayout.addComponent(centerLayout);
//        mapLayout.setComponentAlignment(centerLayout, Alignment.MIDDLE_CENTER);
        // rightLayout
        buildCustomerStatusLayout();
        mapLayout.addComponent(rightLayout);
        mapLayout.setExpandRatio(rightLayout, 1.0f);
    }

    private void buildFirstLayout() {
        // common part: create layout
        leftLayout = new VerticalLayout();
        leftLayout.setImmediate(true);
        leftLayout.setWidth("100.0%");
        leftLayout.setHeight("-1px");
        leftLayout.setMargin(true);
        leftLayout.setSpacing(true);

        // tblLeftLayout
        panelTblCustomer = new CommonTableFilterPanel();
        panelTblCustomer.getToolbar().setVisible(false);
        leftLayout.addComponent(panelTblCustomer);
    }

    private void buildSearchLayout() {
        searchLayout = new GridLayout(4, 3);
        CommonUtils.setBasicAttributeLayout(searchLayout, SEARCH_LAYOUT, true);
        cbxService = CommonUtils.buildComboBox(BundleUtils.getString("customerStatusForm.service"));
        cbxStatus = CommonUtils.buildComboBox(BundleUtils.getString("customerStatusForm.status"));
        dfFromStartTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.fromStartTime"));
        dfToStartTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.toStartTime"));
        dfFromEndTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.fromEndTime"));
        dfToEndTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.toEndTime"));
        dfFromDateRegister = CommonUtils.buildDateField(BundleUtils.getString("term.information.fromDateRegister"));
        dfToDateRegister = CommonUtils.buildDateField(BundleUtils.getString("term.information.toDateRegister"));
        cbxCity = CommonUtils.buildComboBox(BundleUtils.getString("label.taxAuthority"));
        cbxMaxSearch = CommonUtils.buildComboBox(BundleUtils.getString("max.search"));
        cbxMineName = CommonUtils.buildComboBox(BundleUtils.getString("customer.mineName"));
        tfMineName = CommonUtils.buildTextField(BundleUtils.getString("customer.mineName"), 100);
        cbxProvider = CommonUtils.buildComboBox(BundleUtils.getString("term.information.provider"));

        searchLayout.addComponent(cbxService, 0, 0);
        searchLayout.addComponent(cbxProvider, 1, 0);
        searchLayout.addComponent(cbxCity, 2, 0);

        searchLayout.addComponent(cbxMineName, 3, 0);
        searchLayout.addComponent(dfFromStartTime, 0, 1);
        searchLayout.addComponent(dfToStartTime, 1, 1);
        searchLayout.addComponent(dfFromEndTime, 2, 1);
        searchLayout.addComponent(dfToEndTime, 3, 1);
        searchLayout.addComponent(dfFromDateRegister, 0, 2);
        searchLayout.addComponent(dfToDateRegister, 1, 2);
        searchLayout.addComponent(cbxMaxSearch, 2, 2);

        mainLayout.addComponent(searchLayout);

        GridManyButton gridManyButton = new GridManyButton(
                new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH,
                    Constants.BUTTON_INSERT, Constants.BUTTON_SAVE});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnReset = gridManyButton.getBtnCommon().get(1);
        btnExecute = gridManyButton.getBtnCommon().get(2);
        btnExecute.setCaption(BundleUtils.getString("staff.customer.map"));
        btnSave = gridManyButton.getBtnCommon().get(3);
        mainLayout.addComponent(gridManyButton);
    }

    private void buildCenterLayout() {
        // common part: create layout
        centerLayout = new VerticalLayout();
        centerLayout.setImmediate(true);
        centerLayout.setWidth("50px");
        centerLayout.setHeight("-1px");
        centerLayout.setMargin(false);

        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_DEFAULT, Constants.BUTTON_SAVE}, true);
        btnExecute = gridManyButton.getBtnCommon().get(0);
        btnExecute.setCaption(BundleUtils.getString("staff.customer.map"));
        btnSave = gridManyButton.getBtnCommon().get(1);
        centerLayout.addComponent(gridManyButton);
        centerLayout.setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);

    }

    private void buildCustomerStatusLayout() {
        rightLayout = new VerticalLayout();
        rightLayout.setImmediate(true);
        rightLayout.setWidth("100.0%");
        rightLayout.setHeight("-1px");
        rightLayout.setMargin(true);
        rightLayout.setSpacing(true);
        panelTblCustomerStatus = new CommonTableFilterPanel();
        panelTblCustomerStatus.getToolbar().setVisible(false);
        rightLayout.addComponent(panelTblCustomerStatus);
    }

    //Reset all data
    public void doResetData() {
        cbxMineName.clear();
        cbxProvider.clear();
        dfFromStartTime.clear();
        dfToStartTime.clear();
        dfFromEndTime.clear();
        dfToEndTime.clear();
        dfFromDateRegister.clear();
        dfToDateRegister.clear();
        cbxService.clear();
        cbxCity.clear();
        cbxStatus.clear();
    }

    //Get CustomerDTO to Search
    public List<ConditionBean> getLstCondition2Search() {
        List<ConditionBean> lstConditionBeans = new ArrayList<>();
        //Lay ma so thue
//        AppParamsDTO statusDTO = (AppParamsDTO) cbxService.getValue();
//        //Neu du lieu khac null thi set vao doi tuong de tim kiem
//        if (statusDTO != null
//                && !DataUtil.isStringNullOrEmpty(statusDTO.getParCode())) {
//            lstConditionBeans.add(new ConditionBean("status",
//                    statusDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL,
//                    ConditionBean.Type.STRING));
//        }
        String fromStartTime = DataUtil.getDateNullOrZero(dfFromStartTime);
        String toStartTime = DataUtil.getDateNullOrZero(dfToStartTime);
        String fromEndTime = DataUtil.getDateNullOrZero(dfFromEndTime);
        String toEndTime = DataUtil.getDateNullOrZero(dfToEndTime);
        String fromDateRegister = DataUtil.getDateNullOrZero(dfFromDateRegister);
        String toDateRegister = DataUtil.getDateNullOrZero(dfToDateRegister);
        if (!DataUtil.isStringNullOrEmpty(fromStartTime)) {
            lstConditionBeans.add(new ConditionBean("startTime", fromStartTime,
                    ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
        }
        
        if (!DataUtil.isStringNullOrEmpty(toStartTime)) {
            lstConditionBeans.add(new ConditionBean("startTime", toStartTime,
                    ConditionBean.Operator.NAME_LESS_EQUAL, ConditionBean.Type.DATE));
        }

        if (!DataUtil.isStringNullOrEmpty(fromEndTime)) {
            lstConditionBeans.add(new ConditionBean("endTime", fromEndTime,
                    ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
        }
        
        if (!DataUtil.isStringNullOrEmpty(toEndTime)) {
            lstConditionBeans.add(new ConditionBean("endTime", toEndTime,
                    ConditionBean.Operator.NAME_LESS_EQUAL, ConditionBean.Type.DATE));
        }
        
        if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
            lstConditionBeans.add(new ConditionBean("fromDateRegister", fromDateRegister,
                    ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
        }
        
        if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
            lstConditionBeans.add(new ConditionBean("toDateRegister", toDateRegister,
                    ConditionBean.Operator.NAME_LESS_EQUAL, ConditionBean.Type.DATE));
        }
        
        AppParamsDTO providerDTO = (AppParamsDTO) cbxProvider.getValue();
        if (!DataUtil.isNullObject(providerDTO)) {
            String provider = providerDTO.getParCode();
            if (!DataUtil.isStringNullOrEmpty(provider)) {
                lstConditionBeans.add(new ConditionBean("provider", provider,
                        ConditionBean.Operator.NAME_LIKE, ConditionBean.Type.STRING));
            }
        }
        CategoryListDTO mineNameDTO = (CategoryListDTO) cbxMineName.getValue();
        if (mineNameDTO != null && !DataUtil.isStringNullOrEmpty(mineNameDTO.getId())) {
            String mineName = mineNameDTO.getId();
            lstConditionBeans.add(new ConditionBean("mineName", mineName,
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.NUMBER));
        }
        TaxAuthorityDTO taxAuthorityDTO = (TaxAuthorityDTO) cbxCity.getValue();
        if (taxAuthorityDTO != null && !DataUtil.isStringNullOrEmpty(taxAuthorityDTO.getMaCqt())) {
            String taxAuthority = taxAuthorityDTO.getMaCqt();
            lstConditionBeans.add(new ConditionBean("taxAuthority", taxAuthority,
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.NUMBER));
        }

        AppParamsDTO serviceDTO = (AppParamsDTO) cbxService.getValue();
        if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
            lstConditionBeans.add(new ConditionBean("service", serviceDTO.getParCode(),
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }
        AppParamsDTO maxSearchDTO = (AppParamsDTO) cbxMaxSearch.getValue();
        if (maxSearchDTO != null && !DataUtil.isStringNullOrEmpty(maxSearchDTO.getParCode())) {
            lstConditionBeans.add(new ConditionBean("maxSearch", maxSearchDTO.getParCode(),
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }
//        AppParamsDTO city = (AppParamsDTO) cbxCity.getValue();
//        if (city != null && !DataUtil.isStringNullOrEmpty(city.getParCode())) {
//            lstConditionBeans.add(new ConditionBean("city", city.getParCode(),
//                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
//        }

        return lstConditionBeans;
    }

    public CommonTableFilterPanel getPanelTblCustomer() {
        return panelTblCustomer;
    }

    public CommonTableFilterPanel getPanelTblCustomerStatus() {
        return panelTblCustomerStatus;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public Button getBtnExecute() {
        return btnExecute;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public ComboBox getCbxService() {
        return cbxService;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public ComboBox getCbxCity() {
        return cbxCity;
    }

    public ComboBox getCbxMaxSearch() {
        return cbxMaxSearch;
    }

    public ComboBox getCbxMineName() {
        return cbxMineName;
    }

    public ComboBox getCbxProvider() {
        return cbxProvider;
    }

    public void setCbxProvider(ComboBox cbxProvider) {
        this.cbxProvider = cbxProvider;
    }

}
