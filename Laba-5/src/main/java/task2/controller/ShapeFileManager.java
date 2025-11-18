package task2.controller;

import task2.model.Shape;

import java.io.*;
import java.util.List;

public class ShapeFileManager {

    /**
     * Зберігає список фігур у вказаний файл.
     */
    public void saveShapes(List<Shape> shapes, String directoryPath, String filename) throws IOException {
        File dir = new File(directoryPath);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Could not create directory: " + directoryPath);
        }

        File file = new File(dir, filename);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(shapes);
        }
    }

    /**
     * Завантажує список фігур з файлу.
     */
    @SuppressWarnings("unchecked")
    public List<Shape> loadShapes(String directoryPath, String filename)
            throws IOException, ClassNotFoundException {

        File file = new File(directoryPath, filename);

        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getPath());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                return (List<Shape>) obj;
            } else {
                throw new IOException("Invalid file format");
            }
        }
    }
}
