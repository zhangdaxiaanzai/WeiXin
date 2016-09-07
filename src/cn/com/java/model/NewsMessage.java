package cn.com.java.model;

import java.util.List;

public class NewsMessage{
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private int ArticleCount;
	private List<News> Articles;
	public String getToUserName() {
		return this.ToUserName;
	}
	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}
	public String getFromUserName() {
		return this.FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return this.CreateTime;
	}
	public void setCreateTime(String createTime) {
		this.CreateTime = createTime;
	}
	public String getMsgType() {
		return this.MsgType;
	}
	public void setMsgType(String msgType) {
		this.MsgType = msgType;
	}
	
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	
	

}
