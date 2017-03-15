package com.fh.util.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fh.entity.vo.Select;

public enum BannerEnum {
	
	PAT_TOP("0","病人首页图片"),
	PAT_NEWS("1","病人新闻列表"),
	DOC_TOP("2","医生首页图片");
	
	private String code;

	private String desc;
	
	private BannerEnum(String code,String desc) {
		this.code = code;
		this.desc=desc;
	}
	

	public String getCode() {
		return code;
	}
	
	public String getDesc(){
		return desc;
	}


    public static List<Select> getCombo(String selected){
    	
    	List<Select> selects=new ArrayList<Select>();
    	BannerEnum[] enums = BannerEnum.values();  
        for (int i = 0; i < enums.length; i++) { 
        	Select select=new Select(enums[i].code, enums[i].desc);
        	if(StringUtils.isNotBlank(selected)){
        		if(enums[i].code.equals(selected)){
        			select.setSelected("1");
        		}
        	}
        	selects.add(select);
        }
        return selects;
    }
	
}
