package com.myproject.loginDBConnect;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {
	@Autowired
	UserDao userDao;

	@Test
	public void updateUser() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		userDao.deleteUser("asdf"); // 테스트를 위해 데이터베이스를 비운다.
		User user = new User("asdf", "1234", "abc", "aaa@aaa.com", new Date(cal.getTimeInMillis()), "fb", new Date());
		int rowCnt = userDao.insertUser(user);
		assertTrue(rowCnt==1);
		
		user.setPwd("4321"); // 비밀번호를 변경한다.
		user.setEmail("bbb@bbb.com"); // 이메일을 변경한다.
		rowCnt = userDao.updateUser(user); //업데이트 되었는지 확인한다.
		assertTrue(rowCnt==1); //assertTrue 메서드를 통해 최종확인한다.
		
		User user2 = userDao.selectUser(user.getId()); // user의 정보를 user2에 넣는다.
		System.out.println("user = " + user);
		System.out.println("user2 = " + user2); //직접 값을 찍어봐서 확인한다.
		assertTrue(user.equals(user2)); //user와 user2의 데이터가 같은지 확인한다.
		
	}

}
