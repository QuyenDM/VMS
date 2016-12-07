/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.cms.component.MappingCombobox;
import com.cms.dto.DepartmentDTO;
import com.cms.dto.StaffDTO;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.MakeURL;
import java.util.Locale;

/**
 *
 * @author HungKV
 */
public class DialogCreateStaff extends Window{

    @AutoGenerated
    private VerticalLayout mainLayout;
    @AutoGenerated
    private HorizontalLayout horizontalLayoutButton;
    @AutoGenerated
    private Button btnSave;
    @AutoGenerated
    private Button btnCancel;
    @AutoGenerated
    private GridLayout gridLayoutDepartInfo;
    @AutoGenerated
    private TextField txtContact;
    @AutoGenerated
    private Label lblStaffType;
    @AutoGenerated
    private TextField txtAddress;
    @AutoGenerated
    private TextField txtEmail;
    @AutoGenerated
    private PopupDateField pdfBirthDate;
    @AutoGenerated
    private Label lblFax;
    @AutoGenerated
    private TextField txtPhoneNumber;
    @AutoGenerated
    private Label lblPhoneNumber;
    @AutoGenerated
    private ComboBox cbxStatus;
    @AutoGenerated
    private ComboBox cbxStaffType;
    @AutoGenerated
    private Label lblStatus;
  
    @AutoGenerated
    private Label lblDescription;
    @AutoGenerated
    private TextField txtSuperiorUnit;
    @AutoGenerated
    private Label lblSuperiorUnit;
    @AutoGenerated
    private TextField txtStaffName;
    @AutoGenerated
    private Label lblDepartmentName;
    @AutoGenerated
    private TextField txtStaffCode;
    @AutoGenerated
    private Label lblDepartmentCode;
    StaffDTO staffDTO;
    DepartmentDTO deptDTO;
    private MappingCombobox comboDeptTopLevel;
    private MappingCombobox comboStock;
    Locale mlocale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
    String strValidator = BundleUtils.getString("common.validator");
    
//    Label lblVofficeAcc;
//    Label lblTtnsAcc;
//    Label lblOtherAcc;
//    TextField txtVofficeAcc;
//    TextField txtTtnsAcc;
//    TextField txtOtherAcc;
    /**
     * The constructor should first build the main layout, set the composition
     * root and then do any custom initialization.
     *
     * The constructor will not be automatically regenerated by the visual
     * editor.
     */
    public DialogCreateStaff(String captionPanel, StaffDTO staffDTO, DepartmentDTO deptDTO) {
        this.staffDTO = staffDTO;
        this.deptDTO = deptDTO;
        buildMainLayout();
        setContent(mainLayout);
        setWidth("800px");
        setHeight("-1px");
        setModal(true);
        setImmediate(true);
        setCaption(captionPanel);
        // TODO add user code here
    }

    @AutoGenerated
    private VerticalLayout buildMainLayout() {
        // common part: create layout
        mainLayout = new VerticalLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        setModal(true);
        // top-level component properties
        setWidth("800px");
        setHeight("-1px");

        // gridLayoutDepartInfo
        gridLayoutDepartInfo = buildGridLayoutDepartInfo();
        mainLayout.addComponent(gridLayoutDepartInfo);

        // horizontalLayoutButton
        horizontalLayoutButton = buildHorizontalLayoutButton();
        mainLayout.addComponent(horizontalLayoutButton);

        return mainLayout;
    }

