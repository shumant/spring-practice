package com.example.svgToJpg;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;

import java.io.*;

public class ExTranscoder {
    public static void main(String[] args) throws Exception {
        OutputStream outputStream = new FileOutputStream(new File("/Users/Antony/Desktop/1level.jpg"));
        InputStream svg = new FileInputStream("/Users/Antony/Desktop/1level.svg");
        JPEGTranscoder transcoder = new JPEGTranscoder();

        transcoder.addTranscodingHint(JPEGTranscoder.KEY_WIDTH, 325f);
        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 1f);

        TranscoderInput input = new TranscoderInput(svg);

        TranscoderOutput output = new TranscoderOutput(outputStream);

        transcoder.transcode(input, output);

        outputStream.flush();
        outputStream.close();
    }
}
