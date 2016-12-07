/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.PopupAddCategoryList;
import com.cms.common.controller.ConmonController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.StaffDTO;
import com.cms.service.AppParamsServiceImpl;
import com.cms.service.CategoryListServiceImpl;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.cms.utils.ShortcutUtils;
import com.cms.utils.TableUtils;
import com.cms.view.CategoryListView;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author
 */
public class CategoryListController extends ConmonController<CategoryListDTO> {

    private CategoryListView categoryListView;
    private CommonTableFilterPanel panelCategoryList;
    private CustomPageTableFilter<IndexedContainer> tblCategoryList;
    private BeanItemContainer beanItemContainerCategoryList;
    private List<CategoryListDTO> lstCategoryListDTO = Lists.newArrayList();
    private CategoryListServiceImpl serviceCategoryList = new CategoryListServiceImpl();
    private AppParamsServiceImpl serviceAppParams = new AppParamsServiceImpl();
    private String lblDelete = "delete";
    private String lblEdit = "edit";
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("categoryList.header");
    private TableUtils tableUtils = new TableUtils();
    private PopupAddCategoryList popupAddCategoryList;
    private StaffDTO staff;
    private boolean isAddSuccessed;

    private ComboBox cboMineName;

    public CategoryListController(CategoryListView categoryListView) {
        super(CategoryListDTO.class);
        this.categoryListView = categoryListView;
        panelCategoryList = categoryListView.getTblCategoryList();
        tblCategoryList = categoryListView.getTblCategoryList().getMainTable();
        init();
    }

    public CategoryListController() {
        super(CategoryListDTO.class);
    }

    public void init() {
        initButton();
        getDataWS();
        initComboBox();
        initTable();
    }

    public void initComboBox() {
    }

    public void getDataWS() {
        staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
    }

