package com.bummy.web.util;

//네이버 음성합성 Open API 예제
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 * 1. maven project 아니어도 됨
 * 2. 제공받은      String clientId 와 clientSecret 값을 넣고 바로 실행한뒤 이 프로젝트를 리프레쉬하면 임의숫자.mp3가 보임
 * 3. 이 파일을 오디오 플레이어로 실행해 보면 '만나서 반갑습니다'라고 나옴
 * 4. 더 긴 텍스트를 읽어서 처리하도록 응용하기
 */
public class BreakTimeTTS {
	Resource resource;

	public Resource main(String breakTimeMsg, String user_id, Path location) {
			System.out.println("main");
	     String clientId = "Client Id Value";//애플리케이션 클라이언트 아이디값";
	     String clientSecret = "Client Secret Key";//애플리케이션 클라이언트 시크릿값";
	     try {
	    	 String text = URLEncoder.encode(breakTimeMsg, "UTF-8");
	    	   	 
	    	 // 중요
	         String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("POST");
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         
	         // post request
	         String postParams = "speaker=nara&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
	         con.setDoOutput(true);
	         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	         wr.writeBytes(postParams);
	         wr.flush();
	         wr.close();
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { // 정상 호출
	             InputStream is = con.getInputStream();
	             int read = 0;
	             byte[] bytes = new byte[1024];
	             
	             // mp3 파일 생성 new File(경로, user_id + ".mp3")
	             System.out.println(location.toString());
	             File f = new File(location.toString(), user_id + ".mp3");
	             f.createNewFile();
	             OutputStream outputStream = new FileOutputStream(f);
	             while ((read =is.read(bytes)) != -1) {
	                 outputStream.write(bytes, 0, read);
	             }
	             is.close();
	             System.out.println(f);
	             try {
	                 Path file = location.resolve(user_id + ".mp3").normalize();
	                  resource = new UrlResource(file.toUri());

	                 if(resource.exists() || resource.isReadable()) {
	                     System.out.println("file exists ^^:" + file);
	                     System.out.println("file exists **:" + file.toUri());
	                     System.out.println("file exists ==:" + resource.getFilename());
	                 }else {
	                	 System.out.println("Could not find file ㅠㅠ");
	                 }
	             } catch (Exception e) {
	            	 System.out.println("Could not download file");
	             }
    
	         } else {  // 오류 발생
	        	 br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	             String inputLine;
	             StringBuffer response = new StringBuffer();
	             while ((inputLine = br.readLine()) != null) {
	                 response.append(inputLine);
	             }
	             br.close();
	             System.out.println(response.toString());
	         }
	         return resource;
	     } catch (Exception e) {
	         System.out.println(e);
	         return null;
	     }
	 }
}