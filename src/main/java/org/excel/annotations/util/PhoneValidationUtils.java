package org.excel.annotations.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by daiwei on 2017/2/22.
 */
public class PhoneValidationUtils {
    /**
     * 手机号验证
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        //^开始标志，$结束标志，[1]匹配第一位中的数字1，[3,4,5,7,8]匹配【】中的任意数，[0-9]{9}匹配0到9的9位数字
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 电话号码验证
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isPhone(final String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        //[0-9]{5,10}匹配0到9中的前5到10个
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 将excel中科学记数法的电话号码进行转换
     * @param str
     * @return
     */
    public static String  convertPhone(String str) {
        if (!StringUtils.isBlank(str)){
            DecimalFormat df = new DecimalFormat("#");
            Double phone = Double.parseDouble(str);
            return df.format(phone);
        }else {
            return "";
        }
    }
    public static void main(String[] args) {

        System.out.println(PhoneValidationUtils.convertPhone("1.8428385811e10"));
//        String phone = "13900442200";
//        String phone2 = "021-88889999";
//        String phone3 = "88889999";
//        String phone4 = "1842838583";
//        //测试1
//        if(isPhone(phone) || isMobile(phone)){
//            System.out.println("1这是符合的");
//        }
//        //测试2
//        if(isPhone(phone2) || isMobile(phone2)){
//            System.out.println("2这是符合的");
//        }
//        //测试3
//        if(isPhone(phone3) || isMobile(phone3)){
//            System.out.println("3这是符合的");
//        }
//        //测试4
//        if(isPhone(phone4) || isMobile(phone4)){
//            System.out.println("4这是符合的");
//        }else{
//            System.out.println("不符合");
//        }
    }
}
