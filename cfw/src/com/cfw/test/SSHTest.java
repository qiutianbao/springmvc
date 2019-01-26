package com.cfw.test;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:resource/applicationContext.xml" })
public class SSHTest {

//	@Resource
//	private SysUserDao sysUserDao;

//	@Resource
//	private SysUserService sysUserService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public final void testSave() {
		// System.out.println("sysUserService:::" + sysUserService);
//		SysUser sysUser = sysUserService.getByProerties("userName", "admin");
//        Assert.assertEquals(sysUser.getUserName(),("admin")) ;
	}

}
