package com.tecsup.financego.service;

import com.tecsup.financego.common.type.UserDto;
import com.tecsup.financego.entity.TRoleEntity;
import com.tecsup.financego.entity.TUserEntity;
import com.tecsup.financego.error.BadRequestException;
import com.tecsup.financego.repository.TRoleRepository;
import com.tecsup.financego.repository.TUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TUserRepository userRepository;
    private final TRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto register(UserDto userDto) {
        Optional<TRoleEntity>  role = roleRepository.findByDescription(userDto.getRole().getDescription());

        if (role.isEmpty()) throw new BadRequestException("Role not found");

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        TUserEntity userEntity = new TUserEntity(userDto, role.get());
        userRepository.save(userEntity);

        return userEntity.toDto();
    }

    public UserDto getUser(Long id) {
        return userRepository.findById(id).map(TUserEntity::toDto).orElseThrow();
    }

    public UserDto getMe(Principal principal){
        TUserEntity userEntity = userRepository.findByEmail(principal.getName()).orElseThrow();
        return userEntity.toDto();
    }
}
