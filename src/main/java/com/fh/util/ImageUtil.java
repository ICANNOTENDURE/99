package com.fh.util;

import java.io.FileOutputStream;

public class ImageUtil {

	public static void toSmaillImg(String filePath, String thumbPath)
			throws Exception {
		String newurl = thumbPath;
		java.awt.Image bigJpg = javax.imageio.ImageIO.read(new java.io.File(
				filePath));
		float tagsize = 100;
		int old_w = bigJpg.getWidth(null);
		int old_h = bigJpg.getHeight(null);
		int new_w = 0;
		int new_h = 0;
		float tempdouble;
		tempdouble = old_w > old_h ? old_w / tagsize : old_h / tagsize;
		new_w = Math.round(old_w / tempdouble);
		new_h = Math.round(old_h / tempdouble);
		java.awt.image.BufferedImage tag = new java.awt.image.BufferedImage(
				new_w, new_h, java.awt.image.BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(bigJpg, 0, 0, new_w, new_h, null);
		FileOutputStream newimage = new FileOutputStream(newurl);
		com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec
				.createJPEGEncoder(newimage);
		encoder.encode(tag);
		newimage.close();
	}
}
