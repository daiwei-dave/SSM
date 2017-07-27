package org.excel.annotations.test;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.excel.annotations.ImportExcel;
import org.excel.annotations.util.PhoneValidationUtils;
import org.seckill.entity.CouponUser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 暂时还无法使用
 * Created by daiwei on 2017/7/27.
 */
public class Test {
    /**
     * excel导入
     * @param path
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static List<CouponUser> read(String path) throws IOException, InvalidFormatException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    //    path=new String(path.getBytes("gbk"),"utf-8");
        List<CouponUser> couponUserList = new ArrayList<CouponUser>();
        InputStream fileInputStream = new FileInputStream(path);
        ImportExcel<CouponUser> couponUserImportExcel = new ImportExcel<CouponUser>(path, fileInputStream, 0, 0, CouponUser.class);
        couponUserList = couponUserImportExcel.getDataList(CouponUser.class);
        return couponUserList;
    }

    /**
     * Excel导入测试
     * @param args
     */
    public static void main(String[] args) {
        try {
     //            List<CouponUser> couponUserList = Test.read("d:/导入模板新.xlsx");
           List<CouponUser> couponUserList = Test.read("d:/model.xlsx");
            for (int i = 0; i < couponUserList.size(); i++) {
                couponUserList.get(i).setPhone(PhoneValidationUtils.convertPhone(couponUserList.get(i).getPhone()));
                System.out.println(couponUserList.get(i).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        List<CouponUser> couponUserList = null;
//        try {
//            couponUserList = Test.read("d:/poi_test.xlsx");
//            new ExportExcel(null, CouponUser.class).setDataList(couponUserList).write(response, Consts.COUPON_USER_FILE_NAME + ".xlsx").dispose();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

}
