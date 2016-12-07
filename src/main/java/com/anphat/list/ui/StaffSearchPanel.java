package com.anphat.list.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.cms.component.MappingCombobox;
import com.cms.utils.BundleUtils;
import com.cms.utils.MakeURL;

public class StaffSearchPanel extends CustomComponent {

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
    @AutoGenerated
    private VerticalLayout mainEmpLayout;
    @AutoGenerated
    private GridLayout gridEmpLayout;
    @AutoGenerated
    private TextField txtDeptPhone;
    @AutoGenerated
    private Label lblDeptPhone;
    @AutoGenerated
    private TextField txtStaffEmail;
    @AutoGenerated
    private Label lblStaffEmail;
    @AutoGenerated
    private ComboBox cboStatus;
    @AutoGenerated
    private Label lblStatus;
    @AutoGenerated
    private HorizontalLayout horizontalLayoutEmp;
    @AutoGenerated
    private Label lblEmpDept;
    @AutoGenerated
    private HorizontalLayout horizontalLayoutEmp1;
    @AutoGenerated
    private ComboBox cboEmpType;
    @AutoGenerated
    private Label lblEmpType;
    @AutoGenerated
    private TextField txtEmpName;
    @AutoGenerated
    private Label lblEmpName;
    @AutoGenerated
    private TextField txtEmpCode;
    @AutoGenerated
    private Label lblEmpCode;
    //f9
    MappingCombobox f9Departments;
    Button btnReset;
    /**
     * The constructor should first build the main layout, set the composition
     * root and then do any custom initialization.
     *
     * The constructor will not be automatically regenerated by the visual
     * editor.
     */
    public StaffSearchPanel() {
        buildMainLayout();
        setCompositionRoot(mainEmpLayout);

        // TODO add user code here
    }

    @AutoGenerated
    private VerticalLayout buildMainLayout() {
        // common part: create layout
        mainEmpLayout = new VerticalLayout();
        mainEmpLayout.setImmediate(false);
        mainEmpLayout.setWidth("100%");
        mainEmpLayout.setHeight("100%");
        mainEmpLayout.setMargin(true);

        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");

        // gridEmpLayout
        gridEmpLayout = buildGridLayoutEmp();
        mainEmpLayout.addComponent(gridEmpLayout);

        return mainEmpLayout;
    }

    @AutoGenerated
    private GridLayout buildGridLayoutEmp() {
        // common part: create layout
        gridEmpLayout = new GridLayout();
        gridEmpLayout.setStyleName("custom-feildset");
        gridEmpLayout.setCaption(MakeURL.makeURLForGrid(BundleUtils.getString("caption.title.emp.info")));
        gridEmpLayout.setCaptionAsHtml(true);
        gridEmpLayout.setImmediate(false);
        gridEmpLayout.setWidth("100.0%");
        gridEmpLayout.setHeight("-1px");
        gridEmpLayout.setMargin(true);
        gridEmpLayout.setSpacing(true);
        gridEmpLayout.setColumns(4);
        gridEmpLayout.setRows(3);

        // lblEmpCode
        lblEmpCode = new Label();
        lblEmpCode.setImmediate(false);
        lblEmpCode.setWidth("100%");
        lblEmpCode.setHeight("-1px");
        lblEmpCode.setValue(BundleUtils.getString("lb.deptstaff.emp.code"));
        gridEmpLayout.addComponent(lblEmpCode, 0, 0);

        // txtEmpCode
        txtEmpCode = new TextField();
        txtEmpCode.setImmediate(false);
        txtEmpCode.setWidth("100%");
        txtEmpCode.setHeight("-1px");
        txtEmpCode.setRequired(false);
        gridEmpLayout.addComponent(txtEmpCode, 1, 0);

        // lblEmpName
        lblEmpName = new Label();
        lblEmpName.setImmediate(false);
        lblEmpName.setWidth("100%");
        lblEmpName.setHeight("-1px");
        lblEmpName.setValue(BundleUtils.getString("lb.deptstaff.emp.name"));
        gridEmpLayout.addComponent(lblEmpName, 2, 0);

        // txtEmpCode
        txtEmpName = new TextField();
        txtEmpName.setImmediate(false);
        txtEmpName.setWidth("100%");
        txtEmpName.setHeight("-1px");
        txtEmpName.setRequired(false);
        gridEmpLayout.addComponent(txtEmpName, 3, 0);

        // lblEmpDept
        lblEmpDept = new Label();
        lblEmpDept.setImmediate(false);
        lblEmpDept.setWidth("100%");
        lblEmpDept.setHeight("-1px");
        lblEmpDept.setValue(BundleUtils.getString("lb.deptstaff.emp.dept"));
        gridEmpLayout.addComponent(lblEmpDept, 0, 1);

        // horizontalLayoutEmp
        f9Departments = new MappingCombobox(3, 1);
        gridEmpLayout.addComponent(f9Departments.getLayout(), 1, 1, 3, 1);
        gridEmpLayout.setComponentAlignment(f9Departments.getLayout(), Alignment.BOTTOM_LEFT);
        // horizontalLayoutEmp1
        horizontalLayoutEmp1 = buildHorizontalLayout_1();

        // horizontalLayoutEmp
        

        // lblStatus
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("lb.deptstaff.common.status"));
        gridEmpLayout.addComponent(lblStatus, 0, 2);

