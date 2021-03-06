package com.citi.winner21.service;

import com.citi.winner21.config.SeleniumConfig;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.QuerySnapshot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@DependsOn("FirebaseInitialization")
@Service
public class ChatBotService {
    private static final Logger logger = Logger.getLogger(ChatBotService.class.getName());
    @Autowired
    private SeleniumConfig seleniumConfig;
    @Autowired
    ZaloService zaloService;
    WebDriver driver;
    @PreDestroy
    public void destroy() {
        if(this.driver !=  null) {
            this.driver.quit();
            logger.log(Level.INFO, "ChatBotService -> Closed Driver!");
        }
    }
    @PostConstruct
    public void init() {
        driver = seleniumConfig.getFireFoxWebDriver();
            this.listenerComamdZalo();

    }
    public void listenerComamdZalo() {
        EventListener<DocumentSnapshot> eventListener = (documentSnapshot, e) -> {
            ZaloCommand temp = documentSnapshot.toObject(ZaloCommand.class);
            logger.log(Level.INFO, "ChatBotService -> Change action {0}, " , new Object[]{temp.toString()});
            switch (temp.getAction()) {
                case  "LOGIN":
                    logger.log(Level.INFO, "ChatBotService -> Change {0}, " , new Object[]{temp.toString()});
                    doLogin();
                    return;
                 default:
                     logger.log(Level.INFO, "ChatBotService -> Change action {0}, " , new Object[]{temp.toString()});
            }
        };
        zaloService.listenerCommand(eventListener);
    }
    public void listenerComamdTrade() {
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EventListener<QuerySnapshot> eventListener = (documentSnapshot, e) -> {
            List<WefinexCommand> temp = documentSnapshot.toObjects(WefinexCommand.class);
            logger.log(Level.INFO, "ChatBotService -> Change {0}, ", new Object[]{temp.toString()});
            int countLose = 0;
            int countWin = 0;
            for (int i = 0; i < temp.size(); i++) {
                if ("THUA".equalsIgnoreCase(temp.get(i).getAction())) {
                    countLose++;
                } else {
                    break;
                }
            }
            for (int i = 0; i < temp.size(); i++) {
                if ("THANG".equalsIgnoreCase(temp.get(i).getAction())) {
                    countWin++;
                } else {
                    break;
                }
            }
            logger.log(Level.INFO, "ChatBotService -> Thang {0}, Thua {1}", new Object[]{countWin, countLose});
            String ms;
            if (countWin >= 2) {
                ms = "TH???NG " + countWin + " l???n li??n ti???p c?? h???i ?????u t??";
                WebElement chatEl = driver.findElement(By.cssSelector("#richInput"));
                chatEl.clear();
                chatEl.sendKeys(ms);
                chatEl.sendKeys(Keys.ENTER);
                chatEl.clear();
            }
            if (countLose >= 2) {
                ms = "THUA " + countLose + " l???n li??n ti???p [C???NH B??O]";
                WebElement chatEl = driver.findElement(By.cssSelector("#richInput"));
                chatEl.clear();
                chatEl.sendKeys(ms);
                chatEl.sendKeys(Keys.ENTER);
                chatEl.clear();
            }
        };
        zaloService.listenerCommandTrade(eventListener);
//        EventListener<DocumentSnapshot> eventListener = (documentSnapshot, e) -> {
//            WefinexCommand temp = documentSnapshot.toObject(WefinexCommand.class);
//            logger.log(Level.INFO, "ChatBotService -> Change {0}, " , new Object[]{temp.toString()});
//                try {
//
//              // open chat bot
//                    Thread.sleep(500);
//                    WebElement chatEl = driver.findElement(By.cssSelector("#richInput"));
//                    chatEl.clear();
//                    String keysPressed =  Keys.chord(Keys.SHIFT, Keys.ENTER);
//
//                   chatEl.sendKeys("Th???i gian: "+temp.getTime()+ "  -> L???nh:  " +(temp.getType().equalsIgnoreCase("T")? "T??NG" :"GI???M" )+ " -> Ti???n: " + temp.getPrice() +"\n");
//                    chatEl.sendKeys(keysPressed) ;
//                    chatEl.clear();
//
////                    chatEl.sendKeys("Th???i gian: "+temp.getTime());
////                    chatEl.sendKeys(Keys.ENTER);
////                    chatEl.clear();
////                    Thread.sleep(100);
////                    chatEl = driver.findElement(By.cssSelector("#richInput"));
////                    chatEl.sendKeys("L???nh: "+(temp.getType().equalsIgnoreCase("T")? "T??NG" :"GI???M"));
////                    Thread.sleep(100);
////                    chatEl.sendKeys(Keys.ENTER);
////                    chatEl.clear();
////                    chatEl = driver.findElement(By.cssSelector("#richInput"));
////                    chatEl.sendKeys("Ti???n ?????t: "+(temp.getPrice()));
////                    chatEl.sendKeys(Keys.ENTER);
//
//
//
//                    chatEl.sendKeys(keysPressed) ;
//
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//
//
//
//        };
//        zaloService.listenerCommandTrade(eventListener);
    };

    public void doLogin() {
        try {
            driver.get("https://chat.zalo.me");
            Thread.sleep(1000);
            String url = driver.getCurrentUrl();
            if(url.startsWith("https://chat.zalo.me")) {
                logger.log(Level.INFO, "ChatBotService -> is login!");
            } else {
                Thread.sleep(1000);
                if(url.contains("id.zalo.me")) {
                    List<WebElement> logoutEl = driver.findElements(By.cssSelector("div.bottom a[target='_blank']")).stream()
                            .filter( k -> k.getText().equals("????ng xu???t")).collect(Collectors.toList());
                    if(logoutEl.size() == 1) {
                        // login with click on avatar
                        WebElement avatar = driver.findElement(By.cssSelector("#app div.zLogin-layout.parentDisable  span.avatar")) ;
                        avatar.click();
                        logger.log(Level.INFO, "ChatBotService ->  login with click avt: {0}" , new Object[]{url});
                    } else {
                        // login with qr code
//                        [...document.querySelectorAll("li a[href='#']")].filter( k => k.textContent === 'V???i M?? QR')[0].click()
                        driver.findElements(By.cssSelector("li a[href='#']")).stream().filter( k -> k.getText().equalsIgnoreCase("V???I M?? QR")).forEach( z -> z.click());
                        logger.log(Level.INFO, "ChatBotService -> login by QR Code: {0}" , new Object[]{url});
//                        document.querySelector('.qrcode img').getAttribute('src')
                        Thread.sleep(1*1000);
                       this.zaloService.saveQR(driver.findElement(By.cssSelector(".qrcode img")).getAttribute("src"));
                        Thread.sleep(60*1000);
                        this.zaloService.saveQR("");
                        List<WebElement> gEl = driver.findElements(By.cssSelector("input#contact-search-input"));
                        logger.log(Level.INFO, "ChatBotService -> " + gEl.size());
                        for( int i = 0 ; i <  gEl.size() ; i++) {
                            WebElement z =   gEl.get(i) ;
                            Thread.sleep(3*100);
                            z.clear();
                            z.sendKeys("WEFINEX_BOT");
                            Thread.sleep(3*100);
                            z.sendKeys(Keys.ENTER);
                            this.listenerComamdTrade();
                        }
                    }
                } else {
                    logger.log(Level.SEVERE, "ChatBotService -> cannot login with url: {0}" , new Object[]{url});
                }
            }
        }
        catch (Exception ex) {
            logger.log(Level.SEVERE, "ChatBotService -> Erro Fail {0}, " , new Object[]{ex});
        }
    }
}
