package cn.com.java.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager implements X509TrustManager {

	/** 
	 * @Description:信任管理�?
	 * @Commpany BK
	 * @author ZhangAn
		
	 */
	// 检查客户端证书
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		
	}
	 // 检查查服务器端证书
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	}
	 // 返回受信任的X509证书数组
	public X509Certificate[] getAcceptedIssuers() {
		 return null;
	}
}
