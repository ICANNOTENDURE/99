package com.fh.util.enums;

public enum AskStatus {
	
	TO_PAY("0","待支付"),
	CHAT_READY("1","待回复"),
	CHAT_ING("2","咨询中"),
	CHAT_END("3","咨询完成(待评价)"),
	RETURN("4","退费"),
	RET_REQUEST("5","退费申请"),
	COLSE("6","删除"),
	EVAL("8","已评价");
	private String code;
	
	private String desc;
	
	private AskStatus(String code, String desc) {
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
    	AskStatus[] enums = AskStatus.values();  
        for (int i = 0; i < enums.length; i++) {  
            if (enums[i].getCode().equals(key)) {  
                return enums[i].getDesc();  
            }  
        }  
        return "";  
    }  
	
}
