/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerDTO;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vwf5.base.utils.DataUtil;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 *
 * @author quyen
 */
public class CustomerDetailToCreateContractDialog extends CommonDialog {

    private TextField txtTaxCode;//MST
    private TextField txtName;//Ten cong ty
    private TextField txtTelNumber;//So dien thoai
    private TextField txtEmail;//Email
    private TextField txtFax;//Fax
    private TextField txtOfficeAddress;//Dia chi tru so
    private TextField txtDeployAddress;//Dia chi giao dich
    private TextField txtTaxDepartment;//Chi cuc thue
    //Thong tin nguoi dai dien
    private TextField txtTenNguoiDaidien;//Nguoi dai dien
    private TextField txtCMND;//Chung minh thu
    private ComboBox cboChuvuNguoiDaidien;//Chuc vu nguoi dai dien
    private TextField txtEmailNguoiDaidien;
    private TextField txtSDTNguoiDaidien;
    //Thong tin nguoi lien he
    private TextField txtTenNguoiLienhe;//Ten nguoi lien he
    private ComboBox cboChucvuNguoiLienhe;//Ten nguoi lien he
    private TextField txtSDTNguoiLienhe;//SDT nguoi lien he
    private TextField txtEmailNguoiLienhe;//Email nguoi lien he
    //Thong tin dich vu su dung
    private ComboBox cboNhacungcap;
    private ComboBox cboGoicuoc;

    private GridLayout gridThongtinChung;
    private GridLayout gridThongtinLienHe;
    private GridLayout gridGoicuoc;
    private Button btnCreateDoc;

    private List<AppParamsDTO> lstChucvu;
    private List<AppParamsDTO> lstProvider;
    private ComboComponent cboUtils;

    public CustomerDetailToCreateContractDialog() {
        super.setInfo("75%", "-1px", BundleUtils.getString("export.contract.template"));
        getDatas();
        buildComponents();
    }

    private void getDatas() {
        lstChucvu = com.cms.utils.DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        lstProvider = com.cms.utils.DataUtil.getListApParams(Constants.APP_PARAMS.PROVIDER);
        cboUtils = new ComboComponent();
    }

    private void buildComponents() {
        buildGridThongtinChung();
        buildGridThongtinLienHe();
        buildGridGoicuoc();
        buildGridButton();
    }

