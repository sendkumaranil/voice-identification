package com.hackathon.voiceidentification.api;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;


public class VoiceDetectation {
    /*public static void main(String[] args) throws IOException,Exception{
        String fileName = "C:\\Users\\Anil Kumar\\Downloads\\anilaudio.wav";
        Path path = Paths.get(fileName);
        byte[] content = Files.readAllBytes(path);
        transcribeModelSelection(content);
    }*/

    /**
 * Performs transcription of the given audio file synchronously with the selected model.
 *
 * @param fileName the path to a audio file to transcribe
 */
public static void transcribeModelSelection(byte[] content) throws Exception {

  try (SpeechClient speech = SpeechClient.create()) {
    // Configure request with video media type
    RecognitionConfig recConfig =
        RecognitionConfig.newBuilder()
            // encoding may either be omitted or must match the value in the file header
            .setEncoding(AudioEncoding.LINEAR16)
            .setLanguageCode("en-US")
            // sample rate hertz may be either be omitted or must match the value in the file
            // header
            .setSampleRateHertz(16000)
            .setModel("video")
            .build();

    RecognitionAudio recognitionAudio =
        RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(content)).build();

    RecognizeResponse recognizeResponse = speech.recognize(recConfig, recognitionAudio);
    // Just print the first result here.
    SpeechRecognitionResult result = recognizeResponse.getResultsList().get(0);
    // There can be several alternative transcripts for a given chunk of speech. Just use the
    // first (most likely) one here.
    SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
    System.out.printf("Transcript : %s\n", alternative.getTranscript());
  }
}
}
