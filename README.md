此工具为java项目。为android 里面的string.xml里面的字符串坐翻译处理.\n
例如string.xml里面有如下字符串\n
<string name="success">成功</string>\n
翻译后则在translate目录下会有英语.txt文件\n
里面对应的翻译为\n
<string name="success">success</string>\n
要翻译的语言在国家.txt文件里面，若不要某种语言，可删除.若添加，则在后面添加,用逗号隔开\n
翻译api用的是百度翻译\n
目前只支持<string>标签里面的字符串翻译.<br>标签里面包含占位符则可能翻译会有误差<br>
