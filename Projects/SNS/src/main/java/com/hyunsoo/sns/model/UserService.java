package com.hyunsoo.sns.model;

import com.hyunsoo.sns.dto.request.UserCreateRequest;
import com.hyunsoo.sns.entity.User;
import com.hyunsoo.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    /**
     * username으로 UserDetails 엔티티를 불러오는 메소드
     * @param username : username
     * @return : null or UserDetails Entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * User Entity를 저장하는 메소드
     * @param userCreateRequest : dto
     */
    @Transactional
    public void save(UserCreateRequest userCreateRequest) {
        User entity = User.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword1()))
                .build();

        userRepository.save(entity);
    }
}
