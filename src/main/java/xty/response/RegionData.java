package xty.response;

public class RegionData {

    public static final class DataItem {
        public String regionName;
        public NetworkErrorRate networkErrorRate;
    }


    public static final class NetworkErrorRate {
        public float value;
    }

    public DataItem[] data;
}
