package com.weng.system.test;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.weng.system.entity.DocumentLog;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ Description : java类作用描述
 * @ Author : yifeng
 * @ Date : 2019/10/15 14:33
 * @ Version : 1.0
 */
public class StringTest {
    public static void main(String[] args) throws Exception {
        /*String fileNormativeNum = "EveryOne#根组织/12459#温州日报报业集团/12531#TRS技术支持";
        String substring = fileNormativeNum.substring(fileNormativeNum.lastIndexOf("/")+1,fileNormativeNum.lastIndexOf("#"));
        System.out.println(substring);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        System.out.println(stringBuffer);
        DocumentLog documentLog = (DocumentLog) Class.forName("com.weng.system.entity.DocumentLog").newInstance();
        DocumentLog documentLog1 = (DocumentLog) DocumentLog.class.getConstructors()[0].newInstance();*/
        /*String s = "0012";
        Integer integer = Integer.valueOf(fileNormativeNum);
        s = String.format("%04d",integer++);*/
//        System.out.println(substring);

        /*String dispoString = "testtest浙政〔2019〕无页面标签“中央厨房”去掉答复测试：（可以实现，前端）测试附件图标按老的设计，还有图标太小Commentby汶军辉:请王樊协调把老的图标截图给我们，设计师参照设计后提供给前端答复：（可以实现，美工、前端）样式，上面黑的用户觉得难看，用户说看看原来怎么画的Commentby汶军辉:颜色按照老系统的修正掉";
        int index = dispoString.indexOf("测试");
        int startIndex = index-50;
        int endIndex = index+100;
        int strLen = dispoString.length();
        if(startIndex<0){
            startIndex = 0;
        }
        if(endIndex>strLen){
            endIndex = strLen;
        }
        String sLocalName = dispoString.substring(startIndex, endIndex);
        System.out.println(sLocalName);*/
        Boolean[] flag = new Boolean[]{false};
        StringBuilder content = new StringBuilder();
        content.append("\\n\\nJust a moment...\\nbody {\\n  background-color: #fff;\\n  -webkit-font-smoothing: antialiased;\\n  font-family: BlinkMacSystemFont, Ping Fang SC, Segoe UI, Roboto, Oxygen,\\n    Ubuntu, Cantarell, Fira Sans, Droid Sans, Helvetica Neue, sans-serif;\\n}\\n.sl-recap-content {\\n  color: #333;\\n  flex-grow: 1;\\n  display: flex;\\n  justify-content: flex-start;\\n  flex-direction: column;\\n}\\n.sl-recap-container {\\n  top: 50%;\\n  left: 50%;\\n  position: absolute;\\n  z-index: 2;\\n  transform: translate(-50%, -50%);\\n}\\n\\n.sl-recap-host {\\n  font-size: 18px;\\n  text-align: center;\\n  height: 20%;\\n  flex-grow: 0;\\n  flex-shrink: 0;\\n  display: flex;\\n  justify-content: center;\\n  align-items: flex-end;\\n  top: \\\"0px\\\";\\n  text-align: center;\\n}\\n\\n.sl-recap-text {\\n  font-size: 18px;\\n  display: flex;\\n  align-items: center;\\n  justify-content: center;\\n}\\n.sl-recap-spin_logo {\\n  width: 126px;\\n  height: 126px;\\n  position: absolute;\\n  top: 50%;\\n  left: 50%;\\n  transform: translate(-50%, -50%);\\n}\\n.sl-recap-spin_logo--error {\\n  height: 100px;\\n  display: block;\\n  margin: 0 auto 53px;\\n  padding-top: 20px;\\n}\\n.sl-recap-spin_logo--success {\\n  width: 126px;\\n}\\n.sl-recap-process {\\n  width: 200px;\\n  height: 200px;\\n  /* margin-right: 16px; */\\n  display: inline-block;\\n  color: #0fc6c2;\\n  z-index: 2;\\n  animation: 1.8s linear 0s infinite normal none running\\n    animation-sl-recap-process;\\n}\\n\\n@keyframes animation-sl-recap-process {\\n  0% {\\n    transform: rotate(0deg);\\n  }\\n  100% {\\n    transform: rotate(360deg);\\n  }\\n}\\n\\n.sl-recap-circle {\\n  color: #27B876;\\n  stroke: currentcolor;\\n  /* stroke-dasharray: 20px, 60px; */\\n  stroke-dashoffset: 0;\\n  animation: 1.8s linear 0s infinite normal none running\\n    animation-sl-recap-circle;\\n}\\n\\n.sl-recap-circle-grey {\\n  color: rgba(242, 243, 245, 1);\\n  stroke: currentcolor;\\n  /* stroke-dasharray: 80px, 200px; */\\n  stroke-dashoffset: 0;\\n}\\n\\n@keyframes animation-sl-recap-circle {\\n  0% {\\n    stroke-dasharray: 1, 200;\\n    stroke-dashoffset: 0;\\n  }\\n  50% {\\n    stroke-dasharray: 89, 200;\\n    stroke-dashoffset: -35;\\n  }\\n  100% {\\n    stroke-dasharray: 89, 200;\\n    stroke-dashoffset: -124;\\n  }\\n}\\n\\n.sl-recap-image-panel {\\n  display: flex;\\n  flex-direction: column;\\n  justify-content: center;\\n  width: 300px;\\n  margin: auto;\\n  padding: 8px;\\n  /* default display is none */\\n  display: none;\\n  position: relative;\\n}\\n\\n.sl-recap-image-spin {\\n  position: absolute;\\n  left: 0;\\n  top: 0;\\n  right: 0;\\n  bottom: 0;\\n  background-color: rgba(247, 248, 250, 0.75);\\n  border-radius: 8px;\\n  display: flex;\\n  justify-content: center;\\n  align-items: center;\\n  display: none;\\n}\\n\\n.sl-recap-error {\\n  color: #ff4d4f;\\n  text-align: center;\\n  position: relative;\\n  top: 18px;\\n}\\n\\n.sl-recap-success {\\n  color: #0fc6c2;\\n  display: none;\\n  text-align: center;\\n  top: 50%;\\n  padding-top: 26px;\\n  position: relative;\\n  margin-top: -1px;\\n}\\n\\n.sl-recap-footer {\\n  background-color: #f7f8fa;\\n  height: 20px;\\n  flex-grow: 0;\\n  padding-bottom: 20px;\\n  width: 100%;\\n  text-align: center;\\n  font-size: 14px;\\n}\\n.sl-recap-link {\\n  margin: 0 4px;\\n  text-decoration: none;\\n  color: #0fc6c2;\\n}\\n\\n.sl-recap-image-container {\\n  display: flex;\\n  flex-direction: column;\\n  justify-content: center;\\n  align-items: center;\\n}\\n\\n.sl-recap-image-title {\\n  text-align: center;\\n}\\n\\n/* image1 number image */\\n\\n.id-recap-image1 {\\n  display: block;\\n  margin: 0 auto 16px;\\n  cursor: pointer;\\n}\\n\\n.sl-recap-image1-form {\\n  display: flex;\\n}\\n\\n.sl-recap-image1-form-input {\\n  padding: 8px 16px;\\n  font-size: 16px;\\n  outline: 0;\\n  letter-spacing: 4px;\\n  border: 0;\\n  width: 60%;\\n  flex-grow: 1;\\n  border: 1px solid #e3e8ef;\\n  border-right: 0;\\n  border-radius: 4px 0 0 4px;\\n  overflow: hidden;\\n}\\n\\n.sl-recap-image1-form-submit {\\n  background-color: #0fc6c2;\\n  border: 0;\\n  color: white;\\n  font-size: 14px;\\n  width: 88px;\\n  cursor: pointer;\\n  text-align: center;\\n  border-radius: 0 4px 4px 0;\\n}\\n\\n.sl-recap-image1-form-submit:hover {\\n  background-color: #0eb6c0;\\n}\\n\\n/* image2 rotate image */\\n\\n.sl-recap-image2 {\\n  width: 200px;\\n  height: 200px;\\n  position: relative;\\n}\\n\\n.sl-recap-image2-back {\\n  position: absolute;\\n  top: 0;\\n  left: 0;\\n  width: 100%;\\n}\\n.sl-recap-image2-cover {\\n  position: absolute;\\n  top: 0;\\n  left: 0;\\n  width: 100%;\\n  z-index: 1;\\n}\\n\\n.sl-recap-image2-help {\\n  text-align: center;\\n  margin: 28px 0 14px;\\n  color: black;\\n  font-size: 20px;\\n}\\n\\n.sl-recap-image2-bar {\\n  background-color: #e2f0f1;\\n  transition: all 300ms;\\n  border-radius: 24px;\\n  height: 48px;\\n  width: 228px;\\n  position: relative;\\n  margin-top: 10px;\\n}\\n\\n.sl-recap-image2-cursor {\\n  left: 0;\\n  top: 0;\\n  position: absolute;\\n  z-index: 10;\\n  width: 50px;\\n  height: 48px;\\n  display: flex;\\n  justify-content: center;\\n  align-items: center;\\n  border-radius: 50%;\\n}\\n\\n.sl-recap-image2-cursor svg {\\n  position: absolute;\\n  top: -3px;\\n  left: -10px;\\n}\\n.sl-recap-image2-cursor svg {\\n  position: absolute;\\n  top: -3px;\\n  left: -10px;\\n}\\n#sl-recap-refresh-bar svg {\\n  width: 14px;\\n  height: 14px;\\n  color: rgba(0, 0, 0, 0.7);\\n  margin-right: 4px;\\n}\\n#sl-recap-refresh-bar {\\n  margin-top: 14px;\\n  color: rgba(0, 0, 0, 0.7);\\n  font-size: 14px;\\n  display: flex;\\n  align-items: center;\\n}\\n\\n  \\n    var name = \\\"captcha\\\";\\n    if (!window[name]) {\\n      alert(\\\"No config, please do not request directly\\\");\\n      window.close();\\n    }\\n    function run() {\\n      $Recap.create(window[name]).run();\\n    }\\n    const script = document.createElement(\\\"script\\\");\\n    script.setAttribute(\\\"type\\\", \\\"text/javascript\\\");\\n    script.setAttribute(\\\"charset\\\", \\\"utf-8\\\");\\n    script.setAttribute(\\\"src\\\", window[name].entrypoint + \\\"/sdk.js\\\");\\n    script.setAttribute(\\\"onload\\\", \\\"run()\\\");\\n    document.head.appendChild(script);\\n  \\n\\n\\n \\n  \\n    \\n      \\n        \\n\\n  \\n    \\n    \\n    \\n  \\n\\n  \\n        \\n      \\n    \\n    Verifying\\n  \\n  \\n    \\n      \\n\\n  \\n    \\n    \\n    \\n  \\n\\n  \\n    \\n    \\n  \\n  \\n    \\n    Dev tools is opening, refresh and try again\\n  \\n  \\n    \\n      \\n      \\n    Verify Passed\\n  \\n\\n\\n\\n  Security powered by\\n  \\n    SafeLine WAF\\n  \\n");
//        String itemValues = "暂无查看权限;该微博不存在;该内容已被发布者删除;The content has been deleted by the author;The contenthasbeendeleted by the author;头条君找不到你想要的页面;您没有权限访问该板块;出错了！文章没有找到哦~;抱歉，你访问的内容不存在;您没有权限访问该板块;Not Found;你似乎来到了没有知识存在的荒原;您无权访问;您访问的页面丢失了;文章找不到了;您访问的帖子被隐藏;该帖子被楼主藏起来了;帖子已过期;帖子不存在;没有找到相关信息;该贴已被删除;您访问的贴子被隐藏;指定的主题不存在;你要观看的视频不存在;您查看的页面找不到了;啊叻？视频不见了？;Unable to render embedded object;你访问的页面不见了;抱歉，你访问的内容不存在";
        String itemValues = "头条君找不到你想要的页面;您没有权限访问该板块;Just a moment...\\nbody";
        Stream.of(content.toString()).forEach(word -> {
            Arrays.stream(StringUtils.split(itemValues, StringPool.SEMICOLON)).forEach(text -> {
                if (word.contains(text)) {
                    flag[0] = true;
                }
            });
        });
        System.out.println(flag[0]);
    }
}
