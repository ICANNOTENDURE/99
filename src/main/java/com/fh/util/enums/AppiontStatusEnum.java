package com.fh.util.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fh.entity.vo.Select;

/**
 * 预约记录状态
 * @author ZDD
 *
 */
public enum AppiontStatusEnum {
	
	NORMAL("0","正常"),
	CANCEL("1","取消"),
	DONE("2","已做");
	
	private String code;

	private String desc;
	
	private AppiontStatusEnum(String code,String desc) {
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
    	AppiontStatusEnum[] enums = AppiontStatusEnum.values();  
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
	
    public static String getValueByKey(String key) {  
    	AppiontStatusEnum[] enums = AppiontStatusEnum.values();  
        for (int i = 0; i < enums.length; i++) {  
            if (enums[i].getCode().equals(key)) {  
                return enums[i].getDesc();  
            }  
        }  
        return "";  
    }  
}
