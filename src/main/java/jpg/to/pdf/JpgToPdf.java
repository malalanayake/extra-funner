package jpg.to.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JpgToPdf {

    public static final String SAMPLE_JPG_BASED_FOLDER = "sample/JpgToPdf";
    public static final String OUTPUT_FILE = "output.pdf";
    public static Logger LOG = Logger.getLogger(JpgToPdf.class.getName());

    public static void main(String arg[]) throws Exception {
        File root = new File(SAMPLE_JPG_BASED_FOLDER);

        List<String> files = new ArrayList<String>();
        for (int i = 1; i < 11; i++) {
            files.add(i + ".jpg");
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(root, OUTPUT_FILE)));
        document.open();
        files.forEach(f -> {
            try {
                document.newPage();
                Image image = Image.getInstance(new File(root, f).getAbsolutePath());
                image.setAbsolutePosition(0, 0);
                image.setBorderWidth(0);
                image.scaleAbsolute(PageSize.A4);
                document.add(image);
            } catch (Exception ex) {
                LOG.info(ex.getMessage());
                ex.printStackTrace();
            }
        });
        document.close();
    }
}