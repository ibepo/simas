package com.simas.test;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.simas.R;
import com.simas.base.BaseAct;
import com.simas.utils.BitmapUtil;

public class CameraTest extends BaseAct {

	ImageView iv;
	Button btCamera;
	File tempFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_camera);
		init();
	}

	private void init() {

		tempFile = new File(Environment.getExternalStorageDirectory(),
				System.currentTimeMillis() + ".jpg");

		iv = (ImageView) this.findViewById(R.id.iv);
		btCamera = (Button) this.findViewById(R.id.btCamera);
		btCamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
				if (intent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(intent, 100);
				}

			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100 && resultCode == RESULT_OK) {
			// 设置照片存储路径后，有的机型返回的intent 就为null,这时不能从里面取数据了，
			// 需要从自己定义的路径取数据
			// Bitmap thumbnail = data.getParcelableExtra("data");
			Bitmap Opic = null;
			// try {
			// // 读取文件。
			// Opic = BitmapFactory
			// .decodeStream(new FileInputStream(tempFile));
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// }

			Opic = BitmapUtil.reSizePic(tempFile.getAbsolutePath(), 480, 640);

			iv.setImageBitmap(Opic);
		}
	}

}
