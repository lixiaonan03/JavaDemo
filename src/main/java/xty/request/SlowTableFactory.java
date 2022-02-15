package xty.request;

import java.util.*;

public class SlowTableFactory {

    public static final int APP_ANDROID = 0;
    public static final int APP_IOS = 1;

    public static int sApp = APP_ANDROID;


    public static String today() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));

        return "" +
                calendar.get(Calendar.YEAR) + '-' +
                (calendar.get(Calendar.MONTH) + 1) + '-' +
                calendar.get(Calendar.DAY_OF_MONTH) +
                " 00:00:00";
    }

    /**
     * 获取总的请求信息
     */
    public static SlowTableRequest total() {
        SlowTableRequest slowTable = new SlowTableRequest();
        slowTable.dimensions = new String[] {
                "hostId","requestType"
        };
        return slowTable;
    }

    public static SlowTableRequest mapi() {
        SlowTableRequest slowTable = new SlowTableRequest();
        slowTable.dimensions = new String[] {
                "uriId","requestType"
        };

        final List<SlowTableRequest.Filter> filters = new ArrayList<>(Arrays.asList(slowTable.filters));
        filters.add(new SlowTableRequest.Filter("hostId", null, 1891));
        filters.add(new SlowTableRequest.Filter("requestType", null, 0));

        slowTable.filters = filters.toArray(new SlowTableRequest.Filter[0]);
        return slowTable;
    }

    public static SlowTableRequest region() {
        SlowTableRequest slowTable = new SlowTableRequest();

        slowTable.metrics = new String[] {
                "responseTime", "throughput", "networkSpeed", "httpErrorRate", "networkErrorRate"
        };
        slowTable.dimensions = new String[] {
                "regionId"
        };

        final List<SlowTableRequest.Filter> filters = new ArrayList<>(Arrays.asList(slowTable.filters));
        filters.add(new SlowTableRequest.Filter("hostId", null, 1891));
        filters.add(new SlowTableRequest.Filter("requestType", null, 0));
        filters.add(new SlowTableRequest.Filter("countryId", null, 48));
        filters.add(new SlowTableRequest.Filter("regionId", "!=", 0));

        slowTable.filters = filters.toArray(new SlowTableRequest.Filter[0]);
        slowTable.orderByExprs = "networkErrorRate desc";
        slowTable.render = "sdObject";
        slowTable.extra = new SlowTableRequest.Extra(48);

        return slowTable;
    }


    public static SlowTableRequest card() {
        SlowTableRequest slowTable = new SlowTableRequest();
        slowTable.datasource = "APP_DEVICE_DATA";
        slowTable.metrics = new String[] {
                "newCount", "deviceCount", "launchCount", "useTime"
        };
        slowTable.dimensions = null;

        final List<SlowTableRequest.Filter> filters = new ArrayList<>(Arrays.asList(slowTable.filters));
        filters.add(new SlowTableRequest.Filter("sessionDurationTime", ">=", 0));

        slowTable.filters = filters.toArray(new SlowTableRequest.Filter[0]);
        slowTable.orderByExprs = null;
        slowTable.render = "list";
        slowTable.limit = -1;

        return slowTable;
    }

    public static SlowTableRequest timeout() {
        SlowTableRequest slowTable = new SlowTableRequest();
        slowTable.dimensions = new String[] {
                "hostId","requestType"
        };

        final List<SlowTableRequest.Filter> filters = new ArrayList<>(Arrays.asList(slowTable.filters));
        filters.add(new SlowTableRequest.Filter("errorType", null, 2));
        filters.add(new SlowTableRequest.Filter("errorCode", null, 903));

        slowTable.filters = filters.toArray(new SlowTableRequest.Filter[0]);
        return slowTable;
    }

    public static SlowTableRequest mapiTimeout() {
        SlowTableRequest slowTable = new SlowTableRequest();
        slowTable.dimensions = new String[] {
                "uriId","requestType"
        };

        final List<SlowTableRequest.Filter> filters = new ArrayList<>(Arrays.asList(slowTable.filters));
        filters.add(new SlowTableRequest.Filter("hostId", null, 1891));
        filters.add(new SlowTableRequest.Filter("requestType", null, 0));
        filters.add(new SlowTableRequest.Filter("errorType", null, 2));
        filters.add(new SlowTableRequest.Filter("errorCode", null, 903));

        slowTable.filters = filters.toArray(new SlowTableRequest.Filter[0]);
        return slowTable;
    }
}
