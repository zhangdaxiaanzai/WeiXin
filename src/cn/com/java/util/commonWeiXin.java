package cn.com.java.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import cn.com.java.menu.Button;
import cn.com.java.menu.ClickButton;
import cn.com.java.menu.Menu;
import cn.com.java.menu.ViewButton;
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
	  public final static String UPLOAD_URL="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	  private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	  
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
	      // 创建SSLContext对象，并使用我们指定的信任管理器初始化
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
	  
	  /**
		 * 文件上传
		 * @param filePath
		 * @param accessToken
		 * @param type
		 * @return
		 * @throws IOException
		 * @throws NoSuchAlgorithmException
		 * @throws NoSuchProviderException
		 * @throws KeyManagementException
		 */
		public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchMethodException, NoSuchProviderException, KeyManagementException {
			File file = new File(filePath);
			System.out.println();
			if (!file.exists() || !file.isFile()) {
				throw new IOException("文件不存在");
			}

			String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
			
			URL urlObj = new URL(url);
			//连接
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

			con.setRequestMethod("POST"); 
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); 

			//设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");

			//设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");

			byte[] head = sb.toString().getBytes("utf-8");

			//获得输出流
			OutputStream out = new DataOutputStream(con.getOutputStream());
			//输出表头
			out.write(head);

			//文件正文部分
			//把文件已流文件的方式 推入到url中
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();

			//结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

			out.write(foot);

			out.flush();
			out.close();

			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = null;
			String result = null;
			try {
				//定义BufferedReader输入流来读取URL的响应
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				if (result == null) {
					result = buffer.toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					reader.close();
				}
			}

			JSONObject jsonObj = JSONObject.fromObject(result);
			System.out.println(jsonObj);
			String typeName = "media_id";
			if(!"image".equals(type)){
				typeName = type + "_media_id";
			}
			String mediaId = jsonObj.getString(typeName);
			return mediaId;
		}
		/**
		 * 组装菜单
		 * @return
		 */
		
		public static Menu intiMenu(){
			Menu menu=new Menu();
			ClickButton button11 = new ClickButton();
			button11.setName("click菜单");
			button11.setType("click");
			button11.setKey("11");
			
			ViewButton button21 = new ViewButton();
			button21.setName("view菜单");
			button21.setType("view");
			button21.setUrl("http://www.imooc.com");
			
			ClickButton button31 = new ClickButton();
			button31.setName("扫码事件");
			button31.setType("scancode_waitmsg");
			button31.setKey("31");
			
			ClickButton button32 = new ClickButton();
			button32.setName("地理位置");
			button32.setType("location_select");
			button32.setKey("32");
			
			Button button = new Button();
			button.setName("菜单");
			button.setSub_button(new Button[]{button31,button32});//二级菜单
			
			menu.setButton(new Button[]{button11,button21,button});//一级菜单
			
			
			return menu;
		}
		
		public static int creatMenu(String accessToken,String menu){
			int result=0;
			 String requestUrl=CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
			JSONObject jsonObject=httpRequest(requestUrl, "POST", menu);
			if(jsonObject != null){
				result=jsonObject.getInt("errcode");
			}
			return result;
		}
}
