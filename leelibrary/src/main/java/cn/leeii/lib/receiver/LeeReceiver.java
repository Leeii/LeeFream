package cn.leeii.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 广播 Created by Leeii on 2015/4/21.
 */
public class LeeReceiver extends BroadcastReceiver {
    
    /** 接收广播结果接口 */
    private ReceiveResult mReceiveBoard;
    
    /**
     * 广播构造器
     * 
     * @param receiveBoard
     *            接收广播结果接口
     */
    public LeeReceiver(ReceiveResult receiveBoard) {
        mReceiveBoard = receiveBoard;
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        mReceiveBoard.onReceive(intent);
    }
}
