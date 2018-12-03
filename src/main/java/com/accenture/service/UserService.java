package com.accenture.service;

import com.accenture.enums.Role;
import com.accenture.dao.User;
import com.accenture.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        // шифрование пароля с помощью password encoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        // проверка что поле email не пустое
        // отправка только для НЕ пустых срочек
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s \n" +
                            "Welcome to DepartmentIT. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(), // %s
                    user.getActivationCode()
            );
            mailService.send(user.getEmail(), "Activation code", message);
        }
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String,String> form) {

        user.setUsername(username);

        // прежде чем обновлять роли у пользователя,
        // нужно получить список этих ролей,
        // для того что бы проверить, что они установленны
        // данному пользователю
        // запрашиваем роли и переводим из enum в строковый вид
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        // теперь проверяем что данная форма
        // содердит роли для данного пользователя
        for (String key : form.keySet()) {
            if(roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }


    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

    // Обновление данных в профиле пользователя
    public void updateProfile(User user, String password, String email) {
        // получаем текущий email
        String userEmail = user.getEmail();
        // проверяем изменился ли он
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        // если email изменился, то нам нужно его обновить у пользователя
        if (isEmailChanged) {
            user.setEmail(email);
            // если email обновлен, нужно отправить новый ActivationCode
            // его нужно сгенерировать;

            // проверяем что пользователь установил новый email
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        // проверяем что пользователь установил новый пароль
        if(!StringUtils.isEmpty(password)) {
            // так же добавляем шифрование для ищмененного пароля
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepo.save(user);
        // почту мы отправляем только тогда, когда email был изменен
        if (isEmailChanged) {
            sendMessage(user);
        }
    }

}
