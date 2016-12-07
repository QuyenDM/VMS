/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerCareHistoryDTO;
import com.cms.dto.CustomerContactDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.StaffDTO;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextArea;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author quyen
 */
public class CustomerContactDialog extends CommonDialog {

    private GridLayout gridCustomerContact;
    private GridLayout gridCareHistory;
    private TextField txtName;
    private TextField txtTelNumber;
    private TextField txtEmail;
    private TextField txtRegency;
    private ComboBox cboRegency;
    private ComboBox cboStatus;
    private DateField dfDateTracking;
    private TextArea taNotes;
    private Button btnSave;
    private List<AppParamsDTO> lstRegency;
    private List<AppParamsDTO> lstCustomerStatus;
    private List<CustomerContactDTO> lstCustContact;
    private Locale locale;
    private ComboComponent combo;

    public CustomerContactDialog(String caption, List<AppParamsDTO> lstCustomerStatus, List<CustomerContactDTO> lstCustContact) {
        super.setInfo("80%", "-1px", caption);
        this.lstCustomerStatus = lstCustomerStatus;
        this.lstCustContact = lstCustContact;
        buildGridCustomerContact();
//        txtName.focus();
    }

    private void buildGridCustomerContact() {
        gridCustomerContact = new GridLayout(4, 1);
        gridCareHistory = new GridLayout(2, 3);
        locale = (Locale) VaadinSession.getCurrent().getAttribute("locale");
        if (locale == null) {
            locale = new Locale("vi");
        }
        CommonUtils.setBasicAttributeLayout(gridCustomerContact, "", false);
        CommonUtils.setBasicAttributeLayout(gridCareHistory, BundleUtils.getString("label.history.care.caption"), true);
        txtName = CommonUtils.buildTextField(BundleUtils.getString("customer.contact.name"), 100);
        txtEmail = CommonUtils.buildTextField(BundleUtils.getString("customer.contact.email"), 100);
        txtTelNumber = CommonUtils.buildTextField(BundleUtils.getString("customer.contact.telNumber"), 100);
        txtRegency = CommonUtils.buildTextField(BundleUtils.getString("customer.contact.regency"), 100);
        dfDateTracking = new DateField(BundleUtils.getString("customerCareHistoryForm.dateTracking"));
        dfDateTracking.setWidth("100%");
        dfDateTracking.setImmediate(true);
        dfDateTracking.setLocale(locale);
        taNotes = new TextArea(BundleUtils.getString("customerCareHistoryForm.notes"));
        taNotes.setRequired(true);
        taNotes.setWidth("100%");
        cboStatus = CommonUtils.buildComboBox(BundleUtils.getString("customerStatusForm.status"));
        cboStatus.setNullSelectionAllowed(true);
        cboRegency = CommonUtils.buildComboBox("customer.contact.regency");

        gridCustomerContact.addComponent(txtName, 0, 0);
        gridCustomerContact.addComponent(txtEmail, 1, 0);
        gridCustomerContact.addComponent(txtTelNumber, 2, 0);
        gridCustomerContact.addComponent(cboRegency, 3, 0);
        gridCareHistory.addComponent(cboStatus, 0, 0);
        gridCareHistory.addComponent(dfDateTracking, 1, 0);
        gridCareHistory.addComponent(taNotes, 0, 1, 1, 1);

        mainLayout.addComponent(gridCustomerContact);
        mainLayout.addComponent(gridCareHistory);

        lstRegency = DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        String valueRegencyDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstRegency)) {
            valueRegencyDefault = lstRegency.get(0).getParCode();
        }

        combo = new ComboComponent();
        combo.fillDataCombo(cboRegency, Constants.NULL, valueRegencyDefault, lstRegency, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        combo.fillDataCombo(cboStatus, Constants.NULL, valueRegencyDefault, lstCustomerStatus, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        GridManyButton gridManyButton = CommonUtils.getCommonButtonDialog(this);
        btnSave = gridManyButton.getBtnCommon().get(0);
        mainLayout.addComponent(gridManyButton);
        DataUtil.addFocusWindow(this, txtName);
    }

    /**
     * Get input object from input form and from customer
     *
     * @param customerDTO customer is selected by staff
     * @return CustomerContactDTO object will be save to db
     */
    public CustomerContactDTO getInputContactObject(CustomerDTO customerDTO) {
        String phoneNumber = DataUtil.getStringNullOrZero(txtTelNumber.getValue());

        CustomerContactDTO contactDTO;
        contactDTO = getContactExisted(phoneNumber);
        if (DataUtil.isNullObject(contactDTO)) {
            contactDTO = new CustomerContactDTO();
            contactDTO.setTelNumber(phoneNumber);
            contactDTO.setStatus(Constants.ACTIVE);
            contactDTO.setName(DataUtil.getStringNullOrZero(txtName.getValue()));
            contactDTO.setEmail(DataUtil.getStringNullOrZero(txtEmail.getValue()));
            AppParamsDTO regency = (AppParamsDTO) cboRegency.getValue();
            contactDTO.setRegency(DataUtil.getStringNullOrZero(regency.getParCode()));
            contactDTO.setTaxCode(customerDTO.getTaxCode());
            contactDTO.setCustId(customerDTO.getCustId());
        }
        return contactDTO;
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

    public CustomerCareHistoryDTO getInputCustomerCareObject(CustomerDTO customer) {
        StaffDTO staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");

        CustomerCareHistoryDTO careHistoryDTO = new CustomerCareHistoryDTO();
        careHistoryDTO.setCustId(customer.getCustId());
        careHistoryDTO.setTaxCode(customer.getTaxCode());
        careHistoryDTO.setStaffId(staff.getStaffId());
        careHistoryDTO.setStaffCode(staff.getCode());
        //Tam thoi fix dich vu
        careHistoryDTO.setService("1");
        careHistoryDTO.setDateTracking(DataUtil.getDateNullOrZero(dfDateTracking));
        careHistoryDTO.setCreateDate(DateUtil.date2ddMMyyyyHHMMss(new Date()));
        careHistoryDTO.setNotes(DataUtil.getStringNullOrZero(taNotes.getValue()));
        AppParamsDTO customerStatus = (AppParamsDTO) cboStatus.getValue();
        if (customerStatus != null) {
            careHistoryDTO.setStatus(customerStatus.getParCode());
        }
        careHistoryDTO.setContact(DataUtil.getStringNullOrZero(txtName.getValue()));
        careHistoryDTO.setTelNumber(DataUtil.getStringNullOrZero(txtTelNumber.getValue()));
        return careHistoryDTO;
    }

    public void fillData2Dialog(CustomerContactDTO contactDTO) {
        CustomerContactDTO contactExisted = getContactExisted(contactDTO.getTelNumber());
        if (!DataUtil.isNullObject(contactExisted)) {
            txtName.setValue(DataUtil.getStringNullOrZero(contactExisted.getName()));
            txtTelNumber.setValue(DataUtil.getStringNullOrZero(contactExisted.getTelNumber()));
            txtEmail.setValue(DataUtil.getStringNullOrZero(contactExisted.getEmail()));
            String valueRegency = contactExisted.getRegency();
            combo.fillDataCombo(cboRegency, Constants.NULL, valueRegency, lstRegency, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        } else {
            txtName.setValue(DataUtil.getStringNullOrZero(contactDTO.getName()));
            txtTelNumber.setValue(DataUtil.getStringNullOrZero(contactDTO.getTelNumber()));
            txtEmail.setValue(DataUtil.getStringNullOrZero(contactDTO.getEmail()));
        }
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public GridLayout getGridCustomerContact() {
        return gridCustomerContact;
    }

    public void setGridCustomerContact(GridLayout gridCustomerContact) {
        this.gridCustomerContact = gridCustomerContact;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtTelNumber() {
        return txtTelNumber;
    }

    public void setTxtTelNumber(TextField txtTelNumber) {
        this.txtTelNumber = txtTelNumber;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public TextField getTxtRegency() {
        return txtRegency;
    }

    public void setTxtRegency(TextField txtRegency) {
        this.txtRegency = txtRegency;
    }

    public ComboBox getCboRegency() {
        return cboRegency;
    }

    public void setCboRegency(ComboBox cboRegency) {
        this.cboRegency = cboRegency;
    }

}
