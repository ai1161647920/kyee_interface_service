/*
 * Welcome to use the TableGo Tools.
 * 
 * http://www.tablego.cn
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author: bianj
 * Email: tablego@qq.com
 * Version: 6.6.6
 */
package com.kyee.iszx.bean;

/**
 * 方法参数(ky_parameter)
 * 
 * @author zxx
 * @version 1.0.0 2019-12-11
 */
public class KyParameter implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -5949222112302611756L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 逻辑主键 */
    private Integer id;

    /** 参数中文名称 */
    private String nameCn;

    /** 参数英文名称 */
    private String nameEn;

    /** 出现要求，0必传；1根据条件传；2根据需要传 */
    private Integer mark;

    /** 参数描述 */
    private String description;

    /** 参数的属性，入参还是出参，0入参1出参 */
    private Integer attribute;

    /** 影响参数；如何影响看自己需求 */
    private String affectContent;

    /** 所属方法id，外键method外键 */
    private Integer methodId;

    /** 参数类型，parameter_type外键 */
    private Integer type;

    /** 其他要求 */
    private String otherClaim;

    /** 值对应的字典 */
    private Integer valueItem;
    
    /** 参数最后一次使用的值 */
    private String value;
    
    
    /** 该参数是否隐藏 */
    private boolean hide;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取逻辑主键
     * 
     * @return 逻辑主键
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置逻辑主键
     * 
     * @param id
     *          逻辑主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取参数中文名称
     * 
     * @return 参数中文名称
     */
    public String getNameCn() {
        return this.nameCn;
    }

    /**
     * 设置参数中文名称
     * 
     * @param nameCn
     *          参数中文名称
     */
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    /**
     * 获取参数英文名称
     * 
     * @return 参数英文名称
     */
    public String getNameEn() {
        return this.nameEn;
    }

    /**
     * 设置参数英文名称
     * 
     * @param nameEn
     *          参数英文名称
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * 获取出现要求，0必传；1根据条件传；2根据需要传
     * 
     * @return 出现要求
     */
    public Integer getMark() {
        return this.mark;
    }

    /**
     * 设置出现要求，0必传；1根据条件传；2根据需要传
     * 
     * @param mark
     *          出现要求
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }

    /**
     * 获取参数描述
     * 
     * @return 参数描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置参数描述
     * 
     * @param description
     *          参数描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取参数的属性，入参还是出参，0入参1出参
     * 
     * @return 参数的属性
     */
    public Integer getAttribute() {
        return this.attribute;
    }

    /**
     * 设置参数的属性，入参还是出参，0入参1出参
     * 
     * @param attribute
     *          参数的属性
     */
    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    /**
     * 获取影响参数；如何影响看自己需求
     * 
     * @return 影响参数；如何影响看自己需求
     */
    public String getAffectContent() {
        return this.affectContent;
    }

    /**
     * 设置影响参数；如何影响看自己需求
     * 
     * @param affectContent
     *          影响参数；如何影响看自己需求
     */
    public void setAffectContent(String affectContent) {
        this.affectContent = affectContent;
    }

    /**
     * 获取所属方法id，外键method外键
     * 
     * @return 所属方法id
     */
    public Integer getMethodId() {
        return this.methodId;
    }

    /**
     * 设置所属方法id，外键method外键
     * 
     * @param methodId
     *          所属方法id
     */
    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    /**
     * 获取参数类型，parameter_type外键
     * 
     * @return 参数类型
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 设置参数类型，parameter_type外键
     * 
     * @param type
     *          参数类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取其他要求
     * 
     * @return 其他要求
     */
    public String getOtherClaim() {
        return this.otherClaim;
    }

    /**
     * 设置其他要求
     * 
     * @param otherClaim
     *          其他要求
     */
    public void setOtherClaim(String otherClaim) {
        this.otherClaim = otherClaim;
    }

    /**
     * 获取值对应的字典
     * 
     * @return 值对应的字典
     */
    public Integer getValueItem() {
        return this.valueItem;
    }

    /**
     * 设置值对应的字典
     * 
     * @param valueItem
     *          值对应的字典
     */
    public void setValueItem(Integer valueItem) {
        this.valueItem = valueItem;
    }
    
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	/*
	 * public boolean isLAY_CHECKED() { return LAY_CHECKED; }
	 * 
	 * public void setLAY_CHECKED(boolean lAY_CHECKED) { LAY_CHECKED = lAY_CHECKED;
	 * }
	 */

	public boolean getHide() {
		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}
 
    
    
    /* This code was generated by TableGo tools, mark 2 end. */
}