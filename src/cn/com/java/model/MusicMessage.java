package cn.com.java.model;

public class MusicMessage {
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private Music Music;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Music getMusic() {
		return Music;
	}
	public void setMusic(Music music) {
		Music = music;
	}
}
