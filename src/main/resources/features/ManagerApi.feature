#language: en

@firstFeature
Feature: 后台接口测试
  Scenario: 创建需求
    Then case: 登录后台 api: Manager.login assert: $.data.app.appKey=="auto-test"
    Then case: 需求创建 api: Manager.demand_create assert: msg == 请求成功




















    #    Then 需求创建"<shopid>""<shopsn>""<labelid>"
#    Examples:
#    |shopid|shopsn|labelid|
#    |248    |E0010000143248   |5 |