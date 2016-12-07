/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.CustomerCareHistoryDialog;
import com.anphat.customer.ui.CustomerContactDialog;
import com.anphat.customer.ui.CustomerContactForm;
import com.anphat.customer.ui.CustomerDetailForm;
import com.cms.login.ws.WSCustomer;
import com.cms.login.ws.WSCustomerCareHistory;
import com.cms.login.ws.WSCustomerContact;
import com.google.common.collect.Lists;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.UI;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerCareHistoryDTO;
import com.cms.dto.CustomerContactDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerInfomationDTO;
import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StaffDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.dto.TermInformationDTO;
import com.cms.login.ws.WSTaxAuthority;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.themes.Runo;
import com.vwf5.base.dto.ResultDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class CustomerContactController {

    private final CustomerContactForm customerContactForm;
    private final CustomerDetailForm customerDetailForm;
    private CommonTableFilterPanel panelTblContact;
    private CommonTableFilterPanel panelTblHistory;
    private CommonTableFilterPanel panelTblCustomerStatus;
    private CommonTableFilterPanel panelTblTermInfor;
    private final String TERM_INFOR_CAPTION = BundleUtils.getString("term.infor.table.caption");
    private final String CONTACT_CAPTION = BundleUtils.getString("contact.table.caption");
    private final String CUSTOMER_CARE_HISTORY_CAPTION = BundleUtils.getString("customerCareHistoryForm.gridTitle");
    private final String CUSTOMER_STATUS_CAPTION = BundleUtils.getString("customerStatusForm.gridTitle");
    private final LinkedHashMap<String, CustomTable.Align> HEADER_TERM_INFOR = BundleUtils.getHeadersFilter("upload.term.infor.customer.header");
    private final LinkedHashMap<String, CustomTable.Align> HEADER_CONTACT = BundleUtils.getHeadersFilter("contact.infor.header");
    private final LinkedHashMap<String, CustomTable.Align> HEADER_HISTORY_TRANSACTION = BundleUtils.getHeadersFilter("customer.care.history.header");
    private final LinkedHashMap<String, CustomTable.Align> HEADER_CUSTOMER_SERVICE_STATUS = BundleUtils.getHeadersFilter("customer.status.header");

    private BeanItemContainer containerTermInfor;
    private List<TermInformationDTO> lstTermInfors;
    private BeanItemContainer containerCustContact;
    private List<CustomerContactDTO> lstCustContact;
    private BeanItemContainer containerCustCareHistory;
    private List<CustomerCareHistoryDTO> lstCustCareHistory;
    private BeanItemContainer containerCustomerStatus;
    private List<CustomerStatusDTO> lstCustomerStatus;
    private CustomerDTO selectedCust;
    private List<AppParamsDTO> lstServices;
    private List<AppParamsDTO> lstRegency;
    private Map<String, String> mapServices;
    private Map<String, String> mapRegency;
    private List<AppParamsDTO> lstCustomerServiceStatus;
    private Map<String, String> mapCustomerServices;
    private List<AppParamsDTO> lstAppParams;
    private CustomerCareHistoryDialog careHistoryDialog;
    private Map<String, String> mapTaxAuthority;
    private List<TaxAuthorityDTO> lstTaxAuthority;

    public CustomerContactController(CustomerDetailForm customerDetailForm) {
        this.customerDetailForm = customerDetailForm;
        this.customerContactForm = customerDetailForm.getCustomerContactForm();
        getDatas();
        initTables();
        addListenerOfTables();
    }

    private void getDatas() {
        lstAppParams = DataUtil.getListAppParamsDTOs();
        lstServices = DataUtil.getListApParams(lstAppParams, Constants.APP_PARAMS.SERVICE_TYPE);
        if (!DataUtil.isListNullOrEmpty(lstServices)) {
            try {
                mapServices = DataUtil.buildHasmap(lstServices, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
                Logger.getLogger(CustomerContactController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstRegency = DataUtil.getListApParams(lstAppParams, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        if (!DataUtil.isListNullOrEmpty(lstRegency)) {
            try {
                mapRegency = DataUtil.buildHasmap(lstRegency, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
                Logger.getLogger(CustomerContactController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lstCustomerServiceStatus = DataUtil.getListApParams(lstAppParams, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        if (!DataUtil.isListNullOrEmpty(lstCustomerServiceStatus)) {
            try {
                mapCustomerServices = DataUtil.buildHasmap(lstCustomerServiceStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
                Logger.getLogger(CustomerContactController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Lay danh sach tinh
        lstTaxAuthority = WSTaxAuthority.getListProvineTaxAuthority();
        try {
            mapTaxAuthority = DataUtil.buildHasmap(lstTaxAuthority, Constants.TAXAUTHORITY.MA_CQT, Constants.TAXAUTHORITY.TEN_CQT);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(CustomerContactController.class.getName()).log(Level.SEVERE, null, ex);
            mapTaxAuthority = new HashMap<>();
        }
    }

    private void initTables() {
        initTableTermInfor();
        initTableCustContact();
        initTableCustCareHistory();
        initTableCustomerStatus();
    }

    private void initTableTermInfor() {
        panelTblTermInfor = customerDetailForm.getTblTermInfor();
        containerTermInfor = new BeanItemContainer(TermInformationDTO.class);
        //Them nut them moi lien he
        addButtonAddContactOnTblTermInfo(panelTblTermInfor.getMainTable());
        CommonFunctionTableFilter.initTable(panelTblTermInfor, HEADER_TERM_INFOR, containerTermInfor, TERM_INFOR_CAPTION, 5, Constants.CAPTION.TERM_INFORMATION);
        CommonUtils.convertFieldAppParamTable(panelTblTermInfor.getMainTable(), "service", Constants.APP_PARAMS.SERVICE_TYPE, mapServices);
    }

    private void initTableCustContact() {
        panelTblContact = customerContactForm.getTblContact();
        containerCustContact = new BeanItemContainer(CustomerContactDTO.class);
        CommonFunctionTableFilter.initTable(panelTblContact, HEADER_CONTACT, containerCustContact, CONTACT_CAPTION, 5, Constants.CAPTION.CUSTOMER_CONTACT);
        CommonUtils.convertFieldAppParamTable(panelTblContact.getMainTable(), "regency", Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY, mapRegency);
        panelTblContact.getAddButton().setEnabled(false);
    }

    private void initTableCustCareHistory() {
        panelTblHistory = customerContactForm.getTblHistory();
        containerCustCareHistory = new BeanItemContainer(CustomerCareHistoryDTO.class);

        CommonFunctionTableFilter.initTable(panelTblHistory, HEADER_HISTORY_TRANSACTION, containerCustCareHistory, CUSTOMER_CARE_HISTORY_CAPTION, 5, Constants.CAPTION.CUSTOMER_CARE_HISTORY);
        panelTblHistory.getAddButton().setEnabled(false);
        CommonUtils.convertFieldAppParamTable(panelTblHistory.getMainTable(), "service", Constants.APP_PARAMS.SERVICE_TYPE, mapServices);
        CommonUtils.convertFieldAppParamTable(panelTblHistory.getMainTable(), "status", Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS, mapCustomerServices);
    }

    private void addButtonAddContactOnTblTermInfo(CustomPageTableFilter tbl) {
        tbl.addGeneratedColumn("btnAddContact", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                TermInformationDTO term = (TermInformationDTO) itemId;
                CustomerContactDTO contactDTO = term.convert2CustomerContact();
                Button btnAdd = new Button(FontAwesome.PHONE);
                btnAdd.addStyleName(Runo.BUTTON_LINK);
                addBtnAddContactClickListener(btnAdd, contactDTO);
                return btnAdd;
            }
        });
    }

    private void initTableCustomerStatus() {
        panelTblCustomerStatus = customerContactForm.getTblCustomerStatus();
        panelTblCustomerStatus.getToolbar().setVisible(false);
        containerCustomerStatus = new BeanItemContainer(CustomerStatusDTO.class);
        CommonFunctionTableFilter.initTable(panelTblCustomerStatus, HEADER_CUSTOMER_SERVICE_STATUS, containerCustomerStatus, CUSTOMER_STATUS_CAPTION, 5, Constants.CAPTION.CUSTOMER_STATUS);
        CommonUtils.convertFieldAppParamTable(panelTblCustomerStatus.getMainTable(), "service", Constants.APP_PARAMS.SERVICE_TYPE, mapServices);
        CommonUtils.convertFieldAppParamTable(panelTblCustomerStatus.getMainTable(), "status", Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS, mapCustomerServices);
    }

    /**
     * Dien du lieu vao panel chi tiet khach hang
     *
     * @param customer
     */
    public void setData2DetailPanel(CustomerDTO customer) {
        selectedCust = customer;
        selectedCust.setTaxAuthority(mapTaxAuthority.get(customer.getTaxAuthority()));
        setVisibleButtonAddOnTables();
        customerDetailForm.setData2Detail(selectedCust);
        setData2Tables(selectedCust);
    }

    public void setData2Tables(CustomerDTO customer) {
        getListDatasWithCustomer(customer);
        setData2TableTermInfor();
        setData2TableContact();
        setData2TableCustCareHistory();
        setData2TableCustomerStatus();
    }

    private void setData2TableTermInfor() {
        containerTermInfor.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstTermInfors)) {
            containerTermInfor.addAll(lstTermInfors);
        }
        CommonFunctionTableFilter.refreshTable(panelTblTermInfor, HEADER_TERM_INFOR, containerTermInfor);
    }

    private void getListDatasWithCustomer(CustomerDTO customer) {
        if (customer != null && !DataUtil.isStringNullOrEmpty(customer.getTaxCode())) {
            try {
                StaffDTO staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
                String staffCode;
                //Neu la admin thi cho phep lay du lieu cua tat ca cac thanh vien
                if (!DataUtil.isAdmin(staff)) {
                    staffCode = staff.getCode();
                } else {
                    staffCode = null;
                }
                CustomerInfomationDTO customerInfomationDTO = WSCustomer.getCustInfo(customer.getTaxCode(), staffCode);
                if (customerInfomationDTO != null) {
                    lstTermInfors = customerInfomationDTO.getLstTermInformationDTOs();
                    lstCustContact = customerInfomationDTO.getLstCustomerContacts();
                    lstCustCareHistory = customerInfomationDTO.getLstCustomerCareHistoryDTOs();
                    lstCustomerStatus = customerInfomationDTO.getLstCustomerStatusDTOs();
                }
            } catch (Exception e) {
                lstTermInfors = null;
                lstCustContact = null;
                lstCustCareHistory = null;
                lstCustomerStatus = null;
                e.printStackTrace();
            }
        } else {
            lstTermInfors = null;
            lstCustContact = null;
            lstCustCareHistory = null;
            lstCustomerStatus = null;
        }
    }

    private void setData2TableContact() {
        containerCustContact.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstCustContact)) {
            containerCustContact.addAll(lstCustContact);
        }
        CommonFunctionTableFilter.refreshTable(panelTblContact, HEADER_CONTACT, containerCustContact);
    }

    private void setData2TableCustCareHistory() {
        containerCustCareHistory.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstCustCareHistory)) {
            containerCustCareHistory.addAll(lstCustCareHistory);
        }
        CommonFunctionTableFilter.refreshTable(panelTblHistory, HEADER_HISTORY_TRANSACTION, containerCustCareHistory);
    }

    private void setData2TableCustomerStatus() {
        containerCustomerStatus.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstCustomerStatus)) {
            containerCustomerStatus.addAll(lstCustomerStatus);
        }
        CommonFunctionTableFilter.refreshTable(panelTblCustomerStatus, HEADER_CUSTOMER_SERVICE_STATUS, containerCustomerStatus);
    }

    // Enable/Disable button add on table contact and history
    private void setVisibleButtonAddOnTables() {
        if (selectedCust == null || DataUtil.isStringNullOrEmpty(selectedCust.getTaxCode())) {
            panelTblContact.getAddButton().setEnabled(false);
            panelTblHistory.getAddButton().setEnabled(false);
            customerDetailForm.getBtnCreateContractDoc().setEnabled(false);
            customerDetailForm.getBtnCreateContract().setEnabled(false);
        } else {
            customerDetailForm.getBtnCreateContractDoc().setEnabled(true);
            customerDetailForm.getBtnCreateContract().setEnabled(true);
            panelTblContact.getAddButton().setEnabled(true);
            panelTblHistory.getAddButton().setEnabled(true);
        }
    }

    //Add listener Create new on panel table contact and history
    private void addListenerOfTables() {
        addListenerCreateCustContact();
        addListenerCreateCustCareHistory();
    }

    private void addListenerCreateCustContact() {
        CommonUtils.setVisibleBtnTablePanel(panelTblContact, true, false, false, false);
        Button btnAddCustContact = panelTblContact.getAddButton();
        btnAddCustContact.setCaption(BundleUtils.getString("label.customer.contact.addNew"));
        ShortcutUtils.setShortkeyF2(btnAddCustContact);
        addBtnAddContactClickListener(btnAddCustContact, null);
    }

    private void addBtnAddContactClickListener(Button btn, final CustomerContactDTO contactDTO) {
        btn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CustomerContactDialog contactDialog = new CustomerContactDialog(
                        BundleUtils.getString("customerCareHistoryForm"), lstCustomerServiceStatus, lstCustContact);
                if (contactDTO != null) {
                    contactDialog.fillData2Dialog(contactDTO);
                }
                UI.getCurrent().addWindow(contactDialog);
                addListenerBtnSaveContact(contactDialog);
                event.getButton().setEnabled(true);
            }
        });
    }

    private void addListenerBtnSaveContact(final CustomerContactDialog contactDialog) {
        Button btnSave = contactDialog.getBtnSave();
        ShortcutUtils.setShortcutKey(btnSave);
        btnSave.addClickListener(new CommonButtonClickListener() {
            CustomerContactDTO contactDTO;

            @Override
            public void execute() {
//                if(contactDTO.getName())
                CustomerCareHistoryDTO careHistoryDTO = contactDialog.getInputCustomerCareObject(selectedCust);
                if (DataUtil.isStringNullOrEmpty(contactDTO.getId())) {
                    ResultDTO result = WSCustomerContact.insertCustomerContact(contactDTO);
                    if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
                        ResultDTO resultCare = saveCustomerCareHistory(careHistoryDTO);
                        if (resultCare != null && Constants.SUCCESS.equals(resultCare.getMessage())) {
                            contactDialog.close();
                            CommonMessages.showMessageImportSuccess("customerCareHistoryForm");
                            if (DataUtil.isListNullOrEmpty(lstCustContact)) {
                                lstCustContact = new ArrayList<>();
                            }
                            lstCustContact.add(0, contactDTO);
                            setData2TableContact();
                            if (careHistoryDialog.isVisible()) {
                                careHistoryDialog.addContact2ListContact(contactDTO);
                            }
                        }
                    } else {
                        CommonMessages.showInsertFail("customerCareHistoryForm");
                    }
                } else {
                    //Luu thong tin nhat ky goi
                    ResultDTO resultCare = saveCustomerCareHistory(careHistoryDTO);
                    if (resultCare != null && Constants.SUCCESS.equals(resultCare.getMessage())) {
                        contactDialog.close();
                        CommonMessages.showMessageImportSuccess("customerCareHistoryForm");
                        if (DataUtil.isListNullOrEmpty(lstCustContact)) {
                            lstCustContact = new ArrayList<>();
                        }
                        lstCustContact.add(0, contactDTO);
                        setData2TableContact();
                        if (careHistoryDialog != null && careHistoryDialog.isVisible()) {
                            careHistoryDialog.addContact2ListContact(contactDTO);
                        }
                    } else {
                        CommonMessages.showInsertFail("customerCareHistoryForm");
                    }
                }
            }

            @Override
            public boolean isValidated() {
                contactDTO = contactDialog.getInputContactObject(selectedCust);
                if (DataUtil.isStringNullOrEmpty(contactDTO.getName())) {
                    contactDialog.getTxtName().focus();
                    CommonMessages.messageRequire("customerContact.name");
                    return false;
                }
                if (DataUtil.isStringNullOrEmpty(contactDTO.getTelNumber())) {
                    contactDialog.getTxtTelNumber().focus();
                    CommonMessages.messageRequire("customerContact.telNumber");
                    return false;
                }
                return true;
            }
        }
        );
    }

    private void addListenerCreateCustCareHistory() {
        CommonUtils.setVisibleBtnTablePanel(panelTblHistory, true, false, false, false);
        Button btnAddCustCareHistory = panelTblHistory.getAddButton();
        btnAddCustCareHistory.setCaption(BundleUtils.getString("label.history.care.addNew"));
        ShortcutUtils.setShortkeyAddNew(btnAddCustCareHistory);
        btnAddCustCareHistory.addClickListener(new CommonButtonClickListener() {

            @Override
            public void execute() {
                List<CustomerContactDTO> lstCustContactDTOs = Lists.newArrayList();
                lstCustContactDTOs.addAll((Collection<? extends CustomerContactDTO>) panelTblContact.getMainTable().getItemIds());
                careHistoryDialog = new CustomerCareHistoryDialog(BundleUtils.getString("customerCareHistoryForm.insert"), lstServices, lstCustomerServiceStatus, lstCustContactDTOs);
                UI.getCurrent().addWindow(careHistoryDialog);
                addListenerBtnSaveCareHistory(careHistoryDialog);
                addBtnAddContactClickListener(careHistoryDialog.getBtnAddContact(), null);
            }
        });
    }

    private void addListenerBtnSaveCareHistory(final CustomerCareHistoryDialog careDialog) {
        Button btnSave = careDialog.getBtnSave();
        ShortcutUtils.setShortcutKey(btnSave);
        btnSave.addClickListener(new CommonButtonClickListener() {
            String taNotes;
            CustomerContactDTO contact;

            @Override
            public boolean isValidated() {
                taNotes = DataUtil.getStringNullOrZero(careDialog.getTaNotes().getValue());
                contact = (CustomerContactDTO) careDialog.getF9Contact().codeCombo.getValue();
                if (contact == null || DataUtil.isStringNullOrEmpty(contact.getName())) {
                    careDialog.getF9Contact().codeCombo.focus();
                    CommonMessages.showMessageRequired("customerCareHistoryForm.contact");
                    return false;
                }
                if (DataUtil.isStringNullOrEmpty(taNotes)) {
                    careDialog.getTaNotes().focus();
                    CommonMessages.showMessageRequired("customerCareHistoryForm.notes");
                    return false;
                }
                return true;
            }

            @Override
            public void execute() {
                CustomerCareHistoryDTO careHistoryDTO = careDialog.getInputObject(selectedCust);
                ResultDTO result = saveCustomerCareHistory(careHistoryDTO);
                if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
                    careDialog.close();
                }
            }
        }
        );
    }

    /**
     * Cap nhat trang thai dich vu
     *
     * @param careHistoryDTO
     */
    public void updateCustomerStatusTable(CustomerCareHistoryDTO careHistoryDTO) {
        if (DataUtil.isListNullOrEmpty(lstCustomerStatus)) {
            return;
        }
        for (CustomerStatusDTO c : lstCustomerStatus) {
            if (c.getStaffId().equals(careHistoryDTO.getStaffId())
                    && c.getService().equals(careHistoryDTO.getService())
                    && c.getTaxCode().equals(careHistoryDTO.getTaxCode())) {
                c.setStatus(careHistoryDTO.getStatus());
                break;
            }
        }
        setData2TableCustomerStatus();
    }

    //Kiem tra contact co ton tai chua
    public CustomerContactDTO getContactExisted(String phoneNumber) {
        if (!DataUtil.isListNullOrEmpty(lstCustContact)) {
            for (CustomerContactDTO cc : lstCustContact) {
                if (cc.getTelNumber().equals(DataUtil.getStringNullOrZero(phoneNumber))) {
                    return cc;
                }
            }
        }
        return null;
    }

    public ResultDTO saveCustomerCareHistory(CustomerCareHistoryDTO careHistoryDTO) {
        ResultDTO result = WSCustomerCareHistory.insertCustomerCareHistory(careHistoryDTO);
        if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
            if (DataUtil.isListNullOrEmpty(lstCustCareHistory)) {
                lstCustCareHistory = new ArrayList<>();
            }
            lstCustCareHistory.add(0, careHistoryDTO);
            setData2TableCustCareHistory();
            //Cap nhat bang trang thai - dich vu
            updateCustomerStatusTable(careHistoryDTO);
//            careDialog.close();
//            CommonMessages.showMessageImportSuccess("customerCareHistoryForm");
        }
        return result;
    }
}
