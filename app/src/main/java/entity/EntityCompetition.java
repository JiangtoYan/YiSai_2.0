package entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/27.
 */

public class EntityCompetition implements Serializable{

    /**
     * last_time : 2017年02月28日
     * match_name : 亚太杯国际音乐、舞蹈大赛
     * competition_type : 0
     * start_time : 2016年12月22日
     * registration_time_end : 2017年02月28日
     * judge_score_time_begin : 2017年03月01日
     * register_number : 6
     * cpid : 6cJTFs3vvuS
     * competition_process : 1
     * cover_plan : http://7xkoy9.media1.z0.glb.clouddn.com/o_1b4i7qtsc1sqj1vpa14ol1jb71jc415.jpg
     * judge_score_time_end : 2017年03月22日
     * registration_time_begin : 2016年12月22日
     */

    private String last_time;
    private String match_name;
    private String competition_type;
    private String start_time;
    private String registration_time_end;
    private String judge_score_time_begin;
    private int register_number;
    private String cpid;
    private String competition_process;
    private String cover_plan;
    private String judge_score_time_end;
    private String registration_time_begin;

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getMatch_name() {
        return match_name;
    }

    public void setMatch_name(String match_name) {
        this.match_name = match_name;
    }

    public String getCompetition_type() {
        return competition_type;
    }

    public void setCompetition_type(String competition_type) {
        this.competition_type = competition_type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getRegistration_time_end() {
        return registration_time_end;
    }

    public void setRegistration_time_end(String registration_time_end) {
        this.registration_time_end = registration_time_end;
    }

    public String getJudge_score_time_begin() {
        return judge_score_time_begin;
    }

    public void setJudge_score_time_begin(String judge_score_time_begin) {
        this.judge_score_time_begin = judge_score_time_begin;
    }

    public int getRegister_number() {
        return register_number;
    }

    public void setRegister_number(int register_number) {
        this.register_number = register_number;
    }

    public String getCpid() {
        return cpid;
    }

    public void setCpid(String cpid) {
        this.cpid = cpid;
    }

    public String getCompetition_process() {
        return competition_process;
    }

    public void setCompetition_process(String competition_process) {
        this.competition_process = competition_process;
    }

    public String getCover_plan() {
        return cover_plan;
    }

    public void setCover_plan(String cover_plan) {
        this.cover_plan = cover_plan;
    }

    public String getJudge_score_time_end() {
        return judge_score_time_end;
    }

    public void setJudge_score_time_end(String judge_score_time_end) {
        this.judge_score_time_end = judge_score_time_end;
    }

    public String getRegistration_time_begin() {
        return registration_time_begin;
    }

    public void setRegistration_time_begin(String registration_time_begin) {
        this.registration_time_begin = registration_time_begin;
    }
}
