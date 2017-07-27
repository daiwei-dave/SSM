package org.excel.annotations;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Orm（对象关系映射）工具
 * @author Administrator
 * 代维添加了部分注释
 *
 */
public class OrmUtil {

	/**
	 * 构建sql 忽略关键字
	 * 
	 * @param column
	 * @return
	 */
	public static String ingoreKeyword(String column) {
		return "`" + column + "`";
	}

	/**
	 * 获取Excel字段
	 * 
	 * @param bean
	 * @return
	 */
	public static <T> List<ExcelFieldOrm> getExcelFields(T bean) {
		List<ExcelFieldOrm> excelFields = new ArrayList();
		ExcelFieldOrm excel =new ExcelFieldOrm();
		excelFields.add(excel);
		return excelFields;
	}

	/**
	 * 创建model
	 * 
	 * @param genericClass
	 * @return
	 */
	public static <T> T createModel(Class<T> genericClass) {
		try {
			return genericClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error:The instantiation of objects [" + genericClass.getName() + "] failed.");
		}
	}

	/**
	 * 转换值，将从excel获取的值转换为注解所表示的类型
	 * @param value
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object convertValue(Object value, String dataType){
		Object newValue = null;
		try {
			if (Integer.class.getName().equals(dataType)) {
				newValue = Integer.valueOf(value.toString());
			} else if (Double.class.getName().equals(dataType)) {
				newValue = Double.valueOf(value.toString());
			} else if (Float.class.getName().equals(dataType)) {
				newValue = Float.valueOf(value.toString());
			} else if (Long.class.getName().equals(dataType)) {
				newValue = Long.valueOf(value.toString());
			} else if (String.class.getName().equals(dataType)) {
				newValue = value.toString();
			} else if (value instanceof BigDecimal) {
				newValue = ((BigDecimal) value);
			} else if (boolean.class.getName().equals(dataType) || Boolean.class.getName().equals(dataType)) {
				newValue = "1".equals(value) || Boolean.valueOf(value.toString());
			} else if (Class.forName(dataType).isEnum()) {
				Class<Enum> clazz = (Class<Enum>) Class.forName(dataType);
				newValue = Enum.valueOf(clazz, value.toString());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return newValue;
	}

	/**
	 * 克隆属性
	 * @param src
	 * @param target
	 */
	public static <T, E> void cloneProperty(T src, E target) {
		Field[] fields = target.getClass().getDeclaredFields();
		if (!CollectionUtils.sizeIsEmpty(fields)) {
			for (Field field : fields) {
				try {
					Object value = PropertyUtils.getProperty(src, field.getName());
					PropertyUtils.setProperty(target, field.getName(), value);
				} catch (Exception e) {
					System.err.println("copy property[" + field.getName() + "] failed.");
				}
			}
		}
	}

}