    @AutoGenerated
    private GridLayout buildGridLayoutDepartInfo() {
        // common part: create layout
        gridLayoutDepartInfo = new GridLayout();
        gridLayoutDepartInfo.setStyleName("custom-feildset");
        gridLayoutDepartInfo.setCaption(MakeURL.makeURLForGrid(BundleUtils.getString("caption.title.emp.info")));
        gridLayoutDepartInfo.setCaptionAsHtml(true);
        gridLayoutDepartInfo.setImmediate(true);
        gridLayoutDepartInfo.setWidth("100.0%");
        gridLayoutDepartInfo.setHeight("-1px");
        gridLayoutDepartInfo.setMargin(true);
        gridLayoutDepartInfo.setSpacing(true);
        gridLayoutDepartInfo.setColumns(4);
        gridLayoutDepartInfo.setRows(7);

        // lblDepartmentCode
        lblDepartmentCode = new Label();
        lblDepartmentCode.setImmediate(false);
        lblDepartmentCode.setWidth("100.0%");
        lblDepartmentCode.setHeight("-1px");
        lblDepartmentCode.setValue(BundleUtils.getString("lb.deptstaff.emp.code"));
        gridLayoutDepartInfo.addComponent(lblDepartmentCode, 0, 0);

        // txtStaffCode
        txtStaffCode = new TextField();
        txtStaffCode.setImmediate(true);
        txtStaffCode.setWidth("100.0%");
        txtStaffCode.setHeight("-1px");
        txtStaffCode.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        txtStaffCode.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(final FieldEvents.TextChangeEvent event) {
                String code = event.getText();
                if (code != null) {
                    code = code.replaceAll(" ", "");
                    if (!DataUtil.isStringNullOrEmpty(code)) {
                        txtStaffCode.setValue(code.toUpperCase());
                    }
                }
            }
        });
        txtStaffCode.setMaxLength(50);
        txtStaffCode.addValidator(new RegexpValidator("^[a-zA-Z0-9-_]+$", BundleUtils.getString("lb.deptstaff.emp.code")+" "+BundleUtils.getString("message.error.code.format")));
        txtStaffCode.setRequired(true);
        gridLayoutDepartInfo.addComponent(txtStaffCode, 1, 0);

        // lblDepartmentName
        lblDepartmentName = new Label();
        lblDepartmentName.setImmediate(false);
        lblDepartmentName.setWidth("100.0%");
        lblDepartmentName.setHeight("-1px");
        lblDepartmentName.setValue(BundleUtils.getString("lb.deptstaff.emp.name"));
        gridLayoutDepartInfo.addComponent(lblDepartmentName, 2, 0);

        // txtStaffName
        txtStaffName = new TextField();
        txtStaffName.setImmediate(true);
        txtStaffName.setWidth("100.0%");
        txtStaffName.setHeight("-1px");
        txtStaffName.setMaxLength(200);
//        txtStaffName.addValidator(new StringLengthValidator(BundleUtils.getString("common.error.length"), 0, 200, true));
        txtStaffName.setRequired(true);
        gridLayoutDepartInfo.addComponent(txtStaffName, 3, 0);

        // lblSuperiorUnit
        lblSuperiorUnit = new Label();
        lblSuperiorUnit.setImmediate(false);
        lblSuperiorUnit.setWidth("100.0%");
        lblSuperiorUnit.setHeight("-1px");
        lblSuperiorUnit.setValue(BundleUtils.getString("lb.deptstaff.emp.dept"));
        gridLayoutDepartInfo.addComponent(lblSuperiorUnit, 0, 1);

        // txtSuperiorUnit
        txtSuperiorUnit = new TextField();
        txtSuperiorUnit.setImmediate(true);
        txtSuperiorUnit.setWidth("100.0%");
        txtSuperiorUnit.setHeight("-1px");
        comboDeptTopLevel = new MappingCombobox(3, 1);
//        comboDeptTopLevel.getNameCombo().setRequired(true);
        gridLayoutDepartInfo.addComponent(comboDeptTopLevel.getLayout(), 1, 1,3 ,1);
        
//        // lblStock
//        lblStock = new Label();
//        lblStock.setImmediate(false);
//        lblStock.setWidth("100.0%");
//        lblStock.setHeight("-1px");
//        lblStock.setVisible(false);
//        lblStock.setValue(BundleUtils.getString("lb.deptstaff.emp.stock"));
//        gridLayoutDepartInfo.addComponent(lblStock, 0, 2);
//
//        // txtSuperiorUnit
//        comboStock = new MappingCombobox(3, 1);
////        comboDeptTopLevel.getNameCombo().setRequired(true);
//        comboStock.setVisible(false);
//        gridLayoutDepartInfo.addComponent(comboStock.getLayout(), 1, 2,3 ,2);
        
        // lblSuperiorUnit
        lblStaffType = new Label();
        lblStaffType.setImmediate(false);
        lblStaffType.setWidth("100.0%");
        lblStaffType.setHeight("-1px");
        lblStaffType.setValue(BundleUtils.getString("lb.deptstaff.emp.type"));
