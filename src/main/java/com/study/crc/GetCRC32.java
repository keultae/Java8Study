package com.study.crc;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class GetCRC32 {

	public static void main(String args[]) {
		// 파일명을 옵션으로 주지 않으면 에러 내고 종료
		if (args.length != 1) {
			System.err.println("Usage: java GetCRC32 <input filename>");
			System.exit(1);
		}
		
		// CRC32 값과 파일명 화면에 출력
		System.out.format("%08X : %s%n", getCRC32Value(args[0]), args[0]);
		System.out.format("%X : %s%n", getCRC32Value(args[0]), args[0]);
	}


	public static long getCRC32Value(String filename) {
		Checksum crc = new CRC32();
		
		try {
			////////////////////////////////////////////////////////////////////////////////
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
			byte buffer[] = new byte[32768];
			int length = 0;
			
			while ((length = in.read(buffer)) >= 0)
				crc.update(buffer, 0, length);
			
			in.close();
		////////////////////////////////////////////////////////////////////////////////
		} catch (IOException e) {
			System.err.println(e);
			System.exit(2);
		}
		return crc.getValue();
	}
}
