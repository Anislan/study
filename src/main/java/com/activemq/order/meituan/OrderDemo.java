package com.activemq.order.meituan;

import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderQueryByIdRequest;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 *  美团外卖对接Demo
 */
public class OrderDemo {

    public static void main(String[] args) {
        // 声明公共参数,signkey,appAuthToken
        RequestSysParams requestSysParams = new RequestSysParams("cfukkshw8r6gulkc","myAppAuthToken","utf-8");
        // 要查询接口URL对象
        CipCaterTakeoutOrderQueryByIdRequest request = new CipCaterTakeoutOrderQueryByIdRequest();
        // 设置公共参数
        request.setRequestSysParams(requestSysParams);
        // 设置业务参数，不同的业务接口,参数不同
        request.setOrderId(1L);
        String resultJson ="";
        try {
            resultJson=request.doRequest();
            System.out.println(resultJson);
        }catch (IOException e){

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
