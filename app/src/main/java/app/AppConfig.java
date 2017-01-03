package app;

import com.blankj.utilcode.utils.StringUtils;
import com.shawnlin.preferencesmanager.PreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class AppConfig {

    private static AppConfig appConfig = null;
    //	private final static String API_URL_CONFIG_PREFIX = "app_api_url_";
    private final static String SWITCH_CONFIG_PREFIX = "app_switch_";
    private final static String[] switchConfigKeyString = {
            "discovery_recording",
            "discovery_search",
            "competition_search",
            "competition_participate",
            "my_awards",
            "my_uploadmanagement",
            "my_work",
            "my_setting",
            "my_family",
            "my_teacher",
            "my_friend",
            "my_message"};
//  private static final String apiHost = "http://120.25.237.16:8011/";  //正式
//	public static String apiHost = "http://192.168.1.132:8011/";         //测试
    public static String apiHost = "http://120.25.121.111:8011/";        //测试
    public static final String webViewHost = "http://120.25.237.16:8014/";



    private PreferencesManager appConfigSP = null;

    public static AppConfig getInstance() {
        return appConfig;
    }

    public static AppConfig getInstance(PreferencesManager appConfigSP) {
        if(appConfig == null) {
            appConfig = new AppConfig(appConfigSP);
        }
        return appConfig;
    }

    private AppConfig(PreferencesManager appConfigSP) {
        this.appConfigSP = appConfigSP;
//		initSwitchConfig();
        initApi();
    }

    public void setSharedPreferenceUtil(PreferencesManager appConfigSP) {
        this.appConfigSP = appConfigSP;
    }

    private void initApi() {

        appConfigSP.putString("user", apiHost + "user");

        appConfigSP.putString("user_info", apiHost + "user/info");
        appConfigSP.putString("user_avatar", apiHost + "user/avatar");

        appConfigSP.putString("competition_info", apiHost + "competitionInfo");
        appConfigSP.putString("find_module", apiHost + "findModule");

        appConfigSP.putString("qiNiu", apiHost + "qiNiu");

        appConfigSP.putString("child_competition_info", apiHost + "childCompetitionInfo");

        appConfigSP.putString("user_concern", apiHost + "userConcern");

        appConfigSP.putString("user_login", apiHost + "user/login");
        appConfigSP.putString("user_register", apiHost + "user/register");
        appConfigSP.putString("user_find_passwd", apiHost + "user/findPasswd");

        appConfigSP.putString("child_advisor", apiHost + "childAdvisor");

        appConfigSP.putString("work", apiHost + "work");

        appConfigSP.putString("work_category", apiHost + "workCategory");

        appConfigSP.putString("judge_teacher", apiHost + "judgeTeacher");

        appConfigSP.putString("parent_info", apiHost + "parentInfo");
        appConfigSP.putString("family_dynamic", apiHost + "familyDynamic");

        appConfigSP.putString("competition_registration", apiHost + "competitionRegistration");
        appConfigSP.putString("search", apiHost + "search");

        appConfigSP.putString("user_concern", apiHost + "userConcern");
        appConfigSP.putString("child_competition_info", apiHost + "childCompetitionInfo");

        appConfigSP.putString("direct_message", apiHost + "directMessage");

        appConfigSP.putString("judge_teacher_login", apiHost + "judgeTeacher/login");
        appConfigSP.putString("judge_teacher_info", apiHost + "judgeTeacher/info");
        appConfigSP.putString("student", apiHost + "student");

        appConfigSP.putString("judge_teacher", apiHost + "judgeTeacher");

        appConfigSP.putString("competition_score", apiHost + "competitionScore");

        appConfigSP.putString("judge_teacher_avatar", apiHost + "judgeTeacher/avatar");

        appConfigSP.putString("judge_teacher_register", apiHost + "judgeTeacher/register");
        appConfigSP.putString("judge_teacher_find_passwd", apiHost + "judgeTeacher/findPasswd");

        appConfigSP.putString("pay", apiHost + "pay");
        appConfigSP.putString("pay_callback", apiHost + "order/payCallBack");
        appConfigSP.putString("conf", apiHost + "conf");
        appConfigSP.putString("student", apiHost + "student");
        appConfigSP.putString("childInfo", apiHost + "childInfo");
        appConfigSP.putString("competition_registration_data", apiHost + "competitionRegistration");
        appConfigSP.putString("version", webViewHost + "version/version.json");
    }

    public String getApiUrl(String key) {

        if(appConfigSP == null) {
            return null;
        }

        return appConfigSP.getString(key, "");
    }

    public static String getApiHost() {
        return apiHost;
    }

    /**
     * Initialize switch config, run this when app first start
     */
    public void initSwitchConfig() {

        if(appConfigSP == null) {
            return;
        }

        List<String> keyList = Arrays.asList(switchConfigKeyString);

        for(int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            appConfigSP.putBoolean(SWITCH_CONFIG_PREFIX + key, true);
        }
    }

    public void setSwitchConfig(String switchString) {

        if(appConfigSP == null) {
            return;
        }

        if(StringUtils.isEmpty(switchString)) {
            return;
        }
        try {

            JSONObject jsonObject = new JSONObject(switchString);

            if(jsonObject.length() > 0) {

                List<String> keyList = Arrays.asList(switchConfigKeyString);

                for(int i = 0; i < keyList.size(); i++) {

                    String key = keyList.get(i);

                    if(jsonObject.has(key)) {

                        if(jsonObject.optInt(key) == 1) {
                            appConfigSP.putBoolean(SWITCH_CONFIG_PREFIX + key, true);
                        }
                        else{
                            appConfigSP.putBoolean(SWITCH_CONFIG_PREFIX + key, false);
                        }
                    }

                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public boolean getSwitchConfig(String switchName) {

        return appConfigSP != null && appConfigSP.getBoolean(SWITCH_CONFIG_PREFIX + switchName, false);

    }
}
