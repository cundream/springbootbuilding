
package com.github.cundream.springbootbuilding.common.enums;



public enum ErrorCodeEnum {
	/**
	 * Gl 99990100 error code enum.
	 */
	GL99990100(9999100, "参数异常"),
	/**
	 * Gl 99990401 error code enum.
	 */
	GL99990401(99990401, "无访问权限"),
	/**
	 * Gl 000500 error code enum.
	 */
	GL99990500(500, "未知异常"),
	/**
	 * Gl 000403 error code enum.
	 */
	GL99990403(9999403, "无权访问"),
	/**
	 * Gl 000404 error code enum.
	 */
	GL9999404(9999404, "找不到指定资源"),
	/**
	 * Gl 99990001 error code enum.
	 */
	GL99990001(99990001, "注解使用错误"),
	/**
	 * Gl 99990002 error code enum.
	 */
	GL99990002(99990002, "微服务不在线,或者网络超时"),
	/**
	 * JWS 10010001 error code enum.
	 */
//	 1001 用户中心
	JWS10010001(10010001, "会话超时,请刷新页面重试"),
	/**
	 * JWS 10010002 error code enum.
	 */
	JWS10010002(10010002, "TOKEN解析失败"),
	/**
	 * JWS 10010003 error code enum.
	 */
	JWS10010003(10010003, "操作频率过快, 您的帐号已被冻结"),
	/**
	 * JWS 10011001 error code enum.
	 */
	JWS10011001(10011001, "用户Id不能为空"),
	/**
	 * JWS 10011002 error code enum.
	 */
	JWS10011002(10011002, "找不到用户,loginName=%s"),
	/**
	 * JWS 10011003 error code enum.
	 */
	JWS10011003(10011003, "找不到用户,userId=%s"),
	/**
	 * JWS 10011004 error code enum.
	 */
	JWS10011004(10011004, "找不到用户,email=%s"),
	/**
	 * JWS 10011006 error code enum.
	 */
	JWS10011006(10012006, "手机号不能为空"),
	/**
	 * JWS 10011007 error code enum.
	 */
	JWS10011007(10011007, "登录名不能为空"),
	/**
	 * JWS 10011008 error code enum.
	 */
	JWS10011008(10011008, "新密码不能为空"),
	/**
	 * JWS 10011009 error code enum.
	 */
	JWS10011009(10011009, "确认密码不能为空"),
	/**
	 * JWS 10011010 error code enum.
	 */
	JWS10011010(10011010, "两次密码不一致"),
	/**
	 * JWS 10011011 error code enum.
	 */
	JWS10011011(10011011, "用户不存在"),
	/**
	 * JWS 10011012 error code enum.
	 */
	JWS10011012(10011012, "用户名已存在"),
	/**
	 * JWS 10011013 error code enum.
	 */
	JWS10011013(10011013, "手机号已存在"),
	/**
	 * JWS 10011014 error code enum.
	 */
	JWS10011014(10011014, "密码不能为空"),
	/**
	 * JWS 10011016 error code enum.
	 */
	JWS10011016(10011016, "用户名或密码错误"),
	/**
	 * JWS 10011017 error code enum.
	 */
	JWS10011017(10011017, "验证类型错误"),
	/**
	 * JWS 10011018 error code enum.
	 */
	JWS10011018(10011018, "邮箱不能为空"),
	/**
	 * JWS 10011019 error code enum.
	 */
	JWS10011019(10011019, "邮箱已存在"),
	/**
	 * JWS 10011020 error code enum.
	 */
	JWS10011020(10011020, "短信模板不能为空"),
	/**
	 * JWS 10011021 error code enum.
	 */
	JWS10011021(10011021, "发送短信验证码对象转换为json字符串失败"),
	/**
	 * JWS 10011022 error code enum.
	 */
	JWS10011022(10011022, "发送短信验证码失败"),
	/**
	 * JWS 10011023 error code enum.
	 */
	JWS10011023(10011023, "越权操作"),
	/**
	 * JWS 10011024 error code enum.
	 */
	JWS10011024(10011024, "找不到绑定的用户, userId=%"),
	/**
	 * JWS 10011025 error code enum.
	 */
	JWS10011025(10011025, "用户已存在, loginName=%"),
	/**
	 * JWS 10011026 error code enum.
	 */
	JWS10011026(10011026, "更新用户失败, userId=%"),
	/**
	 * JWS 10011027 error code enum.
	 */
	JWS10011027(10011027, "账户被禁用"),
	/**
	 * JWS 10011028 error code enum.
	 */
	JWS10011028(10011028, "链接已失效"),
	/**
	 * JWS 10011029 error code enum.
	 */
	JWS10011029(10011029, "重置密码失败"),
	/**
	 * JWS 10011029 error code enum.
	 */
	JWS11011029(11011029, "邮箱验证码错误"),
	/**
	 * JWS 10011029 error code enum.
	 */
	JWS12011029(12011029, "图形验证码错误"),
	/**
	 * JWS 10011029 error code enum.
	 */
	JWS13011029(13011029, "短信验证码错误"),
	/**
	 * JWS 10011030 error code enum.
	 */
	JWS10011030(10011030, "激活失败, 链接已过期"),
	/**
	 * JWS 10011031 error code enum.
	 */
	JWS10011031(10011031, "验证码超时, 请重新发送验证码"),
	/**
	 * JWS 10011032 error code enum.
	 */
	JWS10011032(10011032, "邮箱不存在, loginName=%s,email=%s"),
	/**
	 * JWS 10011033 error code enum.
	 */
	JWS10011033(10011033, "清空该用户常用菜单失败"),
	/**
	 * JWS 10011034 error code enum.
	 */
	JWS10011034(10011034, "不允许操作admin用户"),
	/**
	 * JWS 10011035 error code enum.
	 */
	JWS10011035(10011035, "原始密码输入错误"),
	/**
	 * JWS 10011036 error code enum.
	 */
	JWS10011036(10011036, "新密码和原始密码不能相同"),
	/**
	 * JWS 10011037 error code enum.
	 */
	JWS10011037(10011037, "修改用户失败,userId=%s"),
	/**
	 * JWS 10011038 error code enum.
	 */
	JWS10011038(10011038, "激活用户失败,userId=%s"),
	/**
	 * JWS 10011039 error code enum.
	 */
	JWS10011039(10011039, "验证token失败"),
	/**
	 * JWS 10011040 error code enum.
	 */
	JWS10011040(10011040, "解析header失败"),
	/**
	 * JWS 10011041 error code enum.
	 */
	JWS10011041(10011041, "页面已过期,请重新登录"),
	/**
	 * JWS 10011042 error code enum.
	 */
	JWS10011042(10011042, "Cookie转码异常"),


