package test;

import data.DataHelper;
import data.DataMySql;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static data.DataMySql.cleanDatabase;
import static java.nio.channels.FileChannel.open;

public class SqlTest {
    @AfterAll
    static void teardown() {
        cleanDatabase();

    }

    @Test
    void successfulTestLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisible();
        var verificationCode = DataMySql.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());

    }

    @Test
    void randomUserTestError() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoRandom();
        var verificationPage = loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotificationVisible();

    }

    @Test
    void randomCodeTestError() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisible();
        var verificationCode = DataHelper.verificationCodeGenerate();
        verificationPage.verifyButtonClick(verificationCode.getCode());
        verificationPage.errorNotificationVisible();

    }
}



