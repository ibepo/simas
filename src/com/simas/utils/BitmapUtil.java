package com.simas.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

public class BitmapUtil {

	/*
	 * 
	 * �˷�����û�����ڵĸı�ԭ�ļ�ͼƬ�Ĵ�С��
	 * 
	 * width��height Ϊ�ο��� ��͸ߣ�ʵ�ʷ��صĴ�С��option.inSampleSize ȷ��
	 */

	public static Bitmap reSizePic(String path, int width, int heith) {

		BitmapFactory.Options option = new BitmapFactory.Options();

		// ����inJustDecodeBounds Ϊtrue֮��BitmapFactory.decodeFile ������bitmap,ֻ��

		// ��option�з���ͼƬ�Ĵ�С

		option.inJustDecodeBounds = true;

		BitmapFactory.decodeFile(path, option);

		int oWidth = option.outWidth;

		int oHeihth = option.outHeight;

		System.out
				.println("-------------------- the originai size wihth and heigth "
						+ oWidth + " , " + oHeihth);

		System.out
				.println("-------------------- the request size wihth and heigth "
						+ width + " , " + heith);

		// ����������������ֻ�Ǽ򵥵ļ��㡣

		int scaleFactor = Math.min(oWidth / width, oHeihth / heith);

		// ��������Ϊfalse������ͼƬ

		option.inJustDecodeBounds = false;

		// ���û�õ�ͼƬ������������Ϊ2ʱ�����ص�ͼƬ��СΪԭ����1/2

		option.inSampleSize = scaleFactor;

		Bitmap res = BitmapFactory.decodeFile(path, option);

		System.out
				.println("-------------------- the resize real size wihth and heigth "
						+ res.getWidth() + " , " + res.getHeight());

		return res;

	}

	/**
	 * convert Bitmap to byte array
	 */
	public static byte[] bitmapToByte(Bitmap b) {
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		b.compress(Bitmap.CompressFormat.PNG, 100, o);
		return o.toByteArray();
	}

	/**
	 * convert byte array to Bitmap
	 */
	public static Bitmap byteToBitmap(byte[] b) {
		return (b == null || b.length == 0) ? null : BitmapFactory
				.decodeByteArray(b, 0, b.length);
	}

	/**
	 * ��bitmapת����Base64����String
	 */
	public static String bitmapToString(Bitmap bitmap) {
		return Base64.encodeToString(bitmapToByte(bitmap), Base64.DEFAULT);
	}

	/**
	 * convert Drawable to Bitmap
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		return drawable == null ? null : ((BitmapDrawable) drawable)
				.getBitmap();
	}

	/**
	 * convert Bitmap to Drawable
	 */
	public static Drawable bitmapToDrawable(Bitmap bitmap) {
		return bitmap == null ? null : new BitmapDrawable(bitmap);
	}

	/**
	 * scale image
	 */
	public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
		return scaleImage(org, (float) newWidth / org.getWidth(),
				(float) newHeight / org.getHeight());
	}

	/**
	 * scale image
	 */
	public static Bitmap scaleImage(Bitmap org, float scaleWidth,
			float scaleHeight) {
		if (org == null) {
			return null;
		}
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(),
				matrix, true);
	}

	public static Bitmap toRoundCorner(Bitmap bitmap) {
		int height = bitmap.getHeight();
		int width = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, width, height);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(0xff424242);
		// paint.setColor(Color.TRANSPARENT);
		canvas.drawCircle(width / 2, height / 2, width / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static Bitmap createBitmapThumbnail(Bitmap bitMap,
			boolean needRecycle) {
		int width = bitMap.getWidth();
		int height = bitMap.getHeight();
		// ������Ҫ�Ĵ�С
		int newWidth = 120;
		int newHeight = 120;
		// �������ű���
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// ȡ����Ҫ���ŵ�matrix����
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// �õ��µ�ͼƬ
		Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height,
				matrix, true);
		if (needRecycle)
			bitMap.recycle();
		return newBitMap;
	}

	public static boolean saveBitmap(Bitmap bitmap, File file) {
		if (bitmap == null)
			return false;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static boolean saveBitmap(Bitmap bitmap, String absPath) {
		return saveBitmap(bitmap, new File(absPath));
	}

	/**
	 * ����ͼƬ������ֵ ���ͼƬ��ԭʼ�߶Ȼ��߿�ȴ������������Ŀ�Ⱥ͸߶ȣ�������Ҫ��������ű�������ֵ������Ͳ����š�
	 * heightRatio��ͼƬԭʼ�߶���ѹ����߶ȵı����� widthRatio��ͼƬԭʼ�����ѹ�����ȵı�����
	 * inSampleSize��������ֵ ��ȡheightRatio��widthRatio����С��ֵ��
	 * inSampleSizeΪ1��ʾ��Ⱥ͸߶Ȳ����ţ�Ϊ2��ʾѹ����Ŀ����߶�Ϊԭ����1/2(ͼƬΪԭ1/4)��
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions(�ߴ�) larger than or equal to
			// the requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	/**
	 * ����·�����ͼƬ��ѹ������bitmap������ʾ
	 * 
	 * @return
	 */
	public static Bitmap getSmallBitmap(String filePath, int w, int h) {
		final BitmapFactory.Options options = new BitmapFactory.Options();

		// ��ֵ��Ϊtrue��ô��������ʵ�ʵ�bitmap����������ڴ�ռ������ֻ����һЩ����߽���Ϣ��ͼƬ��С��Ϣ
		options.inJustDecodeBounds = true;// inJustDecodeBounds����Ϊtrue�����Բ���ͼƬ�����ڴ���,����Ȼ���Լ����ͼƬ�Ĵ�С
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, w, h);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;// ���¶���ͼƬ��ע�����Ҫ��options.inJustDecodeBounds
											// ��Ϊ false
		Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);// BitmapFactory.decodeFile()��ָ����Сȡ��ͼƬ����ͼ
		return bitmap;
	}

	public static Intent buildGalleryPickIntent(Uri saveTo, int aspectX,
			int aspectY, int outputX, int outputY, boolean returnData) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("output", saveTo);
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", returnData);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
		return intent;
	}

	public static Intent buildImagePickIntent(Uri uriFrom, Uri uriTo,
			int aspectX, int aspectY, int outputX, int outputY,
			boolean returnData) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uriFrom, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("output", uriTo);
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", returnData);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
		return intent;
	}

	public static Intent buildCaptureIntent(Uri uri) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		return intent;
	}

}
