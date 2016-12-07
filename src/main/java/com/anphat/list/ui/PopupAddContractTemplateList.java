/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.cms.component.GridManyButton;
import com.cms.utils.BundleUtils;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
;
import com.vaadin.ui.TextField;

/**
 *
 * @author QuyenDM
 */


public class PopupAddContractTemplateList extends Window {

    private VerticalLayout mainLayout = new VerticalLayout();
    private GridLayout addContractTemplateListLayout;
    private Button btnSave;
    private Button btnClose;
    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblPathFile;
    private TextField txtPathFile;
    private Label lblCreatedDate;
    private PopupDateField popCreatedDate;
    private Label lblLastUpdatedDate;
    private PopupDateField popLastUpdatedDate;
    private Label lblStatus;
    private ComboBox cbxStatus;

    public PopupAddContractTemplateList() {
        setCaption(BundleUtils.getString("dialog.ContractTemplateList.caption"));
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");

        addContractTemplateListLayout = new GridLayout();
        addContractTemplateListLayout.setImmediate(false);
        addContractTemplateListLayout.setWidth("100.0%");
        addContractTemplateListLayout.setHeight("-1px");
        addContractTemplateListLayout.setMargin(true);
        addContractTemplateListLayout.setSpacing(true);
        addContractTemplateListLayout.setColumns(4);
        addContractTemplateListLayout.setRows(4);
        setWidth("70.0%");
        setHeight("-1px");
        setModal(true);
        lblCode = new Label();
        lblCode.setImmediate(false);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.ContractTemplateList.code"));
        addContractTemplateListLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(false);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        addContractTemplateListLayout.addComponent(txtCode, 1, 0);
        lblName = new Label();
        lblName.setImmediate(false);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.ContractTemplateList.name"));
        addContractTemplateListLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(false);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        addContractTemplateListLayout.addComponent(txtName, 3, 0);
        lblPathFile = new Label();
        lblPathFile.setImmediate(false);
        lblPathFile.setWidth("100.0%");
        lblPathFile.setHeight("-1px");
        lblPathFile.setValue(BundleUtils.getString("label.ContractTemplateList.pathFile"));
        addContractTemplateListLayout.addComponent(lblPathFile, 0, 1);

        txtPathFile = new TextField();
        txtPathFile.setImmediate(false);
        txtPathFile.setWidth("100.0%");
        txtPathFile.setHeight("-1px");
        addContractTemplateListLayout.addComponent(txtPathFile, 1, 1);
        lblCreatedDate = new Label();
        lblCreatedDate.setImmediate(false);
        lblCreatedDate.setWidth("100.0%");
        lblCreatedDate.setHeight("-1px");
        lblCreatedDate.setValue(BundleUtils.getString("label.ContractTemplateList.createdDate"));
        addContractTemplateListLayout.addComponent(lblCreatedDate, 2, 1);

        popCreatedDate = new PopupDateField();
        popCreatedDate.setImmediate(false);
        popCreatedDate.setWidth("100.0%");
        popCreatedDate.setHeight("-1px");
        addContractTemplateListLayout.addComponent(popCreatedDate, 3, 1);
        lblLastUpdatedDate = new Label();
        lblLastUpdatedDate.setImmediate(false);
        lblLastUpdatedDate.setWidth("100.0%");
        lblLastUpdatedDate.setHeight("-1px");
        lblLastUpdatedDate.setValue(BundleUtils.getString("label.ContractTemplateList.lastUpdatedDate"));
        addContractTemplateListLayout.addComponent(lblLastUpdatedDate, 0, 2);

        popLastUpdatedDate = new PopupDateField();
        popLastUpdatedDate.setImmediate(false);
        popLastUpdatedDate.setWidth("100.0%");
        popLastUpdatedDate.setHeight("-1px");
        addContractTemplateListLayout.addComponent(popLastUpdatedDate, 1, 2);
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.ContractTemplateList.status"));
        addContractTemplateListLayout.addComponent(lblStatus, 2, 2);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        addContractTemplateListLayout.addComponent(cbxStatus, 3, 2);

        mainLayout.addComponent(addContractTemplateListLayout);

        GridManyButton gridBtnPrint = new GridManyButton(new String[]{BundleUtils.getString("btn.ok"), BundleUtils.getString("btn.close")});
        mainLayout.addComponent(gridBtnPrint);
        btnSave = gridBtnPrint.getBtnCommon().get(0);
        btnClose = gridBtnPrint.getBtnCommon().get(1);
        setContent(mainLayout);
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(VerticalLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }

    public GridLayout getAddContractTemplateListLayout() {
        return addContractTemplateListLayout;
    }

    public void setAddContractTemplateListLayout(GridLayout addContractTemplateListLayout) {
        this.addContractTemplateListLayout = addContractTemplateListLayout;
    }

    public TextField getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(TextField txtCode) {
        this.txtCode = txtCode;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtPathFile() {
        return txtPathFile;
    }

    public void setTxtPathFile(TextField txtPathFile) {
        this.txtPathFile = txtPathFile;
    }

    public PopupDateField getPopCreatedDate() {
        return popCreatedDate;
    }

    public void setPopCreatedDate(PopupDateField popCreatedDate) {
        this.popCreatedDate = popCreatedDate;
    }

    public PopupDateField getPopLastUpdatedDate() {
        return popLastUpdatedDate;
    }

    public void setPopLastUpdatedDate(PopupDateField popLastUpdatedDate) {
        this.popLastUpdatedDate = popLastUpdatedDate;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

}
