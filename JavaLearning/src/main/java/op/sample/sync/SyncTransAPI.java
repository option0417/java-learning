/*
 * 版權宣告: FDC all rights reserved.
 */
package op.sample.sync;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：SyncTransAPI.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author 1010481
 *@version 1.0
 *@since 1.0
 */
public interface SyncTransAPI<ENTITY> {
    public String transEntityToString(ENTITY entity);
    public ENTITY transStringToEntity(String entityStr);
}
