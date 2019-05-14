//首页+健康推荐功能
public class MainActivity extends Activity {
    private TextView positionTextView;
    private LocationManager locationManager;
    private String provider;
    private Location location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        positionTextView = (TextView) findViewById(R.id.position_text_view);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            //当没有可用的位置提供器时，提示用户,并结束程序
            Toast.makeText(this, "No Location Provider to use", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            location = locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (location != null) {
            showLocation(location);
        }
        try {
            //实时更新地理信息
            locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }
        @Override
        public void onProviderEnabled(String s) {
        }
        @Override
        public void onProviderDisabled(String s) {
        }
    };
    //设置positionTextView的值并显示
    private void showLocation(Location location) {
        String currentPosition = "当前位置经纬度：("+location.getLatitude()+","+location.getLongitude()+")";
        positionTextView.setText(currentPosition);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //关闭程序时将监听器移除
            try {
                locationManager.removeUpdates(locationListener);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}
