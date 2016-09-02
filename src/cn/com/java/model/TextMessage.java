package cn.com.java.model;

public class TextMessage {
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Content;
	private String MsgId;
	public String getToUserName() {
		return this.ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return this.FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return this.CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return this.MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return this.Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return this.MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	
}
