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

import java.sql.Timestamp;
import java.util.Date;

/**
 * 系统字典表(sys_dictionary_item)
 * 
 * @author zxx
 * @version 1.0.0 2019-12-11
 */
public class SysDictionaryItem implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -8258461438389601889L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 主键 */
    private Integer itemId;

    /** 字典编码 */
    private Integer dictCode;

    /** 字典名称 */
    private String dictName;

    /** 项目编码 */
    private Integer itemCode;

    /** 项目名称 */
    private String itemName;

    /** 项目值 */
    private String itemValue;

    /** 启用标志(0:禁用;1:启用);默认:1启用 */
    private String enableFlag;

    /** 使用标志(0:禁用;1:启用);默认:1启用 */
    private String useFlag;

    /** 项目说明 */
    private String comments;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取主键
     * 
     * @return 主键
     */
    public Integer getItemId() {
        return this.itemId;
    }

    /**
     * 设置主键
     * 
     * @param itemId
     *          主键
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取字典编码
     * 
     * @return 字典编码
     */
    public Integer getDictCode() {
        return this.dictCode;
    }

    /**
     * 设置字典编码
     * 
     * @param dictCode
     *          字典编码
     */
    public void setDictCode(Integer dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取字典名称
     * 
     * @return 字典名称
     */
    public String getDictName() {
        return this.dictName;
    }

    /**
     * 设置字典名称
     * 
     * @param dictName
     *          字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 获取项目编码
     * 
     * @return 项目编码
     */
    public Integer getItemCode() {
        return this.itemCode;
    }

    /**
     * 设置项目编码
     * 
     * @param itemCode
     *          项目编码
     */
    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 获取项目名称
     * 
     * @return 项目名称
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * 设置项目名称
     * 
     * @param itemName
     *          项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取项目值
     * 
     * @return 项目值
     */
    public String getItemValue() {
        return this.itemValue;
    }

    /**
     * 设置项目值
     * 
     * @param itemValue
     *          项目值
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    /**
     * 获取启用标志(0:禁用;1:启用);默认:1启用
     * 
     * @return 启用标志(0
     */
    public String getEnableFlag() {
        return this.enableFlag;
    }

    /**
     * 设置启用标志(0:禁用;1:启用);默认:1启用
     * 
     * @param enableFlag
     *          启用标志(0
     */
    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    /**
     * 获取使用标志(0:禁用;1:启用);默认:1启用
     * 
     * @return 使用标志(0
     */
    public String getUseFlag() {
        return this.useFlag;
    }

    /**
     * 设置使用标志(0:禁用;1:启用);默认:1启用
     * 
     * @param useFlag
     *          使用标志(0
     */
    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }

    /**
     * 获取项目说明
     * 
     * @return 项目说明
     */
    public String getComments() {
        return this.comments;
    }

    /**
     * 设置项目说明
     * 
     * @param comments
     *          项目说明
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /* This code was generated by TableGo tools, mark 2 end. */
}