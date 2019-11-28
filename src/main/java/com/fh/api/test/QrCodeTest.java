package com.fh.api.test;
 
public class QrCodeTest {
 
	public static void main(String[] args) throws Exception {
		// 存放在二维码中的内容
		String text = "llt";
		// 嵌入二维码的图片路径
		String imgPath = "D:\\1904A\\二代\\Sup\\a.jpg";
		// 生成的二维码的路径及名称
		String destPath = "D:\\网页下载地址\\asd.jpg";
		//生成二维码
		QRCodeUtil.encode(text, imgPath, destPath, true);
		// 解析二维码
		String str = QRCodeUtil.decode(destPath);
		// 打印出解析出的内容
		System.out.println(str);
	}
 
}