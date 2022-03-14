package com.study;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealtimeLog {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	static final int classNameWidth = 30;
	static final int methodNameWidth = 15;
	
	public static void debug(StringBuffer _dump, String _msg, Object... _args) {
		if(_dump != null || log.isDebugEnabled()) {
			StackTraceElement[] ste = Thread.currentThread().getStackTrace();
			String fileName = ste[2].getFileName();
			String className = ste[2].getClassName();
			String methodName = ste[2].getMethodName();
			int lineNumber = ste[2].getLineNumber();
			
			int pos = className.length() >= classNameWidth ? className.length() - classNameWidth: 0;
			className = className.substring(pos);
			pos = methodName.length() >= methodNameWidth ? methodName.length() - methodNameWidth: 0;
			methodName = methodName.substring(pos);
			String result = String.format("%s %-" + classNameWidth + "s %-" + methodNameWidth + "s %-3d %s", sdf.format(System.currentTimeMillis()), className, methodName, lineNumber, _msg);
			if(_dump != null) {
				_dump.append(result);
				_dump.append("\r\n");
			}
			log.debug(result, _args);
		}
	}
	
	public static void info(StringBuffer _dump, String _msg, Object... _args) {
		if(_dump != null || log.isInfoEnabled()) {
			StackTraceElement[] ste = Thread.currentThread().getStackTrace();
			String fileName = ste[2].getFileName();
			String className = ste[2].getClassName();
			String methodName = ste[2].getMethodName();
			int lineNumber = ste[2].getLineNumber();
			
			int pos = className.length() >= classNameWidth ? className.length() - classNameWidth: 0;
			className = className.substring(pos);
			pos = methodName.length() >= methodNameWidth ? methodName.length() - methodNameWidth: 0;
			methodName = methodName.substring(pos);
			String result = String.format("%s %-" + classNameWidth + "s %-" + methodNameWidth + "s %-3d %s", sdf.format(System.currentTimeMillis()), className, methodName, lineNumber, _msg);
			if(_dump != null) {
				_dump.append(result);
				_dump.append("\r\n");
			}
			log.info(result, _args);
		}
	}
	
	public static void main(String[] args) {
		RealtimeLog st = new RealtimeLog();
		StringBuffer sb = new StringBuffer();
		
		debug(sb, "realtime log 1");
		
		info(null, "realtime log 2 {}, {}, {}", 1, "A", 3.5);
		
		debug(sb,  "realtime log 3");
		info(null, "realtime log 4");
		
		System.out.println(sb.toString());
	}

}
