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
 * access_method
 * 
 * @author zxx
 * @version 1.0.0 2019-12-11
 */
public class AccessMethod implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 1333579900438604768L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 主键 */
    private Integer id;

    /** 访问方式 */
    private String accessMethod;

    /** 访问其他信息 */
    private String accessOther;

    /** 访问形式 */
    private String accessType;

    /** 其他要求 */
    private String accessClaim;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取主键
     * 
     * @return 主键
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置主键
     * 
     * @param id
     *          主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取访问方式
     * 
     * @return 访问方式
     */
    public String getAccessMethod() {
        return this.accessMethod;
    }

    /**
     * 设置访问方式
     * 
     * @param accessMethod
     *          访问方式
     */
    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }

    /**
     * 获取访问其他信息
     * 
     * @return 访问其他信息
     */
    public String getAccessOther() {
        return this.accessOther;
    }

    /**
     * 设置访问其他信息
     * 
     * @param accessOther
     *          访问其他信息
     */
    public void setAccessOther(String accessOther) {
        this.accessOther = accessOther;
    }

    /**
     * 获取访问形式
     * 
     * @return 访问形式
     */
    public String getAccessType() {
        return this.accessType;
    }

    /**
     * 设置访问形式
     * 
     * @param accessType
     *          访问形式
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    /**
     * 获取其他要求
     * 
     * @return 其他要求
     */
    public String getAccessClaim() {
        return this.accessClaim;
    }

    /**
     * 设置其他要求
     * 
     * @param accessClaim
     *          其他要求
     */
    public void setAccessClaim(String accessClaim) {
        this.accessClaim = accessClaim;
    }

    /* This code was generated by TableGo tools, mark 2 end. */
}