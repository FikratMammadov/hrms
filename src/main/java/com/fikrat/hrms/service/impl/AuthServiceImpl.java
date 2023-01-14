package com.fikrat.hrms.service.impl;

import com.fikrat.hrms.constants.Messages;
import com.fikrat.hrms.dto.auth.JwtResponse;
import com.fikrat.hrms.dto.auth.LoginRequest;
import com.fikrat.hrms.dto.auth.RegisterRequest;
import com.fikrat.hrms.exception.CityNotFoundException;
import com.fikrat.hrms.exception.DuplicateUsernameException;
import com.fikrat.hrms.model.City;
import com.fikrat.hrms.model.Role;
import com.fikrat.hrms.model.User;
import com.fikrat.hrms.model.enums.ERole;
import com.fikrat.hrms.repository.CityRepository;
import com.fikrat.hrms.repository.RoleRepository;
import com.fikrat.hrms.repository.UserRepository;
import com.fikrat.hrms.security.jwt.JwtUtils;
import com.fikrat.hrms.security.service.UserDetailsImpl;
import com.fikrat.hrms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponse response = new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);

        return response;
    }

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUsernameException(Messages.DUPLICATE_USERNAME);
        }

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getFirstName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .city(city)
                .build();

        Role roleUser = roleRepository.findByName(ERole.ROLE_USER)
                .orElse(Role.builder().name(ERole.ROLE_USER).build());

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        roleRepository.saveAll(roles);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
