import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress gameA = new GameProgress(100, 50, 10, 50.5 );
        GameProgress gameB = new GameProgress(75, 40, 7, 70.3 );
        GameProgress gameC = new GameProgress(200, 70, 3, 15.9 );

        saveGame("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameA.dat",gameA);
        saveGame("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameB.dat",gameB);
        saveGame("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameC.dat",gameC);

        ArrayList<String> saveList = new ArrayList<>();
        saveList.add("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameA.dat");
        saveList.add("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameB.dat");
        saveList.add("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameC.dat");

        zipFiles("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGame.zip", saveList);

        File saveADat = new File("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameA.dat");
        File saveBDat = new File("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameB.dat");
        File saveCDat = new File("C:\\Users\\alexe\\Documents\\Netology_Course\\Files\\Games\\savegames\\saveGameC.dat");
        if (saveADat.delete()) System.out.println("Файл \"saveGameA.dat\" удален");
        if (saveBDat.delete()) System.out.println("Файл \"saveGameB.dat\" удален");
        if (saveCDat.delete()) System.out.println("Файл \"saveGameC.dat\" удален");
    }

    public static void saveGame(String savePath, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(savePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String arcPath, ArrayList<String> listToZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(arcPath))) {
            for (String file : listToZip) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
