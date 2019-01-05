package com.ouresports.grimstroke.lib.sensiwords;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

/**
 *
 * @author will
 * @date 2019/1/4
 */
@Service
public class SensitiveWordFilterService {
    private static Set<String> words;
    private static Map<String, String> badWordMap;
    private static final String END_FLAG = "isEnd";
    private static final int minMatchTYpe = 1;
    private static final int maxMatchType = 2;

    public SensitiveWordFilterService() throws FileNotFoundException {
        SensitiveWordFilterService.words = Sets.newHashSet();
        File directory = ResourceUtils.getFile("classpath:dictionary");
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    initWords(file);
                }
            }
        }
        initBadWordMap();
    }

    private void initWords(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String temp;
            while ((temp = reader.readLine()) != null) {
                words.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initBadWordMap() {
        badWordMap = new HashMap(words.size());
        Map nowMap;
        for (String key: words) {
            nowMap = badWordMap;
            for (int i=0; i<key.length(); i++) {
                char keyChar = key.charAt(i);
                Object wordMap = nowMap.get(keyChar);

                if (wordMap == null) {
                    Map<String, String> newWordMap = Maps.newHashMap();
                    newWordMap.put(END_FLAG, "0");
                    nowMap.put(keyChar, newWordMap);
                    nowMap = newWordMap;
                } else {
                    nowMap = (Map) wordMap;
                }

                if (i == key.length() - 1) {
                    nowMap.put(END_FLAG, "1");
                }
            }
        }
    }

    /**
     * 获取文字中的敏感词
     * @param txt 文字
     * @param matchType 匹配规则 1：最小匹配规则，2：最大匹配规则
     * @return
     * @version 1.0
     */
    public Set<String> getBadWord(String txt , int matchType){
        Set<String> sensitiveWordList = Sets.newHashSet();

        for(int i = 0 ; i < txt.length() ; i++){
            int length = checkBadWord(txt, i, matchType);
            if(length > 0){
                sensitiveWordList.add(txt.substring(i, i+length));
                i = i + length - 1;
            }
        }

        return sensitiveWordList;
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    @SuppressWarnings({ "rawtypes"})
    public static int checkBadWord(String txt,int beginIndex,int matchType){
        boolean  flag = false;
        int matchFlag = 0;
        char word;
        Map nowMap = badWordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);
            if(nowMap != null){
                matchFlag++;
                if("1".equals(nowMap.get(END_FLAG))){
                    flag = true;
                    if(minMatchTYpe == matchType){
                        break;
                    }
                }
            }
            else{     //不存在，直接返回
                break;
            }
        }
        /*“粉饰”匹配词库：“粉饰太平”竟然说是敏感词
         * “个人”匹配词库：“个人崇拜”竟然说是敏感词
         * if(matchFlag < 2 && !flag){
            matchFlag = 0;
        }*/
        if(!flag){
            matchFlag = 0;
        }
        return matchFlag;
    }

    /**
     * 替换敏感字字符
     * @param txt
     * @param matchType
     * @param replaceChar 替换字符，默认*
     * @version 1.0
     */
    public String replaceBadWord(String txt, int matchType, String replaceChar){
        String resultTxt = txt;
        Set<String> set = getBadWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word;
        String replaceString;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    /**
     * 获取替换字符串
     * @param replaceChar
     * @param length
     * @return
     * @version 1.0
     */
    private String getReplaceChars(String replaceChar,int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++) {
            resultReplace += replaceChar;
        }

        return resultReplace;
    }
}
