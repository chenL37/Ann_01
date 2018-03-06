package com.study.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * 
 * @author ChanRain
 *
 */
public class Test {
	
	public static void main(String[] args) {
		Filter f1 = new Filter();
		f1.setId(10);
		
		Filter f2 = new Filter();
		f2.setName("张三");
		
		Filter f3 = new Filter();
		f3.setEmail("123456789@qq.com");
		
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);

		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
	}
	
	/**
	 * 根据方法解析注解
	 * @param f
	 * @return
	 */
	private static String query(Filter f) {
		//需要返回的字段
		StringBuilder sb = new StringBuilder();
		
		//1.获取到class
		Class cla = f.getClass();
		
		//2.获取到table的名字
		boolean exist_Table = cla.isAnnotationPresent(Table.class);
		//2.1	Table标签不存在直接返回
		if(!exist_Table){
			return null;
		}
		//2.2	通过字段获取table,并获取其Value值
		Table tab = (Table) cla.getAnnotation(Table.class);
		
		String tableName = tab.value();
		
		sb.append("select * from ").append(tableName).append(" where 1=1");
		
		//3	遍历所有字段
		Field[] fArray = cla.getDeclaredFields();
		
		for(Field field : fArray){
			
			//4	处理每个字段对应的sql
			//4.1	拿到字段名
			boolean fExists = field.isAnnotationPresent(Column.class);
			
			if(!fExists){
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			
			//4.2	拿到字段的值
			String filedName = field.getName();
			String getMethodName = "get"+filedName.substring(0, 1).toUpperCase()+
					filedName.substring(1);
			Object fieldValue = null;
			try {
				Method getMethod = cla.getMethod(getMethodName);
				fieldValue = getMethod.invoke(f,null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//4.3	拼装sql
			if(fieldValue == null||
				fieldValue instanceof Integer && (Integer)fieldValue == 0){
				continue;
			}
			sb.append(" and ").append(filedName).append("=").append(fieldValue);
			
		}
		
		return sb.toString();
	}

}
