package com.hackathon.voiceidentification.api;


import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class VoiceIdentificationController {

    
    @GetMapping({"/","/health","/welcome"})
    public String health(){
        return "Welcome to Voice Identification App";
    }

    @PostMapping(value = "/voice/analyze",produces = {MediaType.APPLICATION_JSON_VALUE})
    public String voiceIdentification(@RequestPart MultipartFile audioFile) {
        try{
            byte[] audioInByte = audioFile.getBytes();
        }catch(Exception e){
            return "{\r\n" + //
                                "    \"error\":\"Error Occurred While Analysis, Please Try Again!\"\r\n" + //
                                "}";
        }

        return "{\r\n" + //
                        "    \"status\": \"Success\",\r\n" + //
                        "    \"analysis\": {\r\n" + //
                        "        \"detectedVoice\": true,\r\n" + //
                        "        \"voiceType\": \"human\",\r\n" + //
                        "        \"confidenceScore\": {\r\n" + //
                        "            \"aiProbability\": 5,\r\n" + //
                        "            \"humanProbability\": 95\r\n" + //
                        "        },\r\n" + //
                        "        \"additionalInfo\": {\r\n" + //
                        "            \"emotionalTone\": \"neutral\",\r\n" + //
                        "            \"backgroundNoiseLevel\": \"low\"\r\n" + //
                        "        }\r\n" + //
                        "    }\r\n" + //
                        "}";
    }

}