	/**
	 * JWS 10012001 error code enum.
	 */
	JWS10012001(10012001, "角色ID不能为空"),
	/**
	 * JWS 10012002 error code enum.
	 */
	JWS10012002(10012002, "拥有的角色不允许禁用"),
	/**
	 * JWS 10012003 error code enum.
	 */
	JWS10012003(10012003, "系统角色不能删除"),
	/**
	 * JWS 10012004 error code enum.
	 */
	JWS10012004(10012004, "超级角色Id不能为空"),

	/**
	 * JWS 10012005 error code enum.
	 */
	JWS10012005(10012005, "找不到角色信息,roleId=%s"),
	/**
	 * JWS 10012006 error code enum.
	 */
	JWS10012006(10012006, "删除角色失败, roleId=%s"),
	/**
	 * JWS 10012007 error code enum.
	 */
	JWS10012007(10012007, "批量删除角色失败, roleId=%s"),
	/**
	 * JWS 10012008 error code enum.
	 */
	JWS10012008(10012008, "找不到绑定的角色, roleId=%s"),


	/**
	 * JWS 10013001 error code enum.
	 */
	JWS10013001(10013001, "父菜单不存在,menuId=%s"),
	/**
	 * JWS 10013002 error code enum.
	 */
	JWS10013002(10013002, "更新上级菜单失败,menuId=%s"),
	/**
	 * JWS 10013003 error code enum.
	 */
	JWS10013003(10013003, "菜单不存在,menuId=%s"),
	/**
	 * JWS 10013004 error code enum.
	 */
	JWS10013004(10013004, "启用菜单失败,menuId=%s"),
	/**
	 * JWS 10013005 error code enum.
	 */
	JWS10013005(10013005, "禁用菜单失败,menuId=%s"),
	/**
	 * JWS 10013006 error code enum.
	 */
	JWS10013006(10013006, "更新菜单状态失败,menuId=%s"),
	/**
	 * JWS 10013007 error code enum.
	 */
	JWS10013007(10013007, "根菜单不能禁用"),
	/**
	 * JWS 10013008 error code enum.
	 */
	JWS10013008(10013008, "删除菜单失败, menuId=%s"),
	/**
	 * JWS 10013009 error code enum.
	 */
	JWS10013009(10013009, "请先分配菜单"),
	/**
	 * JWS 10013010 error code enum.
	 */
	JWS10013010(10013010, "选择菜单不是根目录,menuId=%s"),