        // cboStatus
        cboStatus = new ComboBox();
        cboStatus.setImmediate(true);
        cboStatus.setTextInputAllowed(false);
        cboStatus.setFilteringMode(FilteringMode.OFF);
        cboStatus.setWidth("100%");
        cboStatus.setHeight("-1px");
        cboStatus.setRequired(false);
        gridEmpLayout.addComponent(cboStatus, 1, 2);

        // lblEmpType
        lblEmpType = new Label();
        lblEmpType.setImmediate(false);
        lblEmpType.setWidth("100%");
        lblEmpType.setHeight("-1px");
        lblEmpType.setValue(BundleUtils.getString("lb.deptstaff.emp.type"));
        gridEmpLayout.addComponent(lblEmpType, 2, 2);

        cboEmpType = new ComboBox();
        cboEmpType.setImmediate(true);
        cboEmpType.setTextInputAllowed(false);
        cboEmpType.setFilteringMode(FilteringMode.OFF);
        cboEmpType.setWidth("100%");
        cboEmpType.setHeight("-1px");
        cboEmpType.setRequired(false);
        gridEmpLayout.addComponent(cboEmpType, 3, 2);

            // btnReset
//        GridLayout button = new GridLayout(1, 1);
//        button.setWidth("100%");
//        button.setHeight("-1px");
//        btnReset = new Button();
//        btnReset.setStyleName("v-button-link");
//        btnReset.setIcon(new ThemeResource("img/reset_icon.png"));
//        btnReset.setImmediate(true);
//        btnReset.setHeight("-1px");
//        btnReset.setDescription(Constants.BUTTON_REFRESH);
//        button.addComponent(btnReset);
//        button.setComponentAlignment(btnReset, Alignment.TOP_RIGHT);
//        gridEmpLayout.addComponent(button, 3, 4);
        
        
        return gridEmpLayout;
    }

    @AutoGenerated
    private HorizontalLayout buildHorizontalLayout_1() {
        // common part: create layout
        horizontalLayoutEmp1 = new HorizontalLayout();
        horizontalLayoutEmp1.setImmediate(false);
        horizontalLayoutEmp1.setWidth("100.0%");
        horizontalLayoutEmp1.setHeight("-1px");
        horizontalLayoutEmp1.setMargin(false);

        // cboEmpType
        cboEmpType = new ComboBox();
        cboEmpType.setImmediate(false);
        cboEmpType.setWidth("-1px");
        cboEmpType.setHeight("-1px");
        horizontalLayoutEmp1.addComponent(cboEmpType);

        return horizontalLayoutEmp1;
    }

    public TextField getTxtDeptPhone() {
        return txtDeptPhone;
    }

    public void setTxtDeptPhone(TextField txtDeptPhone) {
        this.txtDeptPhone = txtDeptPhone;
    }

    public TextField getTxtStaffEmail() {
        return txtStaffEmail;
    }

    public void setTxtStaffEmail(TextField txtStaffEmail) {
        this.txtStaffEmail = txtStaffEmail;
    }

    public ComboBox getCboStatus() {
        return cboStatus;
    }

    public void setCboStatus(ComboBox cboStatus) {
        this.cboStatus = cboStatus;
    }

    public ComboBox getCboEmpType() {
        return cboEmpType;
    }

    public void setCboEmpType(ComboBox cboEmpType) {
        this.cboEmpType = cboEmpType;
    }

    public TextField getTxtEmpName() {
        return txtEmpName;
    }

    public void setTxtEmpName(TextField txtEmpName) {
        this.txtEmpName = txtEmpName;
    }

    public TextField getTxtEmpCode() {
        return txtEmpCode;
    }

    public void setTxtEmpCode(TextField txtEmpCode) {
        this.txtEmpCode = txtEmpCode;
    }

    public MappingCombobox getF9Departments() {
        return f9Departments;
    }

    public void setF9Departments(MappingCombobox f9Departments) {
        this.f9Departments = f9Departments;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(Button btnReset) {
        this.btnReset = btnReset;
    }

}
