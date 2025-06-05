package po_pr_kod_g15;

import java.io.FileWriter;
import java.io.IOException;

//Klasa odpowiedzialna za zapis raportu symulacji do pliku.
 
public class Report {

    /**
     * Zapisuje zawartość raportu do wskazanego pliku
     * @param content  Tekst raportu do zapisania
     * @throws IOException Jeśli wystąpi błąd podczas zapisu
     */
    public static void saveReport(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }
}
