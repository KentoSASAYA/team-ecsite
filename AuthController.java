package jp.co.internous.knights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jp.co.internous.knights.model.domain.MstUser;
import jp.co.internous.knights.model.form.UserForm;
import jp.co.internous.knights.model.mapper.MstUserMapper;
import jp.co.internous.knights.model.mapper.TblCartMapper;
import jp.co.internous.knights.model.session.LoginSession;

@RestController
@RequestMapping("/knights/auth")
public class AuthController {

	private Gson gson = new Gson();
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private TblCartMapper cartMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	
	//ログイン
	@RequestMapping("/login")
	public String login(@RequestBody UserForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(f.getUserName(), f.getPassword());
		
		int temporaryId = loginSession.getTemporaryId();
		
		if(user != null && temporaryId != 0) {
			int count = cartMapper.findCountByUserId(temporaryId);
			if(count > 0) {
				cartMapper.updateUserId(user.getId(), temporaryId);
			}
		}
		
		if (user != null) {
			loginSession.setTemporaryId(0);
			loginSession.setLoginFlag(true);
			loginSession.setUserId(user.getId());
			loginSession.setUserName(user.getUserName());
			loginSession.setPassword(user.getPassword());
		} else {
			loginSession.setLoginFlag(false);
			loginSession.setUserId(0);
			loginSession.setUserName(null);
			loginSession.setPassword(null);
		}
		
		return gson.toJson(user);
	}
	
	//ログアウト
	@RequestMapping("/logout")
	public String logout() {
		loginSession.setTemporaryId(0);
		loginSession.setLoginFlag(false);
		loginSession.setUserId(0);
		loginSession.setUserName(null);
		loginSession.setPassword(null);
		
		return "";
	}
}
