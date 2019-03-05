package com.ouresports.grimstroke.lib.streamlink;

import com.alibaba.fastjson.JSONObject;
import com.ouresports.grimstroke.lib.exception.LibServiceException;
import com.ouresports.grimstroke.lib.util.OkHttpUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author will
 * @date 2018/12/19
 */
public class Huya {
    private static final String API_URL = "https://www.huya.com";

    private String roomId;

    public Huya(String roomId) {
        this.roomId = roomId;
    }

    public String getStreamLink() throws LibServiceException {
        try {
            String body = getResponseBody();
            String jsonBody = getFilteredCh(body, "hyPlayerConfig = ([^;]+);");
            JSONObject jsonObject = JSONObject.parseObject(jsonBody).getJSONObject("stream").getJSONArray("data").getJSONObject(0).getJSONArray("gameStreamInfoList").getJSONObject(0);
            return String.format("%s/%s/playlist.m3u8", jsonObject.getString("sHlsUrl"), jsonObject.getString("sStreamName"));
        } catch (Exception e) {
            throw new LibServiceException("获取huya直播流失败");
        }
    }

    private String getResponseBody() {
        String url = String.format("%s/%s", API_URL, roomId);
        return OkHttpUtil.httpGet(url);
    }

    public static String getFilteredCh(String str, String Regex){
        if(null == str){
            return "";
        }
        String result;
        Pattern pat = Pattern.compile(Regex);
        Matcher mat = pat.matcher(str);
        boolean rs = mat.find();
        if(rs) {
            result = mat.group(1);
        }else{
            result = "";
        }
        return result;
    }
}
