package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private SelenideElement error = $("[data-test-id=error-notification]");

    public CardPage(){
        transferButton.shouldBe(visible);
        cancelButton.shouldBe(visible);
    }
    public DashboardPage moneyTransaction(int transaction, DataHelper.CardsInfo cardsInfo){
        amountField.setValue(String.valueOf(transaction));
        fromField.setValue(cardsInfo.getNumber());
        transferButton.click();
        return new DashboardPage();

    }
    public void errorPage(){
        error.shouldBe(visible);
    }
}
