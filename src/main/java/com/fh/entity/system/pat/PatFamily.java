package com.fh.entity.system.pat;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;


@Table(name="pat_family")
public class PatFamily extends BaseEntity{
	
	@Id
	@ApiModelProperty(value = "家属记录id")
	private String famId;
	@ApiModelProperty(value = "登陆账号id")
	private String parentId;
	@ApiModelProperty(value = "家属姓名")
	private String famName;
	//汉字 男或女
	private String famSex;
	//FAM_BRITH
	private Date famBrith;
	//身份证
	private String famIdnum;
	//结婚 **已婚 	未婚	 离异 	丧偶
	private String famMarry;
	//是否默认
	private String famFlag;
	//生育
	//未生育
	//备孕期
	//怀孕期
	//已生育
	private String famBear;
	//手术或外伤
	private String famOperation;
	//药物过敏
	private String famDrugAllergy;
	//食物过敏
	private String famFoodAllergy;
	//个人习惯
	private String famHabit;
	//家族病史
	private String famMedHistory;
	public String getFamId() {
		return famId;
	}
	public void setFamId(String famId) {
		this.famId = famId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getFamName() {
		return famName;
	}
	public void setFamName(String famName) {
		this.famName = famName;
	}
	public String getFamSex() {
		return famSex;
	}
	public void setFamSex(String famSex) {
		this.famSex = famSex;
	}
	public Date getFamBrith() {
		return famBrith;
	}
	public void setFamBrith(Date famBrith) {
		this.famBrith = famBrith;
	}
	public String getFamIdnum() {
		return famIdnum;
	}
	public void setFamIdnum(String famIdnum) {
		this.famIdnum = famIdnum;
	}
	public String getFamMarry() {
		return famMarry;
	}
	public void setFamMarry(String famMarry) {
		this.famMarry = famMarry;
	}
	public String getFamFlag() {
		return famFlag;
	}
	public void setFamFlag(String famFlag) {
		this.famFlag = famFlag;
	}
	public String getFamBear() {
		return famBear;
	}
	public void setFamBear(String famBear) {
		this.famBear = famBear;
	}
	public String getFamOperation() {
		return famOperation;
	}
	public void setFamOperation(String famOperation) {
		this.famOperation = famOperation;
	}
	public String getFamDrugAllergy() {
		return famDrugAllergy;
	}
	public void setFamDrugAllergy(String famDrugAllergy) {
		this.famDrugAllergy = famDrugAllergy;
	}
	public String getFamFoodAllergy() {
		return famFoodAllergy;
	}
	public void setFamFoodAllergy(String famFoodAllergy) {
		this.famFoodAllergy = famFoodAllergy;
	}
	public String getFamHabit() {
		return famHabit;
	}
	public void setFamHabit(String famHabit) {
		this.famHabit = famHabit;
	}
	public String getFamMedHistory() {
		return famMedHistory;
	}
	public void setFamMedHistory(String famMedHistory) {
		this.famMedHistory = famMedHistory;
	}
	
	
	
	
}