    public void initButton() {
        ShortcutUtils.setShortcutKey(categoryListView.getBtnSearch());
        categoryListView.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearch();
                event.getButton().setEnabled(true);
            }
        });
        categoryListView.getBtnRefresh().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                categoryListView.getTxtCode().clear();
                categoryListView.getTxtName().clear();
                categoryListView.getDfReceivedDate().clear();
                categoryListView.getDfEndDate().clear();
                categoryListView.getTxtDescription().clear();
                categoryListView.getTxtCreator().clear();
                event.getButton().setEnabled(true);
            }
        });
        ShortcutUtils.setShortkeyAddNew(panelCategoryList.getAddButton());
        panelCategoryList.getAddButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                InsertOrUpdate(new CategoryListDTO(), false);
                event.getButton().setEnabled(true);
            }
        });

        panelCategoryList.getEditButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<CategoryListDTO> selected = (Set) tblCategoryList.getValue();
                List<CategoryListDTO> lstSelecteds = Lists.newArrayList(selected);
                if (!DataUtil.isListNullOrEmpty(lstSelecteds)) {
                    InsertOrUpdate(lstSelecteds.get(0), false);
                } else {
                    CommonUtils.showChoseOne();
                }
                event.getButton().setEnabled(true);
            }
        });

        panelCategoryList.getCoppyButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<CategoryListDTO> selected = (Set) tblCategoryList.getValue();
                List<CategoryListDTO> lstSelecteds = Lists.newArrayList(selected);
                if (!DataUtil.isListNullOrEmpty(lstSelecteds)) {
                    InsertOrUpdate(lstSelecteds.get(0), true);
                } else {
                    CommonUtils.showChoseOne();
                }
                event.getButton().setEnabled(true);
            }
        });
    }

    public void InsertOrUpdate(final CategoryListDTO categoryListDTO, boolean isCopy) {
        if (staff == null) {
            getDataWS();
        }
        popupAddCategoryList = new PopupAddCategoryList();

        if (categoryListDTO.getCode() == null || isCopy) {
            popupAddCategoryList.setCaption(BundleUtils.getString("caption.add.mineName"));
            popupAddCategoryList.getTxtCode().setValue("");
//            UI.getCurrent().push();
        } else {
            popupAddCategoryList.setCaption(BundleUtils.getString("button.edit.mineName"));
            popupAddCategoryList.getTxtCode().setValue(categoryListDTO.getCode());
        }
        if (categoryListDTO.getName() != null) {
            popupAddCategoryList.getTxtName().setValue(categoryListDTO.getName());
        } else {
            popupAddCategoryList.getTxtName().setValue("");
        }
        if (categoryListDTO.getReceivedDate() != null) {
            popupAddCategoryList.getTxtReceivedDate().setValue(DateUtil.string2DateByPattern(categoryListDTO.getReceivedDate(), DateUtil.DATE_FM_DD_MM_YYYY));
        } else {
            popupAddCategoryList.getTxtReceivedDate().setValue(new Date());
        }
        if (categoryListDTO.getEndDate() != null) {
            popupAddCategoryList.getTxtEndDate().setValue(DateUtil.string2DateByPattern(categoryListDTO.getEndDate(), DateUtil.DATE_FM_DD_MM_YYYY));
        } else {
            popupAddCategoryList.getTxtEndDate().setValue(new Date());
        }
        if (categoryListDTO.getDescription() != null) {
            popupAddCategoryList.getTxtDescription().setValue(categoryListDTO.getDescription());
        } else {
            popupAddCategoryList.getTxtDescription().setValue("");
        }
        popupAddCategoryList.getTxtCreator().setEnabled(false);
        if (categoryListDTO.getCreator() != null) {
            popupAddCategoryList.getTxtCreator().setValue(categoryListDTO.getCreator());
        } else {
            popupAddCategoryList.getTxtCreator().setValue(DataUtil.getStringNullOrZero(staff.getCode()));
        }
        ShortcutUtils.setShortcutKey(popupAddCategoryList.getBtnSave());
        popupAddCategoryList.getBtnSave().addClickListener(new CommonButtonClickListener() {
            String code;
            String name;

            @Override
            public boolean isValidated() {
                code = DataUtil.getStringNullOrZero(popupAddCategoryList.getTxtCode().getValue());
                name = DataUtil.getStringNullOrZero(popupAddCategoryList.getTxtName().getValue());
                if (DataUtil.isStringNullOrEmpty(code)) {
                    CommonMessages.showMessageRequired("label.CategoryList.code");
                    popupAddCategoryList.getTxtCode().focus();
                    return false;
                }
                if (DataUtil.isStringNullOrEmpty(name)) {
                    CommonMessages.showMessageRequired("label.CategoryList.name");
                    popupAddCategoryList.getTxtName().focus();
                    return false;
                }
                return true;
            }

            @Override
            public void execute() {
                categoryListDTO.setCode(code);
                categoryListDTO.setName(name);
                categoryListDTO.setReceivedDate(DataUtil.getDateNullOrZero(popupAddCategoryList.getTxtReceivedDate()));
                categoryListDTO.setEndDate(DataUtil.getDateNullOrZero(popupAddCategoryList.getTxtEndDate()));
                categoryListDTO.setDescription(popupAddCategoryList.getTxtDescription().getValue());
                categoryListDTO.setCreator(popupAddCategoryList.getTxtCreator().getValue());
                if (categoryListDTO.getId() == null) {
                    try {
                        ResultDTO resultDTO = serviceCategoryList.insertCategoryList(categoryListDTO);
                        if (resultDTO.getMessage().equals(Constants.SUCCESS)) {
                            isAddSuccessed = true;
                            //Neu duoc add tu dialog khac
                            if (cboMineName != null) {
                                List<CategoryListDTO> lstMineNames = new ArrayList<>();
                                lstMineNames.addAll((Collection<? extends CategoryListDTO>) cboMineName.getItemIds());
                                lstMineNames.add(categoryListDTO);
                                ComboComponent component = new ComboComponent();
                                component.setValues(cboMineName, lstMineNames, Constants.CATEGORY_LIST.NAME);
                                cboMineName.select(categoryListDTO);
                            }
                            popupAddCategoryList.close();
                            CommonMessages.showMessageInsertSuccess("categoryList");
//                            Notification.show(BundleUtils.getString("categoryList.insert.success"));
                        } else {
                            CommonMessages.showInsertFail(BundleUtils.getString("categoryList"));
                            popupAddCategoryList.getTxtCode().focus();
//                            Notification.show(BundleUtils.getString("categoryList.insert.fail"));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        CommonMessages.showInsertFail(BundleUtils.getString("categoryList"));
                        popupAddCategoryList.getTxtCode().focus();
//                        Notification.show(BundleUtils.getString("categoryList.insert.fail"));
                    }

                } else {
                    try {
                        String message = serviceCategoryList.updateCategoryList(categoryListDTO);
                        if (message.equals(Constants.SUCCESS)) {
                            isAddSuccessed = true;
                            popupAddCategoryList.close();
                            CommonMessages.showMessageUpdateSuccess("categoryList");
//                            Notification.show(BundleUtils.getString("categoryList.update.success"));
                        } else {
                            CommonMessages.showUpdateFail(BundleUtils.getString("categoryList"));
                            popupAddCategoryList.getTxtCode().focus();
//                            CommonMessages.showMessageUpdateSuccess("categoryList");
//                            Notification.show(BundleUtils.getString("categoryList.update.fail"));
                        }
                    } catch (Exception e) {
                        CommonMessages.showUpdateFail(BundleUtils.getString("categoryList"));
                        popupAddCategoryList.getTxtCode().focus();
//                        Notification.show(BundleUtils.getString("categoryList.update.fail"));
                    }
                }

//                categoryListView.getBtnSearch().setEnabled(true);
            }
        });
        popupAddCategoryList.getBtnClose().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popupAddCategoryList.close();
            }
        });
        DataUtil.addFocusWindow(popupAddCategoryList, popupAddCategoryList.getTxtCode());
        UI.getCurrent().addWindow(popupAddCategoryList);
    }

    public void initTable() {
        beanItemContainerCategoryList = new BeanItemContainer<>(CategoryListDTO.class);

        tblCategoryList.setSortDisabled(true);
        tblCategoryList.addGeneratedColumn(lblDelete, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final CategoryListDTO categoryListDTO = (CategoryListDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_delete.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("titleMessage"), BundleUtils.getString("bodyMessage"),
                                BundleUtils.getString("yes"), BundleUtils.getString("no"), new ConfirmDialog.Listener() {
                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    categoryListDTO.setEndDate(DateUtil.date2ddMMyyyyHHMMss(new Date()));
                                    String message = serviceCategoryList.updateCategoryList(categoryListDTO);
                                    if (message.equals(Constants.SUCCESS)) {
                                        Notification.show(BundleUtils.getString("delete.success"));
                                        tblCategoryList.removeItem(itemId);
                                    } else {
                                        Notification.show(BundleUtils.getString("delete.fail"));
                                    }
                                } else {
                                    // User did not confirm
                                }
                            }
                        });
                    }
                });

                return image;
            }

        });
        tblCategoryList.addGeneratedColumn(lblEdit, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final CategoryListDTO categoryListDTO = (CategoryListDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_edit.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        InsertOrUpdate(categoryListDTO, false);
                    }
                });

                return image;
            }

        });
