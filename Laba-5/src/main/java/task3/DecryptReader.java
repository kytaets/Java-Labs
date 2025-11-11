package task3;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptReader extends FilterReader {
    private final char key;

    protected DecryptReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1) ? -1 : (c - key);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int readCount = super.read(cbuf, off, len);
        if (readCount == -1) return -1;
        for (int i = off; i < off + readCount; i++) {
            cbuf[i] = (char)(cbuf[i] - key);
        }
        return readCount;
    }
}
