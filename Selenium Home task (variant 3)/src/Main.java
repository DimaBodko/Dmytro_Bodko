public class Main {
    public static void main(String[] args) throws InterruptedException {
        PayGraderTest tester = new PayGraderTest();
        tester.LoginPage();
        tester.MainPage();
        tester.PayGradePage();
        if (tester.checkRow()) {
            tester.removeRow();
        }
    }
}
