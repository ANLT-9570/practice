package com.dg.main.serviceImpl.orders.payment;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.dg.main.util.ResponseUtils;
import com.dg.main.util.Result;
import com.dg.main.vo.TransToVo;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ZhifubaoPay implements IPay {
    private HttpServletResponse response;
  //  AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
    AlipayTradeWapPayModel model = null;
    // AlipayTradeAppPayModel model=null;
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

//    public void setModel(AlipayTradeWapPayModel model) {
//        this.model = model;
//    }

//    public AlipayTradePrecreateRequest getModel() {
//        return model;
//    }

    public void setModel(AlipayTradeWapPayModel model) {
        this.model = model;
    }

    public static class Config{
        /** 支付宝gatewayUrl */
        //    public static String gatewayUrl;
        public static String gatewayUrl="https://openapi.alipay.com/gateway.do";
        /** 商户应用id */
        // public static String appid;

        public static String appid="2019080966153800";
        public static String pid="2088531972825512";
        public static String seller="771516106@qq.com";
        /** RSA私钥，用于对商户请求报文加签 */
        //  public static String appPrivateKey="MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQC3lertzGUwK0XRvZScnO34vbb3MTFyiODDGjSIPvn4koS5Ii0zwtqnhVgW2W3Cufpr+fwbh3AfI8OcF8sir76iyIgvJda0YHa/PV3r+V7dkg3Ah398Z3i3B00afZMs/TppqdYXq/Oyxq25vMdd+LvOvWEQ1a9evYX9FLBijkx+gH3sropV1Z06l8giiprIdDDpATtp5+7wHonl/6DQKA/n4BQbm8cSJ4rUatKPz08zoOsQtl0bYT9Fbdmlda9Edpf6NQRv0WG0A7hvSKq/DYs21RLkaQMbrX7YZe74l0xYurZgZIhXxJvfP0l7Wx5cD9uKvbyonKOMNTJvhU4SyESfAgMBAAECggEBAJWjIswVL90lSFF+/yTwXJ/Est+lPzKmEt2z/GGZQ1Kyym/ERafNCUBGVdi5/Ncdk9py9mTR7jfThNs0IaTyLqasYSRc6TY48lFKicWPix16sK7fQrB5/lDhKJ2RAWdIOFZr0k+VHpv3iR69mpsj5KDMh9Oa+Mh2HkJ8nJCYgA0S9/UtXuPRTl2fTYQ8c+WDa4jbo7SXNV5vqkk1jjoz0QitZW1IyxvpinO6LB5+dnpkP4c5+zOykGqxPMwJCY/Dw+8s/c9pxTJhtyoflQVIz4CXMKeIJWWthA5hFJ7O8uaE1erjqHIn2zo31IcyXgLP2hme2C3j1yQfM/EIsmm9fOECgYEA62gR+Tq4eiEilHK/hNFogm0YqndXtBB/5/aNJdqnHccbRS1ANlEgk9TNF/Q89v5Y4bMseE/B/HyV5gV2kIO14Ms8istRjBoJbgXHKTDLwhWJI/NpzS6l9YKgfoJmVfc1tmK3krw5j20y960R4a3gvQNneu+13Ftip5TwwDUpKUkCgYEAx6VRbm6bHl6kcw3ND0Pyl9pe1vkMRFD0bCkXQUnb8omsFD63J9+5YkPshD/LaF+iERzG0ARiYype0lS37SDwd+jpNiKPeUydUghmG3bI3q31p54YIt9HzcBxx0zkeiyc36JOUBa+mTn42Wp+nlWI+ORJIGR4EaLLH5vJjRPhpqcCgYEA3dCFRxt+oo3AYLKzJfqQCH22BUJI4MG/VBpsq6OZXqSzXH6kB8pv8PxOeOjJ3U6S/7DuETvOJRRbxo+VRdzlrziyBnJIl8Qc6JAHNxfkly/ltntxUUZIWMtKYYqL1SsIcKAaK+iyMCSMJvbsZ4OIxXGEu0FOUqHFlvTv2Zz9+QkCgYEAqZ2DbH4d3ZRsxK8B+6kvruHA14TpbhUVQ9s5PafBbPKYNu79bqv9dUXd6JHGf5tYNo+rPUmsSTHTM4D0bksg4c8Ia4y7NkPoGKy4CzdE+yLyZQDE4sOt3llUkQ17w2yprrnXLOXV8po1GIoInv5ui35vvkx9UgZr6iPR/cDZYlsCgYEA5ihkNTM9v8FH/Zg4baNqJQ0oMwgkqDF7GE1aaTKnbT0oVT4CROWr37IsQb/OVFGz9pJMNUajetyqogLvrJNyxM5VvNkFFu+K5KbRahcc/1O2KvQKQHL+49v2aj1Vx1bRJX+nJSvKf0JstRxl9Al3KziZXLLIt9hwZp3XW6sHV64=";
        public static String appPrivateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDO1w6iGXWllutsIRuDhl6uH9WEuYVfUfRyDK8BKih+deJj9Of4udnJnfA/mBM4vx53D50K8EVKesycJFsRPVOo+v5IJgKMKL2Me0vdyzSLjZozicfQVpNnx9Od60yOO3jOq6YSv0ItobnQIzxLAnvu2gck6eiyeVvNZ72v7UO4urnI9/3NI0a9CZV81jMn/Yms166ym36zgrQ4yiPPuK98lAfFnZ9lOSyfeBiK1O72Dj9NLMRytxJIr2ZDbLHWwbOazC3fNX+iHYzdMKZpw2AmN1N/0TqBWeFGW3/T1MFmWI7rHjOw9UvRuAxnRa31GWcmiRUrHfbtQ19fDVl3MN4fAgMBAAECggEAfJYXD1aMgYBFL61FeFSQZQtxve9NQZwIxEWVh/sPxxRUl+albzDq2MFUO8nrEgw0WnzCGBufcPxEkGxpmhm5cZg1X9ndQPUtLxf5G5BRhxl030RHYds0EJfRhFZ9l1KFMt87OiGPyBKY7KC8qWXGppkXIonuCsqEnFW3cOdWGkbwidbYhEzK1k0SVrqX00jsSat6kaOmgJJ3rdAMtjJ9A96Yz0F5FRwPucVul1bGa89QZwGX/wF1ytvdxTa1GkIKS09/KVj40Kc7UdpeMdTsSIlQlcMB7OyTXY8zM3hn4rHbR+RXn8aJRFCw9B6aqHCrCa2VcDipYMbU7zLYIa7VUQKBgQD5zHKikLzM3aLL9B3FoNpdaUf3rkNN3sU2yQcNc8JZXiqqLrabsXdUhA8ACPrkRWrRscg2cpG7VpsT4vzHNofnHZBfOAmmDGXnSS/Nh9uP9qa1PD8VpT/irYbOulzNwiK5WhK8/z+iXWBE3txMI1t5QG3pEnrIVN1vHTNoYqs0xwKBgQDT+Zf5DOHAMljEzRznl2+l7w/TrIcxpzcBqOkh0sSvWggi5MRyU/iZXVmlVUH3ogd1r3UwYk7gzDVg+w1fg4wzm1UYnDyMTdsA+pSXQHOHIMMSVy88KUcVatC0evmPm5zZuaTg87ohQUeDVIH0pge1FkRfFwjy9jk4ERWuM8SD6QKBgDhfgUdarqD1K7YnY0ikYmNOTSWCOEIskgkpGORaSXPtomdBn4hHZoy2OMRjQmudltu36V0QQf8rLlgwk6dJkSYBMAGtHdBRX/XwOE+D53L30Ot5qyrxXheD3bZtrF/q/fcicrQgTnYP6JfiS4kLOauFdMhymS74p/hZeK6IBeclAoGAbUZ5QsmSQ1txCZtXsEgQJXGVLiD93On/4FosPOSDwoXeNebLIskJGj7WbPYDuN4NjGy0NAop8Zc0WVdysG+HQj9sYfCBAi9MnDeRjFuMcve/uF+7mP8Khw72omPW7WJiPJeIWPMsW+JkNF7HU9CXnpHpE0Azu9SU8MNV8uT7BKkCgYEAx96glJLc6L2xZLAeatEyKKQNTc/CMKsF8jZMZCmJusDWDuFt9hnkOzKbHoLV0RjBNhaZDMyN3NJENf2Dw6oaFehzY0cGVhLGZZxxCy7OiFyfpU8V/KwYP0gW1TJlhcnLNRdqjHofc/iFpZjrpmo8Ol6LZN5GOlJzoLBIeZdI4ls=";
        public static String privateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsD6EkJfiGl07Gb17K+rsRpnBeb00MEKzInePeZJ5MoYlaMXtAKaf0Tq8Vy0qvEYIVrMh/HdGWa2VHjYXJJVvj7JZG8VEQbFlEI8dV3AG1WAnPiNgwZxiPT4d4VnXLcM7sRdrgDx98nTmVEYfNUa+Sq2jT16xjzERYuPzxQ6rMykmlfMQYN30oPqmGSSIekuHrxNNu7PNV+iBUqwqz7Yn+evIIIvkOjw9+yn2iuCDvWPpiAK5i/G+v+IlnXnnVhmfAFs0bMK2turxEYl6njywMI88CzMobgt3ZfXQ/w9gl7Hvc89wRaU9Rs4NDIlYXJg6Jc4Mz93/zPnNlYzz3YK2zAgMBAAECggEAMRDv8gAGBFOnujzOOnIP4LOm/bcdsKActcUY0zW8UrwKEDdmH2EBrVC26y8i6BPFFetE7cMSHqQXqLT5/udxcejo3eg5JsbPGfheAuUPc6hce9tZU0xDG7MhPbbiNpa/2278QMLvRrkLT7BFGs79k4TgCvFJDtiUcPyn3SFf1rArkcS8O6wR9lspdFmTkpXfuA8KOuGJkSvxW9sO4EYhUhXEqaLqq/hsoVodCfnPR+FQbcpcKpA609YU8j5dJC6mRpZi3D24Ktgk9TuFb5AOlg6jbItvGMFkuWnIvHMQiDwxikV12HV+AIvUr2f9dHVMGaco4ozNJ8dUEJDOXJOtyQKBgQD587b8bnJztvfZFgUnNw9eVOTD/JxvTthMqa3W9gxXloD0zx2ZrMo//b6fAOoz/0s3zqNd7pJgaQyhtqDM1ItBXtdSDPk7Yub8RMbjxunvcVejxO4Zz6/LKDRJlACYOlGM3h6PkIFwK2yeLKHtWVSQychzEXQAPMOIRrnQl1IwvQKBgQCwOW6wTXuYAR3VK8aQLot+59VGsPVLmLTWzrie08vVb4JXavgi3bvDAD+g73y8Dd2tHA8ICPZqqyL1ue2O8XNUOTaxtxqNT35tskMwGXwnmq9UCBEFB16Q2lFXn86iBUPfcSRjjuZ+3dE9jzK+dO9p2KnPJGwGU+rKkzSNyvfXLwKBgQCIAfqnHz4+AuiYgqfpL8e4D7+veWxyENgX1xiV6KrMmkOvcmfr8B7ZdSxdfvTxuE/onRM+EIwr092gvSyAaIMh3gaSSibwDqAb0xzaYql2thj3LzhUaTOB+22AP/2W19z6Jv9AJWSZcbeX1jeCXGJsqq/dFTkmy+ml5L5FZ6G8jQKBgQCmbbcx3htplTRdjLg8lxi7Q6vS1XD0ckXn0NffeFs7OdK5SJcMJqkbIvL0xPjiiZ3fzW4MwpomZ3Xe6DeJFlM7KcWT1T/IUVkvtHlrxcXRiW8oR237d9hBqU3FGXIG5j4RmkMpm13oVwkzwDfGU52AM1U8AkPbPgmyQ5AQka27lQKBgEx7bWaHJntabaBus1QnAsifXt7s4wQqevssDIHTwYa2AxMCfhm9iPMjeo9dwUyc+/14BuO3WJDrVZnaJnw0DdJbiKLdrEK9cgbXC6oZZGrUrkSKhxdGauNS6Iz5otHEaV5utIQv1AIKcrxmBLn1VlZd69f0tQkFyqYGynAoDydg";
        /** 支付宝RSA公钥，用于验签支付宝应答 */
        public static String alipayPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArfH3aR1BmufNFTpB+mTtCnveWkMAxKPO0/1hh9b51bRUBTqk7ClZ/uJVVtMm8pSogit5jHxbMKkyR++oSi/U+GAcQG/43DtBeZtgabhRYOH8JPs3swJYu1F1UYl2N1bQu5hKdXGkZ35Sr3QMufHjO/05cRu1Cck+UP6+yGrYy2r9HJHC1yZAWFxM3G7b4yezFUn2O9nrkCJXnGkyF52HLGXfeuw8e3xMf/MSS81TCRJXNkqnxx7UlBYuahxcfFHvggTg0Ddu7KnFwZd0Q0o1jeZfHH5u4BP+rKNclPe5LrJSocOBrDpBFhh7Yv1HBNvW5aqqD3EjgrmCDUwTpn168wIDAQAB";

        public  static String certPath="C:\\wowo\\base\\appCertPublicKey_2019111169070317.crt";
        public static  String alipayPublicCertPath="C:\\wowo\\base\\alipayCertPublicKey_RSA2.crt";
        public static String rootCertPath="C:\\wowo\\base\\alipayRootCert.crt";
        public static String newPrivateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCVzBMBoQlTEnQpSQCdwiokQU1q28IbNxs2UQKkpBbl3t6EQzCyJSk7GD4jO2WDSor58o5Y/4pZ3qYHWz04HvhKU/34vH84UPjAgVtmRojasnUOte51A8ijXZtyeIk83+aNiNusVnlSTU0xLeeMJJqENrvblQEMMGRSy6SptUu0PgV6vcSqSqnXQ0fWQ0lVCb0TEv4bLLbRjG2cjxc5RePKkiipJgTXT6JRrdh9MwVjlf4SHSV9gNdjvCSG7b00zKai9jxLvgzYBWiyz1VSLxstvLlP0AQ17q4wsL+zpCivGMOVUt5+/qxLOO0go7dQ02/TeJ33S17pw0ZdqwWiK9PVAgMBAAECggEAJTDAe0QO+5f2TRW52HEPY2bvIJuk6nrY/7hHLrcqlDGsDyxbV69QOELBkp+5iUoljP7ChqgTIlLb57GoVlmiDVojEPFDqKi5yZmUJqVWbu2lkFxs8pJY6u5fnKD+eaWhKPyUhYoGnv9gLdiWxz2DO1YXd+Hbm7ob/k5e+F9xhzMJN+36eEPWBz3qSajRXXt9FJVWaYkPoNES4/qIdu73MlFFIs2gg8+FsLQCtZuijLfjjKQxB2x/gJbPRysxlgZp7v5Cez1SkvYG+eKsbVDz6uXt4288/76gy1saWU2TqFh6hJn1AQK0aYjDDlLQAQqutxq5+Bzfz9QbgyrlNAcGQQKBgQDU1T7XKdwNUEr4dDw0L4zeoHHEXuU6/bRlO4QCvhzMIdcNUxcqYI6QdAPkq8Snopn2wUPcZvN4uKOr7tghelWyncV0ROp9zhzTi64k64piLoR9760dbFgtdM5f3NXIVrBxXrTVEwwpQkwDlAiPXwa+CASB4Rh4fqxH9Zqo8CXwJQKBgQC0Ld7tZ0j87NxwDWlCZCwhpPx22K+/Q2Ywi8DLjIYHOpbFXfnH/GDZ+hVTwFbPiHZ6cgDcR/qHszGFWrNXNJqbIp/6deBqfDwIv5PBbsGG62XYxJO8Rpi2etfynFNraD+/HpW0ScNSBW4LL6eQixCOliQ2ueCFRL+exhzJJdFt8QKBgQCH0WV+rSnOHTUahCJ/MvD0iPLQ27XxBUH/cBF4JpX99oJj8GhSFLglmsN5Q9wFFhCmlQKXPeOVYE6iQ5MNGiJiD6iX3/q8ObHPlc2VpfHzYbgPHcodgqxqpaWlKyoJ5LhIQKWpa7RRCAu2JNupknyc/ETUFS8mhb+vVB5UPJwZOQKBgEy3OzPZxjnqn/EVxLt/60pn9RalLC9Dgz8oQe5o5qgtSgS+/psn2hT1Zh9CxbOXZtq7r6IbA5DDu1IMTE/VFf73vyXlCqhMPUN8Tm0Nu1i24aZqDH0dkeggZdHmvZ39xSaa3yLkRUWnCjO5Fp90lpgfVUv32KDlpLD1qUv7zjABAoGANcNHikyMUBUq1TzXwejlq0PuxMg6om1cDbi9KBXC5kTWaTllZy/w0IooEC5jkKkXhGw96ZCQTQCLbmerGWZfkiHG3cbH21hBXMjs1sUQns1gEwJ3OeJxvDnRTmm2gDTuBiDiVSqgJv7EQPzcjFgNu7/4khSRy1WqQeWwbTWV5lg=";
        //   public static String alipayPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtneOxu7Bc3gRr3S8+jUOLNTF9JEB3cLxrdjiO1pAnN2KjjoU0/1eDDsi0SC29GZT2fWNd5hL26Bxmrr4YPrliOZBH92U2p2OJFYzu2arpTDw4vr6U2XV56yi41YcZdaqLkh/+VLTrVLsJICYJI+3gH4ss3rbiJXOMpP1a3MQ/MRFRolONfEFYoTU/DOL8d1Irg6H0WyVmmnKVcUNXTiU0peX8o72IxFRamiTGKvola/M0AomgvKMqHjo5JfRwCOtI7DYDoN85dqB8XByOLblfsnUbuM0rQ9fzgDLuEC0VgzDVoCcPnaHhrLfRTQBdYIfd7O2yLpRtqSYZ+kgs/4R1QIDAQAB";
        /** 签名类型 */
        public static String signType = "RSA2";

        /** 格式 */
        public static String formate = "json";
        /** 编码 */
        public static String charset = "UTF-8";

        //        /** 同步地址 */
//       // public static String returnUrl;
//        public static String returnUrl="http://localhost:8090/zhifubao/alipayReturnNotice.test";
//
//        /** 异步地址 */
//      //  public static String notifyUrl;
//        public static String notifyUrl="http://localhost:8090/zhifubao/alipayNotifyNotice.test";
        // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        public static String notifyUrl = "http://120.77.32.150:8080/zhifubao/alipayNotifyNotice";
        public static String appNotifyUrl="http://120.77.32.150:8080/zhifubao/alipayIosNotify";
        // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
        public static String returnUrl = "http://120.77.32.150:8080/zhifubao/alipayReturnNotice";
        /** 最大查询次数 */
        public static int maxQueryRetry = 5;
        /** 查询间隔（毫秒） */
        public static long queryDuration = 5000;
        /** 最大撤销次数 */
        public static int maxCancelRetry = 3;
        /** 撤销间隔（毫秒） */
        public static long cancelDuration = 3000;
    }
    @Override
    public void pay() throws AlipayApiException, IOException {
        System.out.println("-------------------------zhi fu bao -----start-------paying------------");
        AlipayClient alipayClient = new DefaultAlipayClient(Config.gatewayUrl,ZhifubaoPay.Config.appid,
                ZhifubaoPay.Config.appPrivateKey,"json",ZhifubaoPay.Config.charset,ZhifubaoPay.Config.alipayPublicKey,"RSA2");

        //  AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        // AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl(ZhifubaoPay.Config.notifyUrl);
        //  request.setReturnUrl(ZhifubaoPay.Config.returnUrl);
        if(model!=null&&response!=null) {
//            String orderInfo = null;
//            AlipayTradeAppPayResponse client = alipayClient
//                    .sdkExecute(request);
//            orderInfo = client.getBody();
//            System.out.println(client.getBody());// 就是orderString
//
//            try {
//                ResponseUtils.send(response, Result.successResult(orderInfo));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            request.setBizModel(model);
            String form = alipayClient.pageExecute(request).getBody();
            System.out.println("-------------------------zhi fu bao -----end-------paying------------");
            PrintWriter writer = response.getWriter();
            response.setContentType("text/html;charset=GBK");
            System.out.println(form);
            writer.write(form);
            writer.flush();
            writer.close();
        }


    }
    public String description() {
        StringBuilder sb = new StringBuilder("\nConfigs{");
        sb.append("支付宝网关: ").append(ZhifubaoPay.Config.gatewayUrl).append("\n");
        sb.append(", appid: ").append(ZhifubaoPay.Config.appid).append("\n");
        sb.append(", 商户RSA私钥: ").append(getKeyDescription(ZhifubaoPay.Config.appPrivateKey)).append("\n");
        sb.append(", 支付宝RSA公钥: ").append(getKeyDescription(ZhifubaoPay.Config.alipayPublicKey)).append("\n");
        sb.append(", 签名类型: ").append(ZhifubaoPay.Config.signType).append("\n");

        sb.append(", 查询重试次数: ").append(ZhifubaoPay.Config.maxQueryRetry).append("\n");
        sb.append(", 查询间隔(毫秒): ").append(ZhifubaoPay.Config.queryDuration).append("\n");
        sb.append(", 撤销尝试次数: ").append(ZhifubaoPay.Config.maxCancelRetry).append("\n");
        sb.append(", 撤销重试间隔(毫秒): ").append(ZhifubaoPay.Config.cancelDuration).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String getKeyDescription(String key) {
        int showLength = 6;
        if (StringUtils.isNotEmpty(key) && key.length() > showLength) {
            return new StringBuilder(key.substring(0, showLength)).append("******")
                    .append(key.substring(key.length() - showLength)).toString();
        }
        return null;
    }
}
