package com.deepak.horsetrack.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties("app")
public class ApplicationProperties {

    private TellerMachine tm;
    // This messages can be localized for different lang and loaded from Cache on startup of application
    private Map<String, String> localMessages;

    private Map<String, String> errorMessages;

    public static class TellerMachine{
        private String currencySymbol;
        private String maxHorses;
        private String restockAmount;
        public String getCurrencySymbol() {
            return currencySymbol;
        }

        public void setCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
        }

        public String getMaxHorses() {
            return maxHorses;
        }

        public void setMaxHorses(String maxHorses) {
            this.maxHorses = maxHorses;
        }

        public String getRestockAmount() {
            return restockAmount;
        }

        public void setRestockAmount(String restockAmount) {
            this.restockAmount = restockAmount;
        }


    }

}
