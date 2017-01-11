package com.zhangbin.andlistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	static final boolean L1 =false;
	static final boolean L2 =false;
	static final boolean L3 =false;
	static final boolean L4 =true;
	
	private String[] data ={"apple","banana","orange","mango","grape","pineapple","cherry"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	if(L1){
		setContentView(R.layout.activity_main);
	}else if(L2||L3){
		setContentView(R.layout.activity_main1);
	}else if(L4){
		setContentView(R.layout.activity_main2);
	}
	if(L1)	/*列表选择框 */
	{
			final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
					this, R.array.ctype,android.R.layout.simple_dropdown_item_1line);	//创建一个适配器		
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 为适配器设置列表框下拉时的选项样式
			spinner.setAdapter(adapter); // 将适配器与选择列表框关联
			// 为选择列表框添加OnItemSelectedListener事件监听
					spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent, View arg1,
								int pos, long id) {
							String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
							Log.i("Spinner示例", result);
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
						}
					});
					Button button = (Button) findViewById(R.id.button1); // 获取提交按钮
					// 为提交按钮添加单击事件监听
					button.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// 获取选择的证件类型并通过提示框显示
							Toast.makeText(MainActivity.this,
									"您选择的证件类型是：" + spinner.getSelectedItem().toString(),
									Toast.LENGTH_SHORT).show(); // 显示被选中的复选按钮
						}
					});
	} 
	if(L2)	/*只有文字的列表视图*/
	{
			final ListView listView=(ListView)findViewById(R.id.listView1);
			listView.addHeaderView(line());		//设置header view
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
					this, R.array.ctype1,android.R.layout.simple_list_item_checked);	//创建一个适配器
			listView.setAdapter(adapter); // 将适配器与ListView关联
			listView.addFooterView(line());		//设置footer view
			listView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View arg1, int pos,
						long id) {
					String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
					Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
				}

			});
	}
	if(L3)	/*只有文字的列表视图*/
	{
			final ListView listView=(ListView)findViewById(R.id.listView1);
			ArrayAdapter<String> adapter =new ArrayAdapter<String>(
					MainActivity.this,android.R.layout.simple_list_item_1,data);	//创建一个适配器
			listView.setAdapter(adapter); // 将适配器与ListView关联
			listView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View arg1, int pos,
						long id) {
					String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
					Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
				}

			});
	}	
	if(L4)	/*带图标的列表视图*/
	{
			ListView listview = (ListView) findViewById(R.id.listView1); // 获取列表视图
			int[] imageId = new int[] { R.drawable.img15, R.drawable.img16,
					R.drawable.img17, R.drawable.img18, R.drawable.img19,
					R.drawable.img20, R.drawable.img21, R.drawable.img22 }; // 定义并初始化保存图片id的数组
			String[] title = new String[] { "保密设置", "安全", "系统设置", "上网", "我的文档",
					"GPS导航", "我的音乐", "E-mail" }; // 定义并初始化保存列表项文字的数组
			List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
			// 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
			for (int i = 0; i < imageId.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
				map.put("image", imageId[i]);
				map.put("title", title[i]);
				listItems.add(map); // 将map对象添加到List集合中
			}
	 /*SimpleAdapter的参数说明     
	     第一个参数 表示访问整个android应用程序接口，基本上所有的组件都需要  
	    第二个参数表示生成一个Map(String ,Object)列表选项      
	    第三个参数表示界面布局的id 表示该文件作为列表项的组件     
	    第四个参数表示该Map对象的哪些key对应value来生成列表项     
	    第五个参数表示来填充的组件 Map对象key对应的资源一依次填充组件 顺序有对应关系   
	   注意的是map对象可以key可以找不到 但组件的必须要有资源填充 因为 找不到key也会返回null 其实就相当于给了一个null资源  
	  下面的程序中如果 new String[] { "name", "head", "desc","name" } new int[] {R.id.name,R.id.head,R.id.desc,R.id.head}
	  这个head的组件会被name资源覆盖      
	*/    
			SimpleAdapter adapter = new SimpleAdapter(this, listItems,
					R.layout.items, new String[] { "title", "image" }, new int[] {
							R.id.title, R.id.image }); // 创建SimpleAdapter
			listview.setAdapter(adapter); // 将适配器与ListView关联
			listview.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View arg1, int pos,
						long id) {
					String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
					Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
				}

			});
	}	
	}/*onCreate*/
	
	/*列表视图	*/
	private View line() {
		ImageView image=new ImageView(this);	//创建一个图像视图
		image.setImageResource(R.drawable.line1);	//设置要显示的图片
		return image;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
