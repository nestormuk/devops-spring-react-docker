package com.expense.app.smartexpensebudgettrackerstudentsapp.service;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import com.expense.app.smartexpensebudgettrackerstudentsapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final JavaMailSender mailSender;
    private final Random random = new Random();

    @Override
    @Transactional
    public User registerUser(String name, String email) {
        String otp = String.format("%06d", random.nextInt(999999));

        User user = userRepository.findByEmail(email)
                .orElse(new User(null, email, name, otp, false));

        user.setOtp(otp);
        userRepository.save(user);

        sendOtpEmail(email, otp);
        return user;
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getOtp().equals(otp)) {
            User user = userOpt.get();
            user.setVerified(true);
            user.setOtp(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);
    }
}
