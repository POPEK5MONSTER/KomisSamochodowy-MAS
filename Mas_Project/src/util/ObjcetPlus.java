package util;

import java.io.*;
import java.util.*;

public class ObjcetPlus implements Serializable {
    private static Map<Class, List> carExtent = new HashMap<>();

    public static final String FILE_NAME = "MyExtent.ser";

    public ObjcetPlus() {
        addExtent();
    }

    public static void save() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(carExtent);
            System.out.println("Saved");
        }
    }

    public static void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            carExtent = (Map<Class, List>) ois.readObject();
            System.out.println("Loaded");
        }
    }

    public static <T> List<T>  getExtentFromClass(Class<T> c) {
        carExtent.computeIfAbsent(c, x -> new ArrayList<>());
        return Collections.unmodifiableList(carExtent.get(c));
    }

    public void addExtent(){
        List list = carExtent.computeIfAbsent(this.getClass(), x -> new ArrayList<>());
        list.add(this);
    }

    public void removeExtent(){
        List list = carExtent.get(this.getClass());
        if (list != null){
            list.remove(this);
        }
    }
}
