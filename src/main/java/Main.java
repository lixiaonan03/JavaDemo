

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.math.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.zip.*;

/**
  *  @author lixiaonan
  *  功能描述: 解压文件处理的
  *  时 间： 2022/3/24 7:05 下午
  */
public class Main {

	private static byte[] open(String s) throws Exception {
		InputStream is = new FileInputStream(s);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int count;

		while ((count = is.read(buff, 0, buff.length)) != -1) {
			bos.write(buff, 0, count);
		}

		is.close();
		bos.flush();
		return bos.toByteArray();
	}


	/**
	 * 从一个byte[]数组中截取一部分
	 * @param src
	 * @param begin
	 * @param count
	 * @return
	 */
	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i=begin;i<begin+count; i++){
			bs[i-begin] = src[i];
		}
		return bs;
	}

	//已经解压的数据
	static volatile int yesDecompress = 0;
	public static void todoget(byte[] need){
		try {
			Inflater inflater = new Inflater(true);
			if(yesDecompress==0){
				inflater.setInput(need, 73, need.length - 73);
			}else{
				inflater.setInput(need);
			}
			yesDecompress += 10240;
			byte[] out = new byte[1024 * 10];
			inflater.inflate(out);
			inflater.end();
			File file =new File("/Users/lixiaonan/Downloads/ios解密2");
			FileOutputStream fos= new FileOutputStream(file,true);
			fos.write(out);
			fos.flush();
			fos.close();
		} catch (DataFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws Exception {
		String filename = "/Users/lixiaonan/Downloads/iOS_10205517_1818E212-8E83-410C-B4F7-65AC457EE16C_dm_2022-03-18-19-09_0.data";

		final byte[] in = open(filename);

		while (in.length - yesDecompress > 1024*10){
			byte[] need = subBytes(in,yesDecompress,1024*10);
			todoget(need);
		}
//		String out = decompress(in);
//		System.out.println(out);
//		writeFile("/Users/lixiaonan/Downloads/","密",out);

//
//		Inflater inflater = new Inflater(true);
//		inflater.setInput(in, 73, in.length - 73);
//		byte[] out = new byte[1024 * 10];
//		inflater.inflate(out);
//		File file =new File("/Users/lixiaonan/Downloads/ios解密2");
//		FileOutputStream fos= new FileOutputStream(file);
//		fos.write(out);
//		fos.flush();
//		fos.close();

//		System.out.println(new String(out, StandardCharsets.UTF_8));
	}

	/**
	 * 解压缩
	 *
	 * @param data 待压缩的数据
	 * @return byte[] 解压缩后的数据
	 */
	public static String decompress(byte[] data) {


		try {
			// Encode a String into bytes
			String inputString = "blahblahblah€€";
			byte[] input = inputString.getBytes("UTF-8");

			// Compress the bytes
			byte[] output = new byte[100];
			Deflater compresser = new Deflater();
			compresser.setInput(input);
			compresser.finish();
			int compressedDataLength = compresser.deflate(output);

			// Decompress the bytes
			Inflater decompresser = new Inflater();
			decompresser.setInput(output, 0, compressedDataLength);
			byte[] result = new byte[100];
			int resultLength = decompresser.inflate(result);
			decompresser.end();

			// Decode the bytes into a String
			String outputString = new String(result, 0, resultLength, "UTF-8");
		} catch (java.io.UnsupportedEncodingException ex) {
			// handle
		} catch (java.util.zip.DataFormatException ex) {
			// handle
		}

		byte[] output;
		Inflater decompresser = new Inflater(true);
		decompresser.setInput(data, 73, data.length - 73);
        StringBuilder stringBuilder= new StringBuilder();
		ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
		try {
			int i=0;
			//10424 就不ok了 最大解压10K https://code.googlesource.com/edge/openjdk/+/jdk8u111-b09/jdk/src/share/native/java/util/zip/zlib-1.2.8/inflate.h
			byte[] buf = new byte[1024*5];
			while (!decompresser.finished()) {
				i = decompresser.inflate(buf);
				System.out.println("i==="+i);
				o.write(buf);
			}
			stringBuilder.append(o);
		} catch (Exception e) {
			output = data;
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		decompresser.end();
		try {
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * 将byte数组写入文件
	 *
	 * @param path
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public static void writeFile(String path, String fileName,String content)
			throws IOException {
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(path + fileName);
			OutputStreamWriter oStreamWriter = new OutputStreamWriter(fos);
			oStreamWriter.append(content);
			oStreamWriter.close();

//			fos.write(content);
//			fos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 解压文件/文件夹
	 */
	public static void decompress(String srcPath, String dest) throws Exception {
		File file = new File(srcPath);
		if (!file.exists()) {
			throw new RuntimeException(srcPath + "所指文件不存在");
		}
		ZipFile zf = new ZipFile(file);
		Enumeration entries = zf.entries();
		ZipEntry entry = null;
		while (entries.hasMoreElements()) {
			entry = (ZipEntry) entries.nextElement();
			System.out.println("解压" + entry.getName());
			if (entry.isDirectory()) {
				String dirPath = dest + File.separator + entry.getName();
				File dir = new File(dirPath);
				dir.mkdirs();
			} else {
				// 表示文件
				File f = new File(dest + File.separator + entry.getName());
				if (!f.exists()) {
					//String dirs = FileUtils.getParentPath(f);
					String dirs = f.getParent();
					File parentDir = new File(dirs);
					parentDir.mkdirs();
				}
				f.createNewFile();
				// 将压缩文件内容写入到这个文件中
				InputStream is = zf.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(f);
				int count;
				byte[] buf = new byte[8192];
				while ((count = is.read(buf)) != -1) {
					fos.write(buf, 0, count);
				}
				is.close();
				fos.close();
			}
		}
	}
}





