package com.cms.dto;


/**
* @author QuyenDM
* @version 1.0
* @since 03/11/2016 22:17:52
*/
public class ContractTemplateListDTO {   
	//Fields
     private String contractTemplateId;
     private String code;
     private String name;
     private String pathFile;
     private String createdDate;
     private String lastUpdatedDate;
     private String status;
     //Constructor
     public ContractTemplateListDTO() {

    }
   public ContractTemplateListDTO(String contractTemplateId,String code,String name,String pathFile,String createdDate,String lastUpdatedDate,String status){
         this.contractTemplateId = contractTemplateId;
         this.code = code;
         this.name = name;
         this.pathFile = pathFile;
         this.createdDate = createdDate;
         this.lastUpdatedDate = lastUpdatedDate;
         this.status = status;
     }
     //Getters and Setters
     public String getContractTemplateId() {
         return this.contractTemplateId;
     }
     public void setContractTemplateId(final String contractTemplateId) {
         this.contractTemplateId=contractTemplateId;
     }
     public String getCode() {
         return this.code;
     }
     public void setCode(final String code) {
         this.code=code;
     }
     public String getName() {
         return this.name;
     }
     public void setName(final String name) {
         this.name=name;
     }
     public String getPathFile() {
         return this.pathFile;
     }
     public void setPathFile(final String pathFile) {
         this.pathFile=pathFile;
     }
     public String getCreatedDate() {
         return this.createdDate;
     }
     public void setCreatedDate(final String createdDate) {
         this.createdDate=createdDate;
     }
     public String getLastUpdatedDate() {
         return this.lastUpdatedDate;
     }
     public void setLastUpdatedDate(final String lastUpdatedDate) {
         this.lastUpdatedDate=lastUpdatedDate;
     }
     public String getStatus() {
         return this.status;
     }
     public void setStatus(final String status) {
         this.status=status;
     }

}
