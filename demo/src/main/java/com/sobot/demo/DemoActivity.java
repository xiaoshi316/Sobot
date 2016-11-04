package com.sobot.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.listener.MessageListener;
import com.sobot.chat.utils.LogUtils;
import com.sobot.chat.utils.ResourceUtils;
import com.sobot.chat.utils.ZhiChiConstant;

import java.util.HashMap;
import java.util.Map;

public class DemoActivity extends Activity {

    private EditText sysNum, sysNum1, sobot_uname,sobot_nikename,sobot_phone,sobot_email,sobot_face,sobot_qq,sobot_weixin,sobot_weibo,sobot_sex,sobot_birthday,sobot_remark,sobot_visitTitle,sobot_visitUrl,sobot_skillSetId,sobot_skillSetName,sobot_zhuanrengongNum;
    private TextView unread_msg_num;
    private CheckBox isKeepSession;
    private CheckBox isShowSatisfaction;
    private CheckBox isArtificialIntelligence,isUseVoice;
    private RadioGroup rg_initModeType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        String appKey = "daa66a3ce2bb4c4a99167a2fd3ce38c8";
        LogUtils.isDebug = true;/*打开日志*/
        this.sysNum = ((EditText) findViewById(ResourceUtils.getIdByName(this,
                "id", "sobot_sysNum")));
        this.sysNum1 = ((EditText) findViewById(ResourceUtils.getIdByName(this,
                "id", "sobot_sysNum1")));
        unread_msg_num = (TextView) findViewById(ResourceUtils.getIdByName(this,
                "id", "unread_msg_num"));
        isKeepSession = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
                "id", "isKeepSession"));
        isShowSatisfaction = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
                "id", "isShowSatisfaction"));
        isArtificialIntelligence = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
                "id", "isArtificialIntelligence"));
        isUseVoice = (CheckBox) findViewById(ResourceUtils.getIdByName(this,
                "id", "isUseVoice"));
        rg_initModeType = (RadioGroup)findViewById(ResourceUtils.getIdByName(this,"id",
                "rg_initModeType"));

        sobot_uname = (EditText) findViewById(R.id.sobot_uname);
        sobot_nikename = (EditText) findViewById(R.id.sobot_nikename);
        sobot_phone = (EditText) findViewById(R.id.sobot_phone);
        sobot_email = (EditText) findViewById(R.id.sobot_email);
        sobot_face = (EditText) findViewById(R.id.sobot_face);
        sobot_qq = (EditText) findViewById(R.id.sobot_qq);
        sobot_weixin = (EditText) findViewById(R.id.sobot_weixin);
        sobot_weibo = (EditText) findViewById(R.id.sobot_weibo);
        sobot_sex = (EditText) findViewById(R.id.sobot_sex);
        sobot_birthday = (EditText) findViewById(R.id.sobot_birthday);
        sobot_remark = (EditText) findViewById(R.id.sobot_remark);
        sobot_visitTitle = (EditText) findViewById(R.id.sobot_visitTitle);
        sobot_visitUrl = (EditText) findViewById(R.id.sobot_visitUrl);
        sobot_skillSetId = (EditText) findViewById(R.id.sobot_skillSetId);
        sobot_skillSetName = (EditText) findViewById(R.id.sobot_skillSetName);
        sobot_zhuanrengongNum = (EditText) findViewById(R.id.sobot_zhuanrengongNum);
        sysNum.setText(appKey);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(sobot_uname.getWindowToken(), 0); //强制隐藏键盘

        findViewById(ResourceUtils.getIdByName(this, "id", "sobot_btnStart"))
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {

                        unread_msg_num.setText("");
                        Information info = new Information();
                        info.setAppKey(sysNum.getText().toString());/* 必填 */
//                        info.setColor("#ff00ff");// 设置头部背景颜色  如果同时设置颜色和背景，以背景图片为主
//                        info.setTitleImgId(R.drawable.sobot_top_background_img);//设置头部背景为图片
                        info.setUid(sysNum1.getText().toString());/* 必填 */
                        info.setUname(sobot_uname.getText().toString());/* 用户昵称，选填 */
                        info.setRealname(sobot_nikename.getText().toString());/*用户姓名*/
                        info.setPhone(sobot_phone.getText().toString());/* 用户电话，选填 */
                        info.setEmail(sobot_email.getText().toString());/* 用户邮箱，选填 */
                        info.setFace(sobot_face.getText().toString());/*自定义头像*/
                        info.setQq(sobot_qq.getText().toString());/*用户QQ*/
                        info.setWeixin(sobot_weixin.getText().toString());/*用户微信*/
                        info.setWeibo(sobot_weibo.getText().toString());/*用户微博*/
                        if (TextUtils.isEmpty(sobot_sex.getText().toString())){
                            info.setSex(0);/*用户性别 0.男，1.女*/
                        } else {
                            info.setSex(Integer.parseInt(sobot_sex.getText().toString()));/*用户性别 0.男，1.女*/
                        }
                        info.setBirthday(sobot_birthday.getText().toString());/*用户生日*/
                        info.setRemark(sobot_remark.getText().toString());/*用户备注*/
                        info.setVisitTitle(sobot_visitTitle.getText().toString());/*对话页标题*/
                        info.setVisitUrl(sobot_visitUrl.getText().toString());/*对话页路径*/

                        info.setBackOrClose(isKeepSession.isChecked());// 设置 是否会话保持。默认true，进行会话保持显示返回。false，不进行会话保持显示关闭
                        info.setShowSatisfaction(isShowSatisfaction.isChecked());
                        // 设置为关闭时，点击关闭是否弹出满意度评价。默认true，弹出，false不弹满意度。直接弹是否关闭的dialog
                        info.setArtificialIntelligence(isArtificialIntelligence.isChecked());/*默认是不选，就是显示转人工按钮。选中的话，是智能转人工*/
                        if (TextUtils.isEmpty(sobot_zhuanrengongNum.getText().toString())){
                            info.setArtificialIntelligenceNum(1);/* 未知问题或者向导问题出现 几次时，显示转人工按钮 */
                        } else {
                            info.setArtificialIntelligenceNum(Integer.parseInt(sobot_zhuanrengongNum.getText().toString()));/* 未知问题或者向导问题出现 几次时，显示转人工按钮 */
                        }
                        info.setSkillSetId(sobot_skillSetId.getText().toString());/* 预设技能组编号，选填 */
                        info.setSkillSetName(sobot_skillSetName.getText().toString());/* 预设技能组名称 */
                        info.setUseVoice(isUseVoice.isChecked());/*是否使用语音功能*/
                        Map<String,String> customInfo = new HashMap<String, String>();
                        customInfo.put("技能1", "打麻将");
                        info.setCustomInfo(customInfo);/* 自定义用户资料 */
                        info.setInitModeType(getInitModeType());
                        /* 客服模式控制  -1不控制 按照服务器后台设置的模式运行
						1仅机器人 2仅人工
						3机器人优先 4人工优先*/

                        ConsultingContent consultingContent = new ConsultingContent();/* 咨询内容 */
                        consultingContent.setTitle("乐视超级电视 S50 Air 全配版50英寸2D智能LED黑色（Letv S50 Air）");/* 咨询内容标题 */
                        consultingContent.setImgUrl("http://www.lishui.com/uploads/140301/1-14030111052Y17.jpg");/* 咨询内容图片 选填 */
                        consultingContent.setFromUrl("商品链接： www.baidu.com");/* 发送内容 */
                        info.setConsultingContent(consultingContent);/* 可以设置为null */

                        SobotApi.startSobotChat(getApplicationContext(), info);
                        //当设置为会话保持后、设置回调获取新收到的信息和未读消息数
                        SobotApi.setMessageListener(new MessageListener() {

                            @Override
                            public void onReceiveMessage(int noReadNum,String content) {
                                //未读消息数
                                unread_msg_num.setText(noReadNum + "");
                                //新消息内容
                                LogUtils.i("新消息内容:"+content);
                            }
                        });
                    }
                });
    }

    private int getInitModeType(){
        int resutlt = -1;
        int id = rg_initModeType.getCheckedRadioButtonId();
        switch (id){
            case R.id.rg_initModeType_noSet:
                resutlt = -1;
                break;
            case R.id.rg_initModeType_only_robot:
                resutlt = ZhiChiConstant.type_robot_only;
                break;
            case R.id.rg_initModeType_only_customer:
                resutlt = ZhiChiConstant.type_custom_only;
                break;
            case R.id.rg_initModeType_robot_first:
                resutlt = ZhiChiConstant.type_robot_first;
                break;
            case R.id.rg_initModeType_custom_first:
                resutlt = ZhiChiConstant.type_custom_first;
                break;
        }
        return resutlt;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}