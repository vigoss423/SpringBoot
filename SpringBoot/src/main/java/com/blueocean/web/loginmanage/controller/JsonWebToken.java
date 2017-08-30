package com.blueocean.web.loginmanage.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.common.constant.RetInfoConstant;
import com.blueocean.common.util.JwtHelper;
import com.blueocean.common.util.RetInfoUtil;
import com.blueocean.common.vo.RetInfo;
import com.blueocean.web.hellomanage.dto.userDo;
import com.blueocean.web.hellomanage.service.HelloInfo;
import com.blueocean.web.loginmanage.entity.AccessToken;
import com.blueocean.web.loginmanage.entity.Audience;
import com.blueocean.web.loginmanage.entity.LoginPara;

@RestController
public class JsonWebToken {

	@Resource
	private HelloInfo hi;

	@Autowired
	private Audience audienceEntity;

	@RequestMapping("oauth/token")
	public Object getAccessToken(@RequestBody LoginPara loginPara) {

		RetInfo ret = RetInfoUtil.initRetInfo4Err();
		try {
			System.out.println(audienceEntity.getClientId());
			if (loginPara.getClientId() == null
					|| (loginPara.getClientId().compareTo(audienceEntity.getClientId()) != 0)) {
				return ret;
			}
			// 验证用户名密码
			userDo user = hi.findUserInfoByName(loginPara.getUserName());
			if (user == null) {
				ret.setErrorCode(RetInfoConstant.RETCODE_LOGIN_FAILED);
				ret.setErrorMsg(RetInfoConstant.RETMSG_LOGIN_FAILED);
				return ret;
			} else {
				String md5Password = user.getPassword();
				if (md5Password.compareTo(user.getPassword()) != 0) {
					ret.setErrorCode(RetInfoConstant.RETCODE_LOGIN_FAILED);
					ret.setErrorMsg(RetInfoConstant.RETMSG_LOGIN_FAILED);
					return ret;
				}
			}

			// 拼装accessToken
			String accessToken = JwtHelper.createJWT(loginPara.getUserName(), String.valueOf(user.getUsername()),
					"3123", audienceEntity.getClientId(), audienceEntity.getName(),
					audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());

			// 返回accessToken
			AccessToken accessTokenEntity = new AccessToken();
			accessTokenEntity.setAccess_token(accessToken);
			accessTokenEntity.setExpires_in(audienceEntity.getExpiresSecond());
			accessTokenEntity.setToken_type("bearer");
			ret.setErrorCode(RetInfoConstant.RETCODE_SUCCESS);
			ret.setErrorMsg(RetInfoConstant.RETMSG_SUCCESS_MSG);
			ret.setData(accessTokenEntity);
			return ret;

		} catch (Exception ex) {
			return ret;
		}
	}

}
