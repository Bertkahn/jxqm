package com.cloud.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }
    //
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return o.toString().trim().equals("");
        } else if (o instanceof List) {
            return ((List) o).size() == 0;
        } else if (o instanceof Map) {
            return ((Map) o).size() == 0;
        } else if (o instanceof Set) {
            return ((Set) o).size() == 0;
        } else if (o instanceof Object[]) {
            return ((Object[]) o).length == 0;
        } else if (o instanceof int[]) {
            return ((int[]) o).length == 0;
        } else if (o instanceof long[]) {
            return ((long[]) o).length == 0;
        } else if (o instanceof Integer) {
            return (Integer)o == 0;
        } else if (o instanceof Long) {
            return (Long)o == 0;
        } else if (o instanceof Float) {
            return (Float)o == 0;
        } else if (o instanceof Double) {
            return (Double)o == 0;
        } else if (o instanceof BigDecimal) {
            return ((BigDecimal) o).compareTo(BigDecimal.ZERO) == 0;
        }
        return false;
    }
    // 积分转钱
    public static BigDecimal scoreToMoney (Integer score) {
        return new BigDecimal(score).divide(new BigDecimal(100), 2, RoundingMode.FLOOR);
    }
    // 钱转积分
    public static Integer moneyToScore (BigDecimal money) {
        return money.multiply(new BigDecimal(100)).intValue();
    }
    //n位随机数
    public static int createRandomNum(int n){
        return createRandomNum((int)Math.pow(10, n - 1), (int)Math.pow(10, n) - 1);
    }
    // 创建token
    public static String createToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    //随机数
    public static int createRandomNum(int min,int max){
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    // 加密
    public static String encryptPassword (String password, String pre, String after) {
        return MD5Util.encrypt(pre + password + after);
    }
    //获取ip地址
    public static String getIp() {
        HttpServletRequest request = getServletRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    //email校验
    public static Boolean checkEmail(String email){
        boolean flag;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    //phone校验
    public static Boolean checkPhone(String phone){
        boolean flag;
        try {
            String check = "^[0-9]{6,16}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(phone);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    //account检验
    public static Boolean checkAccount(String account){
        boolean flag;
        try {
            String check = "^[a-zA-Z][0-9a-zA-Z_]{5,15}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(account);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    //字符串校验
    public static Boolean checkString(String str){
        if (str == null || str.equals("")){
            return false;
        }else {
            return true;
        }
    }
    public static Boolean checkString(String str, int length){
        if (str == null || str.length() < length){
            return false;
        }else {
            return true;
        }
    }
    public static Boolean checkString(String str, int small, int big){
        if (str == null || str.length() < small || str.length() > big){
            return false;
        }else {
            return true;
        }
    }

    //创建n位字符串
    public static String creatRandomStr(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    //querystring转map
    public static Map<String,String> stringToMap(String string){
        Map<String,String> map = new HashMap<String, String>();
        String[] list = string.split("&");
        for (String item:list) {
            String[] per = item.split("=");
            String first = per[0];
            String second = "";
            if (per.length == 2){
                second = per[1];
            }
            map.put(first, second);
        }
        return map;
    }

    //map转querystring
    public static String mapToString(Map<String,String> map){
        StringBuilder result = null;
        Collection<String> keyset= map.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);//对key键值按字典升序排序
        for (String key:list) {
            if (result == null){
                result = new StringBuilder(key + "=" + map.get(key));
            }else {
                result.append("&").append(key).append("=").append(map.get(key));
            }
        }
        assert result != null;
        return result.toString();
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        try {
            return JSONObject.parseObject(JSON.toJSONString(map), clazz);
        } catch (Exception e) {
            return null;
        }
    }
    public static Map<String, Object> objectToMap(Object obj) {
        if(obj == null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                map.put(key, value);
            }
        } catch (Exception e) {
            return null;
        }
        return map;
    }

    // 转tree结构
    public static List<Map<String, Object>> listToTreeList (List<Map<String, Object>> list, String id, String pid) {
        List<Map<String, Object>> rootList = new ArrayList<>();
        list.removeIf(item -> {
            for (Map<String, Object> jtem : list) {
                if (item.get(pid).toString().equals(jtem.get(id).toString()))
                    return false;
            }
            item.put("_level", 0);
            item.put("_prefix", "");
            rootList.add(item);
            return true;
        });
        List<Map<String, Object>> result = listToTreeListFor (rootList, list, 0, id, pid);
        Integer temp = 0;
        for (int i = 0; i < result.size(); i++) {
            Map<String, Object> item = result.get(i);
            Integer level = Integer.parseInt(item.get("_level").toString());
            StringBuilder space = new StringBuilder();
            for (int j = 0; j < level; j++) {
                space.append(".  ");
            }
            item.put("_prefix", space + (level == 0 ? "" : "├"));
            if (temp > level && i > 0) {
                Map<String, Object> sub = result.get(i - 1);
                sub.put("_prefix", sub.get("_prefix").toString().replace("├", "└"));
            }
            temp = level;
        }
        return result;
    }
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> listToTreeListFor (List<Map<String, Object>> rootList, List<Map<String, Object>> otherList, Integer level, String id, String pid) {
        for (int i = 0; i < rootList.size(); i++) {
            Map<String, Object> item = rootList.get(i);
            if (Integer.parseInt(item.get("_level").toString()) != level)
                continue;
            Iterator jt= otherList.iterator();
            while(jt.hasNext()) {
                Map<String, Object> jtem = (Map<String, Object>) jt.next();
                if (item.get(id).toString().equals(jtem.get(pid).toString())) {
                    jtem.put("_level", level + 1);
                    i++;
                    rootList.add(i, jtem);
                    jt.remove();
                }
            }
        }
        level++;
        if (CommonUtil.isNotEmpty(otherList))
            return listToTreeListFor(rootList, otherList, level, id, pid);
        else
            return rootList;
    }

    // 转tree结构
    public static List<Map<String, Object>> listToTree (List<Map<String, Object>> list, String id, String pid, String listName) {
        List<Map<String, Object>> bottomList = new ArrayList<>();
        list.removeIf(item -> {
            item.put(listName, new ArrayList<>());
            for (Map<String, Object> jtem : list) {
                if (item.get(id).toString().equals(jtem.get(pid).toString()))
                    return false;
            }
            bottomList.add(item);
            return true;
        });
        return listToTreeFor (bottomList, list, id, pid, listName);
    }
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> listToTreeFor (List<Map<String, Object>> bottomList, List<Map<String, Object>> otherList, String id, String pid
            , String listName) {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterator it = otherList.iterator();
        while(it.hasNext()) {
            Map<String, Object> item = (Map<String, Object>) it.next();
            boolean flag = false;
            Iterator jt= bottomList.iterator();
            while(jt.hasNext()) {
                Map<String, Object> jtem = (Map<String, Object>) jt.next();
                if (item.get(id).toString().equals(jtem.get(pid).toString())) {
                    List<Map<String, Object>> childList = (List<Map<String, Object>>) item.get(listName);
                    childList.add(jtem);
                    item.put(listName, childList);
                    flag = true;
                    jt.remove();
                }
            }
            if (flag) {
                boolean remove = true;
                for (Map<String, Object> ktem : otherList) {
                    if (ktem.get(pid).toString().equals(item.get(id).toString())) {
                        remove = false;
                        break;
                    }
                }
                if (remove) {
                    it.remove();
                    result.add(item);
                }
            }
        }
        result.addAll(bottomList);
        if (CommonUtil.isNotEmpty(otherList))
            return listToTreeFor(result, otherList, id, pid, listName);
        else
            return result;
    }

    //获取request
    public static HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
