/*
 * 版權宣告: FDC all rights reserved.
 */
package op.sample.batch;

import java.util.Date;

import org.springframework.stereotype.Repository;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：JobDetail.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author 1010481
 *@version 1.0
 *@since 1.0
 */
@Repository
public class JobDetail {
    public String getDetail() {
        return "Data from " + new Date().toString();
    }
}
