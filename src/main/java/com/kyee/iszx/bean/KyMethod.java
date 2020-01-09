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
 * ky_method
 * 
 * @author zxx
 * @version 1.0.0 2019-12-11
 */
public class KyMethod implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2325352293499311118L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 逻辑主键 */
    private Integer id;

    /** 所属应用 */
    private Integer appId;

    /** 方法英文名称 */
    private String nameEn;

    /** 方法描述 */
    private String description;

    /** 访问方式，外键access_method */
    private Integer accessMethod;

    /** 方法中文名称 */
    private String nameCn;

    /** 返参类型 */
    private String paramterOutType;

    /** 入参类型 */
    private String paramterInType;

    /** 访问的接口路径 */
    private String accessPath;

    /** 访问接口所需其他信息 */
    private String othetMsg;
    
    /** 影响入参的因素 */
    private String affectContent;

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
     * 获取所属应用
     * 
     * @return 所属应用
     */
    public Integer getAppId() {
        return this.appId;
    }

    /**
     * 设置所属应用
     * 
     * @param appId
     *          所属应用
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取方法英文名称
     * 
     * @return 方法英文名称
     */
    public String getNameEn() {
        return this.nameEn;
    }

    /**
     * 设置方法英文名称
     * 
     * @param nameEn
     *          方法英文名称
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * 获取方法描述
     * 
     * @return 方法描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置方法描述
     * 
     * @param description
     *          方法描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取访问方式，外键access_method
     * 
     * @return 访问方式
     */
    public Integer getAccessMethod() {
        return this.accessMethod;
    }

    /**
     * 设置访问方式，外键access_method
     * 
     * @param accessMethod
     *          访问方式
     */
    public void setAccessMethod(Integer accessMethod) {
        this.accessMethod = accessMethod;
    }

    /**
     * 获取方法中文名称
     * 
     * @return 方法中文名称
     */
    public String getNameCn() {
        return this.nameCn;
    }

    /**
     * 设置方法中文名称
     * 
     * @param nameCn
     *          方法中文名称
     */
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    /**
     * 获取返参类型
     * 
     * @return 返参类型
     */
    public String getParamterOutType() {
        return this.paramterOutType;
    }

    /**
     * 设置返参类型
     * 
     * @param paramterOutType
     *          返参类型
     */
    public void setParamterOutType(String paramterOutType) {
        this.paramterOutType = paramterOutType;
    }

    /**
     * 获取入参类型
     * 
     * @return 入参类型
     */
    public String getParamterInType() {
        return this.paramterInType;
    }

    /**
     * 设置入参类型
     * 
     * @param paramterInType
     *          入参类型
     */
    public void setParamterInType(String paramterInType) {
        this.paramterInType = paramterInType;
    }

    /**
     * 获取访问的接口路径
     * 
     * @return 访问的接口路径
     */
    public String getAccessPath() {
        return this.accessPath;
    }

    /**
     * 设置访问的接口路径
     * 
     * @param accessPath
     *          访问的接口路径
     */
    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    /**
     * 获取访问接口所需其他信息
     * 
     * @return 访问接口所需其他信息
     */
    public String getOthetMsg() {
        return this.othetMsg;
    }

    /**
     * 设置访问接口所需其他信息
     * 
     * @param othetMsg
     *          访问接口所需其他信息
     */
    public void setOthetMsg(String othetMsg) {
        this.othetMsg = othetMsg;
    }

	public String getAffectContent() {
		return this.affectContent;
	}

	public void setAffectContent(String affectContent) {
		this.affectContent = affectContent;
	}

    
     
    /* This code was generated by TableGo tools, mark 2 end. */
}