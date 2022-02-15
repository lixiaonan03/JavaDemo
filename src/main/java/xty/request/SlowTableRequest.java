package xty.request;

public class SlowTableRequest {

    public static final class Filter {
        public String name;

        public int[] value;

        public String operator;

        public Filter() {}

        public Filter(String name, String operator, int... values) {
            this.name = name;
            this.operator = operator;
            this.value = values;
        }
    }

    public static final class Extra {
        public int countryId;

        public Extra(int countryId) {
            this.countryId = countryId;
        }
    }

    public int timePeriod = 1440;

    public String endTime = SlowTableFactory.today();

    public String datasource = "APP_NETWORK_DATA";

    public String[] metrics = new String[] {
            "requestCount","throughput","responseTime","networkTime",
            "dnsTime","httpErrorRate","connectTime","sslTime",
            "firstPacketTime","remainPacketTime","localQueueTime", "networkErrorRate",
            "trafficConsumption","networkSpeed", "bytesSend", "bytesReceived",
            "httpErrorCount","networkErrorCount", "slowCount","slowRequestRate"
    };

    public String[] dimensions;

    public Filter[] filters = new Filter[] {
            SlowTableFactory.sApp == SlowTableFactory.APP_ANDROID ?
                    new Filter("mobileAppVersionId", null, 19326, 19521, 19395) :
                    new Filter("mobileAppVersionId", null, 19280),

            SlowTableFactory.sApp == SlowTableFactory.APP_ANDROID ?
                    new Filter("mobileAppId", "IN", 100059) :
                    new Filter("mobileAppId", "IN", 100057),
    };

    public String orderByExprs = "requestCount DESC";

    public int limit = 1000;

    public String render = "list";

    public Extra extra;
}