//        gridLayoutDepartInfo.addComponent(lblStaffType, 0, 3);
        gridLayoutDepartInfo.addComponent(lblStaffType, 0, 2);

        // txtSuperiorUnit
        cbxStaffType = new ComboBox();
        cbxStaffType.setImmediate(true);
        cbxStaffType.setTextInputAllowed(false);
        cbxStaffType.setFilteringMode(FilteringMode.OFF);
        cbxStaffType.setWidth("100.0%");
        cbxStaffType.setHeight("-1px");
        cbxStaffType.setStyleName("notRequireStyle");
//        gridLayoutDepartInfo.addComponent(cbxStaffType, 1, 3);
        gridLayoutDepartInfo.addComponent(cbxStaffType, 1, 2);

        // lblDescription
        lblDescription = new Label();
        lblDescription.setImmediate(false);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("lb.deptstaff.common.phone"));
//        gridLayoutDepartInfo.addComponent(lblDescription, 0, 4);
        gridLayoutDepartInfo.addComponent(lblDescription, 0, 3);

        // textArea_1
        txtPhoneNumber = new TextField();
        txtPhoneNumber.setImmediate(true);
        txtPhoneNumber.setWidth("100.0%");
        txtPhoneNumber.setHeight("-1px");
        txtPhoneNumber.setMaxLength(100);
                StringBuilder messageErrorOrder = new StringBuilder();
        messageErrorOrder.append(BundleUtils.getString("lb.deptstaff.common.phone"));
        messageErrorOrder.append(BundleUtils.getString(" "));
        messageErrorOrder.append(BundleUtils.getString("message.error.phoneformat"));
        txtPhoneNumber.addValidator(new RegexpValidator("^\\(?(\\d{3,4})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", messageErrorOrder.toString()));        
        txtPhoneNumber.setInputPrompt(BundleUtils.getString("common.phone.format"));
        txtPhoneNumber.setStyleName("notRequireStyle");
//        gridLayoutDepartInfo.addComponent(txtPhoneNumber, 1, 4);
        gridLayoutDepartInfo.addComponent(txtPhoneNumber, 1, 3);

        // lblStatus
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("lb.deptstaff.common.status"));
//        gridLayoutDepartInfo.addComponent(lblStatus, 2, 3);
        gridLayoutDepartInfo.addComponent(lblStatus, 2, 2);

        // cbxStatus
        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(true);
        cbxStatus.setTextInputAllowed(false);
        cbxStatus.setFilteringMode(FilteringMode.OFF);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        cbxStatus.setRequired(true);
//        gridLayoutDepartInfo.addComponent(cbxStatus, 3, 3);
        gridLayoutDepartInfo.addComponent(cbxStatus, 3, 2);

        // lblPhoneNumber
        lblPhoneNumber = new Label();
        lblPhoneNumber.setImmediate(false);
        lblPhoneNumber.setWidth("100.0%");
        lblPhoneNumber.setHeight("-1px");
        lblPhoneNumber.setValue(BundleUtils.getString("lb.deptstaff.common.email"));
//        gridLayoutDepartInfo.addComponent(lblPhoneNumber, 2, 4);
        gridLayoutDepartInfo.addComponent(lblPhoneNumber, 2, 3);

        // txtEmail
        txtEmail = new TextField();
        txtEmail.setImmediate(true);
        txtEmail.addValidator(new EmailValidator(BundleUtils.getString("common.error.email")));
        txtEmail.setRequiredError(BundleUtils.getString("common.error.email"));
        txtEmail.setInputPrompt(BundleUtils.getString("common.email.hint.format"));
        txtEmail.setWidth("100.0%");
        txtEmail.setHeight("-1px");
        txtEmail.setMaxLength(100);
        txtEmail.setStyleName("notRequireStyle");
