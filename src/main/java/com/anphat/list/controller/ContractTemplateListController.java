/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.PopupAddContractTemplateList;
import com.cms.common.controller.ConmonController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import org.vaadin.dialogs.ConfirmDialog;
import java.util.Date;
import com.vaadin.ui.UI;
import com.cms.dto.ContractTemplateListDTO;
import com.cms.service.AppParamsServiceImpl;
import com.cms.service.ContractTemplateListServiceImpl;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.cms.utils.TableUtils;
import java.util.Map;
import com.cms.view.ContractTemplateListView;
import java.util.LinkedHashMap;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.text.ParseException;
import com.vaadin.ui.Image;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 *
 * @author QuyenDM
 */
public class ContractTemplateListController extends ConmonController<ContractTemplateListDTO> {

    private ContractTemplateListView contractTemplateListView;
    private CommonTableFilterPanel panelContractTemplateList;
    private CustomPageTableFilter<IndexedContainer> tblContractTemplateList;
    private BeanItemContainer beanItemContainerContractTemplateList;
    private List<ContractTemplateListDTO> lstContractTemplateListDTO = Lists.newArrayList();
    private ContractTemplateListServiceImpl serviceContractTemplateList = new ContractTemplateListServiceImpl();
    private AppParamsServiceImpl serviceAppParams = new AppParamsServiceImpl();
    private String lblDelete = "delete";
    private String lblEdit = "edit";
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("contractTemplateList.header");
    private List<AppParamsDTO> lstAppParamsDTO;
    private List<AppParamsDTO> lstStatus;
    private Map<String, AppParamsDTO> mapStatus;
    private String[] headerContractTemplateList = new String[]{Constants.STT, "code", "name", "pathFile", "createdDate", "lastUpdatedDate", "status", "delete", "edit"};
    private PopupAddContractTemplateList popupAddContractTemplateList;

    public ContractTemplateListController(ContractTemplateListView contractTemplateListView) {
        super(ContractTemplateListDTO.class);
        this.contractTemplateListView = contractTemplateListView;
        panelContractTemplateList = contractTemplateListView.getTblContractTemplateList();
        tblContractTemplateList = contractTemplateListView.getTblContractTemplateList().getMainTable();
        init();
    }

    public void init() {
        initButton();
        getDataWS();
        initComboBox();
        initTable();
    }

    public void initComboBox() {
        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
        containerStatus.addAll(lstStatus);
        CommonUtils.initCombobox(contractTemplateListView.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
    }

    public void getDataWS() {
// ds appparam
        AppParamsDTO appParamsDTO = new AppParamsDTO();
        appParamsDTO.setStatus(Constants.ACTIVE);
        lstAppParamsDTO = serviceAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.MAX_VALUE, "", "parOrder");
        if (lstAppParamsDTO == null) {
            lstAppParamsDTO = Lists.newArrayList();
        }
        lstStatus = DataUtil.getListApParams(lstAppParamsDTO, "COMMON_STATUS");
        mapStatus = CommonUtils.putAppParams2MapByCode(lstStatus);
    }

