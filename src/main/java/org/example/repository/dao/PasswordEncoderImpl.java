package org.example.repository.dao;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoderImpl extends PasswordEncoder {

    @Override
    public String encode(String password) {
        // Використовуємо бібліотеку Apache Commons Codec для хешування пароля
        // MD5 - це тільки один із варіантів, ви можете вибрати інший хеш-алгоритм
        return DigestUtils.md5Hex(password);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        // Порівнюємо незахешований пароль з закодованим
        return encode(rawPassword).equals(encodedPassword);
    }
}
