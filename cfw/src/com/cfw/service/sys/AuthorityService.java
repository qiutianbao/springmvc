package com.cfw.service.sys;

import com.cfw.model.sys.Authority;
import core.service.Service;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * @author Ray
 * @
 */
public interface AuthorityService extends Service<Authority> {

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_RESTRICTED_ADMIN') or hasRole('ROLE_USER')")
	List<Authority> queryAllMenuList(String globalRoleKey, List<Authority> mainMenuList);

}
