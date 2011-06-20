package org.xianairlines.action.staffs;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cdan
 * Date: 11-6-20
 * Time: 下午7:41
 * To change this template use File | Settings | File Templates.
 */
public class AgeCounter {
        public int getAge(Date birth) {
            return Calendar.getInstance().get(Calendar.YEAR) - birth.getYear() - 1900;
        }

}
