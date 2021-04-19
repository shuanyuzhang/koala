#language: en

@firstFeature
Feature: 后台接口测试
  Scenario: 创建需求
    When case: 登录后台 api: Manager.login assert: $.msg == 登录成功！
      |username|admin|
      |password|123456|

    When case: 需求创建 api: Manager.demand_create_random assert: $.msg==请求成功
      |null|null|
#      |person_num|${person_num}|
#      |hours|${hours}|
#      |start_date|${start_date}|
#      |start_time|${start_time}|
#      |end_time|${end_time}|

#    When case: 批量取消任务sn api: Manager.cancel_task assert: $.code == 605
#      |null|null|
#
#    When case: 任务派工 api: Manager.task_dispatching assert: $.code == 200
#      |shop_id|${shop_id}|
#      |area_id|${area_id}|
#      |start_time|${start_time}|
#      |end_time|${end_time}|
#
#    When case: 全部条件分配人员 api: Manager.task_issued assert: $.code == 200
#      |sn|${task_sn}|
#      |staff_sn|P000001018|
#      |staff_name|章1018|
#      |staff_id|1018|

