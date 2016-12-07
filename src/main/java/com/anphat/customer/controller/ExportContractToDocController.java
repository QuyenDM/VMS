/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.cms.dto.CustomerDTO;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.ui.UI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author quyen
 */
public class ExportContractToDocController {

    private XWPFDocument document;
    private List<XWPFTable> lstTable;
    private final static String fontFamily = "Times New Roman";
    private String fileName = "BM_VNPT.docx";
    private Map<String, String> mapValues;

    public ExportContractToDocController(Map<String, String> mapValues) {
        this.mapValues = mapValues;
        getDatas();
    }

    private void getDatas() {
        try {
            document = new XWPFDocument(new FileInputStream(Constants.PATH_TEMPLATE + fileName));
            //Lay tat ca cac bang
            lstTable = document.getTables();
            System.out.println(lstTable.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateFile(CustomerDTO customerDTO) {
        buildContractDetails(customerDTO);
        buildFile();
    }

    private void buildFile() {
        try {
            FileOutputStream out = new FileOutputStream(Constants.PATH_EXPORT + "BBBG.doc");
            document.write(out);
            out.close();
            final File exportFile = new File(Constants.PATH_EXPORT + "BBBG.doc");
            if (exportFile.exists()) {
                ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("delete.item.title"), BundleUtils.getString("confirm.contract.doc"),
                        BundleUtils.getString("yes"), BundleUtils.getString("no"), new ConfirmDialog.Listener() {
                    @Override
                    public void onClose(ConfirmDialog dialog) {
                        if (dialog.isConfirmed()) {
                            Resource res = new FileResource(exportFile);
                            Page.getCurrent().open(res,
                                    BundleUtils.getString("label.caption.download").
                                    replace("@name", BundleUtils.getString("contract.doc.caption")), true);
                        }
                    }
                });
            } else {
                //Thong bao khong the tai file
                CommonMessages.showExportFail("contract.doc.caption");
            }
            exportFile.deleteOnExit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void buildContractDetails(CustomerDTO customerDTO) {
        for (XWPFTable tbl : lstTable) {
            for (XWPFTableRow rowTbl : tbl.getRows()) {
                for (XWPFTableCell cellTbl : rowTbl.getTableCells()) {
                    for (XWPFParagraph p : cellTbl.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
//                            //Thoi gian
//                            if (text != null && DataUtil.isStringContainDateTime(text)) {
//                                text = DataUtil.replaceDateTime(text);
//                                r.setText(text, 0);
//                            }
                            //Ben giao
                            if (text != null && text.contains(Constants.REPORT.NAME)) {
                                text = text.replace(Constants.REPORT.NAME,
                                        DataUtil.getStringNullOrZero(customerDTO.getName()));
                                r.setText(text, 0);
                            }
                            //Ma so thue
                            if (text != null && text.contains(Constants.REPORT.TAX_CODE)) {
                                text = text.replace(Constants.REPORT.TAX_CODE,
                                        DataUtil.getStringNullOrZero(customerDTO.getTaxCode()));
                                r.setText(text, 0);
                            }
                            //So dien thoai
                            if (text != null && text.contains(Constants.REPORT.TEL_NUMBER)) {
                                text = text.replace(Constants.REPORT.TEL_NUMBER,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.TEL_NUMBER)));
                                r.setText(text, 0);
                            }
                            //Fax
                            if (text != null && text.contains(Constants.REPORT.FAX)) {
                                text = text.replace(Constants.REPORT.FAX,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.FAX)));
                                r.setText(text, 0);
                            }
                            //Email
                            if (text != null && text.contains(Constants.REPORT.EMAIL)) {
                                text = text.replace(Constants.REPORT.EMAIL,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.EMAIL)));
                                r.setText(text, 0);
                            }
                            //Dia chi tru so
                            if (text != null && text.contains(Constants.REPORT.OFFICE_ADDRESS)) {
                                text = text.replace(Constants.REPORT.OFFICE_ADDRESS,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.OFFICE_ADDRESS)));
                                r.setText(text, 0);
                            }
                            //Dia chi giao dich
                            if (text != null && text.contains(Constants.REPORT.DEPLOY_ADDRESS)) {
                                text = text.replace(Constants.REPORT.DEPLOY_ADDRESS,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.DEPLOY_ADDRESS)));
                                r.setText(text, 0);
                            }
                            //Co quan thue
                            if (text != null && text.contains(Constants.REPORT.TAX_DEPARTMENT)) {
                                text = text.replace(Constants.REPORT.TAX_DEPARTMENT,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.TAX_DEPARTMENT)));
                                r.setText(text, 0);
                            }
                            //CMND
                            if (text != null && text.contains(Constants.REPORT.CMND)) {
                                text = text.replace(Constants.REPORT.CMND,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.CMND)));
                                r.setText(text, 0);
                            }
                            //Nguoi dai dien
                            if (text != null && text.contains(Constants.REPORT.NGUOI_DAIDIEN)) {
                                text = text.replace(Constants.REPORT.NGUOI_DAIDIEN,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.NGUOI_DAIDIEN)));
                                r.setText(text, 0);
                            }
                            //Chuc vu Nguoi dai dien
                            if (text != null && text.contains(Constants.REPORT.CHUVU_NGUOI_DAIDIEN)) {
                                text = text.replace(Constants.REPORT.CHUVU_NGUOI_DAIDIEN,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.CHUVU_NGUOI_DAIDIEN)));
                                r.setText(text, 0);
                            }
                            //SDT Nguoi dai dien
                            if (text != null && text.contains(Constants.REPORT.SDT_NGUOI_DAIDIEN)) {
                                text = text.replace(Constants.REPORT.SDT_NGUOI_DAIDIEN,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.SDT_NGUOI_DAIDIEN)));
                                r.setText(text, 0);
                            }
                            //Email Nguoi dai dien
                            if (text != null && text.contains(Constants.REPORT.EMAIL_NGUOI_DAIDIEN)) {
                                text = text.replace(Constants.REPORT.EMAIL_NGUOI_DAIDIEN,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.EMAIL_NGUOI_DAIDIEN)));
                                r.setText(text, 0);
                            }
                            //Nguoi dai dien
                            if (text != null && text.contains(Constants.REPORT.NGUOI_LIENHE)) {
                                text = text.replace(Constants.REPORT.NGUOI_LIENHE,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.NGUOI_LIENHE)));
                                r.setText(text, 0);
                            }
                            //Chuc vu Nguoi lien he
                            if (text != null && text.contains(Constants.REPORT.CHUCVU_NGUOI_LIENHE)) {
                                text = text.replace(Constants.REPORT.CHUCVU_NGUOI_LIENHE,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.CHUCVU_NGUOI_LIENHE)));
                                r.setText(text, 0);
                            }
                            //SDT Nguoi lien he
                            if (text != null && text.contains(Constants.REPORT.SDT_NGUOI_LIENHE)) {
                                text = text.replace(Constants.REPORT.SDT_NGUOI_LIENHE,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.SDT_NGUOI_LIENHE)));
                                r.setText(text, 0);
                            }
                            //Email Nguoi lien he
                            if (text != null && text.contains(Constants.REPORT.EMAIL_NGUOI_LIENHE)) {
                                text = text.replace(Constants.REPORT.EMAIL_NGUOI_LIENHE,
                                        DataUtil.getStringNullOrZero(mapValues.get(Constants.REPORT.EMAIL_NGUOI_LIENHE)));
                                r.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
    }
}
