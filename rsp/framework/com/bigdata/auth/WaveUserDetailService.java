package com.bigdata.auth;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bigdata.bean.SysRole;
import com.bigdata.service.AuthManager;
import com.bigdata.util.CommonUtil;

@SuppressWarnings("deprecation")
public class WaveUserDetailService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(WaveUserDetailService.class);
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Collection<GrantedAuthority> authorities = null;
		try {
			authorities = getUserRoles(userId);
		} catch (SQLException e) {
			logger.debug(" WaveUserDetailService loadUserByUsername error . " , e);
		}
		
		return new User(userId, WaveAuthUtil.PWD, true, true, true, true, authorities);
	}

	private Collection<GrantedAuthority> getUserRoles(String userId) throws SQLException {
		Collection<GrantedAuthority> authorities = initGrantedAuthorities(userId);
		AuthManager authManager = (AuthManager) CommonUtil.getBean("authManager");
        List<SysRole> roles = authManager.getRolesByUserId(userId);

        for (SysRole sysRole : roles) {
        	logger.debug("this is role  ====================== " + sysRole.getRoleId());
            authorities.add(new GrantedAuthorityImpl(sysRole.getRoleId()));
        }
        
        return authorities;
	}

	private Collection<GrantedAuthority> initGrantedAuthorities(String userId) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl(userId));
		return authorities ;
	}

}
