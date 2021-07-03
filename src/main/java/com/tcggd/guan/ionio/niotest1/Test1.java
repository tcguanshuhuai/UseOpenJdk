package com.tcggd.guan.ionio.niotest1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("/tmp/hh");
        FileChannel channel = outputStream.getChannel();
        ByteBuffer wrap = ByteBuffer.wrap("Hello world".getBytes());
        channel.write(wrap);
        channel.close();
    }
}
