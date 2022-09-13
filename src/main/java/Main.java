

import java.io.*;
import java.util.*;
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

	/**
	 *
	 * @param array1  有序数组1
	 * @param array2  有序数组2
	 */
	public static int[] merge(int[] a,int[] b){
        //假设2个数组个数都不为0
		//12345
		//234567
		int[] c = new int[a.length + b.length];

		int i=0,j=0,k = 0;
		while (i<a.length&&j<b.length){
			if(a[i]>=b[j]){
				c[k++] = b[j++];
			}else {
				c[k++] = a[i++];
			}
		}

		while (j<b.length){
			c[k++] = b[j++];
		}
		while (i<a.length){
			c[k++] = a[i++];
		}
		return c;


		/*int addNum = 0; //插入的位置
		int start = 0;//第2个数组的元素从那个地方开始插入
		for (int i = 0; i < array2.length; i++) {
            System.out.println("遍历的次数==="+start);
//			if(start <=array1.length){
				//遍历数组2
				for (int j =start; j < array1.length; j++) {
					if(array2[i] >= array1[j]){
						//如果 2比1大 插入的是1
						result[addNum] = array1[j];
						addNum++;
					}else{
						result[addNum] = array2[i];
						start = j;
						addNum++;
						break;
					}
				}
//			}else{
//				result[addNum] = array2[i];
//				addNum++;
//			}
		}
		return result;*/
	}


	private static int sell(int[] prices){
		// 7,15,5,2,6,3
		if(prices == null || prices.length < 2){
			return 0;
		}
		//先将数组的第一个数当作最小值
		int min = prices[0];
        //存放利润
		int res =0;

		for (int i = 1; i < prices.length; i++) {
			if(prices[i] < min){
               min = prices[i];
			}else{
				//不小，先求出当前值与min的差值
				int tmp = prices[i] - min;
				//差值与res比较
				if(res < tmp){
					//res小就更新
					res = tmp;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) throws Exception {

		List<Integer> list = new ArrayList<>();
		list.add(7);
		list.add(15);
		list.add(5);
		list.add(2);
		list.add(6);
		list.add(3);



//		int[] array1= new int[]{1,2,3,4,5};
//		int[] array2= new int[]{2,3,4,5,6,7};
//
//		int[] result = merge(array1,array2);
//
//		for (int i = 0; i < result.length; i++) {
//			System.out.println("=="+result[i]);
//		}



		int aa =2;
		int b = ~aa;
//		System.out.println("b==="+b);

		try{
			String a= "a";
			int ac = 1/1;
			String a1 = new String("a");
			System.out.println("比价======我的="+(a.equals(a1)));
		}finally {
			System.out.println("报错的=");
		}

		//new InnerTest().test();

//		String filename = "/Users/lixiaonan/Downloads/iOS_10205517_1818E212-8E83-410C-B4F7-65AC457EE16C_dm_2022-03-18-19-09_0.data";
//
//		final byte[] in = open(filename);
//
//		while (in.length - yesDecompress > 1024*10){
//			byte[] need = subBytes(in,yesDecompress,1024*10);
//			todoget(need);
//		}
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





