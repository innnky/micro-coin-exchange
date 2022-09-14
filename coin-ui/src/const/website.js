export default {
  year: "2022", // 首页显示的时间
  version: "3.5.2", // 控制首页显示的版本号
  logo: "Coin",   // 侧边栏搜索的时候顶部展示的文案
  key: "coin", //配置主键,目前用于存储
  title: "coin 快速开发框架",
  validateCode: true,//是否开启验证码校验
  register: true, //是否开启注册
  formLoginClient: 'pig:pig',// 用户名密码登录的 client 信息
  smsLoginClient: 'app:app',// 验证码登录的 client 信息
  remainingTime: 1800000, // token 剩余多少毫秒执行刷新
  whiteList: ["/login", "/404", "/401", "/lock"], // 配置无权限可以访问的页面
  whiteTagList: ["/login", "/404", "/401", "/lock"], // 配置不添加tags页面 （'/advanced-router/mutative-detail/*'——*为通配符）
  lockPage: "/lock",
  tokenTime: 6000,
  statusWhiteList: [428],
  // 配置首页不可关闭
  isFirstPage: false,
  fistPage: {
    label: "首页",
    value: "/wel/index",
    params: {},
    query: {},
    group: [],
    close: false
  },
  // 配置菜单的属性
  menu: {
    props: {
      label: "label",
      path: "path",
      icon: "icon",
      children: "children"
    }
  }
};
