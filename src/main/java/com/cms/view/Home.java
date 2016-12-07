/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.cms.component.CommonFunction;
import com.cms.component.CommonFunctionTableFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.StatisticStaffPointDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.VerticalLayout;
import java.util.LinkedHashMap;
import java.util.List;

public class Home extends CommonOnePanelUI implements View {

    private VerticalLayout stasticPointLayout;
    private CommonTableFilterPanel tblPoints;
    private final LinkedHashMap<String, CustomTable.Align> HEADER
            = BundleUtils.getHeadersFilter("statisticStaffPoint.header");
    private List<StatisticStaffPointDTO> lstStatisticStaffPoint;
    private BeanItemContainer beanContainer;

    public Home() {
        initHome();
    }

    private void buildPointLayout() {
        stasticPointLayout = new VerticalLayout();
        
    }
    
    private void buildStatisticStaffPointTable() {
        beanContainer = new BeanItemContainer<>(StatisticStaffPointDTO.class);
        CommonFunctionTableFilter.initTable(tblPoints, HEADER, beanContainer, DESIGN_ATTR_PLAIN_TEXT, 0, DESIGN_ATTR_PLAIN_TEXT);
    }

    private void initHome() {
        String URL = "https://thongtindoanhnghiep.co/api/company?k=";
        BrowserFrame browser = new BrowserFrame("Tra cứu thông tin doanh nghiệp",
                new ExternalResource("https://thongtindoanhnghiep.co"));
//        BrowserFrame browser = new BrowserFrame("Tra cứu thông tin doanh nghiệp",
//                new ExternalResource("http://tracuunnt.gdt.gov.vn/tcnnt/mstdn.jsp"));
        browser.setWidth("50%");
        browser.setHeight("600px");

        mainLayout.addComponent(browser);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