	/**
	 * JWS 10014001 error code enum.
	 */
	JWS10014001(10014001, "找不到权限信息, actionId=%s"),
	/**
	 * JWS 10014002 error code enum.
	 */
	JWS10014002(10014002, "删除失败, actionId=%s"),
	/**
	 * JWS 10014003 error code enum.
	 */
	JWS10014003(10014003, "保存权限信息失败"),
	/**
	 * JWS 10015001 error code enum.
	 */
	JWS10015001(10015001, "找不到组织信息,groupId=%s"),
	/**
	 * JWS 10015002 error code enum.
	 */
	JWS10015002(10015002, "组织状态不存在"),
	/**
	 * JWS 10015003 error code enum.
	 */
	JWS10015003(10015003, "操作越权, 启用子节点, 必须先启用父节点"),
	/**
	 * JWS 10015004 error code enum.
	 */
	JWS10015004(10015004, "找不到组织信息,groupId=%s"),
	/**
	 * JWS 10015006 error code enum.
	 */
	JWS10015006(10015006, "更新组织信息失败,groupId=%s"),
	/**
	 * JWS 10015007 error code enum.
	 */
	JWS10015007(10015007, "该组织下还存在子节点，不能将其删除, Pid=%s"),
	/**
	 * JWS 10015008 error code enum.
	 */
	JWS10015008(10015008, "该组织下绑定的用户，不能将其删除, groupId=%s"),
	/**
	 * JWS 10015009 error code enum.
	 */
	JWS10015009(10015009, "找不到上级组织, groupId=%s"),
	/**
	 * JWS 10015010 error code enum.
	 */
	JWS10015010(10015010, "该账号已绑定其他用户，请重新输入"),

	/**
	 * JWS 11015010 error code enum.
	 */
	JWS11015010(11015010, "该手机已绑定其他用户，请重新输入"),

	/**
	 * JWS 11015011 error code enum.
	 */
	JWS11015011(11015011, "该邮箱已绑定其他用户，请重新输入"),

	/**
	 * JWS 10015011 error code enum.
	 */
	JWS10015011(10015011, "邮箱和手机号不能全为空"),

	/**
	 * JWS 10015011 error code enum.
	 */
	JWS10015012(10015012, "请输入正确的手机格式"),
	/**
	 * JWS 10015011 error code enum.
	 */
	JWS10015013(10015013, "验证码失效或错误，重试或重新发送验证码"),

	/**
	 * JWS 10015011 error code enum.
	 */
	JWS10015014(10015014, "该分组下还有用户存在"),

	/**
	 * JWS 10015011 error code enum.
	 */
	JWS10015015(10015015, "该分组下还有组存在"),
	/**
	 * JWS 10015011 error code enum.
	 */
	JWS10015016(10015016, "该用户已存在次分组"),

    /**
     * JWS 10015011 error code enum.
     */
    JWS10015017(10015017, "请输入正确的邮箱格式"),
	/**
	 * JWS 10015011 error code enum.
	 */
	JWS10015018(10015018, "该分组下有角色关联");

	private int code;
	private String msg;

	/**
	 * Msg string.
	 *
	 * @return the string
	 */
	public String msg() {
		return msg;
	}

	/**
	 * Code int.
	 *
	 * @return the int
	 */
	public int code() {
		return code;
	}

	ErrorCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * Gets enum.
	 *
	 * @param code the code
	 *
	 * @return the enum
	 */
	public static ErrorCodeEnum getEnum(int code) {
		for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
			if (ele.code() == code) {
				return ele;
			}
		}
		return null;
	}
}
