package com.cms.component;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

public class GridOneButton extends CustomComponent {

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
    @AutoGenerated
    private VerticalLayout mainLayout;
    @AutoGenerated
    private GridLayout gridButton;
    @AutoGenerated
    private Button btnCommon;

    /**
     * The constructor should first build the main layout, set the composition
     * root and then do any custom initialization.
     *
     * The constructor will not be automatically regenerated by the visual
     * editor.
     * @param commonBtn
     */
    public GridOneButton(String commonBtn) {
        buildMainLayout(commonBtn);
        setCompositionRoot(mainLayout);

        // TODO add user code here
    }

    @AutoGenerated
    private VerticalLayout buildMainLayout(String commonBtn) {
        // common part: create layout
        mainLayout = new VerticalLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(false);

        // top-level component properties
        setWidth("100.0%");
        setHeight("-1px");

        // gridButton
        gridButton = buildGridButton(commonBtn);
        mainLayout.addComponent(gridButton);
        mainLayout.setComponentAlignment(gridButton, new Alignment(48));

        return mainLayout;
    }

    @AutoGenerated
    private GridLayout buildGridButton(String commonBtn) {
        // common part: create layout
        gridButton = new GridLayout();
        gridButton.setImmediate(false);
        gridButton.setWidth("-1px");
        gridButton.setHeight("-1px");
        gridButton.setMargin(false);

        // btnSearch
        btnCommon = new Button();
        btnCommon.setCaption(commonBtn);
        btnCommon.setImmediate(true);
        btnCommon.setWidth("100.0%");
        btnCommon.setHeight("-1px");
        gridButton.addComponent(btnCommon, 0, 0);
        gridButton.setComponentAlignment(btnCommon, Alignment.MIDDLE_CENTER);

        return gridButton;
    }

    public Button getBtnCommon() {
        return btnCommon;
    }

    public void setBtnCommon(Button btnCommon) {
        this.btnCommon = btnCommon;
    }

    public void setAlignment(String align) {
        switch (align) {
            case "center":
                mainLayout.setComponentAlignment(gridButton, Alignment.BOTTOM_CENTER);
                break;
            case "left":
                mainLayout.setComponentAlignment(gridButton, Alignment.BOTTOM_LEFT);
                break;
            case "right":
                mainLayout.setComponentAlignment(gridButton, Alignment.BOTTOM_RIGHT);
                break;
        }
    }
}
