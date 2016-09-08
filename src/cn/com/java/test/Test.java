package cn.com.java.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchProviderException;

import cn.com.java.model.Token;
import cn.com.java.util.MessageUtil;
import cn.com.java.util.commonWeiXin;
import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		commonWeiXin commonWeiXin=new commonWeiXin();
		Token token=commonWeiXin.getAccessToken(MessageUtil.WX_APPID, MessageUtil.WX_APPSECRET);
//		String path="C:/Users/admin/Desktop/花.jpg";
		System.out.println(token.getAccessToken());
		String menu=JSONObject.fromObject(commonWeiXin.intiMenu()).toString();
		int result=commonWeiXin.creatMenu(token.getAccessToken(), menu);
		if(result == 0){
			System.out.println("创建菜单成功");
		}else{
			System.out.println("创建菜单失败，错误码："+result);
		}
		/*try {
			String mediaId=commonWeiXin.upload(path,token.getAccessToken(),"thumb");
			System.out.println(mediaId);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
}
