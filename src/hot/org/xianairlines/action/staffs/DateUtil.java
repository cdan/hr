package org.xianairlines.action.staffs;

/**
 * Created by IntelliJ IDEA.
 * User: cdan
 * Date: 11-2-12
 * Time: 下午12:44
 * To change this template use File | Settings | File Templates.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

        public DateUtil() {

        }
        public java.util.Date parse(String dateString, String format) {
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(true);
        java.util.Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return  null;
        }
//      java.sql.Date dateTime = new java.sql.Date(date.getTime());// sql类型
        return date;
    }
}
