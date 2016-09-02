package cn.com.java.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import cn.com.java.model.TextMessage;

public class MessageUtil {
	public static final String MESSAGE_TEXT="text";//�ı���Ϣ
	public static final String MESSAGE_IMAGE="image";//ͼƬ��Ϣ
	public static final String MESSAGE_VOICE="voice";//������Ϣ
	public static final String MESSAGE_VIDEO="video";//��Ƶ��Ϣ
	public static final String MESSAGE_LINK="link";//������Ϣ
	public static final String MESSAGE_SHORTVIDEO="shortvideo";//С��Ƶ��Ϣ
	public static final String MESSAGE_LOCATION="location";//����λ����Ϣ
	public static final String MESSAGE_EVENT="event";//�¼���Ϣ
	public static final String EVENT_SUBSCRIBE="subscribe";//�û���ע�¼�
	public static final String EVENT_UNSUBSCRIBE="unsubscribe";//�û�ȡ����ע�¼�
	public static final String EVENT_CLICK="CLICK";//�Զ���˵��¼�
	public static final String EVENT_VIEW="VIEW";//����˵���ת����ʱ���¼�
	public static final String EVENT_SCAN="SCAN";//�û��ѹ�עʱ���¼���ɨ��ά��ʱ��
	public static final String EVENT_LOCATION="LOCATION";//����Ự���ϱ�����λ�ã�
	
	
	/**
	 * xmlת���ɼ���
	 * @Description:
	 * @Commpany BK
	 * @author ZhangAn
	 */
	public static Map<String , String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException{
		Map<String, String> map=new HashMap<String,String>();
		SAXReader reader=new SAXReader();
		InputStream is=request.getInputStream();
		try {
			Document doc=reader.read(is);
			Element root=doc.getRootElement();
			List<Element> list=root.elements();
			for (Element element : list) {
				map.put(element.getName(), element.getText());
			}
			
		}finally {
			is.close();
		}	
		
		return map;
	}
	/**
	 * 
	* @Title: textMessageToXml 
	* @Description: TODO ���ı�����ת����xml
	* @param @param textMessage
	* @param @return  ��������
	* @return String    �������� 
	* @throws 
	* @Commpany BK
	* @author ZhangAn
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xStream=new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
		 
	}
	
	/**
	 * 
	* @Title: initText ƴ���ı�
	* @Description: TODO
	* @param @param ToUserName
	* @param @param FromUserName
	* @param @param Content
	* @param @return  ��������
	* @return String    �������� 
	* @throws 
	* @Commpany BK
	* @author ZhangAn
	 */
	public static String initText(String ToUserName,String FromUserName,String Content){
		Date date=new Date();
		TextMessage textMessage=new TextMessage();
		textMessage.setFromUserName(ToUserName);
		textMessage.setToUserName(FromUserName);
		textMessage.setMsgType(MESSAGE_TEXT);
		textMessage.setCreateTime(date.toString());
		textMessage.setContent(Content);
		return textMessageToXml(textMessage);
	}
	/**
	 * 
	* @Title: menuText 
	* @Description: TODO ���˵�
	* @param @return  ��������
	* @return String    �������� 
	* @throws 
	* @Commpany BK
	* @author ZhangAn
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("��ӭ���Ĺ�ע,�밴�ղ˵���ʾ���в�����\n\n");
		sb.append("1�����ںŽ���\n");
		sb.append("2�����߽���\n\n");
		sb.append("�ظ�"+"?"+"�����˲˵���");
		return sb.toString();
	}
	/**
	 * 
	* @Title: firstMenu �����Զ��ظ�"1"
	* @Description: TODO
	* @param @return  ��������
	* @return String    �������� 
	* @throws 
	* @Commpany BK
	* @author ZhangAn
	 */
	public static  String firstMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("���ںŽ��ܣ�΢�Ź��ں��ǿ����߻��̼���΢�Ź���ƽ̨�������Ӧ���˺ţ����ʺ���QQ�˺Ż�ͨ��ͨ�����ں�,");
		sb.append("�̼ҿ���΢��ƽ̨��ʵ�ֺ��ض�Ⱥ������֡�ͼƬ����������Ƶ��ȫ��λ��ͨ������ ��");
		sb.append("�γ���һ����������������΢�Ż���Ӫ����ʽ��");
		return sb.toString();
	}
	public static  String secondMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("���߽��ܣ�����ɽ��ѧ��������Ϣ����ѧԺ�����ϵѧ��,\n");
		sb.append("˧�磡��˧�磡��˧�磡��˧�磡��˧�磡��");
		return sb.toString();
	}
}
