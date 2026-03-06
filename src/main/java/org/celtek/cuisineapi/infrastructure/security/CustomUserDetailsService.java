package org.celtek.cuisineapi.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.celtek.cuisineapi.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final SpringDataUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // On cherche l'utilisateur par son email
        var userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        // On le traduit dans le format que Spring Security comprend
        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword()) // Spring va comparer ce mot de passe haché avec celui fourni à la connexion
                .authorities(userEntity.getRole())
                .build();
    }
}
