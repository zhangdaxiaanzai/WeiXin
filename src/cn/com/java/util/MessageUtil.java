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
	public static final String MESSAGE_TEXT="text";//文本消息
	public static final String MESSAGE_IMAGE="image";//图片消息
	public static final String MESSAGE_VOICE="voice";//语音消息
	public static final String MESSAGE_VIDEO="video";//视频消息
	public static final String MESSAGE_LINK="link";//链接消息
	public static final String MESSAGE_SHORTVIDEO="shortvideo";//小视频消息
	public static final String MESSAGE_LOCATION="location";//地理位置消息
	public static final String MESSAGE_EVENT="event";//事件消息
	public static final String EVENT_SUBSCRIBE="subscribe";//用户关注事件
	public static final String EVENT_UNSUBSCRIBE="unsubscribe";//用户取消关注事件
	public static final String EVENT_CLICK="CLICK";//自定义菜单事件
	public static final String EVENT_VIEW="VIEW";//点击菜单跳转链接时的事件
	public static final String EVENT_SCAN="SCAN";//用户已关注时的事件（扫二维码时）
	public static final String EVENT_LOCATION="LOCATION";//进入会话（上报地理位置）
	
	
	/**
	 * xml转换成集合
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
	* @Description: TODO 将文本对象转换成xml
	* @param @param textMessage
	* @param @return  条件参数
	* @return String    返回类型 
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
	* @Title: initText 拼接文本
	* @Description: TODO
	* @param @param ToUserName
	* @param @param FromUserName
	* @param @param Content
	* @param @return  条件参数
	* @return String    返回类型 
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
	* @Description: TODO 主菜单
	* @param @return  条件参数
	* @return String    返回类型 
	* @throws 
	* @Commpany BK
	* @author ZhangAn
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("欢迎您的关注,请按照菜单提示进行操作：\n\n");
		sb.append("1、公众号介绍\n");
		sb.append("2、作者介绍\n\n");
		sb.append("回复"+"?"+"调出此菜单！");
		return sb.toString();
	}
	/**
	 * 
	* @Title: firstMenu 条件自动回复"1"
	* @Description: TODO
	* @param @return  条件参数
	* @return String    返回类型 
	* @throws 
	* @Commpany BK
	* @author ZhangAn
	 */
	public static  String firstMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("公众号介绍：微信公众号是开发者或商家在微信公众平台上申请的应用账号，该帐号与QQ账号互通，通过公众号,");
		sb.append("商家可在微信平台上实现和特定群体的文字、图片、语音、视频的全方位沟通、互动 。");
		sb.append("形成了一种主流的线上线下微信互动营销方式。");
		return sb.toString();
	}
	public static  String secondMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("作者介绍：井冈山大学电子与信息工程学院计算机系学生,\n");
		sb.append("帅哥！！帅哥！！帅哥！！帅哥！！帅哥！！");
		return sb.toString();
	}
}