    private void buildGridThongtinChung() {
        gridThongtinChung = new GridLayout(4, 4);
        CommonUtils.setBasicAttributeLayout(gridThongtinChung, BundleUtils.getString("customer.common.infor"), true);
        txtTaxCode = CommonUtils.buildTextField("customer.taxCode", 14);

        txtName = CommonUtils.buildTextField("customer.name", 200);
        txtName.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                String code = event.getText();
                if (!DataUtil.isStringNullOrEmpty(code)) {
                    txtName.setValue(code.toUpperCase());
                }
            }
        });
        txtName.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        txtEmail = CommonUtils.buildTextField("customer.email", 50);
        txtTelNumber = CommonUtils.buildTextField("customer.telNumber", 50);
        txtFax = CommonUtils.buildTextField("customer.fax", 50);
        txtOfficeAddress = CommonUtils.buildTextField("customer.officeAddress", 500);
        txtDeployAddress = CommonUtils.buildTextField("customer.deployAddress", 500);
        txtTaxDepartment = CommonUtils.buildTextField("customer.taxAuthority", 100);

        txtTenNguoiDaidien = CommonUtils.buildTextField("customer.representativeName", 500);
        txtCMND = CommonUtils.buildTextField("customer.representativeId", 20);
        cboChuvuNguoiDaidien = CommonUtils.buildComboBox("customer.contact.regency");
        txtEmailNguoiDaidien = CommonUtils.buildTextField("customer.contact.email", 100);
        txtSDTNguoiDaidien = CommonUtils.buildTextField("customer.contact.telNumber", 100);

        cboUtils.fillDataCombo(cboChuvuNguoiDaidien, Constants.NULL, Constants.NULL, lstChucvu, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);

        gridThongtinChung.addComponent(txtTaxCode, 0, 0);
        gridThongtinChung.addComponent(txtName, 1, 0, 2, 0);
        gridThongtinChung.addComponent(txtEmail, 3, 0);

        gridThongtinChung.addComponent(txtDeployAddress, 0, 1, 1, 1);
        gridThongtinChung.addComponent(txtOfficeAddress, 2, 1, 3, 1);

        gridThongtinChung.addComponent(txtTelNumber, 0, 2);
        gridThongtinChung.addComponent(txtFax, 1, 2);
        gridThongtinChung.addComponent(txtTaxDepartment, 2, 2);
        gridThongtinChung.addComponent(txtCMND, 3, 2);

        gridThongtinChung.addComponent(txtTenNguoiDaidien, 0, 3);
        gridThongtinChung.addComponent(cboChuvuNguoiDaidien, 1, 3);
        gridThongtinChung.addComponent(txtSDTNguoiDaidien, 2, 3);
        gridThongtinChung.addComponent(txtEmailNguoiDaidien, 3, 3);

        mainLayout.addComponent(gridThongtinChung);
    }

    private void buildGridThongtinLienHe() {
        gridThongtinLienHe = new GridLayout(4, 1);
        CommonUtils.setBasicAttributeLayout(gridThongtinLienHe, BundleUtils.getString("customer.contact"), true);

        txtTenNguoiLienhe = CommonUtils.buildTextField("customerContact.name", 200);
        cboChucvuNguoiLienhe = CommonUtils.buildComboBox("customer.contact.regency");
        txtSDTNguoiLienhe = CommonUtils.buildTextField("customer.contact.telNumber", 50);
        txtEmailNguoiLienhe = CommonUtils.buildTextField("customer.contact.email", 100);

        cboUtils.fillDataCombo(cboChucvuNguoiLienhe, Constants.NULL, Constants.NULL, lstChucvu, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);

        gridThongtinLienHe.addComponent(txtTenNguoiLienhe, 0, 0);
        gridThongtinLienHe.addComponent(cboChucvuNguoiLienhe, 1, 0);
        gridThongtinLienHe.addComponent(txtSDTNguoiLienhe, 2, 0);
        gridThongtinLienHe.addComponent(txtEmailNguoiLienhe, 3, 0);

        mainLayout.addComponent(gridThongtinLienHe);
    }

    private void buildGridGoicuoc() {
        gridGoicuoc = new GridLayout(4, 1);
        CommonUtils.setBasicAttributeLayout(gridGoicuoc, BundleUtils.getString("information.package"), true);

        cboNhacungcap = CommonUtils.buildComboBox("term.information.provider");

        cboUtils.fillDataCombo(cboNhacungcap, Constants.NULL, Constants.NULL, lstProvider, Constants.APP_PARAMS.PROVIDER);

        cboGoicuoc = CommonUtils.buildComboBox("package");
        gridGoicuoc.addComponent(cboNhacungcap, 0, 0);
//        gridGoicuoc.addComponent(cboGoicuoc, 1, 0);
        mainLayout.addComponent(gridGoicuoc);
    }

    private void buildGridButton() {
        GridManyButton gridManyButton = CommonUtils.getCommonButtonDialog(this);
        btnCreateDoc = gridManyButton.getBtnCommon().get(0);
        mainLayout.addComponent(gridManyButton);
    }

    public void initDialog(CustomerDTO customer) {
        txtTaxCode.setValue(DataUtil.getStringNullOrZero(customer.getTaxCode()));
        if (!DataUtil.isStringNullOrEmpty(customer.getName())) {
            txtName.setValue(customer.getName().trim().toUpperCase());
        }
        getCompany(customer.getTaxCode());
    }

    public void addBtnCreateDocListener(Button.ClickListener e) {
        btnCreateDoc.addClickListener(e);
    }

    public void getCompany(String taxCode) {
        String url = "https://thongtindoanhnghiep.co/api/company/" + taxCode;
        try {
            String genreJson = IOUtils.toString(new URL(url), "UTF-8");
            JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            // get the title
            txtOfficeAddress.setValue(DataUtil.getStringNullOrZero((String) genreJsonObject.get("DiaChiCongTy")));
            txtDeployAddress.setValue(DataUtil.getStringNullOrZero((String) genreJsonObject.get("DiaChiNhanThongBaoThue")));
            txtTenNguoiDaidien.setValue(DataUtil.getStringNullOrZero((String) genreJsonObject.get("ChuSoHuu")));
            txtTelNumber.setValue(DataUtil.getStringNullOrZero((String) genreJsonObject.get("NoiDangKyQuanLy_DienThoai")));
            txtTaxDepartment.setValue(DataUtil.getStringNullOrZero((String) genreJsonObject.get("NoiDangKyQuanLy_CoQuanTitle")));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lay du lieu do nguoi dung nhap vao
     *
     * @return
     */
    public Map<String, String> getValueInputed() {
        Map<String, String> inputted = new HashMap<>();
        inputted.put(Constants.REPORT.TAX_CODE, DataUtil.getStringNullOrZero(txtTaxCode.getValue()));
        inputted.put(Constants.REPORT.NAME, DataUtil.getStringNullOrZero(txtName.getValue()));
        inputted.put(Constants.REPORT.OFFICE_ADDRESS, DataUtil.getStringNullOrZero(txtOfficeAddress.getValue()));
        inputted.put(Constants.REPORT.DEPLOY_ADDRESS, DataUtil.getStringNullOrZero(txtDeployAddress.getValue()));
        inputted.put(Constants.REPORT.TAX_DEPARTMENT, DataUtil.getStringNullOrZero(txtTaxDepartment.getValue()));
        inputted.put(Constants.REPORT.CMND, DataUtil.getStringNullOrZero(txtCMND.getValue()));
        inputted.put(Constants.REPORT.NGUOI_DAIDIEN, DataUtil.getStringNullOrZero(txtTenNguoiDaidien.getValue()));
        inputted.put(Constants.REPORT.EMAIL, DataUtil.getStringNullOrZero(txtEmail.getValue()));
        inputted.put(Constants.REPORT.TEL_NUMBER, DataUtil.getStringNullOrZero(txtTelNumber.getValue()));
        inputted.put(Constants.REPORT.FAX, DataUtil.getStringNullOrZero(txtFax.getValue()));
        inputted.put(Constants.REPORT.NGUOI_LIENHE, DataUtil.getStringNullOrZero(txtTenNguoiLienhe.getValue()));
        inputted.put(Constants.REPORT.SDT_NGUOI_LIENHE, DataUtil.getStringNullOrZero(txtSDTNguoiLienhe.getValue()));
        inputted.put(Constants.REPORT.SDT_NGUOI_DAIDIEN, DataUtil.getStringNullOrZero(txtSDTNguoiDaidien.getValue()));
        inputted.put(Constants.REPORT.EMAIL_NGUOI_DAIDIEN, DataUtil.getStringNullOrZero(txtEmailNguoiDaidien.getValue()));
        inputted.put(Constants.REPORT.EMAIL_NGUOI_LIENHE, DataUtil.getStringNullOrZero(txtEmailNguoiLienhe.getValue()));

        AppParamsDTO chucvuNguoiLienhe = (AppParamsDTO) cboChucvuNguoiLienhe.getValue();
        if (chucvuNguoiLienhe != null) {
            inputted.put(Constants.REPORT.CHUCVU_NGUOI_LIENHE, DataUtil.getStringNullOrZero(chucvuNguoiLienhe.getParName()));
        }
        AppParamsDTO chucvuNguoiDaidien = (AppParamsDTO) cboChuvuNguoiDaidien.getValue();
        if (chucvuNguoiDaidien != null) {
            inputted.put(Constants.REPORT.CHUVU_NGUOI_DAIDIEN, DataUtil.getStringNullOrZero(chucvuNguoiDaidien.getParName()));
        }

        AppParamsDTO nhacungcap = (AppParamsDTO) cboNhacungcap.getValue();
        inputted.put(Constants.REPORT.PROVIDER, DataUtil.getStringNullOrZero(nhacungcap.getParName()));

        return inputted;
    }
}
