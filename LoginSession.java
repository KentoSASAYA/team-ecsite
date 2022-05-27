package jp.co.internous.knights.model.session;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class LoginSession implements Serializable {
	private static final long serialVersionUID = -6065498250991524525L;
	
	//ユーザーID
	private int userId;
	//仮ユーザーID
	private int temporaryId;
	//ユーザー名
	private String userName;
	//パスワード
	private String password;
	//ログインフラグ
	private boolean loginFlag;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTemporaryId() {
		return temporaryId;
	}
	public void setTemporaryId(int temporaryId) {
		this.temporaryId = temporaryId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	
}
