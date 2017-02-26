package com.fh.util.enums;

public enum AuditDocStatusEnum {
	
	TO_AUDIT("0","待认证"),
	AUDIT_ING("1","审核中"),
	AUDIT_PASS("2","审核通过"),
	AUDIT_REFUSE("3","审核拒绝");

	private String code;
	
	private String desc;
	
	private AuditDocStatusEnum(String code, String desc) {
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
    	AuditDocStatusEnum[] enums = AuditDocStatusEnum.values();  
        for (int i = 0; i < enums.length; i++) {  
            if (enums[i].getCode().equals(key)) {  
                return enums[i].getDesc();  
            }  
        }  
        return "";  
    }  
	
}
