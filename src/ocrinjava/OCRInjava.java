/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocrinjava;



import org.junit.Test;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;
import static org.junit.Assert.assertTrue;

public class OCRInjava {

    public void givenTessBaseApi_whenImageOcrd_thenTextDisplayed() throws Exception {

    }

    public static void main(String args[]) {
        try {
            
            BytePointer outText;

            TessBaseAPI api = new TessBaseAPI();
            // Initialize tesseract-ocr with English, without specifying tessdata path
            if (api.Init(".", "ENG") != 0) {
                System.err.println("Could not initialize tesseract.");
                System.exit(1);
            }

            // Open input image with leptonica library
            PIX image = pixRead("D:\\error1.bmp");
            api.SetImage(image);
            // Get OCR result
            outText = api.GetUTF8Text();
            String string = outText.getString();
            assertTrue(!string.isEmpty());
            System.out.println("OCR output:\n" + string);

            // Destroy used object and release memory
            api.End();
            outText.deallocate();
            pixDestroy(image);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
