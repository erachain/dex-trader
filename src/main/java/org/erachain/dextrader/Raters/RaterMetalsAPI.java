package org.erachain.dextrader.Raters;
// 30/03 ++

import org.erachain.dextrader.settings.Settings;
import org.erachain.dextrader.traders.TradersManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * see https://metals-api.com
 *
 * {"success":true,"timestamp":1588617540,"date":"2020-05-04","base":"USD","rates":{"AED":3.6738213405,"AFN":76.25650818296,"ALL":113.27736786858,"AMD":480.22567803666,"ANG":1.79582232936,"AOA":559.69615305801,"ARS":66.97374447422,"AUD":1.5565328384,"AWG":1.800666,"AZN":1.67087194687,"BAM":1.78970528443,"BBD":2.01942291012,"BCH":0.004069079081114,"BDT":84.9386697492,"BGN":1.7939069423,"BHD":0.37791790922,"BIF":1906.24778,"BMD":1.00065,"BND":1.41702927303,"BOB":6.8976501084,"BRL":5.55235119764,"BSD":1.00074583992,"BTC":0.00011306215,"BTN":75.6475885064,"BWP":12.19668446326,"BYN":2.45559683585,"BYR":19613.132,"BZD":2.01698484528,"CAD":1.409007303,"CDF":1741.3748772188,"CHF":0.96653936125,"CLF":0.03036137455,"CLP":837.90022452804,"CNH":7.13784744827,"CNY":7.0642636245,"COP":3989.1685245,"CRC":567.8089983384,"CUC":1.00087,"CVE":101.5392464588,"CZK":24.84356438453,"DJF":177.7483151808,"DKK":6.84633420674,"DOP":54.85223163904,"DZD":128.67677232145,"EEK":14.62158239314,"EGP":15.755675901,"ERN":15.00726795982,"ETB":33.53358154696,"ETH":0.0048800136625354,"EUR":0.91717,"FJD":2.24687895,"FKP":0.80437995855,"GBP":0.803705,"GEL":3.21090843312,"GGP":0.80399416095,"GHS":5.80646481286,"GIP":0.80418705975,"GMD":51.12224263045,"GNF":9461.2399407631,"GTQ":7.72145154787,"GYD":209.7923020105,"HKD":7.75611102,"HNL":24.98363728896,"HRK":6.95299080078,"HTG":105.6583709626,"HUF":324.60811602945,"IDR":15171.111235,"ILS":3.5258752485,"IMP":0.8044683705,"INR":75.7207490837,"IQD":1190.439705,"ISK":146.36134693585,"JEP":0.8042915466,"JMD":142.8781551579,"JOD":0.7092223152,"JPY":106.83785313695,"KES":106.632960294,"KGS":78.90406737521,"KHR":4113.6170402112,"KMF":452.6702875025,"KRW":1226.8779042787,"KWD":0.3090247875,"KYD":0.8336889134,"KZT":427.941221805,"LAK":8975.0702119208,"LBP":1522.9703827887,"LKR":189.5414403106,"LRD":198.5396012085,"LSL":18.71266347855,"LTC":0.021314797059763,"LTL":2.9555746304,"LVL":0.6052287384,"LYD":1.42051661061,"MAD":9.89574466775,"MDL":17.9277445152,"MGA":3791.287999796,"MKD":56.38895672894,"MMK":1400.5160289105,"MNT":2791.5065228926,"MOP":7.98718901136,"MRO":357.1499700126,"MTL":0.68383372332,"MUR":40.00489883485,"MVR":15.45622457761,"MWK":737.87838115005,"MXN":24.19229983037,"MYR":4.31796761,"MZN":67.91539494668,"NAD":18.72207449388,"NGN":390.31989908118,"NIO":33.92349047244,"NOK":10.34429421284,"NPR":121.0571085059,"NZD":1.6548732348,"OMR":0.38517409862,"PAB":1.00019593562,"PEN":3.39514915234,"PGK":3.44039150336,"PHP":50.71912278534,"PKR":159.8780441952,"PLN":4.17651994,"PYG":6516.3065029316,"QAR":3.642279115,"RON":4.43097833004,"RSD":107.8800808666,"RUB":75.169837295,"RWF":935.7854,"SAR":3.76001950722,"SBD":8.2758465924,"SCR":17.55230541858,"SEK":9.86299656134,"SGD":1.4171891294,"SHP":0.8039378988,"SLL":9689.3892596702,"SOS":580.24334889458,"SRD":7.45881537945,"SSP":130.3433664,"STD":22064.616966681,"SVC":8.75129921299,"SZL":18.71303353405,"THB":32.40274944524,"TJS":10.25039332868,"TMT":3.500455,"TND":2.89570240402,"TOP":2.318908288,"TRY":7.0548970302,"TTD":6.75820130076,"TWD":29.8513260779,"TZS":2314.4933593786,"UAH":26.96547146832,"UGX":3799.6112132632,"USD":1,"UYU":42.30087761151,"UZS":10109.951599073,"VEF":9.98989900048,"VES":176341.17284,"VND":23460.97403,"VUV":121.43214268608,"WST":2.76689937412,"XAF":600.41764612608,"XAG":0.06761056784,"XAU":0.00058615822,"XCD":2.703522918,"XDR":0.73037643762,"XOF":599.73067477969,"XPD":0.000537300583,"XPF":109.95839658092,"XPT":0.0013041680991,"XRH":0.00013333333333333334,"XRP":4.6040478380865,"YER":250.4594265104,"ZAR":18.6172976775,"ZMK":9010.1075554073,"ZMW":18.33116960115,"ZWL":322.14490100045},"unit":"per ounce"}
 *
 * {"success":true,"timestamp":1570239120,"date":"2019-10-05","base":"USD",
 * "rates":{"AED":3.6010391749342854,"AFN":76.7130739970982,"ALL":109.48629759616963,"AMD":466.9722785380982,
 * "ANG":1.7213540645465177,"AOA":373.5804880200803,"ARS":56.517785577036605,"AUD":1.4478883181051785,"AWG":1.764636680914464,
 * "AZN":1.671543272812857,"BAM":1.7455237049527677,"BBD":1.9803635151562498,"BDT":82.93743774753928,"BGN":1.7467001294066964,
 * "BHD":0.36958354574330354,"BIF":1823.457903611607,"BMD":0.9803537116191963,"BND":1.352401866593482,"BOB":6.7822869691384815,
 * "BRL":3.977396964824911,"BSD":0.9807752637151785,"BTN":69.45267244421784,"BWP":10.825105878200892,"BYN":2.030655660562321,
 * "BYR":19214.932747735715,"BZD":1.9768871808949107,"CAD":1.305066467981607,"CDF":1632.2892847339285,"CHF":0.9757950668600891,
 * "CLF":0.025414689620015175,"CLP":701.2508450649017,"CNH":7.00892857142857,"CNY":7.008062428924375,"COP":3366.5346457001783,
 * "CRC":569.8649474530446,"CUC":0.9803537116191963,"CVE":98.86905807615177,"CZK":22.96581506607589,"DJF":174.2288478883214,
 * "DKK":6.6686639739617855,"DOP":51.47237951452857,"DZD":117.85850848986607,"EEK":14.589285714285714,"EGP":15.985686835810712,
 * "ERN":14.705656640916072,"ETB":29.07767538527857,"EUR":0.8928571428571428,"FJD":2.1617191482686606,"FKP":0.7969099251009731,
 * "GBP":0.7948747107956518,"GEL":2.9067879690992853,"GGP":0.7948453001843035,"GHS":5.3040959178071425,"GIP":0.7969099251009731,
 * "GMD":49.511639739617856,"GNF":9073.173949060714,"GTQ":7.625534292772857,"GYD":204.65377828320536,"HKD":7.685335869181606,
 * "HNL":24.19551096035446,"HRK":6.627240108231071,"HTG":94.0438649464732,"HUF":296.9442766950357,"IDR":13856.319360025,
 * "ILS":3.410850554880178,"IMP":0.7948453001843035,"INR":69.43359083957499,"IQD":1166.6209168267856,"ISK":121.35836437786605,
 * "JEP":0.7948453001843035,"JMD":131.66523959844642,"JOD":0.6936041723853927,"JPY":104.82632053644642,"KES":101.86893161052677,
 * "KGS":68.45369201207767,"KHR":4009.647024626518,"KMF":439.63999843143745,"KRW":1168.82708913375,"KWD":0.29822752048939283,
 * "KYD":0.8173650052939107,"KZT":381.4952188149464,"LAK":8651.6218471825,"LBP":1481.8083398690178,"LKR":178.01756695816067,
 * "LRD":206.41384847652677,"LSL":14.75469785498571,"LTL":2.894729618446339,"LVL":0.5930061566213125,"LYD":1.3872387357358926,
 * "MAD":9.50000098035357,"MDL":17.360141759146426,"MGA":3566.0403101839283,"MKD":55.058663385749995,"MMK":1503.6178777302678,
 * "MNT":2577.7150650954463,"MOP":7.922634406493839,"MRO":349.9866142504196,"MTL":0.6785714285714285,"MUR":35.7349113760241,
 * "MVR":15.14683541821875,"MWK":716.6389014156339,"MXN":19.13184777067589,"MYR":4.103372416767947,"MZN":60.76269263950446,
 * "NAD":14.754692953217857,"NGN":354.40152052860714,"NIO":33.01868260068214,"NOK":8.918130661542678,"NPR":111.12346182502678,
 * "NZD":1.5495960942708034,"OMR":0.37749009842750886,"PAB":0.98078016548375,"PEN":3.312653425355893,"PGK":3.3185355476256246,
 * "PHP":50.69948629465535,"PKR":152.93551429355358,"PLN":3.857107564409196,"PYG":6268.921193286517,"QAR":3.569505117446339,
 * "RON":4.2392494411983925,"RSD":104.90801635230356,"RUB":63.39036116230714,"RWF":907.8075369593749,"SAR":3.6776498960824995,
 * "SBD":8.11331320340375,"SCR":13.43186345633482,"SEK":9.650017646366964,"SGD":1.3518391435630357,"SHP":1.2949531390925892,
 * "SLL":9264.34290714107,"SOS":568.6054840986607,"SRD":7.311515234696697,"SSP":130.08035714285714,"STD":21137.200501941068,
 * "SVC":8.581432100701964,"SZL":14.754686090741965,"THB":29.82337751460714,"TJS":9.506101721500892,"TMT":3.431237990667053,
 * "TND":2.8003842986549103,"TOP":2.282116387592678,"TRY":5.584294733539821,"TTD":6.637096584447678,"TWD":30.285124112779464,
 * "TZS":2246.6801645033925,"UAH":24.110856437002674,"UGX":3609.221566801339,"UYU":36.47115603309642,"UZS":9264.342903219642,
 * "VEF":9.791286616210714,"VES":7153.839285714285,"VND":22752.539116113392,"VUV":113.82447551076785,"WST":2.6066997372651786,
 * "XAF":0.001708007864197066,"XAG":17.89135811130791,"XAU":0.000589871,"XCD":0.3774361251410705,"XDR":1.3951535360968164,
 * "XOF":0.0016972369991968604,"XPF":0.009345273461479193,"XRH":5199.999999999889,"YER":245.4319085526071,"ZAR":14.74119054154732,
 * "ZMK":8824.363351437232,"ZMW":12.892145406062498,"ZWL":315.6738961217232},
 * "unit":"per ounce"}
 *
 * ALL
 * https://metals-api.com/api/latest?access_key=
 *
 * USD/Gold:
 * https://metals-api.com/api/latest?access_key=YOUR_ACCESS_KEY&base=USD&symbols=XAU
 *
 * USD/Palladium:
 * https://metals-api.com/api/latest?access_key=YOUR_ACCESS_KEY&base=USD&symbols=XPD
 *
 * USD/Platinum:
 * https://metals-api.com/api/latest?access_key=YOUR_ACCESS_KEY&base=USD&symbols=XPT
 */
