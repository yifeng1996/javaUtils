package com.weng.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weng.system.common.httpexecutor.HttpExecutors;
import com.weng.system.common.httpexecutor.RetryException;
import com.weng.system.common.httpexecutor.Retryer;
import com.weng.system.entity.WeixinCrawl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Description : java类作用描述
 * @ Author : yifeng
 * @ Date : 2019/10/15 14:33
 * @ Version : 1.0
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaUtilsApplication.class)
public class HttpTest {

    @Autowired
    private HttpExecutors httpClient;

    /**
     * 文广原创互动数据采集提醒
     *
     * @param list
     * @return
     */
    public boolean getOriginalDisseminationCrawResult(List<WeixinCrawl> list) {
        Map<String, Object> map = new HashMap<>();
        String token = "4Twjbb7DemHaG6Gy9qGf3GtEhA0sOShGeOjCXd82VJ_AFFDcGhghdM8FiGL_2WrTvueCqP59pQh_mXFjn-be2w";
        map.put("urls", list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("TRSDH-Access-Token", token);
        String jsonString = JSONObject.toJSONString(map);
        HttpEntity<String> requestObject = new HttpEntity<>(jsonString, headers);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = httpClient.executor(
                    (RestTemplate r) -> r.postForEntity("http://119.254.65.208:8075/datas/api" + "/base/weixincrawl/interact", requestObject,
                            String.class),
                    (RetryException e) -> e.getException() instanceof NullPointerException, new Retryer.RetryMeta(1, 1000));
            log.info("【文广原创互动数据采集提醒】：查询参数：{}", jsonString);
            if (responseEntity == null || StringUtils.isBlank(responseEntity.getBody())) {
                log.error("【文广原创互动数据采集提醒】失败：查询参数：{}, 返回结果：{}", jsonString, responseEntity);
                return false;
            }
            log.info("【文广原创互动数据采集提醒】：返回结果：{}", responseEntity.getBody());
        } catch (Exception e) {
            log.error("【文广原创互动数据采集提醒】失败，查询参数：{}，异常：{}", jsonString, e);
            return false;
        }
        return true;
    }

