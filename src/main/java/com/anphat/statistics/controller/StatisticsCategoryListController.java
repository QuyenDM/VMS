/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.statistics.controller;

import com.anphat.statistics.ui.StatisticsCategoryListSearchPanel;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.StaffDTO;
import com.cms.dto.StatisticsCategoryListDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSCustomerStatus;
import com.cms.utils.DataUtil;
import java.util.LinkedHashMap;
import com.cms.login.ws.WSStaff;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.Constants;
import com.cms.view.StatisticsCategoryListView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomTable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author quyen
 */
public class StatisticsCategoryListController {

    private final StatisticsCategoryListView viewStatisticsCategoryList;
    private StatisticsCategoryListSearchPanel searchForm;
    private List<CategoryListDTO> lstCategoryList;
    private List<StaffDTO> lstStaff;
    private ComboComponent comboBoxUtil;
    private final LinkedHashMap<String, CustomTable.Align> HEADER = BundleUtils.getHeadersFilter("statistics.categoryList.header");
    static final String CAPTION = BundleUtils.getString("title.statistics.categoryList");
    static final String LANG = "statistics.categoryList";
    static final int SIZE = 10;
    private BeanItemContainer tblContainer;
    private CustomPageTableFilter tblStatisticsCategoryList;
    private List<StatisticsCategoryListDTO> lstStatisticsCategoryListDTOs;
    private CommonTableFilterPanel panelStatisticsCategoryList;
    private StaffDTO staff;

    public StatisticsCategoryListController(StatisticsCategoryListView viewStatisticsCategoryList) {
        this.viewStatisticsCategoryList = viewStatisticsCategoryList;
        searchForm = viewStatisticsCategoryList.getCategoryListForm();
        init();
    }

    private void init() {
        getDatas();
        initSearchForm();
        initTable();
        addActionListeners();
    }

    private void initTable() {
        panelStatisticsCategoryList = viewStatisticsCategoryList.getPanelTblStatisticsCategoryList();
        tblStatisticsCategoryList = panelStatisticsCategoryList.getMainTable();
        tblStatisticsCategoryList.setMultiSelect(false);
        tblContainer = new BeanItemContainer<>(StatisticsCategoryListDTO.class);
        addColumnTotalQuanlity();
        CommonFunctionTableFilter.initTable(panelStatisticsCategoryList, HEADER, tblContainer, CAPTION, 10, LANG);
    }

    private void addColumnTotalQuanlity() {
        tblStatisticsCategoryList.addGeneratedColumn("totalQuanlity", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                Long totalQuanlity = 0L;
                StatisticsCategoryListDTO scld = (StatisticsCategoryListDTO) itemId;
                totalQuanlity += Long.parseLong(scld.getStatus1());
                totalQuanlity += Long.parseLong(scld.getStatus2());
                totalQuanlity += Long.parseLong(scld.getStatus3());
                totalQuanlity += Long.parseLong(scld.getStatus4());
                totalQuanlity += Long.parseLong(scld.getStatus5());
                totalQuanlity += Long.parseLong(scld.getStatus6());
                totalQuanlity += Long.parseLong(scld.getStatus7());
                totalQuanlity += Long.parseLong(scld.getStatus8());
                totalQuanlity += Long.parseLong(scld.getStatus9());
                totalQuanlity += Long.parseLong(scld.getStatus10());
                totalQuanlity += Long.parseLong(scld.getStatus11());
                totalQuanlity += Long.parseLong(scld.getStatus12());
                totalQuanlity += Long.parseLong(scld.getStatus13());
                totalQuanlity += Long.parseLong(scld.getStatus14());
                return totalQuanlity;
            }
        });
    }

    private void getDatas() {
        comboBoxUtil = new ComboComponent();
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        try {
            lstCategoryList = WSCategoryList.getListCategoryListDTO(categoryListDTO, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            StaffDTO search = new StaffDTO();
            search.setStaffType(Constants.STAFF.STAFF_TYPE_3);
            lstStaff = WSStaff.getListStaffDTO(search, 0, Constants.INT_100, "asc", "code");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSearchForm() {
        comboBoxUtil.setValues(searchForm.getCboMineName(), lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);
        comboBoxUtil.setValues(searchForm.getCboStaff(), lstStaff, Constants.STAFF.CODE, Boolean.TRUE);
    }

    //An cbo staff neu khong phai admin
    public void setStaff(StaffDTO staff) {
        this.staff = staff;
        if (!DataUtil.isAdmin(staff)) {
            for (StaffDTO st : lstStaff) {
                if (staff.getStaffId().equalsIgnoreCase(st.getStaffId())) {
                    searchForm.getCboStaff().select(st);
                    searchForm.getCboStaff().setVisible(false);
                    break;
                }
            }
        }
    }

    private void addActionListeners() {
        searchForm.getBtnSearch().addClickListener(new CommonButtonClickListener() {

            @Override
            public void execute() throws Exception {
                String categoryListId = "";
                String startTime;
                String endTime;
                String staffCode = "";
                CategoryListDTO cld = (CategoryListDTO) searchForm.getCboMineName().getValue();
                if (!DataUtil.isStringNullOrEmpty(cld.getId())) {
                    categoryListId = cld.getId();
                }
                startTime = DataUtil.getDateNullOrZero(searchForm.getDfStartTime());
                endTime = DataUtil.getDateNullOrZero(searchForm.getDfEndTime());
                if (!DataUtil.isAdmin(staff)) {
                    staffCode = staff.getCode();
                } else {
                    StaffDTO staffDTO = (StaffDTO) searchForm.getCboStaff().getValue();
                    if (!DataUtil.isStringNullOrEmpty(staffDTO.getStaffId())) {
                        staffCode = staffDTO.getCode();
                    }
                }
                doSearch(staffCode, categoryListId, startTime, endTime);
            }
        });

        searchForm.btnReset.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() throws Exception {
                searchForm.refreshData();
                comboBoxUtil.setValues(searchForm.getCboMineName(), lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);
                comboBoxUtil.setValues(searchForm.getCboStaff(), lstStaff, Constants.STAFF.CODE, Boolean.TRUE);
            }
        });
    }

    private void doSearch(String staffCode, String categoryListId, String startTime, String endTime) {
        lstStatisticsCategoryListDTOs
                = WSCustomerStatus.getStatisticsCategoryListByStaff(staffCode, categoryListId, startTime, endTime);
        setData2TableStatisticsCategoryList(lstStatisticsCategoryListDTOs);
    }

    private void setData2TableStatisticsCategoryList(List<StatisticsCategoryListDTO> lstStatisticsCategoryList) {
        tblContainer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstStatisticsCategoryList)) {
            tblContainer.addAll(lstStatisticsCategoryList);
        }
        CommonFunctionTableFilter.refreshTable(panelStatisticsCategoryList, HEADER, tblContainer);
    }
}