//        tableUtils.generateColumn(tblCategoryList);
        CommonUtils.setVisibleBtnTablePanel(panelCategoryList, true, false, true, true);
        CommonFunctionTableFilter.initTable(panelCategoryList, headerData, beanItemContainerCategoryList, BundleUtils.getString("table.list.categoryList"), 10, "categoryList.header", true, true, false, false, false);
//        tblCategoryList.setVisibleColumns(headerCategoryList);

    }

    @Override
    public void onDoSearch() {
        List<ConditionBean> lstConditions = getLstConditionBeanSearch();
        if (DataUtil.isListNullOrEmpty(lstConditions)) {
            lstCategoryListDTO = serviceCategoryList.getListCategoryListDTO(new CategoryListDTO(), 0, Constants.INT_100, "", "code");
        } else {
            try {
                lstCategoryListDTO = serviceCategoryList.getListCategoryListByCondition(lstConditions, 0, Integer.MAX_VALUE, "", "code");
            } catch (Exception e) {
                lstCategoryListDTO = Lists.newArrayList();
            }
        }
        if (DataUtil.isListNullOrEmpty(lstCategoryListDTO)) {
            CommonMessages.showDataNotFound();
        }
        beanItemContainerCategoryList.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstCategoryListDTO)) {
            beanItemContainerCategoryList.addAll(lstCategoryListDTO);
        }
        CommonFunctionTableFilter.refreshTable(panelCategoryList, headerData, beanItemContainerCategoryList);
//        panelCategoryList.getMainTable().setVisibleColumns(headerCategoryList);
        categoryListView.getBtnSearch().setEnabled(true);
    }

    public List<ConditionBean> getLstConditionBeanSearch() {
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        if (!DataUtil.isStringNullOrEmpty(categoryListView.getTxtCode().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("code");
            conditionBean.setValue(categoryListView.getTxtCode().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(categoryListView.getTxtName().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("name");
            conditionBean.setValue(categoryListView.getTxtName().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(categoryListView.getDfReceivedDate().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("receivedDate");
            Date date = categoryListView.getDfReceivedDate().getValue();
            conditionBean.setValue(DateUtil.date2ddMMyyyyString(date));
            conditionBean.setOperator(Constants.OPERATOR.NAME_GREATER_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_DATE);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(categoryListView.getDfEndDate().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("endDate");
            Date date = categoryListView.getDfEndDate().getValue();
            conditionBean.setValue(DateUtil.date2ddMMyyyyString(date));
            conditionBean.setOperator(Constants.OPERATOR.NAME_GREATER_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_DATE);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(categoryListView.getTxtDescription().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("description");
            conditionBean.setValue(categoryListView.getTxtDescription().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(categoryListView.getTxtCreator().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("creator");
            conditionBean.setValue(categoryListView.getTxtCreator().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        return lstConditionBean;

    }

    public boolean isAddSuccessed() {
        return isAddSuccessed;
    }

    public ComboBox getCboMineName() {
        return cboMineName;
    }

    public void setCboMineName(ComboBox cboMineName) {
        this.cboMineName = cboMineName;
    }

}
