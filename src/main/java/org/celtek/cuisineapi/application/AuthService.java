package org.celtek.cuisineapi.application;

import lombok.RequiredArgsConstructor;
import org.celtek.cuisineapi.domain.model.User;
import org.celtek.cuisineapi.domain.port.UserRepository;
import org.celtek.cuisineapi.infrastructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(User userToCreate) {
        // 1. Vérification métier : L'email existe-t-il déjà ?
        if (userRepository.existsByEmail(userToCreate.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
            // Plus tard, tu pourras créer une vraie BusinessException propre.
        }

        // 2. Sécurité : On hache le mot de passe
        String hashedPassword = passwordEncoder.encode(userToCreate.getPassword());
        userToCreate.setPassword(hashedPassword);

        // 3. Règle métier : Tout nouvel inscrit est un simple utilisateur
        userToCreate.setRole("ROLE_USER");

        // 4. On sauvegarde en base de données
        userRepository.save(userToCreate);
    }

    public String login(String email, String password) {
        // 1. Spring Security vérifie l'email et le mot de passe (ça plante tout seul si c'est faux)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return jwtService.generateToken(email);
    }
}
