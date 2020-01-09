package com.kyee.iszx.Enums;

public enum ErrorCodeEnum {

	S000(0, "成功"),
	S001(1,"调用综合支付异常!"),
	S002(2,"调用综合支付失败"),
	S003(3,"未知错误"),
	S004(4,"未知错误"),
	S005(5,"未知错误"),
	S006(6,"未知错误"),
	S007(7,"未知错误"),
	S008(8,"未知错误"),
	S009(9,"未知错误"),
	S010(10,"未知错误"),
	S011(11,"未知错误"),
	S012(12,"未知错误"),
	S013(13,"未知错误"),
	S014(14,"未知错误"),
	S015(15,"未知错误"),
	S016(16,"未知错误"),
	S017(17,"未知错误");


    private Integer code;
    private String description;

     ErrorCodeEnum(Integer code,String description){
        this.code=code;
        this.description=description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ErrorCodeEnum getDescriptionByCode(Integer code){
        for(ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()){
            if(code.equals(errorCodeEnum.getCode())){
                return errorCodeEnum;
            }
        }
        return  ErrorCodeEnum.S001;
    }

}
