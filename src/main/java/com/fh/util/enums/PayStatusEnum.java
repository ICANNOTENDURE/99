package com.fh.util.enums;

public enum PayStatusEnum {
	
	Pay("1","支付成功"),
	Bill("2","计费成功"),
	ApplyForRefund("3","退费申请"),
	Refund("4","退费成功"),
	RefundFailure("5","退费失败");
	private String code;
	
	private String desc;
	
	private PayStatusEnum(String code, String desc) {
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
    	PayStatusEnum[] enums = PayStatusEnum.values();  
        for (int i = 0; i < enums.length; i++) {  
            if (enums[i].getCode().equals(key)) {  
                return enums[i].getDesc();  
            }  
        }  
        return "";  
    }  
	
}
