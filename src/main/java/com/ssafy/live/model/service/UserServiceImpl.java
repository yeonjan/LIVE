package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.ssafy.live.model.dto.User;
import com.ssafy.live.model.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	private JavaMailSenderImpl mailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Autowired
	private UserServiceImpl(UserMapper userMapper, JavaMailSenderImpl mailSender) {
		this.userMapper = userMapper;
		this.mailSender = mailSender;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
	}

	@Override
	public void joinUser(User user) throws Exception {
		userMapper.joinUser(user);
	}

	@Override
	public User loginUser(User user) throws Exception {
		return userMapper.loginUser(user);
	}

	@Override
	public List<User> listUser(Map<String, Object> map) throws Exception {
		return userMapper.listUser(map);
	}

	@Override
	public User getUser(String userId) throws SQLException {
		return userMapper.getUser(userId);
	}

	@Override
	public void updateUser(User user) throws Exception {
		userMapper.updateUser(user);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		userMapper.deleteUser(userId);
	}

	@Override
	public String searchPwd(User user) throws Exception {
		return userMapper.searchPwd(user);
	}

	@Override
	public void saveRefreshToken(String userId, String token) {
		userMapper.saveRefreshToken(userId, token);
	}

	@Override
	public void deleteRefreshToken(String userId) {
		userMapper.deleteRefreshToken(userId);

	}

	@Override
	public String getRefreshToken(String userId) {
		return userMapper.getRefreshToken(userId);
	}

	/* 이메일 인증 */
	@Override
	public int makeRandomNum() {
		// 난수범위 111111 ~ 999999
		Random r = new Random();
		return r.nextInt(888888) + 111111;
	}

	@Override
	public String joinEmail(String email) {
		int checkNum = makeRandomNum();
		String setFrom = "yeonjin137@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
		String toMail = email;
		String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목

		Context context = new Context();
		context.setVariable("checkNum", checkNum);
		String content = templateEngine.process("welcome", context);
//		String content = "홈페이지를 방문해주셔서 감사합니다." + // html 형식으로 작성 !
//				"<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>" + "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; // 이메일 내용 삽입
		sendMail(setFrom, toMail, title, content);
		return Integer.toString(checkNum);
	}

	@Override
	public void sendMail(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();

		// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			// helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
			helper.setText(content, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateEmail(User user) throws Exception {
		userMapper.updateEmail(user);

	}
}
