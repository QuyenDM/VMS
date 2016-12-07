/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.SearchCustomerForm;
import com.vaadin.ui.Button;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.StaffDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSStaff;
import com.cms.login.ws.WSTaxAuthority;
import com.cms.utils.ComboComponent;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.server.VaadinSession;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class SearchCustomerController {

    private final SearchCustomerForm searchForm;
    private CustomerDTO searchDTO;
    //Cac thuoc tinh tren form search
    private String taxCode;
    private String name;
    private String telNumber;
    private String email;
    private String officeAddress;
    private String taxAuthority;
    private String taxDepartment;
    private String provider;

    private AppParamsDTO providerDTO;
    private AppParamsDTO statusDTO;
    private List<AppParamsDTO> lstCustStatus;
    private List<AppParamsDTO> lstMaxSearch;
    private List<AppParamsDTO> lstProvider;
    private List<TaxAuthorityDTO> lstTaxAuthority;
    private List<CategoryListDTO> lstCategoryList;
    private List<StaffDTO> lstStaffs;
    private ComboComponent comboBoxUtil;
    private final List<AppParamsDTO> lstAppParamsAll;
    private Button btnReset;
    private String mineName;

    public SearchCustomerController(SearchCustomerForm searchForm, List<AppParamsDTO> lstAppParamsAll) {
        this.searchForm = searchForm;
        this.lstAppParamsAll = lstAppParamsAll;
        getDatas();
        initSearchForm();
        addListenerResetButton();
    }

    //Lay du lieu
    private void getDatas() {
        comboBoxUtil = new ComboComponent();
        //Lay danh sach trang thai khach hang
        lstCustStatus = DataUtil.getListApParams(lstAppParamsAll, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        lstMaxSearch = DataUtil.getListApParams(lstAppParamsAll, Constants.APP_PARAMS.MAX_SEARCH);
        lstProvider = DataUtil.getListApParams(lstAppParamsAll, Constants.APP_PARAMS.PROVIDER);
//        TaxAuthorityDTO taxAuthorityDTO = new TaxAuthorityDTO();
        try {
            lstTaxAuthority = WSTaxAuthority.getListProvineTaxAuthority();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        try {
            lstCategoryList = WSCategoryList.getListCategoryListDTO(categoryListDTO, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            StaffDTO search = new StaffDTO();
            search.setStaffType(Constants.STAFF.STAFF_TYPE_3);
            lstStaffs = WSStaff.getListStaffDTO(search, 0, Constants.INT_100, "asc", "code");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Khoi tao cac gia tri cho combobox
    private void initSearchForm() {
        //Trang thai
        comboBoxUtil.fillDataCombo(searchForm.getStatus(),
                Constants.ALL, Constants.NULL,
                lstCustStatus, Constants.APP_PARAMS.CUSTOMER_STATUS);
        //So luong tim kiem        
        String valueDefaultMaxSearch = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstMaxSearch)) {
            valueDefaultMaxSearch = lstMaxSearch.get(0).getParCode();
        }
        comboBoxUtil.fillDataCombo(searchForm.getCboMaxSearch(),
                Constants.ALL, valueDefaultMaxSearch,
                lstMaxSearch, Constants.APP_PARAMS.MAX_SEARCH);
        comboBoxUtil.fillDataCombo(searchForm.getCboProvider(),
                Constants.ALL, Constants.NULL,
                lstProvider, Constants.APP_PARAMS.PROVIDER);
        comboBoxUtil.setValues(searchForm.getTaxAuthority(), lstTaxAuthority, Constants.TAXAUTHORITY.TEN_CQT, true);
        comboBoxUtil.setValues(searchForm.getCbxMineName(), lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);
        comboBoxUtil.setValues(searchForm.getCboStaff(), lstStaffs, Constants.STAFF.CODE, Boolean.TRUE);
    }

    //An cbo staff neu khong phai admin
    public void setStaff(StaffDTO staff) {
        if (!DataUtil.isAdmin(staff)) {
            for (StaffDTO st : lstStaffs) {
                if (staff.getStaffId().equalsIgnoreCase(st.getStaffId())) {
                    searchForm.getCboStaff().select(st);
                    searchForm.getCboStaff().setVisible(false);
                    break;
                }
            }
        }
    }

    //Thuc hien lay doi tuong tim kiem
    public CustomerDTO getDTO2Search() {
        searchDTO = new CustomerDTO();
        //Lay ma so thue
        taxCode = searchForm.getTaxCode().getValue();
        name = searchForm.getName().getValue();
        telNumber = searchForm.getTelNumber().getValue();
        email = searchForm.getEmail().getValue();
//        officeAddress = searchForm.getDeployAddress().getValue();
//        taxAuthority = searchForm.getDeployAddress().getValue();
//        officeAddress = searchForm.getDeployAddress().getValue();
//        mineName = searchForm.getMineName().getValue();
//        mineName = searchForm.getCbxMineName().getValue();
        statusDTO = (AppParamsDTO) searchForm.getStatus().getValue();
        //Neu du lieu khac null thi set vao doi tuong de tim kiem
        if (statusDTO != null
                && !DataUtil.isStringNullOrEmpty(statusDTO.getParCode())) {
            searchDTO.setStatus(statusDTO.getParCode());
        }
        //Lay du lieu nha cung cap
        providerDTO = (AppParamsDTO) searchForm.getCboProvider().getValue();
        if (providerDTO != null
                && !DataUtil.isStringNullOrEmpty(providerDTO.getParCode())) {
            searchDTO.setProvider(providerDTO.getParCode());
        }
        if (!DataUtil.isStringNullOrEmpty(taxCode)) {
            searchDTO.setTaxCode(taxCode);
        }
        if (!DataUtil.isStringNullOrEmpty(name)) {
            searchDTO.setName(name);
        }
        if (!DataUtil.isStringNullOrEmpty(telNumber)) {
            searchDTO.setTelNumber(telNumber);
        }
        if (!DataUtil.isStringNullOrEmpty(email)) {
            searchDTO.setEmail(email);
        }
        CategoryListDTO categoryList = (CategoryListDTO) searchForm.getCbxMineName().getValue();
        if (!DataUtil.isNullObject(categoryList)) {
            searchDTO.setMineName(categoryList.getId());
        }
        TaxAuthorityDTO taxAuth = (TaxAuthorityDTO) searchForm.getTaxAuthority().getValue();
        if (!DataUtil.isNullObject(taxAuth)) {
            searchDTO.setTaxAuthority(taxAuth.getMaCqt());
        }
        StaffDTO staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
        if (!searchForm.isVisible()) {
            searchDTO.setStaffId(staff.getStaffId());
        } else {
            staff = (StaffDTO) searchForm.getCboStaff().getValue();
            if (!DataUtil.isNullObject(staff)) {
                searchDTO.setStaffId(staff.getStaffId());
            }
            String custCareCreatedDate = DataUtil.getDateNullOrZero(searchForm.getDfContactCreatedDate());
            if (!DataUtil.isStringNullOrEmpty(custCareCreatedDate)) {
                searchDTO.setCustCareHistoryCreatedDate(custCareCreatedDate);
            }
        }
        return searchDTO;
    }
    //Thuc hien lay doi tuong tim kiem

    public List<ConditionBean> getListCondition2Search() {
        List<ConditionBean> lstConditionBeans = new ArrayList<>();
        //Lay ma so thue
        taxCode = searchForm.getTaxCode().getValue();
        name = searchForm.getName().getValue();
        telNumber = searchForm.getTelNumber().getValue();
        email = searchForm.getEmail().getValue();
        officeAddress = searchForm.getDeployAddress().getValue();
        statusDTO = (AppParamsDTO) searchForm.getStatus().getData();
        //Neu du lieu khac null thi set vao doi tuong de tim kiem
        if (statusDTO != null
                && !DataUtil.isStringNullOrEmpty(statusDTO.getParCode())) {
            lstConditionBeans.add(new ConditionBean("status",
                    statusDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL,
                    ConditionBean.Type.STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(taxCode)) {
            lstConditionBeans.add(new ConditionBean("taxCode",
                    taxCode, ConditionBean.Operator.NAME_LIKE,
                    ConditionBean.Type.STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(name)) {
            lstConditionBeans.add(new ConditionBean("name",
                    name, ConditionBean.Operator.NAME_LIKE,
                    ConditionBean.Type.STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(telNumber)) {
            lstConditionBeans.add(new ConditionBean("telNumber",
                    telNumber, ConditionBean.Operator.NAME_LIKE,
                    ConditionBean.Type.STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(email)) {
            lstConditionBeans.add(new ConditionBean("email",
                    email, ConditionBean.Operator.NAME_LIKE,
                    ConditionBean.Type.STRING));
        }
        return lstConditionBeans;
    }

    //Reset form
    public void resetForm() {
        searchForm.getTaxCode().clear();
        searchForm.getEmail().clear();
        searchForm.getTelNumber().clear();
        searchForm.getName().clear();
        searchForm.getDfContactCreatedDate().clear();

        initSearchForm();
    }

    private void addListenerResetButton() {
        btnReset = searchForm.getBtnReset();
        btnReset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                resetForm();
                event.getButton().setEnabled(true);
            }
        });
    }

    public int getMaxSearch() {
        return DataUtil.getMaxSearch(searchForm.getCboMaxSearch());
    }

    public List<TaxAuthorityDTO> getLstTaxAuthority() {
        return lstTaxAuthority;
    }

    public void setLstTaxAuthority(List<TaxAuthorityDTO> lstTaxAuthority) {
        this.lstTaxAuthority = lstTaxAuthority;
    }

}
