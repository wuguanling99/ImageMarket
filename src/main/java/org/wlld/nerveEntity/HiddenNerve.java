package org.wlld.nerveEntity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wlld.function.ActiveFunction;

/**
 * @author lidapeng
 * 隐层神经元
 * @date 9:30 上午 2019/12/21
 */
public class HiddenNerve extends Nerve {
    private int depth;//所处深度
    static final Logger logger = LogManager.getLogger(HiddenNerve.class);

    public HiddenNerve(int id, int depth, int upNub) {//隐层神经元
        super(id, upNub, "HiddenNerve");
        this.depth = depth;
    }

    @Override
    public void input(long eventId, double parameter) throws Exception {//接收上一层的输入
        logger.debug("name:{},myId:{},depth:{},eventId:{},parameter:{}--getInput", name, getId(), depth, eventId, parameter);
        boolean allReady = insertParameter(eventId, parameter);
        if (allReady) {//参数齐了，开始计算 sigma - threshold
            logger.debug("depth:{},myID:{}--startCalculation", depth, getId());
            double sigma = calculation(eventId);
            double out = activeFunction.sigmoid(sigma);
            logger.debug("depth:{},myID:{},outPut:{}", depth, getId(), out);
            sendMessage(eventId, out);
        }
        // sendMessage();
    }

}
