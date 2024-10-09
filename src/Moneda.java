import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Moneda {
    @SerializedName("base_code")
    private String baseCode;
    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;
    private String result;

    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public Double getConversionRatesByKey(String key) {
        return conversionRates.get(key);
    }

    public Double CalcularConversion(String changeCode,Double monto){
        double rate = getConversionRatesByKey(changeCode);
        return rate * monto;
    }
}
