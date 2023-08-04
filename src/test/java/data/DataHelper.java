package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static String generateLogin() {
        return faker.elderScrolls().firstName();
    }

    private static String generatePassword() {
        return faker.internet().password();
    }

    public static AuthInfo getAuthInfoRandom() {
        return new AuthInfo(generateLogin(), generatePassword());
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode verificationCodeGenerate() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;
    }


}