//        gridLayoutDepartInfo.addComponent(txtEmail, 3, 4);
        gridLayoutDepartInfo.addComponent(txtEmail, 3, 3);

        // lblFax
        lblFax = new Label();
        lblFax.setImmediate(false);
        lblFax.setWidth("100.0%");
        lblFax.setHeight("-1px");
        lblFax.setValue(BundleUtils.getString("lb.deptstaff.emp.birthDate"));
//        gridLayoutDepartInfo.addComponent(lblFax, 0, 5);
        gridLayoutDepartInfo.addComponent(lblFax, 0, 4);

        // pdfBirthDate
        pdfBirthDate = new PopupDateField();
        pdfBirthDate.setLocale(mlocale);
        pdfBirthDate.setImmediate(true);
        pdfBirthDate.setWidth("100.0%");
        pdfBirthDate.setHeight("-1px");
        pdfBirthDate.setStyleName("notRequireStyle");
        pdfBirthDate.setDateFormat("dd/MM/yyyy");
        CommonUtils.addDateValidator(pdfBirthDate);
//        gridLayoutDepartInfo.addComponent(pdfBirthDate, 1, 5);
        gridLayoutDepartInfo.addComponent(pdfBirthDate, 1, 4);
//        
//        lblVofficeAcc = new Label();
//        lblVofficeAcc.setImmediate(false);
//        lblVofficeAcc.setWidth("100.0%");
//        lblVofficeAcc.setHeight("-1px");
//        lblVofficeAcc.setValue("Tài khoản V-Office");
//        gridLayoutDepartInfo.addComponent(lblVofficeAcc,2,4);
        
//        txtVofficeAcc = new TextField();
//        txtVofficeAcc.setImmediate(true);
//        txtVofficeAcc.setWidth("100.0%");
//        txtVofficeAcc.setHeight("-1px");
//        txtVofficeAcc.setMaxLength(100);
//        gridLayoutDepartInfo.addComponent(txtVofficeAcc,3,4);
        //
        
//        lblTtnsAcc = new Label();
//        lblTtnsAcc.setImmediate(false);
//        lblTtnsAcc.setWidth("100.0%");
//        lblTtnsAcc.setHeight("-1px");
//        lblTtnsAcc.setValue("Tài khoản TTNS");
//        gridLayoutDepartInfo.addComponent(lblTtnsAcc,0,5);
        //
