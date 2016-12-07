/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
public class ObjectsDTO extends BaseDTO {
    //Fields

    private String objectId;
    private String appId;
    private String code;
    private String name;
    private String url;
    private String description;
    private String objectType;
    private String status;
    private String roleObjectId;

    //Constructor
    public ObjectsDTO() {
    }

    public ObjectsDTO(String objectId, String appId, String code, String name, String url, String description, String objectType, String status) {
        this.objectId = objectId;
        this.appId = appId;
        this.code = code;
        this.name = name;
        this.url = url;
        this.description = description;
        this.objectType = objectType;
        this.status = status;
    }
    //Getters and setters

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getRoleObjectId() {
        return roleObjectId;
    }

    public void setRoleObjectId(String roleObjectId) {
        this.roleObjectId = roleObjectId;
    }

}
