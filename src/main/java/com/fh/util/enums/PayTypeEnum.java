package com.fh.util.enums;

public enum PayTypeEnum {
	
	WeiXinPay("1","微信支付"),
	AliPay("2","支付宝支付"),
	UnionPay("3","银联支付");
	private String code;
	
	private String desc;
	
	private PayTypeEnum(String code, String desc) {
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
    	PayTypeEnum[] enums = PayTypeEnum.values();  
        for (int i = 0; i < enums.length; i++) {  
            if (enums[i].getCode().equals(key)) {  
                return enums[i].getDesc();  
            }  
        }  
        return "";  
    }  
	
}
