/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.cms.component.CommonSearchForm;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.DateField;

/**
 *
 * @author quyen
 */
public class SearchCustomerForm extends CommonSearchForm {

    private TextField taxCode; //Ma so thue
    private TextField name; //Ten khach hang
    private TextField telNumber; //So dien thoai
    private TextField fax; //So fax
    private TextField email; //Email
    private TextField agency; //Dai ly thue
    private ComboBox status; //Trang thai
    private TextField deployAddress;
    private TextField officeAddress; //Dia chi tru so
    private ComboBox taxDepartment; //Chi cục thuế
    private ComboBox taxAuthority; //Cơ quan thuế
    private TextField representativeName; // Tên người đại diện
    private TextField representativeId; //Chứng minh thư
//    private TextField description; //Mo ta
    private ComboBox cboStaff; //Tên danh sách khai thác
    private DateField dfContactCreatedDate; //Ngày liên hệ
    private ComboBox cbxMineName; //Tên danh sách khai thác
    private ComboBox staffName; //Nhan vien tai len
    private ComboBox cboMaxSearch; //Gioi han so luong tim kiem
    private ComboBox cboProvider; //Nha cung cap

    public SearchCustomerForm() {
        this.caption = BundleUtils.getString("customer.management.header.search");
        initForm();
    }

    private void initForm() {
        column = 4;
        
        row = 3;
        init();
        setCaption();
        buildComponents();
        initSearchContents();
        addShortcuts();
    }

    public void buildComponents() {
        taxCode = CommonUtils.buildTextField("customer.taxCode", 20, "ALT + 1");
        taxCode.focus();
        name = CommonUtils.buildTextField("customer.name", 100, "ALT + 2");
        telNumber = CommonUtils.buildTextField("customer.telNumber", 25, "ALT + 3");
        email = CommonUtils.buildTextField("customer.email", 100, "ALT + 4");
//        deployAddress = CommonUtils.buildTextField("customer.addressDeploy", 200);
//        officeAddress = CommonUtils.buildTextField("customer.officeAddress", 200);
        taxDepartment = CommonUtils.buildComboBox("customer.taxDepartment");
        taxAuthority = CommonUtils.buildComboBox("label.taxAuthority");
//        mineName = CommonUtils.buildTextField("customer.mineName", 100);
        cbxMineName = CommonUtils.buildComboBox("customer.mineName");
        cbxMineName.setRequired(true);
        cboStaff = CommonUtils.buildComboBox("history.label.staffName");
        status = CommonUtils.buildComboBox("customer.status");
        cboMaxSearch = CommonUtils.buildComboBox("max.search");
        cboProvider = CommonUtils.buildComboBox("term.information.provider");
        dfContactCreatedDate = CommonUtils.buildDateField("customerCareHistoryForm.createDate");
    }

    @Override
    public void initSearchContents() {
        searchLayout.setColumns(column);
        searchLayout.setRows(row);

        searchLayout.addComponent(taxCode, 0, 0);
        searchLayout.addComponent(name, 1, 0);
        searchLayout.addComponent(telNumber, 2, 0);
        searchLayout.addComponent(email, 3, 0);
//        searchLayout.addComponent(mineName, 0, 1);
        searchLayout.addComponent(status, 0, 1);
        searchLayout.addComponent(taxAuthority, 1, 1);
        searchLayout.addComponent(dfContactCreatedDate, 2, 1);
        searchLayout.addComponent(cboProvider, 3, 1);
        
        searchLayout.addComponent(cboMaxSearch, 0, 2);
        searchLayout.addComponent(cbxMineName, 1, 2);
        searchLayout.addComponent(cboStaff, 2, 2);
    }

    private void addShortcuts() {
        taxCode.addShortcutListener(
                new AbstractField.FocusShortcut(taxCode, ShortcutAction.KeyCode.NUM1,
                        ModifierKey.ALT));

        name.addShortcutListener(
                new AbstractField.FocusShortcut(name, ShortcutAction.KeyCode.NUM2,
                        ModifierKey.ALT));
        telNumber.addShortcutListener(
                new AbstractField.FocusShortcut(telNumber, ShortcutAction.KeyCode.NUM3,
                        ModifierKey.ALT));

        email.addShortcutListener(
                new AbstractField.FocusShortcut(email, ShortcutAction.KeyCode.NUM4,
                        ModifierKey.ALT));

    }

    public GridLayout getSearchLayout() {
        return searchLayout;
    }

    public ComboBox getCbxMineName() {
        return cbxMineName;
    }

    public void setCbxMineName(ComboBox cbxMineName) {
        this.cbxMineName = cbxMineName;
    }

    public void setSearchLayout(GridLayout searchLayout) {
        this.searchLayout = searchLayout;
    }

    public TextField getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(TextField taxCode) {
        this.taxCode = taxCode;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public TextField getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(TextField telNumber) {
        this.telNumber = telNumber;
    }

    public TextField getFax() {
        return fax;
    }

    public void setFax(TextField fax) {
        this.fax = fax;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getAgency() {
        return agency;
    }

    public void setAgency(TextField agency) {
        this.agency = agency;
    }

    public ComboBox getStatus() {
        return status;
    }

    public ComboBox getCboMaxSearch() {
        return cboMaxSearch;
    }

    public void setStatus(ComboBox status) {
        this.status = status;
    }

    public TextField getDeployAddress() {
        return deployAddress;
    }

    public void setDeployAddress(TextField deployAddress) {
        this.deployAddress = deployAddress;
    }

    public TextField getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(TextField officeAddress) {
        this.officeAddress = officeAddress;
    }

    public ComboBox getTaxDepartment() {
        return taxDepartment;
    }

    public void setTaxDepartment(ComboBox taxDepartment) {
        this.taxDepartment = taxDepartment;
    }

    public ComboBox getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(ComboBox taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public TextField getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(TextField representativeName) {
        this.representativeName = representativeName;
    }

    public TextField getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(TextField representativeId) {
        this.representativeId = representativeId;
    }

//    public TextField getMineName() {
//        return mineName;
//    }
//
//    public void setMineName(TextField mineName) {
//        this.mineName = mineName;
//    }
    public ComboBox getStaffName() {
        return staffName;
    }

    public void setStaffName(ComboBox staffName) {
        this.staffName = staffName;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(Button btnReset) {
        this.btnReset = btnReset;
    }

    public ComboBox getCboProvider() {
        return cboProvider;
    }

    public void setCboProvider(ComboBox cboProvider) {
        this.cboProvider = cboProvider;
    }

    public ComboBox getCboStaff() {
        return cboStaff;
    }

    public void setCboStaff(ComboBox cboStaff) {
        this.cboStaff = cboStaff;
    }

    public DateField getDfContactCreatedDate() {
        return dfContactCreatedDate;
    }

    public void setDfContactCreatedDate(DateField dfContactCreatedDate) {
        this.dfContactCreatedDate = dfContactCreatedDate;
    }

}
