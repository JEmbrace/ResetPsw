package com.hjj.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateInfo {
	
	@SuppressWarnings("resource")
	public static String testOracle(String pwd,String school)
	{
	    Connection con = null;// ����һ�����ݿ�����
	    PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
	    ResultSet result = null;// ����һ�����������
	    try
	    {
	        Class.forName("oracle.jdbc.driver.OracleDriver");// ����Oracle��������
	        System.out.println("��ʼ�����������ݿ⣡");
	        String url = "jdbc:oracle:thin:@���ݿ������IP��ַ:1521:ORCL";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
	        String user = "***";// �û���,ϵͳĬ�ϵ��˻���
	        String password = "***";// �㰲װʱѡ���õ�����
	        con = DriverManager.getConnection(url, user, password);// ��ȡ����
	        //System.out.println("���ӳɹ���");
	        String sql4 = "select * from user_list where username = ?";  
	        pre = con.prepareStatement(sql4);// ʵ����Ԥ�������
	        pre.setString(1, "04131117");
	        result=pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ��� 
	        while(result.next()){
	        	String username=result.getString("username");
	        	System.out.println("mima��"+result.getString("passwd"));
	        	String sql = "update user_list set passwd = ? where username = ?";// Ԥ������䣬�������������
		        pre = con.prepareStatement(sql);// ʵ����Ԥ�������
		        pre.setString(1, pwd);
		        pre.setString(2, username);
		        int res=pre.executeUpdate();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
		        /*System.out.println("���½�� "+res);*/
		        if(res == 1)
		        	return "��ϲ�����������޸ĳɹ�";
		        return "���ˣ����������޸�ʧ��";
	        }
	        return "ϵͳ�ڲ������˻������������˺�";
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        try
	        {
	            // ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
	            // ע��رյ�˳�����ʹ�õ����ȹر�
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println("���ݿ������ѹرգ�");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
		return "";
	}
}