package com.recruitment.challenge.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.recruitment.challenge.dtos.request.ChangePasswordRequestDTO;
import com.recruitment.challenge.dtos.request.UserRequestDTO;
import com.recruitment.challenge.entities.Role;
import com.recruitment.challenge.entities.User;
import com.recruitment.challenge.exceptions.EntityNotFoundException;
import com.recruitment.challenge.exceptions.ExistingEmailException;
import com.recruitment.challenge.repositories.RoleRepository;
import com.recruitment.challenge.repositories.UserRepository;
import com.recruitment.challenge.security.JwtBlacklist;
import com.recruitment.challenge.security.JwtUtil;
import com.recruitment.challenge.services.UserService;
import com.recruitment.challenge.utilities.converters.UserConverter;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private JwtBlacklist jwtBlacklist;
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, 
			JwtBlacklist jwtBlacklist, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.jwtBlacklist = jwtBlacklist;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByIdAndValidated(Long id) throws EntityNotFoundException {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found for id: %d", id));
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(UserRequestDTO userRequestDTO) throws EntityNotFoundException {
		this.validateUserRegisterDTO(userRequestDTO);
		
		User user = UserConverter.dtoToEntity(userRequestDTO);
		user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

		for (String roleName : userRequestDTO.getRoles()) {
			Role role = roleRepository.findByName(roleName).orElseThrow(
					() -> new EntityNotFoundException(String.format("Role not found for role name: ", roleName)));
			user.addRole(role);
		}

		return userRepository.save(user);
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		User user = this.findByIdAndValidated(id);
		userRepository.delete(user);
	}

	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User not found for email: %s", email)));
	}

	@Override
	public User changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, String token) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Boolean isValidOldPass = passwordEncoder.matches(changePasswordRequestDTO.getOldPassword(), user.getPassword());
		
		if(!isValidOldPass) {
			throw new IllegalArgumentException("Old password doesn't match the registered password");
		}
		
		user.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));
		userRepository.save(user);
		jwtBlacklist.add(token.replace(JwtUtil.JWT_PROVIDER, ""));
		
		return user;
	}
	
	private void validateUserRegisterDTO(UserRequestDTO userRegisterDTO) {
		String email = userRegisterDTO.getEmail();
		if(userRepository.existsByEmail(email)) {
			throw new ExistingEmailException(String.format("There is already a registered user with email: ", email));
		}
	}

}
