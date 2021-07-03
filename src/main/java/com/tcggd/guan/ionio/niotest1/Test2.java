package com.tcggd.guan.ionio.niotest1;

import sun.nio.ch.FileChannelImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.*;
import java.util.EnumSet;
import java.util.Set;

import static java.nio.file.StandardOpenOption.*;
import static java.nio.file.attribute.PosixFilePermission.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        //step1 : set the file open attribute
        //use O_RDONLY O_WRONLY  and CREATE
        System.out.println(System.getProperty("jdk.nio.maxCachedBufferSize"));
        EnumSet<StandardOpenOption> options = EnumSet.of(READ);
        options.add(WRITE);
        // this just for verify the thread params in FileChannelImpl.open function
        options.add(CREATE);

        //step2 : if the file not exit, new file add set the file access
        //the access is 666
//        EnumSet<PosixFilePermission> accesses = EnumSet.of(OWNER_READ);
//        accesses.add(OWNER_WRITE);
//        accesses.add(GROUP_READ);
//        accesses.add(GROUP_WRITE);
//        accesses.add(OTHERS_READ);
//        accesses.add(OTHERS_WRITE);
        Set<PosixFilePermission> accs = PosixFilePermissions.fromString("rw-rw-rw-");
        FileAttribute<Set<PosixFilePermission>> attributes = PosixFilePermissions.asFileAttribute(accs);
        System.out.println(attributes.name());
        System.out.println(attributes.value());




        //Step 3:open  file /tmp/aa as rw and append
        // if the file not exit, then create the file use mask
        FileChannelImpl.open(Paths.get("/tmp/aa"), options, attributes);

    }
}
