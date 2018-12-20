package com.ouresports.grimstroke.lib.aliyun.service;

import com.google.common.collect.Maps;
import lombok.Setter;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author will
 * @date 2018/12/5
 */
@Setter
public class AliyunBaseService {
    protected String accessKeyId;
    protected String accessKeySecret;
    protected String regionId;
    protected String apiVersion;

    private final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    /**
     * @param domain        请求地址
     * @param httpMethod    HTTP请求方式GET，POST等
     * @param publicParams  公共参数
     * @param privateParams 接口的私有参数
     * @return 最后的url
     */
    protected String generateURL(String domain, String httpMethod, Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> allEncodeParams = getAllParams(publicParams, privateParams);
        String cqsString = getCQS(allEncodeParams);
        String stringToSign = httpMethod + "&" + percentEncode("/") + "&" + percentEncode(cqsString);
        String signature = hmacSHA1Signature(this.accessKeySecret, stringToSign);
        return domain + "?" + cqsString + "&" + percentEncode("Signature") + "=" + percentEncode(signature);
    }

    protected List<String> getAllParams(Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> encodeParams = new ArrayList<>();
        urlEncodeParams(encodeParams, publicParams);
        urlEncodeParams(encodeParams, privateParams);
        return encodeParams;
    }

    protected void urlEncodeParams(List<String> encodeParams, Map<String, String> params) {
        if (params != null) {
            for (String key : params.keySet()) {
                String value = params.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
    }

    /**
     * 获取CQS 的字符串
     *
     * @param allParams
     * @return
     */
    protected static String getCQS(List<String> allParams) {
        ParamsComparator paramsComparator = new ParamsComparator();
        Collections.sort(allParams, paramsComparator);
        String cqString = "";
        for (int i = 0; i < allParams.size(); i++) {
            cqString += allParams.get(i);
            if (i != allParams.size() - 1) {
                cqString += "&";
            }
        }

        return cqString;
    }

    protected static String hmacSHA1Signature(String accessKeySecret, String stringtoSign) {
        try {
            String key = accessKeySecret + "&";
            try {
                SecretKeySpec signKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
                Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
                mac.init(signKey);
                byte[] rawHmac = mac.doFinal(stringtoSign.getBytes());
                //按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
                return new String(new BASE64Encoder().encode(rawHmac));
            } catch (Exception e) {
                throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
            }
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 参数urlEncode
     *
     * @param value
     * @return
     */
    protected static String percentEncode(String value) {
        try {
            String urlEncodeOrignStr = URLEncoder.encode(value, "UTF-8");
            String plusReplaced = urlEncodeOrignStr.replace("+", "%20");
            String starReplaced = plusReplaced.replace("*", "%2A");
            String waveReplaced = starReplaced.replace("%7E", "~");
            return waveReplaced;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 生成OpenAPI公共参数
     * 不需要修改
     * @return
     */
    protected Map<String, String> generatePublicParameters() {
        Map<String, String> publicParams = Maps.newHashMap();
        publicParams.put("Format", "JSON");
        publicParams.put("Version", apiVersion);
        publicParams.put("AccessKeyId", accessKeyId);
        publicParams.put("SignatureMethod", "HMAC-SHA1");
        publicParams.put("Timestamp", generateTimestamp());
        publicParams.put("SignatureVersion", "1.0");
        publicParams.put("SignatureNonce", generateRandom());
        return publicParams;
    }

    /**
     * 生成当前UTC时间戳
     *
     * @return
     */
    protected static String generateTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    /**
     * 生成随机数
     *
     * @return
     */
    protected static String generateRandom() {
        String signatureNonce = UUID.randomUUID().toString();
        return signatureNonce;
    }

    protected static class ParamsComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }
}
