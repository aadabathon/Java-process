
// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Document;
// import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import java.io.FileOutputStream;
// import java.io.IOException;

// public class SECScraper {
//     public static void main(String[] args) {
//         try {
//             // Step 1: Scrape SEC Website
//             String url = "https://www.sec.gov/Archives/edgar/data/0000320193/000032019323000010/Financial_Report.html"; // Example URL
//             Document doc = Jsoup.connect(url).get();

//             // Step 2: Extract Financial Data
//             Elements tables = doc.select("table");
//             for (Element table : tables) {
//                 Elements rows = table.select("tr");
//                 for (Element row : rows) {
//                     Elements cells = row.select("td");
//                     for (Element cell : cells) {
//                         System.out.println(cell.text()); // Print data for now
//                     }
//                 }
//             }

//             // Step 3: Write Data to Excel (DCF Model)
//             Workbook workbook = new XSSFWorkbook();
//             Sheet sheet = workbook.createSheet("Financial Data");

//             // Example data insertion
//             Row row = sheet.createRow(0);
//             Cell cell = row.createCell(0);
//             cell.setCellValue("Revenue");

//             // Step 4: Save to Excel File
//             try (FileOutputStream fileOut = new FileOutputStream("DCF_Model.xlsx")) {
//                 workbook.write(fileOut);
//             }

//             workbook.close();

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
