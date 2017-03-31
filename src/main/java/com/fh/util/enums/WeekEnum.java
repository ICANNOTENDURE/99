package com.fh.util.enums;

public enum WeekEnum {
	
	Mon("1","Monday"),
	Tues("2","Tuesday"),
	Wed("3","Wednesday"),
	Thur("4","Thurday"),
	Fri("5","Friday"),
	Sat("6","Saturday"),
	Sun("7","Sunday");
	private String code;
	
	private String desc;
	
	private WeekEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	

	public String getCode() {
		return code;
	}


	public String getDesc() {
		return desc;
	}

	 /** 
     * 根据key获取value 
     *  
     * @param key 
     *            : 键值key 
     * @return String 
     */  
    public static String getValueByKey(String key) {  
    	WeekEnum[] enums = WeekEnum.values();  
        for (int i = 0; i < enums.length; i++) {  
            if (enums[i].getCode().equals(key)) {  
                return enums[i].getDesc();  
            }  
        }  
        return "";  
    }  
	
}
