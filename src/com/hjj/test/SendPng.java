package com.hjj.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Decoder.BASE64Encoder;

@WebServlet(name = "exampleServlet", urlPatterns = "/setCode")
public class SendPng extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String png = GetImageStr("�����ڱ������̵���֤��ͼƬλ��");
		PrintWriter out = response.getWriter();
		out.println(png);
		out.flush();
	}

	public static String GetImageStr(String imgFilePath) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
		byte[] data = null;

		// ��ȡͼƬ�ֽ�����
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ���ֽ�����Base64����
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// ����Base64��������ֽ������ַ���
	}

	/**
	 * ��ȡ�ܵ��е�������
	 */
	public byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String result = UpdateInfo.testOracle(request.getParameter("userPass"), request.getParameter("schoolnum"));
		//System.out.println("�޸Ľ��" + result);
	}

}
