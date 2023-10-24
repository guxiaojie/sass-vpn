package com.n.number;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.ecs.model.v20140526.*;




@RestController
public class Rest {


    @GetMapping("/")
    public String hi() {
        return "Hi";
    }


    @GetMapping("/create")
    public String hia() {
        // 请确保代码运行环境设置了环境变量ALIBABA_CLOUD_ACCESS_KEY_ID和ALIBABA_CLOUD_ACCESS_KEY_SECRET。
        // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例使用环境变量获取AccessKey的方式进行调用，建议使用更安全的STS方式。

        System.out.println(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"));

        DefaultProfile profile = DefaultProfile.getProfile("cn-hongkong", System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        IAcsClient client = new DefaultAcsClient(profile);

        RunInstancesRequest request = new RunInstancesRequest();
        request.setRegionId("cn-hongkong");
        request.setImageId("aliyun_2_1903_x64_20G_alibase_20230731.vhd");
        request.setInstanceType("ecs.t5-lc2m1.nano");
        request.setSecurityGroupId("sg-j6c10s1d2x6v9gjmg4q6");
        request.setVSwitchId("vsw-j6cfntfylksdiuj0tegbo");
        request.setInstanceName("ecs_sdk_demo");
        request.setInternetMaxBandwidthOut(1);
        request.setPassword("GujieXxx1122@");
        request.setPeriod(1);
        request.setPeriodUnit("Month");
//        实例需要修改的目标计费方式。取值范围：
//        PrePaid：将按量付费实例转换为包年包月实例。
//        PostPaid：将包年包月实例转换为按量付费实例。
//        默认值：PrePaid。
        request.setInstanceChargeType("PrePaid");

//改了一次密码才成功 ssh root@8.210.125.21 Gujie2593
        try {
            RunInstancesResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            return new Gson().toJson(response);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        return "Error";
    }
}

/*
sudo wget -O /etc/yum.repos.d/docker-ce.repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
sudo yum install yum-plugin-releasever-adapter --disablerepo=* --enablerepo=plus
sudo yum -y install docker-ce

sudo yum update
sudo yum search docker
sudo yum info docker
sudo yum install docker
sudo systemctl enable docker.service
sudo systemctl start docker.service
sudo bash -c "$(wget -qO- https://raw.githubusercontent.com/Jigsaw-Code/outline-server/master/src/server_manager/install_scripts/install_server.sh)"

 */