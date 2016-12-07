/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.cms.component.GridManyButton;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author
 */
public class PopupAddCategoryList extends Window {

    private static String CAPTION = BundleUtils.getString("button.add.mineName");
    private VerticalLayout mainLayout = new VerticalLayout();
    private GridLayout addCategoryListLayout;
    private Button btnSave;
    private Button btnClose;
    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblReceivedDate;
    private DateField txtReceivedDate;
    private Label lblEndDate;
    private DateField txtEndDate;
    private Label lblDescription;
    private TextField txtDescription;
    private Label lblCreator;
    private TextField txtCreator;

    public PopupAddCategoryList() {
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");

        addCategoryListLayout = new GridLayout();
        addCategoryListLayout.setImmediate(true);
        addCategoryListLayout.setWidth("100.0%");
        addCategoryListLayout.setHeight("-1px");
        addCategoryListLayout.setMargin(true);
        addCategoryListLayout.setSpacing(true);
        addCategoryListLayout.setColumns(4);
        addCategoryListLayout.setRows(4);
        setWidth("70.0%");
        setHeight("-1px");
        setModal(true);
        lblCode = new Label();
        lblCode.setImmediate(true);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.CategoryList.code"));
        addCategoryListLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(true);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        addCategoryListLayout.addComponent(txtCode, 1, 0);
        lblName = new Label();
        lblName.setImmediate(true);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.CategoryList.name"));
        addCategoryListLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(true);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        addCategoryListLayout.addComponent(txtName, 3, 0);
        lblReceivedDate = new Label();
        lblReceivedDate.setImmediate(true);
        lblReceivedDate.setWidth("100.0%");
        lblReceivedDate.setHeight("-1px");
        lblReceivedDate.setValue(BundleUtils.getString("label.CategoryList.receivedDate"));
        addCategoryListLayout.addComponent(lblReceivedDate, 0, 1);

        txtReceivedDate = new DateField();
        txtReceivedDate.setImmediate(true);
        txtReceivedDate.setWidth("100.0%");
        txtReceivedDate.setHeight("-1px");
        addCategoryListLayout.addComponent(txtReceivedDate, 1, 1);
        lblEndDate = new Label();
        lblEndDate.setImmediate(true);
        lblEndDate.setWidth("100.0%");
        lblEndDate.setHeight("-1px");
        lblEndDate.setValue(BundleUtils.getString("label.CategoryList.endDate"));
        addCategoryListLayout.addComponent(lblEndDate, 2, 1);

        txtEndDate = new DateField();
        txtEndDate.setImmediate(true);
        txtEndDate.setWidth("100.0%");
        txtEndDate.setHeight("-1px");
        addCategoryListLayout.addComponent(txtEndDate, 3, 1);
        lblDescription = new Label();
        lblDescription.setImmediate(true);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("label.CategoryList.description"));
        addCategoryListLayout.addComponent(lblDescription, 0, 2);

        txtDescription = new TextField();
        txtDescription.setImmediate(true);
        txtDescription.setWidth("100.0%");
        txtDescription.setHeight("-1px");
        addCategoryListLayout.addComponent(txtDescription, 1, 2);
        lblCreator = new Label();
        lblCreator.setImmediate(true);
        lblCreator.setWidth("100.0%");
        lblCreator.setHeight("-1px");
        lblCreator.setValue(BundleUtils.getString("label.CategoryList.creator"));
        addCategoryListLayout.addComponent(lblCreator, 2, 2);

        txtCreator = new TextField();
        txtCreator.setImmediate(true);
        txtCreator.setWidth("100.0%");
        txtCreator.setHeight("-1px");
        addCategoryListLayout.addComponent(txtCreator, 3, 2);

        mainLayout.addComponent(addCategoryListLayout);

        GridManyButton gridBtnPrint = new GridManyButton(new String[]{Constants.BUTTON_SAVE, Constants.BUTTON_CLOSE});
        mainLayout.addComponent(gridBtnPrint);
        btnSave = gridBtnPrint.getBtnCommon().get(0);
        btnClose = gridBtnPrint.getBtnCommon().get(1);
        setContent(mainLayout);
//        setCaption(CAPTION);
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

    public GridLayout getAddCategoryListLayout() {
        return addCategoryListLayout;
    }

    public void setAddCategoryListLayout(GridLayout addCategoryListLayout) {
        this.addCategoryListLayout = addCategoryListLayout;
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

    public DateField getTxtReceivedDate() {
        return txtReceivedDate;
    }

    public void setTxtReceivedDate(DateField txtReceivedDate) {
        this.txtReceivedDate = txtReceivedDate;
    }

    public DateField getTxtEndDate() {
        return txtEndDate;
    }

    public void setTxtEndDate(DateField txtEndDate) {
        this.txtEndDate = txtEndDate;
    }

    public TextField getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextField txtDescription) {
        this.txtDescription = txtDescription;
    }

    public TextField getTxtCreator() {
        return txtCreator;
    }

    public void setTxtCreator(TextField txtCreator) {
        this.txtCreator = txtCreator;
    }

}