    public void initButton() {
        contractTemplateListView.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearch();
                event.getButton().setEnabled(true);
            }
        });
        contractTemplateListView.getBtnRefresh().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                contractTemplateListView.getTxtCode().setValue("");
                contractTemplateListView.getTxtName().setValue("");
                contractTemplateListView.getTxtPathFile().setValue("");
                contractTemplateListView.getPopCreatedDate().setValue(null);
                contractTemplateListView.getPopLastUpdatedDate().setValue(null);
                contractTemplateListView.getCbxStatus().setValue(null);
                event.getButton().setEnabled(true);
            }
        });
        panelContractTemplateList.getAddButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                InsertOrUpdate(new ContractTemplateListDTO());
                event.getButton().setEnabled(true);
            }
        });
    }

    public void InsertOrUpdate(final ContractTemplateListDTO contractTemplateListDTO) {
        popupAddContractTemplateList = new PopupAddContractTemplateList();

        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
        containerStatus.addAll(lstStatus);
        CommonUtils.initCombobox(popupAddContractTemplateList.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
        if (contractTemplateListDTO.getCode() != null) {
            popupAddContractTemplateList.getTxtCode().setValue(contractTemplateListDTO.getCode());
        } else {
            popupAddContractTemplateList.getTxtCode().setValue("");
        }
        if (contractTemplateListDTO.getName() != null) {
            popupAddContractTemplateList.getTxtName().setValue(contractTemplateListDTO.getName());
        } else {
            popupAddContractTemplateList.getTxtName().setValue("");
        }
        if (contractTemplateListDTO.getPathFile() != null) {
            popupAddContractTemplateList.getTxtPathFile().setValue(contractTemplateListDTO.getPathFile());
        } else {
            popupAddContractTemplateList.getTxtPathFile().setValue("");
        }
        if (contractTemplateListDTO.getCreatedDate() != null) {
            try {
                popupAddContractTemplateList.getPopCreatedDate().setValue(DateUtil.convertStringToTime(contractTemplateListDTO.getCreatedDate(), "dd/MM/yyyy HH:mm:ss"));
            } catch (ParseException ex) {
                Logger.getLogger(ContractTemplateListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            popupAddContractTemplateList.getPopCreatedDate().setValue(null);
        }
        if (contractTemplateListDTO.getLastUpdatedDate() != null) {
            try {
                popupAddContractTemplateList.getPopLastUpdatedDate().setValue(DateUtil.convertStringToTime(contractTemplateListDTO.getLastUpdatedDate(), "dd/MM/yyyy HH:mm:ss"));
            } catch (ParseException ex) {
                Logger.getLogger(ContractTemplateListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            popupAddContractTemplateList.getPopLastUpdatedDate().setValue(null);
        }
        if (contractTemplateListDTO.getStatus() != null) {
            AppParamsDTO statusDefault = null;
            for (AppParamsDTO status : lstStatus) {
                if (status.getParCode().equals(contractTemplateListDTO.getStatus())) {
                    statusDefault = status;
                }
            }
            popupAddContractTemplateList.getCbxStatus().setValue(statusDefault);
        } else {
            popupAddContractTemplateList.getCbxStatus().setValue(null);
        }

        popupAddContractTemplateList.getBtnSave().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (popupAddContractTemplateList.getTxtCode().getValue() != null || popupAddContractTemplateList.getTxtCode().getValue().equals("")) {
                    contractTemplateListDTO.setCode(popupAddContractTemplateList.getTxtCode().getValue());
                } else {
                    Notification.show(BundleUtils.getString("contractTemplateList.code.isnotnull"));
                    return;
                }
                if (popupAddContractTemplateList.getTxtName().getValue() != null || popupAddContractTemplateList.getTxtName().getValue().equals("")) {
                    contractTemplateListDTO.setName(popupAddContractTemplateList.getTxtName().getValue());
                } else {
                    Notification.show(BundleUtils.getString("contractTemplateList.code.isnotnull"));
                    return;
                }
                if (popupAddContractTemplateList.getTxtPathFile().getValue() != null || popupAddContractTemplateList.getTxtPathFile().getValue().equals("")) {
                    contractTemplateListDTO.setPathFile(popupAddContractTemplateList.getTxtPathFile().getValue());
                } else {
                    Notification.show(BundleUtils.getString("contractTemplateList.code.isnotnull"));
                    return;
                }
                Date createdDate = popupAddContractTemplateList.getPopCreatedDate().getValue();
                if (createdDate != null) {
                    contractTemplateListDTO.setCreatedDate(DateUtil.date2ddMMyyyyString(createdDate));
                } else {
                    Notification.show(BundleUtils.getString("ContractTemplateList.createdDate.require"));
                }
                Date lastUpdatedDate = popupAddContractTemplateList.getPopLastUpdatedDate().getValue();
                if (lastUpdatedDate != null) {
                    contractTemplateListDTO.setLastUpdatedDate(DateUtil.date2ddMMyyyyString(lastUpdatedDate));
                } else {
                    contractTemplateListDTO.setLastUpdatedDate(null);
                }
                AppParamsDTO status = (AppParamsDTO) popupAddContractTemplateList.getCbxStatus().getValue();
                contractTemplateListDTO.setStatus(status.getParCode());
                if (contractTemplateListDTO.getContractTemplateId() == null) {
                    try {
                        ResultDTO resultDTO = serviceContractTemplateList.insertContractTemplateList(contractTemplateListDTO);
                        if (resultDTO.getMessage().equals(Constants.SUCCESS)) {
                            Notification.show(BundleUtils.getString("contractTemplateList.insert.success"));
                        } else {
                            Notification.show(BundleUtils.getString("contractTemplateList.insert.fail"));
                        }

                    } catch (Exception e) {
                        Notification.show(BundleUtils.getString("contractTemplateList.insert.fail"));
                    }

                } else {
                    try {
                        String message = serviceContractTemplateList.updateContractTemplateList(contractTemplateListDTO);
                        if (message.equals(Constants.SUCCESS)) {
                            Notification.show(BundleUtils.getString("contractTemplateList.update.success"));
                        } else {
                            Notification.show(BundleUtils.getString("contractTemplateList.update.fail"));
                        }
                    } catch (Exception e) {
                        Notification.show(BundleUtils.getString("contractTemplateList.update.fail"));
                    }
                }
            }
        });
        popupAddContractTemplateList.getBtnClose().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popupAddContractTemplateList.close();
            }
        });

        UI.getCurrent().addWindow(popupAddContractTemplateList);
    }

    public void initTable() {
        beanItemContainerContractTemplateList = new BeanItemContainer<>(ContractTemplateListDTO.class);

        tblContractTemplateList.addGeneratedColumn(lblDelete, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final ContractTemplateListDTO contractTemplateListDTO = (ContractTemplateListDTO) itemId;
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
                                    contractTemplateListDTO.setStatus(Constants.DEACTIVE);
                                    String message = serviceContractTemplateList.updateContractTemplateList(contractTemplateListDTO);
                                    if (message.equals(Constants.SUCCESS)) {
                                        Notification.show(BundleUtils.getString("delete.success"));
                                        tblContractTemplateList.removeItem(itemId);
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
        tblContractTemplateList.addGeneratedColumn(lblEdit, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final ContractTemplateListDTO contractTemplateListDTO = (ContractTemplateListDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_edit.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        InsertOrUpdate(contractTemplateListDTO);
                    }
                });

                return image;
            }

        });
        CommonFunctionTableFilter.initTable(panelContractTemplateList, headerData,
                beanItemContainerContractTemplateList, BundleUtils.getString("table.list.contractTemplateList"),
                10, "contractTemplateList.header", true, true, false, false, false);

    }

    @Override
    public void onDoSearch() {
        List<ConditionBean> lstCondition2Search = getLstConditionBeanSearch();
        try {
            lstContractTemplateListDTO = serviceContractTemplateList.getListContractTemplateListByCondition(lstCondition2Search, 0, Integer.MAX_VALUE, "", "name");
        } catch (Exception e) {
            lstContractTemplateListDTO = Lists.newArrayList();
        }
        beanItemContainerContractTemplateList.removeAllItems();
        if (DataUtil.isListNullOrEmpty(lstContractTemplateListDTO)) {
            CommonMessages.showDataNotFound();
        } else {
            beanItemContainerContractTemplateList.addAll(lstContractTemplateListDTO);
            CommonFunctionTableFilter.refreshTable(panelContractTemplateList, headerData, beanItemContainerContractTemplateList);
        }
        contractTemplateListView.getBtnSearch().setEnabled(true);
    }

    public List<ConditionBean> getLstConditionBeanSearch() {
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        if (!DataUtil.isStringNullOrEmpty(contractTemplateListView.getTxtCode().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("code");
            conditionBean.setValue(contractTemplateListView.getTxtCode().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(contractTemplateListView.getTxtName().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("name");
            conditionBean.setValue(contractTemplateListView.getTxtName().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(contractTemplateListView.getTxtPathFile().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("pathFile");
            conditionBean.setValue(contractTemplateListView.getTxtPathFile().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (contractTemplateListView.getPopCreatedDate().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("createdDate");
            conditionBean.setValue(DateUtil.date2ddMMyyyyString(contractTemplateListView.getPopCreatedDate().getValue()));
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_DATE);
            lstConditionBean.add(conditionBean);
        }
        if (contractTemplateListView.getPopLastUpdatedDate().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("lastUpdatedDate");
            conditionBean.setValue(DateUtil.date2ddMMyyyyString(contractTemplateListView.getPopLastUpdatedDate().getValue()));
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_DATE);
            lstConditionBean.add(conditionBean);
        }
        if (contractTemplateListView.getCbxStatus().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("status");
            conditionBean.setValue(((AppParamsDTO) contractTemplateListView.getCbxStatus().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_NUMBER);
            lstConditionBean.add(conditionBean);
        }
        return lstConditionBean;

    }
}
