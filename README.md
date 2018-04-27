# 使用方法：
		一般情况下使用：一般情况下指的是左边一个返回按钮，中间设置一个标题
   		 new CommonTitleBar().init(this).setTitle("登录").create();               //这行代码适用于大部分需求<br>
		其他功能：<br>
		 new CommonTitleBar().init(this)<br>
		 .setRightTextColor(R.color.colorPrimary)       //单独设置当前页面的颜色<br>
		 .setDrawablePadding(5)                         //右侧图标和文字的距离<br>
		 .setBackground(R.color.colorAccent)            //单独设置本页面标题的背景,推荐在title_bar文件中统一设置，有单独需求时再用这个<br>
		 .hideLeftIcon()                                //隐藏左侧返回键<br>
		 .setTitle("标题")                              //设置标题，可以用资源文件，也可以直接传string<br>
		 .setRightTitle(R.string.str)                   //设置右侧文字，可以用资源文件，也可以直接传string<br>
		 .setRightIcon(R.mipmap.icon_search, "left")    //设置右侧图标相对于文字的位置，left图标在左，right图标在右，如果没设置文字就随便填一个<br>
		 .setTitleClickListener()			//右侧文字的点击事件，其他文字点击事件同理，左侧图标默认返回<br>
     		 .create();					//必写<br>
		 ![](https://github.com/PickyQiu/CommonTitleBar/raw/master/screenshot/screenshot.png)
