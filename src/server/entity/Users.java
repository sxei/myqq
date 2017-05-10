package server.entity;




import java.sql.Date;

/**
 * @author Administrator
 * 用户表的实体类
 */
public class Users
{
	private int id;//主键
	private String name;//姓名
	private String pwd;//密码
	private String ip;//IP地址
	private String state;//状态，-1或空表示不在线，0表示在线，1表示Q我，2表示离开，3表示忙碌，4表示隐身
	private String gender;//性别
	private String email;//邮箱
	private String lastLogin;//最后一次登录
	private String lastExit;//最后一次退出
	private String remarke;//备用
	private String signature;//个性签名
	private String headImg;//头像
	private String type;//类型
	private Date birthday;//生日
	

	public Users() 
	{
		super();
	}
	/**
	 * @param id	      	用户ID
	 * @param name			用户名
	 * @param pwd			用户密码
	 * @param gender		用户性别
	 * @param email			用户e-mail
	 * @param lastLogin		用户最后一次登录时间
	 * @param lastExit		用户最后一次下线时间
	 * @param remarke		备注
	 * @param signature		用户签名
	 * @param headImg		用户头像
	 * @param birthday		用户生日
	 * @param type			用户类型（管理员/普通用户）
	 */
	public Users(int id, String name, String pwd, String gender, String email, String remarke,

			String signature, String headImg, Date birthday,String type,String ip,String state) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.gender = gender;
		this.email = email;
		this.remarke = remarke;
		this.signature = signature;
		this.headImg = headImg;
		this.birthday = birthday;
		this.type = type;
		this.ip = ip;
		this.state = state;
	}
	/**
	 * 无用户ID的构造方法
	 * @param name
	 * @param pwd
	 * @param gender
	 * @param email
	 * @param lastLogin
	 * @param lastExit
	 * @param nickName
	 * @param remarke
	 * @param signature
	 * @param headImg
	 * @param birthday
	 */
	
	public Users(String name, String pwd, String gender, String email,
			String signature, String headImg, Date birthday) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.gender = gender;
		this.email = email;
		this.signature = signature;
		this.headImg = headImg;
		this.birthday = birthday;
		this.state=-1+"";//将用户默认状态设为-1表示不在线
	}
	
	public Users(int id, String name, String pwd, String gender, String email,
			Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.gender = gender;
		this.email = email;
		this.birthday = birthday;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the lastLogin
	 */
	public String getLastLogin() {
		return lastLogin;
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * @return the lastExit
	 */
	public String getLastExit() {
		return lastExit;
	}
	/**
	 * @param lastExit the lastExit to set
	 */
	public void setLastExit(String lastExit) {
		this.lastExit = lastExit;
	}
	/**
	 * @return the 
	 */
	public String getRemarke() {
		return remarke;
	}
	/**
	 * @param remarke 
	 */
	public void setRemarke(String remarke) {
		this.remarke = remarke;
	}
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	/**
	 * @return the headImg
	 */
	public String getHeadImg() {
		return headImg;
	}
	/**
	 * @param headImg the headImg to set
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	

}
