package cn.com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import cn.com.java.model.TextMessage;
import cn.com.java.util.CheckStr;
import cn.com.java.util.MessageUtil;

public class WeiXinServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		PrintWriter out=resp.getWriter();
		try {
			
			if(CheckStr.checkSignature(signature, timestamp, nonce)){
				out.println(echostr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out != null){
				out.close();
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> map =new HashMap<String,String>();
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		
		try {
			map=MessageUtil.xmlToMap(req);
			String Content=null;
			String ToUserName=map.get("ToUserName").toString();
			String FromUserName=map.get("FromUserName").toString();
			String CreateTime=map.get("CreateTime").toString();
			String MsgType=map.get("MsgType").toString();
			String message=null;
			if (MessageUtil.MESSAGE_TEXT.equals(MsgType)) {//�ı���Ϣ
				Content=map.get("Content").toString();
				if("1".equals(Content)){
					message=MessageUtil.initText(ToUserName, FromUserName, MessageUtil.firstMenu());
				}else if("2".equals(Content)){
					message=MessageUtil.initText(ToUserName, FromUserName, MessageUtil.secondMenu());
				}else if("3".equals(Content)){
					message=MessageUtil.initNewsMessage(ToUserName, FromUserName);
				}else if("4".equals(Content)){
					message=MessageUtil.initImageMessage(ToUserName, FromUserName);
				}else if("5".equals(Content)){
					message=MessageUtil.initMusicMessage(ToUserName, FromUserName);
				}else if("?".equals(Content) || "��".equals(Content)){
					message=MessageUtil.initText(ToUserName, FromUserName, MessageUtil.menuText());
				}else {
					message=MessageUtil.initText(ToUserName, FromUserName, MessageUtil.menuText());
				}
				System.out.println(message);
			}else if (MessageUtil.MESSAGE_EVENT.equals(MsgType)) {//�¼�
				String eventType=map.get("Event");
				
				if(MessageUtil.EVENT_SUBSCRIBE.equals(eventType)){//��ע�¼�
					message=MessageUtil.initText(ToUserName, FromUserName, MessageUtil.menuText());
				}
				if(MessageUtil.EVENT_UNSUBSCRIBE.equals(eventType)){//ȡ����ע�¼�
					message=null;
				}
				/*if(MessageUtil.EVENT_LOCATION.equals(eventType)){//�ϱ�����λ�ã�����Ự�ͻ��ϱ���
					String Latitude=map.get("Latitude");//����λ��γ��
					String Longitude=map.get("Longitude");//����λ�þ���
					String Precision=map.get("Precision");//����λ�þ���
					StringBuffer sb = new StringBuffer();
					sb.append("���ĵ���λ��:\n" );
					sb.append("����:" + Latitude+"\n");
					sb.append("γ��:" + Longitude+"\n");
					sb.append("����:" + Precision);
					message=MessageUtil.initText(ToUserName, FromUserName, sb.toString());
				}*/
			}
			out.println(message);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
}
