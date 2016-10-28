package com.jcnetwork.iotums.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.jcnetwork.iotums.common.GuidGenerator;
import com.jcnetwork.iotums.common.Privilege;
import com.jcnetwork.iotums.common.util.DateUtils;
import com.jcnetwork.iotums.entity.User;
import com.jcnetwork.iotums.oauth.PasswordHandler;
import com.jcnetwork.iotums.oauth.WdcyUserDetails;
import com.jcnetwork.iotums.persistence.repository.UserMapper;
import com.jcnetwork.iotums.service.UserService;

/**
 * 处理用户, 账号, 安全相关业务
 *
 * @author Jingdong Wang
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null || user.getArchived()) {
			throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
		} else {
			user.getPrivileges().addAll(findPrivileges(user.getId()));
		}

		return new WdcyUserDetails(user);
	}

	@Override
	public User loadCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final Object principal = authentication.getPrincipal();

		if (authentication instanceof OAuth2Authentication && (principal instanceof String
				|| principal instanceof org.springframework.security.core.userdetails.User)) {
			return loadOauthUser((OAuth2Authentication) authentication);
		} else {
			final WdcyUserDetails userDetails = (WdcyUserDetails) principal;
			User user = userRepository.findByGuid(userDetails.user().getGuid());
			user.getPrivileges().addAll(findPrivileges(user.getId()));
			return user;
		}
	}

	@Override
	public List<User> loadUserOverview(User overviewUser) {
		List<User> users = userRepository.findUsersByUsername(overviewUser.getUsername());
		for (User user : users) {
			user.getPrivileges().addAll(findPrivileges(user.getId()));
		}
		return users;
	}

	@Override
	public boolean isExistedUsername(String username) {
		final User user = userRepository.findByUsername(username);
		return user != null;
	}

	@Override
	public void saveUser(User user) {
		String guid = GuidGenerator.generate();
		user.setGuid(guid);
		user.setPassword(PasswordHandler.md5(user.getPassword()));
		user.setCreateTime(DateUtils.getDate());
		userRepository.saveUser(user);

		List<Privilege> privileges = user.getPrivileges();
		if (privileges != null && privileges.size() > 0) {
			for (Privilege privilege : privileges) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("priviege", privilege.name());
				map.put("guid", guid);
				userRepository.savePrivilege(map);
			}
		}

	}

	private User loadOauthUser(OAuth2Authentication oAuth2Authentication) {
		User user = new User();
		user.username(oAuth2Authentication.getName());

		final Collection<GrantedAuthority> authorities = oAuth2Authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			user.getPrivileges().add(Privilege.valueOf(authority.getAuthority()));
		}

		return user;
	}

	/**
	 * 根据UserId 获取User privieges
	 * 
	 * @param userId
	 * @return
	 */
	private Collection<Privilege> findPrivileges(int userId) {
		List<String> strings = userRepository.findPrivilegesByUserId(userId);
		List<Privilege> privileges = new ArrayList<>(strings.size());
		privileges.addAll(strings.stream().map(Privilege::valueOf).collect(Collectors.toList()));
		return privileges;
	}

	@Override
	public boolean isExistedEmail(String email) {
		final User user = userRepository.findUserByEmail(email);
		return user != null;
	}
}