    @Test
    public void test() throws Exception{
        String urls = "[{\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA3MTU5MTgzMg==&mid=2654113218&idx=3&sn=ce773a9258c2de843bafd124a8043c00\",\n" +
                "        \"uid\": \"lsscjrlhh\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA4NjcyNjQ2MA==&mid=2650581044&idx=4&sn=e91cec57dd96bc3391487219487331ca\",\n" +
                "        \"uid\": \"lsjingxw\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA5OTIwMjE1Mw==&mid=2651099959&idx=3&sn=8b63e5a8c028f4c0182aae914fc92396\",\n" +
                "        \"uid\": \"lsxczxtyxx\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MjM5MzY3MDU0Nw==&mid=2651301983&idx=1&sn=0e0663855c4d61f2bacc0bfa5b7090cd\",\n" +
                "        \"uid\": \"lsda2090546\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzI4NjA0NjU1NQ==&mid=2649712780&idx=3&sn=7a22cd8eac7992c997c67cdf3883fba7\",\n" +
                "        \"uid\": \"pinganlishui\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwOTE1NzM5OQ==&mid=2655250514&idx=2&sn=6f7bb345c69347d1755382426ea51c47\",\n" +
                "        \"uid\": \"gh_c8e65ad1b00b\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwNjAwMzY0OQ==&mid=2656410211&idx=2&sn=d364a29aec941842f83f27afc83fd9d9\",\n" +
                "        \"uid\": \"zjyjgl666\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA3MTAxNjkxNA==&mid=2652799734&idx=4&sn=9a3af356bcad583b8751dc2073d93b05\",\n" +
                "        \"uid\": \"zjzxwx\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzUyNjA5NjExNw==&mid=2247522712&idx=3&sn=f31e64f020fc82b11dfc93a34ba43beb\",\n" +
                "        \"uid\": \"gh_c77b1694c81c\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg4MzY0MTkwMw==&mid=2247485118&idx=1&sn=cb0e53c356ec822f5ec97208de389d0a\",\n" +
                "        \"uid\": \"gh_13abb33f3573\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzIyNzAzMzk2Ng==&mid=2650915220&idx=1&sn=3fc520c13111745d1aa67fe5a0bc448e\",\n" +
                "        \"uid\": \"lsajj0578\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzIxNjgxOTYwMw==&mid=2247494936&idx=1&sn=dd2707d11e8b6f5cbb96c2bdbd9e2399\",\n" +
                "        \"uid\": \"wangxintaizhou\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg5NzYzODI5Ng==&mid=2247506570&idx=1&sn=9c752316bd6985246d0bda30ad6e8976\",\n" +
                "        \"uid\": \"migucitic\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwODM0NzY2OQ==&mid=2651046424&idx=1&sn=14de8a1204a2ef6456d3f94fead271ba\",\n" +
                "        \"uid\": \"gh_39bd426c1381\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzkxNzI4NTkwNA==&mid=2247517661&idx=3&sn=aa2ce2622e0d204892ce73e6d97fadc8\",\n" +
                "        \"uid\": \"lssgtzyj\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA4ODQyMTAwMw==&mid=2649663761&idx=4&sn=6d6e3750ca26a76a2df7ffd78ea6a002\",\n" +
                "        \"uid\": \"scxzfgfwx\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzU4MDg4NzM2OQ==&mid=2247492477&idx=1&sn=13418b3089f32dbafa6b0f030600f566\",\n" +
                "        \"uid\": \"zjlsdsj\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg2Mzc2NDQ4OQ==&mid=2247485700&idx=2&sn=0875cadbc993b2a9296fffd2a774e3aa\",\n" +
                "        \"uid\": \"gh_bdd454b37f78\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg2MTY1MzAxMA==&mid=2247532126&idx=2&sn=878119cd8087ab4b85188675b0ab3b3c\",\n" +
                "        \"uid\": \"lsqx58646\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA3MTU5MTgzMg==&mid=2654113218&idx=3&sn=ce773a9258c2de843bafd124a8043c00\",\n" +
                "        \"uid\": \"lsscjrlhh\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzU4NTQ3MDUyNA==&mid=2247538619&idx=2&sn=c97c9253c8a690bec248866d1a68a575\",\n" +
                "        \"uid\": \"lsszjrmfy\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MjM5MzY3MDU0Nw==&mid=2651301983&idx=1&sn=0e0663855c4d61f2bacc0bfa5b7090cd\",\n" +
                "        \"uid\": \"lsda2090546\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzI4NjA0NjU1NQ==&mid=2649712780&idx=3&sn=7a22cd8eac7992c997c67cdf3883fba7\",\n" +
                "        \"uid\": \"pinganlishui\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MjM5OTk5MDA5NA==&mid=2656809082&idx=2&sn=2926cb084e05c8b35c891650766d6374\",\n" +
                "        \"uid\": \"chinabluenews\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwNjAwMzY0OQ==&mid=2656410211&idx=2&sn=d364a29aec941842f83f27afc83fd9d9\",\n" +
                "        \"uid\": \"zjyjgl666\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzIxNjgxOTYwMw==&mid=2247494936&idx=1&sn=dd2707d11e8b6f5cbb96c2bdbd9e2399\",\n" +
                "        \"uid\": \"wangxintaizhou\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwODM0NzY2OQ==&mid=2651046424&idx=1&sn=14de8a1204a2ef6456d3f94fead271ba\",\n" +
                "        \"uid\": \"gh_39bd426c1381\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwNTI0OTYyOA==&mid=2650946087&idx=2&sn=8c1ed48da411a711fa27e5a6e41cfb90\",\n" +
                "        \"uid\": \"zhejianggh\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg2Mzc2NDQ4OQ==&mid=2247485700&idx=2&sn=0875cadbc993b2a9296fffd2a774e3aa\",\n" +
                "        \"uid\": \"gh_bdd454b37f78\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg2MTY1MzAxMA==&mid=2247532126&idx=2&sn=878119cd8087ab4b85188675b0ab3b3c\",\n" +
                "        \"uid\": \"lsqx58646\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA3MTU5MTgzMg==&mid=2654113218&idx=3&sn=ce773a9258c2de843bafd124a8043c00\",\n" +
                "        \"uid\": \"lsscjrlhh\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MjM5NDExMzgwNw==&mid=2651735134&idx=2&sn=6c789b4455bc729cd53da3140d4c5806\",\n" +
                "        \"uid\": \"xihunet\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzI1NDQxNjAxOQ==&mid=2247546688&idx=1&sn=a6285ab6f5ca07e45ad6d5a4f16453ca\",\n" +
                "        \"uid\": \"yhxwsjkj\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA5OTIwMjE1Mw==&mid=2651099959&idx=3&sn=8b63e5a8c028f4c0182aae914fc92396\",\n" +
                "        \"uid\": \"lsxczxtyxx\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MjM5MzY3MDU0Nw==&mid=2651301983&idx=1&sn=0e0663855c4d61f2bacc0bfa5b7090cd\",\n" +
                "        \"uid\": \"lsda2090546\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzI4NjA0NjU1NQ==&mid=2649712780&idx=3&sn=7a22cd8eac7992c997c67cdf3883fba7\",\n" +
                "        \"uid\": \"pinganlishui\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzU4OTUwNTAxMg==&mid=2247512309&idx=3&sn=3802bc2adf8c6dd59741286cd3732702\",\n" +
                "        \"uid\": \"lsctgr\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwOTE1NzM5OQ==&mid=2655250514&idx=2&sn=6f7bb345c69347d1755382426ea51c47\",\n" +
                "        \"uid\": \"gh_c8e65ad1b00b\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzI3NTcwNTg5Mw==&mid=2247570463&idx=3&sn=a44e8465bdfc22def690ec50a6cce782\",\n" +
                "        \"uid\": \"zltxsjlsr\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MjM5MzQwMDI4Mg==&mid=2651755670&idx=3&sn=aaa561fc5bded397793a3d15022b7f5c\",\n" +
                "        \"uid\": \"minsheng66\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MjM5OTk5MDA5NA==&mid=2656809082&idx=2&sn=2926cb084e05c8b35c891650766d6374\",\n" +
                "        \"uid\": \"chinabluenews\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAxODgwMDkyNQ==&mid=2649743388&idx=1&sn=5169562bd19d841f659b5783f8b59d22\",\n" +
                "        \"uid\": \"wangxinzhejiang\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwNjAwMzY0OQ==&mid=2656410211&idx=2&sn=d364a29aec941842f83f27afc83fd9d9\",\n" +
                "        \"uid\": \"zjyjgl666\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA3MTAxNjkxNA==&mid=2652799734&idx=4&sn=9a3af356bcad583b8751dc2073d93b05\",\n" +
                "        \"uid\": \"zjzxwx\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzUyNjA5NjExNw==&mid=2247522712&idx=3&sn=f31e64f020fc82b11dfc93a34ba43beb\",\n" +
                "        \"uid\": \"gh_c77b1694c81c\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg4MzY0MTkwMw==&mid=2247485118&idx=1&sn=cb0e53c356ec822f5ec97208de389d0a\",\n" +
                "        \"uid\": \"gh_13abb33f3573\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzIyNzAzMzk2Ng==&mid=2650915220&idx=1&sn=3fc520c13111745d1aa67fe5a0bc448e\",\n" +
                "        \"uid\": \"lsajj0578\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzU2NzY2NTUxMg==&mid=2247496260&idx=2&sn=7e0273c98c2ac4bea8ad89593efe4d87\",\n" +
                "        \"uid\": \"miguwenxue\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzIxNjgxOTYwMw==&mid=2247494936&idx=1&sn=dd2707d11e8b6f5cbb96c2bdbd9e2399\",\n" +
                "        \"uid\": \"wangxintaizhou\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg5NzYzODI5Ng==&mid=2247506570&idx=1&sn=9c752316bd6985246d0bda30ad6e8976\",\n" +
                "        \"uid\": \"migucitic\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg3MzcyNDQwOQ==&mid=2247484692&idx=1&sn=b2aaf61ea5accec86e9511b9245a455a\",\n" +
                "        \"uid\": \"shantan365\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzIwMjA0ODA3NA==&mid=2648934677&idx=1&sn=484f03e208c534898b367cc14103a240\",\n" +
                "        \"uid\": \"cmreadfans\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzA4MzU3MTAyOA==&mid=2650293650&idx=5&sn=910146c9755f017dcdbddb3db9015665\",\n" +
                "        \"uid\": \"zjlsszgh\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAwODM0NzY2OQ==&mid=2651046424&idx=1&sn=14de8a1204a2ef6456d3f94fead271ba\",\n" +
                "        \"uid\": \"gh_39bd426c1381\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzUyMDQ2OTE1Mg==&mid=2247518658&idx=6&sn=cccc179e11b053ab291e71cce71903a2\",\n" +
                "        \"uid\": \"gh_9ee2d2732330\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzU4MDg4NzM2OQ==&mid=2247492477&idx=1&sn=13418b3089f32dbafa6b0f030600f566\",\n" +
                "        \"uid\": \"zjlsdsj\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg2Mzc2NDQ4OQ==&mid=2247485700&idx=2&sn=0875cadbc993b2a9296fffd2a774e3aa\",\n" +
                "        \"uid\": \"gh_bdd454b37f78\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzAxNDAxNTA0Mg==&mid=2652005406&idx=2&sn=a55036a8a8127b663efff716a0d89ca2\",\n" +
                "        \"uid\": \"lishuijiaoyu\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=MzIzMzg0ODA5NA==&mid=2247522758&idx=1&sn=ee2797d1504f8af98e79c451eca2d219\",\n" +
                "        \"uid\": \"gh_52d454006e7a\"\n" +
                "    }, {\n" +
                "        \"url\": \"https://mp.weixin.qq.com/s?__biz=Mzg2MTY1MzAxMA==&mid=2247532126&idx=2&sn=878119cd8087ab4b85188675b0ab3b3c\",\n" +
                "        \"uid\": \"lsqx58646\"\n" +
                "    }\n" +
                "]";
        List<WeixinCrawl> mapList = JSON.parseArray(urls, WeixinCrawl.class);
        boolean originalDisseminationCrawResult = getOriginalDisseminationCrawResult(mapList);
        System.out.println(originalDisseminationCrawResult);
    }
}
