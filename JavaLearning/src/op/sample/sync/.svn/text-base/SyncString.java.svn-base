/*
 * 版權宣告: FDC all rights reserved.
 */
package op.sample.sync;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：SyncString.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author 1010481
 *@version 1.0
 *@since 1.0
 */
public class SyncString extends SyncObject<IObject, String> {

    /* (non-Javadoc)
     * @see op.sample.sync.SyncObject#transEntityToString(java.lang.Object)
     */
    @Override
    public String transEntityToString(IObject entity) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(String.valueOf(entity.getIntID()));
        strBuf.append(entity.getObjID().toString());
        strBuf.append(entity.getStrID());
        return strBuf.toString();
    }

    /* (non-Javadoc)
     * @see op.sample.sync.SyncObject#transStringToEntity(java.lang.String)
     */
    @Override
    public IObject transStringToEntity(String entityStr) {
        IObject iObject = new IObject();
        iObject.setIntID(Integer.parseInt(entityStr));
        iObject.setObjID(new Object());
        iObject.setStrID(entityStr);        
        return iObject;
    }    
}
