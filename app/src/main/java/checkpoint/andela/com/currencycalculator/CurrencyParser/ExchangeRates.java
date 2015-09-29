package checkpoint.andela.com.currencycalculator.CurrencyParser;

/**
 * Created by andela-cj on 9/29/15.
 */
public enum ExchangeRates {
    USD (1.0)
    ,KWD (0.3021)
    ,BHD (0.3772)
    ,OMR (0.3851)
    ,GBP (0.6584)
    ,JOD (0.7089)
    ,KYD (0.8200)
    ,EUR (0.8942)
    ,CHF (0.9785)
    ,AZN (1.0465)
    ,CAD (1.3334);

    double rate;

    ExchangeRates(double x) {
        rate = x;
    }
}
