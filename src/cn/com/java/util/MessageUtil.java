package cn.com.java.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.thoughtworks.xstream.XStream;

import cn.com.java.model.Image;
import cn.com.java.model.ImageMessage;
import cn.com.java.model.Music;
import cn.com.java.model.MusicMessage;
import cn.com.java.model.News;
import cn.com.java.model.NewsMessage;
import cn.com.java.model.TextMessage;

public class MessageUtil {
	public static final String MESSAGE_TEXT="text";//�ı���Ϣ
	public static final String MESSAGE_NEWS = "news";//ͼ����Ϣ
	public static final String MESSAGE_IMAGE="image";//ͼƬ��Ϣ
	public static final String MESSAGE_MUSIC="music";//ͼƬ��Ϣ
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
	
	public static final String WX_APPID="wx7746475d34337f9e";
	public static final String WX_APPSECRET="3aaf9cce4fdac4fa24f8417d3e52d356";
	
	
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
		sb.append("2�����߽���\n");
		sb.append("3��ͼ����Ϣ\n");
		sb.append("4��ͼƬ��Ϣ\n");
		sb.append("5��������Ϣ\n");
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
	/**
	 * ͼ����Ϣת��Ϊxml
	 * @param newsMessage
	 * @return
	 */
	public static String newstMessageToXml(NewsMessage newsMessage){
		XStream xStream=new XStream();
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item",new News().getClass());
		return xStream.toXML(newsMessage);
		 
	}
	/**
	 * ͼƬ��Ϣת��Ϊxml
	 * @param newsMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	/**
	 * ������Ϣת��Ϊxml
	 * @param newsMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xStream=new XStream();
		xStream.alias("xml", musicMessage.getClass());
		return xStream.toXML(musicMessage);
		 
	}
	/**
	 * ��װͼ����Ϣ
	 * @param ToUserName
	 * @param FromUserName
	 * @return
	 */
	public static String initNewsMessage(String ToUserName,String FromUserName){
		String message = null;
		List<News> listNews=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news = new News();
		news.setTitle("���߽���");
		news.setDescription("�Ű�,һλ˧�磡");
		news.setPicUrl("http://zhangan.ngrok.cc/WeixinTest/images/imooc.jpg");
		news.setUrl("www.baidu.com");
		
//		News news1=new News();
//		news.setTitle("���߽���");
//		news.setDescription("�Ű�,һλ˧�磡");
//		news.setPicUrl("http://zhangan.ngrok.cc/WeixinTest/images/fei.jpg");
//		news.setUrl("www.iqiyi.com");
		
		listNews.add(news);
//		listNews.add(news1);
		Date date=new Date();
		
		newsMessage.setFromUserName(ToUserName);
		newsMessage.setToUserName(FromUserName);
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setCreateTime(date.toString());
		newsMessage.setArticleCount(listNews.size());
		newsMessage.setArticles(listNews);
		
		
		message=newstMessageToXml(newsMessage);
		return message;
				
	}
	
	/**
	 * ��װͼƬ��Ϣ
	 * @param ToUserName
	 * @param FromUserName
	 * @return
	 */
	public static String initImageMessage(String ToUserName,String FromUserName){
		String message=null;
		Image image=new Image();
		ImageMessage imageMessage=new ImageMessage();
		image.setMediaId("gNf2Fj3OIDY6Syte4ospPSaoXUIEXGNDESGg1hbh6sNa0EGE06Ug2X-6kJL43q_w");
		Date date=new Date();
		
		imageMessage.setFromUserName(ToUserName);
		imageMessage.setToUserName(FromUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(date.toString());
		imageMessage.setImage(image);
		
		message=imageMessageToXml(imageMessage);
		
		return message;
		
	}
	/**
	 * ��װ������Ϣ
	 * @param ToUserName
	 * @param FromUserName
	 * @return
	 */
	public static String initMusicMessage(String ToUserName,String FromUserName){
		String message=null;
		Music music=new Music();
		MusicMessage musicMessage=new MusicMessage();
		
		music.setTitle("see you again");
		music.setDescription("��7Ƭβ��");
		music.setHQMusicUrl("http://zhangan.ngrok.cc/WeixinTest/resource/See You Again.mp3");
		music.setMusicUrl("http://zhangan.ngrok.cc/WeixinTest/resource/See You Again.mp3");
		music.setThumbMediaId("lfDbIdEgucUj9O6wEV6vz9nybpKspMlAQiLK_cEtLjuXGQ9J7ADGGUobEEjC3FMc");
		
		Date date=new Date();
		
		musicMessage.setFromUserName(ToUserName);
		musicMessage.setToUserName(FromUserName);
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setCreateTime(date.toString());
		musicMessage.setMusic(music);
		
		message=musicMessageToXml(musicMessage);
		
		return message;
	}
	
}