public class RaterMetalsAPI extends Rater {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaterMetalsAPI.class);

    private boolean TEST = false;

    public RaterMetalsAPI(TradersManager tradersManager, int sleepSec) {
        super(tradersManager, "metals-api", sleepSec);

        try {
            String apiKey = Settings.getInstance().apiKeysJSON.get("metals-api.com").toString();
            this.apiURL = "https://metals-api.com/api/latest?access_key=" + apiKey;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    // {"success":true,"timestamp":1570239120,"date":"2019-10-05","base":"USD","rates":{"AED":3.6010391749342854,"AFN":76.7130739970982,"ALL":109.48629759616963,"AMD":466.9722785380982,"ANG":1.7213540645465177,"AOA":373.5804880200803,"ARS":56.517785577036605,"AUD":1.4478883181051785,"AWG":1.764636680914464,"AZN":1.671543272812857,"BAM":1.7455237049527677,"BBD":1.9803635151562498,"BDT":82.93743774753928,"BGN":1.7467001294066964,"BHD":0.36958354574330354,"BIF":1823.457903611607,"BMD":0.9803537116191963,"BND":1.352401866593482,"BOB":6.7822869691384815,"BRL":3.977396964824911,"BSD":0.9807752637151785,"BTN":69.45267244421784,"BWP":10.825105878200892,"BYN":2.030655660562321,"BYR":19214.932747735715,"BZD":1.9768871808949107,"CAD":1.305066467981607,"CDF":1632.2892847339285,"CHF":0.9757950668600891,"CLF":0.025414689620015175,"CLP":701.2508450649017,"CNH":7.00892857142857,"CNY":7.008062428924375,"COP":3366.5346457001783,"CRC":569.8649474530446,"CUC":0.9803537116191963,"CVE":98.86905807615177,"CZK":22.96581506607589,"DJF":174.2288478883214,"DKK":6.6686639739617855,"DOP":51.47237951452857,"DZD":117.85850848986607,"EEK":14.589285714285714,"EGP":15.985686835810712,"ERN":14.705656640916072,"ETB":29.07767538527857,"EUR":0.8928571428571428,"FJD":2.1617191482686606,"FKP":0.7969099251009731,"GBP":0.7948747107956518,"GEL":2.9067879690992853,"GGP":0.7948453001843035,"GHS":5.3040959178071425,"GIP":0.7969099251009731,"GMD":49.511639739617856,"GNF":9073.173949060714,"GTQ":7.625534292772857,"GYD":204.65377828320536,"HKD":7.685335869181606,"HNL":24.19551096035446,"HRK":6.627240108231071,"HTG":94.0438649464732,"HUF":296.9442766950357,"IDR":13856.319360025,"ILS":3.410850554880178,"IMP":0.7948453001843035,"INR":69.43359083957499,"IQD":1166.6209168267856,"ISK":121.35836437786605,"JEP":0.7948453001843035,"JMD":131.66523959844642,"JOD":0.6936041723853927,"JPY":104.82632053644642,"KES":101.86893161052677,"KGS":68.45369201207767,"KHR":4009.647024626518,"KMF":439.63999843143745,"KRW":1168.82708913375,"KWD":0.29822752048939283,"KYD":0.8173650052939107,"KZT":381.4952188149464,"LAK":8651.6218471825,"LBP":1481.8083398690178,"LKR":178.01756695816067,"LRD":206.41384847652677,"LSL":14.75469785498571,"LTL":2.894729618446339,"LVL":0.5930061566213125,"LYD":1.3872387357358926,"MAD":9.50000098035357,"MDL":17.360141759146426,"MGA":3566.0403101839283,"MKD":55.058663385749995,"MMK":1503.6178777302678,"MNT":2577.7150650954463,"MOP":7.922634406493839,"MRO":349.9866142504196,"MTL":0.6785714285714285,"MUR":35.7349113760241,"MVR":15.14683541821875,"MWK":716.6389014156339,"MXN":19.13184777067589,"MYR":4.103372416767947,"MZN":60.76269263950446,"NAD":14.754692953217857,"NGN":354.40152052860714,"NIO":33.01868260068214,"NOK":8.918130661542678,"NPR":111.12346182502678,"NZD":1.5495960942708034,"OMR":0.37749009842750886,"PAB":0.98078016548375,"PEN":3.312653425355893,"PGK":3.3185355476256246,"PHP":50.69948629465535,"PKR":152.93551429355358,"PLN":3.857107564409196,"PYG":6268.921193286517,"QAR":3.569505117446339,"RON":4.2392494411983925,"RSD":104.90801635230356,"RUB":63.39036116230714,"RWF":907.8075369593749,"SAR":3.6776498960824995,"SBD":8.11331320340375,"SCR":13.43186345633482,"SEK":9.650017646366964,"SGD":1.3518391435630357,"SHP":1.2949531390925892,"SLL":9264.34290714107,"SOS":568.6054840986607,"SRD":7.311515234696697,"SSP":130.08035714285714,"STD":21137.200501941068,"SVC":8.581432100701964,"SZL":14.754686090741965,"THB":29.82337751460714,"TJS":9.506101721500892,"TMT":3.431237990667053,"TND":2.8003842986549103,"TOP":2.282116387592678,"TRY":5.584294733539821,"TTD":6.637096584447678,"TWD":30.285124112779464,"TZS":2246.6801645033925,"UAH":24.110856437002674,"UGX":3609.221566801339,"UYU":36.47115603309642,"UZS":9264.342903219642,"VEF":9.791286616210714,"VES":7153.839285714285,"VND":22752.539116113392,"VUV":113.82447551076785,"WST":2.6066997372651786,"XAF":0.001708007864197066,"XAG":17.89135811130791,"XAU":1533.894736842115,"XCD":0.3774361251410705,"XDR":1.3951535360968164,"XOF":0.0016972369991968604,"XPF":0.009345273461479193,"XRH":5199.999999999889,"YER":245.4319085526071,"ZAR":14.74119054154732,"ZMK":8824.363351437232,"ZMW":12.892145406062498,"ZWL":315.6738961217232},"unit":"per ounce"}
    @Override
    public boolean tryGetRate() {
        // TEST
        if (TEST) {
            this.parse("{\"success\":true,\"timestamp\":1570239120,\"date\":\"2019-10-05\",\"base\":\"USD\",\"rates\":{\"AED\":3.6010391749342854,\"AFN\":76.7130739970982,\"ALL\":109.48629759616963,\"AMD\":466.9722785380982,\"ANG\":1.7213540645465177,\"AOA\":373.5804880200803,\"ARS\":56.517785577036605,\"AUD\":1.4478883181051785,\"AWG\":1.764636680914464,\"AZN\":1.671543272812857,\"BAM\":1.7455237049527677,\"BBD\":1.9803635151562498,\"BDT\":82.93743774753928,\"BGN\":1.7467001294066964,\"BHD\":0.36958354574330354,\"BIF\":1823.457903611607,\"BMD\":0.9803537116191963,\"BND\":1.352401866593482,\"BOB\":6.7822869691384815,\"BRL\":3.977396964824911,\"BSD\":0.9807752637151785,\"BTN\":69.45267244421784,\"BWP\":10.825105878200892,\"BYN\":2.030655660562321,\"BYR\":19214.932747735715,\"BZD\":1.9768871808949107,\"CAD\":1.305066467981607,\"CDF\":1632.2892847339285,\"CHF\":0.9757950668600891,\"CLF\":0.025414689620015175,\"CLP\":701.2508450649017,\"CNH\":7.00892857142857,\"CNY\":7.008062428924375,\"COP\":3366.5346457001783,\"CRC\":569.8649474530446,\"CUC\":0.9803537116191963,\"CVE\":98.86905807615177,\"CZK\":22.96581506607589,\"DJF\":174.2288478883214,\"DKK\":6.6686639739617855,\"DOP\":51.47237951452857,\"DZD\":117.85850848986607,\"EEK\":14.589285714285714,\"EGP\":15.985686835810712,\"ERN\":14.705656640916072,\"ETB\":29.07767538527857,\"EUR\":0.8928571428571428,\"FJD\":2.1617191482686606,\"FKP\":0.7969099251009731,\"GBP\":0.7948747107956518,\"GEL\":2.9067879690992853,\"GGP\":0.7948453001843035,\"GHS\":5.3040959178071425,\"GIP\":0.7969099251009731,\"GMD\":49.511639739617856,\"GNF\":9073.173949060714,\"GTQ\":7.625534292772857,\"GYD\":204.65377828320536,\"HKD\":7.685335869181606,\"HNL\":24.19551096035446,\"HRK\":6.627240108231071,\"HTG\":94.0438649464732,\"HUF\":296.9442766950357,\"IDR\":13856.319360025,\"ILS\":3.410850554880178,\"IMP\":0.7948453001843035,\"INR\":69.43359083957499,\"IQD\":1166.6209168267856,\"ISK\":121.35836437786605,\"JEP\":0.7948453001843035,\"JMD\":131.66523959844642,\"JOD\":0.6936041723853927,\"JPY\":104.82632053644642,\"KES\":101.86893161052677,\"KGS\":68.45369201207767,\"KHR\":4009.647024626518,\"KMF\":439.63999843143745,\"KRW\":1168.82708913375,\"KWD\":0.29822752048939283,\"KYD\":0.8173650052939107,\"KZT\":381.4952188149464,\"LAK\":8651.6218471825,\"LBP\":1481.8083398690178,\"LKR\":178.01756695816067,\"LRD\":206.41384847652677,\"LSL\":14.75469785498571,\"LTL\":2.894729618446339,\"LVL\":0.5930061566213125,\"LYD\":1.3872387357358926,\"MAD\":9.50000098035357,\"MDL\":17.360141759146426,\"MGA\":3566.0403101839283,\"MKD\":55.058663385749995,\"MMK\":1503.6178777302678,\"MNT\":2577.7150650954463,\"MOP\":7.922634406493839,\"MRO\":349.9866142504196,\"MTL\":0.6785714285714285,\"MUR\":35.7349113760241,\"MVR\":15.14683541821875,\"MWK\":716.6389014156339,\"MXN\":19.13184777067589,\"MYR\":4.103372416767947,\"MZN\":60.76269263950446,\"NAD\":14.754692953217857,\"NGN\":354.40152052860714,\"NIO\":33.01868260068214,\"NOK\":8.918130661542678,\"NPR\":111.12346182502678,\"NZD\":1.5495960942708034,\"OMR\":0.37749009842750886,\"PAB\":0.98078016548375,\"PEN\":3.312653425355893,\"PGK\":3.3185355476256246,\"PHP\":50.69948629465535,\"PKR\":152.93551429355358,\"PLN\":3.857107564409196,\"PYG\":6268.921193286517,\"QAR\":3.569505117446339,\"RON\":4.2392494411983925,\"RSD\":104.90801635230356,\"RUB\":63.39036116230714,\"RWF\":907.8075369593749,\"SAR\":3.6776498960824995,\"SBD\":8.11331320340375,\"SCR\":13.43186345633482,\"SEK\":9.650017646366964,\"SGD\":1.3518391435630357,\"SHP\":1.2949531390925892,\"SLL\":9264.34290714107,\"SOS\":568.6054840986607,\"SRD\":7.311515234696697,\"SSP\":130.08035714285714,\"STD\":21137.200501941068,\"SVC\":8.581432100701964,\"SZL\":14.754686090741965,\"THB\":29.82337751460714,\"TJS\":9.506101721500892,\"TMT\":3.431237990667053,\"TND\":2.8003842986549103,\"TOP\":2.282116387592678,\"TRY\":5.584294733539821,\"TTD\":6.637096584447678,\"TWD\":30.285124112779464,\"TZS\":2246.6801645033925,\"UAH\":24.110856437002674,\"UGX\":3609.221566801339,\"UYU\":36.47115603309642,\"UZS\":9264.342903219642,\"VEF\":9.791286616210714,\"VES\":7153.839285714285,\"VND\":22752.539116113392,\"VUV\":113.82447551076785,\"WST\":2.6066997372651786,\"XAF\":0.001708007864197066,\"XAG\":17.89135811130791,\"XAU\":0.000589234,\"XCD\":0.3774361251410705,\"XDR\":1.3951535360968164,\"XOF\":0.0016972369991968604,\"XPF\":0.009345273461479193,\"XRH\":5199.999999999889,\"YER\":245.4319085526071,\"ZAR\":14.74119054154732,\"ZMK\":8824.363351437232,\"ZMW\":12.892145406062498,\"ZWL\":315.6738961217232},\"unit\":\"per ounce\"}");
            return true;
        } else {
            return super.tryGetRate();
        }
    }

    protected void parse(String result) {

        JSONObject json = null;
        try {
            //READ JSON
            json = (JSONObject) JSONValue.parse(result);
        } catch (NullPointerException | ClassCastException e) {
            //JSON EXCEPTION
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

        try {
            if (json == null || !(boolean) json.get("success"))
                return;
        } catch (Exception e) {
            return;
        }

        String base = json.get("base").toString();
        if (!base.equals("USD")) {
            return;
        }

        JSONObject tickers = (JSONObject) json.get("rates");
        for (Object item: tickers.entrySet()) {

            Map.Entry entry = (Map.Entry<String, Object>)item;
            String ticker = entry.getKey().toString();
            BigDecimal price = new BigDecimal(entry.getValue().toString());

            if (ticker.equals("XAU")) {
                if (cnt.DEVELOP_USE) {
                    setRate(1105L, 1106L, this.courseName, price);
                } else {
                    setRate(95L, 21L, this.courseName, price);
                }
            }
        }
    }
}