package com.cfw.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:resource/applicationContext.xml", "classpath:applicationContext-security.xml" })
public class SpringSecurityTest {
//
//	@Resource
//	private AuthorityService roleAuthorityService;
//
//	@Test
//	public void testAuthority() {
//		List<Authority> authorityList = roleAuthorityService.doQueryAll();
//		List<Authority> resultList = roleAuthorityService.queryAllMenuList("ROLE_ADMIN", authorityList);
//		System.out.println(resultList);
//	}

}
