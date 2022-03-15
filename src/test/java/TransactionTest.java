import com.codeborne.selenide.Configuration;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TransactionTest {
    @Test
    void shouldTransactionOfFistCardToSecond() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var LoginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.verify(verificationCode);
        val balanceFistCardBefore = dashboardPage.getFirstCardBalance();
        val balanceSecondCardBefore = dashboardPage.getSecondCardBalance();
        val transactionPage = dashboardPage.secondCard();
        int transaction = 200;
        transactionPage.moneyTransaction(transaction, DataHelper.FistCard());
        val balanceFistCardAfter = dashboardPage.getFirstCardBalance();
        val balanceSecondCardAfter = dashboardPage.getSecondCardBalance();
        assertEquals((balanceFistCardBefore-transaction), balanceFistCardAfter);
        assertEquals((balanceSecondCardBefore+transaction), balanceSecondCardAfter);


    }
}