//        txtTtnsAcc = new TextField();
//        txtTtnsAcc.setImmediate(true);
//        txtTtnsAcc.setWidth("100.0%");
//        txtTtnsAcc.setHeight("-1px");
//        txtTtnsAcc.setMaxLength(100);
//        gridLayoutDepartInfo.addComponent(txtTtnsAcc,1,5);
//        //
//        
//        lblVofficeAcc = new Label();
//        lblVofficeAcc.setImmediate(false);
//        lblVofficeAcc.setWidth("100.0%");
//        lblVofficeAcc.setHeight("-1px");
//        lblVofficeAcc.setValue("Tài khoản khác");
//        gridLayoutDepartInfo.addComponent(lblVofficeAcc,2,5);
//        //
//        txtOtherAcc = new TextField();
//        txtOtherAcc.setImmediate(true);
//        txtOtherAcc.setWidth("100.0%");
//        txtOtherAcc.setHeight("-1px");
//        txtOtherAcc.setMaxLength(100);
//        gridLayoutDepartInfo.addComponent(txtOtherAcc,3,5);
        

        
        return gridLayoutDepartInfo;
    }

    @AutoGenerated
    private HorizontalLayout buildHorizontalLayoutButton() {
        // common part: create layout
        horizontalLayoutButton = new HorizontalLayout();
        horizontalLayoutButton.setImmediate(false);
        horizontalLayoutButton.setWidth("100.0%");
        horizontalLayoutButton.setHeight("-1px");
        horizontalLayoutButton.setMargin(true);
        horizontalLayoutButton.setSpacing(true);

        // btnSave
        btnSave = new Button();
        btnSave.setCaption(BundleUtils.getString("common.button.save"));
        btnSave.setImmediate(true);
        btnSave.setWidth("-1px");
        btnSave.setHeight("-1px");
        btnSave.setIcon(new ThemeResource(Constants.ICON.SAVE));
        horizontalLayoutButton.addComponent(btnSave);
        horizontalLayoutButton.setComponentAlignment(btnSave, new Alignment(34));

        // btnCancel
        btnCancel = new Button();
        btnCancel.setCaption(BundleUtils.getString("common.button.cancel"));
        btnCancel.setImmediate(true);
        btnCancel.setWidth("-1px");
        btnCancel.setHeight("-1px");
        btnCancel.setIcon(new ThemeResource(Constants.ICON.CANCEL));
        horizontalLayoutButton.addComponent(btnCancel);
        horizontalLayoutButton.setComponentAlignment(btnCancel, new Alignment(33));

        return horizontalLayoutButton;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public TextField getTxtContact() {
        return txtContact;
    }

    public void setTxtContact(TextField txtContact) {
        this.txtContact = txtContact;
    }

    public TextField getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(TextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public PopupDateField getPdfBirthDate() {
        return pdfBirthDate;
    }

    public void setPdfBirthDate(PopupDateField pdfBirthDate) {
        this.pdfBirthDate = pdfBirthDate;
    }

    public TextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public void setTxtPhoneNumber(TextField txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

    public ComboBox getCbxStaffType() {
        return cbxStaffType;
    }

    public void setCbxStaffType(ComboBox cbxStaffType) {
        this.cbxStaffType = cbxStaffType;
    }

    public TextField getTxtSuperiorUnit() {
        return txtSuperiorUnit;
    }

    public void setTxtSuperiorUnit(TextField txtSuperiorUnit) {
        this.txtSuperiorUnit = txtSuperiorUnit;
    }

    public TextField getTxtStaffName() {
        return txtStaffName;
    }

    public void setTxtStaffName(TextField txtStaffName) {
        this.txtStaffName = txtStaffName;
    }

    public TextField getTxtStaffCode() {
        return txtStaffCode;
    }

    public void setTxtStaffCode(TextField txtStaffCode) {
        this.txtStaffCode = txtStaffCode;
    }

    public StaffDTO getStaffDTO() {
        return staffDTO;
    }

    public void setStaffDTO(StaffDTO staffDTO) {
        this.staffDTO = staffDTO;
    }

    public MappingCombobox getComboDeptTopLevel() {
        return comboDeptTopLevel;
    }

    public void setComboDeptTopLevel(MappingCombobox comboDeptTopLevel) {
        this.comboDeptTopLevel = comboDeptTopLevel;
    }

    public DepartmentDTO getDeptDTO() {
        return deptDTO;
    }

    public void setDeptDTO(DepartmentDTO deptDTO) {
        this.deptDTO = deptDTO;
    }

    public Locale getMlocale() {
        return mlocale;
    }

    public void setMlocale(Locale mlocale) {
        this.mlocale = mlocale;
    }

    public String getStrValidator() {
        return strValidator;
    }

    public void setStrValidator(String strValidator) {
        this.strValidator = strValidator;
    }

    public MappingCombobox getComboStock() {
        return comboStock;
    }

    public void setComboStock(MappingCombobox comboStock) {
        this.comboStock = comboStock;
    }

//    public TextField getTxtVofficeAcc() {
//        return txtVofficeAcc;
//    }
//
//    public void setTxtVofficeAcc(TextField txtVofficeAcc) {
//        this.txtVofficeAcc = txtVofficeAcc;
//    }
//
//    public TextField getTxtTtnsAcc() {
//        return txtTtnsAcc;
//    }
//
//    public void setTxtTtnsAcc(TextField txtTtnsAcc) {
//        this.txtTtnsAcc = txtTtnsAcc;
//    }
//
//    public TextField getTxtOtherAcc() {
//        return txtOtherAcc;
//    }
//
//    public void setTxtOtherAcc(TextField txtOtherAcc) {
//        this.txtOtherAcc = txtOtherAcc;
//    }
//    
    
}
