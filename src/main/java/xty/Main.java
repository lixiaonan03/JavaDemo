package xty;

import xty.response.RegionData;
import xty.request.SlowTableFactory;
import xty.response.SlowTable;

import java.util.Calendar;
import java.util.Date;

public class Main {


    private static void byTotalCount() throws Exception {
        // 1. 根据 SlowTable，获取总的请求数和错误数
        SlowTable[] slowTables = NetworkUtils.querySlowTable(
                SlowTableFactory.total()
        );

        int networkErrorCount = 0;
        int requestCount = 0;

        for (SlowTable st : slowTables) {
            if ("mapi.wanwustore.cn".equals(st.hostName)) {
                networkErrorCount = st.networkErrorCount;
                requestCount = st.requestCount;
                break;
            }
        }
//        System.out.printf("总的网络请求数 %d\n", requestCount);
//        System.out.printf("错误网络请求数 %d\n", networkErrorCount);


        // 2. 获取埋点请求数和错误数 /uicount/
        slowTables = NetworkUtils.querySlowTable(
                SlowTableFactory.mapi()
        );

        int countlyRequestCount = 0;
        int countlyNetworkErrorCount = 0;

        for (SlowTable st : slowTables) {
            if ("/o/ub/*/uicount/count/i".equals(st.uriName)) {
                countlyRequestCount += st.requestCount;
                countlyNetworkErrorCount += st.networkErrorCount;

//                System.out.printf("/ub/countly 请求数 %d\n", st.requestCount);
//                System.out.printf("/ub/countly 错误请求数 %d\n", st.networkErrorCount);
            }
            else if ("/o/uc/*/uicount/count/i".equals(st.uriName)) {
                countlyRequestCount += st.requestCount;
                countlyNetworkErrorCount += st.networkErrorCount;

//                System.out.printf("/uc/countly 请求数 %d\n", st.requestCount);
//                System.out.printf("/uc/countly 错误请求数 %d\n", st.networkErrorCount);
            }
        }

        // 3. 计算之
        requestCount -= countlyRequestCount;
        networkErrorCount -= countlyNetworkErrorCount;

        // 不用管可能的除 0 异常
        System.out.printf("A: 所有网络错误\n" +
                "请求总次数：%d" +
                "错误请求总次数：%d\n" +
                "错误次数占比: %.2f%%\n\n",
                requestCount, networkErrorCount,
                100F * networkErrorCount / requestCount
        );

        errorNetworkCount = networkErrorCount;
    }

    private static void byTotalDevices() throws Exception {
        int totalDeviceCount = NetworkUtils.queryCardData(SlowTableFactory.card())[0].deviceCount;
        System.out.printf("昨日活跃设备数 %d\n\n", totalDeviceCount);
    }

    private static void byTimeout() throws Exception {
        // 1. 先计算总的超时请求数
        SlowTable[] slowTables = NetworkUtils.querySlowTable(
                SlowTableFactory.timeout()
        );
        int timeoutRequestCount = 0;
        for (SlowTable st : slowTables) {
            if ("mapi.wanwustore.cn".equals(st.hostName)) {
                timeoutRequestCount = st.requestCount;
                break;
            }
        }

        // 2. 除去埋点后的超时数量
        slowTables = NetworkUtils.querySlowTable(
                SlowTableFactory.mapiTimeout()
        );
        int countlyTimeoutCount = 0;
        for (SlowTable st : slowTables) {
            if ("/o/ub/*/uicount/count/i".equals(st.uriName)) {
                countlyTimeoutCount += st.requestCount;
            }
            else if ("/o/uc/*/uicount/count/i".equals(st.uriName)) {
                countlyTimeoutCount += st.requestCount;
            }
        }

        timeoutRequestCount -= countlyTimeoutCount;

        System.out.printf("B. 超时情况\n" +
                "超时请求次数：%d\n" +
                "超时请求次数占比；%.2f%%\n\n",
                timeoutRequestCount,
                100F * timeoutRequestCount / errorNetworkCount
        );
    }

    /**
     * 去除埋点后的请求错误数
     */
    private static int errorNetworkCount = 0;

    private static void byChinaRegions() throws Exception {
        RegionData regionData = NetworkUtils.queryRegionData(
                SlowTableFactory.region()
        );

        System.out.println("mapi 错误率城市列表");
        for (RegionData.DataItem item: regionData.data) {
            System.out.printf("%s %.2f%%\n", item.regionName, item.networkErrorRate.value);
        }
    }
//    public int firstBadVersion(int n) {
//        int left = 1, right = n;
//        while (left < right) { // 循环直至区间左右端点相同
//            int mid = left + (right - left) / 2; // 防止计算时溢出
//            if (isBadVersion(mid)) {
//                right = mid; // 答案在区间 [left, mid] 中
//            } else {
//                left = mid + 1; // 答案在区间 [mid+1, right] 中
//            }
//        }
//        // 此时有 left == right，区间缩为一个点，即为答案
//        return left;
//    }
    
    public static void main(String[] args) throws Exception {
//        SlowTableFactory.sApp = SlowTableFactory.APP_IOS;


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        System.out.printf("%d月%d号网络错误数据:\n\n",
                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

        byTotalCount();
        byTotalDevices();
        byTimeout();
        byChinaRegions();
    }
}
