package kr.kro.hereinkorea.global.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/apii")
@Slf4j
public class OpenApiController {

    @GetMapping("/open-api")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1"
                + "?serviceKey=Had%2BznoLVPTQg7iygM9Hb%2Ba64OjDqjcztb2seubysp1ivP0dbmpaLeOYyaYhEmdCBu0skkp%2FiZEdP%2BqL%2BrAyew%3D%3D"
                + "&MobileOS=ETC&MobileApp=Test&_type=json"
                + "&pageNo=0&numOfRows=20&contentTypeId=32";

        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while((returnLine = br.readLine()) != null){
            result.append(returnLine+"\n\r");
        }

        urlConnection.disconnect();

        return  result.toString();
    }
}
