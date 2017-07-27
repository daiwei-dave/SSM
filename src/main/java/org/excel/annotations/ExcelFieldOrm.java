package org.excel.annotations;

import java.io.Serializable;

/**
 * Excel字段
 * @author Administrator
 *
 */
public class ExcelFieldOrm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4589929077971942849L;

	/**
	 * 字段名
	 */
	private String fieldName;
	
	/**
	 * excel标题
	 */
	private String excelTitle;
	
	/**
	 * excel值
	 */
	private String excelValue;
	
	/**
	 * excel排序
	 */
	private int excelSort;
	
	/**
	 * excel类型
	 */
	private int excelType;
	
	/**
	 * excel格式
	 */
	private int excelAlign;
	
	/**
	 * excel记录
	 */
	private String excelNote;
	
	/**
	 * 语言
	 */
	private String language;
	
	/**
	 * 必输
	 */
	private boolean mustInput;
	
	/**
	 * 数据类型
	 */
	private String dataType;
	
	/**
	 * i18nField
	 */
	private boolean i18nField;

	/**
	 * 自定义范围
	 */
	private boolean customField;
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the excelTitle
	 */
	public String getExcelTitle() {
		return excelTitle;
	}
	/**
	 * @param excelTitle the excelTitle to set
	 */
	public void setExcelTitle(String excelTitle) {
		this.excelTitle = excelTitle;
	}
	/**
	 * @return the excelValue
	 */
	public String getExcelValue() {
		return excelValue;
	}
	/**
	 * @param excelValue the excelValue to set
	 */
	public void setExcelValue(String excelValue) {
		this.excelValue = excelValue;
	}
	/**
	 * @return the excelSort
	 */
	public int getExcelSort() {
		return excelSort;
	}
	/**
	 * @param excelSort the excelSort to set
	 */
	public void setExcelSort(int excelSort) {
		this.excelSort = excelSort;
	}
	/**
	 * @return the excelType
	 */
	public int getExcelType() {
		return excelType;
	}
	/**
	 * @param excelType the excelType to set
	 */
	public void setExcelType(int excelType) {
		this.excelType = excelType;
	}
	/**
	 * @return the excelAlign
	 */
	public int getExcelAlign() {
		return excelAlign;
	}
	/**
	 * @param excelAlign the excelAlign to set
	 */
	public void setExcelAlign(int excelAlign) {
		this.excelAlign = excelAlign;
	}
	/**
	 * @return the excelNote
	 */
	public String getExcelNote() {
		return excelNote;
	}
	/**
	 * @param excelNote the excelNote to set
	 */
	public void setExcelNote(String excelNote) {
		this.excelNote = excelNote;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the mustInput
	 */
	public boolean isMustInput() {
		return mustInput;
	}
	/**
	 * @param mustInput the mustInput to set
	 */
	public void setMustInput(boolean mustInput) {
		this.mustInput = mustInput;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the i18nField
	 */
	public boolean isI18nField() {
		return i18nField;
	}
	/**
	 * @param i18nField the i18nField to set
	 */
	public void setI18nField(boolean i18nField) {
		this.i18nField = i18nField;
	}
	/**
	 * @return the customField
	 */
	public boolean isCustomField() {
		return customField;
	}
	/**
	 * @param customField the customField to set
	 */
	public void setCustomField(boolean customField) {
		this.customField = customField;
	}

}
