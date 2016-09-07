package cn.com.java.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import cn.com.java.model.Token;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class commonWeiXin {
	/** 
	* @Description:
	* @Commpany 微信获取用户信息公共�?
	* @author ZhangAn
	
	*/
	 private static Logger log = Logger.getLogger(commonWeiXin.class);
	  public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	  public final static String getuser_url="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	  public final static String getalluser_url="https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	  /**
	   * 发送https请求
	   * 
	   * @param requestUrl 请求地址
	   * @param requestMethod 请求方式（GET、POST�?
	   * @param outputStr 提交的数据?
	   * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性�??)
	   */
	  public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		  JSONObject json=null;
	    try {
	      // 创建SSLContext对象，并使用我们指定的信任管理器初始�?
	      TrustManager[] tm = { new MyX509TrustManager() };
	      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	      sslContext.init(null, tm, new java.security.SecureRandom());
	      // 从上述SSLContext对象中得到SSLSocketFactory对象
	      SSLSocketFactory ssf = sslContext.getSocketFactory();
 	      URL url = new URL(requestUrl);
	      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	      conn.setSSLSocketFactory(ssf);
	      conn.setDoOutput(true);
	      conn.setDoInput(true);
	      conn.setUseCaches(false);
	      // 设置请求方式（GET/POST�?
	      conn.setRequestMethod(requestMethod);
	      // 当outputStr不为null时向输出流写数据
	      if (null != outputStr) {
	        OutputStream outputStream = conn.getOutputStream();
	        // 注意编码格式
	        outputStream.write(outputStr.getBytes("UTF-8"));
	        outputStream.close();
	      }
	      // 从输入流读取返回内容
	      InputStream inputStream = conn.getInputStream();
	      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	      String str = null;
	      StringBuffer buffer = new StringBuffer();
	      while ((str = bufferedReader.readLine()) != null) {
	        buffer.append(str);
	      }
	      // 释放资源
	      bufferedReader.close();
	      inputStreamReader.close();
	      inputStream.close();
	      inputStream = null;
	      conn.disconnect();
//	      map.put("token", buffer.toString());
	      json=JSONObject.fromObject(buffer.toString());
	  
	    } catch (ConnectException ce) {
	      log.error(ce.getMessage());
	      log.error("连接超时：{}", ce);
	    } catch (Exception e) {
	      log.error(e.getMessage());
	      log.error("https请求异常：{}", e);
	    }
	    return json;
	  }
	  
	  public static JSONArray getUserInfo(String requestUrl, String requestMethod, String outputStr) {
		  JSONArray json=null;
	    try {
	      // 创建SSLContext对象，并使用我们指定的信任管理器初始�?
	      TrustManager[] tm = { new MyX509TrustManager() };
	      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	      sslContext.init(null, tm, new java.security.SecureRandom());
	      // 从上述SSLContext对象中得到SSLSocketFactory对象
	      SSLSocketFactory ssf = sslContext.getSocketFactory();
 	      URL url = new URL(requestUrl);
	      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	      conn.setSSLSocketFactory(ssf);
	      conn.setDoOutput(true);
	      conn.setDoInput(true);
	      conn.setUseCaches(false);
	      // 设置请求方式（GET/POST�?
	      conn.setRequestMethod(requestMethod);
	      // 当outputStr不为null时向输出流写数据
	      if (null != outputStr) {
	        OutputStream outputStream = conn.getOutputStream();
	        // 注意编码格式
	        outputStream.write(outputStr.getBytes("UTF-8"));
	        outputStream.close();
	      }
	      // 从输入流读取返回内容
	      InputStream inputStream = conn.getInputStream();
	      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	      String str = null;
	      StringBuffer buffer = new StringBuffer();
	      while ((str = bufferedReader.readLine()) != null) {
	        buffer.append(str);
	      }
	      // 释放资源
	      bufferedReader.close();
	      inputStreamReader.close();
	      inputStream.close();
	      inputStream = null;
	      conn.disconnect();
//	      map.put("token", buffer.toString());
	      json=JSONArray.fromObject(buffer.toString());
	  
	    } catch (ConnectException ce) {
	      log.error(ce.getMessage());
	      log.error("连接超时：{}", ce);
	    } catch (Exception e) {
	      log.error(e.getMessage());
	      log.error("https请求异常：{}", e);
	    }
	    return json;
	  }
	  /**
	   * 获取接口访问凭证
	   * 
	   * @param appid 凭证
	   * @param appsecret 密钥
	   * @return
	   */
	  public Token getAccessToken(String appid, String appsecret) {
	    Token token = null;
	    String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
	    // 发起GET请求获取凭证
	   JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
	    if (null != jsonObject) {
	      try {
	        token = new Token();
	        token.setAccessToken(jsonObject.getString("access_token"));
	        token.setExpiresIn(jsonObject.getInt("expires_in"));
	      } catch (Exception e) {
	        token = null;
	        // 获取token失败
	        log.error(e.getMessage());
	        log.error("获取token失败");
	      }
	    }
	    return token;
	  }
	  
	  public JSONObject getUserInfo(String accessToken,String nextOpenid){
		  JSONObject json=null;
		  String requestUrl=getuser_url.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenid);
		  json=httpsRequest(requestUrl, "POST", null);
		  try {
			  if(null != json){
				  return json;
				  
			  }else{
				  return null; 
			  }
			
		} catch (Exception e) {
			 log.error(e.getMessage());
			 log.error("操作失败");
			return null;
		}
		  
	  }
	  public JSONObject getAllUserInfo(String accessToken,String outPutStr){
		  JSONObject json=null;
		  String requestUrl=getalluser_url.replace("ACCESS_TOKEN", accessToken);
		  json=httpsRequest(requestUrl, "POST", outPutStr);
		  try {
			  if(null != json){
				  return json;
				  
			  }else{
				  return null; 
			  }
			  
		  } catch (Exception e) {
			  log.error(e.getMessage());
			  log.error("操作失败");
			  return null;
		  }
		  
	  }
	  /**
	   * URL编码（utf-8）
	   * 
	   * @param source
	   * @return
	   */
	  public static String urlEncodeUTF8(String source) {
	    String result = source;
	    try {
	      result = java.net.URLEncoder.encode(source, "utf-8");
	    } catch (UnsupportedEncodingException e) {
	    	log.error(e.getMessage());
	    }
	    return result;
	  }
	  /**
	   * 根据内容类型判断文件扩展�?
	   * 
	   * @param contentType 内容类型
	   * @return
	   */
	  public static String getFileExt(String contentType) {
	    String fileExt = "";
	    if ("image/jpeg".equals(contentType))
	      fileExt = ".jpg";
	    else if ("audio/mpeg".equals(contentType))
	      fileExt = ".mp3";
	    else if ("audio/amr".equals(contentType))
	      fileExt = ".amr";
	    else if ("video/mp4".equals(contentType))
	      fileExt = ".mp4";
	    else if ("video/mpeg4".equals(contentType))
	      fileExt = ".mp4";
	    return fileExt;
	 
	  }
	  /**
	  * @Title: httpRequest 
	  * @Description: TODO 模拟http请求
	  * @param @param requestUrl
	  * @param @param requestMethod
	  * @param @param outputStr
	  * @param @return  条件参数
	  * @return JSONObject    返回类型 
	  * @throws 
	  * @commpany BK
	  * @author ZhangAn
	   */
	  public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		  JSONObject json=null;
	    try {
	      // 创建SSLContext对象，并使用我们指定的信任管理器初始�?
	      TrustManager[] tm = { new MyX509TrustManager() };
	      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	      sslContext.init(null, tm, new java.security.SecureRandom());
	      // 从上述SSLContext对象中得到SSLSocketFactory对象
	      SSLSocketFactory ssf = sslContext.getSocketFactory();
 	      URL url = new URL(requestUrl);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	      conn.setSSLSocketFactory(ssf);
	      conn.setDoOutput(true);
	      conn.setDoInput(true);
	      conn.setUseCaches(false);
	      // 设置请求方式（GET/POST�?
	      conn.setRequestMethod(requestMethod);
	      // 当outputStr不为null时向输出流写数据
	      if (null != outputStr) {
	        OutputStream outputStream = conn.getOutputStream();
	        // 注意编码格式
	        outputStream.write(outputStr.getBytes("UTF-8"));
	        outputStream.close();
	      }
	      // 从输入流读取返回内容
	      InputStream inputStream = conn.getInputStream();
	      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	      String str = null;
	      StringBuffer buffer = new StringBuffer();
	      while ((str = bufferedReader.readLine()) != null) {
	        buffer.append(str);
	      }
	      // 释放资源
	      bufferedReader.close();
	      inputStreamReader.close();
	      inputStream.close();
	      inputStream = null;
	      conn.disconnect();
//	      map.put("token", buffer.toString());
	      json=JSONObject.fromObject(buffer.toString());
	  
	    } catch (ConnectException ce) {
	    	log.error(ce.getMessage());
	      log.error("连接超时：{}", ce);
	    } catch (Exception e) {
	      log.error(e.getMessage());
	      log.error("https请求异常：{}", e);
	    }
	    return json;
	  }
}
