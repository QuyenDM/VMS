package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author @version 1.0
 * @since 24/08/2016 01:03:56
 */
public class CategoryListDTO extends BaseDTO {
    //Fields

    private String id;
    private String code;
    private String name;
    private String receivedDate;
    private String endDate;
    private String description;
    private String creator;
    private String custQuantity;
    //Constructor

    public CategoryListDTO() {

    }

    public CategoryListDTO(String id, String code, String name, String receivedDate, String endDate, String description, String creator, String custQuantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.receivedDate = receivedDate;
        this.endDate = endDate;
        this.description = description;
        this.creator = creator;
        this.custQuantity = custQuantity;
    }
    //Getters and Setters

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getReceivedDate() {
        return this.receivedDate;
    }

    public void setReceivedDate(final String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(final String creator) {
        this.creator = creator;
    }

    public String getCustQuantity() {
        return this.custQuantity;
    }

    public void setCustQuantity(final String custQuantity) {
        this.custQuantity = custQuantity;
    }

}
