package com.capgemini.candidateorganizationsystem.helper;


import com.capgemini.candidateorganizationsystem.entities.Candidate;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "id", "firstName", "lastName", "phoneNumber", "mailAddress", "address", "expDuration","profile" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Candidate> csvToCandidates(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Candidate> candidates = new ArrayList<Candidate>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Candidate candidate = new Candidate(
              csvRecord.get("firstName"),
              csvRecord.get("lastName"),
                csvRecord.get("phoneNumber"),
                csvRecord.get("mailAddress"),
                csvRecord.get("address"),
                Integer.parseInt(csvRecord.get("expDuration")),
                csvRecord.get("profile")
            );

        candidates.add(candidate);
      }

      return candidates;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream CandidatesToCSV(List<Candidate> candidates) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Candidate candidate : candidates) {
        List<String> data = Arrays.asList(
              String.valueOf(candidate.getId()),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getPhoneNumber(),
                candidate.getMailAddress(),
                candidate.getAddress(),
                String.valueOf(candidate.getExpDuration()),
                candidate.getProfile()
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }

}
